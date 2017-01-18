package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.hhwt.travelplanner.fragment.SpecialdealsFragment;
import com.hhwt.travelplanner.fragment.Thingsfragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.categorylistvalues;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.foodtype;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.RectImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 12/09/16.
 */
public class  New_Explore_activities_Fragment extends Fragment implements
        DatePickerDialog.OnDateSetListener,View.OnClickListener{
    Retrofit retrofit;
    TextView discoverse;
        LinearLayout closeopenview;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    int tsucess;
    ArrayList<foodtype> foodtypdetails;
    public static final String EXTRATRIPINFO = "Viewinfo";
    private CreatedTripModel ctm;
    ArrayList<foodclassification> foodclassificationdetails;
    ArrayList<photoss> fphotosdetails;
    Hashtable connectFlags;
    public static final String KEY_USERID = "id";
    private static final String TOURDETAILS = "http://hhwt.tech/hhwt_webservice/tourcontent.php";

    String mess;
    private  static  final  String FOODANDDRINK = "http://hhwt.tech/hhwt_webservice/get_category_value_new.php";
View save,savethings;

String cidval,useridval;

View vv;
    RectImageView bigpage;
    RobotoTextView cityName;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static Activity _A;
    public String fb_id, _CityName, _CityID, _CityImage, _Country;
    int valcount;
    CreatedTripModel cc;
    ArrayList<Tourdetails> photosdetails;
   // LinearLayout linearvalbottom;
   String tosno;
    TextView apptitle;
    TextView anntitle,anncontent;
    public int sortitemposition = 0;
ImageView toolbarsearch;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    TextView daytourcreatetrip;
    SearchBox search;
    private ViewPager viewPager;
    ArrayList<categorylistvalues> categorylistvaluesdetails;
String mapclickmsg;
ImageView backclick;
LinearLayout linearvalbottom;
    TextView countryapptitle;
    ImageView filter;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();

int mapclickstatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.newfragment_explore_activity, container, false);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        useridval = Sessiondata.getInstance().getReviewfbemailid();;

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);


        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);


        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);



        backclick.setOnClickListener(this);




        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));









        valcount = 0;
        // LinearLayout linearvalbottom;
        //linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
       // linearvalbottom.setVisibility(View.GONE);


        discoverse = (TextView) v.findViewById(R.id.discoverse);
        closeopenview = (LinearLayout) v.findViewById(R.id.closeopenview);
        _A = getActivity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "1");
        _CityName = sharedpreferences.getString("city", "0");
        _CityImage = sharedpreferences.getString("img", "0");
        _Country = sharedpreferences.getString("country", "0");


        bigpage = (RectImageView) v.findViewById(R.id.bigpage);
       // cityName = (RobotoTextView) v.findViewById(R.id.cityName);
        Sessiondata.getInstance().setCityname(_CityName);
        Sessiondata.getInstance().setCountryname(_Country);
       // cityName.setText(_CityName);

        daytourcreatetrip = (TextView) v.findViewById(R.id.daytour);
        anncontent = (TextView) v.findViewById(R.id.anncontent);
        anntitle = (TextView) v.findViewById(R.id.anntitle);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.VISIBLE);
        apptitle.setText(_CityName);
        countryapptitle.setText(_Country);

        discoverse.setText("Discover" + " " + _CityName);
        daytourcreatetrip.setOnClickListener(this);

      /* toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        anntitle.setText(Sessiondata.getInstance().getCitytitle());
        anncontent.setText(Sessiondata.getInstance().getCitydetails());














/*        tosno = Sessiondata.getInstance().getToursno();


        if(tosno.equals("1")){
            anntitle.setText("Annyeong-haseyo!");
anncontent.setText("From trying authentic halal Korean food, to exploring hidden artsy lanes, Seoul is a delightful mix of historical and pop-culture! Halal eateries are mainly located near the Seoul Central Mosque, but do note that most Muslim-friendly eateries serve alcohol as its a huge part of the local culture.");
        }
        else if (tosno.equals("3")){
            anntitle.setText("Konnichiwa!");
            anncontent.setText("Vibrant and rich in pop-culture, Tokyo is one of those cities you’ll never get sick of. There’s always something new to explore and the city seems to change every single day! Halal food can be pretty easily found in the touristy areas as well but do note that most Muslim-friendly eateries serve alcohol as its a huge part of the local culture.");
        }

        else if (tosno.equals("4")){

            anntitle.setText("Hello!");
            anncontent.setText("The sunny island of Singapore may be tiny but there’s plenty to see, do and eat! There is a huge selection of halal-certified and Muslim-owned eateries so you’ll never go hungry. After all, eating is one of the local’s favourite pastimes!");
        }
        else if (tosno.equals("2")) {
            anntitle.setText("Neih hou!");
                    anncontent.setText("With an iconic skyline and natural harbor, Hong Kong is an immensely vibrant cosmopolitan city that comes alive at night with its bustling night markets! Being the land of dim sum, you just can’t leave the city without trying dim sum and roasted duck. Do note that Halal certified restaurants may also serve alcohol.");
        }
        else if(tosno.equals("5"))
        {
            anntitle.setText("Konnichiwa!");
            anncontent.setText("Step into the past when you set foot in the historical capital of Japan. Quite possibly one of the most beautiful cities in Japan, Kyoto is rich in tradition with a total of 17 UNESCO World Heritage Sites! Halal food isn’t as abundant as in Tokyo, but you can find vegetarian and seafood options in the city. Do note that most eateries serve alcohol as its a huge part of the local culture.");
        }
        else if (tosno.equals("6")){
            anntitle.setText("Konnichiwa!");
            anncontent.setText("Not quite as busy as Tokyo nor as serene as Kyoto, Osaka has its own unique charm that calls out to us! Being Japan’s foodie capital, this city comes alive at night with its bustling shopping streets and arcades.  Do note that most eateries serve alcohol as its a huge part of the local culture.");
        }
        else{
            anntitle.setText("");
            anncontent.setText("");
        }*/





//1 seoul
//3 toyko
//4 singapoure
        //2 Hong kong
        //5kyoto
//6 osaka





     //   foodanddrinks();




        viewPager = (ViewPager) v.findViewById(R.id.viewpagers);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
       // setupTabIcons();

        setupTabIconss();

        if(valcount == 0){
            closeopenview.setVisibility(View.VISIBLE);
        }


        discoverse.setOnClickListener(this);

        Picasso.with(getActivity()).load(_CityImage).networkPolicy(NetworkPolicy.OFFLINE).into(bigpage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(getActivity())
                        .load(_CityImage)
                        .error(R.drawable.background_default)
                        .into(bigpage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                            }
                        });
            }
        });
        ctm = new CreatedTripModel();
        return v;
    }

    private void thingstodo() {
        tosno = Sessiondata.getInstance().getToursno();
        cidval = "3";
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //new thingWebPageTask(savethings, cidval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new thingWebPageTasknew(savethings, cidval,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    private void foodanddrinks() {
        tosno = Sessiondata.getInstance().getToursno();
        cidval = "1";
  /*      fphotosdetails = new ArrayList<photoss>();
        foodclassificationdetails = new ArrayList<foodclassification>();
        foodtypdetails = new ArrayList<foodtype>();
        categorylistvaluesdetails = new ArrayList<categorylistvalues>();
        callfoodanddrinks();
*/

        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //new WebPageTask(save, cidval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        new WebPageTasknew(save, cidval,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {



            tosno = Sessiondata.getInstance().getToursno();
            photosdetails = new ArrayList<Tourdetails>();


            Tourcitydetails();

           // Sessiondata.getInstance().setTourdetails(null);

           thingstodo();

            foodanddrinks();
        } catch (Exception we) {

        }
    }






    private class WebPageTasknew extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid,useridval;
        View vs;
        private WebPageTasknew(View vs, String subcatid,String useridval) {
            this.subcatid = subcatid;
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
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapinew(subcatid, tosno,useridval);
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

                           /* if (dd.tourdetails.size() > 0) {
                                for (CategorylistValuesResponse.tourdetails s : dd.tourdetails) {
                                    tourdetails.add(new tourdetails(s.sno, s.image, s.content, s.sellingrate, s.currency, s.id, s.sub_id, s.image_one, s.image_two, s.image_three, s.image_four, s.tour_type, s.rate, s.highlights,s.inclusionandexclusion, s.exclusion, s.overviews, s.whatcanyouexpect, s.cancellation_policy, s.location, s.addi_info, s.tour_opt_info, s.tour_opt_link, s.website, s.number, s.tour_classification_one, s.tour_classification_two, s.enquiry, s.departurepoint, s.departuredate, s.departuretime, s.duration, s.returndetails));
                                }


                            }*/

                            items.add(new Categorylistmodel(dd.sno, dd.caegoryid, dd.name, dd.description,dd.foodc,dd.prayerc,dd.openhrs, dd.closehrs, dd.website, dd.timeremaining, dd.estimatedtime, dd.pricecurrency, dd.price, dd.country, dd.city, dd.state, dd.address, dd.postalcode, dd.map, dd.phone, dd.hhwtlink, dd.activity, dd.weight, dd.district, dd.latitude, dd.longitude, dd.weight, photoitems, fooditems, "", dd.tags, dd.foodttags, dd.foodctags, dd.tours,tourdetails,dd.overalreviews,dd.userreviews));
                            Sessiondata.getInstance().setFoodcatgorynew((ArrayList<Categorylistmodel>) items);
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
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {

                        Sessiondata.getInstance().setFoodcatgorynew(null);
                    }
                    d.dismiss();
                }


                else if (c.Status == 0) {
                    d.dismiss();
                    Sessiondata.getInstance().setFoodcatgorynew(null);

                }
            }}}















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
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid, tosno);
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
                            Sessiondata.getInstance().setFoodcatgory((ArrayList<Categorylistmodel>) items);
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
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {

                        Sessiondata.getInstance().setFoodcatgory(null);
                    }
                    d.dismiss();
                }


                else if (c.Status == 0) {
                    d.dismiss();
                    Sessiondata.getInstance().setFoodcatgory(null);

                }
            }}}







    public void Categorylistdesc(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Collections.reverse(items);
        //Changeadapter(items, value);
    }

    public void Categorylist(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
       // Changeadapter(items, value);
    }


    public void Categorylistmaxstart(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        Collections.reverse(items);}

       // Changeadapter(items, value);}

    public void Categorylistminstar(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
       // Changeadapter(items, value);
    }








    private class thingWebPageTasknew extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {


        List<Categorylistmodel> items = new ArrayList<>();


        ProgressDialog d = new ProgressDialog(_A);

        String subcatid,useridval;
        View vs;
        private thingWebPageTasknew(View vs, String subcatid,String useridval) {
            this.subcatid = subcatid;
            this.vs = vs;
            this.useridval = useridval;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // items.clear();

            d.setMessage("Please wait...");
            d.show();
        }
        @Override
        protected CategorylistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapinew(subcatid, tosno,useridval);
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
                            Sessiondata.getInstance().setThingstodonewuimainnew((ArrayList<Categorylistmodel>) items);
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
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {
                        Sessiondata.getInstance().setThingstodonewuimainnew(null);

                    }
                    d.dismiss();
                }

                else if (c.Status == 0) {
                    d.dismiss();
                    Sessiondata.getInstance().setThingstodonewuimainnew(null);

                }

            }}}





















    private class thingWebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {


        List<Categorylistmodel> items = new ArrayList<>();


        ProgressDialog d = new ProgressDialog(_A);

        String subcatid;
        View vs;
        private thingWebPageTask(View vs, String subcatid) {
            this.subcatid = subcatid;
            this.vs = vs;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // items.clear();

            d.setMessage("Please wait...");
            d.show();
        }
        @Override
        protected CategorylistValuesResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid, tosno);
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
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}
                        }
                    } else {
                        Sessiondata.getInstance().setThingstodonewuimain(null);

                    }
                   d.dismiss();
                }

                else if (c.Status == 0) {
                    d.dismiss();
                    Sessiondata.getInstance().setThingstodonewuimain(null);

                }

            }}}




/*
    private void setupTabIcons() {


        TextView tabOne = (TextView) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabss, null);
        tabOne.setText("Things To Do");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.thingsto, 0, 0);
        tabOne.setSelected(true);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tabss, null);
        tabTwo.setText("Food & Drinks");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.foods, 0, 0);
        tabOne.setSelected(true);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tabss, null);
        tabThree.setText("Special Deals");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.new_image, 0, 0);
        tabOne.setSelected(true);
        tabLayout.getTabAt(2).setCustomView(tabThree);
*/



/* TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();*//*


    }
*/



    private void setupTabIconss() {
        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabss, null);
        TextView tv1 = (TextView) tabOne.findViewById(R.id.tab);
        tv1.setText("Things To Do");
        ImageView img1 = (ImageView) tabOne.findViewById(R.id.img);
        img1.setImageResource(R.drawable.dasimageselecterthingsto);
        tabOne.setSelected(true);

        tabLayout.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabOne2 = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabss, null);
        TextView tv2 = (TextView) tabOne2.findViewById(R.id.tab);
        tv2.setText("Food & Drinks");
        ImageView img2 = (ImageView) tabOne2.findViewById(R.id.img);
        img2.setImageResource(R.drawable.dasimageselecterfoodwhite);
        tabLayout.getTabAt(1).setCustomView(tabOne2);

        LinearLayout tabOne3 = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabss, null);
        TextView tv3 = (TextView) tabOne3.findViewById(R.id.tab);
        tv3.setText("Special Deals");
        ImageView img3 = (ImageView) tabOne3.findViewById(R.id.img);
        img3.setImageResource(R.drawable.dasimageselecterspecialdeals);

        tabLayout.getTabAt(2).setCustomView(tabOne3);


    }

    private void setupViewPager(ViewPager viewPager) {
        //ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFrag(new Thingsfragment(), "Things To Do");
        adapter.addFrag(new Foodfragment(), "Food & Drinks");
        adapter.addFrag(new SpecialdealsFragment(), "Special Deals");


        viewPager.setAdapter(adapter);
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


    private void Tourcitydetails() {
        photosdetails.clear();
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
                               // progressDialog.dismiss();
                                //showExploreFragment();

                            }
                            else {
                             //   progressDialog.dismiss();
                               // Toast.makeText(getActivity(), "No data's available", Toast.LENGTH_LONG).show();


                                photosdetails.clear();
                                Sessiondata.getInstance().setTourdetails(null);




                            }

                        } catch (Exception ex) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                       // progressDialog.dismiss();
                       // Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
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
        /*progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();*/
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

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
    }

    public New_Explore_activities_Fragment() {
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
        Fragment fr = new CategoryItem_list_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(v == discoverse){
            if(valcount == 0){
                closeopenview.setVisibility(View.VISIBLE);
                valcount = 1;
            }
            else if (valcount == 1){
                closeopenview.setVisibility(View.VISIBLE);
                valcount = 0;


            }



        }

        else  if(v == daytourcreatetrip){
           // cc = Sessiondata.getInstance().getCtmvalue();
            // MytripDetFragmentmap(cc);

            showExploreFragment();

        }


else if (v == backclick){
            getFragmentManager().popBackStack();

        }
    }





    public void showExploreFragment() {
        Fragment fr = new CreateTripFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }





    public void MytripDetFragmentmap(CreatedTripModel viewModel) {
        Sessiondata.getInstance().setMtrip(1);
        Fragment fr = new MyTrip_Fragment_explore();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


}
