
package com.hhwt.travelplanner.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VoteCityPojo {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("categorylist")
    @Expose
    private List<Votecitylist> votecitylist = new ArrayList<Votecitylist>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public VoteCityPojo() {
    }

    /**
     * 
     * @param status
     * @param votecitylist
     * @param msg
     */
    public VoteCityPojo(Integer status, String msg, List<Votecitylist> votecitylist) {
        this.status = status;
        this.msg = msg;
        this.votecitylist = votecitylist;
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
     *     The votecitylist
     */
    public List<Votecitylist> getVotecitylist() {
        return votecitylist;
    }

    /**
     * 
     * @param votecitylist
     *     The votecitylist
     */
    public void setVotecitylist(List<Votecitylist> votecitylist) {
        this.votecitylist = votecitylist;
    }

}
