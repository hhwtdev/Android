/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhwt.travelplanner.model;

import java.io.Serializable;
import java.util.List;

public class Categorylistmodel implements Serializable {
    public List<photoss> photos;
    public List<foodclassification> foodclassification;

    public String sno;
    public String name;
    public String description;
    public String foodc;
    public String prayerc;
    public String openhrs;
    public String closehrs;
    public String website;
    public String timeremaining;
    public String estimatedtime;
    public String pricecurrency;
    public String price;
    public String country;
    public String city;
    public String state;
    public String address;
    public String postalcode;
    public String map;
    public String phone;
    public String hhwtlink;
    public String activity;
    public String weight;
    public String district;
    public String latitude;
    public String longitude;
    String foodttags;
    String foodctags;
    String tours;



    public String overalreviews;
    public String userreviews;

    public List<tourdetails> tourdetails;
    public String rating;
    public String date;
    public String tags;
    public String categoryID;
    public CreatedTripModel ctm;


   public Categorylistmodel() {
    }

    public Categorylistmodel(String sno,
                             String caegoryid,
                             String name,
                             String description,
                             String foodc,
                             String prayerc,
                             String openhrs,
                             String closehrs,
                             String website,
                             String timeremaining,
                             String estimatedtime,
                             String pricecurrency,
                             String price,
                             String country,
                             String city,
                             String state,
                             String address,
                             String postalcode,
                             String map,
                             String phone,
                             String hhwtlink,
                             String activity,
                             String weight,
                             String district,
                             String latitude,
                             String longitude,
                             String rating,

                             List<photoss> photos,
                             List<foodclassification> foodclassification, String date, String tags,String foodttags,String foodctags,String tours, List<tourdetails> tourdetails,String overalreviews,String userreviews) {

        this.tours = tours;
        this.foodttags = foodttags;
        this.foodctags = foodctags;
        this.tags = tags;
        this.categoryID = caegoryid;
        this.sno = sno;
        this.name = name;

        this.description = description;

        this.foodc = foodc;
        this.prayerc = prayerc;

        this.openhrs = openhrs;

        this.closehrs = closehrs;
        this.website = website;
        this.timeremaining = timeremaining;
        this.estimatedtime = estimatedtime;
        this.pricecurrency = pricecurrency;
        this.price = price;
        this.country = country;
        this.city = city;
        this.state = state;
        this.address = address;
        this.postalcode = postalcode;
        this.map = map;
        this.phone = phone;
        this.hhwtlink = hhwtlink;
        this.activity = activity;
        this.weight = weight;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.photos = photos;
        this.foodclassification = foodclassification;
        this.date = date;
        this.tourdetails = tourdetails;
        this.overalreviews = overalreviews;
        this.userreviews = userreviews;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodc() {
        return foodc;
    }

    public String getPrayerc() {
        return prayerc;
    }


    public String getFoodttags() {
        return foodttags;
    }
    public String getFoodctags() {
        return foodctags;
    }



    public String getTours() {
        return tours;
    }
    public String getOpenhrs() {
        return openhrs;
    }

    public String getClosehrs() {
        return closehrs;
    }

    public String getWebsite() {
        return website;
    }

    public String getTimeremaining() {
        return timeremaining;
    }

    public String getEstimatedtime() {
        return estimatedtime;
    }

    public String getPricecurrency() {
        return pricecurrency;
    }

    public String getPrice() {
        return price;
    }

    public String getCountry() {
        return country;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getMap() {
        return map;
    }

    public String getPhone() {
        return phone;
    }

    public String getHhwtlink() {
        return hhwtlink;
    }

    public String getActivity() {
        return activity;
    }

    public String getWeight() {
        return weight;
    }

    public String getDistrict() {
        return district;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getRating() {
        return rating;
    }

    public String getOveralreviews() {
        return overalreviews;
    }
    public String getUserreviews() {
        return userreviews;
    }

    public List<photoss> getPhotos() {
        return photos;
    }

    public List<foodclassification> getFoodclassification() {
        return foodclassification;
    }

    public List<com.hhwt.travelplanner.model.tourdetails> getTourdetails() {
        return tourdetails;
    }

    public String getTags() {
        return tags;
    }

    public void setCtm(CreatedTripModel ctm) {
        this.ctm = ctm;
    }

    public CreatedTripModel getCtm() {
        return ctm;
    }
}
