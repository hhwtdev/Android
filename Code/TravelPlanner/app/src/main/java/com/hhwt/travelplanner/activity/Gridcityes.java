package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.CustomApplicationClass;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.adapter.CustomGrids;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.quinny898.library.persistentsearch.SearchBox;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
 * Created by jeyavijay on 10/09/16.
 */
public class Gridcityes extends Fragment {
    private static final String AALCITES = "http://hhwt.tech/hhwt_webservice/get_cities.php";
    GridView grid;
    ArrayList<Categorylistcity>citydetails;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static Activity _A;
    public String fb_id;
    String mess;
    Retrofit retrofit;

    public int sortitemposition = 0;
    String tosno;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    ProgressDialog progressDialog;
    TextView apptitle;
    ImageView backclick;
    String cidval;
    View savethings;
    View save;
    int viewstatus;
    TextView countryapptitle;
    String message;
    ImageView filter,toolbarsearch;

    LinearLayout linearvalbottom;
    SearchBox search;
    String useridval;

    CustomGrids adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.citygride,container,false);
        CustomApplicationClass.getInstance().trackScreenView("Explore");

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

       // Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.VISIBLE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.GONE);



        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);


        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);



        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);






        _A = getActivity();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");



        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        citydetails = new ArrayList<Categorylistcity>();


        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Explore a city");
        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);

        countryapptitle.setVisibility(View.GONE);

        grid=(GridView) v.findViewById(R.id.grid);

       /* CustomGrids cus_adapter = new CustomGrids(getActivity(),build_cities);
        grid.setAdapter(cus_adapter);*/

// Grid Listeners
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                editor.putString("sno",citydetails.get(position).getSno());
                editor.putString("city",citydetails.get(position).getCity());
                editor.putString("img", citydetails.get(position).getImg());
                editor.putString("country", citydetails.get(position).getCountry());
                editor.commit();
                Sessiondata.getInstance().setToursno(citydetails.get(position).getSno());
                String cityimage =  citydetails.get(position).getImg();
                SharedPreferences preferencessfbprofiledob = getActivity().getSharedPreferences("imgs", getActivity().getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editorsimg = preferencessfbprofiledob.edit();
                editorsimg.putString("fimg", cityimage);
                editorsimg.commit();

                tosno = Sessiondata.getInstance().getToursno();


                Sessiondata.getInstance().setCitytitle(citydetails.get(position).getSalutation());

                Sessiondata.getInstance().setCitydetails(citydetails.get(position).getDescription());


                //   thingstodo();


                reviewsplit();






            }
        });

        return v;
    }






    @Override
    public void onResume() {
        super.onResume();

        CustomApplicationClass.getInstance().trackScreenView("Explore");

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener


                    // Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();

                    //getFragmentManager().popBackStack();
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });





    }










    private void foodanddrinks() {

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

        new WebPageTask(save, cidval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);






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
                            }
                        }
                    } else {
                        Sessiondata.getInstance().setFoodcatgory(null);
                    }
                    d.dismiss();



                    thingstodonew();

                    /*Fragment fr = new New_Explore_activities_Fragment();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);*/
                }


                else if (c.Status == 0) {
                    d.dismiss();

                    thingstodonew();


                    Sessiondata.getInstance().setFoodcatgory(null);

                }


                /*else {
                    if (items.size() > 0) {

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
                            }} else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}}} else {

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
                }*/

            }}}






    private void thingstodonew() {

        cidval = "3";
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //new thingWebPageTask(savethings, cidval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);



        new thingWebPageTasknew(savethings, cidval,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }






    private class thingWebPageTasknew extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {


        List<Categorylistmodel> items = new ArrayList<>();


        //  ProgressDialog d = new ProgressDialog(_A);

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

            //  d.setMessage("Please wait...");
            // d.show();
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
                        //d.dismiss();
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


                    foodanddrinksnew();

                    // d.dismiss();
                }

                else if (c.Status == 0) {
                    // d.dismiss();
                    Sessiondata.getInstance().setThingstodonewuimainnew(null);

                    foodanddrinksnew();




                }

            }}}


    private void foodanddrinksnew() {

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



    private class WebPageTasknew extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        // ProgressDialog d = new ProgressDialog(_A);
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
            // d.setMessage("Please wait...");
            // d.show();
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

                            Sessiondata.getInstance().setFoodcatgorynew((ArrayList<Categorylistmodel>) items);
                        }
                    } else {
                        //  d.dismiss();
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
                    // d.dismiss();





                    Fragment fr = new New_Explore_activities_Fragment();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);


                }


                else if (c.Status == 0) {
                    //  d.dismiss();
                    Sessiondata.getInstance().setFoodcatgorynew(null);





                }
            }}}









    private void reviewsplit() {





        SharedPreferences preferencprofileway = getActivity().getSharedPreferences("newfb", getActivity().MODE_PRIVATE);
        String proviewpic = preferencprofileway.getString("facebookway", null);
        Log.d("profilename", "" + proviewpic);


        if (proviewpic.equals("2")) {




//facebookname
            SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
            String fprofilename = preferencessname.getString("facebookname", null);
            Log.d("profilename", "" + fprofilename);


            Sessiondata.getInstance().setLoginname(fprofilename);


//facebookpicture
            SharedPreferences preferencesspic = getActivity().getSharedPreferences("newprofilepicture", getActivity().MODE_PRIVATE);
            String profilepicture = preferencesspic.getString("facebookname", null);
            Log.d("profilename", "" + profilepicture);


//facebook  fbid

            SharedPreferences preferencessfbid = getActivity().getSharedPreferences("newprofilefbid", getActivity().MODE_PRIVATE);
            String profilefbid = preferencessfbid.getString("facebookid", null);
            Log.d("profilename", "" + profilefbid);

            Sessiondata.getInstance().setReviewfbemailid(profilefbid);
            useridval = Sessiondata.getInstance().getReviewfbemailid();





            String URL = profilepicture;
            GetXMLTask task = new GetXMLTask();
            // Execute the task
            task.execute(new String[]{URL});
















        }
        else if (proviewpic.equals("3")) {
            Sessiondata.getInstance().setFbimage(null);



            //Registername
            SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
            String fprofilename = preferencessname.getString("registername", null);
            Log.d("profilename", "" + fprofilename);
            Sessiondata.getInstance().setLoginname(fprofilename);



            SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
            String profileemail = preferencessemail.getString("registeremail", null);
            Log.d("profileemail", "" + profileemail);

            Sessiondata.getInstance().setReviewfbemailid(profileemail);


            useridval = Sessiondata.getInstance().getReviewfbemailid();


            thingstodonew();




        }

        else {
            Sessiondata.getInstance().setFbimage(null);


//Registername
            SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
            String fprofilename = preferencessname.getString("registername", null);
            Log.d("profilename", "" + fprofilename);
            Sessiondata.getInstance().setLoginname(fprofilename);


            SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
            String profileemail = preferencessemail.getString("registeremail", null);
            Log.d("profileemail", "" + profileemail);


            Sessiondata.getInstance().setReviewfbemailid(profileemail);



            useridval = Sessiondata.getInstance().getReviewfbemailid();




            thingstodonew();



        }









    }













    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {




        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            //  imageView.setImageBitmap(result);
            Log.d("Arjunimage", "" + result);
            Sessiondata.getInstance().setFbimage(result);
            //Sessiondata.getInstance().setFbprofilename(pname);




            thingstodonew();


        }



        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }



        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }









    private void thingstodo() {

        cidval = "3";
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        new thingWebPageTask(savethings, cidval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }


    private class thingWebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {


        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();



        String subcatid;
        View vs;
        private thingWebPageTask(View vs, String subcatid) {
            this.subcatid = subcatid;
            this.vs = vs;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            items.clear();

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
                        foodanddrinks();
                    }
                    d.dismiss();

                    foodanddrinks();



                   /* Fragment fr = new New_Explore_activities_Fragment();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);*/
                }

                else if (c.Status == 0) {
                    d.dismiss();
                    foodanddrinks();
                    Sessiondata.getInstance().setThingstodonewuimain(null);

                }


               /* else {
                    Sessiondata.getInstance().setThingstodonewuimain(null);
                    if (items.size() > 0) {
                        Sessiondata.getInstance().setThingstodonewuimain(null);

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
                            }} else if (i == 2) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylist(itemsfinal, 0);
                            }
                        } else if (i == 3) {
                            if (null != itemsfinal && itemsfinal.size() > 0) {
                                Categorylistdesc(itemsfinal, 0);}}} else {

                    }
                    //d.dismiss();

                    Snackbar snack = Snackbar.make(vs, "There is no item to show", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    ViewGroup group = (ViewGroup) snack.getView();
                    View v = snack.getView();
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                    snack.show();
                }*/



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








    /*@Override
    public void onItemClick(View view, City c) {
      *//*  editor.putString("sno",c.getSno());
        editor.putString("city",c.getCity());
        editor.putString("img", c.getImg());
        editor.commit();
        String cityimage =  c.getImg();
        SharedPreferences preferencessfbprofiledob = getActivity().getSharedPreferences("imgs", getActivity().getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editorsimg = preferencessfbprofiledob.edit();
        editorsimg.putString("fimg", cityimage);
        editorsimg.commit();
        showExploreFragment();*//*
    }*/
    public Gridcityes() {


    }
    public void onConnectSuccess() {

    }
    // called when Tapjoy connect call failed
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            citydetails = new ArrayList<Categorylistcity>();
            analytics();
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





    private void analytics() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,AALCITES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("Status");
                            message = userObject.getString("msg");

                            JSONArray jsonarray1 = userObject.getJSONArray("categorylist");

                            for (int i = 0; i < jsonarray1.length(); i++) {
                                JSONObject obj2 = jsonarray1.getJSONObject(i);
                                Categorylistcity datas = new Categorylistcity();
                                datas.setSno(obj2.getString("sno"));
                                datas.setCity(obj2.getString("city"));
                                datas.setImg(obj2.getString("img"));
                                datas.setCountry(obj2.getString("country"));
                                datas.setSalutation(obj2.getString("salutation"));
                                datas.setDescription(obj2.getString("description"));

                                citydetails.add(datas);

                                /*Sessiondata.getInstance().setGuidimagedetails(photosdetails);
                                Log.d("Photourl", "" + Sessiondata.getInstance().getGuidimagedetails());*/
                            }



                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                            progressDialog.dismiss();
                            CustomGrids cus_adapter = new CustomGrids(getActivity(),citydetails);
                            grid.setAdapter(cus_adapter);

                            //Toast.makeText(getActivity(),"page"+pageno,Toast.LENGTH_SHORT).show();
                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                params.put("","");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }























/*
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
                        *//*save.setVisibility(View.VISIBLE);
                        materialrippleeff.setVisibility(View.VISIBLE);*//*
                        //Votecity(c.getCity());






                    } else {
                       *//* save.setVisibility(View.GONE);
                        materialrippleeff.setVisibility(View.GONE);*//*

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
    }*/







}
