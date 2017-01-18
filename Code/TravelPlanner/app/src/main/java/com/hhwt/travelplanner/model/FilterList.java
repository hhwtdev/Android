package com.hhwt.travelplanner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by madhankumar on 15/05/16.
 */
public class FilterList implements Serializable {

    public List<Starsfilter> list=new ArrayList<>();
    public List<Starsfilter> starlist=new ArrayList<>();

    public FilterList(){}
    public FilterList(List<Starsfilter> list,List<Starsfilter> starlist){
        this.list=list;
        this.starlist=starlist;
    }

    public void setList(List<Starsfilter> list) {
        this.list = list;
    }

    public List<Starsfilter> getList() {
        return list;
    }

    public void setStarlist(List<Starsfilter> starlist) {
        this.starlist = starlist;
    }

    public List<Starsfilter> getStarlist() {
        return starlist;
    }
}
