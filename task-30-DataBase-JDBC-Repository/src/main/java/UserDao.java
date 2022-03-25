import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDao {

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(100)" +
                    ")");
        }
    }

    public Collection<User> getAll() throws SQLException {
        Collection<User> userCollection = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet cursor = statement.executeQuery("SELECT * FROM users");
            while (cursor.next()) {
                userCollection.add(createAuthorFromCursorIfPossible(cursor));
            }
        }
        return userCollection;
    }

    private User createAuthorFromCursorIfPossible(ResultSet cursor) throws SQLException {
        User user = new User();
        user.id = cursor.getInt("id");
        user.name = cursor.getString("name");
        return user;
    }

    public void insert(User user) throws SQLException {
        if (user.id != 0) {
            throw new IllegalArgumentException("ID is: " + user.id);
        }
        final String sql = "INSERT INTO users (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.name);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()) {
                    user.id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }
        }
    }

    public void update(User user) throws SQLException {
        if(user.id == 0) {
            throw new IllegalArgumentException("Id is not set");
        }
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, user.name);
            statement.setInt(2, user.id);
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        if (id == 0) {
            throw new IllegalArgumentException("ID is: " + id);
        }
        final String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Removal user failed, no rows affected");
            }
        }
    }
}
