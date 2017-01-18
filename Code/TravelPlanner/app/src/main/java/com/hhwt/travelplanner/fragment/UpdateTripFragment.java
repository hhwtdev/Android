package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.MyTripResponse;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CreateTripFragment;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.fragment.Add_activities_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.RectImageView;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateTripFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener,View.OnClickListener {
    private RobotoTextView start_date, btncreateatrip;
    private RobotoTextView end_date;
    private Boolean _startDate;
    private RobotoEditText entertripname;
    Hashtable connectFlags;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    ProgressDialog progressDialog;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id, _CityName, _CityID, _CityImage;
    public CreatedTripModel newtrip;
    RectImageView cityIcon;
    RobotoTextView cityName;
    int tsucess;
    String message;
    String tripidval;
    String tripname;
    String startingdate;
    String endingdate;
    String createdon;
    ImageView backclick;
    String cityid;
    String city;
    String image;
    View save;
    public static final String KEY_FB_ID = "fb_id";
    public static final String KEY_TRIPID = "tripid";
    public static final String KEY_TRIPNAME = "tripname";
    public static final String KEY_SDATE = "sdate";
    public static final String KEY_EDATE = "edate";
    public static final String KEY_TRIPDES = "tripdes";
    private static final String UPDATETRIP = "http://www.hhwt.tech/hhwt_webservice/update_tripdetails.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_trip, container, false);
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        _A = getActivity();
        Bundle bundle = this.getArguments();
        newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        internet = new InternetAccessCheck();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "0");
        _CityName = sharedpreferences.getString("city", "0");
        _CityImage = sharedpreferences.getString("img", "0");
        internet = new InternetAccessCheck();

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        // Find our View instances
        start_date = (RobotoTextView) v.findViewById(R.id.start_date);
        end_date = (RobotoTextView) v.findViewById(R.id.end_date);
        entertripname = (RobotoEditText) v.findViewById(R.id.entertripname);
        btncreateatrip = (RobotoTextView) v.findViewById(R.id.btncreateatrip);
        cityName = (RobotoTextView) v.findViewById(R.id.cityTitle);
        cityIcon = (RectImageView) v.findViewById(R.id.cityIcon);
        cityName.setText(_CityName + "");
        Picasso.with(getActivity()).load(_CityImage).networkPolicy(NetworkPolicy.OFFLINE).into(cityIcon, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(getActivity())
                        .load(_CityImage)
                        .error(R.drawable.background_default)
                        .into(cityIcon, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                            }
                        });
            }
        });
        String startdate = Date_utill.Dateformatchangeforall(newtrip.getStartingdate());
        String enddate = Date_utill.Dateformatchangeforall(newtrip.getEndingdate());
        start_date.setText(startdate);
        end_date.setText(enddate);
        start_date.setTag(newtrip.getStartingdate());
        end_date.setTag(newtrip.getEndingdate());
        entertripname.setText(newtrip.getTripname());

        btncreateatrip.setText(getResources().getString(R.string.title_updatetrip));
        v.findViewById(R.id.btncreateatrip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if no view has focus:
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (Date_utill.isValidDate(start_date.getTag().toString())) {
                    if (Date_utill.isValidDate(end_date.getTag().toString())) {
                        if (!entertripname.getText().toString().trim().isEmpty()) {
                            if (Date_utill.isDategreater(start_date.getTag().toString().trim(), end_date.getTag().toString().trim())) {
                                try {
                                    newtrip.getTripid();
                                    start_date.getTag().toString().trim();
                                    entertripname.getText().toString().trim();
                                    end_date.getTag().toString().trim();

                                 //   Updatetrip();

                                    new WebTripUpdateTask(v, fb_id, newtrip.getTripid(), entertripname.getText().toString().trim(), start_date.getTag().toString().trim(), end_date.getTag().toString().trim(), "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                                } catch (Exception e) {

                                }
                            } else {
                                Snackbar snack = Snackbar.make(v, "End date is lesser than start date", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null);
                                ViewGroup group = (ViewGroup) snack.getView();
                                View vv = snack.getView();
                                TextView tv = (TextView) vv.findViewById(android.support.design.R.id.snackbar_text);
                                tv.setTextColor(Color.WHITE);

                                group.setBackgroundColor(getResources().getColor(R.color.cpb_red_dark));
                                snack.show();
                            }
                        } else {
                            Snackbar snack = Snackbar.make(v, "Enter trip name", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null);
                            ViewGroup group = (ViewGroup) snack.getView();
                            View vv = snack.getView();
                            TextView tv = (TextView) vv.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setTextColor(Color.WHITE);

                            group.setBackgroundColor(getResources().getColor(R.color.cpb_red_dark));
                            snack.show();
                        }
                    } else {
                        Snackbar snack = Snackbar.make(v, "End Date is not valid", Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                        ViewGroup group = (ViewGroup) snack.getView();
                        View vv = snack.getView();
                        TextView tv = (TextView) vv.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);

                        group.setBackgroundColor(getResources().getColor(R.color.cpb_red_dark));
                        snack.show();
                    }
                } else {
                    Snackbar snack = Snackbar.make(v, "Start Date is not valid", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View vv = snack.getView();
                    TextView tv = (TextView) vv.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);

                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red_dark));
                    snack.show();
                }
            }
        });
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _startDate = true;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        UpdateTripFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(false);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(false);
                if (true) {
                    dpd.setAccentColor(Color.parseColor("#386b61"));
                }
                if (true) {
                    dpd.setTitle("Select your start date");
                }
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });
        // Show a datepicker when the dateButton is clicked
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _startDate = false;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        UpdateTripFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(false);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(false);
                if (true) {
                    dpd.setAccentColor(Color.parseColor("#386b61"));
                }
                if (true) {
                    dpd.setTitle("Select your end date");
                }
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });
        return v;
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
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);



        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener


                    // Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();

                    //getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });





    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-" + (++monthOfYear) + "-" + dayOfMonth;

        if (_startDate) {
            start_date.setTag(date);
            date = Date_utill.Dateformatchangeforall(date);
            start_date.setText(date);
        } else {
            end_date.setTag(date);
            date = Date_utill.Dateformatchangeforall(date);
            end_date.setText(date);
        }

    }

    public UpdateTripFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static final String EXTRATRIPINFO = "Viewinfo";

    public void showViewDetFragment(CreatedTripModel viewModel) {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        }
       /* Fragment fr = new Add_activities_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);*/

     //   getFragmentManager().popBackStack();
        getFragmentManager().popBackStack();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (v == backclick){

            getFragmentManager().popBackStack();

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
                    newtrip = new CreatedTripModel(c.tripid, c.tripname, c.startingdate, c.endingdate,c.cityid,c.city,c.img);
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "Your trip has been updated", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                    snack.show();

                  /*  editor.putString("sno", newtrip.cityid);
                    editor.putString("city", newtrip.city);
                    editor.putString("img", newtrip.img);
                    editor.commit();
                    showguideFragment(newtrip);*/

                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();

                   // new WebPageTaskmain(save, fb_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


                  //  getFragmentManager().popBackStack();

                /*    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            showViewDetFragment(newtrip);
                        }
                    }, 100);
                    */







                } else {
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "Please check your trip name, it should be unique", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();
                }}}}





    public void showguideFragment(CreatedTripModel ctm) {

        Sessiondata.getInstance().setTripeditupdate(2);
        Fragment fr = new Add_activities_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }




/*
private class WebPageTaskmain extends AsyncTask<Void, MyTripResponse, MyTripResponse> {
    ProgressDialog d = new ProgressDialog(_A);
    List<Categorylistmodel> items = new ArrayList<>();
    String fbid;
    View vs;

    private WebPageTaskmain(View vs, String fbid) {
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


                    d.dismiss();

                    //showguideFragment(c);

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

*/














  /*  private void Updatetrip() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,UPDATETRIP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            tsucess = userObject.getInt("status");
                            message = userObject.getString("msg");
                            tripidval = userObject.getString("tripid");
                            tripname = userObject.getString("tripname");
                            startingdate = userObject.getString("startingdate");
                            endingdate = userObject.getString("endingdate");
                            createdon = userObject.getString("createdon");
                            cityid = userObject.getString("cityid");
                            city = userObject.getString("city");
                            image = userObject.getString("img");



                            if(tsucess == 1)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Your trip has been updated", Toast.LENGTH_LONG).show();

                               getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Please check your trip name, it should be unique", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception ex) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {@Override
                    protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put(KEY_FB_ID,fb_id);
            params.put(KEY_TRIPID,newtrip.getTripid());
            params.put(KEY_TRIPNAME,entertripname.getText().toString());
            params.put(KEY_SDATE,start_date.getTag().toString().trim());
            params.put(KEY_EDATE,end_date.getTag().toString().trim());
            params.put(KEY_TRIPDES,"");



            return params;
        }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }
*/

}