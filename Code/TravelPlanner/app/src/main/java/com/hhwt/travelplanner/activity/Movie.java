package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhwt.travelplanner.R;

/**
 * Created by jeyavijay on 18/03/16.
 */
public class Movie extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mov = inflater.inflate(R.layout.movie_frag, container, false);
        ((TextView)mov.findViewById(R.id.textView1)).setText("Movies List");
        return mov;
    }}

