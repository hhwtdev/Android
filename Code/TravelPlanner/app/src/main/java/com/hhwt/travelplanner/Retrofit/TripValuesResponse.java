package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mathankumar on 07/02/16.
 */
public class TripValuesResponse {

    public int Status;

    @SerializedName("categorylistvalues")
    public List<categorylistvalue> result;

    public class categorylistvalue {

        public String sno,
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
                activity,
                weight,
                district,
                latitude,
                longitude,
                rating;
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
