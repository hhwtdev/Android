package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mathankumar on 07/02/16.
 */
public class CategorylistValuesResponse {

    public int Status;
    public String msg;

    @SerializedName("categorylistvalues")
    public List<categorylistvalue> result;

    public class categorylistvalue {

        public String sno,caegoryid,
                name,
                description,
                foodc,
                prayerc,
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
                rating,tags,
        foodttags,foodctags,tours,overalreviews,userreviews;
        @SerializedName("photos")
        public List<photosr> photosres;

        @SerializedName("foodclassification")
        public List<foodclassificationr> foodclassificationres;
        @SerializedName("tourdetails")
        public List<tourdetails> tourdetails;

    }






    public class photosr {
        public String id,
                photourl,
                credits;
    }

    public class foodclassificationr {
        public String foodclassificationvalues;
    }




public class tourdetails {
    public String sno,image,content,sellingrate,currency,id,sub_id,image_one,image_two,image_three,image_four,tour_type,rate,highlights,inclusionandexclusion,exclusion,overviews,whatcanyouexpect,cancellation_policy,location,addi_info,tour_opt_info,tour_opt_link,website,number,tour_classification_one,tour_classification_two,enquiry,departurepoint,departuredate,departuretime,duration,returndetails;
}

}
