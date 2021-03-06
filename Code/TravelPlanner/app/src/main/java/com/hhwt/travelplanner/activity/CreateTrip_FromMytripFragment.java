package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
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

import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateTrip_FromMytripFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener {
    private RobotoTextView start_date;
    private RobotoTextView end_date;
    private Boolean _startDate;
    private RobotoEditText entertripname;
    Hashtable connectFlags;
    RectImageView cityImage;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    public CreatedTripModel newtrip;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id, _CityName, _CityID, _CityImage;
    public static final String EXTRAVIEWINFO = "Viewinfo";

    RobotoTextView cityName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_trip, container, false);
        //  Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        _A = getActivity();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "0");
        _CityName = sharedpreferences.getString("city", "0");
        _CityImage = sharedpreferences.getString("img", "0");
        internet = new InternetAccessCheck();
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        start_date = (RobotoTextView) v.findViewById(R.id.start_date);
        end_date = (RobotoTextView) v.findViewById(R.id.end_date);
        entertripname = (RobotoEditText) v.findViewById(R.id.entertripname);

                cityName = (RobotoTextView) v.findViewById(R.id.cityTitle);
        cityImage= (RectImageView) v.findViewById(R.id.cityIcon);
        cityName.setText(_CityName+"");
        Picasso.with(getActivity()).load(_CityImage).networkPolicy(NetworkPolicy.OFFLINE).into(cityImage, new Callback() {
            @Override
            public void onSuccess() {

            }
            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(getActivity())
                        .load(_CityImage)
                        .error(R.drawable.background_default)
                        .into(cityImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }
                            public void onError() {

                            }});}});
        v.findViewById(R.id.btncreateatrip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                    Categorylist(start_date, fb_id, entertripname.getText().toString().trim(), start_date.getTag().toString().trim(), end_date.getTag().toString().trim(), "");
                                } catch (IOException e) {
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
                        CreateTrip_FromMytripFragment.this,
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
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _startDate = false;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        CreateTrip_FromMytripFragment.this,
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

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
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

    public CreateTrip_FromMytripFragment() {
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    public static final String EXTRATRIPINFO = "Viewinfo";
    public void showViewDetFragment(CreatedTripModel viewModel) {
        Fragment fr = new Add_activities_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public void showViewDetFragmentss(CreatedTripModel viewModel) {
      Sessiondata.getInstance().setSavetrip(0);
      Fragment fr = new Createdatetripnew();
      Bundle bundle = new Bundle();
      bundle.putSerializable(EXTRAVIEWINFO, viewModel);
      fr.setArguments(bundle);
      FragmentChangeListener fc = (FragmentChangeListener) getActivity();
      fc.replaceFragment(fr);
  }
    public void Categorylist(final View vs, String fbid, String tripname, String sdate, String edate, String tripdes) throws IOException {
        Boolean success = internet.isNetworkConnected(_A);
        if (success) {
            ProgressDialog d = new ProgressDialog(_A);
            d.setMessage("Please wait...");
            d.show();
            ApiCall a = retrofit.create(ApiCall.class);
            Call<TripRegisterResponse> call = a.TripRegister(fbid, tripname, sdate, edate, tripdes,_CityID);
            TripRegisterResponse c = call.execute().body();
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    newtrip = new CreatedTripModel(c.tripid, c.tripname, c.startingdate, c.endingdate,c.cityid,c.city,c.img);
                    d.dismiss();
                    Sessiondata.getInstance().setCtmvalue(newtrip);
                    Sessiondata.getInstance().setCtripresult(newtrip);

            Sessiondata.getInstance().setCreatedatelist(newtrip);
                    entertripname.setText("");
                    showViewDetFragmentss(newtrip);
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
                }}}}}
