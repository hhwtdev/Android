package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hhwt.travelplanner.CheckLocationActivity;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.NearbyelementResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CategoryItem_list_Fragment;
import com.hhwt.travelplanner.activity.CategoryItem_list_NearbyelementFragment;
import com.hhwt.travelplanner.activity.CategoryItem_searchlist_Fragment;
import com.hhwt.travelplanner.activity.ExplorenewThingsfragment;
import com.hhwt.travelplanner.activity.MyTrip_Fragment_explore;
import com.hhwt.travelplanner.activity.NewUiFoodfragment;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.NewuiSpecialdealsFragment;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.activity.viewclick;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.categorylistvalues;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

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

public class View_itemInfo_Fragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,
        DatePickerDialog.OnDateSetListener,View.OnClickListener {
    private RobotoTextView mapclick, saveclick, dialclick, cat_name, cat_description, title_info, address_first, address_second, call, web, openinghours, closinghours, serves, vserves, foodcount, thingstocount, prayerscount;
    SliderLayout slider;
    private Boolean _startDate;
    private RelativeLayout prayers, thingstodo, food;
    Categorylistmodel C;
    NetworkImageView mapimage;
    ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
    public static final String EXTRAlinkINFO = "url";
    public static final String EXTRAVIEWINFO = "Viewinfo";
    String imgurl, ingid;
    public RatingBar item_ratingBar;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    private static final String MYMAPVIEWCLICK = "http://hhwt.tech/hhwt_webservice/get_nearby_elements.php";
    public static final String KEY_USERNAME = "did";
    ProgressDialog progressDialog;
    TextView apptitle;
    int ii;
    CreatedTripModel cc;
    private static final String NORMALREVIEWCMD = "http://hhwt.tech/hhwt_webservice/insertcomments.php";
    private static final String VIEWCOMMENT = "http://hhwt.tech/hhwt_webservice/viewcomments.php";
    Hashtable connectFlags;
    public static final String KEY_DATAELEMENT = "dataelement";
    public static final String KEY_FBID = "fbid";
    public static final String KEY_NAME = "name";
    public static final String KEY_COMMENTS = "comments";
    int viewstatus;
    int changepinstatus;
    private static final String DETAILSDATAELEMENTVIEWING = "http://hhwt.tech/hhwt_webservice/dataelementsview.php";
    String message;

    ImageView toolbarsearch;

    private static final String GUIDVIEWSHOWING = "http://hhwt.tech/hhwt_webservice/guide_views.php";

    SearchBox search;

ImageView backclick;


    String changepinmsg;
    ImageView reviewadd;
    ArrayList<viewclick> viewdetails;
    categorylistvalues datas;


    String userfbemailid,dateelemintidval,guidid;

LinearLayout linearvalbottom;

    String snovaluemap;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    public static final String EXTRATRIPINFO = "Viewinfo";
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    ArrayList<viewclick> emphistorydetails;
    String norsnno;
    String norfbbid;
    String norname;String changpas;

    public void mapcliclShowWebFragment() {
        Fragment fr = new Website_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAlinkINFO, Sessiondata.getInstance().getWebsitemapclick());
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public void ShowWebFragment() {
        Fragment fr = new Website_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAlinkINFO, C.getWebsite());
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void showguideFragment(CreatedTripModel ctm) {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        }

        Sessiondata.getInstance().setDataelementtripis("2");

        Fragment fr = new CategoryItem_list_NearbyelementFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public void showmapguidFragment(CreatedTripModel viewModel) {




        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else if (getActivity().getSupportFragmentManager().equals(null)) {




            Fragment fr = new CategoryItem_list_Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRATRIPINFO, viewModel);
            fr.setArguments(bundle);
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }











        Sessiondata.getInstance().setDataelementtripis("2");





        Fragment fr = new CategoryItem_list_NearbyelementFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);

    }



    public void mapView() {
        Intent mapview = new Intent(getActivity(), CheckLocationActivity.class);
        Bundle b = new Bundle();
        b.putString("lat", C.getLatitude());
        b.putString("long", C.getLongitude());
        b.putString("name", C.getName());
        b.putString("address", C.getAddress());

        mapview.putExtras(b);
        startActivity(mapview);
    }


    public void mapclickmapView() {

        Intent mapview = new Intent(getActivity(), CheckLocationActivity.class);
        Bundle b = new Bundle();
        b.putString("lat", Sessiondata.getInstance().getLatitudemapclick());
        b.putString("long", Sessiondata.getInstance().getLongitudeemapclick());
        b.putString("name", Sessiondata.getInstance().getNamemapclick());
        b.putString("address", Sessiondata.getInstance().getMapclickaddress());

        mapview.putExtras(b);
        startActivity(mapview);
    }


    public void showViewDetFragmentmap(CreatedTripModel viewModel) {
        Sessiondata.getInstance().setMtrip(1);
        Fragment fr = new SaveTrip_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public void showViewDetFragment(Categorylistmodel viewModel) {
        Sessiondata.getInstance().setMtrip(0);
        Fragment fr = new SaveTrip_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public void MytripDetFragment(Categorylistmodel viewModel) {
        Sessiondata.getInstance().setMtrip(0);
       Fragment fr = new MyTrip_Fragment_explore();

       // Fragment fr = new SaveTrip_Fragment();


        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_iteminfo, container, false);


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("HHWT");

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);
        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);




        mapclick = (RobotoTextView) v.findViewById(R.id.mapclick);
        _A = getActivity();
        mRequestQueue = Volley.newRequestQueue(_A);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fb_id = sharedpreferences.getString("fb_id", "0");
        internet = new InternetAccessCheck();
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        SharedPreferences preferencesss = getActivity().getSharedPreferences("tempss", getActivity().MODE_PRIVATE);
        String profileid = preferencesss.getString("namess", null);

        Log.d("profileid", "" + profileid);

        item_ratingBar = (RatingBar) v.findViewById(R.id.item_ratingBar);
        saveclick = (RobotoTextView) v.findViewById(R.id.saveclick);
        dialclick = (RobotoTextView) v.findViewById(R.id.dialclick);
        cat_name = (RobotoTextView) v.findViewById(R.id.cat_name);
        foodcount = (RobotoTextView) v.findViewById(R.id.foodcount);
        // reviewadd = (ImageView) v.findViewById(R.id.reviewclick);
        thingstocount = (RobotoTextView) v.findViewById(R.id.thingstocount);
        prayerscount = (RobotoTextView) v.findViewById(R.id.prayerscount);
        prayers = (RelativeLayout) v.findViewById(R.id.prayers);
        thingstodo = (RelativeLayout) v.findViewById(R.id.thingstodo);
        food = (RelativeLayout) v.findViewById(R.id.food);
        cat_description = (RobotoTextView) v.findViewById(R.id.cat_description);
        title_info = (RobotoTextView) v.findViewById(R.id.title_info);
        address_first = (RobotoTextView) v.findViewById(R.id.address_first);
        address_second = (RobotoTextView) v.findViewById(R.id.address_second);
        call = (RobotoTextView) v.findViewById(R.id.call);
        web = (RobotoTextView) v.findViewById(R.id.web);
        openinghours = (RobotoTextView) v.findViewById(R.id.openinghours);
        closinghours = (RobotoTextView) v.findViewById(R.id.closinghours);
        vserves = (RobotoTextView) v.findViewById(R.id.vserves);
        serves = (RobotoTextView) v.findViewById(R.id.serves);
        slider = (SliderLayout) v.findViewById(R.id.slider);
        mapimage = (NetworkImageView) v.findViewById(R.id.mapimage);
        saveclick = (RobotoTextView) v.findViewById(R.id.saveclick);

        Bundle bundle = this.getArguments();

        ii = Sessiondata.getInstance().getUpdateresult();
        if (ii == 1) {


            backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
            backclick.setVisibility(View.VISIBLE);

            backclick.setOnClickListener(this);





            Log.d("Ratting", "" + Sessiondata.getInstance().getMapratingclick());
            Toast.makeText(getActivity(),Sessiondata.getInstance().getMapratingclick(),Toast.LENGTH_SHORT);
            try {
                Float rate = Float.parseFloat(Sessiondata.getInstance().getMapratingclick());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);
            }
            item_ratingBar.setEnabled(false);
            norsnno = Sessiondata.getInstance().getMapclicksno();
            norname = Sessiondata.getInstance().getMapclickname();
            norfbbid = profileid;
            viewdetails = new ArrayList<viewclick>();
            if (null != Sessiondata.getInstance().getCategoryIDmapclick()) {
                if (Sessiondata.getInstance().getCategoryIDmapclick().equalsIgnoreCase("1")) {
                    title_info.setText("Restaurant info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.food_48, 0);
                } else if (Sessiondata.getInstance().getCategoryIDmapclick().equalsIgnoreCase("2")) {
                    title_info.setText("Prayers info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.prayers_48, 0);
                } else if (Sessiondata.getInstance().getCategoryIDmapclick().equalsIgnoreCase("3")) {
                    title_info.setText("Attraction info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.things_todo_48, 0);
                } else if (Sessiondata.getInstance().getCategoryIDmapclick().equalsIgnoreCase("4")) {
                    title_info.setText("Neighbourhood info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.neighbourhood_48, 0);
                } else {
                    title_info.setText("My trip info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.prayers_48, 0);
                }
            }
            cat_name.setText(Sessiondata.getInstance().getNamemapclick());
            cat_description.setText(Sessiondata.getInstance().getDescriptionmapclick());
            address_first.setText(Sessiondata.getInstance().getMapclickaddress());
            address_second.setText(Sessiondata.getInstance().getCitymapclick() + " - " + Sessiondata.getInstance().getPostalclickmapclick());
            call.setText(Sessiondata.getInstance().getPhonemapclick());
            web.setText(Sessiondata.getInstance().getWebsitemapclick());
            openinghours.setText(Sessiondata.getInstance().getOpenhrsmapclick());
            closinghours.setText(Sessiondata.getInstance().getCloshrsmapclick());
            if (null != Sessiondata.getInstance().getFoodclassificationdetails() && Sessiondata.getInstance().getFoodclassificationdetails().size() > 0) {
                serves.setVisibility(View.VISIBLE);
                vserves.setVisibility(View.VISIBLE);
                List<foodclassification> Foodclassification = Sessiondata.getInstance().getFoodclassificationdetails();
                for (foodclassification s : Foodclassification) {
                    serves.setText(Sessiondata.getInstance().getTagmapclick());
                }

            } else {
                vserves.setVisibility(View.GONE);
                serves.setVisibility(View.GONE);
            }


            if (null != Sessiondata.getInstance().getPhotosdetails() && Sessiondata.getInstance().getPhotosdetails().size() > 0) {
                List<photoss> Photos = Sessiondata.getInstance().getPhotosdetails();
                for (photoss s : Photos) {
                    HashMap<String, String> url_maps = new HashMap<String, String>();
                    url_maps.put("name", s.getPhotourl());
                    url_maps.put("id", s.getId());
                    wordList.add(url_maps);
                }
                slider.setVisibility(View.VISIBLE);
                slider.removeAllSliders();
                slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                slider.setCustomAnimation(new DescriptionAnimation());
                slider.setDuration(5000);
                slider.addOnPageChangeListener(this);
                set(wordList);
            } else {
                slider.setVisibility(View.GONE);
            }

            try {
                mapimage.setImageUrl("http://maps.googleapis.com/maps/api/staticmap?zoom=14&size=240x240&markers=size:mid|color:red|" + Sessiondata.getInstance().getLatitudemapclick() + "," + Sessiondata.getInstance().getLongitudeemapclick() + "&sensor=false", mImageLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
            t2.setMovementMethod(LinkMovementMethod.getInstance());
            v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapcliclShowWebFragment();
                }
            });


            v.findViewById(R.id.mapimage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapclickmapView();
                }
            });

            v.findViewById(R.id.mapclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapclickmapView();
                }
            });


            v.findViewById(R.id.dialclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + Sessiondata.getInstance().getPhonemapclick()));
                    startActivity(intent);
                }
            });




            cc = Sessiondata.getInstance().getCtmvalue();


            v.findViewById(R.id.saveclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null == cc.getTripid() || cc.getTripid().equals(null)) {

                        MytripDetFragmentmap(cc);
                        // MytripDetFragment(C);
                    } else {
                        showViewDetFragmentmap(cc);
                    }

                }
            });

            v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // Sessiondata.getInstance().setNearstaus(1);
                    NearByelementsModel n = (NearByelementsModel) v.getTag();
                    CreatedTripModel cc = Sessiondata.getInstance().getCtmvalue();
                    ;
                    cc.setCategoryID("3");
                    cc.setCategorytype("Things to do info");
                    cc.setN(n);
                    showmapguidFragment(cc);
                }
            });


            v.findViewById(R.id.food).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Sessiondata.getInstance().setNearstaus(1);
                    NearByelementsModel n = (NearByelementsModel) v.getTag();
                    CreatedTripModel cc = Sessiondata.getInstance().getCtmvalue();
                    ;
                    cc.setCategoryID("1");
                    cc.setCategorytype("Restaurant info");
                    cc.setN(n);
                    showmapguidFragment(cc);
                }
            });


            v.findViewById(R.id.prayers).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Sessiondata.getInstance().setNearstaus(1);
                    NearByelementsModel n = (NearByelementsModel) v.getTag();
                    CreatedTripModel cc = Sessiondata.getInstance().getCtmvalue();
                    ;
                    cc.setCategoryID("2");
                    cc.setCategorytype("Prayers info");
                    cc.setN(n);
                    showmapguidFragment(cc);
                }
            });


            CategoryItem_list_Fragment.clickeventcheck = 0;
            CategoryItem_list_NearbyelementFragment.clickeventcheck = 0;
            CategoryItem_searchlist_Fragment.clickeventcheck = 0;


            List<NearByelementsModel> nearByelementsModels = Sessiondata.getInstance().getNearByelementsModeldetails();

            Log.d("catid", "" + nearByelementsModels.get(0).getCategoryid());
            Log.d("distrit", "" + nearByelementsModels.get(0).getDistrict());


            if (nearByelementsModels.size() > 0) {
                NearByelementsModel nem1 = nearByelementsModels.get(0);
                NearByelementsModel nem2 = nearByelementsModels.get(1);
                NearByelementsModel nem3 = nearByelementsModels.get(2);
                NearByelementsModel nem4 = nearByelementsModels.get(3);
                foodcount.setText(" ( " + nem1.getTotalno() + " ) ");
                thingstocount.setText(" ( " + nem3.getTotalno() + " ) ");
                prayerscount.setText(" ( " + nem2.getTotalno() + " ) ");
                Log.d("Foodlist1", "" + nem1.getTotalno());
                Log.d("thingstodo1", "" + nem3.getTotalno());
                Log.d("prayers1", "" + nem2.getTotalno());
                if (nem2.getTotalno().equals(0)) {
                    prayers.setClickable(false);

                }


                food.setTag(nem1);
                thingstodo.setTag(nem3);
                prayers.setTag(nem2);

                Log.d("Food1", "" + nem1);
                Log.d("thing1s", "" + nem3);
                Log.d("pray1", "" + nem2);
            } else {
                foodcount.setText("(" + 0 + " ) ");
                thingstocount.setText("(" + 0 + " ) ");
                prayerscount.setText(" (" + 0 + " ) ");
                food.setTag(new NearByelementsModel("", "", "0"));
                thingstodo.setTag(new NearByelementsModel("", "", "0"));
                prayers.setTag(new NearByelementsModel("", "", "0"));
            }


/////////////////////dataelementnot map
            userfbemailid = Sessiondata.getInstance().getLoginfbid();
            dateelemintidval = Sessiondata.getInstance().getSnocreatenewtrip();
            dataelementviewing();
            guidid = Sessiondata.getInstance().getGuideledataid();
            guidanalysingview();



        } else {

            Bundle bundl = this.getArguments();
int ftp = Sessiondata.getInstance().getFtplistval();

if(ftp == 4)
{
    C = (Categorylistmodel) bundl.getSerializable(ExplorenewThingsfragment.EXTRATRIPINFO);
    cc = C.getCtm();
}
else if(ftp == 5)
{
    C = (Categorylistmodel) bundl.getSerializable(NewUiFoodfragment.EXTRATRIPINFO);
    cc = C.getCtm();

}

else if(ftp == 6)
{
    C = (Categorylistmodel) bundl.getSerializable(NewuiSpecialdealsFragment.EXTRATRIPINFO);
    cc = C.getCtm();

}


else {
    C = (Categorylistmodel) bundl.getSerializable(CategoryItem_list_Fragment.EXTRATRIPINFO);
    cc = C.getCtm();
}
            if (C == null) {

            }
            Log.d("listwadda", "" + C);

         //  cc = C.getCtm();




          //  cc = Sessiondata.getInstance().getCtmvalue();

            // Log.d("getTripidca", "" + cc.getTripid());

            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);

            }

            item_ratingBar.setEnabled(false);


            norsnno = C.getSno();
            Log.d("cmdsno", "" + C.getSno());
            norname = C.getName();
            Log.d("cmdname", "" + C.getName());
            norfbbid = profileid;
            Log.d("fbid", "" + norfbbid);

            viewdetails = new ArrayList<viewclick>();
            // viewriewcomment();


            if (null != C.getCategoryID()) {
                if (C.getCategoryID().equalsIgnoreCase("1")) {
                    title_info.setText("Restaurant info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.food_48, 0);
                } else if (C.getCategoryID().equalsIgnoreCase("2")) {
                    title_info.setText("Prayers info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.prayers_48, 0);
                } else if (C.getCategoryID().equalsIgnoreCase("3")) {
                    title_info.setText("Attraction info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.things_todo_48, 0);
                } else if (C.getCategoryID().equalsIgnoreCase("4")) {
                    title_info.setText("Neighbourhood info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.neighbourhood_48, 0);
                } else {
                    title_info.setText("My trip info");
                    title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.prayers_48, 0);
                }
            }

            cat_name.setText(C.getName());
            cat_description.setText(C.getDescription());
            address_first.setText(C.getAddress());
            address_second.setText(C.getCity() + " - " + C.getPostalcode());
            call.setText(C.getPhone());
            web.setText(C.getWebsite());
            openinghours.setText(C.getOpenhrs());
            closinghours.setText(C.getClosehrs());
            Log.d("CSNOLIS", "" + C.getSno());
            new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                serves.setVisibility(View.VISIBLE);
                vserves.setVisibility(View.VISIBLE);
                List<foodclassification> Foodclassification = C.getFoodclassification();
                for (foodclassification s : Foodclassification) {
                    serves.setText(C.getTags());
                }

            } else {
                vserves.setVisibility(View.GONE);
                serves.setVisibility(View.GONE);
            }

            if (null != C.getPhotos() && C.getPhotos().size() > 0) {
                List<photoss> Photos = C.getPhotos();
                for (photoss s : Photos) {
                    HashMap<String, String> url_maps = new HashMap<String, String>();
                    url_maps.put("name", s.getPhotourl());
                    url_maps.put("id", s.getId());
                    wordList.add(url_maps);
                }
                slider.setVisibility(View.VISIBLE);
                slider.removeAllSliders();
                slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                slider.setCustomAnimation(new DescriptionAnimation());
                slider.setDuration(5000);
                slider.addOnPageChangeListener(this);
                set(wordList);
            } else {
                slider.setVisibility(View.GONE);
            }


            try {
                mapimage.setImageUrl("http://maps.googleapis.com/maps/api/staticmap?zoom=14&size=240x240&markers=size:mid|color:red|" + C.getLatitude() + "," + C.getLongitude() + "&sensor=false", mImageLoader);
                  /*  new DownloadImageTask(holder.imgView)
                            .execute(webimg);*/
                // Picasso.with(context).load("http://maps.googleapis.com/maps/api/staticmap?zoom=14&size=240x240&markers=size:mid|color:red|10.9939584,76.9728637&sensor=false").into(holder.imgView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
            t2.setMovementMethod(LinkMovementMethod.getInstance());
            v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowWebFragment();
                }
            });


            v.findViewById(R.id.mapimage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapView();
                }
            });

            v.findViewById(R.id.mapclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapView();
                }
            });

            v.findViewById(R.id.dialclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + C.getPhone()));
                    startActivity(intent);
                }
            });
            v.findViewById(R.id.saveclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //cc.getTripid();

                    if (null == cc.getTripid() || cc.getTripid().equals(null)) {
                        MytripDetFragment(C);
                    } else {
                        showViewDetFragment(C);
                    }

                }
            });


            v.findViewById(R.id.prayers).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NearByelementsModel n = (NearByelementsModel) v.getTag();
                    CreatedTripModel cc = C.getCtm();
                    cc.setCategoryID("2");
                    cc.setCategorytype("Prayers info");
                    cc.setN(n);
                    showguideFragment(cc);
                }
            });

            v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NearByelementsModel n = (NearByelementsModel) v.getTag();
                    CreatedTripModel cc = C.getCtm();
                    cc.setCategoryID("3");
                    cc.setCategorytype("Things to do info");
                    cc.setN(n);
                    showguideFragment(cc);
                }
            });

            v.findViewById(R.id.food).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NearByelementsModel n = (NearByelementsModel) v.getTag();
                    CreatedTripModel cc = C.getCtm();

                    cc.setCategoryID("1");
                    cc.setCategorytype("Restaurant info");
                    cc.setN(n);
                    showguideFragment(cc);
                }
            });



            CategoryItem_list_Fragment.clickeventcheck = 0;
            CategoryItem_list_NearbyelementFragment.clickeventcheck = 0;
            CategoryItem_searchlist_Fragment.clickeventcheck = 0;


/////////////////////dataelementnot map
            userfbemailid = Sessiondata.getInstance().getLoginfbid();
            dateelemintidval = Sessiondata.getInstance().getSnocreatenewtrip();
            dataelementviewing();


        }


        return v;
    }


    // called when Tapjoy connect call succeed

    public void onConnectSuccess() {

    }
    // called when Tapjoy connect call failed

    public void onConnectFailure() {
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    //session end
    @Override
    public void onStop() {

        super.onStop();
    }


    private void showdialogreview() {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.reviewcomment, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.reviewcmd);

        final String empty = editText.getText().toString();
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        changpas = editText.getText().toString();
                        Log.d("password", "" + changpas);


                        Normalcmd();

                    }

                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();


                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }

    public void set(ArrayList<HashMap<String, String>> url_maps) {
        slider.removeAllSliders();
        for (int i = 0; i <= url_maps.size() - 1; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map = url_maps.get(i);
            TextSliderView textSliderView = new TextSliderView(_A);
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .image(map.get("name"))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            String s = map.get("name");
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", map.get("id"));
            slider.addSlider(textSliderView);
        }

    }








    private void dataelementviewing() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,DETAILSDATAELEMENTVIEWING,
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
                            //Toast.makeText(getActivity(),"page"+pageno,Toast.LENGTH_SHORT).show();
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
                params.put("user_id",userfbemailid);
                params.put("dataelement_id",dateelemintidval);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
















    private void Normalcmd() {
        final String username = changpas;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NORMALREVIEWCMD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            changepinstatus = userObject.getInt("status");
                            changepinmsg = userObject.getString("msg");

                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (changepinstatus == 1) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), changepinmsg, Toast.LENGTH_LONG).show();

                        } else if (changepinstatus == 0) {
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
                params.put(KEY_DATAELEMENT, norsnno);
                params.put(KEY_FBID, norfbbid);
                params.put(KEY_NAME, norname);
                params.put(KEY_COMMENTS, username);
                Log.d("parameterparssing", "" + params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }





    private void guidanalysingview() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,GUIDVIEWSHOWING,
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
                            //Toast.makeText(getActivity(),"page"+pageno,Toast.LENGTH_SHORT).show();
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
                params.put("user_id", userfbemailid);
                params.put("dataelement_id",dateelemintidval);
                params.put("guide_id",guidid);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
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

    public View_itemInfo_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onSliderClick(BaseSliderView slider) {

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

    private class WebPageTask extends AsyncTask<Void, List<NearByelementsModel>, List<NearByelementsModel>> {
        ProgressDialog d = new ProgressDialog(_A);
        List<NearByelementsModel> NEM = new ArrayList<>();
        String did;

        private WebPageTask(String did) {
            this.did = did;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            d.setMessage("Please wait...");
            d.show();
        }
        @Override
        protected List<NearByelementsModel> doInBackground(Void... params) {
            List<NearByelementsModel> NEM = new ArrayList<>();
            ApiCall a = retrofit.create(ApiCall.class);
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<NearbyelementResponse> call = a.GetNearByelements(did);

            // Fetch and print a list of the contributors to the library.
            NearbyelementResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {

                if (c.Status == 1) {
                    if (c.Nearbyelement.size() > 0) {

                        for (NearbyelementResponse.Elements dd : c.Nearbyelement) {
                            NEM.add(new NearByelementsModel(dd.district, dd.categoryid, dd.totalno));
                        }
                    }
                    d.dismiss();
                }
            }
            return NEM;
        }


        @Override
        protected void onPostExecute(List<NearByelementsModel> nearByelementsModels) {
            super.onPostExecute(nearByelementsModels);
            if (nearByelementsModels.size() > 0) {
                NearByelementsModel nem1 = nearByelementsModels.get(0);
                NearByelementsModel nem2 = nearByelementsModels.get(1);
                NearByelementsModel nem3 = nearByelementsModels.get(2);
                NearByelementsModel nem4 = nearByelementsModels.get(3);
                foodcount.setText(" ( " + nem1.getTotalno() + " ) ");
                thingstocount.setText(" ( " + nem3.getTotalno() + " ) ");
                prayerscount.setText(" ( " + nem2.getTotalno() + " ) ");
                Log.d("Foodlist1", "" + nem1.getTotalno());
                Log.d("thingstodo1", "" + nem3.getTotalno());
                Log.d("prayers1", "" + nem2.getTotalno());
                food.setTag(nem1);
                thingstodo.setTag(nem3);
                prayers.setTag(nem2);
                if (prayerscount.equals(0)) {
                    prayers.setClickable(false);

                }
                Log.d("Food1", "" + nem1);
                Log.d("thing1s", "" + nem3);
                Log.d("pray1", "" + nem2);
            } else {
                foodcount.setText(" ( " + 0 + " ) ");
                thingstocount.setText(" ( " + 0 + " ) ");
                prayerscount.setText(" ( " + 0 + " ) ");
                food.setTag(new NearByelementsModel("", "", "0"));
                thingstodo.setTag(new NearByelementsModel("", "", "0"));
                prayers.setTag(new NearByelementsModel("", "", "0"));
            }
        }
    }
    public void Categorylist(final View vs, String subcatid) throws IOException {
        List<Categorylistmodel> items = new ArrayList<>();

        Boolean success = internet.isNetworkConnected(_A);
        if (success) {
        }
    }


    private void viewriewcomment() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, VIEWCOMMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("status");
                            Log.d("status", "" + viewstatus);
                            JSONArray jsonarray = userObject.getJSONArray("msg");

                            if (jsonarray.equals(null)) {

                            } else {
                                for (int j = 0; j < jsonarray.length(); j++) {
                                    JSONObject obj1 = jsonarray.getJSONObject(j);
                                    viewclick datas1 = new viewclick();
                                    datas1.setSno(obj1.getString("sno"));
                                    Log.d("setSno", "" + datas1.getSno());
                                    datas1.setFbid(obj1.getString("fbid"));
                                    Log.d("setFbid", "" + datas1.getFbid());
                                    datas1.setName(obj1.getString("name"));
                                    Log.d("setName", "" + datas1.getName());
                                    datas1.setComments(obj1.getString("comments"));
                                    Log.d("setComments", "" + datas1.getComments());
                                    datas1.setTs(obj1.getString("ts"));
                                    Log.d("setTs", "" + datas1.getTs());
                                    viewdetails.add(datas1);
                                    if (viewdetails == null) {
                                    } else {
                                        Sessiondata.getInstance().setViewdetails(viewdetails);
                                        Log.d("viewdetaillist", "" + Sessiondata.getInstance().getViewdetails());
                                    }
                                }

                            }
                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                            progressDialog.dismiss();
                            emphistorydetails = Sessiondata.getInstance().getViewdetails();
                            if (emphistorydetails != null) {
                                Log.d("hlistarray", "" + Sessiondata.getInstance().getViewdetails());
                            } else {

                            }
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
                params.put(KEY_DATAELEMENT, norsnno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }

    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<viewclick> sing_in_datas;

        public emphistoryAdapter(Context con, ArrayList<viewclick> singindatas) {
            this.context = con;
            this.sing_in_datas = singindatas;
            mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return sing_in_datas.size();
        }

        @Override
        public Object getItem(int position) {
            return sing_in_datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (null == view) {
                view = mLayoutInflater.inflate(R.layout.hlistinflate, null);
                viewHolder = new ViewHolder();


                viewHolder.name = (TextView) view.findViewById(R.id.reviewname);

                viewHolder.reviewcomment = (TextView) view.findViewById(R.id.reviewcomment);


                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();


            viewHolder.name.setText(sing_in_datas.get(position).getName());
            viewHolder.reviewcomment.setText(sing_in_datas.get(position).getComments());


            return view;
        }
    }
    static class ViewHolder {
        TextView name;
        TextView reviewcomment;
    }}
