package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.activity.CategoryItem_list_Fragment;
import com.hhwt.travelplanner.activity.CreateTripFragment;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

public class Add_activities_Thingstodo_Fragment extends Fragment implements
        DatePickerDialog.OnDateSetListener {
    public static final String EXTRATRIPINFO = "Viewinfo";
    private CreatedTripModel ctm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_activity, container, false);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));


        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        Bundle bundle = this.getArguments();
        ctm = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
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
    public Add_activities_Thingstodo_Fragment() {

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
}