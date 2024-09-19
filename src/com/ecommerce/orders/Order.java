package com.ecommerce.orders;

import com.ecommerce.Customer; // import necessary packages
import com.ecommerce.Product;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalCost;

    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = new ArrayList<>(customer.getShoppingCart());
        this.totalCost = customer.calculateTotal();
    }

    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Generate order summary
    public String orderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Products:\n");
        for (Product product : products) {
            summary.append(product.toString()).append("\n");
        }
        summary.append("+------------------------------------------+\n");
        summary.append("Total Cost: $").append(totalCost).append("\n");
        summary.append("+------------------------------------------+");

        return summary.toString();
    }
}