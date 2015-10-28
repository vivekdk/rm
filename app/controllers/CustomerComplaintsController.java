package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import com.fasterxml.jackson.databind.node.ObjectNode; 
import com.fasterxml.jackson.databind.node.ArrayNode; 
import play.libs.Json;


import java.util.*;

import com.redmart.assignment.complaints.*;
import org.bson.types.ObjectId;

public class CustomerComplaintsController extends Controller {

	public Result preflight(String all) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Allow", "*");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
        return ok();
    }

	
//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
    public Result getAllComplaints() {	
				
		ObjectNode result = Json.newObject();
		ArrayNode complaintsArr = result.putArray ("complaints");
		List complaints = Complaint.getAllComplaints();
		Iterator itr = complaints.iterator();
		while (itr.hasNext()){
			Complaint c = (Complaint) itr.next();
			
			ObjectNode tmp = Json.newObject();
			tmp.put ("id", c.getId());
			tmp.put ("complaintMessage", c.getComplaintMessage());
			tmp.put ("status", c.getStatus());
			tmp.put ("assignedTo", c.getAssignedTo());
			tmp.put ("createdOn", c.getCreatedOn());
			
			ArrayNode comments = tmp.putArray ("comments");
			Iterator itrComments = c.getComments().iterator();
			while (itrComments.hasNext()){
				Comment comment = (Comment) itrComments.next();
				ObjectNode tmpComment = Json.newObject();
				tmpComment.put ("comment", comment.getComment());
				tmpComment.put ("commentedOn", comment.getCommentedOn());
				tmpComment.put ("customer", comment.getCustomer().getName());
				comments.add (tmpComment);
			}
			
			ObjectNode tmpCust = Json.newObject();
			tmpCust.put ("id", c.getCustomer().getId());
			tmpCust.put ("name", c.getCustomer().getName());
			tmpCust.put ("phone", c.getCustomer().getPhone());
			tmpCust.put ("address", c.getCustomer().getAddress());
			tmpCust.put ("city", c.getCustomer().getCity());
			tmp.put ("customer", tmpCust);			
			
			complaintsArr.add(tmp);
		}		
        return ok(result);
	}
	
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
    public Result createComplaint() {	
	
		ObjectNode request = (ObjectNode) request().body().asJson();		
		
		Complaint newComplaint = new Complaint();
		newComplaint.setComplaintMessage(request.get("complaintMessage").textValue());
		newComplaint.setCreatedOn (new Date().getTime());
		newComplaint.setCustomer (request.get("customerId").textValue());
		newComplaint.setStatus ("NEW");	
		newComplaint.save();		
		
		ObjectNode result = Json.newObject();		
		result.put("status", "OK");
		result.put("message", "Customer Complaint created successfully");
		
		return ok(result);
	}
	
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public Result updateStatus(String complaintId, String status){
		System.out.println (complaintId + "" + status);
		
		Complaint complaint = MongoSingleton.getMongoDS().get(Complaint.class, new ObjectId(complaintId));
		complaint.setStatus (status);
		complaint.save();
		
		ObjectNode result = Json.newObject();
		result.put("status", "OK");
		result.put("message", "Complaint status changed successfully");
		return ok(result);		
	}
	
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public Result addComment(String complaintId, String customerId){
	
		ObjectNode request = (ObjectNode) request().body().asJson();	
		
		Comment newComment = new Comment (request.get("comment").textValue(), customerId);
		Complaint complaint = MongoSingleton.getMongoDS().get(Complaint.class, new ObjectId(complaintId));
		complaint.setComments (newComment);
		complaint.save();
		
		ObjectNode result = Json.newObject();
		result.put("status", "OK");
		result.put("message", "Comment added successfully");
		return ok(result);		
	}	
	
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public Result assignTo(String complaintId, String assignTo){
	
		Complaint complaint = MongoSingleton.getMongoDS().get(Complaint.class, new ObjectId(complaintId));
		complaint.setAssignedTo (assignTo);
		complaint.save();
		
		ObjectNode result = Json.newObject();
		result.put("status", "OK");
		result.put("message", "Assigned to " + assignTo + " successfully");
		return ok(result);		
	}	

	public Result getAllCustomers(){
	
		ObjectNode result = Json.newObject();
		ArrayNode customerArr = result.putArray ("customers");
		List customers = Customer.getAllCustomers();
		Iterator itr = customers.iterator();
		while (itr.hasNext()){
			Customer c = (Customer) itr.next();
			
			ObjectNode tmp = Json.newObject();
			tmp.put ("id", c.getId());
			tmp.put ("name", c.getName());
			tmp.put ("phone", c.getPhone());
			tmp.put ("city", c.getCity());						
			
			customerArr.add(tmp);
		}		
        return ok(result);	
	}	
}
