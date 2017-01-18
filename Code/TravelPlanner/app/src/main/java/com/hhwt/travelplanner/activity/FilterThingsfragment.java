package com.hhwt.travelplanner.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

/**
 * Created by jeyavijay on 15/10/16.
 */
public class FilterThingsfragment extends Fragment implements View.OnClickListener{
    RatingBar twoitem_ratingBar,threeitem_ratingBar,fouritem_ratingBar;

    LinearLayout twostarselect,threestarselect,fourthstar,anystarselect;

    TextView applyfilter,clearall;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.filterflow,container,false);


        twoitem_ratingBar = (RatingBar) v.findViewById(R.id.twoitem_ratingBar);
        threeitem_ratingBar = (RatingBar) v.findViewById(R.id.threeitem_ratingBar);
        fouritem_ratingBar = (RatingBar) v.findViewById(R.id.fouritem_ratingBar);
        twostarselect = (LinearLayout) v.findViewById(R.id.twostarselect);
        threestarselect =  (LinearLayout) v.findViewById(R.id.threestarselect);
        fourthstar = (LinearLayout) v.findViewById(R.id.fourthstar);
        anystarselect = (LinearLayout) v.findViewById(R.id.anystarselect);
        applyfilter = (TextView) v.findViewById(R.id.applyfilter);
        clearall = (TextView) v.findViewById(R.id.clearall);
        anystarselect.setOnClickListener(this);
        twostarselect.setOnClickListener(this);
        threestarselect.setOnClickListener(this);
        fourthstar.setOnClickListener(this);
        applyfilter.setOnClickListener(this);
        clearall.setOnClickListener(this);

        AlertUtils.RatingColorGreen(getActivity(), twoitem_ratingBar);
        try {
            twoitem_ratingBar.setRating(Float.parseFloat("2"));

        } catch (NumberFormatException e) {

        }



        AlertUtils.RatingColorGreen(getActivity(), threeitem_ratingBar);
        try {
            threeitem_ratingBar.setRating(Float.parseFloat("3"));

        } catch (NumberFormatException e) {

        }


        AlertUtils.RatingColorGreen(getActivity(), fouritem_ratingBar);
        try {
            fouritem_ratingBar.setRating(Float.parseFloat("4"));

        } catch (NumberFormatException e) {

        }






        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if(v == twostarselect){
            anystarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            twostarselect.setBackgroundColor(Color.parseColor("#00cccb"));
            threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        else if (v == threestarselect){
            anystarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            threestarselect.setBackgroundColor(Color.parseColor("#00cccb"));
            fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        else if (v == fourthstar){

            anystarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            fourthstar.setBackgroundColor(Color.parseColor("#00cccb"));
        }

        else if (v == anystarselect){

            anystarselect.setBackgroundColor(Color.parseColor("#00cccb"));
            twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));

        }
        else if (v == applyfilter){

            Sessiondata.getInstance().setExplorenewfullpage(1);
            Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }


        else if (v == clearall){
            Sessiondata.getInstance().setExplorenewfullpage(1);
            Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }
    }
}
