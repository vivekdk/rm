package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;                     
import play.libs.Json;
import play.libs.Json.*;                        

import static play.libs.Json.toJson;

import com.fasterxml.jackson.databind.JsonNode;     
import com.fasterxml.jackson.databind.node.ArrayNode;           
import com.fasterxml.jackson.databind.node.ObjectNode;   
import views.html.*;

import com.redmart.assignment.slotbooking.*;
import com.redmart.assignment.slotbooking.exceptions.*;
import java.util.ArrayList;

public class SlotBookingController extends Controller {

	@BodyParser.Of(play.mvc.BodyParser.Json.class)
    public Result checkSlot() {
		JsonNode json = request().body().asJson();
		JsonNode metaInfo = json.get("meta");
		JsonNode vans = metaInfo.get("vans");
		JsonNode products = metaInfo.get("products");
		JsonNode customers = metaInfo.get("customers");
		JsonNode orderInfo = json.get("order");
		
		ObjectNode result = Json.newObject();
				
		try{		

			SlotBooking a = new SlotBooking(new TimeRange(0, vans.get("operatingHours").get("from").intValue(), vans.get("operatingHours").get("to").intValue()), vans.get("num").intValue(), vans.get("maxCapacity").intValue());
			
			for (JsonNode order : orderInfo){	
				try{
				
					JsonNode productsInOrder = order.get("products");
					ArrayList<Product> prdList= new ArrayList<Product>(productsInOrder.size());
					for (JsonNode p : productsInOrder){
						for (JsonNode p1 : products){						
							if (p.equals (p1.get("id"))){															
								Product product = new Product(p1.get("id").textValue(), p1.get("description").textValue(), p1.get("dimension").intValue(), p1.get("amount").intValue());
								prdList.add (product);
							}
						}						
					}
					
					Van selectedVanForDelivery = a.arrangeDelivery(new Order(order.get("id").textValue(), prdList), new TimeRange(order.get("slot").get("date").longValue(), order.get("slot").get("from").intValue(), order.get("slot").get("to").intValue()));		

					if (selectedVanForDelivery != null){						
						result.put("status", "OK");
						result.put("order" + order.get("id"), selectedVanForDelivery.toString());			
					}
					else{						
						result.put("status", "KO");
						result.put("order" + order.get("id"), "No Vans available for delivery");				
					}
				}
				catch (OrderTooBigException orderException){					
					orderException.printStackTrace();			
					result.put("status", "KO");
					result.put("message", "Order is too big to fit into a single van");					
				}
				catch (OrderOutsideOperatingHoursException orderException){					
					orderException.printStackTrace();			
					result.put("status", "KO");
					result.put("message", "Order cannot be delivered as the chosen slot is outside the operating hours");					
				}				
			}		
		}
		catch (Exception e){
			e.printStackTrace();			
			result.put("status", "KO");
			result.put("message", e.toString());		
		}
        return ok(result);
    }

}
