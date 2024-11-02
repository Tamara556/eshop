package managers;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductManager {
    private Connection connection;

    public ProductManager(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(String name, String description, double price, int quantity, int categoryId) throws SQLException {
        String sql = "INSERT INTO Product (name, description, price, quantity, category_id) VALUES ('" + name + "', '" + description + "', " + price + ", " + quantity + ", " + categoryId + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void editProduct(int id, String name, String description, double price, int quantity, int categoryId) throws SQLException {
        String sql = "UPDATE Product SET name = '" + name + "', description = '" + description + "', price = " + price + ", quantity = " + quantity + ", category_id = " + categoryId + " WHERE id = " + id;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id = " + id;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    // Aggregation methods
    public int countProducts() throws SQLException {
        String sql = "SELECT SUM(quantity) FROM Product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public double maxPrice() throws SQLException {
        String sql = "SELECT MAX(price) FROM Product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getDouble(1) : 0;
        }
    }

    public double minPrice() throws SQLException {
        String sql = "SELECT MIN(price) FROM Product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getDouble(1) : 0;
        }
    }

    public double avgPrice() throws SQLException {
        String sql = "SELECT AVG(price) FROM Product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getDouble(1) : 0;
        }
    }
}
