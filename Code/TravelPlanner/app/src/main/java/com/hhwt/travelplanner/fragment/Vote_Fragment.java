package com.hhwt.travelplanner.fragment;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.hhwt.travelplanner.Retrofit.VoteCityPojo;
import com.hhwt.travelplanner.Retrofit.Votecitylist;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CreateTrip_FromMytripFragment;
import com.hhwt.travelplanner.adapter.VotePageadapter;
import com.hhwt.travelplanner.fragment.Add_activities_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Vote_Fragment extends Fragment implements
        VotePageadapter.OnItemClickListener {
    private CreatedTripModel ctm;
    public static final String EXTRATRIPINFO = "Viewinfo";
    RecyclerView save;

    Categorylistmodel C;
    ArrayList<String> dates = new ArrayList<>();
    VotePageadapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Hashtable connectFlags;
String valsno;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    View vr;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    Votecitylist votecityList;
    private static final String TOVOTE = "http://hhwt.tech/hhwt_webservice/tovote.php";

    int viewstatus;
    String emsg;

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        new VoteTask(save, fb_id, votecityList).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        valsno =  votecityList.getSno();
                        viewriewcomment();

                    } catch (Exception we) {

                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vote, container, false);
        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        save = (RecyclerView) v.findViewById(R.id.rvsave);
       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        //  Tapjoy.setDebugEnabled(true);
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
        save.setHasFixedSize(true);
        // use a linear layout manager
        // mLayoutManager = new LinearLayoutManager(getActivity());
        save.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        return v;
    }

    public void CreateTrip_FromMytripFragment() {
        Fragment fr = new CreateTrip_FromMytripFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public Vote_Fragment() {
        // Required empty public constructor

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
       // Tapjoy.onActivityStop(getActivity());
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
        Fragment fr = new Add_activities_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public void Votecity(List<Votecitylist> trip) {
        adapter = new VotePageadapter(trip);
        adapter.setOnItemClickListener(this);
        save.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, Votecitylist viewModel) {
        votecityList = viewModel;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Vote to bring this city to the app?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    private class WebPageTask extends AsyncTask<Void, VoteCityPojo, VoteCityPojo> {

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
        protected VoteCityPojo doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<VoteCityPojo> call = a.GetAllcityforvote();

            // Fetch and print a list of the contributors to the library.
            VoteCityPojo c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(VoteCityPojo c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.getStatus() == 1) {
                    if (null != c.getVotecitylist() && c.getVotecitylist().size() > 0) {
                        save.setVisibility(View.VISIBLE);
                        Votecity(c.getVotecitylist());
                    } else {
                        save.setVisibility(View.GONE);

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

    private class VoteTask extends AsyncTask<Void, VoteCityPojo, VoteCityPojo> {

        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid;
        View vs;
        Votecitylist votecityList;

        private VoteTask(View vs, String fbid, Votecitylist votecityList) {
            this.fbid = fbid;
            this.vs = vs;
            this.votecityList = votecityList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();
        }

        @Override
        protected VoteCityPojo doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
// asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Map<String, String> data = new HashMap<>();
            data.put("Sno", votecityList.getSno());

            data.put("Userid", fb_id);
            Call<VoteCityPojo> call = a.GetAllcityforvote();

            // Fetch and print a list of the contributors to the library.
            VoteCityPojo c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(VoteCityPojo c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.getStatus() == 1) {
                    if (null != c.getVotecitylist() && c.getVotecitylist().size() > 0) {
                        save.setVisibility(View.VISIBLE);
                        Votecity(c.getVotecitylist());
                    } else {
                        save.setVisibility(View.GONE);

                    }
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
                   /* Snackbar snack = Snackbar.make(vs, c.getMsg(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
*/




                   // new Alreadyvote(save, fb_id, votecityList).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                /*    Snackbar snack = Snackbar.make(vs, "Thanks for your vote!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);




                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                    snack.show();*/
                    d.dismiss();
                } else {
                    d.dismiss();
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
                    Snackbar snack = Snackbar.make(vs, c.getMsg(), Snackbar.LENGTH_LONG)
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





    private void viewriewcomment() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST,TOVOTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("Status");
                            Log.d("status", "" + viewstatus);
                           emsg = userObject.getString("msg");
                            Log.d("msg",""+emsg);



                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {


                            if(emsg.equals("Successfully voted")){

                                Toast.makeText(getActivity(),"Thanks for your vote!",Toast.LENGTH_LONG).show();



                                /*Snackbar snack = Snackbar.make(vr, "Thanks for your vote!", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null);
                                ViewGroup group = (ViewGroup) snack.getView();
                                View v = snack.getView();
                                TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                                tv.setTextColor(Color.WHITE);
                                group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                                snack.show();*/
                            }
                            else if(emsg.equals("Already Voted!"))
                            {

                                Toast.makeText(getActivity(),"Already Voted",Toast.LENGTH_LONG).show();

                                /*Snackbar snack = Snackbar.make(vr,"Already Voted", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null);
                                ViewGroup group = (ViewGroup) snack.getView();
                                View v = snack.getView();
                                TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                                tv.setTextColor(Color.WHITE);
                                group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                                snack.show();*/
                            }




                        } else if (viewstatus == 0) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                params.put("cityid",valsno);
                params.put("userid",fb_id);
                Log.d("paramsusername", "" + params);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
//initialize the progress dialog and show it

    }














   /* private class Alreadyvote extends AsyncTask<Void, VoteCityPojo, VoteCityPojo> {

        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid;
        View vs;
        Votecitylist votecityList;

        private Alreadyvote(View vs, String fbid, Votecitylist votecityList) {
            this.fbid = fbid;
            this.vs = vs;
            this.votecityList = votecityList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();
        }

        @Override
        protected VoteCityPojo doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
// asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Map<String, String> data = new HashMap<>();
            data.put("cityid", votecityList.getSno());

            data.put("userid", fb_id);
            Call<VoteCityPojoss> call = a.votealreadyrvote(votecityList.getSno(),fb_id);

            // Fetch and print a list of the contributors to the library.
            VoteCityPojo c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        protected void onPostExecute(VoteCityPojoss c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.getStatus() == 1) {







                    if (null != c.getVotecitylist() && c.getVotecitylist().size() > 0) {
                        save.setVisibility(View.VISIBLE);
                        Votecity(c.getVotecitylist());
                    } else {
                        save.setVisibility(View.GONE);

                    }
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
                   *//* Snackbar snack = Snackbar.make(vs, c.getMsg(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
*//*




                    if(c.getMsg().equals("Already Voted!"))

                    {

                        Snackbar snack = Snackbar.make(vs, "Already Voted", Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                        ViewGroup group = (ViewGroup) snack.getView();
                        View v = snack.getView();
                        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);
                        group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                        snack.show();
                    }

                    else if (c.getMsg().equals("Successfully voted")){


                        Snackbar snack = Snackbar.make(vs, "Thanks for your vote!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                        ViewGroup group = (ViewGroup) snack.getView();
                        View v = snack.getView();
                        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);
                        group.setBackgroundColor(getResources().getColor(R.color.cpb_green_dark));
                        snack.show();

                    }







                    d.dismiss();
                } else {
                    d.dismiss();
                    //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
                    //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
                    Snackbar snack = Snackbar.make(vs, c.getMsg(), Snackbar.LENGTH_LONG)
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
    }*/












}
