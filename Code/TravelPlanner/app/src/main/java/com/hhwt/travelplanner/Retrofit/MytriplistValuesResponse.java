package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mathankumar on 07/02/16.
 */
public class MytriplistValuesResponse {

    public int status;
    public List<categorylistvalue> result;

    public class categorylistvalue {

        public String sno, caegoryid,
                 name,
                description,
                openhrs,
                closehrs,
                website,
                timeremaining,
                estimatedtime,
                pricecurrency,
                price,
                country,
                city,
                state,
                address,
                postalcode,
                map,
                phone,
                hhwtlink,
                 activity;
        public String rating;
        public String district,
                latitude,
                longitude,
                weight, date, tags;
        public String notes;
        public String idnumber;
        @SerializedName("photos")
        public List<photosr> photosres;

        @SerializedName("foodclassification")
        public List<foodclassificationr> foodclassificationres;


    }

    public class photosr {
        public String id,
                photourl,
                credits;
    }

    public class foodclassificationr {
        public String foodclassificationvalues;
    }
}
