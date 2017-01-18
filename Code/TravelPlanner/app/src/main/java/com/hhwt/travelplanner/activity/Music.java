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
public class Music extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mus = inflater.inflate(R.layout.music_frag, container, false);
        ((TextView)mus.findViewById(R.id.textView2)).setText("Music Tracks");
        return mus;
    }}

