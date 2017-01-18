package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.adapter.StarFilterAdapter;
import com.hhwt.travelplanner.adapter.TypeFilterAdapter;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.model.FilterList;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
public class Filter_Fragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    RecyclerView _Starfilter, _TypeFilter;
    RobotoTextView saveclick;
    Activity _activity;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    public static final String EXTRATRIPINFO = "Viewinfo";
    public static final String EXTRAFILTERINFO = "Viewfilter";
    public CreatedTripModel newtrip;
    public static FilterList FL = new FilterList();
    StarFilterAdapter Adapter1;
    TypeFilterAdapter Adapter2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_fragment, container, false);
        _activity = getActivity();
        _Starfilter = (RecyclerView) v.findViewById(R.id.starbase);
        _TypeFilter = (RecyclerView) v.findViewById(R.id.typeface);
        saveclick = (RobotoTextView) v.findViewById(R.id.saveclick);
        Bundle bundle = this.getArguments();
        newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        FL = (FilterList) bundle.getSerializable(EXTRAFILTERINFO);
        GridLayoutManager layoutM = new GridLayoutManager(_activity, 2);
        _TypeFilter.setLayoutManager(layoutM);
        _TypeFilter.setNestedScrollingEnabled(false);
        GridLayoutManager layoutManager = new GridLayoutManager(_activity, 2);
        _Starfilter.setLayoutManager(layoutManager);
        _Starfilter.setNestedScrollingEnabled(false);
        Adapter1 = new StarFilterAdapter(_activity, FL.getStarlist());
        _Starfilter.setAdapter(Adapter1);
        Adapter2 = new TypeFilterAdapter(_activity, FL.getList());
        _TypeFilter.setAdapter(Adapter2);
        saveclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FL = new FilterList(Adapter2.ItemList, Adapter1.ItemList);
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
