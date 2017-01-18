package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.adapter.Categorylistadapter;
import com.hhwt.travelplanner.adapter.Spinneradapter;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class CategoryItem_list_NearbyelementFragment extends Fragment implements
        Categorylistadapter.OnItemClickListener,View.OnClickListener {
    Spinner filter;
    Retrofit retrofit;
    InternetAccessCheck internet;
    Hashtable connectFlags;
    public static Activity _A;
    String mess;
    RecyclerView categoryitems;

    String catgid;
    ImageView backclick;
    Categorylistadapter adapter;
    ImageView toolbarsearch;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    List<Categorylistmodel> itemsfinal = new ArrayList<>();
    RobotoTextView btndiscover;
    TextView countryapptitle;
    TextView apptitle;
    public static int clickeventcheck = 0;
    public void showViewDetFragment(Categorylistmodel viewModel) {
     /*   Sessiondata.getInstance().setUpdateresult(0);

        Sessiondata.getInstance().setFtplistval(9);*/


        /*Fragment fr = new View_itemInfo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);*/



        if(catgid.equals("3")) {


            Sessiondata.getInstance().setUpdateresult(0);
            Sessiondata.getInstance().setFtplistval(9);
            Fragment fr = new NewUiView_itemInfo_Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRAVIEWINFO, viewModel);
            fr.setArguments(bundle);
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

        else if (catgid.equals("1")){

            Sessiondata.getInstance().setUpdateresult(0);
            Sessiondata.getInstance().setFtplistval(9);
            Fragment fr = new NewUiView_itemInfo_Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRAVIEWINFO, viewModel);
            fr.setArguments(bundle);
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);

        }

        else if(catgid.equals("2")){


            Sessiondata.getInstance().setUpdateresult(0);
            Sessiondata.getInstance().setFtplistval(9);
            Fragment fr = new NewUiView_itemInfo_Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRAVIEWINFO, viewModel);
            fr.setArguments(bundle);
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }







    }
    @Override
    public void onFavItemClick(View view, Categorylistmodel viewModel) {
        if (clickeventcheck == 0) {
            clickeventcheck = 1;

            viewModel.setCtm(newtrip);
            viewModel.getCtm();
            showViewDetFragment(viewModel);
        }
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

    @Retention(RUNTIME)
    @interface Json {
    }
    private RecyclerView.LayoutManager mLayoutManager;
    public CreatedTripModel newtrip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentsearchitemlist, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.GONE);





        btndiscover = (RobotoTextView) v.findViewById(R.id.btndiscover);
        StrictMode.setThreadPolicy(policy);
        internet = new InternetAccessCheck();
        _A = getActivity();
        Bundle bundle = this.getArguments();


        if(Sessiondata.getInstance().getDataelementtripis().equals("1")){
            newtrip = (CreatedTripModel) bundle.getSerializable(NewUiView_itemInfo_Fragment.EXTRATRIPINFO);
        }

        else{
            newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        }




      //  newtrip = new CreatedTripModel();



        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Spinneradapter spinnerAdapter = new Spinneradapter();
        categoryitems = (RecyclerView) v.findViewById(R.id.categoryitems);
        filter = (Spinner) v.findViewById(R.id.filterlist);
        categoryitems.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        categoryitems.setLayoutManager(mLayoutManager);
        filter.setAdapter(spinnerAdapter);
        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                    if (null != itemsfinal && itemsfinal.size() > 0) {
                        Categorylistmaxstart(itemsfinal);
                    }

                } else if (i == 1) {
                    if (null != itemsfinal && itemsfinal.size() > 0) {
                        Categorylistminstar(itemsfinal);
                    }
                } else if (i == 2) {
                    if (null != itemsfinal && itemsfinal.size() > 0) {
                        Categorylist(itemsfinal);
                    }
                } else if (i == 3) {
                    if (null != itemsfinal && itemsfinal.size() > 0) {
                        Categorylistdesc(itemsfinal);
                    }}}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayList<String> filtertypetype = new ArrayList<>();
        ArrayList<String> code = new ArrayList<>();
        if (filtertypetype.size() > 0) {
            filtertypetype.clear();}
        if (code.size() > 0) {
            code.clear();
        }
        filtertypetype.add("Sort by max to min star");
        filtertypetype.add("Sort by min to max star");
        filtertypetype.add("Sort by A-Z");
        filtertypetype.add("Sort by Z-A");
        code.add("2");
        code.add("3");
        code.add("0");
        code.add("1");
        spinnerAdapter.addItems(filtertypetype, code, getActivity());
        filter.setAdapter(spinnerAdapter);
        if (null != newtrip.getCategoryID()) {
            if (newtrip.getCategoryID().equalsIgnoreCase("1")) {
                apptitle.setText("Nearby Food Places");
                btndiscover.setText("Food");
                btndiscover.setClickable(false);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("2")) {
                apptitle.setText("Nearby Prayer Spaces");
                btndiscover.setText("Prayers");
                btndiscover.setClickable(false);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("3")) {
                apptitle.setText("Nearby Attractions");
                btndiscover.setText("Things To Do");
                btndiscover.setClickable(false);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("4")) {
                btndiscover.setText("Neighbourhood");
                btndiscover.setClickable(false);
            } else {
                btndiscover.setText("To add");
                btndiscover.setClickable(true);
            }
        }
        clickeventcheck = 0;
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            NearByelementsModel m = newtrip.getN();

            catgid = newtrip.CategoryID;
            new WebPageTask(categoryitems, newtrip.CategoryID, m.getDistrict()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } catch (Exception e) {
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    public CategoryItem_list_NearbyelementFragment() {
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
    public void Categorylist(List<Categorylistmodel> items) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        adapter = new Categorylistadapter(items);
        adapter.setOnItemClickListener(this);
        categoryitems.setAdapter(adapter);}
    public void Categorylistdesc(List<Categorylistmodel> items) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Collections.reverse(items);
        adapter = new Categorylistadapter(items);
        adapter.setOnItemClickListener(this);
        categoryitems.setAdapter(adapter);
    }
    public void Categorylistmaxstart(List<Categorylistmodel> items) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        Collections.reverse(items);
        adapter = new Categorylistadapter(items);
        adapter.setOnItemClickListener(this);
        categoryitems.setAdapter(adapter);
    }
    public void Categorylistminstar(List<Categorylistmodel> items) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        adapter = new Categorylistadapter(items);
        adapter.setOnItemClickListener(this);
        categoryitems.setAdapter(adapter);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }


    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid;
        View vs;
        String district;
        private WebPageTask(View vs, String subcatid, String district) {
            this.subcatid = subcatid;
            this.vs = vs;
            this.district = district;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();
        }
        @Override
        protected CategorylistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<CategorylistValuesResponse> call = a.CategorylistValuesapiNearby(subcatid, district);
            CategorylistValuesResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }
        @Override
        protected void onPostExecute(CategorylistValuesResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.Status == 1) {
                    if (null != c.result && c.result.size() > 0) {
                        for (CategorylistValuesResponse.categorylistvalue dd : c.result) {
                            List<photoss> photoitems = new ArrayList<>();
                            List<foodclassification> fooditems = new ArrayList<>();
                            List<tourdetails> tourdetails = new ArrayList<>();


                            if (dd.photosres.size() > 0) {
                                for (CategorylistValuesResponse.photosr s : dd.photosres) {
                                    photoitems.add(new photoss(s.id, s.photourl, s.credits));
                                }
                            }if (dd.foodclassificationres.size() > 0) {
                                for (CategorylistValuesResponse.foodclassificationr s : dd.foodclassificationres) {
                                    fooditems.add(new foodclassification(s.foodclassificationvalues));
                                }
                            }

                            if (dd.tourdetails.size() > 0) {
                                for (CategorylistValuesResponse.tourdetails s : dd.tourdetails) {
                                    tourdetails.add(new tourdetails(s.sno, s.image, s.content, s.sellingrate, s.currency, s.id, s.sub_id, s.image_one, s.image_two, s.image_three, s.image_four, s.tour_type, s.rate, s.highlights,s.inclusionandexclusion, s.exclusion, s.overviews, s.whatcanyouexpect, s.cancellation_policy, s.location, s.addi_info, s.tour_opt_info, s.tour_opt_link, s.website, s.number, s.tour_classification_one, s.tour_classification_two, s.enquiry, s.departurepoint, s.departuredate, s.departuretime, s.duration, s.returndetails));
                                }


                            }

                            items.add(new Categorylistmodel(dd.sno, dd.caegoryid, dd.name, dd.description,dd.foodc,dd.prayerc,dd.openhrs, dd.closehrs, dd.website, dd.timeremaining, dd.estimatedtime, dd.pricecurrency, dd.price, dd.country, dd.city, dd.state, dd.address, dd.postalcode, dd.map, dd.phone, dd.hhwtlink, dd.activity, dd.weight, dd.district, dd.latitude, dd.longitude, dd.weight, photoitems, fooditems, "", dd.tags, dd.foodttags, dd.foodctags, dd.tours,tourdetails,dd.overalreviews,dd.userreviews));
                        }
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
                    if (items.size() > 0) {
                        Categorylist(items);
                        itemsfinal = items;
                    }
                    d.dismiss();}
                else {
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "There is no item to show", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();}}}}
}
