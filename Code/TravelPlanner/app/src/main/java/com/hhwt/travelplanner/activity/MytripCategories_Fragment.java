package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.MytriplistValuesResponse;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.adapter.Datelistadapter;
import com.hhwt.travelplanner.adapter.MyCategorylistadapter;
import com.hhwt.travelplanner.fragment.Add_activities_Thingstodo_Fragment;
import com.hhwt.travelplanner.fragment.View_mytripInfo_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.Categorylistmodelmytrip;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.CustomLinearLayoutManager;
import com.hhwt.travelplanner.widgets.RectImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class MytripCategories_Fragment extends Fragment implements
        MyCategorylistadapter.OnItemClickListener,View.OnClickListener {
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    RecyclerView categoryitems;
    MyCategorylistadapter adapter;
    public static final String EXTRATRIPINFO = "Viewinfo";
    public static final String EXTRAVIEWINFO = "Viewinfo";
    List<Categorylistmodelmytrip> itemsfinal = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    public CreatedTripModel newtrip;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id, _CityName, _CityID, _CityImage;
    ArrayList<String> dates = new ArrayList<>();
    RecyclerView dateslist;
    Datelistadapter adapter2;
    RobotoTextView cityname;
    RectImageView cityimage;
    Hashtable connectFlags;
    ImageView backclick;
    LinearLayout linearvalbottom;

TextView apptitle;
    public void showViewDetFragment(Categorylistmodelmytrip viewModel) {
        Fragment fr = new View_mytripInfo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    @Override
    public void onFavItemClick(View view, Categorylistmodelmytrip viewModel) {
        viewModel.setCtm(newtrip);
        showViewDetFragment(viewModel);


    }

    @Override
    public void onDeleteitemClick(final View view, final Categorylistmodelmytrip viewmodel) {
        new AlertDialog.Builder(_A)
                .setTitle("Delete event")
                .setMessage("Are you sure you want to delete this event?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        try {
                            new MytripstodeleteTask(view, fb_id, newtrip.getTripid(), viewmodel.getDate(), viewmodel.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        } catch (Exception e) {

                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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
        }
    }


    @Retention(RUNTIME)
    @interface Json {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentmy_itemlist, container, false);
        //  Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        ArrayList<String> filtertypetype = new ArrayList<>();
        ArrayList<String> code = new ArrayList<>();
        internet = new InternetAccessCheck();


        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Trips");


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);



        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);



        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));



//        Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        // Tapjoy.setDebugEnabled(true);

        _A = getActivity();
        setHasOptionsMenu(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "1");
        _CityName = sharedpreferences.getString("city", "Seoul");
        _CityImage = sharedpreferences.getString("img", "0");
        Bundle bundle = this.getArguments();
        newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        categoryitems = (RecyclerView) v.findViewById(R.id.categoryitems);
        dates = Date_utill.dates(newtrip.getStartingdate(), newtrip.getEndingdate());
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        cityname= (RobotoTextView) v.findViewById(R.id.cityname);
        cityimage=(RectImageView)v.findViewById(R.id.seulk);
        dateslist = (RecyclerView) v.findViewById(R.id.datelist);
        setdateadapter(dates);
        RobotoTextView tripname = (RobotoTextView) v.findViewById(R.id.tripname);
        tripname.setText(newtrip.getTripname());
        // use a linear layout manager
        cityname.setText(_CityName);
        //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
        Picasso.with(getActivity()).load(_CityImage).networkPolicy(NetworkPolicy.OFFLINE).into(cityimage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(getActivity())
                        .load(_CityImage)
                        .error(R.drawable.background_default)
                        .into(cityimage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                            }
                        });
            }
        });
        v.findViewById(R.id.adddate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String output = Date_utill.Dateincrement(newtrip.getEndingdate());
                    try {
                        //String tripname, String sdate, String edate, String tripdes) {
                        new WebTripUpdateTask(v, fb_id, newtrip.getTripid(), newtrip.getTripname(), newtrip.getStartingdate(), output, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception e) {

                    }
                } catch (ParseException e) {

                } catch (NullPointerException e) {

                }
            }
        });

        // Show a datepicker when the dateButton is clickedow a datepicker when the dateButton is clicked
        // Inflate the layout for this fragment
        return v;
    }


    // called when Tapjoy connect call succeed

    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    // called when Tapjoy connect call failed

    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }

    //session start
    @Override
    public void onStart() {
        super.onStart();
        //Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
       // Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }


    private void setdateadapter(ArrayList<String> dates) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            dateslist.setLayoutManager(layoutManager);
        } else {
           // final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

            dateslist.setLayoutManager(layoutManager);

        }
        //     save.setLayoutManager(mLayoutManager);
        adapter2 = new Datelistadapter(dates);
        dateslist.setAdapter(adapter2);
    }

    public void showViewDetFragment(CreatedTripModel viewModel) {
        Fragment fr = new Add_activities_Thingstodo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public MytripCategories_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            newtrip.getTripid();
            new WebPageTask(categoryitems, newtrip.getTripid()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } catch (Exception e) {

        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void Categorylist(List<Categorylistmodelmytrip> items) {
        Collections.sort(items, new Comparator<Categorylistmodelmytrip>() {
            public int compare(Categorylistmodelmytrip v1, Categorylistmodelmytrip v2) {
                return v1.getDate().compareTo(v2.getDate());
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        categoryitems.setLayoutManager(layoutManager);
        adapter = new MyCategorylistadapter(getActivity(), items);
        adapter.setOnItemClickListener(this);
        categoryitems.setAdapter(adapter);
        categoryitems.setNestedScrollingEnabled(false);


    }


    private class WebPageTask extends AsyncTask<Void, MytriplistValuesResponse, MytriplistValuesResponse> {

        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodelmytrip> items = new ArrayList<>();
        View vs;
        String tripid;

        private WebPageTask(View vs, String tripid) {
            this.tripid = tripid;
            this.vs = vs;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            d.setMessage("Please wait...");
            d.show();
        }

        @Override
        protected MytriplistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<MytriplistValuesResponse> call = a.MytripCategoriesapi(fb_id, tripid);
            Log.d("samfbid", "" + fb_id);
            Log.d("samtripid", "" + tripid);


            // Fetch and print a list of the contributors to the library.
            MytriplistValuesResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(MytriplistValuesResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    if (null != c.result && c.result.size() > 0) {
                        for (MytriplistValuesResponse.categorylistvalue dd : c.result) {
                            List<photoss> photoitems = new ArrayList<>();
                            List<foodclassification> fooditems = new ArrayList<>();
                            if (dd.photosres.size() > 0) {
                                for (MytriplistValuesResponse.photosr s : dd.photosres) {
                                    photoitems.add(new photoss(s.id, s.photourl, s.credits));
                                }
                            }
                            if (dd.foodclassificationres.size() > 0) {
                                for (MytriplistValuesResponse.foodclassificationr s : dd.foodclassificationres) {
                                    fooditems.add(new foodclassification(s.foodclassificationvalues));
                                }
                            }

                            items.add(new Categorylistmodelmytrip(dd.sno, dd.caegoryid, dd.name, dd.notes, dd.idnumber, dd.description, dd.openhrs, dd.closehrs, dd.website, dd.timeremaining, dd.estimatedtime, dd.pricecurrency, dd.price, dd.country, dd.city, dd.state, dd.address, dd.postalcode, dd.map, dd.phone, dd.hhwtlink, dd.activity, dd.weight, dd.district, dd.latitude, dd.longitude, dd.weight, photoitems, fooditems, dd.date, dd.tags));

                            Sessiondata.getInstance().setNotesno((ArrayList<Categorylistmodelmytrip>) items);

                            Log.d("Arraylistvalues", "" + Sessiondata.getInstance().getNotesno());

                        }
                    } else {
                        d.dismiss();
                        //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                        //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
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
                        categoryitems.setVisibility(View.VISIBLE);
                        Categorylist(items);
                        itemsfinal = items;
                    } else {
                        categoryitems.setVisibility(View.GONE);
                    }
                    d.dismiss();
                } else {
                    d.dismiss();
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
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
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<TripRegisterResponse> call = a.TripDateUpdate(fb_id, tripid, tripname, sdate, edate, tripdes);

            // Fetch and print a list of the contributors to the library.
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
                    newtrip = new CreatedTripModel(c.tripid, c.tripname, c.startingdate, c.endingdate, c.cityid, c.city, c.img);
                    d.dismiss();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // only for lollypop and newer versions
                        adapter2.add(c.endingdate);
                    } else {
                        dates.add(c.endingdate);
                        setdateadapter(dates);
                    }
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
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            getFragmentManager().popBackStack();
        }
    }


    private class MytripstodeleteTask extends AsyncTask<Void, TripRegisterResponse, TripRegisterResponse> {

        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid, tripid, Selecteddate, ID;
        View vs;
        String del;


        private MytripstodeleteTask(final View vs, String fbid, String tripid, String Selecteddate, String ID) {
            this.fbid = fbid;
            this.tripid = tripid;
            this.Selecteddate = Selecteddate;
            this.ID = ID;
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
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<TripRegisterResponse> call = a.DeleteTripitem(fbid, tripid, Selecteddate, ID);

            // Fetch and print a list of the contributors to the library.
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
                    try {
                        newtrip.getTripid();
                        new WebPageTask(categoryitems, newtrip.getTripid()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception we) {

                    }
                    d.dismiss();
                } else {
                    d.dismiss();
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
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

}
