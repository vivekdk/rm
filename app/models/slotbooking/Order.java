package com.redmart.assignment.slotbooking;

import java.util.ArrayList;

/**
 * Created by user on 10/23/2015.
 */
public class Order {
    private String id;
    private ArrayList<Product> items;
    private int dimension;
    private double totalCost;

    public String getId() {
        return id;
    }

    private int getTotalDimension (){
        int dimension = 0;
        for (Product p : items){
            dimension += p.getDimension();
        }
        return dimension;
    }

    private double getTotalAmount (){
        double cost = 0;
        for (Product p : items){
            cost  += p.getAmount();
        }
        return cost;
    }

    public Order(String id, ArrayList<Product> items) {
        this.id = id;
        this.items = items;
        this.dimension = 0;
        this.totalCost = 0;

        for (Product p : items){
            this.dimension += p.getDimension();
            this.totalCost += p.getAmount();
        }
    }

    public int getDimension() {
        return dimension;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void addItem(Product p) {
        this.items.add (p);
        this.totalCost = this.getTotalAmount();
        this.dimension = this.getTotalDimension();
    }

    @Override
    public String toString() {
        return "Order{" +
                "dimension=" + dimension +
                ", items=" + items +
                ", totalCost=" + totalCost +
                '}';
    }
}
