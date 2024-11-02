package managers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryManager {
    private Connection connection;

    public CategoryManager(Connection connection) {
        this.connection = connection;
    }

    public void addCategory(String name) throws SQLException {
        String sql = "INSERT INTO Category (name) VALUES ('" + name + "')";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void editCategory(int id, String name) throws SQLException {
        String sql = "UPDATE Category SET name = '" + name + "' WHERE id = " + id;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM Category WHERE id = " + id;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
