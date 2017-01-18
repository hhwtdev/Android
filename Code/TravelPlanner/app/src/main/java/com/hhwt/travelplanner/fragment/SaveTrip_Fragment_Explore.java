package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CategoryItem_list_Fragment;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.adapter.Savetripdatelistadapter;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.categorylistvalues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaveTrip_Fragment_Explore extends Fragment implements
        Savetripdatelistadapter.OnItemClickListener {
    private CreatedTripModel ctm;
    public static final String EXTRATRIPINFO = "Viewinfo";
    RecyclerView save;
    Categorylistmodel C;
    categorylistvalues CSS;
    ArrayList<String> dates = new ArrayList<>();
    Savetripdatelistadapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Hashtable connectFlags;
    CreatedTripModel S;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    int s;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_savetrip, container, false);
        hasOptionsMenu();

        final FloatingActionButton fabcreatingtrip = (FloatingActionButton) v.findViewById(R.id.createviewtrip);

        fabcreatingtrip.setVisibility(View.GONE);

        save = (RecyclerView) v.findViewById(R.id.rvsave);
        Bundle bundle = this.getArguments();
        _A = getActivity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fb_id = sharedpreferences.getString("fb_id", "0");
        internet = new InternetAccessCheck();
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        s = Sessiondata.getInstance().getSavetrip();
        if (s == 1) {
            S = (CreatedTripModel) bundle.getSerializable(CategoryItem_list_Fragment.EXTRAVIEWINFO);
            ctm = Sessiondata.getInstance().getCtmvalue();
            datasetchange(ctm);
            CSS = new categorylistvalues();
            Log.d("Csssno", "" + CSS.getSno());
        } else {
            C = (Categorylistmodel) bundle.getSerializable(CategoryItem_list_Fragment.EXTRAVIEWINFO);
            ctm = C.getCtm();
            datasetchange(ctm);
        }
        return v;
    }

    public SaveTrip_Fragment_Explore() {
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }
    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new CategoryItem_list_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    @Override
    public void onitemclick(View view, String viewModel) {
        try {
            if (s == 1) {
                new WebPageTask(view, fb_id, ctm.getTripid(), "", "", viewModel, Sessiondata.getInstance().getMapclicksno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new WebPageTask(view, fb_id, ctm.getTripid(), "", "", viewModel, C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        } catch (Exception e) {
        }}
    @Override
    public void onaddclick(View view, String viewModel) {
        try {
            String output = Date_utill.Dateincrement(ctm.getEndingdate());
            if (s == 1) {
                new WebTripUpdateTask(view, fb_id, ctm.getTripid(), ctm.getTripname(), ctm.getStartingdate(), output, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new WebTripUpdateTask(view, fb_id, ctm.getTripid(), ctm.getTripname(), ctm.getStartingdate(), output, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        } catch (Exception e) {

        }
    }
    private class WebPageTask extends AsyncTask<Void, TripRegisterResponse, TripRegisterResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid, tripid, sdate, edate, selecteddate, selected_categoryid;
        View vs;
        private WebPageTask(View vs, String fbid, String tripid, String sdate, String edate, String selecteddate, String selected_categoryid) {
            this.fbid = fbid;
            this.tripid = tripid;
            this.sdate = sdate;
            this.edate = edate;
            this.selecteddate = selecteddate;
            this.selected_categoryid = selected_categoryid;
            this.vs = vs;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();
        }

        @Override
        protected TripRegisterResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<TripRegisterResponse> call = a.Triptodo(fbid, tripid, "9.30 am", "10.30am", selecteddate, selected_categoryid);
            TripRegisterResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(TripRegisterResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    Snackbar snack = Snackbar.make(vs, "Trip Saved", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);

                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(ContextCompat.getColor(_A, R.color.cpb_green_dark));
                    snack.show();
                    getFragmentManager().popBackStack();


                    d.dismiss();
                } else {
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "Try again later", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(ContextCompat.getColor(_A, R.color.cpb_red_dark));
                    snack.show();
                    getFragmentManager().popBackStack();
                }
            }}}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();
                    return true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class WebTripUpdateTask extends AsyncTask<Void, TripRegisterResponse, TripRegisterResponse> {
        String tripid;
        View vs;
        String fbid;
        String tripname;
        String sdate;
        String edate;
        String tripdes;
        ProgressDialog d = new ProgressDialog(_A);

        private WebTripUpdateTask(final View vs, String fbid, String tripid, String tripname, String sdate, String edate, String tripdes) {
            this.tripid = tripid;
            this.tripname = tripname;
            this.sdate = sdate;
            this.edate = edate;
            this.tripdes = tripdes;
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
        protected TripRegisterResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<TripRegisterResponse> call = a.TripDateUpdate(fb_id, tripid, tripname, sdate, edate, tripdes);
            TripRegisterResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }

        @Override
        protected void onPostExecute(TripRegisterResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    ctm = new CreatedTripModel(c.tripid, c.tripname, c.startingdate, c.endingdate, c.cityid, c.city, c.img);
                    d.dismiss();
                    datasetchange(ctm);
                    Snackbar snack = Snackbar.make(vs, "Date has been added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                    snack.show();
                } else {
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "Something went wrong", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();
                }
            }
        }
    }

    public void datasetchange(CreatedTripModel ctm) {
        save.setHasFixedSize(true);
        dates = Date_utill.dates(ctm.getStartingdate(), ctm.getEndingdate());
        save.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        dates.add("");
        adapter = new Savetripdatelistadapter(dates);
        adapter.setOnItemClickListener(this);
        save.setAdapter(adapter);
    }
}