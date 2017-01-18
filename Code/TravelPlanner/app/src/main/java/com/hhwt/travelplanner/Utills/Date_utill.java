package com.hhwt.travelplanner.Utills;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mathankumar on 05/02/16.
 */
public class Date_utill {

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            Date date = new Date();
            return isDategreater(dateFormat.format(date), inDate);
        } catch (ParseException pe) {
            return false;
        }
    }

    public static boolean isDategreater(String enddate, String startdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(startdate);
            Date date2 = sdf.parse(enddate);

            if (date1.after(date2)) {
                return true;
            }
            if (date1.equals(date2)) {
                return true;
            }
            if (date1.before(date2)) {
                return false;
            }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    public static ArrayList<String> dates(String startdate, String enddate) {
        ArrayList<String> newdates = new ArrayList<>();
        try {
            List<Date> dates = new ArrayList<Date>();

            DateFormat formatter;

            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = (Date) formatter.parse(startdate);
            Date endDate = (Date) formatter.parse(enddate);
            long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
            long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
            long curTime = startDate.getTime();
            while (curTime <= endTime) {
                dates.add(new Date(curTime));
                curTime += interval;
            }
            for (int i = 0; i < dates.size(); i++) {
                Date lDate = (Date) dates.get(i);
                String ds = formatter.format(lDate);
                System.out.println(" Date is ..." + ds);
                newdates.add(ds);
            }
            return newdates;
        } catch (ParseException pe) {
            pe.printStackTrace();
            return newdates;
        }
    }

    public static String Dateformatchange(String dare) {
        String newDateString;
        try {
            final String OLD_FORMAT = "yyyy-MM-dd";
            final String NEW_FORMAT = "yyyy_MMM_dd";

// August 12, 2010


            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = null;

            d = sdf.parse(dare);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            newDateString = dare;
        }
        return newDateString;
    }
    public static String Dateformatchangeforall(String dare) {
        String newDateString;
        try {
            final String OLD_FORMAT = "yyyy-MM-dd";
            final String NEW_FORMAT = "dd MMM yyyy";
// August 12, 2010
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = null;
            d = sdf.parse(dare);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            newDateString = dare;
        }
        return newDateString;
    }

    public static String Dateincrement(String dt) throws ParseException {
        // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 1);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }
}
