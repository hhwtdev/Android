package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.quinny898.library.persistentsearch.SearchBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by jeyavijay on 21/10/16.
 */
public class Filterthingstodofragment extends Fragment implements View.OnClickListener{


    RatingBar twoitem_ratingBar,threeitem_ratingBar,fouritem_ratingBar;

    LinearLayout twostarselect,threestarselect,fourthstar,anystarselect;
    String mess;
    TextView applyfilter,clearall;
    Retrofit retrofit;
    public int sortitemposition = 0;
    CheckBox AmusementParkscheckbox,Landmarkcheckbox,Museumscheckbox,NatureParkscheckbox,OutdoorActivitiescheckbox;

    CheckBox SightsLandmarkscheckboxfv,Shoppingcheckboxfc,TravellerResourcescheckboxfc,Transportationcheckboxfc,TheatreConcertscheckboxfc,ZoosAquariumsservedfc,otherscheeckbox;

    String AmusementParks,Landmark,Museums,NatureParks,OutdoorActivities,SightsLandmarks,Shopping,TravellerResources,Transportation,TheatreConcerts,ZoosAquariums,others;

    String tosno,cidval;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();

    String weightval;
    public static Activity _A;
    TextView countryapptitle;

    ImageView filter,toolbarsearch;

    String foodcval,foottypeval;
    TextView apptitle;
    LinearLayout linearvalbottom;

    ImageView backclick;
    View save;

    String allneirboorval,neibourdis;
    SearchBox search;
    LinearLayout allneighbourhoods;

String useridval;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.thingstodofragmentfilter,container,false);

        _A = getActivity();

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);


        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.GONE);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Filters");

        allneirboorval = Sessiondata.getInstance().getNeighboorhoodval();
        neibourdis = allneirboorval;

      // Toast.makeText(getActivity(),neibourdis,Toast.LENGTH_LONG).show();



       AmusementParks = "'Amusement Parks'";
        Landmark = "'Landmark'";
        Museums = "'Museums'";
        NatureParks = "'Nature & Parks'";
        OutdoorActivities = "'Outdoor Activities'";
        SightsLandmarks = "'Sights & Landmarks'";
        Shopping = "'Shopping'";
        TravellerResources = "'Traveller Resources'";
        Transportation = "'Transportation'";
        TheatreConcerts = "'Theatre & Concerts'";
        ZoosAquariums = "'Zoos & Aquariums'";
        others = "'Others'";

        useridval = Sessiondata.getInstance().getReviewfbemailid();

        twoitem_ratingBar = (RatingBar) v.findViewById(R.id.twoitem_ratingBar);
        threeitem_ratingBar = (RatingBar) v.findViewById(R.id.threeitem_ratingBar);
        fouritem_ratingBar = (RatingBar) v.findViewById(R.id.fouritem_ratingBar);
        twostarselect = (LinearLayout) v.findViewById(R.id.twostarselect);
        threestarselect =  (LinearLayout) v.findViewById(R.id.threestarselect);
        fourthstar = (LinearLayout) v.findViewById(R.id.fourthstar);
        anystarselect = (LinearLayout) v.findViewById(R.id.anystarselect);
        applyfilter = (TextView) v.findViewById(R.id.applyfilter);
        clearall = (TextView) v.findViewById(R.id.clearall);

        allneighbourhoods = (LinearLayout) v.findViewById(R.id.allneighbourhoods);

        AmusementParkscheckbox = (CheckBox)v.findViewById(R.id.checkBox1);
        allneighbourhoods.setOnClickListener(this);


        Landmarkcheckbox = (CheckBox)v.findViewById(R.id.checkBox2);
        Museumscheckbox = (CheckBox)v.findViewById(R.id.checkBox3);
        NatureParkscheckbox = (CheckBox)v.findViewById(R.id.checkBox4);
        OutdoorActivitiescheckbox = (CheckBox)v.findViewById(R.id.checkBox5);


        SightsLandmarkscheckboxfv = (CheckBox)v.findViewById(R.id.checkBox6);
        Shoppingcheckboxfc = (CheckBox)v.findViewById(R.id.checkBox7);
        TravellerResourcescheckboxfc = (CheckBox)v.findViewById(R.id.checkBox8);
        Transportationcheckboxfc = (CheckBox)v.findViewById(R.id.checkBox9);
        TheatreConcertscheckboxfc = (CheckBox)v.findViewById(R.id.checkBox10);
        ZoosAquariumsservedfc = (CheckBox)v.findViewById(R.id.checkBox11);
         otherscheeckbox = (CheckBox)v.findViewById(R.id.checkBox12);






        AmusementParkscheckbox.setChecked(true);

        Landmarkcheckbox.setChecked(true);


        Museumscheckbox.setChecked(true);
        NatureParkscheckbox.setChecked(true);
        OutdoorActivitiescheckbox.setChecked(true);
        SightsLandmarkscheckboxfv.setChecked(true);
        Shoppingcheckboxfc.setChecked(true);
        TravellerResourcescheckboxfc.setChecked(true);

        Transportationcheckboxfc.setChecked(true);

        TheatreConcertscheckboxfc.setChecked(true);
        ZoosAquariumsservedfc.setChecked(true);

        otherscheeckbox.setChecked(true);




        weightval = "0";
        anystarselect.setBackgroundColor(Color.parseColor("#00cccb"));
        twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
        threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
        fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));
        AlertUtils.RatingColorGreen(getActivity(), twoitem_ratingBar);
        try {
            twoitem_ratingBar.setRating(Float.parseFloat("2"));

        } catch (NumberFormatException e) {

        }


        AlertUtils.RatingColorGreen(getActivity(), fouritem_ratingBar);
        try {
            fouritem_ratingBar.setRating(Float.parseFloat("4"));

        } catch (NumberFormatException e) {

        }



        AlertUtils.RatingColorGreen(getActivity(), threeitem_ratingBar);
        try {
            threeitem_ratingBar.setRating(Float.parseFloat("3"));

        } catch (NumberFormatException e) {

        }



















        //First CheckBox
        AmusementParkscheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(AmusementParkscheckbox.isChecked())
                {
                    AmusementParks = "'Amusement Parks'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    AmusementParks="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });



        Landmarkcheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Landmarkcheckbox.isChecked())
                {
                    Landmark = "'Landmark'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    Landmark="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });


        Museumscheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Museumscheckbox.isChecked())
                {
                    Museums = "'Museums'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    Museums="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });




        NatureParkscheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(NatureParkscheckbox.isChecked())
                {
                    NatureParks = "'Nature & Parks'";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }
                else
                {
                    NatureParks="''";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });

        OutdoorActivitiescheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(OutdoorActivitiescheckbox.isChecked())
                {
                    OutdoorActivities = "'Outdoor Activities'";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    OutdoorActivities="''";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });



        SightsLandmarkscheckboxfv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(SightsLandmarkscheckboxfv.isChecked())
                {
                    SightsLandmarks = "'Sights & Landmarks'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    SightsLandmarks="''";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });




        Shoppingcheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Shoppingcheckboxfc.isChecked())
                {
                    Shopping = "'Shopping'";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }
                else
                {
                    Shopping="''";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });



        TravellerResourcescheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TravellerResourcescheckboxfc.isChecked())
                {
                    TravellerResources = "'Traveller Resources'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    TravellerResources="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });





        Transportationcheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Transportationcheckboxfc.isChecked())
                {
                    Transportation = "'Transportation'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }
                else
                {
                    Transportation="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });


        TheatreConcertscheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TheatreConcertscheckboxfc.isChecked())
                {
                    TheatreConcerts = "'Theatre & Concerts'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }
                else
                {
                    TheatreConcerts="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });






        ZoosAquariumsservedfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ZoosAquariumsservedfc.isChecked())
                {
                    ZoosAquariums = "'Zoos & Aquariums'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    ZoosAquariums="''";

                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });










        otherscheeckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(otherscheeckbox.isChecked())
                {
                    others = "'Others'";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                }
                else
                {
                    others="''";
                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                }

            }
        });





        tosno = Sessiondata.getInstance().getToursno();

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

            weightval = "2";

            anystarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            twostarselect.setBackgroundColor(Color.parseColor("#00cccb"));
            threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));


            AlertUtils.RatingColorGreen(getActivity(), fouritem_ratingBar);
            try {
                fouritem_ratingBar.setRating(Float.parseFloat("4"));

            } catch (NumberFormatException e) {

            }




            AlertUtils.RatingColorwhite(getActivity(), twoitem_ratingBar);
            try {
                twoitem_ratingBar.setRating(Float.parseFloat("2"));

            } catch (NumberFormatException e) {

            }


            AlertUtils.RatingColorGreen(getActivity(), threeitem_ratingBar);
            try {
                threeitem_ratingBar.setRating(Float.parseFloat("3"));

            } catch (NumberFormatException e) {

            }



        }

        else if (v == threestarselect){
            weightval = "3";

            anystarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            threestarselect.setBackgroundColor(Color.parseColor("#00cccb"));
            fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));


            AlertUtils.RatingColorGreen(getActivity(), fouritem_ratingBar);
            try {
                fouritem_ratingBar.setRating(Float.parseFloat("4"));

            } catch (NumberFormatException e) {

            }


            AlertUtils.RatingColorwhite(getActivity(), threeitem_ratingBar);
            try {
                threeitem_ratingBar.setRating(Float.parseFloat("3"));

            } catch (NumberFormatException e) {

            }


            AlertUtils.RatingColorGreen(getActivity(), twoitem_ratingBar);
            try {
                twoitem_ratingBar.setRating(Float.parseFloat("2"));

            } catch (NumberFormatException e) {

            }

        }

        else if (v == fourthstar){
            weightval = "4";

            anystarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            fourthstar.setBackgroundColor(Color.parseColor("#00cccb"));




            AlertUtils.RatingColorwhite(getActivity(), fouritem_ratingBar);
            try {
                fouritem_ratingBar.setRating(Float.parseFloat("4"));

            } catch (NumberFormatException e) {

            }





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






        }

        else if (v == anystarselect){

            weightval = "0";
            anystarselect.setBackgroundColor(Color.parseColor("#00cccb"));
            twostarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            threestarselect.setBackgroundColor(Color.parseColor("#ffffff"));
            fourthstar.setBackgroundColor(Color.parseColor("#ffffff"));
            AlertUtils.RatingColorGreen(getActivity(), twoitem_ratingBar);
            try {
                twoitem_ratingBar.setRating(Float.parseFloat("2"));

            } catch (NumberFormatException e) {

            }


            AlertUtils.RatingColorGreen(getActivity(), fouritem_ratingBar);
            try {
                fouritem_ratingBar.setRating(Float.parseFloat("4"));

            } catch (NumberFormatException e) {

            }



            AlertUtils.RatingColorGreen(getActivity(), threeitem_ratingBar);
            try {
                threeitem_ratingBar.setRating(Float.parseFloat("3"));

            } catch (NumberFormatException e) {

            }





            Sessiondata.getInstance().setExplorenewfullpage(1);

            //foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;







        }
        else if (v == applyfilter){

            //  Sessiondata.getInstance().setExplorenewfullpage(1);

            cidval = "3";
            //tosno







            if(AmusementParkscheckbox.isChecked())
            {
                AmusementParks = "'Amusement Parks'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+"," +OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                AmusementParks="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }


            if(Landmarkcheckbox.isChecked())
            {
                Landmark = "'Landmark'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                Landmark="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }



            if(Museumscheckbox.isChecked())
            {
                Museums = "'Museums'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                Museums="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }




            if(NatureParkscheckbox.isChecked())
            {
                NatureParks = "'Nature & Parks'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                NatureParks="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }


            if(OutdoorActivitiescheckbox.isChecked())
            {
                OutdoorActivities = "'Outdoor Activities'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                OutdoorActivities="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }


            if(SightsLandmarkscheckboxfv.isChecked())
            {
                SightsLandmarks = "'Sights & Landmarks'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                SightsLandmarks="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }

            if(Shoppingcheckboxfc.isChecked())
            {
                Shopping = "'Shopping'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                Shopping="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }


            if(TravellerResourcescheckboxfc.isChecked())
            {
                TravellerResources = "'Traveller Resources'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }
            else
            {
                TravellerResources="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }



            if(Transportationcheckboxfc.isChecked())
            {
                Transportation = "'Transportation'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                Transportation="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }




            if(TheatreConcertscheckboxfc.isChecked())
            {
                TheatreConcerts = "'Theatre & Concerts'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else
            {
                TheatreConcerts="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }


            if(ZoosAquariumsservedfc.isChecked())
            {
                ZoosAquariums = "'Zoos & Aquariums'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }
            else
            {
                ZoosAquariums="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }





            if(otherscheeckbox.isChecked())
            {
                others = "'Others'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";

            }
            else
            {
                others="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }







            if(foodcval.equals("'','','','','','','','','','','',''") && foottypeval.equals("''")) {


                AmusementParks = "'Amusement Parks'";
                Landmark = "'Landmark'";
                Museums = "'Museums'";
                NatureParks = "'Nature & Parks'";
                OutdoorActivities = "'Outdoor Activities'";
                SightsLandmarks = "'Sights & Landmarks'";
                Shopping = "'Shopping'";
                TravellerResources = "'Traveller Resources'";
                Transportation = "'Transportation'";
                TheatreConcerts = "'Theatre & Concerts'";
                ZoosAquariums = "'Zoos & Aquariums'";
                others = "'Others'";


                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                foottypeval = "''";
            }
            else {


                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                foottypeval = "''";

            }














            //Toast.makeText(getActivity(),tosno,Toast.LENGTH_LONG).show();
            foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            foottypeval = "''";

            // weightval



           /* Toast.makeText(getActivity(),cidval,Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),weightval,Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),foodcval,Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),foottypeval,Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),useridval,Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),neibourdis,Toast.LENGTH_LONG).show();*/



            mess = getResources().getString(R.string.base_url);
            retrofit = new Retrofit.Builder()
                    .baseUrl(mess)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            new WebPageTask(save,cidval,weightval,foodcval,foottypeval,useridval,neibourdis).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);






        }


        else if (v == allneighbourhoods) {


            /*Fragment fr = new Neighbourhoods();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);
*/




            Fragment fr = new Neighbourhoodsnew();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);










        }





        else if (v == clearall){






            AmusementParkscheckbox.setChecked(false);

            Landmarkcheckbox.setChecked(false);


            Museumscheckbox.setChecked(false);
            NatureParkscheckbox.setChecked(false);
            OutdoorActivitiescheckbox.setChecked(false);
            SightsLandmarkscheckboxfv.setChecked(false);
            Shoppingcheckboxfc.setChecked(false);
            TravellerResourcescheckboxfc.setChecked(false);

            Transportationcheckboxfc.setChecked(false);

            TheatreConcertscheckboxfc.setChecked(false);
            ZoosAquariumsservedfc.setChecked(false);

            otherscheeckbox.setChecked(false);




            if(AmusementParkscheckbox.isChecked())
            {
                AmusementParks = "'Amusement Parks'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+"," +OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                AmusementParks="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(Landmarkcheckbox.isChecked())
            {
                Landmark = "'Landmark'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                Landmark="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }



            if(Museumscheckbox.isChecked())
            {
                Museums = "'Museums'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                Museums="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }




            if(NatureParkscheckbox.isChecked())
            {
                NatureParks = "'Nature & Parks'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                NatureParks="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(OutdoorActivitiescheckbox.isChecked())
            {
                OutdoorActivities = "'Outdoor Activities'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                OutdoorActivities="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(SightsLandmarkscheckboxfv.isChecked())
            {
                SightsLandmarks = "'Sights & Landmarks'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                SightsLandmarks="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }

            if(Shoppingcheckboxfc.isChecked())
            {
                Shopping = "'Shopping'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                Shopping="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(TravellerResourcescheckboxfc.isChecked())
            {
                TravellerResources = "'Traveller Resources'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                TravellerResources="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }



            if(Transportationcheckboxfc.isChecked())
            {
                Transportation = "'Transportation'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                Transportation="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }




            if(TheatreConcertscheckboxfc.isChecked())
            {
                TheatreConcerts = "'Theatre & Concerts'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                TheatreConcerts="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(ZoosAquariumsservedfc.isChecked())
            {
                ZoosAquariums = "'Zoos & Aquariums'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                ZoosAquariums="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }





            if(otherscheeckbox.isChecked())
            {
                others = "'Others'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                others="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }



            Sessiondata.getInstance().setExplorenewfullpage(1);

            AmusementParks = "'Amusement Parks'";
            Landmark = "'Landmark'";
            Museums = "'Museums'";
            NatureParks = "'Nature & Parks'";
            OutdoorActivities = "'Outdoor Activities'";
            SightsLandmarks = "'Sights & Landmarks'";
            Shopping = "'Shopping'";
            TravellerResources = "'Traveller Resources'";
            Transportation = "'Transportation'";
            TheatreConcerts = "'Theatre & Concerts'";
            ZoosAquariums = "'Zoos & Aquariums'";
            others = "'Others'";



         //   foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;




           /* Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/
        }



        if (v == backclick){

            // Sessiondata.getInstance().setExplorenewfullpage(2);

            /*Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/


           /*Sessiondata.getInstance().setBackfilter(1);
            getFragmentManager().popBackStack();
*/
           /* Sessiondata.getInstance().setExplorenewfullpage(1);

            Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/





          //  getFragmentManager().popBackStack();







            AmusementParkscheckbox.setChecked(false);

            Landmarkcheckbox.setChecked(false);


            Museumscheckbox.setChecked(false);
            NatureParkscheckbox.setChecked(false);
            OutdoorActivitiescheckbox.setChecked(false);
            SightsLandmarkscheckboxfv.setChecked(false);
            Shoppingcheckboxfc.setChecked(false);
            TravellerResourcescheckboxfc.setChecked(false);

            Transportationcheckboxfc.setChecked(false);

            TheatreConcertscheckboxfc.setChecked(false);
            ZoosAquariumsservedfc.setChecked(false);

            otherscheeckbox.setChecked(false);




            if(AmusementParkscheckbox.isChecked())
            {
                AmusementParks = "'Amusement Parks'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+"," +OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                AmusementParks="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(Landmarkcheckbox.isChecked())
            {
                Landmark = "'Landmark'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                Landmark="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }



            if(Museumscheckbox.isChecked())
            {
                Museums = "'Museums'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                Museums="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }




            if(NatureParkscheckbox.isChecked())
            {
                NatureParks = "'Nature & Parks'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                NatureParks="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(OutdoorActivitiescheckbox.isChecked())
            {
                OutdoorActivities = "'Outdoor Activities'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                OutdoorActivities="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(SightsLandmarkscheckboxfv.isChecked())
            {
                SightsLandmarks = "'Sights & Landmarks'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                SightsLandmarks="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }

            if(Shoppingcheckboxfc.isChecked())
            {
                Shopping = "'Shopping'";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                Shopping="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(TravellerResourcescheckboxfc.isChecked())
            {
                TravellerResources = "'Traveller Resources'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                TravellerResources="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }



            if(Transportationcheckboxfc.isChecked())
            {
                Transportation = "'Transportation'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                Transportation="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }




            if(TheatreConcertscheckboxfc.isChecked())
            {
                TheatreConcerts = "'Theatre & Concerts'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }
            else
            {
                TheatreConcerts="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }


            if(ZoosAquariumsservedfc.isChecked())
            {
                ZoosAquariums = "'Zoos & Aquariums'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                ZoosAquariums="''";

                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }





            if(otherscheeckbox.isChecked())
            {
                others = "'Others'";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            }
            else
            {
                others="''";
                foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
            }



            Sessiondata.getInstance().setExplorenewfullpage(1);

            AmusementParks = "'Amusement Parks'";
            Landmark = "'Landmark'";
            Museums = "'Museums'";
            NatureParks = "'Nature & Parks'";
            OutdoorActivities = "'Outdoor Activities'";
            SightsLandmarks = "'Sights & Landmarks'";
            Shopping = "'Shopping'";
            TravellerResources = "'Traveller Resources'";
            Transportation = "'Transportation'";
            TheatreConcerts = "'Theatre & Concerts'";
            ZoosAquariums = "'Zoos & Aquariums'";
            others = "'Others'";






            cidval = "3";
            //tosno


            foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

            foottypeval = "''";

            // weightval

            mess = getResources().getString(R.string.base_url);
            retrofit = new Retrofit.Builder()
                    .baseUrl(mess)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            new WebPageTask(save,cidval,weightval,foodcval,foottypeval,useridval,neibourdis).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);






        }





    }




    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                   /* Sessiondata.getInstance().setExplorenewfullpage(1);
                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);*/




                    AmusementParkscheckbox.setChecked(false);

                    Landmarkcheckbox.setChecked(false);


                    Museumscheckbox.setChecked(false);
                    NatureParkscheckbox.setChecked(false);
                    OutdoorActivitiescheckbox.setChecked(false);
                    SightsLandmarkscheckboxfv.setChecked(false);
                    Shoppingcheckboxfc.setChecked(false);
                    TravellerResourcescheckboxfc.setChecked(false);

                    Transportationcheckboxfc.setChecked(false);

                    TheatreConcertscheckboxfc.setChecked(false);
                    ZoosAquariumsservedfc.setChecked(false);

                    otherscheeckbox.setChecked(false);




                    if(AmusementParkscheckbox.isChecked())
                    {
                        AmusementParks = "'Amusement Parks'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+"," +OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        AmusementParks="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }


                    if(Landmarkcheckbox.isChecked())
                    {
                        Landmark = "'Landmark'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        Landmark="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }



                    if(Museumscheckbox.isChecked())
                    {
                        Museums = "'Museums'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        Museums="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }




                    if(NatureParkscheckbox.isChecked())
                    {
                        NatureParks = "'Nature & Parks'";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }
                    else
                    {
                        NatureParks="''";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }


                    if(OutdoorActivitiescheckbox.isChecked())
                    {
                        OutdoorActivities = "'Outdoor Activities'";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        OutdoorActivities="''";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }


                    if(SightsLandmarkscheckboxfv.isChecked())
                    {
                        SightsLandmarks = "'Sights & Landmarks'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        SightsLandmarks="''";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }

                    if(Shoppingcheckboxfc.isChecked())
                    {
                        Shopping = "'Shopping'";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }
                    else
                    {
                        Shopping="''";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }


                    if(TravellerResourcescheckboxfc.isChecked())
                    {
                        TravellerResources = "'Traveller Resources'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        TravellerResources="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }



                    if(Transportationcheckboxfc.isChecked())
                    {
                        Transportation = "'Transportation'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }
                    else
                    {
                        Transportation="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }




                    if(TheatreConcertscheckboxfc.isChecked())
                    {
                        TheatreConcerts = "'Theatre & Concerts'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }
                    else
                    {
                        TheatreConcerts="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }


                    if(ZoosAquariumsservedfc.isChecked())
                    {
                        ZoosAquariums = "'Zoos & Aquariums'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        ZoosAquariums="''";

                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }





                    if(otherscheeckbox.isChecked())
                    {
                        others = "'Others'";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    }
                    else
                    {
                        others="''";
                        foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;
                    }



                    Sessiondata.getInstance().setExplorenewfullpage(1);

                    AmusementParks = "'Amusement Parks'";
                    Landmark = "'Landmark'";
                    Museums = "'Museums'";
                    NatureParks = "'Nature & Parks'";
                    OutdoorActivities = "'Outdoor Activities'";
                    SightsLandmarks = "'Sights & Landmarks'";
                    Shopping = "'Shopping'";
                    TravellerResources = "'Traveller Resources'";
                    Transportation = "'Transportation'";
                    TheatreConcerts = "'Theatre & Concerts'";
                    ZoosAquariums = "'Zoos & Aquariums'";
                    others = "'Others'";






                    cidval = "3";
                    //tosno


                    foodcval = AmusementParks+","+Landmark+","+Museums+","+NatureParks+","+OutdoorActivities+","+SightsLandmarks+","+Shopping+","+TravellerResources+","+Transportation+","+TheatreConcerts+","+ZoosAquariums+","+others;

                    foottypeval = "''";

                    // weightval

                    mess = getResources().getString(R.string.base_url);
                    retrofit = new Retrofit.Builder()
                            .baseUrl(mess)
                            .addConverterFactory(GsonConverterFactory.create()).build();

                    new WebPageTask(save,cidval,weightval,foodcval,foottypeval,useridval,neibourdis).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);












                    return true;
                }
                return false;
            }
        });

    }








    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid,weight,foodc,foottype,useridval,district;
        View vs;
        private WebPageTask(View vs, String subcatid, String weight,String foodc,String foottype,String useridval,String district) {
            this.subcatid = subcatid;
            this.vs = vs;
            this.weight = weight;
            this.foodc = foodc;
            this.foottype = foottype;
            this.useridval = useridval;
            this.district = district;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();
        }
        @Override
        protected CategorylistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);

           // Toast.makeText(getActivity(),tosno,Toast.LENGTH_LONG).show();

            Call<CategorylistValuesResponse> call = a.filterresultnewuiapi(subcatid, tosno, weight, foodc, foottype,useridval,district);
            CategorylistValuesResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }
        @Override
        protected void onPostExecute(CategorylistValuesResponse c) {

            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.Status == 1) {
                    if (null != c.result && c.result.size() > 0) {
                        for (CategorylistValuesResponse.categorylistvalue dd : c.result) {
                            List<photoss> photoitems = new ArrayList<>();
                            List<foodclassification> fooditems = new ArrayList<>();
                            List<tourdetails> tourdetails = new ArrayList<>();


                            if (dd.photosres.size() > 0) {
                                for (CategorylistValuesResponse.photosr s : dd.photosres) {
                                    photoitems.add(new photoss(s.id, s.photourl, s.credits));
                                }
                            }if (dd.foodclassificationres.size() > 0) {
                                for (CategorylistValuesResponse.foodclassificationr s : dd.foodclassificationres) {
                                    fooditems.add(new foodclassification(s.foodclassificationvalues));
                                }
                            }

                            if (dd.tourdetails.size() > 0) {
                                for (CategorylistValuesResponse.tourdetails s : dd.tourdetails) {
                                    tourdetails.add(new tourdetails(s.sno, s.image, s.content, s.sellingrate, s.currency, s.id, s.sub_id, s.image_one, s.image_two, s.image_three, s.image_four, s.tour_type, s.rate, s.highlights,s.inclusionandexclusion, s.exclusion, s.overviews, s.whatcanyouexpect, s.cancellation_policy, s.location, s.addi_info, s.tour_opt_info, s.tour_opt_link, s.website, s.number, s.tour_classification_one, s.tour_classification_two, s.enquiry, s.departurepoint, s.departuredate, s.departuretime, s.duration, s.returndetails));
                                }


                            }

                            items.add(new Categorylistmodel(dd.sno, dd.caegoryid, dd.name, dd.description,dd.foodc,dd.prayerc,dd.openhrs, dd.closehrs, dd.website, dd.timeremaining, dd.estimatedtime, dd.pricecurrency, dd.price, dd.country, dd.city, dd.state, dd.address, dd.postalcode, dd.map, dd.phone, dd.hhwtlink, dd.activity, dd.weight, dd.district, dd.latitude, dd.longitude, dd.weight, photoitems, fooditems, "", dd.tags, dd.foodttags, dd.foodctags, dd.tours,tourdetails,dd.overalreviews,dd.userreviews));



                            Sessiondata.getInstance().setThingstodonewuimain((ArrayList<Categorylistmodel>) items);

                            Sessiondata.getInstance().setExplorenewfullpage(1);



                            /*Fragment fr = new Exporeallnewui();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);*/
                        }
                    } else {
                        d.dismiss();
                        Snackbar snack = Snackbar.make(vs, "There is no item to show", Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                        ViewGroup group = (ViewGroup) snack.getView();
                        View v = snack.getView();
                        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);
                        group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                        snack.show();
                    }
                    if (items.size() > 0) {

                        itemsfinal = items;
                        int i = sortitemposition;
                        if (i == 0) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistmaxstart(itemsfinal, 0);}
                        } else if (i == 1) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistminstar(itemsfinal, 0);}
                        } else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {
                        d.dismiss();
                        Sessiondata.getInstance().setThingstodonewuimain(null);
                    }
                    d.dismiss();


                    Sessiondata.getInstance().setScroolthings(2);
                    Sessiondata.getInstance().setScroolfood(2);

                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);


                }


                else if (c.Status == 0) {
                    d.dismiss();
                    Sessiondata.getInstance().setThingstodonewuimain(null);
                    Sessiondata.getInstance().setExplorenewfullpage(1);

                    Toast.makeText(getActivity(), "Sorry no results available", Toast.LENGTH_LONG).show();


                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);
                }
            }
            else {


                d.dismiss();

                Sessiondata.getInstance().setExplorenewfullpage(1);

                //Toast.makeText(getActivity(),"failedfilter",Toast.LENGTH_LONG).show();


                Fragment fr = new Exporeallnewui();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }





        }}



    public void Categorylist(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        // Changeadapter(items, value);
    }


    public void Categorylistminstar(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        // Changeadapter(items, value);
    }


    public void Categorylistmaxstart(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        Collections.reverse(items);}


    public void Categorylistdesc(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Collections.reverse(items);
        //Changeadapter(items, value);
    }

}
