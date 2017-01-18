
package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("img")
    @Expose
    private String img;

    /**
     * No args constructor for use in serialization
     * 
     */
    public City() {
    }

    /**
     * 
     * @param sno
     * @param img
     * @param city
     */
    public City(String sno, String city, String img) {
        this.sno = sno;
        this.city = city;
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
