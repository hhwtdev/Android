package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by Mathankumar on 11/02/16.
 */
public class CreatedTripModel implements Serializable {
    public String msg;
    public String tripid;
    public String tripname;
    public String startingdate;
    public String endingdate;
    public String Searchtext;
    public String createdon;


    public String cityid, img;


    public String city;

    public String categorytype, CategoryID;

    public NearByelementsModel n;

    public CreatedTripModel() {
    }

    public CreatedTripModel(String tripid,
                            String tripname,
                            String startingdate, String endingdate, String cityid, String city, String img) {
        this.tripid = tripid;
        this.tripname = tripname;
        this.startingdate = startingdate;
        this.endingdate = endingdate;


        this.cityid = cityid;
        this.city = city;
        this.img = img;
    }

    public void setSearchtext(String searchtext) {
        Searchtext = searchtext;
    }

    public String getSearchtext() {
        return Searchtext;
    }

    public String getTripname() {
        return tripname;
    }

    public String getTripid() {
        return tripid;
    }

    public String getCityid() {
        return cityid;
    }

    public String getImg() {
        return img;
    }

    public String getCity() {
        return city;
    }

    public String getStartingdate() {
        return startingdate;
    }

    public String getMsg() {
        return msg;
    }

    public String getEndingdate() {
        return endingdate;
    }


    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setN(NearByelementsModel n) {
        this.n = n;
    }

    public NearByelementsModel getN() {
        return n;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getCreatedon() {
        return createdon;
    }
}
