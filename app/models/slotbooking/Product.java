package com.redmart.assignment.slotbooking;

/**
 * Created by user on 10/23/2015.
 */
public class Product {
    private String id;
    private String description; // no localization for now
    private float amount;      // i am not considering the currency
    private int dimension; // in cubic inches (volume)

    public Product(String id, String description, int dimension, float amount) {
        this.id = id;
        this.dimension = dimension;
        this.description = description;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "amount=" + amount +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", dimension=" + dimension +
                '}';
    }
}
