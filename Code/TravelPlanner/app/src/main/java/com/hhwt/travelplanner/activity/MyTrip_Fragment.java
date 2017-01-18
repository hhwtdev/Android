package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.CustomApplicationClass;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.MyTripResponse;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.adapter.MyTripadapter;
import com.hhwt.travelplanner.fragment.Add_activities_Fragment;
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

public class MyTrip_Fragment extends Fragment implements
        MyTripadapter.OnItemClickListener {
    private CreatedTripModel ctm;
    public static final String EXTRATRIPINFO = "Viewinfo";
    RecyclerView save;
    Categorylistmodel C;
    ArrayList<String> dates = new ArrayList<>();
    MyTripadapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Hashtable connectFlags;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    RelativeLayout createnewtrip;
    TextView  apptitle;
     MaterialRippleLayout materialrippleeff;
    LinearLayout linearvalbottom;

    ImageView backclick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_savetrip, container, false);


//        Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));


        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Trips");


        CustomApplicationClass.getInstance().trackScreenView("Trips");

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.GONE);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.VISIBLE);



        final FloatingActionButton fabcreatingtrip = (FloatingActionButton) v.findViewById(R.id.createviewtrip);

        save = (RecyclerView) v.findViewById(R.id.rvsave);
        createnewtrip = (RelativeLayout) v.findViewById(R.id.createnewtrip);
        materialrippleeff = (MaterialRippleLayout) v.findViewById(R.id.materialrippleeff);
        _A = getActivity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");

       fabcreatingtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                City_CreateTripFromMytrip_Fragment();
            }});




        internet = new InternetAccessCheck();
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        save.setHasFixedSize(true);
        save.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        createnewtrip.setVisibility(View.VISIBLE);


        createnewtrip.findViewById(R.id.createnewtrip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City_CreateTripFromMytrip_Fragment();
            }
        });







        return v;
    }

    public void City_CreateTripFromMytrip_Fragment() {
        Fragment fr = new City_CreateTripFromMytrip_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public MyTrip_Fragment() {

    }

    @Override
    public void onResume() {
        super.onResume();

            CustomApplicationClass.getInstance().trackScreenView("Trips");

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
        Sessiondata.getInstance().setTripeditupdate(1);
        Fragment fr = new Add_activities_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void Mytrips(List<CreatedTripModel> trip) {
        adapter = new MyTripadapter(trip);
        adapter.setOnItemClickListener(this);
        save.setAdapter(adapter);
    }

    private class WebPageTask extends AsyncTask<Void, MyTripResponse, MyTripResponse> {
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
        protected MyTripResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<MyTripResponse> call = a.User_trip_details(fbid);
            MyTripResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }

        @Override
        protected void onPostExecute(MyTripResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    if (null != c.result && c.result.size() > 0) {
                        List<CreatedTripModel> trip = new ArrayList<>();
                        for (MyTripResponse.Mytrip m : c.result) {
                            CreatedTripModel newtrip = new CreatedTripModel(m.tripid, m.tripname, m.startingdate, m.endingdate, m.cityid, m.city, m.img);
                            newtrip.setCreatedon(m.createdon);
                            trip.add(newtrip);
                        }
                        if (trip.size() > 0) {
                            save.setVisibility(View.VISIBLE);
                            materialrippleeff.setVisibility(View.GONE);
                            Mytrips(trip);
                        } else {
                            save.setVisibility(View.GONE);
                            //visible
                            materialrippleeff.setVisibility(View.GONE);
                        }
                    } else {
                        save.setVisibility(View.GONE);
                        materialrippleeff.setVisibility(View.VISIBLE);
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

    private class MytripstodeleteTask extends AsyncTask<Void, TripRegisterResponse, TripRegisterResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid;
        View vs;
        String del;

        private MytripstodeleteTask(View vs, String fbid, String del) {
            this.fbid = fbid;
            this.del = del;
            this.vs = vs;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Updating view ...");
            d.show();
        }

        @Override
        protected TripRegisterResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<TripRegisterResponse> call = a.DeleteTrip(fbid, del);
            TripRegisterResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }

        protected void onPostExecute(TripRegisterResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    try {
                        new WebPageTask(save, fb_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception we) {

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

    @Override
    public void onviewclick(View view, CreatedTripModel c) {
        editor.putString("sno", c.getCityid());
        editor.putString("city", c.getCity());
        editor.putString("img", c.getImg());
        editor.commit();
        showguideFragment(c);
    }

    @Override
    public void oneditclick(View view, final CreatedTripModel viewModel) {
        new AlertDialog.Builder(_A)
                .setTitle("Delete Trip")
                .setMessage("Are you sure you want to delete this trip?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            new MytripstodeleteTask(save, fb_id, viewModel.getTripid()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        } catch (Exception e) {

                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}