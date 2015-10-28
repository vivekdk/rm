package com.redmart.assignment.complaints;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.mongodb.morphia.*;
import org.mongodb.morphia.query.*;


@Entity("Complaints")

public class Complaint {
    @Id
    private ObjectId id;
    private long createdOn;
	private String complaintMessage;
	private String assignedTo;
	private ComplaintStatus status;
	
    @Reference
    private Customer customer;
	
	@Embedded
    private List<Comment> comments = new ArrayList<Comment>();	
	
	public Complaint (){
	}	
	
	public String toString (){
		return this.createdOn + "-" + this.customer;
	}
	
	public String getId(){
		return this.id.toString();
	}

	public long getCreatedOn(){
		return this.createdOn;
	}
	
	public List getComments(){
		return this.comments;
	}	
	
	public String getAssignedTo(){
		return this.assignedTo;
	}		
		
	public String getStatus(){
		return this.status.toString();
	}

	public Customer getCustomer(){
		return this.customer;
	}

	public void setCreatedOn(long createdOn){
		this.createdOn = createdOn;
	}
	
	public void setComments(Comment comment){
		this.comments.add(comment);
	}	
	
	public void setAssignedTo(String assignedTo){
		this.assignedTo = assignedTo;
	}		
		
	public void setStatus(String status){
		this.status = ComplaintStatus.valueOf(status);
	}

	public void setCustomer(String customerId){
		this.customer  =  MongoSingleton.getMongoDS().get(Customer.class, new ObjectId(customerId));
	}
	
	public String getComplaintMessage(){
		return this.complaintMessage;
	}

	public void setComplaintMessage(String message){
		this.complaintMessage = message;
	}	
	
	// These things are better done in a DAO for the entity
	// However for this exercise, i am letting it here
	public static List getAllComplaints (){
		final Query<Complaint> query = MongoSingleton.getMongoDS().createQuery(Complaint.class);
		final List<Complaint> complaints = query.asList();	
		
		return complaints;
	}
	
	public boolean save (){
		MongoSingleton.getMongoDS().save(this);
		return true;
	}
}