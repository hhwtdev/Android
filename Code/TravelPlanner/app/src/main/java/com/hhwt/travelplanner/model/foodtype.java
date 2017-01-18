package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by jeyavijay on 28/03/16.
 */
public class foodtype implements Serializable {


    public foodtype() {

    }

    public String getFoodtypevalues() {
        return foodtypevalues;
    }

    public void setFoodtypevalues(String foodtypevalues) {
        this.foodtypevalues = foodtypevalues;
    }

    public String foodtypevalues;

    public foodtype(String foodtypevaluess) {
        this.foodtypevalues = foodtypevaluess;


    }

}
