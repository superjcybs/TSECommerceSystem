import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> productCatalog = new ArrayList<>();
    private static Customer customer = new Customer(1, "John Doe");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TS E-Commerce System");
            System.out.println("1. Add Product to Catalog");
            System.out.println("2. View Products in Catalog");
            System.out.println("3. Show Cart Details");
            System.out.println("4. Show Total Cart Amount");
            System.out.println("5. Edit Cart (Add/Remove Products)");
            System.out.println("6. Place Order");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = getValidInt(scanner);

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    showCartDetails();
                    break;
                case 4:
                    showTotalCartAmount();
                    break;
                case 5:
                    editCart(scanner);
                    break;
                case 6:
                    placeOrder();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // Option 1: Add Product to Catalog
    private static void addProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productID = getValidInt(scanner);

        scanner.nextLine(); // Consume newline
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = getValidDouble(scanner);

        Product product = new Product(productID, name, price);
        productCatalog.add(product);
        System.out.println("Product added: " + product);
    }

    // Option 2: View Products in Catalog
    private static void viewProducts() {
        if (productCatalog.isEmpty()) {
            System.out.println("No products available in the catalog.");
        } else {
            System.out.println("\n--- Product Catalog ---");
            for (Product product : productCatalog) {
                System.out.println(product);
            }
        }
    }

    // Option 3: Show Cart Details
    private static void showCartDetails() {
        if (customer.getShoppingCart().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("\n--- Shopping Cart ---");
            for (Product product : customer.getShoppingCart()) {
                System.out.println(product);
            }
        }
    }

    // Option 4: Show Total Cart Amount
    private static void showTotalCartAmount() {
        System.out.println("Total Cart Amount: $" + customer.calculateTotal());
    }

    // Option 5: Edit Cart (Add/Remove Products)
    private static void editCart(Scanner scanner) {
        System.out.println("\n--- Edit Cart ---");
        System.out.println("1. Add Product to Cart");
        System.out.println("2. Remove Product from Cart");
        System.out.print("Choose an option: ");
        int choice = getValidInt(scanner);

        switch (choice) {
            case 1:
                addProductToCart(scanner);
                break;
            case 2:
                removeProductFromCart(scanner);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Helper method: Add Product to Cart
    private static void addProductToCart(Scanner scanner) {
        System.out.print("Enter Product ID to add to cart: ");
        int productID = getValidInt(scanner);

        Product productToAdd = findProductByID(productID);
        if (productToAdd != null) {
            customer.addToCart(productToAdd);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Helper method: Remove Product from Cart
    private static void removeProductFromCart(Scanner scanner) {
        System.out.print("Enter Product ID to remove from cart: ");
        int productID = getValidInt(scanner);

        Product productToRemove = findProductInCartByID(productID);
        if (productToRemove != null) {
            customer.removeFromCart(productToRemove);
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    // Option 6: Place Order
    private static void placeOrder() {
        if (customer.getShoppingCart().isEmpty()) {
            System.out.println("Your cart is empty. Add some products before placing an order.");
        } else {
            Order order = new Order(1001, customer);
            System.out.println("\n--- Order Summary ---");
            System.out.println(order.orderSummary());
        }
    }

    // Find a product by ID in the catalog
    private static Product findProductByID(int productID) {
        for (Product product : productCatalog) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    // Find a product by ID in the customer's cart
    private static Product findProductInCartByID(int productID) {
        for (Product product : customer.getShoppingCart()) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    // Get a valid integer from user input with exception handling
    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Get a valid double from user input with exception handling
    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
