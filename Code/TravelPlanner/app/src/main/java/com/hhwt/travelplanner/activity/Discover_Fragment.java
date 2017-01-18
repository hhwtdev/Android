package com.hhwt.travelplanner.activity;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import com.hhwt.travelplanner.adapter.CityViewPager;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.MaterialDesignIconsTextView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*import butterknife.BindView;
import butterknife.ButterKnife;*/


public class Discover_Fragment extends Fragment {
    Hashtable connectFlags;
   // @BindView(R.id.pager)
    ViewPager pager;
   // @BindView(R.id.activity_wizard_possition)
    MaterialDesignIconsTextView activityWizardPossition;
  //  @BindView(R.id.mytrip)
    RelativeLayout mytrip;
    //@BindView(R.id.btndiscover)
    RobotoTextView btndiscover;
   // @BindView(R.id.guide)
    RelativeLayout guide;


    private CityViewPager mPagerAdapter;
    Boolean seoul = true;
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;

    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;

    public Discover_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            new WebPageTask(pager, fb_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } catch (Exception we) {

        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //  Tapjoy.setDebugEnabled(true);
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


        View v = inflater.inflate(R.layout.fragment_seouldiscover, container, false);
        // Inflate the layout for this fragment
       // ButterKnife.bind(this, v);
        //Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        //Tapjoy.setDebugEnabled(true);

        // Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        v.findViewById(R.id.btndiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seoul) {
                    showExploreFragment();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("We are adding places for this city please wait for few days").setPositiveButton("OK", dialogClickListener)
                            .show();

                }

            }
        });
       pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                setNavigator();
            }
        });
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


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showExploreFragment() {
        Fragment fr = new Explore_activities_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);

    }

    public void showcreatetripFragment() {
        Fragment fr = new MessagesFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void showmytripFragment() {
        Fragment fr = new MessagesFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void showguideFragment() {
        Fragment fr = new MessagesFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public void setNavigator() {
        String navigation = "";
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            if (i == pager.getCurrentItem()) {
                navigation += _A.getString(R.string.material_icon_point_full)
                        + "  ";
            } else {
                navigation += _A.getString(R.string.material_icon_point_empty)
                        + "  ";
            }
        }
        if (pager.getCurrentItem() == 0) {
            seoul = true;
        } else {
            seoul = false;
        }
        activityWizardPossition.setText(navigation);
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
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<AllCItyListPojo> call = a.GetAllcity();

            // Fetch and print a list of the contributors to the library.
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
                        Votecity(c.getCity());
                    } else {


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

    public void Votecity(List<City> trip) {
        mPagerAdapter = new CityViewPager(_A, trip);
        //   mPagerAdapter.setOnItemClickListener(this);
        pager.setAdapter(mPagerAdapter);
        setNavigator();
    }


    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:

                    break;

            }
        }
    };
}
