package com.hhwt.travelplanner.fragment;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.AllCItyListPojo;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.City;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.City_Guide_Fragment;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.adapter.VoteNextadapter;
import com.hhwt.travelplanner.fragment.Reviews_Choose_CategoryItem_list;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.MaterialRippleLayout;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Review_Select_City_Fragment extends Fragment implements
        VoteNextadapter.OnItemClickListener,View.OnClickListener {



ImageView backclick;

    RecyclerView save;
    Categorylistmodel C;

    public static final String ARG_PAGE = "ARG_PAGE";
    public static City_Guide_Fragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        City_Guide_Fragment fragment = new City_Guide_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<String> dates = new ArrayList<>();
    VoteNextadapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Hashtable connectFlags;

    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    //TextView labelheader;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
   // RelativeLayout voteForNext;
   // MaterialRippleLayout materialrippleeff;

    LinearLayout linearvalbottom;

    public static final String EXTRATRIPINFO = "Viewinfo";
    private CreatedTripModel ctm;

    TextView apptitle;
    public Review_Select_City_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    break;

          /*      case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getActivity().getSupportFragmentManager().popBackStack(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getId(), getActivity().getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                    }
                    break;*/
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seouldiscover, container, false);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        ctm = new CreatedTripModel();
       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        //Tapjoy.setDebugEnabled(true);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Add a review");


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);



        Reviews_Choose_CategoryItem_list.firsttime = true;


        save = (RecyclerView) v.findViewById(R.id.rvsave);
        /*voteForNext = (RelativeLayout) v.findViewById(R.id.voteForNext);
        materialrippleeff = (MaterialRippleLayout) v.findViewById(R.id.materialrippleeff);*/



        /*labelheader = (TextView) v.findViewById(R.id.labelheader);
        labelheader.setText("Choose your city");*/
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

       /* voteForNext.setVisibility(View.GONE);
        voteForNext.findViewById(R.id.voteForNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTrip_FromMytripFragment();
            }
        });

*/











       /* RobotoTextView discover = (RobotoTextView) v.findViewById(R.id.btndiscover);
        discover.setText("Select City");
        v.findViewById(R.id.btndiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctm.setCategoryID("1");
                ctm.setCategorytype("Restaurant info");
                showguideFragment(ctm);
            }
        });*/




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("CHOOSE A CITY TO REVIEW").setPositiveButton("OK", dialogClickListener)
                .show();
        // Inflate the layout for this fragment
        return v;
    }


   /* public void CreateTrip_FromMytripFragment() {
        Fragment fr = new Vote_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
*/


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            new WebPageTask(save, fb_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } catch (Exception we) {

        }
    }





    public void Votecity(List<City> trip) {
        adapter = new VoteNextadapter(trip);
        adapter.setOnItemClickListener(this);
        save.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, City c) {
        editor.putString("sno", c.getSno());
        editor.putString("city", c.getCity());
        editor.putString("img", c.getImg());
        editor.commit();
        ctm.setCategoryID("1");
        ctm.setCategorytype("Restaurant info");
        showguideFragment(ctm);
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
      //  Tapjoy.onActivityStop(getActivity());
        super.onStop();
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
                        save.setVisibility(View.VISIBLE);
                       // materialrippleeff.setVisibility(View.VISIBLE);



                        Votecity(c.getCity());
                    } else {
                        save.setVisibility(View.GONE);
                       // materialrippleeff.setVisibility(View.GONE);

                    }

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








    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void showguideFragment(CreatedTripModel ctm) {

     /*   Sessiondata.getInstance().setReviewstrip(ctm);

        Intent in = new Intent(getActivity(),Reviews_choose_newui.class);
        startActivity(in);*/


        Fragment fr = new Reviews_Choose_CategoryItem_list();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);


                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
