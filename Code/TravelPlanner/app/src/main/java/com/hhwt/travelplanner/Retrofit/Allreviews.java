
package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Allreviews implements Serializable {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("categorylist")
    @Expose
    private List<Categorylist> categorylist = new ArrayList<Categorylist>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Allreviews() {
    }

    /**
     * 
     * @param categorylist
     * @param status
     * @param msg
     */
    public Allreviews(Integer status, String msg, List<Categorylist> categorylist) {
        this.status = status;
        this.msg = msg;
        this.categorylist = categorylist;
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
     *     The categorylist
     */
    public List<Categorylist> getCategorylist() {
        return categorylist;
    }

    /**
     * 
     * @param categorylist
     *     The categorylist
     */
    public void setCategorylist(List<Categorylist> categorylist) {
        this.categorylist = categorylist;
    }

}
