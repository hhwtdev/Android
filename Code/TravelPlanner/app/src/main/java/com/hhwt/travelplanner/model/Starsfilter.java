
package com.hhwt.travelplanner.model;

import java.io.Serializable;

public class Starsfilter implements Serializable {


    private String Starcount;
    private int ID;
    private boolean selectedStarcount;

    /**
     * No args constructor for use in serialization
     */
    public Starsfilter() {
    }

    public Starsfilter(int ID, String Starcount, boolean selectedStarcount) {
        this.ID = ID;
        this.Starcount = Starcount;
        this.selectedStarcount = selectedStarcount;
    }

    public void setStarcount(String Starcount) {
        Starcount = Starcount;
    }

    public void setID(int ID) {
        ID = ID;
    }

    public String getStarcount() {
        return Starcount;
    }

    public int getID() {
        return ID;
    }

    public Boolean getSelectedStarcount() {
        return selectedStarcount;
    }

    public void setSelectedStarcount(boolean selectedStarcount) {
        this.selectedStarcount = selectedStarcount;
    }

}
