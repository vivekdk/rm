package com.redmart.assignment.slotbooking;

/**
 * Created by user on 10/23/2015.
 */
public class Customer {
    private String name;
    private String phone;
    private String email;
    private Address address;
    // there are lot of other information about the user that can be captured.. however ignoring for this assignment

    public Customer(String name, String phone, String email, Address address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
