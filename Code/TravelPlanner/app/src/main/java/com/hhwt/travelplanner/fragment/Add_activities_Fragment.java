package com.hhwt.travelplanner.fragment;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CategoryItem_list_Fragment;
import com.hhwt.travelplanner.activity.CategoryItem_searchlist_Fragment;
import com.hhwt.travelplanner.activity.CreateTripFragment;
import com.hhwt.travelplanner.activity.MytripCategories_Fragment;
import com.hhwt.travelplanner.activity.NavigationActivity;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.fragment.UpdateTripFragment;
import com.hhwt.travelplanner.adapter.Datelistadapter;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.CreatedTripModel;

import com.hhwt.travelplanner.widgets.RectImageView;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_activities_Fragment extends Fragment implements
        DatePickerDialog.OnDateSetListener,View.OnClickListener {
    private CreatedTripModel ctm;
    public static final String EXTRATRIPINFO = "Viewinfo";
    RobotoEditText search_t;
    private ArrayList<String> dates = new ArrayList<>();
    private RecyclerView dateslist;
    private Datelistadapter adapter;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id, _CityName, _CityID, _CityImage;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    ImageView toolbaredit, toolbarsearch;
    ImageView backclick;
    RobotoTextView cityname;
RectImageView cityImage;
LinearLayout linearvalbottom;
int j;
    TextView apptitle;


int itool;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_activity, container, false);
        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Trips");

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);




        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);




        Bundle bundle = this.getArguments();
        hasOptionsMenu();

        if (j == 1) {
            ctm = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        }
        else {
            ctm = (CreatedTripModel) bundle.getSerializable(UpdateTripFragment.EXTRATRIPINFO);
        }
        internet = new InternetAccessCheck();
        _A = getActivity();
        setHasOptionsMenu(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "0");
        _CityName = sharedpreferences.getString("city", "0");
        _CityImage = sharedpreferences.getString("img", "0");
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        search_t = (RobotoEditText) v.findViewById(R.id.search_t);
        cityname= (RobotoTextView) v.findViewById(R.id.cityname);
        cityImage= (RectImageView) v.findViewById(R.id.cityImage);


        itool = Sessiondata.getInstance().getToolvalnewui();
        if (itool == 1) {
            toolbaredit = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.edit);
            toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        }
        else {

            toolbaredit = (ImageView) ((NavigationActivity) this.getActivity()).findViewById(R.id.edit);
            toolbarsearch = (ImageView) ((NavigationActivity) this.getActivity()).findViewById(R.id.search);
        }
        toolbarsearch.setVisibility(View.GONE);
        cityname.setText(_CityName);
        toolbaredit.setVisibility(View.VISIBLE);
        toolbaredit.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaredit.setVisibility(View.GONE);
                showViewDetFragment(ctm);

            }
        });
        search_t.setHint("Search "+_CityName);
        Picasso.with(getActivity()).load(_CityImage).networkPolicy(NetworkPolicy.OFFLINE).into(cityImage, new Callback() {
            @Override
            public void onSuccess() {

            }


            public void onError() {
                Picasso.with(getActivity())
                        .load(_CityImage)
                        .error(R.drawable.background_default)
                        .into(cityImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }


                            public void onError() {

                            }
                        });
            }
        });
        v.findViewById(R.id.food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaredit.setVisibility(View.GONE);
                ctm.setCategoryID("1");
                ctm.setCategorytype("Restaurant info");
                showguideFragment(ctm);

            }
        });
        v.findViewById(R.id.tripsummary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaredit.setVisibility(View.GONE);
                showTripFragment(ctm);
            }
        });
        RobotoTextView tripname = (RobotoTextView) v.findViewById(R.id.tripname);
        tripname.setText(ctm.getTripname());
        dates = Date_utill.dates(ctm.getStartingdate(), ctm.getEndingdate());
        dateslist = (RecyclerView) v.findViewById(R.id.datelist);
        dateslist.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        dateslist.setLayoutManager(layoutManager);

        adapter = new Datelistadapter(dates);
        dateslist.setAdapter(adapter);

        v.findViewById(R.id.searchicon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaredit.setVisibility(View.GONE);
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (!search_t.getText().toString().trim().isEmpty()) {
                    ctm.setSearchtext(search_t.getText().toString().trim());
                    ctm.setCategoryID("6");
                    ctm.setCategorytype("Your search info");
                    showsearchFragment(ctm);
                    search_t.setText("");
                } else {
                    Snackbar snack = Snackbar.make(v, "Please enter some text to search", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View c = snack.getView();
                    TextView tv = (TextView) c.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();

                }
            }
        });

        v.findViewById(R.id.adddate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String output = Date_utill.Dateincrement(ctm.getEndingdate());
                    try {

                        new WebTripUpdateTask(v, fb_id, ctm.getTripid(), ctm.getTripname(), ctm.getStartingdate(), output, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception e) {

                    }
                } catch (ParseException e) {

                } catch (NullPointerException e) {

                }
            }
        });
        v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaredit.setVisibility(View.GONE);
                ctm.setCategoryID("3");
                ctm.setCategorytype("Attraction info");
                showguideFragment(ctm);

            }

        });
        v.findViewById(R.id.prayers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaredit.setVisibility(View.GONE);
                ctm.setCategoryID("2");
                ctm.setCategorytype("Prayers info");
                showguideFragment(ctm);
            }
        });

        return v;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);

    }*/


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;


    }

    public Add_activities_Fragment() {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new CategoryItem_list_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void showsearchFragment(CreatedTripModel ctm) {
        Fragment fr = new CategoryItem_searchlist_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void showTripFragment(CreatedTripModel ctm) {
        Fragment fr = new MytripCategories_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
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
                    ctm = new CreatedTripModel(c.tripid, c.tripname, c.startingdate, c.endingdate,c.cityid,c.city,c.img);
                    d.dismiss();
                    adapter.add(c.endingdate);
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

    @Override
    public void onDetach() {
        super.onDetach();
        toolbaredit.setVisibility(View.GONE);
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toolbaredit.setVisibility(View.GONE);
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    //getFragmentManager().popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showViewDetFragment(CreatedTripModel viewModel) {
        Fragment fr = new UpdateTripFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
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

                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });

    }





}
