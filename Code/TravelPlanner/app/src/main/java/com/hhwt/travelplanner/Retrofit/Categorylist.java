
package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Categorylist implements Serializable {

    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("placeid")
    @Expose
    private String placeid;
    @SerializedName("placename")
    @Expose
    private String placename;
    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("timestamped")
    @Expose
    private String timestamped;
    @SerializedName("purpose")
    @Expose
    private Object purpose;
    @SerializedName("rating")
    @Expose
    private String rating;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Categorylist() {
    }

    /**
     * 
     * @param timestamped
     * @param reviews
     * @param username
     * @param placeid
     * @param likes
     * @param name
     * @param userid
     * @param rating
     * @param purpose
     * @param placename
     * @param cid
     */
    public Categorylist(String cid, String name, String userid, String username, String placeid, String placename, String reviews, String likes, String timestamped, Object purpose, String rating) {
        this.cid = cid;
        this.name = name;
        this.userid = userid;
        this.username = username;
        this.placeid = placeid;
        this.placename = placename;
        this.reviews = reviews;
        this.likes = likes;
        this.timestamped = timestamped;
        this.purpose = purpose;
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * 
     * @param cid
     *     The cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 
     * @param userid
     *     The userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The placeid
     */
    public String getPlaceid() {
        return placeid;
    }

    /**
     * 
     * @param placeid
     *     The placeid
     */
    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    /**
     * 
     * @return
     *     The placename
     */
    public String getPlacename() {
        return placename;
    }

    /**
     * 
     * @param placename
     *     The placename
     */
    public void setPlacename(String placename) {
        this.placename = placename;
    }

    /**
     * 
     * @return
     *     The reviews
     */
    public String getReviews() {
        return reviews;
    }

    /**
     * 
     * @param reviews
     *     The reviews
     */
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    /**
     * 
     * @return
     *     The likes
     */
    public String getLikes() {
        return likes;
    }

    /**
     * 
     * @param likes
     *     The likes
     */
    public void setLikes(String likes) {
        this.likes = likes;
    }

    /**
     * 
     * @return
     *     The timestamped
     */
    public String getTimestamped() {
        return timestamped;
    }

    /**
     * 
     * @param timestamped
     *     The timestamped
     */
    public void setTimestamped(String timestamped) {
        this.timestamped = timestamped;
    }

    /**
     * 
     * @return
     *     The purpose
     */
    public Object getPurpose() {
        return purpose;
    }

    /**
     * 
     * @param purpose
     *     The purpose
     */
    public void setPurpose(Object purpose) {
        this.purpose = purpose;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

}
