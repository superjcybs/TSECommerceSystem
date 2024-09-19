package com.ecommerce;

import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String name;
    private ArrayList<Product> shoppingCart;

    public Customer(int customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    // Add product to shopping cart
    public void addToCart(Product product) {
        shoppingCart.add(product);
        System.out.println(product.getName() + " added to the cart.");
    }

    // Remove product from shopping cart
    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
        System.out.println(product.getName() + " removed from the cart.");
    }

    // Calculate total cost of products in the shopping cart
    public double calculateTotal() {
        double total = 0;
        for (Product product : shoppingCart) {
            total += product.getPrice();
        }
        return total;
    }

    // Place order
    public void placeOrder() {
        System.out.println("+------------------------------------------+");
        System.out.println("Order placed by " + name + ". Total cost: $" + calculateTotal());
        System.out.println("+------------------------------------------+");
    }
}