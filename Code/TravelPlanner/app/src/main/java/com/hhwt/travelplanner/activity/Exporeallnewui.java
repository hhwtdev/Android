package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;

import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.Categorylistneibourhood;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.interfaces.SearchInterface;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 22/09/16.
 */
public class Exporeallnewui extends Fragment implements View.OnClickListener, SearchInterface {
    private TabLayout tabLayout;
    public static final int RESULT_OK = -1;
    private ViewPager viewPager;
    ImageView backclick;
    LinearLayout linearvalbottom;
    TextView daytourcreatetrip;
    SharedPreferences sharedpreferences;
    TextView apptitle;
    RelativeLayout parentview;
    Retrofit retrofit;
    public static final String EXTRATRIPINFO = "Viewinfo";
    int i;
    ImageView toolbarsearch;
    String cidval,foodcidval;
    ImageView filter;
int j;
    String tosno;
    String allneighbourhoods;
    String eunpeong,dobong,nowon,seongbuk,jongno,seodaemun,mapo,yongsan,jung,seongdong,gangnam,songpa,yeongdeungpo,dongjak,gwangjin,seocho,dongdaemun,guro,geumcheon,gangbuk,cheoin,giheung,kowloon,wanchai,central,tuenmun,islands,southern,eastern,kwuntong,shamshuipo,tsuenwan;
    SearchBox search;
    View save;
    String mess;
    private  static final String CITYNEARBOURHOOD = "http://hhwt.tech/hhwt_webservice/get_district.php";
    String searchval,thingssearchval;

    ArrayList<Categorylistneibourhood> Categorylistparam;
    ArrayList<Categorylistneibourhood> products = new ArrayList<Categorylistneibourhood>();

    String neifhbouvalue;

    TextView countryapptitle;
    String _CityName, Counytryname;
    SharedPreferences.Editor editor;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    public int sortitemposition = 0;
    private CreatedTripModel ctm;
    public static Activity _A;
String useridval;
    int k;
    View foodsave;
String message;
    String allneigval;

    ArrayList<String> boxname;


    int staus;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.newuiexplore, container, false);

        _A = getActivity();


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));






        useridval = Sessiondata.getInstance().getReviewfbemailid();

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);
        daytourcreatetrip = (TextView) v.findViewById(R.id.daytour);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);


        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);
        filter.setOnClickListener(this);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.VISIBLE);

        toolbarsearch.setOnClickListener(this);

        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);

        parentview = (RelativeLayout) v.findViewById(R.id.parentview);


        _CityName = Sessiondata.getInstance().getCityname();
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        Counytryname = Sessiondata.getInstance().getCountryname();

        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.VISIBLE);
        countryapptitle.setText(Counytryname);
        apptitle.setText(_CityName);
        backclick.setOnClickListener(this);
        daytourcreatetrip.setOnClickListener(this);


        viewPager = (ViewPager) v.findViewById(R.id.viewpagersexplore);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabsexplore);
        tabLayout.setupWithViewPager(viewPager);


        // setupTabIcons();
        setupTabIconss();


        k =  viewPager.getCurrentItem();

if(k == 0){
    filter.setVisibility(View.VISIBLE);
  //  j = 0;


    toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Sessiondata.getInstance().setExplorenewfullpage(1);
            i = Sessiondata.getInstance().getExplorenewfullpage();
            useridval = Sessiondata.getInstance().getReviewfbemailid();
            tosno = Sessiondata.getInstance().getToursno();

            if (i == 1) {

                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                search.setVisibility(View.VISIBLE);
                Sessiondata.getInstance().setExplorenewfullpage(1);
                tosno = Sessiondata.getInstance().getToursno();
                cidval = "3";
                OPenSearch(itemsfinal);
                parentview.setVisibility(View.VISIBLE);


            } else if (i == 2) {

                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                search.setVisibility(View.VISIBLE);
                Sessiondata.getInstance().setExplorenewfullpage(2);
                //itemsfinal = Sessiondata.getInstance().getFoodcatgory();
                tosno = Sessiondata.getInstance().getToursno();
                foodcidval = "1";
                foodOPenSearch(itemsfinal);
                parentview.setVisibility(View.VISIBLE);


            } else if (i == 3) {

                Sessiondata.getInstance().setExplorenewfullpage(3);

                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                search.setVisibility(View.GONE);
            }


        }
    });





}
  else if (k == 1){

        filter.setVisibility(View.VISIBLE);
   // j = 1;

    toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Sessiondata.getInstance().setExplorenewfullpage(2);
            i = Sessiondata.getInstance().getExplorenewfullpage();
            useridval = Sessiondata.getInstance().getReviewfbemailid();
            tosno = Sessiondata.getInstance().getToursno();



            if (i == 1) {

                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                search.setVisibility(View.VISIBLE);
                Sessiondata.getInstance().setExplorenewfullpage(1);
                tosno = Sessiondata.getInstance().getToursno();
                cidval = "3";
                OPenSearch(itemsfinal);
                parentview.setVisibility(View.VISIBLE);


            } else if (i == 2) {

                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                search.setVisibility(View.VISIBLE);
                Sessiondata.getInstance().setExplorenewfullpage(2);
                //itemsfinal = Sessiondata.getInstance().getFoodcatgory();
                tosno = Sessiondata.getInstance().getToursno();
                foodcidval = "1";
                foodOPenSearch(itemsfinal);
                parentview.setVisibility(View.VISIBLE);


            } else if (i == 3) {

                Sessiondata.getInstance().setExplorenewfullpage(3);

                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                search.setVisibility(View.GONE);
            }


        }
    });










}
        else {
    filter.setVisibility(View.GONE);

}


        i = Sessiondata.getInstance().getExplorenewfullpage();


        if (i == 1) {

            TabLayout.Tab tab = tabLayout.getTabAt(0);
            //tab.setText("Things To Do");

            tab.select();

            //tabLayout.setTabTextColors(Color.BLACK,Color.WHITE);
            // tabLayout.setTabTextColors(getResources().getColorStateList(R.color.white));
            //   tabLayout.setSelectedTabIndicatorColor(Color.WHITE);


            tabLayout.setTabTextColors(ContextCompat.getColorStateList(getActivity(), R.color.white));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.white));
            toolbarsearch.setVisibility(View.VISIBLE);
            toolbarsearch.setOnClickListener(this);
           // filter.setVisibility(View.GONE);







        } else if (i == 2) {

            toolbarsearch.setVisibility(View.VISIBLE);
            toolbarsearch.setOnClickListener(this);
            TabLayout.Tab tab = tabLayout.getTabAt(1);

            //filter.setVisibility(View.VISIBLE);
            tab.select();


            if (i == 2) {
                j = 1;
                filter.setVisibility(View.VISIBLE);
            }



        } else if (i == 3) {

            toolbarsearch.setVisibility(View.GONE);
           // filter.setVisibility(View.GONE);
            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();
        }


        search.setSearchString("");

        sortitemposition = 0;

        // search.setSearchString("");





/*
        toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (i == 1) {

                    search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                    search.setVisibility(View.VISIBLE);
                    Sessiondata.getInstance().setExplorenewfullpage(1);
                    tosno = Sessiondata.getInstance().getToursno();
                    cidval = "3";
                    OPenSearch(itemsfinal);
                    parentview.setVisibility(View.VISIBLE);


                } else if (i == 2) {

                    search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                    search.setVisibility(View.VISIBLE);
                    Sessiondata.getInstance().setExplorenewfullpage(2);
                    //itemsfinal = Sessiondata.getInstance().getFoodcatgory();
                    tosno = Sessiondata.getInstance().getToursno();
                    foodcidval = "1";
                    foodOPenSearch(itemsfinal);
                    parentview.setVisibility(View.VISIBLE);


                } else if (i == 3) {

                    Sessiondata.getInstance().setExplorenewfullpage(3);

                    search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                    search.setVisibility(View.GONE);
                }


            }
        });
*/







/*
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // your code here
                ctm = new CreatedTripModel();
                if(position == 0){

                    Toast.makeText(getActivity(),"Things",Toast.LENGTH_LONG).show();
                    ctm.setCategoryID("3");
                    ctm.setCategorytype("Things to do info");
                    showguideFragment(ctm);
                }
                else if (position == 1){


                    Toast.makeText(getActivity(),"Food",Toast.LENGTH_LONG).show();
                    ctm.setCategoryID("1");
                    ctm.setCategorytype("Restaurant info");
                    showguideFragment(ctm);
                }

                else if (position == 2){
                    ctm.setCategoryID("2");
                    ctm.setCategorytype("Prayers info");
                    showguideFragment(ctm);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/






        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){  // if you want the second page, for example
                    //Your code here
                    toolbarsearch.setVisibility(View.VISIBLE);


                    Sessiondata.getInstance().setExplorenewfullpage(1);
                    tosno = Sessiondata.getInstance().getToursno();


                    i = Sessiondata.getInstance().getExplorenewfullpage();
                    useridval = Sessiondata.getInstance().getReviewfbemailid();

                    if (i == 1) {
                        j = 0;
                        filter.setVisibility(View.VISIBLE);

                    }



                    j = 0;

                    filter.setVisibility(View.VISIBLE);





                    toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (i == 1) {

                                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                                search.setVisibility(View.VISIBLE);
                                Sessiondata.getInstance().setExplorenewfullpage(1);
                                tosno = Sessiondata.getInstance().getToursno();
                                cidval = "3";
                                OPenSearch(itemsfinal);
                                parentview.setVisibility(View.VISIBLE);


                            } else if (i == 2) {

                                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                                search.setVisibility(View.VISIBLE);
                                Sessiondata.getInstance().setExplorenewfullpage(2);
                                //itemsfinal = Sessiondata.getInstance().getFoodcatgory();
                                tosno = Sessiondata.getInstance().getToursno();
                                foodcidval = "1";
                                foodOPenSearch(itemsfinal);
                                parentview.setVisibility(View.VISIBLE);


                            } else if (i == 3) {

                                Sessiondata.getInstance().setExplorenewfullpage(3);

                                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                                search.setVisibility(View.GONE);
                            }


                        }
                    });






                }

                else if (position == 1){
                    toolbarsearch.setVisibility(View.VISIBLE);

                    Sessiondata.getInstance().setExplorenewfullpage(2);


                    i = Sessiondata.getInstance().getExplorenewfullpage();
                    useridval = Sessiondata.getInstance().getReviewfbemailid();
                    tosno = Sessiondata.getInstance().getToursno();
                    if (i == 2) {
                        j = 1;
                        filter.setVisibility(View.VISIBLE);
                    }




                    j = 1;

                    filter.setVisibility(View.VISIBLE);


                    toolbarsearch.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {




                            if (i == 1) {

                                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                                search.setVisibility(View.VISIBLE);
                                Sessiondata.getInstance().setExplorenewfullpage(1);
                                tosno = Sessiondata.getInstance().getToursno();
                                cidval = "3";
                                OPenSearch(itemsfinal);
                                parentview.setVisibility(View.VISIBLE);


                            } else if (i == 2) {

                                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                                search.setVisibility(View.VISIBLE);
                                Sessiondata.getInstance().setExplorenewfullpage(2);
                                //itemsfinal = Sessiondata.getInstance().getFoodcatgory();
                                tosno = Sessiondata.getInstance().getToursno();
                                foodcidval = "1";
                                foodOPenSearch(itemsfinal);
                                parentview.setVisibility(View.VISIBLE);


                            } else if (i == 3) {

                                Sessiondata.getInstance().setExplorenewfullpage(3);

                                search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                                search.setVisibility(View.GONE);
                            }


                        }
                    });




                }

                else if (position == 2){


                    filter.setVisibility(View.GONE);
                    toolbarsearch.setVisibility(View.GONE);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });














        return v;
    }


    public void Categorylistmaxstart(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });


    }

    public void Categorylistminstar(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });


        // Changeadapter(items, value);


    }


    public void Categorylistdesc(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Collections.reverse(items);

        //Changeadapter(items, value);
    }

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

        tosno = Sessiondata.getInstance().getToursno();

        thingssearchval = search.getSearchText().toString();


       // Toast.makeText(getActivity(), searchval, Toast.LENGTH_LONG).show();


        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        new WebPageTask(save,cidval,thingssearchval,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }


    @Override
    public void OPenSearch(List<Categorylistmodel> v) {
        search.revealFromMenuItem(R.id.search, _A);
       /* if (null != v && v.size() > 0) {
            for (int items = 0; items < v.size(); items++) {
                Categorylistmodel l = v.get(items);
                SearchResult option = new SearchResult(l.getName(), getResources().getDrawable(
                        R.drawable.ic_history));
                search.addSearchable(option);
            }
        }*/
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
        });
    }


    public void enablelist(String value) {
        ArrayList<Categorylistmodel> searchtext = new ArrayList<>();
        if (itemsfinal.size() > 0) {
            for (int items = 0; items < itemsfinal.size(); items++) {
                Categorylistmodel l = itemsfinal.get(items);
                if (l.getName().contains(value)) {
                    searchtext.add(l);
                }
            }
            parentview.setVisibility(View.VISIBLE);
            Categorylist(searchtext, 0);
            toolbarsearch.setVisibility(View.VISIBLE);
        }
    }


    protected void foodcloseSearch() {


        tosno = Sessiondata.getInstance().getToursno();
        searchval = search.getSearchText().toString();

     /*   Toast.makeText(getActivity(), tosno, Toast.LENGTH_LONG).show();


        Toast.makeText(getActivity(), foodcidval, Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), searchval, Toast.LENGTH_LONG).show();
*/


      //  Toast.makeText(getActivity(), useridval, Toast.LENGTH_LONG).show();







        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        new foodWebPageTask(foodsave, foodcidval, searchval,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }







    @Override
    public void foodOPenSearch(List<Categorylistmodel> v) {
        search.revealFromMenuItem(R.id.search, _A);
       /* if (null != v && v.size() > 0) {
            for (int items = 0; items < v.size(); items++) {
                Categorylistmodel l = v.get(items);
                SearchResult option = new SearchResult(l.getName(), getResources().getDrawable(
                        R.drawable.ic_history));
                search.addSearchable(option);
            }}*/

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


                foodcloseSearch();
            }

            @Override
            public void onSearchTermChanged(String term) {
            }

            @Override
            public void onSearch(String searchTerm) {

            }

            @Override
            public void onResultClick(SearchResult result) {
                enablelists(result.title);
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
        });
    }



    public void enablelists(String value) {
        ArrayList<Categorylistmodel> searchtext = new ArrayList<>();
        if (itemsfinal.size() > 0) {
            for (int items = 0; items < itemsfinal.size(); items++) {
                Categorylistmodel l = itemsfinal.get(items);
                if (l.getName().contains(value)) {
                    searchtext.add(l);
                }
            }
            parentview.setVisibility(View.VISIBLE);
            Categorylist(searchtext, 0);
            toolbarsearch.setVisibility(View.VISIBLE);
        }
    }





















    public void Categorylist(ArrayList<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        // Changeadapter(items, value);

        Sessiondata.getInstance().setThingstodonewuimain(items);

        /* tourdetailsvalue = Sessiondata.getInstance().getThingstodonewuimain();

        if(tourdetailsvalue!=null){
            emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
            save.setAdapter(emphistory);
        }
        else{

        }
*/

    }











    private class foodWebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid,searchvalue,useridval;
        View vs;
        private foodWebPageTask(View vs, String subcatid, String searchvalue,String useridval) {
            this.subcatid = subcatid;
            this.searchvalue = searchvalue;
            this.vs = vs;
            this.useridval = useridval;

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
            Call<CategorylistValuesResponse> call = a.Searchvalnewsapi(tosno,subcatid,searchvalue,useridval);
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

            //(tosno,subcatid,searchvalue,useridval);
            /*Toast.makeText(getActivity(), anullcheck, Toast.LENGTH_LONG).show();

            Toast.makeText(getActivity(), String.valueOf(c.Status), Toast.LENGTH_LONG).show();

            Toast.makeText(getActivity(), subcatid, Toast.LENGTH_LONG).show();

            Toast.makeText(getActivity(), tosno, Toast.LENGTH_LONG).show();

            Toast.makeText(getActivity(), searchvalue, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), useridval, Toast.LENGTH_LONG).show();*/

            message = c.msg;

            if (anullcheck.equals("Yes")) {

                //Toast.makeText(getActivity(), c.msg, Toast.LENGTH_LONG).show();


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






                            Sessiondata.getInstance().setExplorenewfullpage(2);



                            /*Fragment fr = new Exporeallnewui();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);*/
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

                        itemsfinal = items;
                        int i = sortitemposition;
                        if (i == 0) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistmaxstart(itemsfinal, 0);}
                        } else if (i == 1) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistminstar(itemsfinal, 0);}
                        } else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist((ArrayList<Categorylistmodel>) itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {

                        Sessiondata.getInstance().setFoodcatgory(null);
                    }
                    d.dismiss();

                    search.hideCircularly(_A);


                    Sessiondata.getInstance().setScroolthings(2);
                    Sessiondata.getInstance().setScroolfood(2);




                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);


                   /* i = Sessiondata.getInstance().getExplorenewfullpage();


                    if(i == 1) {

                        TabLayout.Tab tab = tabLayout.getTabAt(0);
                        //tab.setText("Things To Do");

                        tab.select();


                        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getActivity(), R.color.orangeindicatore));
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.orangeindicatore));


                    }
                    else if (i == 2){


                        TabLayout.Tab tab = tabLayout.getTabAt(1);
                        tab.select();
                    }
                    else if (i == 3){


                        TabLayout.Tab tab = tabLayout.getTabAt(2);
                        tab.select();
                    }*/





                }


                else if (c.Status == 0) {
                    d.dismiss();
                    search.hideCircularly(_A);


//Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();

                    Sessiondata.getInstance().setFoodcatgory(null);
                    Sessiondata.getInstance().setExplorenewfullpage(2);

                    //Toast.makeText(getActivity(),"failedfilter",Toast.LENGTH_LONG).show();



                }
            }}}






    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid,searchvalue,useridval;
        View vs;
        private WebPageTask(View vs, String subcatid, String searchvalue,String useridval) {
            this.subcatid = subcatid;
            this.searchvalue = searchvalue;
            this.vs = vs;
            this.useridval = useridval;

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
            Call<CategorylistValuesResponse> call = a.Searchvalnewsapi(tosno,subcatid,searchvalue,useridval);
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
                            Sessiondata.getInstance().setThingstodonewuimain((ArrayList<Categorylistmodel>) items);

                            Sessiondata.getInstance().setExplorenewfullpage(1);



                            /*Fragment fr = new Exporeallnewui();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);*/
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

                        itemsfinal = items;
                        int i = sortitemposition;
                        if (i == 0) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistmaxstart(itemsfinal, 0);}
                        } else if (i == 1) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistminstar(itemsfinal, 0);}
                        } else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist((ArrayList<Categorylistmodel>) itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {

                        Sessiondata.getInstance().setThingstodonewuimain(null);
                    }
                    d.dismiss();



                    search.hideCircularly(_A);




                    Sessiondata.getInstance().setScroolthings(2);
                    Sessiondata.getInstance().setScroolfood(2);

                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);













/*
                    i = Sessiondata.getInstance().getExplorenewfullpage();


                    if(i == 1) {

                        TabLayout.Tab tab = tabLayout.getTabAt(0);
                        //tab.setText("Things To Do");

                        tab.select();

                        //tabLayout.setTabTextColors(Color.BLACK,Color.WHITE);
                        // tabLayout.setTabTextColors(getResources().getColorStateList(R.color.white));
                        //   tabLayout.setSelectedTabIndicatorColor(Color.WHITE);


                        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getActivity(), R.color.orangeindicatore));
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.orangeindicatore));


                    }
                    else if (i == 2){


                        TabLayout.Tab tab = tabLayout.getTabAt(1);
                        tab.select();
                    }
                    else if (i == 3){


                        TabLayout.Tab tab = tabLayout.getTabAt(2);
                        tab.select();
                    }*/





                }


                else if (c.Status == 0) {
                    d.dismiss();
                    search.hideCircularly(_A);
                    Sessiondata.getInstance().setFoodcatgory(null);
                    Sessiondata.getInstance().setExplorenewfullpage(1);

                    //Toast.makeText(getActivity(),"failedfilter",Toast.LENGTH_LONG).show();



                }
            }}}



















    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new CategoryItem_list_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            //setupViewPager(viewPager);

        } catch (Exception we) {
            Log.d("Exe", "" + we);
        }
    }


    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabs, null);
        tabOne.setText("Things To Do");
       tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.thingsto, 0, 0);
        //tabLayout.getTabAt(0).setIcon(R.drawable.thingsto);
        tabOne.setSelected(true);

        tabLayout.getTabAt(0).setCustomView(tabOne);
        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tabs, null);
        tabTwo.setText("Food & Drinks");
       tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.foods, 0, 0);
       // tabLayout.getTabAt(1).setIcon(R.drawable.foods);
        tabOne.setSelected(true);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tabs, null);
        tabThree.setText("Special Deals");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.new_image, 0, 0);
     //   tabLayout.getTabAt(2).setIcon(R.drawable.new_image);
        tabOne.setSelected(true);
        tabLayout.getTabAt(2).setCustomView(tabThree);
        //   tabLayout.getTabAt(2).setIcon(R.drawable.new_image);

    }






    private void setupTabIconss() {
        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabssss, null);
       TextView tv1 = (TextView) tabOne.findViewById(R.id.tab);
        tv1.setText("Things To Do");
        ImageView img1 = (ImageView) tabOne.findViewById(R.id.img);
       img1.setImageResource(R.drawable.imageselecter);
        tabOne.setSelected(true);

        tabLayout.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabOne2 = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabssss, null);
        TextView tv2 = (TextView) tabOne2.findViewById(R.id.tab);
        tv2.setText("Food & Drinks");
        ImageView img2 = (ImageView) tabOne2.findViewById(R.id.img);
        img2.setImageResource(R.drawable.imageselecterfoodwhite);
        tabLayout.getTabAt(1).setCustomView(tabOne2);

        LinearLayout tabOne3 = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabssss, null);
        TextView tv3 = (TextView) tabOne3.findViewById(R.id.tab);
        tv3.setText("Special Deals");
        ImageView img3 = (ImageView) tabOne3.findViewById(R.id.img);
        img3.setImageResource(R.drawable.imageselecterspecialdeals);

        tabLayout.getTabAt(2).setCustomView(tabOne3);


    }



    private void setupViewPager(ViewPager viewPager) {
       // ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFrag(new ExplorenewThingsfragment(), "Things To Do");



        adapter.addFrag(new NewUiFoodfragment(), "Food & Drinks");
        adapter.addFrag(new NewuiSpecialdealsFragment(), "Special Deals");
        viewPager.setAdapter(adapter);
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
             getFragmentManager().popBackStack();

        }

         else  if(v == daytourcreatetrip){
             // cc = Sessiondata.getInstance().getCtmvalue();
             // MytripDetFragmentmap(cc);

             showExploreFragment();

         }

        else if (v == filter){




             if(j == 0){


                 InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.hideSoftInputFromWindow(search.getWindowToken(), 0);





                 eunpeong = "'Eunpeong-gu'";
                 dobong = "'Dobong-gu'";
                 nowon = "'Nowon-gu'";
                 seongbuk = "'Seongbuk-gu'";
                 jongno = "'Jongno-gu'";
                 seodaemun = "'Seodaemun-gu'";
                 mapo = "'Mapo-gu'";
                 yongsan = "'Yongsan-gu'";
                 jung = "'Jung-gu'";
                 seongdong = "'Seongdong-gu'";
                 gangnam = "'Gangnam-gu'";
                 songpa = "'Songpa-gu'";
                 yeongdeungpo = "'Yeongdeungpo-gu'";
                 dongjak = "'Dongjak-gu'";
                 gwangjin = "'Gwangjin-gu'";
                 seocho = "'Seocho-gu'";
                 dongdaemun = "'Dongdaemun-gu'";
                 guro = "'Guro-gu'";
                 geumcheon = "'Geumcheon-gu'";
                 gangbuk = "'Gangbuk-gu'";
                 cheoin = "'Cheoin-gu'";
                 giheung = "'Giheung-gu'";
                 kowloon = "'Kowloon City'";
                 wanchai = "'Wan Chai'";
                 central =  "'Central'";
                 tuenmun = "'Tuen Mun'";
                 islands = "'Islands'";
                 southern = "'Southern'";
                 eastern = "'Eastern'";
                 kwuntong = "'Kwun Tong'";
                 shamshuipo = "'Sham Shui Po'";
                 tsuenwan = "'Tsuen Wan'";

               //  allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;


                // allneighbourhoods = "' '";



                 Sessiondata.getInstance().setExplorenewfullpage(1);


                 boxname = new ArrayList<>();


                 tosno = Sessiondata.getInstance().getToursno();
                 Categorylistparam = new ArrayList<Categorylistneibourhood>();
                 fillData();



             }

             else if (j == 1) {

                 InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                 //  Toast.makeText(getActivity(),"filteclcik",Toast.LENGTH_LONG).show();
                 Sessiondata.getInstance().setExplorenewfullpage(2);




                 eunpeong = "'Eunpeong-gu'";
                 dobong = "'Dobong-gu'";
                 nowon = "'Nowon-gu'";
                 seongbuk = "'Seongbuk-gu'";
                 jongno = "'Jongno-gu'";
                 seodaemun = "'Seodaemun-gu'";
                 mapo = "'Mapo-gu'";
                 yongsan = "'Yongsan-gu'";
                 jung = "'Jung-gu'";
                 seongdong = "'Seongdong-gu'";
                 gangnam = "'Gangnam-gu'";
                 songpa = "'Songpa-gu'";
                 yeongdeungpo = "'Yeongdeungpo-gu'";
                 dongjak = "'Dongjak-gu'";
                 gwangjin = "'Gwangjin-gu'";
                 seocho = "'Seocho-gu'";
                 dongdaemun = "'Dongdaemun-gu'";
                 guro = "'Guro-gu'";
                 geumcheon = "'Geumcheon-gu'";
                 gangbuk = "'Gangbuk-gu'";
                 cheoin = "'Cheoin-gu'";
                 giheung = "'Giheung-gu'";
                 kowloon = "'Kowloon City'";
                 wanchai = "'Wan Chai'";
                 central =  "'Central'";
                 tuenmun = "'Tuen Mun'";
                 islands = "'Islands'";
                 southern = "'Southern'";
                 eastern = "'Eastern'";
                 kwuntong = "'Kwun Tong'";
                 shamshuipo = "'Sham Shui Po'";
                 tsuenwan = "'Tsuen Wan'";

               //  allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                 boxname = new ArrayList<>();


                 tosno = Sessiondata.getInstance().getToursno();
                 Categorylistparam = new ArrayList<Categorylistneibourhood>();
                 foodfillData();



















             }
         }

        else if (v == toolbarsearch){


             if (i == 1) {

                 search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                 search.setVisibility(View.VISIBLE);
                 Sessiondata.getInstance().setExplorenewfullpage(1);
                 tosno = Sessiondata.getInstance().getToursno();
                 cidval = "3";
                 OPenSearch(itemsfinal);
                 parentview.setVisibility(View.VISIBLE);


             } else if (i == 2) {

                 search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                 search.setVisibility(View.VISIBLE);
                 Sessiondata.getInstance().setExplorenewfullpage(2);
                 //itemsfinal = Sessiondata.getInstance().getFoodcatgory();
                 tosno = Sessiondata.getInstance().getToursno();
                 foodcidval = "1";
                 foodOPenSearch(itemsfinal);
                 parentview.setVisibility(View.VISIBLE);


             } else if (i == 3) {

                 Sessiondata.getInstance().setExplorenewfullpage(3);

                 search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
                 search.setVisibility(View.GONE);
             }

         }
    }






    private void foodfillData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CITYNEARBOURHOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject json = new JSONObject(response);

                    staus = json.getInt("Status");
                    message = json.getString("msg");
                    JSONArray jarray = json.getJSONArray("categorylist");
                    for(int i =0; i<jarray.length();i++){

                        JSONObject cararray = jarray.getJSONObject(i);
                        Categorylistneibourhood citypass = new Categorylistneibourhood();
                        citypass.setDistrict(cararray.getString("district"));
                        Categorylistparam.add(citypass);

                        Sessiondata.getInstance().setCategoryneibourho(Categorylistparam);
                    }

                    if(staus == 1){

                        //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        products = Sessiondata.getInstance().getCategoryneibourho();




                        String result = "";
                        boolean first = true;

                        for(int i =0;i<products.size(); i++){

                            allneigval = products.get(i).getDistrict();
                            boxname.add(allneigval);
                        }


                        String frnames[]=boxname.toArray(new String[boxname.size()]);

                        for(String k: frnames)
                        {
                            System.out.println(k);
                            if (first) {
                                result += "'" +k+ "'";
                                first = false;
                            } else {
                                result += ","+"'"+k+"'";
                            }
                        }

                        if (!result.isEmpty()) {
                            neifhbouvalue = result;
                        } else {
                            neifhbouvalue = "' '";
                        }




                        Sessiondata.getInstance().setNeighboorhoodval(neifhbouvalue);









                        //  Fragment fr = new Filteraction();
                        Fragment fr = new FilterFoodfragment();
                        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                        fc.replaceFragment(fr);





                    }

                    else {

                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("cityid",tosno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
















    private void fillData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CITYNEARBOURHOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject json = new JSONObject(response);

                    staus = json.getInt("Status");
                    message = json.getString("msg");
                    JSONArray jarray = json.getJSONArray("categorylist");
                    for(int i =0; i<jarray.length();i++){

                        JSONObject cararray = jarray.getJSONObject(i);
                        Categorylistneibourhood citypass = new Categorylistneibourhood();
                        citypass.setDistrict(cararray.getString("district"));
                        Categorylistparam.add(citypass);

                        Sessiondata.getInstance().setCategoryneibourho(Categorylistparam);
                    }

                    if(staus == 1){

                        //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        products = Sessiondata.getInstance().getCategoryneibourho();




                        String result = "";
                        boolean first = true;

                        for(int i =0;i<products.size(); i++){

                            allneigval = products.get(i).getDistrict();
                            boxname.add(allneigval);
                        }


                        String frnames[]=boxname.toArray(new String[boxname.size()]);

                        for(String k: frnames)
                        {
                            System.out.println(k);
                            if (first) {
                                result += "'" +k+ "'";
                                first = false;
                            } else {
                                result += ","+"'"+k+"'";
                            }
                        }

                        if (!result.isEmpty()) {
                            neifhbouvalue = result;
                        } else {
                            neifhbouvalue = "' '";
                        }




                        Sessiondata.getInstance().setNeighboorhoodval(neifhbouvalue);









                        //  Fragment fr = new Filteraction();
                        Fragment fr = new Filterthingstodofragment();
                        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                        fc.replaceFragment(fr);






                    }

                    else {

                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("cityid",tosno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }





























    @Override
    public void onResume() {
        super.onResume();

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
                    return true;
                }
                return false;
            }
        });


    }








    public void showExploreFragment() {
        Fragment fr = new CreateTripFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
