package com.hhwt.travelplanner.model;

import java.io.Serializable;

/**
 * Created by Mathankumar on 11/02/16.
 */
public class introviewpager implements Serializable {

    int photourl, id;

    public introviewpager() {
    }

    public introviewpager(int id,
                          int photourl
    ) {
        this.id = id;
        this.photourl = photourl;

    }

    public int getId() {
        return id;
    }

    public int getPhotourl() {
        return photourl;
    }

}
