package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by Mathankumar on 11/02/16.
 */
public class NearByelementsModel implements Serializable {

    public NearByelementsModel() {

    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String district;

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String categoryid;

    public void setTotalno(String totalno) {
        this.totalno = totalno;
    }

    public String totalno;

    public NearByelementsModel(String district,
                               String categoryid,
                               String totalno) {
        this.district = district;
        this.categoryid = categoryid;
        this.totalno = totalno;

    }

    public String getCategoryid() {
        return categoryid;
    }

    public String getDistrict() {
        return district;
    }

    public String getTotalno() {
        return totalno;
    }
}
