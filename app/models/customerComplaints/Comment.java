package com.redmart.assignment.complaints;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.Date;


@Entity("Comments")
@Embedded
public class Comment {
    @Id
    private ObjectId id;
    private String comment;
	private long commentedOn;

    @Reference
    private Customer customer;	
	
	public Comment (){
	}
	
	public Comment (String comment, String customerId){
		this.comment = comment;
		this.setCustomer (customerId);
		this.commentedOn = new Date().getTime();
	}
	
	public String getId(){
		return this.id.toString();
	}

	public String getComment(){
		return this.comment;
	}
	
	public long getCommentedOn(){
		return this.commentedOn;
	}	

	public Customer getCustomer(){
		return this.customer;
	}
	
	public void setCustomer(String customerId){
		this.customer  =  MongoSingleton.getMongoDS().get(Customer.class, new ObjectId(customerId));
	}	
}