package com.hhwt.travelplanner.activity;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.fragment.Vote_Next_Fragment;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class HomeFIrstFragment extends Fragment {
    ProgressDialog progressDialog;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;
    public static HomeFIrstFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        HomeFIrstFragment fragments = new HomeFIrstFragment();
        fragments.setArguments(args);
        return fragments;
    }

    int viewstatus;
    String message;
    private static final String ANALATIC = "http://hhwt.tech/hhwt_webservice/pages.php";


    ArrayList<Guidview> viewdetails;
    Activity _A;
    Hashtable connectFlags;

    String userfbidemail;

    String pageno;
    ArrayList<Guidimageurl> photosdetails;


    public HomeFIrstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    private Tracker mTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_first, container, false);
        _A = getActivity();

        SharedPreferences preferencprofileway = getActivity().getSharedPreferences("newfb", getActivity().MODE_PRIVATE);
        String proviewpic = preferencprofileway.getString("facebookway", null);
        Log.d("profilename", "" + proviewpic);

        if(proviewpic.equals("2")){

            SharedPreferences preferencessfbid = getActivity().getSharedPreferences("newprofilefbid", getActivity().MODE_PRIVATE);
            String profilefbid = preferencessfbid.getString("facebookid", null);
            Log.d("profilename", "" + profilefbid);
            userfbidemail = profilefbid;
            Sessiondata.getInstance().setLoginfbid(profilefbid);
            //Toast.makeText(getActivity(), profilefbid, Toast.LENGTH_SHORT).show();


        }
        else if (proviewpic.equals("3")){

            SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
            String profileemail = preferencessemail.getString("registeremail", null);
            Log.d("profileemail", "" + profileemail);
            userfbidemail = profileemail;

            Sessiondata.getInstance().setLoginfbid(profileemail);
            //Toast.makeText(getActivity(), profileemail, Toast.LENGTH_SHORT).show();
        }

        else{

            SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
            String profileemail = preferencessemail.getString("registeremail", null);
            Log.d("profileemail", "" + profileemail);
            userfbidemail = profileemail;
            Sessiondata.getInstance().setLoginfbid(profileemail);

            //Toast.makeText(getActivity(),profileemail,Toast.LENGTH_SHORT).show();
        }
        pageno = "D";
        analytics();

        v.findViewById(R.id.explore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageno = "P1";
                analytics();
                showExploreFragment();
            }
        });
        v.findViewById(R.id.createtrip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageno = "P2";
                analytics();
                showcreatetripFragment();
            }
        });

        v.findViewById(R.id.mytrip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageno = "P3";
                analytics();
                showmytripFragment();
            }
        });
        v.findViewById(R.id.guide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageno = "P4";
                analytics();
                showGuideFragment();
            }
        });
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
       // Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
      //  Tapjoy.onActivityStop(getActivity());
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
        Fragment fr = new Vote_Next_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();

        fc.replaceFragment(fr);

        /*  mTracker.setScreenName("Image~" + getString(R.string.title_explore));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }

    public void showcreatetripFragment() {
        Fragment fr = new City_CreateTrip_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
       /* mTracker.setScreenName("Image~" + getString(R.string.title_createtrip));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }

    public void showmytripFragment() {
        Fragment fr = new MyTrip_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
  /*      mTracker.setScreenName("Image~" + getString(R.string.title_mytrip));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }
    public void showGuideFragment() {
        Fragment fr = new City_Guide_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
  /*      mTracker.setScreenName("Image~" + getString(R.string.title_mytrip));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }











    private void analytics() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ANALATIC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("status");
                            message = userObject.getString("msg");
                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                            //Toast.makeText(getActivity(),"page"+pageno,Toast.LENGTH_SHORT).show();
                        } else if (viewstatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                params.put("user_id",userfbidemail);
                params.put("page_id",pageno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}
