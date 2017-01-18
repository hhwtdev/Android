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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.CustomApplicationClass;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.AllCItyListPojo;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.City;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.activity.Tourdetails;
import com.hhwt.travelplanner.adapter.Tourcityada;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.Imageval;

import org.json.JSONArray;
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

/**
 * Created by jeyavijay on 18/07/16.
 */
public class Touractivity extends Fragment implements Tourcityada.OnItemClickListener {
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    InternetAccessCheck internet;
    String mess;
    public static Activity _A;
    Hashtable connectFlags;
    Tourcityada adapter;
    public static final String EXTRATRIPINFO = "Viewinfo";
    RecyclerView save;
    Retrofit retrofit;
    private static final String TOURDETAILS = "http://hhwt.tech/hhwt_webservice/tourcontent.php";
    ProgressDialog progressDialog;
    public static final String KEY_USERID = "id";
    int tsucess;
    TextView apptitle;
    String tosno;
    ArrayList<Tourdetails> photosdetails;
    ArrayList<Imageval> imagedetails;
    LinearLayout linearvalbottom;
    ImageView backclick;
    public Touractivity() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.touractivity, container, false);

        CustomApplicationClass.getInstance().trackScreenView("Tours");

        //Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Tours");
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.GONE);
        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.VISIBLE);


        save = (RecyclerView) v.findViewById(R.id.rvsave);
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
        save.setHasFixedSize(true);
        save.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        return  v;

    }
    public void onConnectSuccess() {

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
    public void onResume() {
        super.onResume();

        CustomApplicationClass.getInstance().trackScreenView("Tours");


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
    public void Votecity(List<City> trip) {
        adapter = new Tourcityada(trip);
        adapter.setOnItemClickListener(this);
        save.setAdapter(adapter);

    }
    public void onItemClick(View view, City c) {
        editor.putString("sno", c.getSno());
        editor.putString("city", c.getCity());
        editor.putString("img", c.getImg());
        editor.commit();
        Sessiondata.getInstance().setToursno(c.getSno());
        tosno = Sessiondata.getInstance().getToursno();
        photosdetails = new ArrayList<Tourdetails>();
        imagedetails = new ArrayList<Imageval>();
        Tourcitydetails();
        photosdetails.clear();
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
            Call<AllCItyListPojo> call = a.GetAllcitys();
            AllCItyListPojo c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();}
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
                        save.setVisibility(View.VISIBLE);
                        Votecity(c.getCity());
                    } else {
                        save.setVisibility(View.GONE);}

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
    public void showExploreFragment() {
        Fragment fr = new Tourlist();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    private void Tourcitydetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,TOURDETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            tsucess = userObject.getInt("success");
                            if(tsucess == 1) {
                                JSONArray jsonarray1 = userObject.getJSONArray("tours_content");

                                for (int i = 0; i < jsonarray1.length(); i++) {
                                    JSONObject obj2 = jsonarray1.getJSONObject(i);
                                    Tourdetails datas = new Tourdetails();
                                    datas.setSno(obj2.getString("sno"));
                                    datas.setId(obj2.getString("id"));
                                    datas.setSub_id(obj2.getString("sub_id"));
                                    datas.setImage(obj2.getString("image_one"));
                                    datas.setImagetwo(obj2.getString("image_two"));
                                    datas.setImagethree(obj2.getString("image_three"));
                                    datas.setImagefour(obj2.getString("image_four"));
                                    datas.setContent(obj2.getString("content"));
                                    datas.setCurrence(obj2.getString("currency"));
                                    datas.setSelling(obj2.getString("selling_rate"));
                                    datas.setTour_type(obj2.getString("tour_type"));
                                    datas.setRate(obj2.getString("rate"));
                                    datas.setOverview1(obj2.getString("highlights"));
                                    datas.setOverview2(obj2.getString("inclusionandexclusion"));
                                    datas.setExclusion(obj2.getString("exclusion"));
                                    datas.setOverviews(obj2.getString("overviews"));
                                    datas.setLong_overviews(obj2.getString("whatcanyouexpect"));
                                    datas.setCancellation_policy(obj2.getString("cancellation_policy"));
                                    datas.setLocation(obj2.getString("location"));
                                    datas.setAddi_info(obj2.getString("addi_info"));
                                    datas.setTour_opt_info(obj2.getString("tour_opt_info"));
                                    datas.setTour_opt_link(obj2.getString("tour_opt_link"));
                                    datas.setWebsite(obj2.getString("website"));
                                    datas.setNumber(obj2.getString("number"));
                                    datas.setEmail(obj2.getString("email"));
                                    datas.setTour_classification_one(obj2.getString("tour_classification_one"));
                                    datas.setTour_classification_two(obj2.getString("tour_classification_two"));
                                    datas.setEnquiry(obj2.getString("enquiry"));
                                    datas.setDeparturepoint(obj2.getString("departurepoint"));
                                    datas.setDeparturedate(obj2.getString("departuredate"));
                                    datas.setDeparturetime(obj2.getString("departuretime"));
                                    datas.setDuration(obj2.getString("duration"));
                                    datas.setReturndetails(obj2.getString("returndetails"));
                                    photosdetails.add(datas);
                                    Sessiondata.getInstance().setTourdetails(photosdetails);
                                }
                                progressDialog.dismiss();
                                showExploreFragment();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "No data's available", Toast.LENGTH_LONG).show();
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
            params.put(KEY_USERID,tosno);
            return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }








}
