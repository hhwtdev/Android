
package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Votecitylist {

    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("votes")
    @Expose
    private String votes;
    @SerializedName("img")
    @Expose
    private String img;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Votecitylist() {
    }

    /**
     * 
     * @param sno
     * @param img
     * @param votes
     * @param city
     */
    public Votecitylist(String sno, String city, String votes, String img) {
        this.sno = sno;
        this.city = city;
        this.votes = votes;
        this.img = img;
    }

    /**
     * 
     * @return
     *     The sno
     */
    public String getSno() {
        return sno;
    }

    /**
     * 
     * @param sno
     *     The sno
     */
    public void setSno(String sno) {
        this.sno = sno;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The votes
     */
    public String getVotes() {
        return votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(String votes) {
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The img
     */
    public String getImg() {
        return img;
    }

    /**
     * 
     * @param img
     *     The img
     */
    public void setImg(String img) {
        this.img = img;
    }

}
