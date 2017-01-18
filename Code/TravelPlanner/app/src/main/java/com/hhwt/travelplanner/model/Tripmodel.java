package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by Mathankumar on 10/02/16.
 */
public class Tripmodel implements Serializable {
    public String Tripname, TripId, TrpStartDate, TripEndDate;

    public Tripmodel(String Tripname, String TripId, String TrpStartDate, String TripEndDate) {
        this.TripEndDate = TripEndDate;
        this.TrpStartDate = TrpStartDate;
        this.TripId = TripId;
        this.Tripname = Tripname;
    }

    public String getTripEndDate() {
        return TripEndDate;
    }

    public String getTripId() {
        return TripId;
    }

    public String getTripname() {
        return Tripname;
    }

    public String getTrpStartDate() {
        return TrpStartDate;
    }

    public void setTripEndDate(String tripEndDate) {
        TripEndDate = tripEndDate;
    }

    public void setTripId(String tripId) {
        TripId = tripId;
    }

    public void setTripname(String tripname) {
        Tripname = tripname;
    }

    public void setTrpStartDate(String trpStartDate) {
        TrpStartDate = trpStartDate;
    }
}
