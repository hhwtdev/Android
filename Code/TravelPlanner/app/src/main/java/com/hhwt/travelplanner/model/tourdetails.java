package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by jeyavijay on 22/10/16.
 */
public class tourdetails implements Serializable {


    public String sno;



    public String image;



    public String content;



    public String sellingrate;



    public String currency;


    public String id;



    public  String sub_id;



    public  String image_one;

    public  String image_two;



    public  String image_three;



    public  String image_four;




    public  String tour_type;



    public  String rate;




    public  String highlights;


    public  String inclusionandexclusion;


    public  String exclusion;



    public  String overviews;

    public  String whatcanyouexpect;




    public  String cancellation_policy;



    public  String location;



    public  String addi_info;

    public  String tour_opt_info;



    public  String tour_opt_link;



    public  String website;



    public  String number;
    public  String tour_classification_one;
    public  String tour_classification_two;
    public  String enquiry;
    public  String departurepoint;
    public  String departuredate;
    public  String departuretime;
    public  String duration;
    public  String returndetails;

    public tourdetails(String sno,
                   String image, String content, String sellingrate, String currency, String id, String sub_id, String image_one, String image_two, String image_three, String image_four, String tour_type,
                       String rate, String highlights, String inclusionandexclusion, String exclusion, String overviews, String whatcanyouexpect, String cancellation_policy, String location,String addi_info, String tour_opt_info, String tour_opt_link, String website, String number, String tour_classification_one,String tour_classification_two,String enquiry,String departurepoint,String departuredate,String departuretime,String duration,String returndetails) {
        this.sno = sno;
        this.image = image;
        this.content = content;
        this.sellingrate = sellingrate;
        this.currency = currency;
        this.id = id;
        this.sub_id = sub_id;
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_three = image_three;
        this.image_four = image_four;
        this.tour_type = tour_type;
        this.rate = rate;
        this.highlights = highlights;
        this.inclusionandexclusion = inclusionandexclusion;
        this.exclusion = exclusion;
        this.overviews = overviews;
        this.whatcanyouexpect = whatcanyouexpect;
        this.cancellation_policy = cancellation_policy;
        this.location = location;
        this.addi_info = addi_info;
        this.tour_opt_info = tour_opt_info;
        this.tour_opt_link = tour_opt_link;
        this.website = website;
        this.number = number;
        this.tour_classification_one = tour_classification_one;
        this.tour_classification_two = tour_classification_two;
        this.enquiry = enquiry;
        this.departurepoint = departurepoint;
        this.departuredate = departuredate;
        this.departuretime = departuretime;
        this.duration = duration;
        this.returndetails = returndetails;



    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSellingrate() {
        return sellingrate;
    }

    public void setSellingrate(String sellingrate) {
        this.sellingrate = sellingrate;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getImage_one() {
        return image_one;
    }

    public void setImage_one(String image_one) {
        this.image_one = image_one;
    }


    public String getImage_two() {
        return image_two;
    }

    public void setImage_two(String image_two) {
        this.image_two = image_two;
    }


    public String getImage_three() {
        return image_three;
    }

    public void setImage_three(String image_three) {
        this.image_three = image_three;
    }
    public String getImage_four() {
        return image_four;
    }

    public void setImage_four(String image_four) {
        this.image_four = image_four;
    }
    public String getTour_type() {
        return tour_type;
    }

    public void setTour_type(String tour_type) {
        this.tour_type = tour_type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getExclusion() {
        return exclusion;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public String getOverviews() {
        return overviews;
    }

    public void setOverviews(String overviews) {
        this.overviews = overviews;
    }

    public String getWhatcanyouexpect() {
        return whatcanyouexpect;
    }

    public void setWhatcanyouexpect(String whatcanyouexpect) {
        this.whatcanyouexpect = whatcanyouexpect;
    }

    public String getCancellation_policy() {
        return cancellation_policy;
    }

    public void setCancellation_policy(String cancellation_policy) {
        this.cancellation_policy = cancellation_policy;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getTour_opt_info() {
        return tour_opt_info;
    }

    public void setTour_opt_info(String tour_opt_info) {
        this.tour_opt_info = tour_opt_info;
    }

    public String getTour_opt_link() {
        return tour_opt_link;
    }

    public void setTour_opt_link(String tour_opt_link) {
        this.tour_opt_link = tour_opt_link;
    }


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getTour_classification_one() {
        return tour_classification_one;
    }

    public void setTour_classification_one(String tour_classification_one) {
        this.tour_classification_one = tour_classification_one;
    }

    public String getTour_classification_two() {
        return tour_classification_two;
    }

    public void setTour_classification_two(String tour_classification_two) {
        this.tour_classification_two = tour_classification_two;
    }


    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }
    public String getDeparturepoint() {
        return departurepoint;
    }

    public void setDeparturepoint(String departurepoint) {
        this.departurepoint = departurepoint;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getReturndetails() {
        return returndetails;
    }

    public void setReturndetails(String returndetails) {
        this.returndetails = returndetails;
    }


    public String getInclusionandexclusion() {
        return inclusionandexclusion;
    }

    public void setInclusionandexclusion(String inclusionandexclusion) {
        this.inclusionandexclusion = inclusionandexclusion;
    }

    public String getAddi_info() {
        return addi_info;
    }

    public void setAddi_info(String addi_info) {
        this.addi_info = addi_info;
    }


}
