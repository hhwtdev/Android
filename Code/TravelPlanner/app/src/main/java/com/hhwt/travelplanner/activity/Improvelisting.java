package com.hhwt.travelplanner.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.fragment.Suggestesedit;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.quinny898.library.persistentsearch.SearchBox;

/**
 * Created by jeyavijay on 07/11/16.
 */
public class Improvelisting extends Fragment implements View.OnClickListener{

    LinearLayout suggesteditclick,openhrs,busness,incorrect;
    ImageView backclick,filter,toolbarsearch;
    SearchBox search;
    LinearLayout linearvalbottom;
    TextView  apptitle;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.imporvelisting,container,false);



        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);
        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);

        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Improve Listing");




        busness = (LinearLayout) v.findViewById(R.id.busness);
        busness.setOnClickListener(this);


        incorrect = (LinearLayout) v.findViewById(R.id.incorrect);
        incorrect.setOnClickListener(this);

        openhrs = (LinearLayout) v.findViewById(R.id.openhrs);
        suggesteditclick = (LinearLayout) v.findViewById(R.id.suggesteditclick);

        suggesteditclick.setOnClickListener(this);
        openhrs.setOnClickListener(this);

        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(v == suggesteditclick){

            Fragment fc = new Suggestesedit();
            FragmentChangeListener fr = (FragmentChangeListener) getActivity();
            fr.replaceFragment(fc);
        }

        else if (v == backclick){
            getFragmentManager().popBackStack();
        }

        else if(v == openhrs)
        {
            Fragment fc = new Openhoursedit();
            FragmentChangeListener fr = (FragmentChangeListener) getActivity();
            fr.replaceFragment(fc);

        }

        else if (v == busness) {

            AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
            alert.setTitle("Thanks for helping out!");
            alert.setMessage("We'll be reviewing your edits for this listing.");
            alert.setCancelMessage(null);

            alert.setButton("OKAY", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //do somthing or dismiss dialog by                dialog.dismiss();
                  //  getFragmentManager().popBackStack();

                }
            });

            alert.show();
        }

        else if (v == incorrect) {

            AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
            alert.setTitle("Thanks for helping out!");
            alert.setMessage("We'll be reviewing your edits for this listing.");
            alert.setCancelMessage(null);

            alert.setButton("OKAY", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //do somthing or dismiss dialog by                dialog.dismiss();
                    //  getFragmentManager().popBackStack();

                }
            });

            alert.show();


        }


        }


}
