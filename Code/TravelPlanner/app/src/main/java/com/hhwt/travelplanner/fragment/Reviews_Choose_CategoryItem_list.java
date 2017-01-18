package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CreateTripFragment;
import com.hhwt.travelplanner.activity.Filter_Fragment;
import com.hhwt.travelplanner.activity.Mapvinavivisible;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.adapter.Categorylistadapter;
import com.hhwt.travelplanner.adapter.Spinneradapter;
import com.hhwt.travelplanner.adapter.Spinneradaptercat;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.model.FilterList;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.interfaces.SearchInterface;
import com.hhwt.travelplanner.model.Starsfilter;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class Reviews_Choose_CategoryItem_list extends Fragment implements
        Categorylistadapter.OnItemClickListener, SearchInterface,View.OnClickListener {
    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_OK = -1;
    public static final int RESULT_FIRST_USER = 1;
    Spinner filtercate;

    ImageView backclick;

    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    Categorylistmodel C;
    String mess;
    RecyclerView categoryitems;
    Categorylistadapter adapter;
    RobotoTextView filter;
    public int sortitemposition = 0;
    public static int clickeventcheck = 0;
    public static int selectedcattype = 0;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    public static final String EXTRATRIPINFO = "Viewinfo";
    public static final String EXTRAFILTERINFO = "Viewfilter";
    public static boolean filtercheck = false, clicked = false;
    public static Categorylistmodel categorydetails;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    public static Boolean firsttime = true;
    public List<Starsfilter> Not_checkedstar(boolean val) {
        List<Starsfilter> n = new ArrayList<>();
        n.add(new Starsfilter(1, "1 star", val));
        n.add(new Starsfilter(2, "2 star", val));
        n.add(new Starsfilter(3, "3 star", val));
        n.add(new Starsfilter(4, "4 star", val));
        n.add(new Starsfilter(5, "5 star", val));
        return n;
    }
    public void showViewDetFragment(Categorylistmodel viewModel) {
        Sessiondata.getInstance().setUpdateresult(0);
        Fragment fr = new ReviewsCreateFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public void showViewDetMap(CreatedTripModel ctm) {
        Sessiondata.getInstance().setUpdateresult(0);
        Fragment fr = new Mapvinavivisible();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public void showfilterpage(CreatedTripModel ctm) {
        toolbarsearch.setVisibility(View.GONE);
        clickeventcheck = 0;
        Fragment fr = new Filter_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        if (Filter_Fragment.FL != null && (Filter_Fragment.FL.getList().size() <= 0 || Filter_Fragment.FL.getStarlist().size() <= 0)) {
            FilterList fl = new FilterList(Not_checked(Sortbytype(itemsfinal), true), Not_checkedstar(true));
            bundle.putSerializable(EXTRAFILTERINFO, fl);
        } else {
            FilterList fl = new FilterList(Filter_Fragment.FL.getList(), Filter_Fragment.FL.getStarlist());
            bundle.putSerializable(EXTRAFILTERINFO, fl);
        }
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    @Override
    public void onFavItemClick(View view, Categorylistmodel viewModel) {
        if (clickeventcheck == 0) {
            clickeventcheck = 1;
            viewModel.setCtm(newtrip);
            categorydetails = viewModel;
            toolbarsearch.setVisibility(View.GONE);
            clicked = true;
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
        if(v == backclick){
            getFragmentManager().popBackStack();
        }
    }

    @Retention(RUNTIME)
    @interface Json {
    }

    LinearLayout linearvalbottom;
    private RecyclerView.LayoutManager mLayoutManager;
    public CreatedTripModel newtrip;
    ImageView toolbarsearch, toolbaredit;
    public SearchBox search;
    ImageView searchicon;
    private Toolbar mToolbar;
    LinearLayout parentview;
    Hashtable connectFlags;
    ArrayList<String> filtercat = new ArrayList<>();
    ArrayList<String> codecat = new ArrayList<>();
    Spinneradapter spinnerAdapter;
    Spinneradaptercat pinnerAdapter;
    public static List<Starsfilter> Got_from = new ArrayList<>();
    RobotoTextView toolbarcancel, toolbarsave;
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {hasOptionsMenu();
        View v = inflater.inflate(R.layout.fragmentitemlist, container, false);






        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

      //  getActivity().mDrawerToggle.setDrawerIndicatorEnabled(false);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbaredit = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.edit);
        toolbarsave = (RobotoTextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.submit);
        toolbarcancel = (RobotoTextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.cancel);
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        toolbaredit.setVisibility(View.GONE);
        parentview = (LinearLayout) v.findViewById(R.id.parentview);
        if (Filter_Fragment.FL == null || Filter_Fragment.FL.getList().size() <= 0 && Filter_Fragment.FL.getStarlist().size() <= 0) {
            filtercheck = false;
            if (itemsfinal.size() > 0) {
                itemsfinal.clear();
            }
        } else {
            filtercheck = true;
        }
        if (filtercat.size() > 0) {
            filtercat.clear();
            codecat.clear();
        }
        clickeventcheck = 0;
        sortitemposition = 0;
        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setSearchString("");
        toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OPenSearch(itemsfinal);
                parentview.setVisibility(View.GONE);
            }
        });
        StrictMode.setThreadPolicy(policy);
        internet = new InternetAccessCheck();
        _A = getActivity();
        Bundle bundle = this.getArguments();
        //categorydetails = new Categorylistmodel(dd.sno, dd.caegoryid, dd.name, dd.description, dd.foodc, dd.prayerc, dd.openhrs, dd.closehrs, dd.website, dd.timeremaining, dd.estimatedtime, dd.pricecurrency, dd.price, dd.country, dd.city, dd.state, dd.address, dd.postalcode, dd.map, dd.phone, dd.hhwtlink, dd.activity, dd.weight, dd.district, dd.latitude, dd.longitude, dd.weight, photoitems, fooditems, "", dd.tags);

        categorydetails = new Categorylistmodel();



        newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        spinnerAdapter = new Spinneradapter();
        pinnerAdapter = new Spinneradaptercat();
        categoryitems = (RecyclerView) v.findViewById(R.id.categoryitems);
        filter = (RobotoTextView) v.findViewById(R.id.filterlist);
        filtercate = (Spinner) v.findViewById(R.id.filtercat);
        if ((Filter_Fragment.FL != null) && (Filter_Fragment.FL.getList().size() > 0 || Filter_Fragment.FL.getStarlist().size() > 0)) {
            ArrayList<String> snos = new ArrayList<>();
            List<Categorylistmodel> items = new ArrayList<>();
            List<Categorylistmodel> typesitems = new ArrayList<>();
            List<Starsfilter> getLists = new ArrayList<>();
            List<Starsfilter> getStarLists = new ArrayList<>();
            for (Starsfilter s : Filter_Fragment.FL.getStarlist()) {
                if (s.getSelectedStarcount()) {
                    getStarLists.add(s);
                }
            }
            for (Starsfilter s : getStarLists) {
                for (Categorylistmodel c : itemsfinal) {
                    String star = s.getID() + "";
                    if (c.getRating().contains(star)) {
                        if (snos.size() > 0) {
                            if (!snos.contains(c.sno)) {
                                items.add(c);
                                snos.add(c.sno);}
                        } else {
                            items.add(c);
                            snos.add(c.sno);}
                    }}}
            for (Starsfilter s : Filter_Fragment.FL.getList()) {
                if (s.getSelectedStarcount()) {
                    getLists.add(s);
                }
            }
            if (getLists.size() > 0) {
                if (items.size() > 0) {
                    snos.clear();
                    for (Starsfilter s : getLists) {
                        for (Categorylistmodel c : items) {
                            if (c.activity.equals(s.getStarcount())) {
                                if (snos.size() > 0) {
                                    if (!snos.contains(c.sno)) {
                                        typesitems.add(c);
                                        snos.add(c.sno);
                                    }

                                } else {
                                    typesitems.add(c);
                                    snos.add(c.sno);
                                }}}}
                    Categorylist(typesitems, 0);
                } else {
                    snos.clear();
                    for (Starsfilter s : getLists) {
                        for (Categorylistmodel c : itemsfinal) {
                            if (c.activity.equals(s.getStarcount())) {
                                if (snos.size() > 0) {
                                    if (!snos.contains(c.sno)) {
                                        typesitems.add(c);
                                        snos.add(c.sno);}
                                } else {
                                    typesitems.add(c);
                                    snos.add(c.sno);}
                            }}}
                    Categorylist(typesitems, 0);
                }
            } else {
                Categorylist(items, 0);
            }
            toolbarsearch.setVisibility(View.VISIBLE);

        } else {
            Categorylistmaxstart(itemsfinal, 0);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showViewDetMap(newtrip);
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfilterpage(newtrip);
            }
        });
        filtercate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (!filtercheck) {
                    selectedcattype = i;
                    if (null != itemsfinal && itemsfinal.size() > 0) {
                        itemsfinal.clear();}
                    try {
                        new WebPageTask(categoryitems, codecat.get(i)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (Exception e) {
                    }
                } else {
                    if (selectedcattype != i) {
                        filtercheck = false;
                        selectedcattype = i;
                        if (null != itemsfinal && itemsfinal.size() > 0) {
                            itemsfinal.clear();
                            Filter_Fragment.FL.getList().clear();
                        }
                        try {
                            new WebPageTask(categoryitems, codecat.get(i)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        } catch (Exception e) {
                        }}}}@Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (null != newtrip.getCategoryID()) {
            if (newtrip.getCategoryID().equalsIgnoreCase("1")) {
                filtercat.add("Food");
                filtercat.add("Prayer spaces");
                filtercat.add("Things To Do");
                codecat.add("1");
                codecat.add("2");
                codecat.add("3");
                pinnerAdapter = new Spinneradaptercat();
                pinnerAdapter.addItems(filtercat, codecat, getActivity());
                filtercate.setAdapter(pinnerAdapter);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("2")) {
                filtercat.add("Prayer spaces");
                filtercat.add("Food");
                filtercat.add("Things To Do");
                codecat.add("2");
                codecat.add("1");
                codecat.add("3");
                pinnerAdapter = new Spinneradaptercat();
                pinnerAdapter.addItems(filtercat, codecat, getActivity());
                filtercate.setAdapter(pinnerAdapter);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("3")) {
                filtercat.add("Things To Do");
                filtercat.add("Food");
                filtercat.add("Prayer spaces");
                codecat.add("3");
                codecat.add("1");
                codecat.add("2");
                pinnerAdapter = new Spinneradaptercat();
                pinnerAdapter.addItems(filtercat, codecat, getActivity());
                filtercate.setAdapter(pinnerAdapter);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("4")) {
                filtercat.add("Food");
                filtercat.add("Prayer spaces");
                filtercat.add("Things To Do");
                codecat.add("1");
                codecat.add("2");
                codecat.add("3");
                pinnerAdapter = new Spinneradaptercat();
                pinnerAdapter.addItems(filtercat, codecat, getActivity());
                filtercate.setAdapter(pinnerAdapter);
            } else if (newtrip.getCategoryID().equalsIgnoreCase("6")) {
                filtercat.add("Search Result");
                filtercat.add("Food");
                filtercat.add("Prayer spaces");
                filtercat.add("Things To Do");
                codecat.add("6");
                codecat.add("1");
                codecat.add("2");
                codecat.add("3");
                pinnerAdapter = new Spinneradaptercat();
                pinnerAdapter.addItems(filtercat, codecat, getActivity());
                filtercate.setAdapter(pinnerAdapter);
            }
        }
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
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
    public Reviews_Choose_CategoryItem_list() {
    }
    public void Changeadapter(List<Categorylistmodel> items, int value) {
        if (value == 1) {
            mLayoutManager = new LinearLayoutManager(getActivity());
            categoryitems.setLayoutManager(mLayoutManager);
            adapter = new Categorylistadapter(items);
            adapter.setOnItemClickListener(this);
            categoryitems.setAdapter(adapter);
        } else {
            mLayoutManager = new LinearLayoutManager(getActivity());
            categoryitems.setLayoutManager(mLayoutManager);
            adapter = new Categorylistadapter(items);
            adapter.setOnItemClickListener(this);
            categoryitems.setAdapter(adapter);
        }}
    public List<Starsfilter> Not_checked(ArrayList<String> Sortby, boolean val) {
        List<Starsfilter> n = new ArrayList<>();
        int i = 1;
        for (String s : Sortby) {
            n.add(new Starsfilter(i, s, val));
            i++;}
        return n;
    }
    public ArrayList<String> Sortbytype(List<Categorylistmodel> items) {
        List<String> v = new ArrayList<>();
        for (Categorylistmodel s : items) {
            v.add(s.activity);
        }
        Set<String> uniqueGas = new HashSet<String>(v);
        System.out.println(uniqueGas.size());
        ArrayList<String> sortbytype = new ArrayList<>();
        sortbytype.addAll(uniqueGas);
        return sortbytype;
    }
    public void Categorylist(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Changeadapter(items, value);
    }
    public void Categorylistdesc(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Collections.reverse(items);
        Changeadapter(items, value);
    }
    public void Categorylistmaxstart(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        Collections.reverse(items);
        Changeadapter(items, value);
    }
    public void Categorylistminstar(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });Changeadapter(items, value);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toolbarsave.setVisibility(View.GONE);
                toolbarcancel.setVisibility(View.GONE);
                toolbaredit.setVisibility(View.GONE);
                toolbarsearch.setVisibility(View.GONE);
                clickeventcheck = 0;
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
        clickeventcheck = 0;
        Filter_Fragment.FL.getList().clear();
        Filter_Fragment.FL.getStarlist().clear();
    }
    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid;
        View vs;
        private WebPageTask(View vs, String subcatid) {
            this.subcatid = subcatid;
            this.vs = vs;
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
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid,"1");
            CategorylistValuesResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;}
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
                            Sessiondata.getInstance().setFoodcatgory((ArrayList<Categorylistmodel>) items);
                            Log.d("ArjunCategorylistmodel", "" + Sessiondata.getInstance().getFoodcatgory());
                        }
                        if (firsttime) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("CHOOSE A PLACE TO REVIEW").setPositiveButton("OK", dialogClickListener)
                                    .show();
                            firsttime = false;
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
                        categoryitems.setVisibility(View.VISIBLE);
                        toolbarsearch.setVisibility(View.VISIBLE);
                        itemsfinal = items;
                        int i = sortitemposition;
                        if (i == 0) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistmaxstart(itemsfinal, 0);
                            }
                        } else if (i == 1) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistminstar(itemsfinal, 0);
                            }
                        } else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);
                            }}
                    } else {
                        toolbarsearch.setVisibility(View.INVISIBLE);
                        categoryitems.setVisibility(View.INVISIBLE);
                    }
                    d.dismiss();
                } else {
                    if (items.size() > 0) {
                        toolbarsearch.setVisibility(View.VISIBLE);
                        categoryitems.setVisibility(View.VISIBLE);
                        itemsfinal = items;
                        itemsfinal = items;
                        int i = sortitemposition;
                        if (i == 0) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistmaxstart(itemsfinal, 0);
                            }

                        } else if (i == 1) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistminstar(itemsfinal, 0);
                            }
                        } else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);
                            }}
                    } else {
                        toolbarsearch.setVisibility(View.INVISIBLE);
                        categoryitems.setVisibility(View.INVISIBLE);
                    }
                    d.dismiss();
                    Snackbar snack = Snackbar.make(vs, "There is no item to show", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();
                }}}}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    protected void closeSearch() {
        search.hideCircularly(_A);
    }
    @Override
    public void OPenSearch(List<Categorylistmodel> v) {
        search.revealFromMenuItem(R.id.search, _A);
        if (null != v && v.size() > 0) {
            for (int items = 0; items < v.size(); items++) {
                Categorylistmodel l = v.get(items);
                SearchResult option = new SearchResult(l.getName(), getResources().getDrawable(
                        R.drawable.ic_history));
                search.addSearchable(option);
            }
        }
        search.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {
            }
        });
        search.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {
            }
            public void onSearchClosed() {
                closeSearch();
            }
            public void onSearchTermChanged(String term) {
            }

            public void onSearch(String searchTerm) {
            }
            @Override
            public void onResultClick(SearchResult result) {
                enablelist(result.title);
                try {
                    search.clearResults();
                    search.clearSearchable();
                    search.setSearchString("");
                } catch (NullPointerException e) {

                }
            }
            public void onSearchCleared() {

            }

        });
    }

    @Override
    public void foodOPenSearch(List<Categorylistmodel> items) {

    }

    public void enablelist(String value) {
        List<Categorylistmodel> searchtext = new ArrayList<>();
        if (itemsfinal.size() > 0) {
            for (int items = 0; items < itemsfinal.size(); items++) {
                Categorylistmodel l = itemsfinal.get(items);
                if (l.getName().contains(value)) {
                    searchtext.add(l);
                }}
            parentview.setVisibility(View.VISIBLE);
            Categorylist(searchtext, 0);
            toolbarsearch.setVisibility(View.VISIBLE);
        }}
}
