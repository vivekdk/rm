package com.redmart.assignment.complaints;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity("Complaints")

public class Complaints {
    @Id
    private ObjectId id;
    private long createdOn;
    @Reference
    private Customer customer;
	
	public String toString (){
		return createdOn + "-" + customer;
	}
}