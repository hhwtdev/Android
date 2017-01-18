package com.hhwt.travelplanner.activity;

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
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.adapter.Savetripdatelistadapter;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 30/08/16.
 */
public class Createdatetripnew extends Fragment implements
        Savetripdatelistadapter.OnItemClickListener,View.OnClickListener {
private CreatedTripModel ctm;
public static final String EXTRATRIPINFO = "Viewinfo";
        RecyclerView save;
    String m;
        CreatedTripModel C;
        ArrayList<String> dates = new ArrayList<>();
        Savetripdatelistadapter adapter;
private RecyclerView.LayoutManager mLayoutManager;

        Hashtable connectFlags;
    ImageView backclick;

        Retrofit retrofit;
        InternetAccessCheck internet;
public static Activity _A;
        String mess;
        CreatedTripModel S;
        int z;

        String MyPREFERENCES = "HHWT";
        SharedPreferences sharedpreferences;
        SharedPreferences.Editor editor;
public String fb_id;

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getFragmentManager().getBackStackEntryCount() == 1) getActivity().finish();
            }
        });
    }
*/
    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_savetrip, container, false);

    backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
    backclick.setVisibility(View.VISIBLE);
    backclick.setOnClickListener(this);
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
    m = Sessiondata.getInstance().getSnocreatenewtrip();
        z = Sessiondata.getInstance().getMtrip();
    if (z == 1) {
        S = (CreatedTripModel) bundle.getSerializable(CategoryItem_list_Fragment.EXTRAVIEWINFO);
        ctm = Sessiondata.getInstance().getCtmvalue();
        }
    else {
        C = (CreatedTripModel) bundle.getSerializable(CategoryItem_list_Fragment.EXTRAVIEWINFO);
        ctm = Sessiondata.getInstance().getCreatedatelist();
    }
    dates = Date_utill.dates(ctm.getStartingdate(), ctm.getEndingdate());
        datasetchange(ctm);
        return v;
        }

public Createdatetripnew() {
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
        if (z == 1) {
        Log.d("Csssnomap", "" + Sessiondata.getInstance().getMapclicksno());
        new WebPageTask(view, fb_id, ctm.getTripid(), "", "", viewModel, Sessiondata.getInstance().getMapclicksno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            Log.d("one", "" + fb_id);
            Log.d("tone", "" + ctm.getTripid());
            Log.d("fione", "" + m);
            Log.d("thone", "" + viewModel);

        new WebPageTask(view, fb_id, ctm.getTripid(), "", "", viewModel, m).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        } catch (Exception e) {

        }
        }

@Override
public void onaddclick(View view, String viewModel) {
        try {
        if (z == 1) {

        String output = Date_utill.Dateincrement(ctm.getEndingdate());
        new WebTripUpdateTask(view, fb_id, ctm.getTripid(), ctm.getTripname(), ctm.getStartingdate(), output, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
        String output = Date_utill.Dateincrement(ctm.getEndingdate());
        //String tripname, String sdate, String edate, String tripdes) {
        new WebTripUpdateTask(view, fb_id, ctm.getTripid(), ctm.getTripname(), ctm.getStartingdate(), output, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        } catch (Exception e) {

        }
        }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == backclick){
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();

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
                    // handle back button's click listener


                   // Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();

                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });

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
    }@Override
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
                group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                snack.show();
                d.dismiss();
                getFragmentManager().popBackStack();
                getFragmentManager().popBackStack();
                getFragmentManager().popBackStack();

            } else {
                d.dismiss();
                Snackbar snack = Snackbar.make(vs, "Try again later", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                ViewGroup group = (ViewGroup) snack.getView();
                View v = snack.getView();
                TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                snack.show();
                d.dismiss();
                getFragmentManager().popBackStack();}}}}
    private class WebTripUpdateTask extends AsyncTask<Void, TripRegisterResponse, TripRegisterResponse> {String tripid;
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
    }@Override
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
    }@Override
    protected void onPostExecute(TripRegisterResponse c) {
        super.onPostExecute(c);
        String anullcheck = c != null ? "Yes"
                : "no";
        if (anullcheck.equals("Yes")) {
            if (c.status == 1) {
                ctm = new CreatedTripModel(c.tripid, c.tripname, c.startingdate, c.endingdate,c.cityid,c.city,c.img);
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
