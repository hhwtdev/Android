package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by Mathankumar on 11/02/16.
 */
public class foodclassification implements Serializable {
    public void setFoodclassificationvalues(String foodclassificationvalues) {
        this.foodclassificationvalues = foodclassificationvalues;
    }

    public String foodclassificationvalues;
    public foodclassification(){}
    public foodclassification(String foodclassificationvalues) {
        this.foodclassificationvalues = foodclassificationvalues;

    }

    public String getFoodclassificationvalues() {
        return foodclassificationvalues;
    }
}
