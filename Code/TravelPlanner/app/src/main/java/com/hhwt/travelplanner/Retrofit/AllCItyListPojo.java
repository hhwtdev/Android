
package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllCItyListPojo {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("categorylist")
    @Expose
    private List<City> city = new ArrayList<City>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllCItyListPojo() {
    }

    /**
     * 
     * @param status
     * @param msg
     * @param city
     */
    public AllCItyListPojo(Integer status, String msg, List<City> city) {
        this.status = status;
        this.msg = msg;
        this.city = city;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The city
     */
    public List<City> getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The City
     */
    public void setCity(List<City> city) {
        this.city = city;
    }

}
