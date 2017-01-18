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

/**
 * Created by jeyavijay on 15/10/16.
 */
public class FilterFoodfragment extends Fragment implements View.OnClickListener{

    RatingBar twoitem_ratingBar,threeitem_ratingBar,fouritem_ratingBar;

    LinearLayout twostarselect,threestarselect,fourthstar,anystarselect;
    String mess;
    TextView applyfilter,clearall;
    Retrofit retrofit;
    public int sortitemposition = 0;
    CheckBox Koreancheckbox,westerncheckbox,fusioncheckbox,cafecheckbox,otherscheckbox;

    CheckBox halalcertifiedcheckboxfv,muslimownedcheckboxfc,halalmeatcheckboxfc,seafoodcheckboxfc,vegetariancheckboxfc,alcoholservedfc;

    String korean,western,fusion,cafe,others,halalcertificated,muslimowned,halalmeat,seafood,vegtarian,alcoholserved;

    String tosno,cidval;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();


    String useridval;

LinearLayout allneighbourhoods;



String weightval;
    public static Activity _A;
    TextView countryapptitle;
    SearchBox search;
    ImageView filter,toolbarsearch;

    String foodcval,foottypeval;
    TextView apptitle;
    LinearLayout linearvalbottom;
    String allneirboorval,neibourdis;
    ImageView backclick;
View save;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.filterflow,container,false);
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

        useridval = Sessiondata.getInstance().getReviewfbemailid();

        allneirboorval = Sessiondata.getInstance().getNeighboorhoodval();
        neibourdis = allneirboorval;

       // Toast.makeText(getActivity(),neibourdis,Toast.LENGTH_LONG).show();





        allneighbourhoods = (LinearLayout) v.findViewById(R.id.allneighbourhoods);

        allneighbourhoods.setOnClickListener(this);








        halalcertificated = "'Halal certified'";
        muslimowned      = "'Muslim Owned'";
        halalmeat   =  "'Halal meat'";
        seafood      = "'Seafood'";
        vegtarian    =  "'Vegetarian'";
        alcoholserved = "'Alcohol served'";



        korean  = "'Korean'";
        western = "'Western'";
        fusion = "'Fusion'";
        cafe = "'Japanese'";
        others = "'Others'";










        twoitem_ratingBar = (RatingBar) v.findViewById(R.id.twoitem_ratingBar);
        threeitem_ratingBar = (RatingBar) v.findViewById(R.id.threeitem_ratingBar);
        fouritem_ratingBar = (RatingBar) v.findViewById(R.id.fouritem_ratingBar);
        twostarselect = (LinearLayout) v.findViewById(R.id.twostarselect);
        threestarselect =  (LinearLayout) v.findViewById(R.id.threestarselect);
        fourthstar = (LinearLayout) v.findViewById(R.id.fourthstar);
        anystarselect = (LinearLayout) v.findViewById(R.id.anystarselect);
        applyfilter = (TextView) v.findViewById(R.id.applyfilter);
        clearall = (TextView) v.findViewById(R.id.clearall);



        Koreancheckbox = (CheckBox)v.findViewById(R.id.checkBox1);
        westerncheckbox = (CheckBox)v.findViewById(R.id.checkBox2);
        fusioncheckbox = (CheckBox)v.findViewById(R.id.checkBox3);
        cafecheckbox = (CheckBox)v.findViewById(R.id.checkBox4);
        otherscheckbox = (CheckBox)v.findViewById(R.id.checkBox5);


        halalcertifiedcheckboxfv = (CheckBox)v.findViewById(R.id.checkBox6);
        muslimownedcheckboxfc = (CheckBox)v.findViewById(R.id.checkBox7);
        halalmeatcheckboxfc = (CheckBox)v.findViewById(R.id.checkBox8);
        seafoodcheckboxfc = (CheckBox)v.findViewById(R.id.checkBox9);
        vegetariancheckboxfc = (CheckBox)v.findViewById(R.id.checkBox10);
        alcoholservedfc = (CheckBox)v.findViewById(R.id.checkBox11);




        Koreancheckbox.setChecked(true);
        westerncheckbox.setChecked(true);
        fusioncheckbox.setChecked(true);
        cafecheckbox.setChecked(true);
        otherscheckbox.setChecked(true);
        halalcertifiedcheckboxfv.setChecked(true);
        muslimownedcheckboxfc.setChecked(true);
        halalmeatcheckboxfc.setChecked(true);
        seafoodcheckboxfc.setChecked(true);
        vegetariancheckboxfc.setChecked(true);
        alcoholservedfc.setChecked(true);









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
        Koreancheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Koreancheckbox.isChecked())
                {
                    korean = "'Korean'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }
                else
                {
                    korean="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });



        westerncheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(westerncheckbox.isChecked())
                {
                    western = "'Western'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    western="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });


        fusioncheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(fusioncheckbox.isChecked())
                {
                    fusion = "'Fusion'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    fusion="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });




        cafecheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cafecheckbox.isChecked())
                {
                    cafe = "'Japanese'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    cafe="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });

        otherscheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(otherscheckbox.isChecked())
                {
                    others = "'Others'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    others="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });



        halalcertifiedcheckboxfv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(halalcertifiedcheckboxfv.isChecked())
                {
                    halalcertificated = "'Halal certified'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    halalcertificated="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });




        muslimownedcheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(muslimownedcheckboxfc.isChecked())
                {
                    muslimowned = "'Muslim Owned'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    muslimowned="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });



        halalmeatcheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(halalmeatcheckboxfc.isChecked())
                {
                    halalmeat = "'Halal meat'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    halalmeat="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });





        seafoodcheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seafoodcheckboxfc.isChecked())
                {
                    seafood = "'Seafood'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    seafood="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });


        vegetariancheckboxfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(vegetariancheckboxfc.isChecked())
                {
                    vegtarian = "'Vegetarian'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    vegtarian="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                }

            }
        });






        alcoholservedfc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(alcoholservedfc.isChecked())
                {
                    alcoholserved = "'Alcohol served'";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                }
                else
                {
                    alcoholserved="''";
                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
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




        }
        else if (v == applyfilter){

          //  Sessiondata.getInstance().setExplorenewfullpage(1);

            cidval = "1";
            //tosno

           // foodcval = korean+","+western+","+fusion+","+cafe+","+others;






            if(Koreancheckbox.isChecked())
            {
                korean = "'Korean'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }
            else
            {
                korean="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(westerncheckbox.isChecked())
            {
                western = "'Western'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                western="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(fusioncheckbox.isChecked())
            {
                fusion = "'Fusion'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                fusion="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(cafecheckbox.isChecked())
            {
                cafe = "'Japanese'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                cafe="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(otherscheckbox.isChecked())
            {
                others = "'Others'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                others="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(halalcertifiedcheckboxfv.isChecked())
            {
                halalcertificated = "'Halal certified'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                halalcertificated="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }



            if(muslimownedcheckboxfc.isChecked())
            {
                muslimowned = "'Muslim Owned'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                muslimowned="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(halalmeatcheckboxfc.isChecked())
            {
                halalmeat = "'Halal meat'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                halalmeat="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(seafoodcheckboxfc.isChecked())
            {
                seafood = "'Seafood'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                seafood="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(vegetariancheckboxfc.isChecked())
            {
                vegtarian = "'Vegetarian'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                vegtarian="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(alcoholservedfc.isChecked())
            {
                alcoholserved = "'Alcohol served'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                alcoholserved="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }






if(foodcval.equals("'','','','','',''") && foottypeval.equals("'','','','',''")){



    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";



    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


else if (foottypeval.equals("'Korean','','','',''") && foodcval.equals("'','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "''";
    fusion = "''";
    cafe = "''";
    others = "''";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}




else if (foottypeval.equals("'','Western','','',''") && foodcval.equals("'','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";

    korean  = "''";
    western = "'Western'";
    fusion = "''";
    cafe = "''";
    others = "''";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



else if (foottypeval.equals("'','','Fusion','',''") && foodcval.equals("'','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";

    korean  = "''";
    western = "''";
    fusion = "'Fusion'";
    cafe = "''";
    others = "''";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



else if (foottypeval.equals("'','','','Japanese',''") && foodcval.equals("'','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";

    korean  = "''";
    western = "''";
    fusion = "''";
    cafe = "'Japanese'";
    others = "''";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


else if (foottypeval.equals("'','','','','Others'") && foodcval.equals("'','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";

    korean  = "''";
    western = "''";
    fusion = "''";
    cafe = "''";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','','',''")){

    halalcertificated = "''";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}
else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','','',''")){

    halalcertificated = "''";
    muslimowned      = "''";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}
else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','Seafood','',''")){

    halalcertificated = "''";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "'Seafood'";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}
else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','','Vegetarian',''")){

    halalcertificated = "''";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','','','Alcohol served'")){

    halalcertificated = "''";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



//fooodclasification alonesufing




else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','Seafood','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "'Seafood'";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}







else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','','Vegetarian',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}

else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','','','Alcohol served'")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','','',''")){

    halalcertificated = "''";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','Seafood','',''")){

    halalcertificated = "''";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "'Seafood'";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}



else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','','Vegetarian',''")){

    halalcertificated = "''";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}







else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','','',''Alcohol served")){

    halalcertificated = "''";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','Seafood','',''")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','','Vegetarian',''")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','','',''Alcohol served")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','Seafood','Vegetarian',''")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','Seafood','','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','','Vegetarian','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }







else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','','','Alcohol served'")){

    halalcertificated = "''";
    muslimowned      = "''";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}

else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','','','Alcohol served'")){

    halalcertificated = "''";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}





else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}




else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','Seafood','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "''";
    seafood      = "'Seafood'";
    vegtarian    =  "''";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}




else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','','Vegetarian',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "''";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}
























            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','','Vegetarian',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','','','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "''";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','Seafood','',''")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','','Vegetarian',''")){
                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','','','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','Seafood','Vegetarian',''")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','Seafood','','Alcohol served'")){
                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','Seafood','','Alcohol served'")){
                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','Seafood','','Alcohol served'")){
                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','Seafood','','Alcohol served'")){
                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','Seafood','Vegetarian',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','Seafood','Vegetarian',''")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','','Vegetarian','Alcohol served'")){
                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','','Vegetarian','Alcohol served'")){
                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','Seafood','','Alcohol served'")){
                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','','','Alcohol served'")){
                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "''";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','','','Alcohol served'")){
                halalcertificated = "' '";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','Seafood','','Alcohol served'")){
                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }








else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','','','Alcohol served'")){
    halalcertificated = "'Halal certified'";
    muslimowned      = "''";
    halalmeat   =  "'Halal meat'";
    seafood      = "''";
    vegtarian    =  "''";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}















            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','','Seafood','Vegetarian',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','','Seafood','Vegetarian',''")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','Seafood','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','','Vegetarian',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','','','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','Seafood','Vegetarian',''")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','Seafood','','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','Seafood','','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','','Halal meat','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }








            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','Seafood','','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','Seafood','Vegetarian',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'','Muslim Owned','Halal meat','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "''";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','','Halal meat','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "''";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "''";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "''";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','Seafood','','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "''";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','Seafood','Vegetarian',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "''";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'','','','',''") && foodcval.equals("'Halal certified','Muslim Owned','Halal meat','Seafood','Vegetarian','Alcohol served'")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





else if (foottypeval.equals("'Korean','Western','Fusion','Japanese','Others'") && foodcval.equals("'','','','','',''")){

    halalcertificated = "'Halal certified'";
    muslimowned      = "'Muslim Owned'";
    halalmeat   =  "'Halal meat'";
    seafood      = "'Seafood'";
    vegtarian    =  "'Vegetarian'";
    alcoholserved = "'Alcohol served'";

    korean  = "'Korean'";
    western = "'Western'";
    fusion = "'Fusion'";
    cafe = "'Japanese'";
    others = "'Others'";

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


}




            else if (foottypeval.equals("'Korean','Western','Fusion','Japanese',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'','Western','Fusion','Japanese',''Others") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'Korean','Western','Fusion','',''Others") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'Korean','Western','','Japanese',''Others") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "''";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }

            else if (foottypeval.equals("'Korean','','Fusion','Japanese',''Others") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "''";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }






            else if (foottypeval.equals("'Korean','Western','Fusion','',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "''";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'Korean','Western','','Japanese',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "''";
                cafe = "'Japanese'";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'Korean','Western','','','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "''";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }





            else if (foottypeval.equals("'','Western','Fusion','Japanese',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','Western','Fusion','','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }









                       else if (foottypeval.equals("'','Western','','Japanese','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "''";
                cafe = "''Japanese";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'Korean','','Fusion','','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "''";
                fusion = "'Fusion'";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','','Fusion','Japanese','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "''";
                fusion = "'Fusion'";
                cafe = "Japanese''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'Korean','','','Japanese','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "''";
                fusion = "''";
                cafe = "Japanese''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }











            else if (foottypeval.equals("'','','','Japanese','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "''";
                fusion = "''";
                cafe = "Japanese''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','Fusion','','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "''";
                fusion = "'Fusion'";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','','Fusion','Japanese',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "''";
                fusion = "'Fusion'";
                cafe = "'Japanese'";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','Western','','','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "''";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'','Western','','Japanese',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "''";
                cafe = "'Japanese'";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }


            else if (foottypeval.equals("'','Western','Fusion','',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "''";
                western = "'Western'";
                fusion = "'Fusion'";
                cafe = "''";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'Korean','Western','','',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "'Western'";
                fusion = "''";
                cafe = "''";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }




            else if (foottypeval.equals("'Korean','','Fusion','',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "''";
                fusion = "'Fusion'";
                cafe = "''";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }



            else if (foottypeval.equals("'Korean','','','Japanese',''") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "''";
                fusion = "''";
                cafe = "'Japanese'";
                others = "''";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }






            else if (foottypeval.equals("'Korean','','','','Others'") && foodcval.equals("'','','','','',''")){

                halalcertificated = "'Halal certified'";
                muslimowned      = "'Muslim Owned'";
                halalmeat   =  "'Halal meat'";
                seafood      = "'Seafood'";
                vegtarian    =  "'Vegetarian'";
                alcoholserved = "'Alcohol served'";

                korean  = "'Korean'";
                western = "''";
                fusion = "''";
                cafe = "''";
                others = "'Others'";

                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;


            }








            else {

    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
}














            foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

            foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

           // weightval




          /*  Toast.makeText(getActivity(),cidval,Toast.LENGTH_LONG).show();
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



          /*  Fragment fr = new Neighbourhoods();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/



            Fragment fr = new Neighbourhoodsnew();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);







        }







        else if (v == clearall){






            Koreancheckbox.setChecked(false);
            westerncheckbox.setChecked(false);
            fusioncheckbox.setChecked(false);
            cafecheckbox.setChecked(false);
            otherscheckbox.setChecked(false);
            halalcertifiedcheckboxfv.setChecked(false);
            muslimownedcheckboxfc.setChecked(false);
            halalmeatcheckboxfc.setChecked(false);
            seafoodcheckboxfc.setChecked(false);
            vegetariancheckboxfc.setChecked(false);
            alcoholservedfc.setChecked(false);


            if(Koreancheckbox.isChecked())
            {
                korean = "'Korean'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }
            else
            {
                korean="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(westerncheckbox.isChecked())
            {
                western = "'Western'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                western="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(fusioncheckbox.isChecked())
            {
                fusion = "'Fusion'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                fusion="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(cafecheckbox.isChecked())
            {
                cafe = "'Japanese'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                cafe="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(otherscheckbox.isChecked())
            {
                others = "'Others'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                others="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(halalcertifiedcheckboxfv.isChecked())
            {
                halalcertificated = "'Halal certified'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                halalcertificated="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }



            if(muslimownedcheckboxfc.isChecked())
            {
                muslimowned = "'Muslim Owned'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                muslimowned="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(halalmeatcheckboxfc.isChecked())
            {
                halalmeat = "'Halal meat'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                halalmeat="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(seafoodcheckboxfc.isChecked())
            {
                seafood = "'Seafood'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                seafood="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(vegetariancheckboxfc.isChecked())
            {
                vegtarian = "'Vegetarian'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                vegtarian="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(alcoholservedfc.isChecked())
            {
                alcoholserved = "'Alcohol served'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                alcoholserved="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


          /*  foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

            foottypeval = korean+","+western+","+fusion+","+cafe+","+others;*/



         /*   halalcertificated = "0";
            muslimowned      = "0";
            halalmeat   =  "0";
            seafood      = "0";
            vegtarian    =  "0";
            alcoholserved = "0";



            korean  = "0";
            western = "0";
            fusion = "0";
            cafe = "0";
            others = "0";
*/



            halalcertificated = "'Halal certified'";
            muslimowned      = "'Muslim Owned'";
            halalmeat   =  "'Halal meat'";
            seafood      = "'Seafood'";
            vegtarian    =  "'Vegetarian'";
            alcoholserved = "'Alcohol served'";



            korean  = "'Korean'";
            western = "'Western'";
            fusion = "'Fusion'";
            cafe = "'Japanese'";
            others = "'Others'";

/*
            foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

            foottypeval = korean+","+western+","+fusion+","+cafe+","+others;*/






            Sessiondata.getInstance().setExplorenewfullpage(2);

           /* Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/
        }



        if (v == backclick){

           // Sessiondata.getInstance().setExplorenewfullpage(2);

            /*Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/


       /*     Sessiondata.getInstance().setBackfilter(1);
            getFragmentManager().popBackStack();*/


          //  getFragmentManager().popBackStack();





            Koreancheckbox.setChecked(false);
            westerncheckbox.setChecked(false);
            fusioncheckbox.setChecked(false);
            cafecheckbox.setChecked(false);
            otherscheckbox.setChecked(false);
            halalcertifiedcheckboxfv.setChecked(false);
            muslimownedcheckboxfc.setChecked(false);
            halalmeatcheckboxfc.setChecked(false);
            seafoodcheckboxfc.setChecked(false);
            vegetariancheckboxfc.setChecked(false);
            alcoholservedfc.setChecked(false);


            if(Koreancheckbox.isChecked())
            {
                korean = "'Korean'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }
            else
            {
                korean="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(westerncheckbox.isChecked())
            {
                western = "'Western'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                western="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(fusioncheckbox.isChecked())
            {
                fusion = "'Fusion'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                fusion="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(cafecheckbox.isChecked())
            {
                cafe = "'Japanese'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                cafe="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(otherscheckbox.isChecked())
            {
                others = "'Others'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                others="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(halalcertifiedcheckboxfv.isChecked())
            {
                halalcertificated = "'Halal certified'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                halalcertificated="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }



            if(muslimownedcheckboxfc.isChecked())
            {
                muslimowned = "'Muslim Owned'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                muslimowned="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(halalmeatcheckboxfc.isChecked())
            {
                halalmeat = "'Halal meat'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                halalmeat="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(seafoodcheckboxfc.isChecked())
            {
                seafood = "'Seafood'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                seafood="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


            if(vegetariancheckboxfc.isChecked())
            {
                vegtarian = "'Vegetarian'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                vegtarian="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }

            if(alcoholservedfc.isChecked())
            {
                alcoholserved = "'Alcohol served'";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

            }
            else
            {
                alcoholserved="''";
                foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
            }


          /*  foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

            foottypeval = korean+","+western+","+fusion+","+cafe+","+others;*/



         /*   halalcertificated = "0";
            muslimowned      = "0";
            halalmeat   =  "0";
            seafood      = "0";
            vegtarian    =  "0";
            alcoholserved = "0";



            korean  = "0";
            western = "0";
            fusion = "0";
            cafe = "0";
            others = "0";
*/



            halalcertificated = "'Halal certified'";
            muslimowned      = "'Muslim Owned'";
            halalmeat   =  "'Halal meat'";
            seafood      = "'Seafood'";
            vegtarian    =  "'Vegetarian'";
            alcoholserved = "'Alcohol served'";



            korean  = "'Korean'";
            western = "'Western'";
            fusion = "'Fusion'";
            cafe = "'Japanese'";
            others = "'Others'";

            cidval = "1";


            foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

            foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

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


                   /* Sessiondata.getInstance().setExplorenewfullpage(2);

                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);*/






                    Koreancheckbox.setChecked(false);
                    westerncheckbox.setChecked(false);
                    fusioncheckbox.setChecked(false);
                    cafecheckbox.setChecked(false);
                    otherscheckbox.setChecked(false);
                    halalcertifiedcheckboxfv.setChecked(false);
                    muslimownedcheckboxfc.setChecked(false);
                    halalmeatcheckboxfc.setChecked(false);
                    seafoodcheckboxfc.setChecked(false);
                    vegetariancheckboxfc.setChecked(false);
                    alcoholservedfc.setChecked(false);


                    if(Koreancheckbox.isChecked())
                    {
                        korean = "'Korean'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }
                    else
                    {
                        korean="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }


                    if(westerncheckbox.isChecked())
                    {
                        western = "'Western'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        western="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }

                    if(fusioncheckbox.isChecked())
                    {
                        fusion = "'Fusion'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        fusion="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }

                    if(cafecheckbox.isChecked())
                    {
                        cafe = "'Japanese'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        cafe="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }

                    if(otherscheckbox.isChecked())
                    {
                        others = "'Others'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        others="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }


                    if(halalcertifiedcheckboxfv.isChecked())
                    {
                        halalcertificated = "'Halal certified'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        halalcertificated="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }



                    if(muslimownedcheckboxfc.isChecked())
                    {
                        muslimowned = "'Muslim Owned'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        muslimowned="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }

                    if(halalmeatcheckboxfc.isChecked())
                    {
                        halalmeat = "'Halal meat'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        halalmeat="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }


                    if(seafoodcheckboxfc.isChecked())
                    {
                        seafood = "'Seafood'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        seafood="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }


                    if(vegetariancheckboxfc.isChecked())
                    {
                        vegtarian = "'Vegetarian'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        vegtarian="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }

                    if(alcoholservedfc.isChecked())
                    {
                        alcoholserved = "'Alcohol served'";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

                    }
                    else
                    {
                        alcoholserved="''";
                        foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                        foottypeval = korean+","+western+","+fusion+","+cafe+","+others;
                    }


          /*  foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

            foottypeval = korean+","+western+","+fusion+","+cafe+","+others;*/



         /*   halalcertificated = "0";
            muslimowned      = "0";
            halalmeat   =  "0";
            seafood      = "0";
            vegtarian    =  "0";
            alcoholserved = "0";



            korean  = "0";
            western = "0";
            fusion = "0";
            cafe = "0";
            others = "0";
*/



                    halalcertificated = "'Halal certified'";
                    muslimowned      = "'Muslim Owned'";
                    halalmeat   =  "'Halal meat'";
                    seafood      = "'Seafood'";
                    vegtarian    =  "'Vegetarian'";
                    alcoholserved = "'Alcohol served'";



                    korean  = "'Korean'";
                    western = "'Western'";
                    fusion = "'Fusion'";
                    cafe = "'Japanese'";
                    others = "'Others'";

                    cidval = "1";


                    foodcval = halalcertificated+","+muslimowned+","+halalmeat+","+seafood+","+vegtarian+","+alcoholserved;

                    foottypeval = korean+","+western+","+fusion+","+cafe+","+others;

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

            Call<CategorylistValuesResponse> call = a.foodfilterresultnewuiapi(subcatid, tosno, weight, foodc, foottype,useridval,district);
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
                            Sessiondata.getInstance().setFoodcatgory((ArrayList<Categorylistmodel>) items);

                            Sessiondata.getInstance().setExplorenewfullpage(2);



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
                        Sessiondata.getInstance().setFoodcatgory(null);
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
                    Sessiondata.getInstance().setFoodcatgory(null);
                    Sessiondata.getInstance().setExplorenewfullpage(2);

                    //Toast.makeText(getActivity(),"failedfilter",Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), "Sorry no results available", Toast.LENGTH_LONG).show();

                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);
                }
            }
            else {


                d.dismiss();
                Sessiondata.getInstance().setExplorenewfullpage(2);

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
