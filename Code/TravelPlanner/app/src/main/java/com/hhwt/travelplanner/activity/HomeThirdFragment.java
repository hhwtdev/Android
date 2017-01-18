package com.hhwt.travelplanner.activity;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.fragment.Review_Select_City_Fragment;
import com.hhwt.travelplanner.fragment.Touractivity;
import com.hhwt.travelplanner.fragment.Vote_Next_Fragment;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class HomeThirdFragment extends Fragment {
    ProgressDialog progressDialog;
    private static final String GUIDLIDTVIEW = "http://www.everestitservices.com/hhwt_webservice/guidesfetch.php";
    int viewstatus;
    ArrayList<Guidview> viewdetails;
    private static final String ANALATIC = "http://hhwt.tech/hhwt_webservice/pages.php";
    Hashtable connectFlags;
    ArrayList<Guidimageurl> photosdetails;
String message,userfbidemail,pageno;

    public HomeThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }//private Tracker mTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_third, container, false);




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

        // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        //Tapjoy.setDebugEnabled(true);

        v.findViewById(R.id.review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageno = "P9";
                analytics();
                showExploreFragment();
            }
        });

        v.findViewById(R.id.tour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageno = "P10";
                analytics();
                toutshowExploreFragment();

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
      //  Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
        //Tapjoy.onActivityStop(getActivity());
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


    public void toutshowExploreFragment() {
        Fragment fr = new Touractivity();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();

        fc.replaceFragment(fr);

        /*  mTracker.setScreenName("Image~" + getString(R.string.title_explore));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }



    public void showExploreFragment() {
        Fragment fr = new Review_Select_City_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();

        fc.replaceFragment(fr);

        /*  mTracker.setScreenName("Image~" + getString(R.string.title_explore));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }


    public void showmytripFragment() {
        Fragment fr = new Vote_Next_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void showguideFragment() {
        /*Fragment fr = new Guide_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
*/
        viewdetails = new ArrayList<Guidview>();


        photosdetails = new ArrayList<Guidimageurl>();
        viewriewcomment();







      /*  mTracker.setScreenName("Image~" + getString(R.string.title_guides));
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
    }


    private void viewriewcomment() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GUIDLIDTVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("status");
                            Log.d("status", "" + viewstatus);
                            /*empprofilemsg = userObject.getString("msg");
                            Log.d("msg",""+empprofilemsg);
*/

                            JSONArray jsonarray = userObject.getJSONArray("msg");


                            for (int j = 0; j < jsonarray.length(); j++) {
                                JSONObject obj1 = jsonarray.getJSONObject(j);
                                Guidview datas1 = new Guidview();
                                datas1.setSno(obj1.getString("sno"));
                                Log.d("setSno", "" + datas1.getSno());
                                datas1.setGuides(obj1.getString("guides"));
                                Log.d("setFbid", "" + datas1.getGuides());
                                datas1.setGuidecover(obj1.getString("guidecover"));
                                Log.d("imageurl", "" + datas1.getGuidecover());
                                datas1.setDescription(obj1.getString("description"));
                                Log.d("setName", "" + datas1.getDescription());
                                datas1.setTips(obj1.getString("tips"));
                                Log.d("setComments", "" + datas1.getTips());

                                    /*JSONArray jsonarray1 = obj1.getJSONArray("result");


                                    for (int i = 0; i < jsonarray1.length(); i++) {
                                        JSONObject obj2 = jsonarray1.getJSONObject(i);
                                        Guidimageurl datas = new Guidimageurl();
                                        datas.setDataelementid(obj2.getString("dataelementid"));
                                        Log.d("setId", "" + datas.getDataelementid());
                                        datas.setName(obj2.getString("name"));
                                        Log.d("setPhotourl", "" + datas.getName());
                                        datas.setActivity(obj2.getString("activity"));
                                        Log.d("setCredits", "" + datas.getActivity());
                                        datas.setPhoto(obj2.getString("photo"));
                                        Log.d("setCrediturl", "" + datas.getPhoto());
                                        Sessiondata.getInstance().setGuidimgurl(datas.getPhoto());
                                        photosdetails.add(datas);
                                        Sessiondata.getInstance().setGuidimagedetails(photosdetails);
                                        Log.d("Photourl", "" + Sessiondata.getInstance().getGuidimagedetails());
                                    }
*/


                                viewdetails.add(datas1);
                                Sessiondata.getInstance().setGuidlistdetails(viewdetails);
                                Log.d("viewdetaillist", "" + Sessiondata.getInstance().getGuidlistdetails());

                            }


                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                            progressDialog.dismiss();


                            Fragment fr = new City_Guide_Fragment();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                            // Toast.makeText(getApplicationContext(), empprofilemsg, Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                //params.put("","");
                Log.d("paramsusername", "" + params);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
//initialize the progress dialog and show it
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
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
                           // Toast.makeText(getActivity(), "page" + pageno, Toast.LENGTH_SHORT).show();
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
                params.put("user_id", userfbidemail);
                params.put("page_id", pageno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
