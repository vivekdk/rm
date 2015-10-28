package com.redmart.assignment.slotbooking.exceptions;

/**
 * Created by user on 10/24/2015.
 */
public class OrderOutsideOperatingHoursException extends Exception {
    public OrderOutsideOperatingHoursException(String message) {
        super(message);
    }
}
