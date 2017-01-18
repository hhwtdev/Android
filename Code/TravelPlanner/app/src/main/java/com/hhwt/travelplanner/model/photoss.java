package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by Mathankumar on 11/02/16.
 */
public class photoss implements Serializable {
    public void setId(String id) {
        this.id = id;
    }

    public String id;

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String photourl;

    public String getCrediturl() {
        return crediturl;
    }

    public void setCrediturl(String crediturl) {
        this.crediturl = crediturl;
    }

    public String crediturl;

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String credits;

    public String getBigurl() {
        return bigurl;
    }

    public void setBigurl(String bigurl) {
        this.bigurl = bigurl;
    }

    public String bigurl;

    public photoss() {
    }

    public photoss(String id,
                   String photourl,
                   String credits) {
        this.id = id;
        this.photourl = photourl;
        this.credits = credits;

    }

    public String getId() {
        return id;
    }

    public String getPhotourl() {
        return photourl;
    }


    public String getCredits() {
        return credits;
    }
}
