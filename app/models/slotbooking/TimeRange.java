package com.redmart.assignment.slotbooking;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 10/23/2015.
 */
public class TimeRange {
    private long date;  // Date... not implementing the timezone specific scenarios here
    private int from;   // From time in minutes
    private int to;  // To time in minutes

    public TimeRange(long date, int from, int to) throws Exception {
        if (from >= to){
            throw new Exception("From time should be lesser than To time");
        }
        else if (from > 1400 || to > 1440 || from == to){
            throw new Exception("Invalid time range");
        }

        // convert the date to the beginning of the day
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(date));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.date = cal.getTime().getTime();

        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "date=" + date +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
