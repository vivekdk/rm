package com.redmart.assignment.complaints;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.ArrayList;

import org.mongodb.morphia.*;
import org.mongodb.morphia.query.*;

@Entity("Customer")

public class Customer {
    @Id
    private ObjectId id;
    private String name;
	private String phone;
	private String city;
	private String address;
	
	
	public String getId(){
		return this.id.toString();
	}

	public String getName(){
		return this.name;
	}
	
	public String getPhone(){
		return this.phone;
	}	

	public String getCity(){
		return this.city;
	}

	public String getAddress(){
		return this.address;
	}	
	
	public String toString (){
		return this.name + "-" + this.phone + "-" + this.city + "-" + this.address;
	}

	// This method is better written in a DAO for the entity
	// However for this exercise, i am letting it stay here
	public static List getAllCustomers (){
		final Query<Customer> query = MongoSingleton.getMongoDS().createQuery(Customer.class);
		final List<Customer> customers = query.asList();	
		
		return customers;
	}
}