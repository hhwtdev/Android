package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Retrofit.NearbyelementResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.CheckLocationActivity;
import com.hhwt.travelplanner.fragment.SaveTrip_Fragment;
import com.hhwt.travelplanner.fragment.Tourlistdeatails;
import com.hhwt.travelplanner.fragment.Website_Fragment;
import com.hhwt.travelplanner.model.Categorylist;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.Imageval;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.categorylistvalues;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 30/09/16.
 */
public class NewUiView_itemInfo_Fragment extends Fragment implements View.OnClickListener,BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener
       {
    ImageView backclick,toolbarsearch;
    TextView apptitle;
    SliderLayout slider;
    public static Activity _A;
    Categorylistmodel C;
           ArrayList<Tourdetails> tourdetailsvalue;

           categorylistvalues guidview;

           emphistoryAdapter  resvalue;


     /*      private static final String REVIEWVAL = "www.hhwt.tech/hhwt_webservice/review list.php";*/
         //  private static final String REVIEWVAL = "http://hhwt.tech/hhwt_webservice/review list.php";



           private static final String REVIEWVAL = "http://hhwt.tech/hhwt_webservice/review_list.php";

LinearLayout fivebar,fourbar,threebar,twobar,onebar;



LinearLayout reviewoval;


int hereview  = 45;
           int fivew = 200;
           int fourw = 150;
           int threew = 100;
           int twow = 75;
           int onew = 50;
           int ozeross = 0;

        //   ListView simlist;


           ArrayList<Categorylist> values;



           String rsnoval,rsfb_id;

           public static final String KEY_SNO ="sno";


           public static final String KEY_FBID ="fb_id";



           List<tourdetails> tourdetailsvalues;
           ImageView myImageView,secoundmyImageView;
    CreatedTripModel cc;
           ArrayList<Categorylistmodel> foodanddrinktourdetailsvalue;
           ArrayList<Imageval> imagedetails;
           public static final String EXTRATRIPINFO = "Viewinfo";


           LinearLayout overallreviewvisible,userprofileclick;


           ArrayList<Categorylistmodel> thinkstodotourdetailsvalue;


           ImageView userprofileimage;

           TextView userpronames;
TextView reviewcount;

TextView specialdealcontent1,specaildealrate1,specialdealcontent2,specialdealrate2;
           String tdetailsimage,tdetailsrate,tdetailsreviews,tdescontent,tdeshoverview,tdlosoverview,tsesinquery;
    ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
    LinearLayout linearvalbottom;
           private RelativeLayout prayers, thingstodo, food;
           RelativeLayout specialfirstitem;
           LinearLayout specialdealavailable,thingstodoattractionavailable,foodanddrinksavailable,prayearspacealavailable,improvelisting;
           TextView cat_description,clocktime,closereson;
           RobotoTextView address_first,serves,call,web;
           LinearLayout vserves,mapclick;
           public CreatedTripModel newtrip;
          // dialclickcall;
           public static final String EXTRAlinkINFO = "url";
           public static final String EXTRAVIEWINFO = "Viewinfo";
TextView thingstocount,foodcount,prayerscount;
TextView countryapptitle;
           ImageView filter;
           SquareImageView attrationimageone,attrationimagetwo;
String CategoryID,districtval;
TextView specialdealsall;
           String message;
           int stag;;
           String average,oner,twor,threer,fourr,fiver,totalreviews;
           ArrayList<Categorylist> vatlistthings;



           RelativeLayout specialfirstview,specialsewcondview;
           LinearLayout foodtagview;

FrameLayout secondattraction,foodsecondcount,prayersecondcount,thigsfrag1,foodsfrag1,prayerfirst;
          ProgressDialog progressDialog;


           RobotoTextView attrationtitleone,attrationsubtileone,attrationaddressone,attrationtitletwo,attrationsubtiletwo,attrationaddresstwo,foodtag,nature;
           RatingBar attrationratbarone,attrationratbartwo,reviewbigratingstar;

           RobotoTextView foodtitleone,foodsubtileone,foodaddressone;
           RatingBar  foodratbarone;

           TextView ratingshowtxt,totalrevicecount;

           SquareImageView  foodimageone;


           SquareImageView  prayerimageone;
           RobotoTextView prayertitleone,prayersubtileone,prayeraddressone;
           RatingBar prayerratbarone;

TextView prayerfirstfloat,foodsfirstfloat,thingstodofirstfloat,peoplewviewd;

           SearchBox search;
           ArrayList<Categorylistmodel> thingstodonear;
           ArrayList<Categorylistmodel> foodnear;
           ArrayList<Categorylistmodel> prayernear;
           Retrofit retrofit;
           InternetAccessCheck internet;
           String mess;
    int ii;

           String sharlink;
           public RatingBar item_ratingBar;

           public void ShowWebFragment() {
               Fragment fr = new Website_Fragment();
               Bundle bundle = new Bundle();
               bundle.putSerializable(EXTRAlinkINFO, C.getWebsite());
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


           public void showViewDetFragment(Categorylistmodel viewModel) {
               Sessiondata.getInstance().setMtrip(0);
               Fragment fr = new SaveTrip_Fragment();
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

               Sessiondata.getInstance().setDataelementtripis("1");
               Fragment fr = new CategoryItem_list_NearbyelementFragment();
               Bundle bundle = new Bundle();
               bundle.putSerializable(EXTRATRIPINFO, ctm);
               fr.setArguments(bundle);
               FragmentChangeListener fc = (FragmentChangeListener) getActivity();
               fc.replaceFragment(fr);
           }






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.newuiviewfragment,container,false);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

//        Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);
        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);

        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);


        //simlist = (ListView) v.findViewById(R.id.simlist);

      //  reviewoval = (LinearLayout) v.findViewById(R.id.reviewoval);







       // ratingshowtxt = (TextView) v.findViewById(R.id.ratingshowtxt);
      //  totalrevicecount = (TextView) v.findViewById(R.id.totalrevicecount);
      //  onebar = (LinearLayout) v.findViewById(R.id.onebar);
       // twobar = (LinearLayout) v.findViewById(R.id.twobar);
       // threebar = (LinearLayout) v.findViewById(R.id.threebar);
     //   fourbar = (LinearLayout) v.findViewById(R.id.fourbar);
       // fivebar = (LinearLayout) v.findViewById(R.id.fivebar);


       reviewcount = (TextView) v.findViewById(R.id.reviewcount);
        specialdealavailable = (LinearLayout) v.findViewById(R.id.specialdealavailable);
        thingstodoattractionavailable = (LinearLayout) v.findViewById(R.id.attractionavailable);
        foodanddrinksavailable = (LinearLayout) v.findViewById(R.id.foodanddrinksavailable);
        specialdealcontent1 = (TextView) v.findViewById(R.id.myImageViewText);
        specaildealrate1 = (TextView) v.findViewById(R.id.kwr);
        specialdealcontent2 = (TextView) v.findViewById(R.id.secoundmyImageViewText);
        specialdealrate2 = (TextView) v.findViewById(R.id.secoundkwr);
        specialfirstitem = (RelativeLayout) v.findViewById(R.id.relativelayout);
        prayearspacealavailable = (LinearLayout) v.findViewById(R.id.prayearspacealavailable);
        improvelisting = (LinearLayout) v.findViewById(R.id.improvelisting);
        improvelisting.setOnClickListener(this);
        prayerfirst = (FrameLayout) v.findViewById(R.id.prayerfirst);

        foodtagview = (LinearLayout) v.findViewById(R.id.foodtagview);
        specialdealsall = (TextView) v.findViewById(R.id.specialdealsall);
        specialfirstview = (RelativeLayout) v.findViewById(R.id.relativelayout);
        specialsewcondview = (RelativeLayout) v.findViewById(R.id.secondtourrelativelayout);
     //   reviewbigratingstar = (RatingBar) v.findViewById(R.id.item_ratingBarreviewstar);

        imagedetails = new ArrayList<Imageval>();
        prayerfirstfloat = (TextView) v.findViewById(R.id.prayerfirstfloat);



     //   overallreviewvisible = (LinearLayout) v.findViewById(R.id.overallreview);
       // userprofileclick = (LinearLayout) v.findViewById(R.id.profileclick);


      //  userprofileimage = (ImageView) v.findViewById(R.id.profile_image);
       // userpronames = (TextView) v.findViewById(R.id.namelist);






    /*    peoplewviewd = (TextView) v.findViewById(R.id.peoplewviewd);
        peoplewviewd.setOnClickListener(this);*/

        foodsfirstfloat = (TextView) v.findViewById(R.id.foodsfirstfloat);
        thingstodofirstfloat = (TextView) v.findViewById(R.id.thingstodofirstfloat);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        //HorizontalBarChart barChart = (HorizontalBarChart) v.findViewById(R.id.chart);




      //  BarChart barChart = (BarChart) v.findViewById(R.id.chart);

        // HorizontalBarChart barChart= (HorizontalBarChart) v.findViewById(R.id.chart);



        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        internet = new InternetAccessCheck();
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();



        cat_description = (TextView) v.findViewById(R.id.anncontent);
        address_first = (RobotoTextView) v.findViewById(R.id.address_first);
clocktime = (TextView) v.findViewById(R.id.opennowtime);
        closereson = (TextView) v.findViewById(R.id.closereson);
        serves = (RobotoTextView) v.findViewById(R.id.serves);
        vserves = (LinearLayout) v.findViewById(R.id.tagvisible);
        call = (RobotoTextView) v.findViewById(R.id.call);
        web = (RobotoTextView) v.findViewById(R.id.web);
        item_ratingBar = (RatingBar) v.findViewById(R.id.item_ratingBar);
        mapclick = (LinearLayout) v.findViewById(R.id.mapviewclick);
       // dialclickcall = (LinearLayout) v.findViewById(R.id.dialclickcall);
        foodcount = (TextView) v.findViewById(R.id.foodcount);
        thingstocount = (TextView) v.findViewById(R.id.thingstocount);
        prayerscount = (TextView) v.findViewById(R.id.prayerscount);




        prayers = (RelativeLayout) v.findViewById(R.id.prayers);
        thingstodo = (RelativeLayout) v.findViewById(R.id.thingstodo);
        food = (RelativeLayout) v.findViewById(R.id.food);
        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.GONE);
attrationtitleone = (RobotoTextView) v.findViewById(R.id.titlethree);
attrationsubtileone = (RobotoTextView) v.findViewById(R.id.subtitlethree);
attrationratbarone = (RatingBar) v.findViewById(R.id.item_ratingBarthree);
attrationaddressone = (RobotoTextView) v.findViewById(R.id.addressthree);
        thigsfrag1 = (FrameLayout) v.findViewById(R.id.thigsfrag1);

        foodsfrag1 = (FrameLayout) v.findViewById(R.id.foodsfrag1);


        nature = (RobotoTextView) v.findViewById(R.id.nature);


        newtrip = new CreatedTripModel();



        foodtag = (RobotoTextView) v.findViewById(R.id.foodtag);
        attrationimageone = (SquareImageView) v.findViewById(R.id.imagethree);
        secondattraction = (FrameLayout) v.findViewById(R.id.secondattraction);
        attrationtitletwo = (RobotoTextView) v.findViewById(R.id.titletwo);
        attrationsubtiletwo= (RobotoTextView) v.findViewById(R.id.subtitletwo);
        attrationratbartwo = (RatingBar) v.findViewById(R.id.item_ratingBartwo);
        attrationaddresstwo = (RobotoTextView) v.findViewById(R.id.addresstwo);
        attrationimagetwo = (SquareImageView) v.findViewById(R.id.imagetwo);
        foodsecondcount = (FrameLayout) v.findViewById(R.id.foodsecondcount);
        foodtitleone = (RobotoTextView) v.findViewById(R.id.titlefood);
        foodsubtileone = (RobotoTextView) v.findViewById(R.id.subtitlefood);
        foodratbarone = (RatingBar) v.findViewById(R.id.item_ratingBarprayerfood);
        foodaddressone = (RobotoTextView) v.findViewById(R.id.address);
        foodimageone = (SquareImageView) v.findViewById(R.id.imagefood);
        prayersecondcount = (FrameLayout) v.findViewById(R.id.prayersecondcont);
        prayertitleone = (RobotoTextView) v.findViewById(R.id.Peopletitletwo);
        prayersubtileone = (RobotoTextView) v.findViewById(R.id.Peoplesubtitletwo);
        prayerratbarone = (RatingBar) v.findViewById(R.id.Peopleitem_ratingBartwo);
        prayeraddressone = (RobotoTextView) v.findViewById(R.id.Peopleaddresstwo);
        prayerimageone = (SquareImageView) v.findViewById(R.id.Peopleimagetwo);
        _A = getActivity();
        slider = (SliderLayout) v.findViewById(R.id.slider);
        myImageView = (ImageView) v.findViewById(R.id.myImageView);
        secoundmyImageView = (ImageView) v.findViewById(R.id.secoundmyImageView);

//        userprofileclick.setVisibility(View.GONE);

        ii = Sessiondata.getInstance().getUpdateresult();
        if (ii == 1) {






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



            photoss newsItem = Sessiondata.getInstance().getPhotosdetails().get(1);

            if (newsItem.getPhotourl().length() > 0) {
                try {
                    final String s = newsItem.getPhotourl();
                    Picasso.with(_A).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(myImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                        }
                        @Override
                        public void onError() {
                            Picasso.with(_A)
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(myImageView, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                        }
                                    });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }





            photoss newsItemsecond = Sessiondata.getInstance().getPhotosdetails().get(2);

            if (newsItemsecond.getPhotourl().length() > 0) {
                try {
                    final String s = newsItemsecond.getPhotourl();
                    Picasso.with(_A).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(secoundmyImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                        }
                        @Override
                        public void onError() {
                            Picasso.with(_A)
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(secoundmyImageView, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                        }
                                    });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




        }

        else {
            Bundle bundl = this.getArguments();




            int ftp = Sessiondata.getInstance().getFtplistval();

            if(ftp == 4)
            {
                C = (Categorylistmodel) bundl.getSerializable(ExplorenewThingsfragment.EXTRATRIPINFO);
                cc = C.getCtm();
                foodtagview.setVisibility(View.GONE);

Sessiondata.getInstance().setSugesteditlistview(C);



                apptitle.setText(C.getName());


                if(C.getCategoryID().equals("1")){

                    nature.setText(C.getFoodttags());

                }
                else {
                    nature.setText(C.getActivity());
                }



                reviewcount.setText(C.getRating());





                serves.setText(C.getPrice()+" "+C.getPricecurrency());
                cat_description.setText(C.getDescription());
                address_first.setText(C.getAddress());
                clocktime.setText("Opening Hours :  " + C.getOpenhrs() + "\n" + "Closing Hours :  "+C.getClosehrs());

                closereson.setText(C.getTimeremaining());


                new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                    serves.setVisibility(View.VISIBLE);
                    vserves.setVisibility(View.VISIBLE);
                    List<foodclassification> Foodclassification = C.getFoodclassification();
                    for (foodclassification s : Foodclassification) {
                        // serves.setText(C.getTags());
                        //   serves.setText(C.getPrice()+" "+C.getPricecurrency());
                    }

                } else {
                    vserves.setVisibility(View.VISIBLE);
                    //serves.setText(C.getPrice() +" "+C.getPricecurrency());
                    //serves.setVisibility(View.GONE);
                }
                call.setText(C.getPhone());

                //web.setText(C.getWebsite());

                RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
                t2.setMovementMethod(LinkMovementMethod.getInstance());
                v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ShowWebFragment();
                    }
                });



                AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
                try {
                    Float rate = Float.parseFloat(C.getRating());
                    item_ratingBar.setRating(rate);

                } catch (NumberFormatException e) {
                    item_ratingBar.setRating(0);
                }





        /*
            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);

            }*/

               item_ratingBar.setEnabled(true);

                v.findViewById(R.id.mapviewclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapView();
                    }
                });



                /*v.findViewById(R.id.dialclickcall).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + C.getPhone()));
                        startActivity(intent);
                    }
                });*/



                v.findViewById(R.id.saveclickview).setOnClickListener(new View.OnClickListener() {
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


                v.findViewById(R.id.shareclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sharlink = C.getName() + "\n" + C.getWebsite() + "\n" + C.getAddress();
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));


                    }
                });




                if(C.getTours().equals("0")){

                    specialdealavailable.setVisibility(View.GONE);
                    specialfirstview.setVisibility(View.GONE);
                    specialsewcondview.setVisibility(View.GONE);
                }
                else if (C.getTours().equals("1")){

                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.GONE);

                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);


                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            //tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });




                }


                else{
                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.VISIBLE);


                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);







                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            tdetailsreviews = "";
                            //  tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });





                    specialdealcontent2.setText(tourdetailsvalues.get(1).getContent());
                    specialdealrate2.setText("from " + tourdetailsvalues.get(1).getCurrency() + "  " + tourdetailsvalues.get(1).getRate());

                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(1).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(secoundmyImageView);


                    v.findViewById(R.id.secondtourrelativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(1).getImage();
                            tdetailsrate = tourdetailsvalues.get(1).getRate();
                            //  tdetailsreviews = tourdetailsvalues.get(1).getReviews();

                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(1).getContent();
                            tdeshoverview = tourdetailsvalues.get(1).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(1).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(1).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(1).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(1).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(1).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(1).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(1).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(1).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(1).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(1).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(1).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(1).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(1).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(1).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(1).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(1).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(1).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(1).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(1).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(1).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(1).getImage_three());

                            imagedetails.add(img3);

                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(1).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });

                }







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

                /*v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NearByelementsModel n = (NearByelementsModel) v.getTag();
                        CreatedTripModel cc = C.getCtm();
                        cc.setCategoryID("3");
                        cc.setCategorytype("Things to do info");
                        cc.setN(n);
                        showguideFragment(cc);
                    }
                });*/

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










/*


                if(C.getUserreviews().equals("0")) {
                    userprofileclick.setVisibility(View.VISIBLE);

                    //  0 Means visible userprofile

                    if (null != Sessiondata.getInstance().getFbimage()) {

                        userprofileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
                        userpronames.setText(Sessiondata.getInstance().getLoginname());

                    } else {
                        userprofileimage.setVisibility(View.VISIBLE);
                        userpronames.setText("" + Sessiondata.getInstance().getLoginname());

                    }

                }

                else{//  1 Means unvisible userprofile
                    userprofileclick.setVisibility(View.GONE);
                }







                if(C.getOveralreviews().equals("1")) {

                    reviewoval.setVisibility(View.VISIBLE);

                    overallreviewvisible.setVisibility(View.VISIBLE);


                    // reviewcomentshow
                    //webservices

                 values = new ArrayList<Categorylist>();





                    vatlistthings = new ArrayList<Categorylist>();
                     rsnoval = C.getSno();

                    rsfb_id =  Sessiondata.getInstance().getReviewfbemailid();


                  */
/*  Toast.makeText(getActivity(),rsnoval,Toast.LENGTH_LONG).show();

                   Toast.makeText(getActivity(),rsfb_id,Toast.LENGTH_LONG).show();*//*

                   // Reviewcount();
                   // viewriewcomment();



                   Reviewsposting();

                }


                else{
                    reviewoval.setVisibility(View.GONE);
                    overallreviewvisible.setVisibility(View.GONE);

                    // reviewcomentnotshow





                }
*/
















            }
            else if(ftp == 5)
            {
                C = (Categorylistmodel) bundl.getSerializable(NewUiFoodfragment.EXTRATRIPINFO);
                cc = C.getCtm();
                foodtagview.setVisibility(View.VISIBLE);
                foodtag.setText(C.getFoodctags());

                Sessiondata.getInstance().setSugesteditlistview(C);




                apptitle.setText(C.getName());


                if(C.getCategoryID().equals("1")){

                    nature.setText(C.getFoodttags());

                }
                else {
                    nature.setText(C.getActivity());
                }



                reviewcount.setText(C.getRating());





                serves.setText(C.getPrice()+" "+C.getPricecurrency());
                cat_description.setText(C.getDescription());
                address_first.setText(C.getAddress());
                clocktime.setText("Opening Hours :  " + C.getOpenhrs() + "\n" + "Closing Hours :  "+C.getClosehrs());
                closereson.setText(C.getTimeremaining());


//Toast.makeText(getActivity(),C.getSno(),Toast.LENGTH_LONG).show();

                new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                    serves.setVisibility(View.VISIBLE);
                    vserves.setVisibility(View.VISIBLE);
                    List<foodclassification> Foodclassification = C.getFoodclassification();
                    for (foodclassification s : Foodclassification) {
                        // serves.setText(C.getTags());
                        //   serves.setText(C.getPrice()+" "+C.getPricecurrency());
                    }

                } else {
                    vserves.setVisibility(View.VISIBLE);
                    //serves.setText(C.getPrice() +" "+C.getPricecurrency());
                    //serves.setVisibility(View.GONE);
                }
                call.setText(C.getPhone());

                //web.setText(C.getWebsite());

                RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
                t2.setMovementMethod(LinkMovementMethod.getInstance());
                v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ShowWebFragment();
                    }
                });



                AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
                try {
                    Float rate = Float.parseFloat(C.getRating());
                    item_ratingBar.setRating(rate);

                } catch (NumberFormatException e) {
                    item_ratingBar.setRating(0);
                }





        /*
            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);

            }*/

                item_ratingBar.setEnabled(true);

                v.findViewById(R.id.mapviewclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapView();
                    }
                });



            /*    v.findViewById(R.id.dialclickcall).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + C.getPhone()));
                        startActivity(intent);
                    }
                });*/



                v.findViewById(R.id.saveclickview).setOnClickListener(new View.OnClickListener() {
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


                v.findViewById(R.id.shareclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sharlink = C.getName() + "\n" + C.getWebsite() + "\n" + C.getAddress();
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));


                    }
                });




                if(C.getTours().equals("0")){

                    specialdealavailable.setVisibility(View.GONE);
                    specialfirstview.setVisibility(View.GONE);
                    specialsewcondview.setVisibility(View.GONE);
                }
                else if (C.getTours().equals("1")){

                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.GONE);

                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);


                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            //tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });




                }


                else{
                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.VISIBLE);


                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);







                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            tdetailsreviews = "";
                            //  tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });





                    specialdealcontent2.setText(tourdetailsvalues.get(1).getContent());
                    specialdealrate2.setText("from " + tourdetailsvalues.get(1).getCurrency() + "  " + tourdetailsvalues.get(1).getRate());

                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(1).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(secoundmyImageView);


                    v.findViewById(R.id.secondtourrelativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(1).getImage();
                            tdetailsrate = tourdetailsvalues.get(1).getRate();
                            //  tdetailsreviews = tourdetailsvalues.get(1).getReviews();

                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(1).getContent();
                            tdeshoverview = tourdetailsvalues.get(1).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(1).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(1).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(1).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(1).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(1).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(1).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(1).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(1).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(1).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(1).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(1).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(1).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(1).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(1).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(1).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(1).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(1).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(1).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(1).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(1).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(1).getImage_three());

                            imagedetails.add(img3);

                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(1).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });

                }







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

               /* v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NearByelementsModel n = (NearByelementsModel) v.getTag();
                        CreatedTripModel cc = C.getCtm();
                        cc.setCategoryID("3");
                        cc.setCategorytype("Things to do info");
                        cc.setN(n);
                        showguideFragment(cc);
                    }
                });*/

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




/*



                if(C.getUserreviews().equals("0")) {
                    userprofileclick.setVisibility(View.VISIBLE);

                    //  0 Means visible userprofile

                    if (null != Sessiondata.getInstance().getFbimage()) {

                        userprofileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
                        userpronames.setText(Sessiondata.getInstance().getLoginname());

                    } else {
                        userprofileimage.setVisibility(View.VISIBLE);
                        userpronames.setText("" + Sessiondata.getInstance().getLoginname());

                    }

                }

                else{//  1 Means unvisible userprofile
                    userprofileclick.setVisibility(View.GONE);
                }







                if(C.getOveralreviews().equals("1")) {

                    reviewoval.setVisibility(View.VISIBLE);

                    overallreviewvisible.setVisibility(View.VISIBLE);


                    // reviewcomentshow
                    //webservices

                    values = new ArrayList<Categorylist>();





                    vatlistthings = new ArrayList<Categorylist>();
                    rsnoval = C.getSno();

                    rsfb_id =  Sessiondata.getInstance().getReviewfbemailid();


                  */
/*  Toast.makeText(getActivity(),rsnoval,Toast.LENGTH_LONG).show();

                   Toast.makeText(getActivity(),rsfb_id,Toast.LENGTH_LONG).show();*//*

                    // Reviewcount();
                    // viewriewcomment();



                    Reviewsposting();

                }


                else{
                    reviewoval.setVisibility(View.GONE);
                    overallreviewvisible.setVisibility(View.GONE);

                    // reviewcomentnotshow





                }
*/
















            }

            else if(ftp == 6)
            {
                C = (Categorylistmodel) bundl.getSerializable(NewuiSpecialdealsFragment.EXTRATRIPINFO);
                cc = C.getCtm();

                Sessiondata.getInstance().setSugesteditlistview(C);
                Log.d("listwadda", "" + C);


                apptitle.setText(C.getName());


                if(C.getCategoryID().equals("1")){

                    nature.setText(C.getFoodttags());

                }
                else {
                    nature.setText(C.getActivity());
                }



                reviewcount.setText(C.getRating());





                serves.setText(C.getPrice()+" "+C.getPricecurrency());
                cat_description.setText(C.getDescription());
                address_first.setText(C.getAddress());
                clocktime.setText("Opening Hours :  " + C.getOpenhrs() + "\n" + "Closing Hours :  "+C.getClosehrs());

                closereson.setText(C.getTimeremaining());

                new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                    serves.setVisibility(View.VISIBLE);
                    vserves.setVisibility(View.VISIBLE);
                    List<foodclassification> Foodclassification = C.getFoodclassification();
                    for (foodclassification s : Foodclassification) {
                        // serves.setText(C.getTags());
                        //   serves.setText(C.getPrice()+" "+C.getPricecurrency());
                    }

                } else {
                    vserves.setVisibility(View.VISIBLE);
                    //serves.setText(C.getPrice() +" "+C.getPricecurrency());
                    //serves.setVisibility(View.GONE);
                }
                call.setText(C.getPhone());

                //web.setText(C.getWebsite());

                RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
                t2.setMovementMethod(LinkMovementMethod.getInstance());
                v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ShowWebFragment();
                    }
                });



                AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
                try {
                    Float rate = Float.parseFloat(C.getRating());
                    item_ratingBar.setRating(rate);

                } catch (NumberFormatException e) {
                    item_ratingBar.setRating(0);
                }





        /*
            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);

            }*/

                item_ratingBar.setEnabled(true);

                v.findViewById(R.id.mapviewclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapView();
                    }
                });



              /*  v.findViewById(R.id.dialclickcall).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + C.getPhone()));
                        startActivity(intent);
                    }
                });
*/


                v.findViewById(R.id.saveclickview).setOnClickListener(new View.OnClickListener() {
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


                v.findViewById(R.id.shareclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sharlink = C.getName() + "\n" + C.getWebsite() + "\n" + C.getAddress();
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));


                    }
                });




                if(C.getTours().equals("0")){

                    specialdealavailable.setVisibility(View.GONE);
                    specialfirstview.setVisibility(View.GONE);
                    specialsewcondview.setVisibility(View.GONE);
                }
                else if (C.getTours().equals("1")){

                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.GONE);

                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);


                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            //tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });




                }


                else{
                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.VISIBLE);


                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);







                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            tdetailsreviews = "";
                            //  tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });





                    specialdealcontent2.setText(tourdetailsvalues.get(1).getContent());
                    specialdealrate2.setText("from " + tourdetailsvalues.get(1).getCurrency() + "  " + tourdetailsvalues.get(1).getRate());

                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(1).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(secoundmyImageView);


                    v.findViewById(R.id.secondtourrelativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(1).getImage();
                            tdetailsrate = tourdetailsvalues.get(1).getRate();
                            //  tdetailsreviews = tourdetailsvalues.get(1).getReviews();

                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(1).getContent();
                            tdeshoverview = tourdetailsvalues.get(1).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(1).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(1).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(1).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(1).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(1).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(1).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(1).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(1).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(1).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(1).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(1).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(1).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(1).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(1).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(1).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(1).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(1).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(1).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(1).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(1).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(1).getImage_three());

                            imagedetails.add(img3);

                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(1).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });

                }







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

               /* v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NearByelementsModel n = (NearByelementsModel) v.getTag();
                        CreatedTripModel cc = C.getCtm();
                        cc.setCategoryID("3");
                        cc.setCategorytype("Things to do info");
                        cc.setN(n);
                        showguideFragment(cc);
                    }
                });*/

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







/*
                if(C.getUserreviews().equals("0")) {
                    userprofileclick.setVisibility(View.VISIBLE);

                    //  0 Means visible userprofile

                    if (null != Sessiondata.getInstance().getFbimage()) {

                        userprofileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
                        userpronames.setText(Sessiondata.getInstance().getLoginname());

                    } else {
                        userprofileimage.setVisibility(View.VISIBLE);
                        userpronames.setText("" + Sessiondata.getInstance().getLoginname());

                    }

                }

                else{//  1 Means unvisible userprofile
                    userprofileclick.setVisibility(View.GONE);
                }







                if(C.getOveralreviews().equals("1")) {

                    reviewoval.setVisibility(View.VISIBLE);

                    overallreviewvisible.setVisibility(View.VISIBLE);


                    // reviewcomentshow
                    //webservices

                    values = new ArrayList<Categorylist>();





                    vatlistthings = new ArrayList<Categorylist>();
                    rsnoval = C.getSno();

                    rsfb_id =  Sessiondata.getInstance().getReviewfbemailid();


                  *//*  Toast.makeText(getActivity(),rsnoval,Toast.LENGTH_LONG).show();

                   Toast.makeText(getActivity(),rsfb_id,Toast.LENGTH_LONG).show();*//*
                    // Reviewcount();
                    // viewriewcomment();



                    Reviewsposting();

                }


                else{
                    reviewoval.setVisibility(View.GONE);
                    overallreviewvisible.setVisibility(View.GONE);

                    // reviewcomentnotshow





                }*/


















            }



            else if (ftp == 7) {
                C = (Categorylistmodel) bundl.getSerializable(NewUiView_itemInfo_Fragment.EXTRATRIPINFO);
                cc = C.getCtm();
                foodtagview.setVisibility(View.GONE);

                Sessiondata.getInstance().setSugesteditlistview(C);
                apptitle.setText(C.getName());


                if (C.getCategoryID().equals("1")) {

                    nature.setText(C.getFoodttags());

                } else {
                    nature.setText(C.getActivity());
                }


                reviewcount.setText(C.getRating());


                serves.setText(C.getPrice() + " " + C.getPricecurrency());
                cat_description.setText(C.getDescription());
                address_first.setText(C.getAddress());
                clocktime.setText("Opening Hours :  " + C.getOpenhrs() + "\n" + "Closing Hours :  " + C.getClosehrs());
                closereson.setText(C.getTimeremaining());

                C.getSno();


                new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                    serves.setVisibility(View.VISIBLE);
                    vserves.setVisibility(View.VISIBLE);
                    List<foodclassification> Foodclassification = C.getFoodclassification();
                    for (foodclassification s : Foodclassification) {
                        // serves.setText(C.getTags());
                        //   serves.setText(C.getPrice()+" "+C.getPricecurrency());
                    }

                } else {
                    vserves.setVisibility(View.VISIBLE);
                    //serves.setText(C.getPrice() +" "+C.getPricecurrency());
                    //serves.setVisibility(View.GONE);
                }
                call.setText(C.getPhone());

                //web.setText(C.getWebsite());

                RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
                t2.setMovementMethod(LinkMovementMethod.getInstance());
                v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ShowWebFragment();
                    }
                });


                AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
                try {
                    Float rate = Float.parseFloat(C.getRating());
                    item_ratingBar.setRating(rate);

                } catch (NumberFormatException e) {
                    item_ratingBar.setRating(0);
                }





        /*
            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);

            }*/

                item_ratingBar.setEnabled(true);

                v.findViewById(R.id.mapviewclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapView();
                    }
                });



                /*v.findViewById(R.id.dialclickcall).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + C.getPhone()));
                        startActivity(intent);
                    }
                });*/


                v.findViewById(R.id.saveclickview).setOnClickListener(new View.OnClickListener() {
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


                v.findViewById(R.id.shareclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sharlink = C.getName() + "\n" + C.getWebsite() + "\n" + C.getAddress();
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));


                    }
                });


                if (C.getTours().equals("0")) {

                    specialdealavailable.setVisibility(View.GONE);
                    specialfirstview.setVisibility(View.GONE);
                    specialsewcondview.setVisibility(View.GONE);
                } else if (C.getTours().equals("1")) {

                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.GONE);

                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);


                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            //tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });


                } else {
                    specialdealavailable.setVisibility(View.VISIBLE);
                    specialfirstview.setVisibility(View.VISIBLE);
                    specialsewcondview.setVisibility(View.VISIBLE);


                    specialdealsall.setVisibility(View.GONE);
                    tourdetailsvalues = C.getTourdetails();


                    specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                    specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(0).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(myImageView);


                    v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(0).getImage();
                            tdetailsrate = tourdetailsvalues.get(0).getRate();
                            tdetailsreviews = "";
                            //  tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                            tdescontent = tourdetailsvalues.get(0).getContent();
                            tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(0).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(0).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(0).getImage_three());

                            imagedetails.add(img3);
                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(0).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });


                    specialdealcontent2.setText(tourdetailsvalues.get(1).getContent());
                    specialdealrate2.setText("from " + tourdetailsvalues.get(1).getCurrency() + "  " + tourdetailsvalues.get(1).getRate());

                    Glide.with(getActivity())
                            .load(tourdetailsvalues.get(1).getImage())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.background_default)
                            .into(secoundmyImageView);


                    v.findViewById(R.id.secondtourrelativelayout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tdetailsimage = tourdetailsvalues.get(1).getImage();
                            tdetailsrate = tourdetailsvalues.get(1).getRate();
                            //  tdetailsreviews = tourdetailsvalues.get(1).getReviews();

                            tdetailsreviews = "";
                            tdescontent = tourdetailsvalues.get(1).getContent();
                            tdeshoverview = tourdetailsvalues.get(1).getOverviews();
                            tdlosoverview = tourdetailsvalues.get(1).getWhatcanyouexpect();
                            tsesinquery = tourdetailsvalues.get(1).getEnquiry();
                            Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(1).getWebsite());
                            Sessiondata.getInstance().setTourimage(tdetailsimage);
                            Sessiondata.getInstance().setTourcontent(tdescontent);
                            Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(1).getCurrency());
                            Sessiondata.getInstance().setTourrate(tdetailsrate);
                            Sessiondata.getInstance().setTourreview(tdetailsreviews);
                            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                            Sessiondata.getInstance().setTourenqiry(tsesinquery);
                            Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(1).getSub_id());
                            Sessiondata.getInstance().setTourid(tourdetailsvalues.get(1).getId());
                            Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(1).getLocation());
                            Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(1).getAddi_info());
                            Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(1).getCancellation_policy());
                            Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(1).getTour_opt_info());
                            Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(1).getTour_opt_link());
                            Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(1).getExclusion());
                            Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(1).getDeparturepoint());
                            Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(1).getDeparturedate());
                            Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(1).getDeparturetime());
                            Sessiondata.getInstance().setDuration(tourdetailsvalues.get(1).getDuration());
                            Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(1).getReturndetails());
                            Sessiondata.getInstance().setOve1(tourdetailsvalues.get(1).getHighlights());
                            Sessiondata.getInstance().setOver2(tourdetailsvalues.get(1).getInclusionandexclusion());
                            Imageval img1 = new Imageval();
                            img1.setId("1");
                            img1.setImage(tourdetailsvalues.get(1).getImage_one());

                            imagedetails.add(img1);
                            Imageval img2 = new Imageval();
                            img2.setId("2");
                            img2.setImage(tourdetailsvalues.get(1).getImage_two());

                            imagedetails.add(img2);
                            Imageval img3 = new Imageval();
                            img3.setId("3");
                            img3.setImage(tourdetailsvalues.get(1).getImage_three());

                            imagedetails.add(img3);

                            Imageval img4 = new Imageval();
                            img4.setId("3");
                            img4.setImage(tourdetailsvalues.get(1).getImage_four());

                            imagedetails.add(img4);
                            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                            Fragment fr = new Tourlistdeatails();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        }
                    });

                }


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

               /* v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NearByelementsModel n = (NearByelementsModel) v.getTag();
                        CreatedTripModel cc = C.getCtm();
                        cc.setCategoryID("3");
                        cc.setCategorytype("Things to do info");
                        cc.setN(n);
                        showguideFragment(cc);
                    }
                });*/

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






















/*
                if(C.getUserreviews().equals("0")) {
                    userprofileclick.setVisibility(View.VISIBLE);

                    //  0 Means visible userprofile

                    if (null != Sessiondata.getInstance().getFbimage()) {

                        userprofileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
                        userpronames.setText(Sessiondata.getInstance().getLoginname());

                    } else {
                        userprofileimage.setVisibility(View.VISIBLE);
                        userpronames.setText("" + Sessiondata.getInstance().getLoginname());

                    }

                }

                else{//  1 Means unvisible userprofile
                    userprofileclick.setVisibility(View.GONE);
                }







                if(C.getOveralreviews().equals("1")) {

                    reviewoval.setVisibility(View.VISIBLE);

                    overallreviewvisible.setVisibility(View.VISIBLE);


                    // reviewcomentshow
                    //webservices

                    values = new ArrayList<Categorylist>();





                    vatlistthings = new ArrayList<Categorylist>();
                    rsnoval = C.getSno();

                    rsfb_id =  Sessiondata.getInstance().getReviewfbemailid();


                   *//* Toast.makeText(getActivity(),rsnoval,Toast.LENGTH_LONG).show();

                   Toast.makeText(getActivity(),rsfb_id,Toast.LENGTH_LONG).show();*//*
                    // Reviewcount();
                    // viewriewcomment();



                    Reviewsposting();

                }


                else{
                    reviewoval.setVisibility(View.GONE);
                    overallreviewvisible.setVisibility(View.GONE);

                    // reviewcomentnotshow





                }

*/


















            }





            else if (ftp == 8){

                    C = (Categorylistmodel) bundl.getSerializable(CategoryItem_list_NearbyelementFragment.EXTRAVIEWINFO);
                    cc = C.getCtm();
                    foodtagview.setVisibility(View.GONE);
                Sessiondata.getInstance().setSugesteditlistview(C);

                    apptitle.setText(C.getName());


                    if (C.getCategoryID().equals("1")) {

                        nature.setText(C.getFoodttags());

                    } else {
                        nature.setText(C.getActivity());
                    }


                    reviewcount.setText(C.getRating());


                    serves.setText(C.getPrice() + " " + C.getPricecurrency());
                    cat_description.setText(C.getDescription());
                    address_first.setText(C.getAddress());
                    clocktime.setText("Opening Hours :  " + C.getOpenhrs() + "\n" + "Closing Hours :  " + C.getClosehrs());
                closereson.setText(C.getTimeremaining());


                    new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                        serves.setVisibility(View.VISIBLE);
                        vserves.setVisibility(View.VISIBLE);
                        List<foodclassification> Foodclassification = C.getFoodclassification();
                        for (foodclassification s : Foodclassification) {
                            // serves.setText(C.getTags());
                            //   serves.setText(C.getPrice()+" "+C.getPricecurrency());
                        }

                    } else {
                        vserves.setVisibility(View.VISIBLE);
                        //serves.setText(C.getPrice() +" "+C.getPricecurrency());
                        //serves.setVisibility(View.GONE);
                    }
                    call.setText(C.getPhone());

                    //web.setText(C.getWebsite());

                    RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
                    t2.setMovementMethod(LinkMovementMethod.getInstance());
                    v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ShowWebFragment();
                        }
                    });


                   AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
                    try {
                        Float rate = Float.parseFloat(C.getRating());
                        item_ratingBar.setRating(rate);

                    } catch (NumberFormatException e) {
                        item_ratingBar.setRating(0);
                    }
                item_ratingBar.setEnabled(true);





                    v.findViewById(R.id.mapviewclick).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mapView();
                        }
                    });



                /*v.findViewById(R.id.dialclickcall).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + C.getPhone()));
                        startActivity(intent);
                    }
                });*/


                    v.findViewById(R.id.saveclickview).setOnClickListener(new View.OnClickListener() {
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


                    v.findViewById(R.id.shareclick).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            sharlink = C.getName() + "\n" + C.getWebsite() + "\n" + C.getAddress();
                            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
                            startActivity(Intent.createChooser(sharingIntent, "Share using"));


                        }
                    });


                    if (C.getTours().equals("0")) {

                        specialdealavailable.setVisibility(View.GONE);
                        specialfirstview.setVisibility(View.GONE);
                        specialsewcondview.setVisibility(View.GONE);
                    } else if (C.getTours().equals("1")) {

                        specialdealavailable.setVisibility(View.VISIBLE);
                        specialfirstview.setVisibility(View.VISIBLE);
                        specialsewcondview.setVisibility(View.GONE);

                        specialdealsall.setVisibility(View.GONE);
                        tourdetailsvalues = C.getTourdetails();


                        specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                        specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                        Glide.with(getActivity())
                                .load(tourdetailsvalues.get(0).getImage())
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.background_default)
                                .into(myImageView);


                        v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tdetailsimage = tourdetailsvalues.get(0).getImage();
                                tdetailsrate = tourdetailsvalues.get(0).getRate();
                                //tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                                tdetailsreviews = "";
                                tdescontent = tourdetailsvalues.get(0).getContent();
                                tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                                tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                                tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                                Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                                Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                                Sessiondata.getInstance().setTourimage(tdetailsimage);
                                Sessiondata.getInstance().setTourcontent(tdescontent);
                                Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                                Sessiondata.getInstance().setTourrate(tdetailsrate);
                                Sessiondata.getInstance().setTourreview(tdetailsreviews);
                                Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                                Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                                Sessiondata.getInstance().setTourenqiry(tsesinquery);
                                Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                                Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                                Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                                Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                                Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                                Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                                Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                                Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                                Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                                Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                                Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                                Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                                Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                                Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                                Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                                Imageval img1 = new Imageval();
                                img1.setId("1");
                                img1.setImage(tourdetailsvalues.get(0).getImage_one());

                                imagedetails.add(img1);
                                Imageval img2 = new Imageval();
                                img2.setId("2");
                                img2.setImage(tourdetailsvalues.get(0).getImage_two());

                                imagedetails.add(img2);
                                Imageval img3 = new Imageval();
                                img3.setId("3");
                                img3.setImage(tourdetailsvalues.get(0).getImage_three());

                                imagedetails.add(img3);
                                Imageval img4 = new Imageval();
                                img4.setId("3");
                                img4.setImage(tourdetailsvalues.get(0).getImage_four());

                                imagedetails.add(img4);
                                Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                                Fragment fr = new Tourlistdeatails();
                                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                                fc.replaceFragment(fr);

                            }
                        });


                    } else {
                        specialdealavailable.setVisibility(View.VISIBLE);
                        specialfirstview.setVisibility(View.VISIBLE);
                        specialsewcondview.setVisibility(View.VISIBLE);


                        specialdealsall.setVisibility(View.GONE);
                        tourdetailsvalues = C.getTourdetails();


                        specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
                        specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
                        Glide.with(getActivity())
                                .load(tourdetailsvalues.get(0).getImage())
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.background_default)
                                .into(myImageView);


                        v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tdetailsimage = tourdetailsvalues.get(0).getImage();
                                tdetailsrate = tourdetailsvalues.get(0).getRate();
                                tdetailsreviews = "";
                                //  tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                                tdescontent = tourdetailsvalues.get(0).getContent();
                                tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                                tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                                tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                                Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                                Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                                Sessiondata.getInstance().setTourimage(tdetailsimage);
                                Sessiondata.getInstance().setTourcontent(tdescontent);
                                Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                                Sessiondata.getInstance().setTourrate(tdetailsrate);
                                Sessiondata.getInstance().setTourreview(tdetailsreviews);
                                Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                                Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                                Sessiondata.getInstance().setTourenqiry(tsesinquery);
                                Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                                Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                                Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                                Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                                Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                                Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                                Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                                Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                                Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                                Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                                Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                                Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                                Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                                Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                                Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                                Imageval img1 = new Imageval();
                                img1.setId("1");
                                img1.setImage(tourdetailsvalues.get(0).getImage_one());

                                imagedetails.add(img1);
                                Imageval img2 = new Imageval();
                                img2.setId("2");
                                img2.setImage(tourdetailsvalues.get(0).getImage_two());

                                imagedetails.add(img2);
                                Imageval img3 = new Imageval();
                                img3.setId("3");
                                img3.setImage(tourdetailsvalues.get(0).getImage_three());

                                imagedetails.add(img3);
                                Imageval img4 = new Imageval();
                                img4.setId("3");
                                img4.setImage(tourdetailsvalues.get(0).getImage_four());

                                imagedetails.add(img4);
                                Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                                Fragment fr = new Tourlistdeatails();
                                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                                fc.replaceFragment(fr);

                            }
                        });


                        specialdealcontent2.setText(tourdetailsvalues.get(1).getContent());
                        specialdealrate2.setText("from " + tourdetailsvalues.get(1).getCurrency() + "  " + tourdetailsvalues.get(1).getRate());

                        Glide.with(getActivity())
                                .load(tourdetailsvalues.get(1).getImage())
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.background_default)
                                .into(secoundmyImageView);


                        v.findViewById(R.id.secondtourrelativelayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tdetailsimage = tourdetailsvalues.get(1).getImage();
                                tdetailsrate = tourdetailsvalues.get(1).getRate();
                                //  tdetailsreviews = tourdetailsvalues.get(1).getReviews();

                                tdetailsreviews = "";
                                tdescontent = tourdetailsvalues.get(1).getContent();
                                tdeshoverview = tourdetailsvalues.get(1).getOverviews();
                                tdlosoverview = tourdetailsvalues.get(1).getWhatcanyouexpect();
                                tsesinquery = tourdetailsvalues.get(1).getEnquiry();
                                Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(1).getId());
                                Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(1).getWebsite());
                                Sessiondata.getInstance().setTourimage(tdetailsimage);
                                Sessiondata.getInstance().setTourcontent(tdescontent);
                                Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(1).getCurrency());
                                Sessiondata.getInstance().setTourrate(tdetailsrate);
                                Sessiondata.getInstance().setTourreview(tdetailsreviews);
                                Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                                Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                                Sessiondata.getInstance().setTourenqiry(tsesinquery);
                                Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(1).getSub_id());
                                Sessiondata.getInstance().setTourid(tourdetailsvalues.get(1).getId());
                                Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(1).getLocation());
                                Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(1).getAddi_info());
                                Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(1).getCancellation_policy());
                                Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(1).getTour_opt_info());
                                Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(1).getTour_opt_link());
                                Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(1).getExclusion());
                                Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(1).getDeparturepoint());
                                Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(1).getDeparturedate());
                                Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(1).getDeparturetime());
                                Sessiondata.getInstance().setDuration(tourdetailsvalues.get(1).getDuration());
                                Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(1).getReturndetails());
                                Sessiondata.getInstance().setOve1(tourdetailsvalues.get(1).getHighlights());
                                Sessiondata.getInstance().setOver2(tourdetailsvalues.get(1).getInclusionandexclusion());
                                Imageval img1 = new Imageval();
                                img1.setId("1");
                                img1.setImage(tourdetailsvalues.get(1).getImage_one());

                                imagedetails.add(img1);
                                Imageval img2 = new Imageval();
                                img2.setId("2");
                                img2.setImage(tourdetailsvalues.get(1).getImage_two());

                                imagedetails.add(img2);
                                Imageval img3 = new Imageval();
                                img3.setId("3");
                                img3.setImage(tourdetailsvalues.get(1).getImage_three());

                                imagedetails.add(img3);

                                Imageval img4 = new Imageval();
                                img4.setId("3");
                                img4.setImage(tourdetailsvalues.get(1).getImage_four());

                                imagedetails.add(img4);
                                Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                                Fragment fr = new Tourlistdeatails();
                                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                                fc.replaceFragment(fr);

                            }
                        });

                    }


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

                   /* v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
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
*/
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






/*

                if(C.getUserreviews().equals("0")) {
                    userprofileclick.setVisibility(View.VISIBLE);

                    //  0 Means visible userprofile

                    if (null != Sessiondata.getInstance().getFbimage()) {

                        userprofileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
                        userpronames.setText(Sessiondata.getInstance().getLoginname());

                    } else {
                        userprofileimage.setVisibility(View.VISIBLE);
                        userpronames.setText("" + Sessiondata.getInstance().getLoginname());

                    }

                }

                else{//  1 Means unvisible userprofile
                    userprofileclick.setVisibility(View.GONE);
                }







                if(C.getOveralreviews().equals("1")) {

                    reviewoval.setVisibility(View.VISIBLE);

                    overallreviewvisible.setVisibility(View.VISIBLE);


                    // reviewcomentshow
                    //webservices

                    values = new ArrayList<Categorylist>();





                    vatlistthings = new ArrayList<Categorylist>();
                    rsnoval = C.getSno();

                    rsfb_id =  Sessiondata.getInstance().getReviewfbemailid();


                  */
/*  Toast.makeText(getActivity(),rsnoval,Toast.LENGTH_LONG).show();

                   Toast.makeText(getActivity(),rsfb_id,Toast.LENGTH_LONG).show();*//*

                    // Reviewcount();
                    // viewriewcomment();



                    Reviewsposting();

                }


                else{
                    reviewoval.setVisibility(View.GONE);
                    overallreviewvisible.setVisibility(View.GONE);

                    // reviewcomentnotshow





                }


*/




















            }





















            else {

                C = (Categorylistmodel) bundl.getSerializable(CategoryItem_list_Fragment.EXTRATRIPINFO);
                cc = C.getCtm();

                Sessiondata.getInstance().setSugesteditlistview(C);
            }
            if (C == null) {

            }
            Log.d("listwadda", "" + C);


            apptitle.setText(C.getName());


            if(C.getCategoryID().equals("1")){

                foodtagview.setVisibility(View.VISIBLE);
                foodtag.setText(C.getFoodctags());
                nature.setText(C.getFoodttags());

            }






else {
                foodtagview.setVisibility(View.GONE);
                nature.setText(C.getActivity());
            }



            reviewcount.setText(C.getRating());















            serves.setText(C.getPrice()+" "+C.getPricecurrency());
            cat_description.setText(C.getDescription());
            address_first.setText(C.getAddress());
            clocktime.setText("Opening Hours :  " + C.getOpenhrs() + "\n" + "Closing Hours :  "+C.getClosehrs());

            closereson.setText(C.getTimeremaining());


            new WebPageTask(C.getSno()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            if (null != C.getFoodclassification() && C.getFoodclassification().size() > 0) {
                serves.setVisibility(View.VISIBLE);
                vserves.setVisibility(View.VISIBLE);
                List<foodclassification> Foodclassification = C.getFoodclassification();
                for (foodclassification s : Foodclassification) {
                   // serves.setText(C.getTags());
                 //   serves.setText(C.getPrice()+" "+C.getPricecurrency());
                }

            } else {
                vserves.setVisibility(View.VISIBLE);
                //serves.setText(C.getPrice() +" "+C.getPricecurrency());
                //serves.setVisibility(View.GONE);
            }
            call.setText(C.getPhone());

            //web.setText(C.getWebsite());

            RobotoTextView t2 = (RobotoTextView) v.findViewById(R.id.web);
            t2.setMovementMethod(LinkMovementMethod.getInstance());
            v.findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ShowWebFragment();
                }
            });




            AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);

            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);
            }






      /*  *//**//*
            try {
                Float rate = Float.parseFloat(C.getRating());
                item_ratingBar.setRating(rate);
            } catch (NumberFormatException e) {
                item_ratingBar.setRating(0);

            }*//**//**/

            item_ratingBar.setEnabled(true);

            v.findViewById(R.id.mapviewclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapView();
                }
            });



            /*v.findViewById(R.id.dialclickcall).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + C.getPhone()));
                    startActivity(intent);
                }
            });*/



            v.findViewById(R.id.saveclickview).setOnClickListener(new View.OnClickListener() {
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


            v.findViewById(R.id.shareclick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sharlink = C.getName() + "\n" + C.getWebsite() + "\n" + C.getAddress();
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
                    startActivity(Intent.createChooser(sharingIntent, "Share using"));


                }
            });




           if(C.getTours().equals("0")){

                    specialdealavailable.setVisibility(View.GONE);
               specialfirstview.setVisibility(View.GONE);
               specialsewcondview.setVisibility(View.GONE);
            }
            else if (C.getTours().equals("1")){

                specialdealavailable.setVisibility(View.VISIBLE);
               specialfirstview.setVisibility(View.VISIBLE);
               specialsewcondview.setVisibility(View.GONE);

               specialdealsall.setVisibility(View.GONE);
               tourdetailsvalues = C.getTourdetails();


               specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
               specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
               Glide.with(getActivity())
                       .load(tourdetailsvalues.get(0).getImage())
                       .placeholder(R.drawable.loading)
                       .error(R.drawable.background_default)
                       .into(myImageView);


               v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       tdetailsimage = tourdetailsvalues.get(0).getImage();
                       tdetailsrate = tourdetailsvalues.get(0).getRate();
                       //tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                       tdetailsreviews = "";
                       tdescontent = tourdetailsvalues.get(0).getContent();
                       tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                       tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                       tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                       Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                       Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                       Sessiondata.getInstance().setTourimage(tdetailsimage);
                       Sessiondata.getInstance().setTourcontent(tdescontent);
                       Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                       Sessiondata.getInstance().setTourrate(tdetailsrate);
                       Sessiondata.getInstance().setTourreview(tdetailsreviews);
                       Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                       Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                       Sessiondata.getInstance().setTourenqiry(tsesinquery);
                       Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                       Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                       Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                       Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                       Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                       Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                       Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                       Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                       Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                       Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                       Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                       Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                       Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                       Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                       Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                       Imageval img1 = new Imageval();
                       img1.setId("1");
                       img1.setImage(tourdetailsvalues.get(0).getImage_one());

                       imagedetails.add(img1);
                       Imageval img2 = new Imageval();
                       img2.setId("2");
                       img2.setImage(tourdetailsvalues.get(0).getImage_two());

                       imagedetails.add(img2);
                       Imageval img3 = new Imageval();
                       img3.setId("3");
                       img3.setImage(tourdetailsvalues.get(0).getImage_three());

                       imagedetails.add(img3);
                       Imageval img4 = new Imageval();
                       img4.setId("3");
                       img4.setImage(tourdetailsvalues.get(0).getImage_four());

                       imagedetails.add(img4);
                       Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                       Fragment fr = new Tourlistdeatails();
                       FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                       fc.replaceFragment(fr);

                   }
               });




            }


            else{
                specialdealavailable.setVisibility(View.VISIBLE);
               specialfirstview.setVisibility(View.VISIBLE);
               specialsewcondview.setVisibility(View.VISIBLE);


               specialdealsall.setVisibility(View.GONE);
               tourdetailsvalues = C.getTourdetails();


               specialdealcontent1.setText(tourdetailsvalues.get(0).getContent());
               specaildealrate1.setText("from " + tourdetailsvalues.get(0).getCurrency() + "  " + tourdetailsvalues.get(0).getRate());
               Glide.with(getActivity())
                       .load(tourdetailsvalues.get(0).getImage())
                       .placeholder(R.drawable.loading)
                       .error(R.drawable.background_default)
                       .into(myImageView);







               v.findViewById(R.id.relativelayout).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       tdetailsimage = tourdetailsvalues.get(0).getImage();
                       tdetailsrate = tourdetailsvalues.get(0).getRate();
                       tdetailsreviews = "";
                     //  tdetailsreviews = tourdetailsvalues.get(0).getReviews();
                       tdescontent = tourdetailsvalues.get(0).getContent();
                       tdeshoverview = tourdetailsvalues.get(0).getOverviews();
                       tdlosoverview = tourdetailsvalues.get(0).getWhatcanyouexpect();
                       tsesinquery = tourdetailsvalues.get(0).getEnquiry();
                       Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(0).getId());
                       Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(0).getWebsite());
                       Sessiondata.getInstance().setTourimage(tdetailsimage);
                       Sessiondata.getInstance().setTourcontent(tdescontent);
                       Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(0).getCurrency());
                       Sessiondata.getInstance().setTourrate(tdetailsrate);
                       Sessiondata.getInstance().setTourreview(tdetailsreviews);
                       Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                       Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                       Sessiondata.getInstance().setTourenqiry(tsesinquery);
                       Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(0).getSub_id());
                       Sessiondata.getInstance().setTourid(tourdetailsvalues.get(0).getId());
                       Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(0).getLocation());
                       Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(0).getAddi_info());
                       Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(0).getCancellation_policy());
                       Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(0).getTour_opt_info());
                       Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(0).getTour_opt_link());
                       Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(0).getExclusion());
                       Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(0).getDeparturepoint());
                       Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(0).getDeparturedate());
                       Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(0).getDeparturetime());
                       Sessiondata.getInstance().setDuration(tourdetailsvalues.get(0).getDuration());
                       Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(0).getReturndetails());
                       Sessiondata.getInstance().setOve1(tourdetailsvalues.get(0).getHighlights());
                       Sessiondata.getInstance().setOver2(tourdetailsvalues.get(0).getInclusionandexclusion());
                       Imageval img1 = new Imageval();
                       img1.setId("1");
                       img1.setImage(tourdetailsvalues.get(0).getImage_one());

                       imagedetails.add(img1);
                       Imageval img2 = new Imageval();
                       img2.setId("2");
                       img2.setImage(tourdetailsvalues.get(0).getImage_two());

                       imagedetails.add(img2);
                       Imageval img3 = new Imageval();
                       img3.setId("3");
                       img3.setImage(tourdetailsvalues.get(0).getImage_three());

                       imagedetails.add(img3);
                       Imageval img4 = new Imageval();
                       img4.setId("3");
                       img4.setImage(tourdetailsvalues.get(0).getImage_four());

                       imagedetails.add(img4);
                       Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                       Fragment fr = new Tourlistdeatails();
                       FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                       fc.replaceFragment(fr);

                   }
               });





               specialdealcontent2.setText(tourdetailsvalues.get(1).getContent());
               specialdealrate2.setText("from " + tourdetailsvalues.get(1).getCurrency() + "  " + tourdetailsvalues.get(1).getRate());

               Glide.with(getActivity())
                       .load(tourdetailsvalues.get(1).getImage())
                       .placeholder(R.drawable.loading)
                       .error(R.drawable.background_default)
                       .into(secoundmyImageView);


               v.findViewById(R.id.secondtourrelativelayout).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       tdetailsimage = tourdetailsvalues.get(1).getImage();
                       tdetailsrate = tourdetailsvalues.get(1).getRate();
                     //  tdetailsreviews = tourdetailsvalues.get(1).getReviews();

                       tdetailsreviews = "";
                       tdescontent = tourdetailsvalues.get(1).getContent();
                       tdeshoverview = tourdetailsvalues.get(1).getOverviews();
                       tdlosoverview = tourdetailsvalues.get(1).getWhatcanyouexpect();
                       tsesinquery = tourdetailsvalues.get(1).getEnquiry();
                       Sessiondata.getInstance().setWebidchange(tourdetailsvalues.get(1).getId());
                       Sessiondata.getInstance().setWebsite(tourdetailsvalues.get(1).getWebsite());
                       Sessiondata.getInstance().setTourimage(tdetailsimage);
                       Sessiondata.getInstance().setTourcontent(tdescontent);
                       Sessiondata.getInstance().setTourcuren(tourdetailsvalues.get(1).getCurrency());
                       Sessiondata.getInstance().setTourrate(tdetailsrate);
                       Sessiondata.getInstance().setTourreview(tdetailsreviews);
                       Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                       Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                       Sessiondata.getInstance().setTourenqiry(tsesinquery);
                       Sessiondata.getInstance().setToursubid(tourdetailsvalues.get(1).getSub_id());
                       Sessiondata.getInstance().setTourid(tourdetailsvalues.get(1).getId());
                       Sessiondata.getInstance().setLocationname(tourdetailsvalues.get(1).getLocation());
                       Sessiondata.getInstance().setAddtionalcon(tourdetailsvalues.get(1).getAddi_info());
                       Sessiondata.getInstance().setCancelceon(tourdetailsvalues.get(1).getCancellation_policy());
                       Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalues.get(1).getTour_opt_info());
                       Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalues.get(1).getTour_opt_link());
                       Sessiondata.getInstance().setExclusions(tourdetailsvalues.get(1).getExclusion());
                       Sessiondata.getInstance().setDeppoint(tourdetailsvalues.get(1).getDeparturepoint());
                       Sessiondata.getInstance().setDepdate(tourdetailsvalues.get(1).getDeparturedate());
                       Sessiondata.getInstance().setDeptime(tourdetailsvalues.get(1).getDeparturetime());
                       Sessiondata.getInstance().setDuration(tourdetailsvalues.get(1).getDuration());
                       Sessiondata.getInstance().setReturndetails(tourdetailsvalues.get(1).getReturndetails());
                       Sessiondata.getInstance().setOve1(tourdetailsvalues.get(1).getHighlights());
                       Sessiondata.getInstance().setOver2(tourdetailsvalues.get(1).getInclusionandexclusion());
                       Imageval img1 = new Imageval();
                       img1.setId("1");
                       img1.setImage(tourdetailsvalues.get(1).getImage_one());

                       imagedetails.add(img1);
                       Imageval img2 = new Imageval();
                       img2.setId("2");
                       img2.setImage(tourdetailsvalues.get(1).getImage_two());

                       imagedetails.add(img2);
                       Imageval img3 = new Imageval();
                       img3.setId("3");
                       img3.setImage(tourdetailsvalues.get(1).getImage_three());

                       imagedetails.add(img3);

                       Imageval img4 = new Imageval();
                       img4.setId("3");
                       img4.setImage(tourdetailsvalues.get(1).getImage_four());

                       imagedetails.add(img4);
                       Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                       Fragment fr = new Tourlistdeatails();
                       FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                       fc.replaceFragment(fr);

                   }
               });

            }







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

           /* v.findViewById(R.id.thingstodo).setOnClickListener(new View.OnClickListener() {
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
*/
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







     /*       if(C.getUserreviews().equals("0")) {
                userprofileclick.setVisibility(View.VISIBLE);

                //  0 Means visible userprofile

                if (null != Sessiondata.getInstance().getFbimage()) {

                    userprofileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
                    userpronames.setText(Sessiondata.getInstance().getLoginname());

                } else {
                    userprofileimage.setVisibility(View.VISIBLE);
                    userpronames.setText("" + Sessiondata.getInstance().getLoginname());

                }

            }

            else{//  1 Means unvisible userprofile
                userprofileclick.setVisibility(View.GONE);
            }







            if(C.getOveralreviews().equals("1")) {

                reviewoval.setVisibility(View.VISIBLE);

                overallreviewvisible.setVisibility(View.VISIBLE);


                // reviewcomentshow
                //webservices

                values = new ArrayList<Categorylist>();





                vatlistthings = new ArrayList<Categorylist>();
                rsnoval = C.getSno();

                rsfb_id =  Sessiondata.getInstance().getReviewfbemailid();


                  *//*  Toast.makeText(getActivity(),rsnoval,Toast.LENGTH_LONG).show();

                   Toast.makeText(getActivity(),rsfb_id,Toast.LENGTH_LONG).show();*//*
                // Reviewcount();
                // viewriewcomment();



                Reviewsposting();

            }


            else{
                reviewoval.setVisibility(View.GONE);
                overallreviewvisible.setVisibility(View.GONE);

                // reviewcomentnotshow





            }*/














        }

            return v;
    }









           private void viewriewcomment() {

               StringRequest stringRequest = new StringRequest(Request.Method.POST,REVIEWVAL,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               Log.d("Vollyarjunresponse", "" + response);
                               //parse json data
                               try {
                                   JSONObject obj = new JSONObject(response);


                                   stag = obj.getInt("Status");
                                   message = obj.getString("msg");

                                   JSONObject subObject = obj.getJSONObject("ratingarray");


                                   average = subObject.getString("average");
                                   oner = subObject.getString("oner");
                                   twor = subObject.getString("twor");
                                   threer = subObject.getString("threer");
                                   fourr = subObject.getString("fourr");
                                   fiver = subObject.getString("fiver");
                                   totalreviews = subObject.getString("totalreviews");




                                   JSONArray cat = obj.getJSONArray("categorylist");

                                   for(int i=0; i <cat.length();i++){
                                       JSONObject catobj = cat.getJSONObject(i);
                                       Categorylist relis = new Categorylist();
                                       relis.setSno(catobj.getString("sno"));
                                       relis.setName(catobj.getString("name"));
                                       relis.setLikescount(catobj.getString("likescount"));
                                       relis.setLdescription(catobj.getString("ldescription"));
                                       relis.setRating(catobj.getString("rating"));
                                       relis.setUsername(catobj.getString("username"));
                                       relis.setImage(catobj.getString("image"));
                                       relis.setUserliked(catobj.getString("userliked"));

                                       vatlistthings.add(relis);

                                   }


                                   Sessiondata.getInstance().setSimplevalueend(vatlistthings);



                               } catch (Exception ex) {
                                   //don't forget this
                               }
                               if (stag == 1) {
                                   progressDialog.dismiss();
                             /*      values = Sessiondata.getInstance().getSimplevalueend();
                                   resvalue =  new Reviesvalue(getActivity(),values);
                                   simlist.setAdapter(resvalue);*/

                               } else if (stag == 0) {
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
                       params.put(KEY_SNO,rsnoval);
                       params.put(KEY_FBID,rsfb_id);
                       return params;
                   }

               };




               RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
               Log.d("Result array", "" + requestQueue);
               Log.d("Result final", "" + stringRequest);
               requestQueue.add(stringRequest);
//initialize the progress dialog and show it
               progressDialog = new ProgressDialog(getActivity());
               progressDialog.setMessage("Please Wait....");
               progressDialog.show();
           }













/*


           private void Reviewsposting() {
               StringRequest stringRequest = new StringRequest(Request.Method.POST,REVIEWVAL,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               Log.d("Vollyarjunresponse", "" + response);




                               //parse json data
                               try {

                                  // Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();



                                   JSONObject obj = new JSONObject(response);


                                   stag = obj.getInt("status");
                                   message = obj.getString("msg");

                                   JSONObject subObject = obj.getJSONObject("ratingarray");


                                   average = subObject.getString("average");
                                   oner = subObject.getString("oner");
                                   twor = subObject.getString("twor");
                                   threer = subObject.getString("threer");
                                   fourr = subObject.getString("fourr");
                                   fiver = subObject.getString("fiver");
                                   totalreviews = subObject.getString("totalreviews");




                                   JSONArray cat = obj.getJSONArray("categorylist");

                                   for(int i=0; i <cat.length();i++){
                                       JSONObject catobj = cat.getJSONObject(i);
                                       Categorylist relis = new Categorylist();
                                       relis.setSno(catobj.getString("sno"));
                                       relis.setName(catobj.getString("name"));
                                       relis.setLikescount(catobj.getString("likescount"));
                                       relis.setLdescription(catobj.getString("ldescription"));
                                       relis.setRating(catobj.getString("rating"));
                                       relis.setUsername(catobj.getString("username"));
                                       relis.setImage(catobj.getString("image"));
                                       relis.setUserliked(catobj.getString("userliked"));

                                       vatlistthings.add(relis);
                                       Sessiondata.getInstance().setSimplevalueend(vatlistthings);

                                   }









                               } catch (Exception ex) {
                               }
                               if (stag == 1) {
                                  // progressDialog.dismiss();

                                   values = Sessiondata.getInstance().getSimplevalueend();
                                   resvalue = new emphistoryAdapter(getActivity(), values);
                                   simlist.setAdapter(resvalue);


                                   AlertUtils.RatingColorGreen(getActivity(), reviewbigratingstar);
                                   try {
                                       reviewbigratingstar.setRating(Float.parseFloat(average));
                                   } catch (NumberFormatException e) {

                                   }
                                   ratingshowtxt.setText(average);
                                   totalrevicecount.setText(totalreviews);


                                   if(oner.equals("0")){

                                       ViewGroup.LayoutParams params = onebar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = ozeross;
                                       onebar.setLayoutParams(params);
                                   }
                                      else {
                                       ViewGroup.LayoutParams params = onebar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = onew;
                                       onebar.setLayoutParams(params);

                                   }










                                   if(twor.equals("0")){


                                       ViewGroup.LayoutParams params = twobar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = ozeross;
                                       twobar.setLayoutParams(params);
                                   }


                                   else {

                                       ViewGroup.LayoutParams params = twobar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = twow;
                                       twobar.setLayoutParams(params);



                                   }










                                   if(threer.equals("0")){


                                    ViewGroup.LayoutParams params = threebar.getLayoutParams();

                                    params.height = hereview;
                                    params.width = ozeross;
                                    threebar.setLayoutParams(params);
                                }


                              else {

                                    ViewGroup.LayoutParams params = threebar.getLayoutParams();

                                    params.height = hereview;
                                    params.width = threew;
                                    threebar.setLayoutParams(params);



                                }





                                   if(fourr.equals("0")){


                                       ViewGroup.LayoutParams params = fourbar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = ozeross;
                                       fourbar.setLayoutParams(params);
                                   }


                                   else {

                                       ViewGroup.LayoutParams params = fourbar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = fourw;
                                       fourbar.setLayoutParams(params);



                                   }








                                   if(fiver.equals("0")){


                                       ViewGroup.LayoutParams params = fivebar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = ozeross;
                                       fivebar.setLayoutParams(params);
                                   }


                                   else {

                                       ViewGroup.LayoutParams params = fivebar.getLayoutParams();

                                       params.height = hereview;
                                       params.width = fivew;
                                       fivebar.setLayoutParams(params);



                                   }









                               } else if (stag == 0) {
                                   //progressDialog.dismiss();
                                   //  Toast.makeText(getActivity(),"Aaaaa",Toast.LENGTH_LONG).show();


                                   reviewoval.setVisibility(View.GONE);
                                   overallreviewvisible.setVisibility(View.GONE);



                               }
                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError volleyError) {
                             //  progressDialog.dismiss();
                               Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_LONG).show();
                           }
                       }){
                   @Override
                   protected Map<String,String> getParams(){
                       Map<String,String> params = new HashMap<String, String>();
                       Log.d("Mapparams",""+params);
                       params.put(KEY_SNO,rsnoval);
                       params.put(KEY_FBID,rsfb_id);


                       return params;
                   }

               };
               RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
               requestQueue.add(stringRequest);
              // progressDialog = new ProgressDialog(getActivity());
             //  progressDialog.setMessage("Please Wait....");
              // progressDialog.show();
           }

*/






           private class emphistoryAdapter extends BaseAdapter {
           ViewHolder viewHolder;
               LayoutInflater mLayoutInflater = null;
               Context context;
               ArrayList<Categorylist> sing_in_datas;
               private int lastPosition = -1;
               int height = 0;
               public emphistoryAdapter(Context con, ArrayList<Categorylist> singindatas) {
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
                       view = mLayoutInflater.inflate(R.layout.reviewid, null);
                       viewHolder = new ViewHolder();
                       viewHolder.profilename = (RobotoTextView) view.findViewById(R.id.datereviewfirstnamelist);
                       viewHolder.weakago = (RobotoTextView) view.findViewById(R.id.datereviewfirstdays);
                       viewHolder.ratingview = (RatingBar) view.findViewById(R.id.datereviewfirstitem_ratingBar);
                       viewHolder.linearlay = (LinearLayout) view.findViewById(R.id.linearlay);
                       viewHolder.title = (RobotoTextView) view.findViewById(R.id.datereviewfirsttitle);
                       viewHolder.subtitle = (RobotoTextView) view.findViewById(R.id.datereviewfirstsubtitle);
                       viewHolder.likenumber = (RobotoTextView) view.findViewById(R.id.firstslikecont);
                       viewHolder.profileimage = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.datereviewfirstprofile_image);
                       viewHolder.likeimage = (ImageView) view.findViewById(R.id.likecountrycont);

                       view.setTag(viewHolder);

                   } else {
                       viewHolder = (ViewHolder) view.getTag();
                   }
                   final ViewHolder viewHolder = (ViewHolder) view.getTag();

                   viewHolder.profilename.setText(sing_in_datas.get(position).getUsername());


                   //  viewHolder.weakago.setText(sing_in_datas.get(position).);

                   AlertUtils.RatingColorGreen(context, viewHolder.ratingview);
                   try {
                       viewHolder.ratingview.setRating(Float.parseFloat(sing_in_datas.get(position).getRating()));
                   } catch (NumberFormatException e) {

                   }
                   viewHolder.title.setText(sing_in_datas.get(position).getName());
                   viewHolder.subtitle.setText(sing_in_datas.get(position).getLdescription());
                   viewHolder.likenumber.setText(sing_in_datas.get(position).getLikescount());









                   Categorylist newsItem = (Categorylist) sing_in_datas.get(position);


                   final ViewHolder vh = viewHolder;
                   vh.linearlay.post(new Runnable() {
                       public void run() {
                           height = vh.linearlay.getHeight();
                       }
                   });
                   if (newsItem.getImage().length() > 0) {
                       try {
                           final String s = newsItem.getImage();
                           //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                           if (height != 0) {
                               Picasso.with(getActivity()).load(s).resize(height, height)
                                       .centerCrop().networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.profileimage, new Callback() {
                                   @Override
                                   public void onSuccess() {

                                   }

                                   @Override
                                   public void onError() {
                                       //Try again online if cache failed
                                       Picasso.with(getActivity())
                                               .load(s)
                                               .resize(height, height)
                                               .centerCrop()
                                               .error(R.drawable.background_default)
                                               .placeholder(R.drawable.loading)
                                               .into(viewHolder.profileimage, new Callback() {
                                                   @Override
                                                   public void onSuccess() {

                                                   }

                                                   @Override
                                                   public void onError() {
                                                       //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                                   }
                                               });
                                   }
                               });
                           } else {
                               Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.profileimage, new Callback() {
                                   @Override
                                   public void onSuccess() {

                                   }

                                   @Override
                                   public void onError() {
                                       //Try again online if cache failed
                                       Picasso.with(getActivity())
                                               .load(s)
                                               .error(R.drawable.background_default)
                                               .placeholder(R.drawable.loading)
                                               .into(viewHolder.profileimage, new Callback() {
                                                   @Override
                                                   public void onSuccess() {

                                                   }

                                                   @Override
                                                   public void onError() {
                                                       //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                                   }
                                               });
                                   }
                               });

                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }




            /*       if (newsItem.getImage().length() > 0) {
                       try {
                           final String s = newsItem.getImage();
                           //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                           Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.profileimage, new Callback() {
                               @Override
                               public void onSuccess() {

                               }

                               @Override
                               public void onError() {
                                   //Try again online if cache failed
                                   Picasso.with(context)
                                           .load(s)
                                           .error(R.drawable.background_default)
                                           .placeholder(R.drawable.loading)
                                           .into(viewHolder.profileimage, new Callback() {
                                               @Override
                                               public void onSuccess() {

                                               }

                                               @Override
                                               public void onError() {
                                                   //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                               }
                                           });
                               }
                           });
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
*/



                   if(sing_in_datas.get(position).getUserliked().equals("0")) {

                       viewHolder.likeimage.setImageResource(R.drawable.likeup);

                   }
                   else{
                       viewHolder.likeimage.setImageResource(R.drawable.likeup);
                       viewHolder.likeimage.setColorFilter(ContextCompat.getColor(getContext(), R.color.neuiblueclor));

                   }
                   return view;
               }
           }

           static class ViewHolder {
               RobotoTextView profilename;
               RobotoTextView weakago;
               de.hdodenhof.circleimageview.CircleImageView profileimage;
               RatingBar ratingview;
               RobotoTextView title;
               RobotoTextView subtitle;
               RobotoTextView likenumber;
               ImageView likeimage;
               LinearLayout linearlay;

           }







































           private void Reviewcount() {

               StringRequest stringRequest = new StringRequest(Request.Method.POST, REVIEWVAL, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.d("Vollyarjunresponse", "" + response);
                       try{
                           JSONObject obj = new JSONObject(response);


                           stag = obj.getInt("Status");
                           message = obj.getString("msg");

                           JSONObject subObject = obj.getJSONObject("ratingarray");


                           average = subObject.getString("average");
                           oner = subObject.getString("oner");
                           twor = subObject.getString("twor");
                           threer = subObject.getString("threer");
                           fourr = subObject.getString("fourr");
                           fiver = subObject.getString("fiver");
                           totalreviews = subObject.getString("totalreviews");




                           JSONArray cat = obj.getJSONArray("categorylist");

                                  for(int i=0; i <cat.length();i++){
                                      JSONObject catobj = cat.getJSONObject(i);



                                      Categorylist relis = new Categorylist();

                                      relis.setSno(catobj.getString("sno"));
                                      relis.setName(catobj.getString("name"));
                                      relis.setLikescount(catobj.getString("likescount"));
                                      relis.setLdescription(catobj.getString("ldescription"));
                                      relis.setRating(catobj.getString("rating"));
                                      relis.setUsername(catobj.getString("username"));
                                      relis.setImage(catobj.getString("image"));
                                      relis.setUserliked(catobj.getString("userliked"));

                                      vatlistthings.add(relis);

                                  }


                           Sessiondata.getInstance().setSimplevalueend(vatlistthings);

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                       if(stag == 1){
                        /*   progressDialog.dismiss();
                           values = Sessiondata.getInstance().getSimplevalueend();
                           resvalue =  new Reviesvalue(getActivity(),values);
                           simlist.setAdapter(resvalue);*/
                       }

                       else {
                           progressDialog.dismiss();





                       }



                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       progressDialog.dismiss();
                       Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                   }
               })
               {
                   @Override
                   protected Map<String, String> getParams() {
                       Map<String, String> params = new HashMap<String,String>();
                       params.put("sno",rsnoval);
                       params.put("fb_id",rsfb_id);
                       return params;
                   }
               };

               RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
               requestQueue.add(stringRequest);
               progressDialog = new ProgressDialog(getActivity());
               progressDialog.setMessage("Please Wait....");
               progressDialog.show();





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
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }


    public NewUiView_itemInfo_Fragment() {
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




           private class WebPageTask extends AsyncTask<Void, List<NearByelementsModel>, List<NearByelementsModel>> implements View.OnClickListener{
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

                  // Call<NearbyelementResponse> call = a.GetNearByelements(did);




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

                       Sessiondata.getInstance().setNewuinearbyfoodcount(nem1.getTotalno());
                       Sessiondata.getInstance().setNewuinearbythingstodocount(nem3.getTotalno());
                       Sessiondata.getInstance().setNewuinearbyprayerscount(nem2.getTotalno());


                       foodcount.setText("Food Places");
                     //  thingstocount.setText("(" + nem3.getTotalno() + ")"  + " Attractions");
                       thingstocount.setText("Attractions");
                       //prayerscount.setText("(" + nem2.getTotalno() + ")" + " Prayer Spaces");
                       prayerscount.setText("Prayer Spaces");
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
                       Sessiondata.getInstance().setNewuinearbyfoodcount("0");
                       Sessiondata.getInstance().setNewuinearbythingstodocount("0");
                       Sessiondata.getInstance().setNewuinearbyprayerscount("0");

                       //foodcount.setText("(" + 0 + ")" + " Food Places");
                      // thingstocount.setText("(" + 0 + ")" + " Attractions");
                       foodcount.setText("Food Places");
                       thingstocount.setText("Attractions");
                     //  prayerscount.setText("(" + 0 + ")" + " Prayer Spaces");

                       prayerscount.setText("Prayer Spaces");

                       food.setTag(new NearByelementsModel("", "", "0"));
                       thingstodo.setTag(new NearByelementsModel("", "", "0"));
                       prayers.setTag(new NearByelementsModel("", "", "0"));
                   }

                   NearByelementsModel nem1 = nearByelementsModels.get(0);
                   NearByelementsModel nem2 = nearByelementsModels.get(1);
                   NearByelementsModel nem3 = nearByelementsModels.get(2);
                   Sessiondata.getInstance().getNewuinearbyfoodcount();
                   Sessiondata.getInstance().getNewuinearbythingstodocount();
                   Sessiondata.getInstance().getNewuinearbyprayerscount();

                   //prayerscount.getText().toString().charAt(1);

                   if(Sessiondata.getInstance().getNewuinearbyprayerscount().equals("0")){

                       prayearspacealavailable.setVisibility(View.GONE);
                       prayerfirst.setVisibility(View.GONE);

                   }
                   else {

                       CategoryID = "2";
                       districtval = nem2.getDistrict();
                       new  prayervalWebPageTasknearplace(CategoryID,districtval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                       prayearspacealavailable.setVisibility(View.VISIBLE);

                       prayerfirst.setVisibility(View.VISIBLE);
                       prayerfirst.setOnClickListener(this);
                   }

                   if(Sessiondata.getInstance().getNewuinearbyfoodcount().equals("0")){
                       foodanddrinksavailable.setVisibility(View.GONE);
                       foodsfrag1.setVisibility(View.GONE);

                   }
                   else {
                       CategoryID = "1";

                       //Toast.makeText(getActivity(),nem1.getDistrict(),Toast.LENGTH_LONG).show();


                       new foodanddrinkWebPageTasknearplace(CategoryID,nem1.getDistrict()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                       foodanddrinksavailable.setVisibility(View.VISIBLE);
                       foodsfrag1.setVisibility(View.VISIBLE);
                       foodsfrag1.setOnClickListener(this);

                   }


                   if( Sessiondata.getInstance().getNewuinearbythingstodocount().equals("0")){
                       thingstodoattractionavailable.setVisibility(View.GONE);
                       thigsfrag1.setVisibility(View.GONE);

                   }
                   else {
                       CategoryID = "3";

                       new thingstodoWebPageTasknearplace(CategoryID,nem3.getDistrict()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                       thingstodoattractionavailable.setVisibility(View.VISIBLE);

                       thigsfrag1.setVisibility(View.VISIBLE);
                       thigsfrag1.setOnClickListener(this);









                   }


                   thingstodo.setOnClickListener(this);





               }

               /**
                * Called when a view has been clicked.
                *
                * @param v The view that was clicked.
                */
               @Override
               public void onClick(View v) {

                    if (v == thigsfrag1){

                        thingstodonear = Sessiondata.getInstance().getThingsfrontdatanear();
                        thingstodonear.get(0).setCtm(newtrip);
                       Sessiondata.getInstance().setSnocreatenewtrip(thingstodonear.get(0).getSno());
                       Sessiondata.getInstance().setUpdateresult(0);
                       Sessiondata.getInstance().setFtplistval(7);

                       Fragment fr = new NewUiView_itemInfo_Fragment();
                       Bundle bundle = new Bundle();
                       bundle.putSerializable(EXTRATRIPINFO, thingstodonear.get(0));
                       fr.setArguments(bundle);
                       FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                       fc.replaceFragment(fr);

                   }

                   else if (v == foodsfrag1){


                        foodnear = Sessiondata.getInstance().getFoodfrontdatanear();
                        foodnear.get(0).setCtm(newtrip);
                        Sessiondata.getInstance().setSnocreatenewtrip(foodnear.get(0).getSno());
                        Sessiondata.getInstance().setUpdateresult(0);
                        Sessiondata.getInstance().setFtplistval(7);

                        Fragment fr = new NewUiView_itemInfo_Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(EXTRATRIPINFO, foodnear.get(0));
                        fr.setArguments(bundle);
                        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                        fc.replaceFragment(fr);
                    }
else if (v == prayerfirst) {


                        prayernear = Sessiondata.getInstance().getPrayerfrontdatanear();
                        prayernear.get(0).setCtm(newtrip);
                        Sessiondata.getInstance().setSnocreatenewtrip(prayernear.get(0).getSno());
                        Sessiondata.getInstance().setUpdateresult(0);
                        Sessiondata.getInstance().setFtplistval(7);

                        Fragment fr = new NewUiView_itemInfo_Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(EXTRATRIPINFO, prayernear.get(0));
                        fr.setArguments(bundle);
                        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                        fc.replaceFragment(fr);
                    }


                   else if(v == thingstodo){

                        NearByelementsModel n = (NearByelementsModel) v.getTag();
                        CreatedTripModel cc = C.getCtm();
                        cc.setCategoryID("3");
                        cc.setCategorytype("Things to do info");
                        cc.setN(n);
                        showguideFragment(cc);
                    }

               }
           }




           private class thingstodoWebPageTasknearplace extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse>  {
               ProgressDialog d = new ProgressDialog(_A);
               List<Categorylistmodel> items = new ArrayList<>();
               String subcatid;
               View vs;
               String district;
               private thingstodoWebPageTasknearplace(String subcatid, String district) {
                   this.subcatid = subcatid;
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
                                   Sessiondata.getInstance().setThingsfrontdatanear((ArrayList<Categorylistmodel>) items);


////

                                   thingstodonear = Sessiondata.getInstance().getThingsfrontdatanear();

                                   if (Sessiondata.getInstance().getNewuinearbythingstodocount().equals("1"))

                                   {
                                       attrationtitleone.setText(thingstodonear.get(0).getName());
                                       attrationsubtileone.setText(thingstodonear.get(0).getActivity());

                                       thingstodofirstfloat.setText(thingstodonear.get(0).getRating());

                                       AlertUtils.RatingColorGreen(getActivity(), attrationratbarone);
                                       try {
                                           attrationratbarone.setRating(Float.parseFloat(thingstodonear.get(0).getRating()));
                                       } catch (NumberFormatException e) {

                                       }
                                       // attrationaddressone.setText(thingstodonear.get(0).getDescription());


                                       attrationaddressone.setText("Within 500 meters");


                                       Glide.with(getActivity())
                                               .load(thingstodonear.get(0).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(attrationimageone);
                                       secondattraction.setVisibility(View.GONE);










                                   }



                                   else {

                                       secondattraction.setVisibility(View.GONE);

                                       attrationtitleone.setText(thingstodonear.get(0).getName());
                                       attrationsubtileone.setText(thingstodonear.get(0).getActivity());

                                       thingstodofirstfloat.setText(thingstodonear.get(0).getRating());



                                       AlertUtils.RatingColorGreen(getActivity(), attrationratbarone);
                                       try {
                                           attrationratbarone.setRating(Float.parseFloat(thingstodonear.get(0).getRating()));
                                       } catch (NumberFormatException e) {

                                       }
                                       //attrationaddressone.setText(thingstodonear.get(0).getDescription());


                                       attrationaddressone.setText("Within 500 meters");


                                       Glide.with(getActivity())
                                               .load(thingstodonear.get(0).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(attrationimageone);


















                                  /*     attrationtitletwo.setText(thingstodonear.get(1).getName());
                                       attrationsubtiletwo.setText(thingstodonear.get(1).getActivity());
                                       AlertUtils.RatingColorGreen(getActivity(), attrationratbartwo);
                                       try {
                                           attrationratbartwo.setRating(Float.parseFloat(thingstodonear.get(1).getRating()));
                                       } catch (NumberFormatException e) {

                                       }
                                       attrationaddresstwo.setText(thingstodonear.get(1).getDescription());
                                       Glide.with(getActivity())
                                               .load(thingstodonear.get(1).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(attrationimagetwo);
 }
*/

                                   }

                                 //  Toast.makeText(getActivity(),"things",Toast.LENGTH_SHORT).show();


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
                             //  Categorylist(items);
                               //itemsfinal = items;
                           }
                           d.dismiss();


                       }
                       else {
                           d.dismiss();
                           Snackbar snack = Snackbar.make(vs, "There is no item to show", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null);
                           ViewGroup group = (ViewGroup) snack.getView();
                           View v = snack.getView();
                           TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                           tv.setTextColor(Color.WHITE);
                           group.setBackgroundColor(getResources().getColor(R.color.cpb_red));
                           snack.show();}}}

               /**
                * Called when a view has been clicked.
                *
                * @param v The view that was clicked.
                */

           }






           private class foodanddrinkWebPageTasknearplace extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
               ProgressDialog d = new ProgressDialog(_A);
               List<Categorylistmodel> items = new ArrayList<>();
               String subcatid;
               View vs;
               String district;
               private foodanddrinkWebPageTasknearplace(String subcatid, String district) {
                   this.subcatid = subcatid;
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

                                   Sessiondata.getInstance().setFoodfrontdatanear((ArrayList<Categorylistmodel>) items);


                                   /////
                                   foodnear = Sessiondata.getInstance().getFoodfrontdatanear();

                                   if (Sessiondata.getInstance().getNewuinearbyfoodcount().equals("1"))

                                   {


                                       foodtitleone.setText(foodnear.get(0).getName());
                                       foodsubtileone.setText(foodnear.get(0).getActivity());

                                       foodsfirstfloat.setText(foodnear.get(0).getRating());



                                       AlertUtils.RatingColorGreen(getActivity(), foodratbarone);
                                       try {
                                           foodratbarone.setRating(Float.parseFloat(foodnear.get(0).getRating()));
                                       } catch (NumberFormatException e) {

                                       }
                                      // foodaddressone.setText(foodnear.get(0).getDescription());


                                       foodaddressone.setText("Within 500 meters");





                                       Glide.with(getActivity())
                                               .load(foodnear.get(0).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(foodimageone);



                                      foodsecondcount.setVisibility(View.GONE);

                                   }


                                   else {

                                       foodsecondcount.setVisibility(View.GONE);

                                       foodtitleone.setText(foodnear.get(0).getName());
                                       foodsubtileone.setText(foodnear.get(0).getActivity());
                                       foodsfirstfloat.setText(foodnear.get(0).getRating());
                                       AlertUtils.RatingColorGreen(getActivity(), foodratbarone);
                                       try {
                                           foodratbarone.setRating(Float.parseFloat(foodnear.get(0).getRating()));
                                       } catch (NumberFormatException e) {

                                       }
                                      // foodaddressone.setText(foodnear.get(0).getDescription());



                                       foodaddressone.setText("Within 500 meters");

                                     /*  Glide.with(getActivity())
                                               .load(foodnear.get(0).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(foodimageone);*/



                                      if(foodnear.get(0).getPhotos().equals(null)){


                                       }




else {
                                          String newsItem = foodnear.get(0).getPhotos().get(0).getPhotourl();


                                          if (newsItem.length() > 0) {
                                              try {
                                                  final String s = newsItem;
                                                  //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                                                  Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(foodimageone, new Callback() {
                                                      @Override
                                                      public void onSuccess() {

                                                      }

                                                      @Override
                                                      public void onError() {
                                                          //Try again online if cache failed
                                                          Picasso.with(getActivity())
                                                                  .load(s)
                                                                  .error(R.drawable.background_default)
                                                                  .placeholder(R.drawable.loading)
                                                                  .into(foodimageone, new Callback() {
                                                                      @Override
                                                                      public void onSuccess() {

                                                                      }

                                                                      @Override
                                                                      public void onError() {
                                                                          //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                                                      }
                                                                  });
                                                      }
                                                  });
                                              } catch (Exception e) {
                                                  e.printStackTrace();
                                              }
                                          }


                                      }













                                   }
                                   //  Toast.makeText(getActivity(),"food",Toast.LENGTH_SHORT).show();


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
                               //  Categorylist(items);
                               //itemsfinal = items;
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




           private class prayervalWebPageTasknearplace extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
               ProgressDialog d = new ProgressDialog(_A);
               List<Categorylistmodel> items = new ArrayList<>();
               String subcatid;
               View vs;
               String district;
               private prayervalWebPageTasknearplace(String subcatid, String district) {
                   this.subcatid = subcatid;
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
                                   Sessiondata.getInstance().setPrayerfrontdatanear((ArrayList<Categorylistmodel>) items);


                                   //////
                                   prayernear = Sessiondata.getInstance().getPrayerfrontdatanear();

                                   if (Sessiondata.getInstance().getNewuinearbyfoodcount().equals("1"))

                                   {
                                       prayertitleone.setText(prayernear.get(0).getName());
                                       prayersubtileone.setText(prayernear.get(0).getActivity());


                                       prayerfirstfloat.setText(prayernear.get(0).getRating());



                                       AlertUtils.RatingColorGreen(getActivity(), prayerratbarone);
                                       try {
                                           prayerratbarone.setRating(Float.parseFloat(prayernear.get(0).getRating()));
                                       } catch (NumberFormatException e) {

                                       }
                                      // prayeraddressone.setText(prayernear.get(0).getDescription());


                                       prayeraddressone.setText("Within 500 meters");




                                       Glide.with(getActivity())
                                               .load(prayernear.get(0).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(prayerimageone);



                                       prayersecondcount.setVisibility(View.GONE);

                                   }


                                   else {

                                       prayersecondcount.setVisibility(View.GONE);

                                       prayertitleone.setText(prayernear.get(0).getName());
                                       prayersubtileone.setText(prayernear.get(0).getActivity());



                                  //     prayerfirstfloat.setText(prayernear.get(0).getRating());


                                    //   Toast.makeText(getActivity(),prayernear.get(0).getRating().toString(),Toast.LENGTH_SHORT).show();


                                       AlertUtils.RatingColorGreen(getActivity(), prayerratbarone);
                                       try {
                                           prayerratbarone.setRating(Float.parseFloat(prayernear.get(0).getRating()));
                                       } catch (NumberFormatException e) {

                                       }







                                      // prayeraddressone.setText(prayernear.get(0).getDescription());


                                       prayeraddressone.setText("Within 500 meters");

                                       Glide.with(getActivity())
                                               .load(prayernear.get(0).getPhotos().get(0).getPhotourl())
                                               .placeholder(R.drawable.loading)
                                               .error(R.drawable.background_default)
                                               .into(prayerimageone);


                                   }

                                   //Toast.makeText(getActivity(),"prayer",Toast.LENGTH_SHORT).show();


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
                               //  Categorylist(items);
                               //itemsfinal = items;
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

else  if(v == improvelisting){


            Fragment fr = new Improvelisting();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

        /*else if (v == thingstodoattractionavailable)
        {
           Toast.makeText(getActivity(),"things",Toast.LENGTH_SHORT).show();

        }*/
       /* else if (v == peoplewviewd){

            getFragmentManager().popBackStack();
        }*/





    }


}
