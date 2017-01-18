package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.UserRegistrationResponse;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CreateTripFragment;
import com.hhwt.travelplanner.activity.Filter_Fragment;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewsCreateFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener,View.OnClickListener {
    private Toolbar mToolbar;
    public static final String EXTRATRIPINFO = "Viewinfo";
    private CreatedTripModel ctm;
    ImageView toolbarsearch, toolbaredit;
    RobotoTextView toolbarcancel, toolbarsave;
    public static Categorylistmodel categorydetails;
    Button btnsubmit;
    public static RobotoEditText reviewtitle, place, message, selectdate;
    RatingBar ratingBar;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    LinearLayout linearvalbottom;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    ImageView backclick;
    public String fb_id, nameis, dataelement, reviews, reviewname, placename, rating, purpose, time;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new Reviews_Choose_CategoryItem_list();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
        toolbarsave.setVisibility(View.GONE);
        toolbarcancel.setVisibility(View.GONE);
        toolbaredit.setVisibility(View.GONE);
        toolbarsearch.setVisibility(View.GONE);
       /* ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getFragmentManager().popBackStack();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getActivity().getSupportFragmentManager().popBackStack(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getId(), getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                    }

                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hasOptionsMenu();
        View v = inflater.inflate(R.layout.activity_reviews, container, false);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);





        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        _A = getActivity();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fb_id = sharedpreferences.getString("fb_id", "0");
        nameis = sharedpreferences.getString("name", "");
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        categorydetails = new Categorylistmodel();
        ctm = new CreatedTripModel();
        Bundle bundle = this.getArguments();
        categorydetails = new Categorylistmodel();
        categorydetails = (Categorylistmodel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        ctm = categorydetails.getCtm();
        dataelement = categorydetails.getSno();
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.GONE);
        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbaredit = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.edit);
        toolbarsave = (RobotoTextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.submit);
        toolbarcancel = (RobotoTextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.cancel);

        btnsubmit = (Button) v.findViewById(R.id.btnsubmit);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarsave.setVisibility(View.VISIBLE);
        toolbarcancel.setVisibility(View.VISIBLE);
        toolbaredit.setVisibility(View.GONE);
        toolbarsearch.setVisibility(View.GONE);
        place = (RobotoEditText) v.findViewById(R.id.place);
        message = (RobotoEditText) v.findViewById(R.id.message);
        reviewtitle = (RobotoEditText) v.findViewById(R.id.reviewtitle);
        selectdate = (RobotoEditText) v.findViewById(R.id.selectdate);
        place.setInputType(InputType.TYPE_NULL);
        toolbarcancel.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarsave.setVisibility(View.GONE);
                toolbarcancel.setVisibility(View.GONE);
                toolbaredit.setVisibility(View.GONE);
                toolbarsearch.setVisibility(View.GONE);
                Filter_Fragment.FL.getList().clear();
                Filter_Fragment.FL.getStarlist().clear();
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                }
             /*   ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
            }
        });
        toolbarsave.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "";
                reviewname = reviewtitle.getText().toString();
                placename = place.getText().toString();
                reviews = message.getText().toString();
                time = selectdate.getText().toString();
                rating = "" + ratingBar.getRating();
                if (reviewname == null || reviewname.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter review title");
                    return;
                }
                if (placename == null || placename.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Choose a place you visited");
                    return;
                }
                if (reviews == null || reviews.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter your review");
                    return;}
                if (time == null || time.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter visited date");
                    return;
                }
                if (rating == null || rating.isEmpty() || rating.equalsIgnoreCase("0") || rating.equalsIgnoreCase("0.0")) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Please give a star rating");
                    return;
                } else {
                    try {
                        new WebPageTask(v).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception e) {

                    }}
            }
        });
        ratingBar = (RatingBar) v.findViewById(R.id.item_ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        if (Reviews_Choose_CategoryItem_list.clicked) {
            categorydetails = Reviews_Choose_CategoryItem_list.categorydetails;
            ((RobotoEditText) v.findViewById(R.id.place)).setText(categorydetails.getName());
            place.setInputType(InputType.TYPE_CLASS_TEXT);
            place.setText(categorydetails.getName());
        }
        v.findViewById(R.id.place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ReviewsCreateFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(false);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(true);
                if (true) {
                    dpd.setAccentColor(Color.parseColor("#386b61"));
                }
                if (true) {
                    dpd.setTitle("Select your visited date");
                }
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");}
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = "";
                reviewname = reviewtitle.getText().toString();
                placename = place.getText().toString();
                reviews = message.getText().toString();
                time = selectdate.getText().toString();
                rating = "" + ratingBar.getRating();
                if (reviewname == null || reviewname.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter review title");
                    return;
                }
                if (placename == null || placename.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Choose a place you visited");
                    return;
                }
                if (reviews == null || reviews.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter your review");
                    return;
                }
                if (time == null || time.isEmpty()) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter visited date");
                    return;
                }
                if (rating == null || rating.isEmpty() || rating.equalsIgnoreCase("0") || rating.equalsIgnoreCase("0.0")) {
                    AlertUtils.SnackbarerrorAlert(getActivity(), v, "Please give a star rating");
                    return;
                } else {
                    try {
                        new WebPageTask(v).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception e) {

                    }}
            }
        });
        return v;}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toolbarsave.setVisibility(View.VISIBLE);
                toolbarcancel.setVisibility(View.VISIBLE);
                toolbaredit.setVisibility(View.GONE);
                toolbarsearch.setVisibility(View.GONE);
                Filter_Fragment.FL.getList().clear();
                Filter_Fragment.FL.getStarlist().clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        toolbarsave.setVisibility(View.GONE);
        toolbarcancel.setVisibility(View.GONE);
        toolbaredit.setVisibility(View.GONE);
        toolbarsearch.setVisibility(View.GONE);
       /* ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-" + (++monthOfYear) + "-" + dayOfMonth;
        selectdate.setTag(date);
        date = Date_utill.Dateformatchangeforall(date);
        selectdate.setText(date);
    }
    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == backclick) {
            getFragmentManager().popBackStack();
        }
    }

    private class WebPageTask extends AsyncTask<Void, UserRegistrationResponse, UserRegistrationResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        View vs;
        private WebPageTask(View vs) {
            this.vs = vs;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            d.setMessage("Please wait...");
            d.show();
        }
        @Override
        protected UserRegistrationResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Map<String, String> data = new HashMap<>();
            data.put("time", time);
            data.put("purpose", purpose);
            data.put("rating", rating);
            data.put("placename", placename);
            data.put("reviewname", reviewname);
            data.put("reviews", reviews);
            data.put("dataelement", dataelement);
            data.put("name", nameis);
            data.put("fb_id", fb_id);
            Call<UserRegistrationResponse> call = a.Insertcommentsapi(data);
            UserRegistrationResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }
        @Override
        protected void onPostExecute(UserRegistrationResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    d.dismiss();
                    AlertUtils.SnackbarsuccessAlert(getActivity(), vs, "Success! Help other Muslims by leaving more reviews.");
                    toolbarsave.setVisibility(View.GONE);
                    toolbarcancel.setVisibility(View.GONE);
                    toolbaredit.setVisibility(View.GONE);
                    toolbarsearch.setVisibility(View.GONE);
               /*     ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("WOULD YOU LIKE TO REVIEW MORE PLACES").setPositiveButton("YES", dialogClickListener)
                            .setNegativeButton("NOT NOW", dialogClickListener).show();
                } else {
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "Please check you have entered every text entry", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();}}}}
}
