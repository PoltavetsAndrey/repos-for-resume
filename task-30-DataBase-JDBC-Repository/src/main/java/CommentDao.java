import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CommentDao {

    private final Connection connection;

    public CommentDao(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS comments ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "text_comment VARCHAR(1000), " +
                    "book_id INTEGER, " +
                    "user_id INTEGER" +
                    ")");
        }
    }

    public Collection<Comment> getAll() throws SQLException {
        Collection<Comment> commentCollection = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet cursor = statement.executeQuery("SELECT * FROM comments");
            while (cursor.next()) {
                commentCollection.add(createCommentFromCursorIfPossible(cursor));
            }
        }
        return commentCollection;
    }

    private Comment createCommentFromCursorIfPossible(ResultSet cursor) throws SQLException {
        Comment comment = new Comment();
        comment.id = cursor.getInt("id");
        comment.textComment = cursor.getString("text_comment");
        comment.bookId = cursor.getInt("book_id");
        comment.userId = cursor.getInt("user_id");
        return comment;
    }

    public void insert(Comment comment) throws SQLException {
        if (comment.id != 0) {
            throw new IllegalArgumentException("ID is: " + comment.id);
        }
        final String sql = "INSERT INTO comments (text_comment, book_id, user_id) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comment.textComment);
            statement.setInt(2, comment.bookId);
            statement.setInt(3, comment.userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating comment failed, no rows affected");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()) {
                    comment.id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating comment failed, no ID obtained");
                }
            }
        }
    }

    public void delete(int id) throws SQLException {
        if (id == 0) {
            throw new IllegalArgumentException("ID is: " + id);
        }
        final String sql = "DELETE FROM comments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Removal comment failed, no rows affected");
            }
        }
    }

    public Collection<Comment> findByBook(Book book) throws SQLException {
        Collection<Comment> comments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM comments" +
                        //"JOIN book ON comments.book_id = book.id " +
                        " WHERE book_id LIKE ?")) {
            statement.setInt(1, book.id);
            ResultSet cursor = statement.executeQuery();
            while (cursor.next()) {
                comments.add(createCommentFromCursorIfPossible(cursor));
            }
            return comments;
        }
    }

    public void update(Comment comment) throws SQLException {
        if(comment.id == 0) {
            throw new IllegalArgumentException("Id is not set");
        }
        String sql = "UPDATE comments SET text_comment = ?, book_id = ?, user_id = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, comment.textComment);
            statement.setInt(2, comment.bookId);
            statement.setInt(3, comment.userId);
            statement.setInt(4, comment.id);
            statement.executeUpdate();
        }
    }
}
