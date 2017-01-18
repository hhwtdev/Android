package com.hhwt.travelplanner.Retrofit;

import java.util.List;

/**
 * Created by Mathankumar on 07/02/16.
 */
public class MyTripResponse {

    public int status;
    public String msg;
    public List<Mytrip> result;

    public class Mytrip {
        public String tripid, tripname, startingdate, endingdate,createdon,cityid,city,img;
    }

    //mathan@facebook.com
    //  "status": 1,
    //       "msg": "Registration Successfull",
    //    "fb_id": "mathan@facebook.com",
    //   "imgurl": "http://www.hhwt.tech/hhwt_webservice/uploads/thing%20to%20do.jpg",
    // "name": "Mathan",
    //"email": "mathan047@gmail.com"
}
