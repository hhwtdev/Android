package com.hhwt.travelplanner.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;


import java.util.Hashtable;
public class Addnewpalcesecond extends Fragment implements View.OnClickListener{
    Button food1,food2,food3;
    String category;
    String foodcat1,foodcat2,foodcat3,foodcat4,foodcat5,foodcat6,foodcat7,foodcat8,foodcat9;
    String prayersubcat1, prayersubcat2;
    String thiubcat1, thiubcat2,thiubcat3,thiubcat4,thiubcat5,thiubcat6,thiubcat7,thiubcat8,thiubcat9,thiubcat10;
    String thiubcat11,thiubcat12,thiubcat13,thiubcat14,thiubcat15,thiubcat16,thiubcat17;
    Button submitplacenext;
    int f0=0;
    int f2=0;
    int f3=0;
    Button foodsub1,foodsub2,foodsub3,foodsub4,foodsub5,foodsub6,foodsub7,foodsub8,foodsub9;
    LinearLayout linsubcat;
    LinearLayout catsucat2,catsucat3;
    int fsubcc1 =0;
    int fsubcc2 =0;
    int fsubcc3 =0;
    int fsubcc4 =0;
    int fsubcc5 =0;
    int fsubcc6 =0;

    int fsubcc7 =0;
    int fsubcc8 =0;
    int fsubcc9 =0;
    int prasubcc1 =0;
    int prasubcc2 =0;
    int subcc1 =0;
    int subcc2 =0;
    int subcc3 =0;
    int subcc4 =0;
    int subcc5 =0;
    int subcc6 =0;
    int subcc7 =0;
    int subcc8 =0;
    int subcc9 =0;
    int subcc10 =0;
    int subcc11 =0;
    int subcc12 =0;
    int subcc13 =0;
    int subcc14 =0;
    int subcc15 =0;
    int subcc16 =0;
    int subcc17 =0;
    int sval;
    ImageView backclick;

LinearLayout linearvalbottom;
    TextView apptitle;
    Button pracat1,pracat2;
    Button subc1,subc2,subc3,subc4,subc5,subc6,subc7,subc8,subc9,subc10,subc11,subc12,subc13,subc14,subc15,subc16,subc17;
    Hashtable connectFlags;
    String finalsubcate;
    public Addnewpalcesecond() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addaddresssecond, container, false);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);



        food1 = (Button)v.findViewById(R.id.food1);
        food2 = (Button)v.findViewById(R.id.food2);
        food3 = (Button)v.findViewById(R.id.food3);
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Add a new place");
        submitplacenext = (Button)v.findViewById(R.id.nextsecond);
        linsubcat = (LinearLayout) v.findViewById(R.id.subcat);
        catsucat2 = (LinearLayout)v.findViewById(R.id.subcatpra);
        catsucat3 = (LinearLayout)v.findViewById(R.id.subcatfood);
        foodsub1  = (Button)v.findViewById(R.id.sub1food);
        foodsub2 = (Button)v.findViewById(R.id.sub2food);
        foodsub3  = (Button)v.findViewById(R.id.sub3food);
        foodsub4 = (Button)v.findViewById(R.id.sub4food);
        foodsub5  = (Button)v.findViewById(R.id.sub5food);
        foodsub6 = (Button)v.findViewById(R.id.sub6food);
        foodsub7  = (Button)v.findViewById(R.id.sub7food);
        foodsub8 = (Button)v.findViewById(R.id.sub8food);
        foodsub9  = (Button)v.findViewById(R.id.sub9food);
        pracat1  = (Button)v.findViewById(R.id.sub1pra);
        pracat2 = (Button)v.findViewById(R.id.sub2pra);
        subc1 = (Button)v.findViewById(R.id.sub1);
        subc2 = (Button)v.findViewById(R.id.sub2);
        subc3 = (Button)v.findViewById(R.id.sub3);
        subc4 = (Button)v.findViewById(R.id.sub4);
        subc5 = (Button)v.findViewById(R.id.sub5);
        subc6 = (Button)v.findViewById(R.id.sub6);
        subc7 = (Button)v.findViewById(R.id.sub7);
        subc8 = (Button)v.findViewById(R.id.sub8);
        subc9 = (Button)v.findViewById(R.id.sub9);
        subc10 = (Button)v.findViewById(R.id.sub10);
        subc11 = (Button)v.findViewById(R.id.sub11);
        subc12 = (Button)v.findViewById(R.id.sub12);
        subc13 = (Button)v.findViewById(R.id.sub13);
        subc14 = (Button)v.findViewById(R.id.sub14);
        subc15 = (Button)v.findViewById(R.id.sub15);
        subc16 = (Button)v.findViewById(R.id.sub16);
        subc17 = (Button)v.findViewById(R.id.sub17);
        linsubcat.setVisibility(View.GONE);
        catsucat2.setVisibility(View.GONE);
        catsucat3.setVisibility(View.GONE);
        food1.setOnClickListener(this);
        food2.setOnClickListener(this);
        food3.setOnClickListener(this);
        foodsub1.setOnClickListener(this);
        foodsub2.setOnClickListener(this);
        foodsub3.setOnClickListener(this);
        foodsub4.setOnClickListener(this);
        foodsub5.setOnClickListener(this);
        foodsub6.setOnClickListener(this);
        foodsub7.setOnClickListener(this);
        foodsub8.setOnClickListener(this);
        foodsub9.setOnClickListener(this);
        pracat1.setOnClickListener(this);
        pracat2.setOnClickListener(this);
        subc1.setOnClickListener(this);
        subc2.setOnClickListener(this);
        subc3.setOnClickListener(this);
        subc4.setOnClickListener(this);
        subc5.setOnClickListener(this);
        subc6.setOnClickListener(this);
        subc7.setOnClickListener(this);
        subc8.setOnClickListener(this);
        subc9.setOnClickListener(this);
        subc10.setOnClickListener(this);
        subc11.setOnClickListener(this);
        subc12.setOnClickListener(this);
        subc13.setOnClickListener(this);
        subc14.setOnClickListener(this);
        subc15.setOnClickListener(this);
        subc16.setOnClickListener(this);
        subc17.setOnClickListener(this);
        submitplacenext.setOnClickListener(this);
        return  v;
    }
    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onClick(View v) {
        if (v == food1) {
            f0 = 1;
            category = "FOOD & DRINKS";
            food1.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
            food1.setText("FOOD & DRINKS");
            food1.setTextColor(Color.WHITE);
            food2.setClickable(false);
            food3.setClickable(false);
            linsubcat.setVisibility(View.GONE);
            catsucat2.setVisibility(View.GONE);
            catsucat3.setVisibility(View.VISIBLE);
            foodsub1.setText("Halal certified");
            foodsub1.setTextSize(11);
            foodsub2.setText("Muslim owned");
            foodsub2.setTextSize(11);
            foodsub3.setText("Alcohol served in this establishment");
            foodsub3.setTextSize(11);
            foodsub4.setText("Halal meat available");
            foodsub4.setTextSize(11);
            foodsub5.setText("Mosque nearby");
            foodsub5.setTextSize(11);
            foodsub6.setText("Seafood options available");
            foodsub6.setTextSize(11);
            foodsub7.setText("Vegetarian food available");
            foodsub7.setTextSize(11);
            foodsub8.setText("Kosher");
            foodsub8.setTextSize(11);
            foodsub9.setText("Prayer facilities in establishment");
            foodsub9.setTextSize(11);
            Sessiondata.getInstance().setSuvalue(1);
        }
        else if(v == backclick){

            getFragmentManager().popBackStack();
        }
        else if (v == food2) {
            f2 = 1;
            category = "THINGS TO DO";
            food2.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
            food2.setText("THINGS TO DO");
            food2.setTextColor(Color.WHITE);
            food1.setClickable(false);
            food3.setClickable(false);
            linsubcat.setVisibility(View.VISIBLE);
            catsucat2.setVisibility(View.GONE);
            catsucat3.setVisibility(View.GONE);
            subc1.setText("Transportation");
            subc1.setTextSize(11);
            subc2.setText("Museums");
            subc2.setTextSize(11);
            subc3.setText("Nightlife");
            subc3.setTextSize(11);
            subc4.setText("Fun & Games");
            subc4.setTextSize(11);
            subc5.setText("Amusement Parts");
            subc5.setTextSize(11);
            subc6.setText("Shopping");
            subc6.setTextSize(11);
            subc7.setText("BoatTours & WaterSports");
            subc7.setTextSize(11);
            subc8.setText("Classes & Workshops");
            subc8.setTextSize(11);
            subc9.setText("Nature & Parks");
            subc9.setTextSize(11);
            subc10.setText("Outdoor Activities");
            subc10.setTextSize(11);
            subc11.setText("Sights & Landmarks");
            subc11.setTextSize(11);
            subc12.setText("Spas & Wellness");
            subc12.setTextSize(11);
            subc13.setText("Theatre & Concerts");
            subc13.setTextSize(11);
            subc14.setText("Tours & Activities");
            subc14.setTextSize(11);
            subc15.setText("Traveller Resources");
            subc15.setTextSize(11);
            subc16.setText("Zoos & Aquariums");
            subc16.setTextSize(11);
            subc17.setText("Others");
            subc17.setTextSize(11);
            Sessiondata.getInstance().setSuvalue(2);
        }
        else if (v == food3) {
            f3 = 1;
            category = "PRAYER SPACES";
            food3.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
            food3.setText("PRAYER SPACES");
            food3.setTextColor(Color.WHITE);
            food1.setClickable(false);
            food2.setClickable(false);
            linsubcat.setVisibility(View.GONE);
            catsucat2.setVisibility(View.VISIBLE);
            catsucat3.setVisibility(View.GONE);
            pracat1.setText("Mosque");
            pracat1.setTextSize(11);
            pracat2.setText("Prayer Facilities");
            pracat2.setTextSize(11);
            Sessiondata.getInstance().setSuvalue(3);
        }
        else if(v == foodsub1) {
            if(fsubcc1 == 0) {
                fsubcc1 = 1;
                foodcat1 = "Halal certified";
                foodsub1.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub1.setTextColor(Color.WHITE);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub1.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc1 = 0;}
        }
        else if(v == foodsub2) {
            if(fsubcc2 == 0) {
                fsubcc2 = 1;
                foodcat2 = "Muslim owned";
                foodsub2.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub2.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub2.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc2 = 0;}
        }
        else if(v == foodsub3) {
            if(fsubcc3 == 0) {
                fsubcc3 = 1;
                foodcat3 = "Alcohol served in this establishment";
                foodsub3.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub3.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub3.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc3 = 0;
            }
        }
        else if(v == foodsub4) {
            if(fsubcc4 == 0) {
                fsubcc4 = 1;
                foodcat4 = "Halal meat available";
                foodsub4.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub4.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub4.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub4.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc4 = 0;
            }
        }
        else if(v == foodsub5) {
            if(fsubcc5 == 0) {
                fsubcc5 = 1;
                foodcat5 = "Mosque nearby";
                foodsub5.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub5.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub5.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub5.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc5 = 0;
            }
        }
        else if(v == foodsub6) {
            if(fsubcc6 == 0) {
                fsubcc6 = 1;
                foodcat6 = "Seafood options available";
                foodsub6.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub6.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub6.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub6.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc6 = 0;
            }
        }
        else if(v == foodsub7) {
            if(fsubcc7 == 0) {
                fsubcc7 = 1;
                foodcat7 = "Vegetarian food available";
                foodsub7.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub7.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub8.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub7.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub7.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc7 = 0;
            }
        }
        else if(v == foodsub8) {
            if(fsubcc8 == 0) {
                fsubcc8 = 1;
                foodcat8 = "Kosher";
                foodsub8.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub8.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub9.setClickable(true);
            }
            else {
                foodsub8.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub8.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc8 = 0;
            }
        }
        else if(v == foodsub9) {
            if(fsubcc9 == 0) {
                fsubcc9 = 1;
                foodcat9 = "Prayer facilities in establishment";
                foodsub9.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                foodsub9.setTextColor(Color.WHITE);
                foodsub1.setClickable(true);
                foodsub2.setClickable(true);
                foodsub3.setClickable(true);
                foodsub4.setClickable(true);
                foodsub5.setClickable(true);
                foodsub6.setClickable(true);
                foodsub7.setClickable(true);
                foodsub8.setClickable(true);
            }
            else {
                foodsub9.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                foodsub9.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                fsubcc9 = 0;
            }
        }
        else if(v == pracat1)
        {if(prasubcc1 == 0) {
                prasubcc1 = 1;
                prayersubcat1 = "Mosque";
                pracat1.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                pracat1.setTextColor(Color.WHITE);
                pracat2.setClickable(true);
            }
            else {
                pracat1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                pracat1.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                prasubcc1 = 0;
        }
        }
        else if(v == pracat2)
        {if(prasubcc2 == 0) {
                prasubcc2 = 1;
                prayersubcat2 = "Prayer Facilities";
                pracat2.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                pracat2.setTextColor(Color.WHITE);
                pracat1.setClickable(true);
            }
            else {
                pracat2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                pracat2.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                prasubcc2 = 0;

            }
        }
        else if(v == subc1){
            if(subcc1 == 0) {
                subcc1 = 1;
                thiubcat1 = "Transportation";
                subc1.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc1.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else{
                subc1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc1.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc1 = 0;
            }}
        else if(v == subc2){
            if(subcc2 == 0) {
                subcc2 = 1;
                thiubcat2 = "Museums";
                subc2.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc2.setTextColor(Color.WHITE);
                subc1.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else{
                subc2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc2.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc2 = 0;}
        }
        else if(v == subc3){
            if(subcc3 == 0) {
                subcc3 = 1;
                thiubcat3 ="Nightlife";
                subc3.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc3.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc1.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else{
                subc3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc3.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc3 = 0;
            }
        }
        else if(v == subc4){
            if(subcc4 == 0) {
                subcc4 = 1;
                thiubcat4 ="Fun & Games";
                subc4.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc4.setTextColor(Color.WHITE);
                subc2.setClickable(true);subc3.setClickable(true);
                subc1.setClickable(true);
                subc5.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc4.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc4.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc4 = 0;
            }
        }
        else if(v == subc5){
            if(subcc5 == 0) {
                subcc5 = 1;
                thiubcat5 ="Amusement Parts";
                subc5.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc5.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc5.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc5.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc5 = 0;
            }}
        else if(v == subc6){
            if(subcc6 == 0) {
                subcc6 = 1;
                thiubcat6="Shopping";
                subc6.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc6.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc6.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc6.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc6 = 0;
            }}
        else if(v == subc7){
            if(subcc7 == 0) {
                subcc7 = 1;
                thiubcat7 ="BoatTours & WaterSports";
                subc7.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc7.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc7.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc7.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc7 = 0;}}
        else if(v == subc8){
            if(subcc8 == 0) {
                subcc8 = 1;
                thiubcat8="Classes & Workshops";
                subc8.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc8.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc8.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc8.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc8 = 0;
            }
        }
        else if(v == subc9){
            if(subcc9 == 0) {
                subcc9 = 1;
                thiubcat9="Nature & Parks";
                subc9.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc9.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc9.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc9.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc9 = 0;
            }
        }
        else if(v == subc10){
            if(subcc10 == 0) {
                subcc10 = 1;
                thiubcat10="Outdoor Activities";
                subc10.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc10.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc10.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc10.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc10 = 0;
            }
        }
        else if(v == subc11){
            if(subcc11 == 0) {
                subcc11 = 1;
                thiubcat11="Sights & Landmarks";
                subc11.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc11.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc11.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc11.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc11 = 0;
            }}
        else if(v == subc12){
            if(subcc12 == 0) {
                subcc12 = 1;
                thiubcat12="Spas & Wellness";
                subc12.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc12.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);
            }
            else {
                subc12.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc12.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc12 = 0;}
        }
        else if(v == subc13){
            if(subcc13 == 0) {
                subcc13 = 1;
                thiubcat13="Theatre & Concerts";
                subc13.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc13.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);}
            else {
                subc13.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc13.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc13 = 0;
            }}
        else if(v == subc14){
            if(subcc14 == 0) {
                subcc14 = 1;
                thiubcat14="Tours & Activities";
                subc14.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc14.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);}
            else {
                subc14.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc14.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc14 = 0;}}
        else if(v == subc15){
            if(subcc15 == 0) {
                subcc15 = 1;
                thiubcat15="Traveller Resources";
                subc15.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc15.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc16.setClickable(true);
                subc17.setClickable(true);}
            else {
                subc15.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc15.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc15 = 0;
            }}
        else if(v == subc16){
            if(subcc16 == 0) {
                subcc16 = 1;
                thiubcat16="Zoos & Aquariums";
                subc16.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc16.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc17.setClickable(true);}
            else {
                subc16.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc16.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc16 = 0;}}
        else if(v == subc17){
            if(subcc17 == 0) {
                subcc17 = 1;
                thiubcat17="Others";
                subc17.setBackgroundDrawable(getResources().getDrawable(R.drawable.foodbgselect));
                subc17.setTextColor(Color.WHITE);
                subc2.setClickable(true);
                subc3.setClickable(true);
                subc4.setClickable(true);
                subc5.setClickable(true);
                subc1.setClickable(true);
                subc6.setClickable(true);
                subc7.setClickable(true);
                subc8.setClickable(true);
                subc9.setClickable(true);
                subc10.setClickable(true);
                subc11.setClickable(true);
                subc12.setClickable(true);
                subc13.setClickable(true);
                subc14.setClickable(true);
                subc15.setClickable(true);
                subc16.setClickable(true);
            }
            else {
                subc17.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                subc17.setTextColor(getContext().getResources().getColor(R.color.feedtypecolor));
                subcc17 = 0;
            }
        }
        else if(v == submitplacenext) {
            sval = Sessiondata.getInstance().getSuvalue();
            if (fsubcc1 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (fsubcc2 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (fsubcc3 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }

            else if (fsubcc4 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (fsubcc5 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            } else if (fsubcc6 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (fsubcc7 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (fsubcc8 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (fsubcc9 == 1)
            {finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;
            }
            else if (prasubcc1 == 1) {
                finalsubcate = prayersubcat1+","+prayersubcat2;
            }
            else if (prasubcc2 == 1) {
                finalsubcate = prayersubcat1+","+prayersubcat2;}
            else if (subcc1 == 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;
             }
            else if (subcc2 == 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc3 == 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc4 == 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc5 == 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc6== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
             else if (subcc7== 1) {
                 finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;
             }
            else if (subcc8== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc9== 1) {finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc10== 1) {
                 finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc11== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc12== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc13== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc14== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc15== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc16== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
            else if (subcc17== 1) {
                finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
           else if(f0 == 1){
            }
            else if(f2 == 1){
            }
            else if(f3 == 1){
            }
            else if (sval == 1){
                 finalsubcate  = foodcat1+","+foodcat2+","+foodcat3+","+foodcat4+","+foodcat5+","+foodcat6+","+foodcat7+","+foodcat8+","+foodcat9;}
             else if (sval == 2){
                 finalsubcate = thiubcat1+","+thiubcat2+","+thiubcat3+","+thiubcat4+","+thiubcat5+","+thiubcat6+","+thiubcat7+","+thiubcat8+","+thiubcat9+","+thiubcat10+","+thiubcat11+","+thiubcat12+","+thiubcat13+","+thiubcat14+","+thiubcat15+","+thiubcat16+","+thiubcat17;}
             else if (sval == 3){
                 finalsubcate = prayersubcat1+","+prayersubcat2;}
            Sessiondata.getInstance().setSubcategreyvalues(finalsubcate);
            Sessiondata.getInstance().setMaincategory(category);
            Fragment fr = new Addnewpalcethird();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }}}