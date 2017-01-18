package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.AllCItyListPojo;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.City;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.adapter.VoteNextadapter;
import com.hhwt.travelplanner.fragment.Add_activities_Fragment;
import com.hhwt.travelplanner.fragment.Vote_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.MaterialRippleLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class City_CreateTrip_Fragment extends Fragment implements
        VoteNextadapter.OnItemClickListener {
    private CreatedTripModel ctm;
    public static final String EXTRATRIPINFO = "Viewinfo";
    RecyclerView save;
    Categorylistmodel C;
    ArrayList<String> dates = new ArrayList<>();
    VoteNextadapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Hashtable connectFlags;
    Retrofit retrofit;
    //TextView labelheader;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    RelativeLayout voteForNext;
    MaterialRippleLayout materialrippleeff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_votetrip, container, false);
        save = (RecyclerView) v.findViewById(R.id.rvsave);
        voteForNext = (RelativeLayout) v.findViewById(R.id.voteForNext);
        materialrippleeff = (MaterialRippleLayout) v.findViewById(R.id.materialrippleeff);
       /* labelheader = (TextView) v.findViewById(R.id.labelheader);
        labelheader.setText("Choose your city");*/
        _A = getActivity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        internet = new InternetAccessCheck();
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        save.setHasFixedSize(true);

        save.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        voteForNext.setVisibility(View.GONE);
        voteForNext.findViewById(R.id.voteForNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTrip_FromMytripFragment();
            }
        });

        return v;
    }

    public void CreateTrip_FromMytripFragment() {
        Fragment fr = new Vote_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public City_CreateTrip_Fragment() {

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            new WebPageTask(save, fb_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } catch (Exception we) {

        }
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
        Fragment fr = new Add_activities_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public void Votecity(List<City> trip) {
        adapter = new VoteNextadapter(trip);
        adapter.setOnItemClickListener(this);
        save.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, City c) {
        editor.putString("sno", c.getSno());
        editor.putString("city", c.getCity());
        editor.putString("img", c.getImg());
        editor.commit();
        showExploreFragment();
    }
    private class WebPageTask extends AsyncTask<Void, AllCItyListPojo, AllCItyListPojo> {

        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid;
        View vs;

        private WebPageTask(View vs, String fbid) {
            this.fbid = fbid;
            this.vs = vs;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();
        }

        @Override
        protected AllCItyListPojo doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<AllCItyListPojo> call = a.GetAllcity();
            AllCItyListPojo c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(AllCItyListPojo c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.getStatus() == 1) {
                    if (null != c.getCity() && c.getCity().size() > 0) {
                        save.setVisibility(View.VISIBLE);
                        materialrippleeff.setVisibility(View.VISIBLE);
                        Votecity(c.getCity());
                    } else {
                        save.setVisibility(View.GONE);
                        materialrippleeff.setVisibility(View.GONE);

                    }

                    d.dismiss();
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
            }
            d.dismiss();
        }
    }
    public void showExploreFragment() {
        Fragment fr = new CreateTripFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
}
