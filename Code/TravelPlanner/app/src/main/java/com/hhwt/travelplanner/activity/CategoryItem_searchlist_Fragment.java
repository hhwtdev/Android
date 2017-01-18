package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hhwt.travelplanner.adapter.Categorylistadapter;
import com.hhwt.travelplanner.adapter.Spinneradaptercat;
import com.hhwt.travelplanner.fragment.View_itemInfo_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.model.FilterList;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.interfaces.SearchInterface;
import com.hhwt.travelplanner.model.Starsfilter;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
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

public class CategoryItem_searchlist_Fragment extends Fragment implements
        Categorylistadapter.OnItemClickListener, SearchInterface {
    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_OK = -1;
    public static final int RESULT_FIRST_USER = 1;
    Hashtable connectFlags;
    ArrayList<String> filtercat = new ArrayList<>();
    ArrayList<String> codecat = new ArrayList<>();
    Spinner filtercate;
    RobotoTextView filter;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    RecyclerView categoryitems;
    Categorylistadapter adapter;
    ImageView toolbarsearch;
    public SearchBox search;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    public static final String EXTRATRIPINFO = "Viewinfo";
    public static final String EXTRAFILTERINFO = "Viewfilter";
    public static boolean filtercheck = false;
    public static int selectedcattype = 0;
    List<Categorylistmodel> itemsfinal = new ArrayList<>();
    public int sortitemposition = 0;
    public static int clickeventcheck = 0;
    public void showViewDetFragment(Categorylistmodel viewModel) {
        Fragment fr = new View_itemInfo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public List<Starsfilter> Not_checkedstar(boolean val) {
        List<Starsfilter> n = new ArrayList<>();
        n.add(new Starsfilter(1, "1 star", val));
        n.add(new Starsfilter(2, "2 star", val));
        n.add(new Starsfilter(3, "3 star", val));
        n.add(new Starsfilter(4, "4 star", val));
        n.add(new Starsfilter(5, "5 star", val));
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
    public List<Starsfilter> Not_checked(ArrayList<String> Sortby, boolean val) {
        List<Starsfilter> n = new ArrayList<>();
        int i = 1;
        for (String s : Sortby) {
            n.add(new Starsfilter(i, s, val));
            i++;
        }
        return n;
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
            showViewDetFragment(viewModel);
            toolbarsearch.setVisibility(View.GONE);}}
    @Retention(RUNTIME)
    @interface Json {
    }
    LinearLayout parentview;
    private RecyclerView.LayoutManager mLayoutManager;
    public CreatedTripModel newtrip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> filtertypetype = new ArrayList<>();
        ArrayList<String> code = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragmentitemlist, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        sortitemposition = 0;
        StrictMode.setThreadPolicy(policy);
        internet = new InternetAccessCheck();
        _A = getActivity();
        Bundle bundle = this.getArguments();
        newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Spinneradaptercat pinnerAdapter = new Spinneradaptercat();
        categoryitems = (RecyclerView) v.findViewById(R.id.categoryitems);
        filtercate = (Spinner) v.findViewById(R.id.filtercat);
        filter = (RobotoTextView) v.findViewById(R.id.filterlist);
        parentview = (LinearLayout) v.findViewById(R.id.parentview);
        if (Filter_Fragment.FL == null || Filter_Fragment.FL.getList().size() <= 0 && Filter_Fragment.FL.getStarlist().size() <= 0) {
            filtercheck = false;
            if (itemsfinal.size() > 0) {
                itemsfinal.clear();}
        } else {
            filtercheck = true;
        }
        if (filtercat.size() > 0) {
            filtercat.clear();
            codecat.clear();
        }
        search = (SearchBox) ((NavigationActivity) this.getActivity()).findViewById(R.id.searchbox);
        search.setSearchString("");
        toolbarsearch = (ImageView) ((NavigationActivity) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OPenSearch(itemsfinal);
                parentview.setVisibility(View.GONE);}
        });
        categoryitems.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        categoryitems.setLayoutManager(mLayoutManager);
        if ((Filter_Fragment.FL != null) && (Filter_Fragment.FL.getList().size() > 0 || Filter_Fragment.FL.getStarlist().size() > 0)) {
            ArrayList<String> snos = new ArrayList<>();
            List<Categorylistmodel> items = new ArrayList<>();
            List<Categorylistmodel> typesitems = new ArrayList<>();
            List<Starsfilter> getLists = new ArrayList<>();
            List<Starsfilter> getStarLists = new ArrayList<>();
            for (Starsfilter s : Filter_Fragment.FL.getStarlist()) {
                if (s.getSelectedStarcount()) {
                    getStarLists.add(s);}}
            for (Starsfilter s : getStarLists) {
                for (Categorylistmodel c : itemsfinal) {
                    String star = s.getID() + "";
                    if (c.getRating().contains(star)) {
                        if (snos.size() > 0) {
                            if (!snos.contains(c.sno)) {
                                items.add(c);
                                snos.add(c.sno);}} else {
                            items.add(c);
                            snos.add(c.sno);}}}}
            for (Starsfilter s : Filter_Fragment.FL.getList()) {
                if (s.getSelectedStarcount()) {
                    getLists.add(s);}}
            if(getLists.size()>0) {
                if(items.size()>0) {
                    snos.clear();
                    for (Starsfilter s : getLists) {
                        for (Categorylistmodel c : items) {
                            if (c.activity.equals(s.getStarcount())) {
                                if (snos.size() > 0) {
                                    if (!snos.contains(c.sno)) {
                                        typesitems.add(c);
                                        snos.add(c.sno);}
                                } else {
                                    typesitems.add(c);
                                    snos.add(c.sno);}}}}Categorylist(typesitems);}else{snos.clear();
                    for (Starsfilter s : getLists) {
                        for (Categorylistmodel c : itemsfinal) {
                            if (c.activity.equals(s.getStarcount())) {
                                if (snos.size() > 0) {
                                    if (!snos.contains(c.sno)) {
                                        typesitems.add(c);
                                        snos.add(c.sno);}
                                } else {
                                    typesitems.add(c);
                                    snos.add(c.sno);
                                }}}}
                    Categorylist(typesitems);}}
            else{
                Categorylist(items);
            }
            toolbarsearch.setVisibility(View.VISIBLE);
        } else {
            Categorylistmaxstart(itemsfinal);
        }
        v.findViewById(R.id.filterlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfilterpage(newtrip);
            }
        });
        filtercate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                    if (!filtercheck) {
                        selectedcattype = i;
                        if (null != itemsfinal && itemsfinal.size() > 0) {
                            itemsfinal.clear();
                        }
                        try {
                            new WebPageTask(categoryitems, newtrip.getSearchtext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
                                new WebPageTask(categoryitems, newtrip.getSearchtext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            } catch (Exception e) {

                            }
                        }
                    }
                } else {
                    if (!filtercheck) {
                        selectedcattype = i;
                        if (null != itemsfinal && itemsfinal.size() > 0) {
                            itemsfinal.clear();
                        }
                        try {
                            new WebPagedefaultTask(categoryitems, codecat.get(i)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
                                new WebPagedefaultTask(categoryitems, codecat.get(i)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            } catch (Exception e) {

                            }
                        }
                    }}}
            @Override
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
                filtercat.add("Neighbourhood");
                filtercat.add("Food");
                filtercat.add("Prayer spaces");
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
            } else {
            }
        }
        clickeventcheck = 0;
        return v;
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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public void onResume() {
        super.onResume();
    }
    public CategoryItem_searchlist_Fragment() {
    }
    public void Categorylist(List<Categorylistmodel> items) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        adapter = new Categorylistadapter(items);
        adapter.setOnItemClickListener(this);
        categoryitems.setAdapter(adapter);
    }
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
            this.vs = vs;}
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();}
        @Override
        protected CategorylistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<CategorylistValuesResponse> call = a.searchCategorylistValuesapi(subcatid);
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
                            if (dd.photosres.size() > 0) {
                                for (CategorylistValuesResponse.photosr s : dd.photosres) {
                                    photoitems.add(new photoss(s.id, s.photourl, s.credits));
                                }
                            }
                            if (dd.foodclassificationres.size() > 0) {
                                for (CategorylistValuesResponse.foodclassificationr s : dd.foodclassificationres) {
                                    fooditems.add(new foodclassification(s.foodclassificationvalues));
                                }
                            }
                            items.add(new Categorylistmodel());
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
                        snack.show();}
                    if (items.size() > 0) {
                        categoryitems.setVisibility(View.VISIBLE);
                        itemsfinal = items;
                        Categorylist(items);
                        toolbarsearch.setVisibility(View.VISIBLE);
                    } else {
                        toolbarsearch.setVisibility(View.INVISIBLE);
                        categoryitems.setVisibility(View.INVISIBLE);
                    }
                    d.dismiss();
                } else {
                    if (items.size() > 0) {
                        categoryitems.setVisibility(View.VISIBLE);
                        itemsfinal = items;
                        Categorylist(items);
                        toolbarsearch.setVisibility(View.VISIBLE);
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
    private class WebPagedefaultTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid;
        View vs;
        private WebPagedefaultTask(View vs, String subcatid) {
            this.subcatid = subcatid;
            this.vs = vs;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Please wait...");
            d.show();}
        @Override protected CategorylistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid,"1");
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
                            if (dd.photosres.size() > 0) {
                                for (CategorylistValuesResponse.photosr s : dd.photosres) {
                                    photoitems.add(new photoss(s.id, s.photourl, s.credits));}
                            }
                            if (dd.foodclassificationres.size() > 0) {
                                for (CategorylistValuesResponse.foodclassificationr s : dd.foodclassificationres) {
                                    fooditems.add(new foodclassification(s.foodclassificationvalues));
                                }
                            }
                            items.add(new Categorylistmodel());}} else {
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
                        itemsfinal = items;
                        int i = sortitemposition;
                        Categorylist(itemsfinal);
                        toolbarsearch.setVisibility(View.VISIBLE);
                    } else {
                        categoryitems.setVisibility(View.INVISIBLE);
                    }
                    d.dismiss();
                } else {
                    if (items.size() > 0) {
                        categoryitems.setVisibility(View.VISIBLE);
                        itemsfinal = items;
                        int i = sortitemposition;
                        Categorylist(itemsfinal);
                        toolbarsearch.setVisibility(View.VISIBLE);
                    } else {
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
                    snack.show();}}}}

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
            }}
        search.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {
            }
        });
        search.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {
            }
            @Override
            public void onSearchClosed() {
                closeSearch();
            }
            @Override
            public void onSearchTermChanged(String term) {
            }
            @Override
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
            @Override
            public void onSearchCleared() {
            }
        });}

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
                }
            }
            parentview.setVisibility(View.VISIBLE);
            Categorylist(searchtext);
            toolbarsearch.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toolbarsearch.setVisibility(View.GONE);
                clickeventcheck = 0;
                Filter_Fragment.FL.getList().clear();
                Filter_Fragment.FL.getStarlist().clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}}