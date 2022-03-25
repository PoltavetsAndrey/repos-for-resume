import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class AuthorDao {

    private final Connection connection;

    public AuthorDao(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS authors (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(100), " +
                "birth_year INTEGER" +
                ")");
    }

    public Collection<Author> getAll() throws SQLException {
        Collection<Author> collectionAuthors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet cursor = statement.executeQuery("SELECT * FROM authors");
            while (cursor.next()) {
                collectionAuthors.add(createAuthorFromCursorIfPossible(cursor));
            }
        }
        return collectionAuthors;
    }

    public Optional<Author> getById(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet cursor = statement.executeQuery(String.format(
                    "SELECT * FROM authors WHERE id = %d", id));
            if (cursor.next()) {
                return Optional.of(createAuthorFromCursorIfPossible(cursor));

            } else {
                return Optional.empty();
            }
        }
    }

    public Collection<Author> findByName(String text) throws SQLException {
        Collection<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors " +
                   "WHERE name LIKE ?")) {
                statement.setString(1,"%" + text + "%");
                ResultSet cursor = statement.executeQuery();
                if (cursor.next()) {
                    authors.add(createAuthorFromCursorIfPossible(cursor));
                }
            } return authors;
        }

    private Author createAuthorFromCursorIfPossible(ResultSet cursor) throws SQLException {
        Author author = new Author();
        author.id = cursor.getInt("id");
        author.name = cursor.getString("name");
        author.birthYear = cursor.getInt("birth_year");
        return author;
    }

    public void update(Author author) throws SQLException {
        if(author.id == 0) {
            throw new IllegalArgumentException("Id is not set");
        }
        String sql = "UPDATE authors SET name = ?, birth_year = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, author.name);
            statement.setInt(2, author.birthYear);
            statement.setInt(3, author.id);
            statement.executeUpdate();
        }
    }

    public void insert(Author author) throws SQLException {
        if (author.id != 0) {
            throw new IllegalArgumentException("ID is: " + author.id);
        }
        final String sql = "INSERT INTO authors (name, birth_year) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.name);
            statement.setInt(2, author.birthYear);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating author failed, no rows affected");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()) {
                    author.id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating author failed, no ID obtained");
                }
            }
        }
    }

    public Collection<Author> findByBook(Book book) throws SQLException {
        Collection<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM authors" +
                                " WHERE id LIKE ?")) {
            statement.setInt(1,  book.authorId);
            ResultSet cursor = statement.executeQuery();
            while (cursor.next()) {
                authors.add(createAuthorFromCursorIfPossible(cursor));
            }
            return authors;
        }
    }

    public void delete(int id) throws SQLException {
        if (id == 0) {
            throw new IllegalArgumentException("ID is: " + id);
        }
        final String sql = "DELETE FROM authors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Removal author failed, no rows affected");
            }
        }
    }
}
