
import managers.CategoryManager;
import managers.ProductManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/eshop";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner scanner = new Scanner(System.in)) {

            CategoryManager categoryManager = new CategoryManager(connection);
            ProductManager productManager = new ProductManager(connection);
            String command;

            while (true) {
                System.out.println("Commands: exit, add category, edit category, delete category, add product, edit product, delete product, print sum, print max, print min, print avg");
                command = scanner.nextLine();

                switch (command.toLowerCase()) {
                    case "exit":
                        return;
                    case "add category":
                        System.out.print("Enter category name: ");
                        String categoryName = scanner.nextLine();
                        categoryManager.addCategory(categoryName);
                        break;
                    case "edit category":
                        System.out.print("Enter category ID: ");
                        int editCatId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new name: ");
                        String newCatName = scanner.nextLine();
                        categoryManager.editCategory(editCatId, newCatName);
                        break;
                    case "delete category":
                        System.out.print("Enter category ID: ");
                        int delCatId = Integer.parseInt(scanner.nextLine());
                        categoryManager.deleteCategory(delCatId);
                        break;
                    case "add product":
                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String productDescription = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double productPrice = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter quantity: ");
                        int productQuantity = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter category ID: ");
                        int productCategoryId = Integer.parseInt(scanner.nextLine());
                        productManager.addProduct(productName, productDescription, productPrice, productQuantity, productCategoryId);
                        break;
                    case "edit product":
                        System.out.print("Enter product ID: ");
                        int editProdId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new name: ");
                        String newProdName = scanner.nextLine();
                        System.out.print("Enter new description: ");
                        String newProdDesc = scanner.nextLine();
                        System.out.print("Enter new price: ");
                        double newProdPrice = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter new quantity: ");
                        int newProdQuantity = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new category ID: ");
                        int newProdCategoryId = Integer.parseInt(scanner.nextLine());
                        productManager.editProduct(editProdId, newProdName, newProdDesc, newProdPrice, newProdQuantity, newProdCategoryId);
                        break;
                    case "delete product":
                        System.out.print("Enter product ID: ");
                        int delProdId = Integer.parseInt(scanner.nextLine());
                        productManager.deleteProduct(delProdId);
                        break;
                    case "print sum":
                        System.out.println("Total products in stock: " + productManager.countProducts());
                        break;
                    case "print max":
                        System.out.println("Most expensive product price: " + productManager.maxPrice());
                        break;
                    case "print min":
                        System.out.println("Cheapest product price: " + productManager.minPrice());
                        break;
                    case "print avg":
                        System.out.println("Average product price: " + productManager.avgPrice());
                        break;
                    default:
                        System.out.println("Unknown command!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
