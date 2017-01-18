package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.NearbyelementResponse;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.fragment.SaveTrip_Fragment;
import com.hhwt.travelplanner.fragment.Website_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
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

/**
 * Created by jeyavijay on 26/03/16.
 */
public class MapView_itemInfo_Fragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,
        DatePickerDialog.OnDateSetListener {
    private RobotoTextView mapclick, saveclick, dialclick, cat_name, cat_description, title_info, address_first, address_second, call, web, openinghours, closinghours, serves, vserves, foodcount, thingstocount, prayerscount;
    SliderLayout slider;
    private Boolean _startDate;
    private RelativeLayout prayers, thingstodo, food;
    Categorylistmodel C;
    NetworkImageView mapimage;
    ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
    public static final String EXTRAlinkINFO = "url";
    public static final String EXTRAVIEWINFO = "Viewinfo";
    public RatingBar item_ratingBar;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    int ii;
    String snoid = "";
    CreatedTripModel cc;
    String titlename = "";
    String ratingview = "";
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    public static final String EXTRATRIPINFO = "Viewinfo";
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    int locationCount = 0;
    public MapView_itemInfo_Fragment() {
    }

    public void ShowWebFragment() {
        Fragment fr = new Website_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAlinkINFO, C.getWebsite());
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public void mapView() {
        Uri gmmIntentUri = Uri.parse("geo:" + C.getLatitude() + "," + C.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(_A.getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
    public void showViewDetFragment(Categorylistmodel viewModel) {
        Fragment fr = new SaveTrip_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    public void MytripDetFragment(Categorylistmodel viewModel) {
        Fragment fr = new MyTrip_Fragment_explore();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, viewModel);
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mapviewpage, container, false);
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
        item_ratingBar = (RatingBar) v.findViewById(R.id.item_ratingBar);
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
        saveclick = (RobotoTextView) v.findViewById(R.id.saveclick);
        Bundle bundle = this.getArguments();
        ii = Sessiondata.getInstance().getUpdateresult();
        if (ii == 1) {
            locationCount = Sessiondata.getInstance().getFoodcatgory().size();
            for (int i = 0; i < locationCount; i++) {
                titlename = Sessiondata.getInstance().getFoodcatgory().get(i).getName();
                ratingview = Sessiondata.getInstance().getFoodcatgory().get(i).getRating();
                snoid = Sessiondata.getInstance().getFoodcatgory().get(i).getSno();
            }
            new WebPageTask(snoid).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            C = (Categorylistmodel) bundle.getSerializable(Mapvinavivisible.EXTRAVIEWINFO);
            cc = C.getCtm();
            item_ratingBar.setRating(Float.parseFloat(C.getRating()));
            item_ratingBar.setEnabled(false);

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
                    showguideFragment(cc);}
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
        }
        return v;
    }

    public void onBackPressed() {

        Intent ne = new Intent(getActivity(), Mapvinavivisible.class);
        startActivity(ne);
    }

    public void set(ArrayList<HashMap<String, String>> url_maps) {
        slider.removeAllSliders();
        for (int i = 0; i <= url_maps.size() - 1; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map = url_maps.get(i);
            TextSliderView textSliderView = new TextSliderView(_A);
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

    }@Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }


    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    public void onPageSelected(int position) {
    }
    public void onPageScrollStateChanged(int state) {

    }
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
            Call<NearbyelementResponse> call = a.GetNearByelements(did);
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
            }}}
    public void Categorylist(final View vs, String subcatid) throws IOException {
        List<Categorylistmodel> items = new ArrayList<>();

        Boolean success = internet.isNetworkConnected(_A);
        if (success) {
        }}}
