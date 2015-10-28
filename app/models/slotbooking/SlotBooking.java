package com.redmart.assignment.slotbooking;

import com.redmart.assignment.slotbooking.exceptions.OrderOutsideOperatingHoursException;
import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by user on 10/23/2015.
 */
public class SlotBooking {
    private TimeRange slotDuration;  // in minutes
    private ArrayList<Van> vans;       // vans

    public SlotBooking(TimeRange slotDuration, int numVans, int vanCapacity) {
       this.slotDuration = slotDuration;
        vans = new ArrayList<Van>(numVans);

        if (numVans > 0){
            for (int i = 1; i <= numVans; i++){
                vans.add (new Van (String.valueOf(i), vanCapacity));
            }
        }
    }

    private void checkIfOutsideOperatingHours (TimeRange timeslot) throws OrderOutsideOperatingHoursException {
        // check if the delivery time is within the operating hours
        if (timeslot.getFrom() < this.slotDuration.getFrom() || timeslot.getTo() > this.slotDuration.getTo()){
            throw new OrderOutsideOperatingHoursException("Order cannot be delivered as the chosen slot is outside the operating hours");
        }
    }

    private Van checkIfVanIsAvailable (Order order, TimeRange timeslot) throws Exception{
        // Check if any of the vans are available for the order delivery
        for (Van van : vans){
            if (van.isAvailabilityForTimeRange(order, timeslot)){
                van.addOrder(order, timeslot);
                return van;
            }
        }
        return null;
    }

    public Van arrangeDelivery (Order order, TimeRange selectedSlot) throws Exception{

        // Throw error in case
        // 1. None of the vans are available
        // 2. If the order time is beyond operating hours
        // 3. if the order volume does not fit into any of the van (it could also so happen that order volume is greater than van's max volume, in that case arrange two vans)

        // Check if the order is within the operating hours
        this.checkIfOutsideOperatingHours(selectedSlot);

        // Check if any van is available to deliver this order for the selected timezone
        //this.checkIfVanIsAvailable(order, selectedSlot);
        Van selectedVan = this.checkIfVanIsAvailable(order, selectedSlot);
        if (selectedVan != null)
            System.out.println(selectedVan + " can deliver the order " + order.getId() + " with dimension " + order.getDimension());
        else
            System.out.println("No vans available for delivery during this time");

        return selectedVan;
    }

/*    public static void  main (String args[]) throws Exception{
        SlotBooking a = new SlotBooking(new TimeRange(0, 540, 1080), 4, 6750);

        // Prepare an order
        Product p1 = new Product("1", "prd 1", 6400, 100);
        Product p2 = new Product("2", "prd 2", 350, 200);

        ArrayList<Product> prdList= new ArrayList<Product>(2);
        prdList.add (p1);
        prdList.add (p2);

        a.arrangeDelivery(new Order("1", prdList), new TimeRange(1445858983488l, 540, 660));
        a.arrangeDelivery(new Order("2", prdList), new TimeRange(1445858983488l, 540, 660));
        a.arrangeDelivery(new Order("3", prdList), new TimeRange(1445858983488l, 540, 660));
        a.arrangeDelivery(new Order("4", prdList), new TimeRange(1445858983488l, 540, 660));

    }*/
}
