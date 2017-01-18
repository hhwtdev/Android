package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

public class Overviewwithdetails extends Fragment implements View.OnClickListener{
    TextView daytour,firstdes,seconddes,thirddes,cancelation,additionalinformation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toursoverview,container,false);
        daytour = (TextView) v.findViewById(R.id.daytour);
        firstdes = (TextView) v.findViewById(R.id.firstdes);
        seconddes = (TextView) v.findViewById(R.id.seconddes);
        thirddes = (TextView) v.findViewById(R.id.thirddes);
        cancelation = (TextView) v.findViewById(R.id.cancelation);
        additionalinformation = (TextView) v.findViewById(R.id.additionalinformation);
        cancelation.setText(""+Sessiondata.getInstance().getCancelceon());
        additionalinformation.setText(""+Sessiondata.getInstance().getAddtionalcon());
        thirddes.setText(""+Sessiondata.getInstance().getOver2());
        seconddes.setText(""+Sessiondata.getInstance().getOve1());
        firstdes.setText(""+Sessiondata.getInstance().getTourloOverviews());
        daytour.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        if(v == daytour){
            Fragment fr = new Makeonenquiry();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }}}
