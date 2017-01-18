package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.RectImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Hashtable;

public class Explore_activities_Fragment extends Fragment implements
        DatePickerDialog.OnDateSetListener {

    public static final String EXTRATRIPINFO = "Viewinfo";
    private CreatedTripModel ctm;
    Hashtable connectFlags;
    RectImageView bigpage;
    RobotoTextView cityName;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static Activity _A;
    public String fb_id, _CityName, _CityID, _CityImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_activity, container, false);
        _A = getActivity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "0");
        _CityName = sharedpreferences.getString("city", "0");
        _CityImage = sharedpreferences.getString("img", "0");
        bigpage = (RectImageView) v.findViewById(R.id.bigpage);
        cityName = (RobotoTextView) v.findViewById(R.id.cityName);
        cityName.setText(_CityName);
        Picasso.with(getActivity()).load(_CityImage).networkPolicy(NetworkPolicy.OFFLINE).into(bigpage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(getActivity())
                        .load(_CityImage)
                        .error(R.drawable.background_default)
                        .into(bigpage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }
                            @Override
                            public void onError() {
                            }
                        });
            }
        });
        ctm = new CreatedTripModel();
        v.findViewById(R.id.food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctm.setCategoryID("1");
                ctm.setCategorytype("Restaurant info");
                showguideFragment(ctm);
            }
        });
        v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctm.setCategoryID("3");
                ctm.setCategorytype("Things to do info");
                showguideFragment(ctm);
            }
        });
        v.findViewById(R.id.prayers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctm.setCategoryID("2");
                ctm.setCategorytype("Prayers info");
                showguideFragment(ctm);
            }
        });
        return v;
    }
    public void onConnectSuccess() {

    }

    public void onConnectFailure() {

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
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
    }

    public Explore_activities_Fragment() {
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new CategoryItem_list_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}