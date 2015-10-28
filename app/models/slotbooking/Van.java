package com.redmart.assignment.slotbooking;

import com.redmart.assignment.slotbooking.exceptions.OrderTooBigException;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 10/23/2015.
 */
public class Van{
    /*
        Identifier for each van
    */
    private String id;

    /*
        Max capacity of the van
    */
    private int maxCapacity;

    public Map<String,Integer> schedule = new HashMap();

    public Van(String id) {
        this.id= id;

        // default to 6750 cubic in (15 * 20 * 15)
        this.maxCapacity = 6750;
    }

    public Van(String id, int maxCapacity) {
        this.id= id;
        this.maxCapacity = maxCapacity;
    }

    public boolean isAvailabilityForTimeRange(Order order, TimeRange timerange) throws Exception {
        // check the schedule
        //System.out.println(timerange);
        Integer capacityAtSlot = this.schedule.get(timerange.toString());
        //System.out.println(capacityAtSlot);
        if (capacityAtSlot == null && (order.getDimension() > this.maxCapacity)){
            throw new OrderTooBigException("Order is greater than capacity of the van");
        }
        else if (capacityAtSlot != null && ((capacityAtSlot.intValue() + order.getDimension()) < this.maxCapacity)){
            return true;
        }
        else if (capacityAtSlot == null){
            return true;
        }
        return false;
    }

    private boolean addSchedule (TimeRange timerange, int capacity){
        //System.out.println(this.schedule);
        Integer capacityAtSlot = this.schedule.get (timerange.toString());

        System.out.println(capacityAtSlot);

        if (capacityAtSlot  != null)
            this.schedule.put(timerange.toString(), new Integer(capacityAtSlot.intValue() +  capacity));
        else {
            //System.out.println (this.schedule.keySet());
            this.schedule.put(timerange.toString(), new Integer(capacity));
        }

        return true;
    }

    public boolean addOrder (Order order, TimeRange timeslot) throws Exception {
        //System.out.println(order.getDimension());
        if (this.isAvailabilityForTimeRange(order, timeslot)){
            return this.addSchedule( timeslot, order.getDimension());
        }
        else{
            throw new Exception ("Either Van is not available for this time or Van has reached its max capacity");
        }
    }

    @Override
    public String toString() {
        return "Van{" +
                "id='" + id + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
