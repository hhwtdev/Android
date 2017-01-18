package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
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
import com.hhwt.travelplanner.Retrofit.TripRegisterResponse;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.CategoryItem_list_Fragment;
import com.hhwt.travelplanner.activity.CategoryItem_list_NearbyelementFragment;
import com.hhwt.travelplanner.activity.CategoryItem_searchlist_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.Categorylistmodelmytrip;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class View_mytripInfo_Fragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,
        DatePickerDialog.OnDateSetListener {
    private RobotoTextView mapclick, saveclick, dialclick, cat_name, cat_description, title_info, address_first, address_second, call, web, openinghours, closinghours, serves, vserves, foodcount, thingstocount, prayerscount;
    SliderLayout slider;
    private RobotoTextView end_date;
    private Boolean _startDate;
    private RelativeLayout prayers, thingstodo, food;
    Categorylistmodelmytrip C;
    NetworkImageView mapimage;
    ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
    public static final String EXTRAlinkINFO = "url";
    public static final String EXTRAVIEWINFO = "Viewinfo";

    public RatingBar item_ratingBar;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;

    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    public static final String EXTRATRIPINFO = "Viewinfo";
    CreatedTripModel cc;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

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
        Fragment fr = new CategoryItem_list_NearbyelementFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    public void mapView() {
        Intent mapview=new Intent(getActivity(),CheckLocationActivity.class);
        Bundle b=new Bundle();
        b.putString("lat", C.getLatitude());
        b.putString("long",C.getLongitude());
        b.putString("name",C.getName());
        b.putString("address",C.getAddress());

        mapview.putExtras(b);
        startActivity(mapview);
    }


    public void showViewDetFragment(Categorylistmodel viewModel) {
        Fragment fr = new SaveTrip_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mytripview_iteminfo, container, false);
       // Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        mapclick = (RobotoTextView) v.findViewById(R.id.mapclick);
        _A = getActivity();
        mRequestQueue = Volley.newRequestQueue(_A);
        Bundle bundle = this.getArguments();
        C = (Categorylistmodelmytrip) bundle.getSerializable(CategoryItem_list_Fragment.EXTRAVIEWINFO);
        cc = C.getCtm();
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
        item_ratingBar = (RatingBar) v.findViewById(R.id.item_ratingBar);
        try {
            Float rate = Float.parseFloat(C.getRating());
            item_ratingBar.setRating(rate);
        } catch (NumberFormatException e) {
            item_ratingBar.setRating(0);

        }
        saveclick = (RobotoTextView) v.findViewById(R.id.saveclick);
        dialclick = (RobotoTextView) v.findViewById(R.id.dialclick);
        cat_name = (RobotoTextView) v.findViewById(R.id.cat_name);
        foodcount = (RobotoTextView) v.findViewById(R.id.foodcount);
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
        } else {
            saveclick.setVisibility(View.VISIBLE);
            title_info.setText("My trip info");
            title_info.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.prayers_48, 0);

        }
        cat_name.setText(C.getName());
        cat_description.setText(C.getDescription());
        address_first.setText(C.getAddress());
        address_second.setText(C.getCity() + " - " + C.getPostalcode());
        call.setText(C.getPhone());
        web.setText(C.getWebsite());
        openinghours.setText(C.getOpenhrs());

        RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowWebFragment();
            }
        });

        v.findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + C.getPhone()));
                startActivity(intent);
            }
        });
        v.findViewById(R.id.address_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView();
            }
        });
        v.findViewById(R.id.address_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView();
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
            public void onClick(final View v) {
                new AlertDialog.Builder(_A)
                        .setTitle("Delete event")
                        .setMessage("Are you sure you want to delete this event?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                try {
                                    new MytripstodeleteTask(v, fb_id, cc.getTripid(), C.getDate(), C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                } catch (Exception e) {

                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

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
        // Show a datepicker when the dateButton is clickedow a datepicker when the dateButton is clicked
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        openinghours.setText(C.getOpenhrs());
        closinghours.setText(C.getClosehrs());
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
    }

    public void set(ArrayList<HashMap<String, String>> url_maps) {
        slider.removeAllSliders();
        for (int i = 0; i <= url_maps.size() - 1; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map = url_maps.get(i);
            TextSliderView textSliderView = new TextSliderView(_A);
            // initialize a SliderLayout
            textSliderView
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

    public View_mytripInfo_Fragment() {
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

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

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
                food.setTag(nem1);
                thingstodo.setTag(nem3);
                prayers.setTag(nem2);

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


    private class MytripstodeleteTask extends AsyncTask<Void, TripRegisterResponse, TripRegisterResponse> {

        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String fbid, tripid, Selecteddate, ID;
        View vs;


        private MytripstodeleteTask(final View vs, String fbid, String tripid, String Selecteddate, String ID) {
            this.fbid = fbid;
            this.tripid = tripid;
            this.Selecteddate = Selecteddate;
            this.ID = ID;
            this.vs = vs;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d.setMessage("Updating view ...");
            d.show();
        }

        @Override
        protected TripRegisterResponse doInBackground(Void... params) {
            ApiCall a = retrofit.create(ApiCall.class);
            // asynchronous
            // Create a call instance for looking up Retrofit contributors.
            Call<TripRegisterResponse> call = a.DeleteTripitem(fbid, tripid, Selecteddate, ID);

            // Fetch and print a list of the contributors to the library.
            TripRegisterResponse c = null;
            try {
                c = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(TripRegisterResponse c) {
            super.onPostExecute(c);
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    try {
                        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getFragmentManager().popBackStack();
                        }
                    } catch (Exception we) {

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
}
