package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.activity.Exporeallnewui;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.activity.Tourdetails;
import com.hhwt.travelplanner.adapter.Categorylistadapter;
import com.hhwt.travelplanner.adapter.UinewCategorylistadapter;
import com.hhwt.travelplanner.fragment.Tourlistdeatails;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.Imageval;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 12/09/16.
 */
public class SpecialdealsFragment extends Fragment  implements
        UinewCategorylistadapter.OnItemClickListener,View.OnClickListener{

    int thlowerlimt;
    int thuperlimit;

    String tosno;
    String cidval;
    View savethings;
    View save;
    String subcatid;
    public static Activity _A;
    public String fb_id, _CityName, _CityID, _CityImage;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String MyPREFERENCES = "HHWT";
    String mess;
    private RecyclerView.LayoutManager mLayoutManager;
    public int sortitemposition = 0;
    public static int clickeventcheck = 0;
    RecyclerView categoryitems;
    Retrofit retrofit;
    public CreatedTripModel newtrip;
    UinewCategorylistadapter adapter;
    TextView planyourtour;
    public static final String EXTRATRIPINFO = "Viewinfo";
    SquareImageView imagespecal1,imagetwo,imagethree;
    ArrayList<Tourdetails> Specialtours;
    String tdetailsimage,tdetailsrate,tdetailsreviews,tdescontent,tdeshoverview,tdlosoverview,tsesinquery;
    TextView title,subtitle,titletwo,subtitletwo,titlethree,subtitlethree;
    ArrayList<Imageval> imagedetails;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    CardView onetopcard,twotopcard,thirdtopcard;

    String useridval;

    @Override
    public void onFavItemClick(View view, Categorylistmodel viewModel) {
        if (clickeventcheck == 0) {
            clickeventcheck = 1;
            viewModel.setCtm(newtrip);
           // showViewDetFragment(viewModel);
          //  toolbarsearch.setVisibility(View.GONE);

        }
    }



    public SpecialdealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.specialdealsnewfragment_one, container, false);
        _A = getActivity();

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        useridval = Sessiondata.getInstance().getReviewfbemailid();



        newtrip = Sessiondata.getInstance().getCtripresult();
        Specialtours = Sessiondata.getInstance().getTourdetails();

        if(Specialtours!=null) {
            planyourtour = (TextView) v.findViewById(R.id.planyourtour);
            planyourtour.setOnClickListener(this);

            imagespecal1 = (SquareImageView) v.findViewById(R.id.image);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
            imagetwo = (SquareImageView) v.findViewById(R.id.imagetwo);
            titletwo = (TextView) v.findViewById(R.id.titletwo);
            subtitletwo = (TextView) v.findViewById(R.id.subtitletwo);
            imagethree = (SquareImageView) v.findViewById(R.id.imagethree);
            titlethree = (TextView) v.findViewById(R.id.titlethree);
            subtitlethree = (TextView) v.findViewById(R.id.subtitlethree);


            onetopcard = (CardView) v.findViewById(R.id.onetopcard);
            onetopcard.setOnClickListener(this);
            twotopcard = (CardView) v.findViewById(R.id.twotopcard);
            twotopcard.setOnClickListener(this);
            thirdtopcard = (CardView) v.findViewById(R.id.thirdtopcard);
            thirdtopcard.setOnClickListener(this);



            imagedetails = new ArrayList<Imageval>();
            String newsItem = Specialtours.get(0).getImage();

            if (newsItem.length() > 0) {
                try {
                    final String s = newsItem;
                    Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(imagespecal1, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Picasso.with(getActivity())
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(imagespecal1, new Callback() {
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

            title.setText("" + Specialtours.get(0).getContent());
            subtitle.setText("" + Specialtours.get(0).getDeparturedate());


            String newsItem1 = Specialtours.get(1).getImage();

            if (newsItem1.length() > 0) {
                try {
                    final String s = newsItem1;
                    Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(imagetwo, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Picasso.with(getActivity())
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(imagetwo, new Callback() {
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

            titletwo.setText("" + Specialtours.get(1).getContent());
            subtitletwo.setText("" + Specialtours.get(1).getDeparturedate());

            String newsItem2 = Specialtours.get(2).getImage();

            if (newsItem2.length() > 0) {
                try {
                    final String s = newsItem2;
                    Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(imagethree, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Picasso.with(getActivity())
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(imagethree, new Callback() {
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

            titlethree.setText("" + Specialtours.get(2).getContent());
            subtitlethree.setText("" + Specialtours.get(2).getDeparturedate());


        }


        else{
            onetopcard = (CardView) v.findViewById(R.id.onetopcard);
            onetopcard.setVisibility(View.GONE);

            twotopcard = (CardView) v.findViewById(R.id.twotopcard);
            twotopcard.setVisibility(View.GONE);
            thirdtopcard = (CardView) v.findViewById(R.id.thirdtopcard);
            thirdtopcard.setVisibility(View.GONE);

            planyourtour = (TextView) v.findViewById(R.id.planyourtour);
            planyourtour.setVisibility(View.GONE);



            View layouttoast = inflater.inflate(R.layout.toastcustom, (ViewGroup) v.findViewById(R.id.toastcustom));
            ((TextView) layouttoast.findViewById(R.id.texttoast)).setText("No special deals at the moment. Please check back later");

            Toast mytoast = new Toast(getActivity());
            mytoast.setView(layouttoast);
            mytoast.setDuration(Toast.LENGTH_LONG);
            mytoast.show();



        }

        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if(v == planyourtour){

            Sessiondata.getInstance().setExplorenewfullpage(3);


            tosno = Sessiondata.getInstance().getToursno();
            thingstodo();
           /* Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/
        }


        else if (v == onetopcard)
        {

            tdetailsimage = Specialtours.get(0).getImage();
            tdetailsrate = Specialtours.get(0).getRate();
            tdetailsreviews = Specialtours.get(0).getReviews();
            tdescontent =  Specialtours.get(0).getContent();
            tdeshoverview =   Specialtours.get(0).getOverviews();
            tdlosoverview =  Specialtours.get(0).getLong_overviews();
            tsesinquery =  Specialtours.get(0).getEnquiry();
            Sessiondata.getInstance().setWebidchange(Specialtours.get(0).getId());
            Sessiondata.getInstance().setWebsite(Specialtours.get(0).getWebsite());
            Sessiondata.getInstance().setTourimage(tdetailsimage);
            Sessiondata.getInstance().setTourcontent(tdescontent);
            Sessiondata.getInstance().setTourcuren(Specialtours.get(0).getCurrence());
            Sessiondata.getInstance().setTourrate(tdetailsrate);
            Sessiondata.getInstance().setTourreview(tdetailsreviews);
            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
            Sessiondata.getInstance().setTourenqiry(tsesinquery);
            Sessiondata.getInstance().setToursubid(Specialtours.get(0).getSub_id());
            Sessiondata.getInstance().setTourid(Specialtours.get(0).getId());
            Sessiondata.getInstance().setLocationname(Specialtours.get(0).getLocation());
            Sessiondata.getInstance().setAddtionalcon(Specialtours.get(0).getAddi_info());
            Sessiondata.getInstance().setCancelceon(Specialtours.get(0).getCancellation_policy());
            Sessiondata.getInstance().setTouroperatorlink(Specialtours.get(0).getTour_opt_info());
            Sessiondata.getInstance().setTouroperatorlinkurl(Specialtours.get(0).getTour_opt_link());
            Sessiondata.getInstance().setExclusions(Specialtours.get(0).getExclusion());
            Sessiondata.getInstance().setDeppoint(Specialtours.get(0).getDeparturepoint());
            Sessiondata.getInstance().setDepdate(Specialtours.get(0).getDeparturedate());
            Sessiondata.getInstance().setDeptime(Specialtours.get(0).getDeparturetime());
            Sessiondata.getInstance().setDuration(Specialtours.get(0).getDuration());
            Sessiondata.getInstance().setReturndetails(Specialtours.get(0).getReturndetails());
            Sessiondata.getInstance().setOve1(Specialtours.get(0).getOverview1());
            Sessiondata.getInstance().setOver2(Specialtours.get(0).getOverview2());
            Imageval img1 = new Imageval();
            img1.setImage(Specialtours.get(0).getImage());
            img1.setId("1");
            imagedetails.add(img1);
            Imageval img2 = new Imageval();
            img2.setImage(Specialtours.get(0).getImagetwo());
            img2.setId("2");
            imagedetails.add(img2);
            Imageval img3 = new Imageval();
            img3.setImage(Specialtours.get(0).getImagethree());
            img3.setId("3");
            imagedetails.add(img3);
            Imageval img4 = new Imageval();
            img4.setImage(Specialtours.get(0).getImagefour());
            img4.setId("3");
            imagedetails.add(img4);
            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
            Fragment fr = new Tourlistdeatails();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

        else if (v == twotopcard){
            tdetailsimage = Specialtours.get(1).getImage();
            tdetailsrate = Specialtours.get(1).getRate();
            tdetailsreviews = Specialtours.get(1).getReviews();
            tdescontent =  Specialtours.get(1).getContent();
            tdeshoverview =   Specialtours.get(1).getOverviews();
            tdlosoverview =  Specialtours.get(1).getLong_overviews();
            tsesinquery =  Specialtours.get(1).getEnquiry();
            Sessiondata.getInstance().setWebidchange(Specialtours.get(1).getId());
            Sessiondata.getInstance().setWebsite(Specialtours.get(1).getWebsite());
            Sessiondata.getInstance().setTourimage(tdetailsimage);
            Sessiondata.getInstance().setTourcontent(tdescontent);
            Sessiondata.getInstance().setTourcuren(Specialtours.get(1).getCurrence());
            Sessiondata.getInstance().setTourrate(tdetailsrate);
            Sessiondata.getInstance().setTourreview(tdetailsreviews);
            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
            Sessiondata.getInstance().setTourenqiry(tsesinquery);
            Sessiondata.getInstance().setToursubid(Specialtours.get(1).getSub_id());
            Sessiondata.getInstance().setTourid(Specialtours.get(1).getId());
            Sessiondata.getInstance().setLocationname(Specialtours.get(1).getLocation());
            Sessiondata.getInstance().setAddtionalcon(Specialtours.get(1).getAddi_info());
            Sessiondata.getInstance().setCancelceon(Specialtours.get(1).getCancellation_policy());
            Sessiondata.getInstance().setTouroperatorlink(Specialtours.get(1).getTour_opt_info());
            Sessiondata.getInstance().setTouroperatorlinkurl(Specialtours.get(1).getTour_opt_link());
            Sessiondata.getInstance().setExclusions(Specialtours.get(1).getExclusion());
            Sessiondata.getInstance().setDeppoint(Specialtours.get(1).getDeparturepoint());
            Sessiondata.getInstance().setDepdate(Specialtours.get(1).getDeparturedate());
            Sessiondata.getInstance().setDeptime(Specialtours.get(1).getDeparturetime());
            Sessiondata.getInstance().setDuration(Specialtours.get(1).getDuration());
            Sessiondata.getInstance().setReturndetails(Specialtours.get(1).getReturndetails());
            Sessiondata.getInstance().setOve1(Specialtours.get(1).getOverview1());
            Sessiondata.getInstance().setOver2(Specialtours.get(1).getOverview2());
            Imageval img1 = new Imageval();
            img1.setImage(Specialtours.get(1).getImage());
            img1.setId("1");
            imagedetails.add(img1);
            Imageval img2 = new Imageval();
            img2.setImage(Specialtours.get(1).getImagetwo());
            img2.setId("2");
            imagedetails.add(img2);
            Imageval img3 = new Imageval();
            img3.setImage(Specialtours.get(1).getImagethree());
            img3.setId("3");
            imagedetails.add(img3);
            Imageval img4 = new Imageval();
            img4.setImage(Specialtours.get(1).getImagefour());
            img4.setId("3");
            imagedetails.add(img4);
            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
            Fragment fr = new Tourlistdeatails();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

else if (v == thirdtopcard){

            tdetailsimage = Specialtours.get(2).getImage();
            tdetailsrate = Specialtours.get(2).getRate();
            tdetailsreviews = Specialtours.get(2).getReviews();
            tdescontent =  Specialtours.get(2).getContent();
            tdeshoverview =   Specialtours.get(2).getOverviews();
            tdlosoverview =  Specialtours.get(2).getLong_overviews();
            tsesinquery =  Specialtours.get(2).getEnquiry();
            Sessiondata.getInstance().setWebidchange(Specialtours.get(2).getId());
            Sessiondata.getInstance().setWebsite(Specialtours.get(2).getWebsite());
            Sessiondata.getInstance().setTourimage(tdetailsimage);
            Sessiondata.getInstance().setTourcontent(tdescontent);
            Sessiondata.getInstance().setTourcuren(Specialtours.get(2).getCurrence());
            Sessiondata.getInstance().setTourrate(tdetailsrate);
            Sessiondata.getInstance().setTourreview(tdetailsreviews);
            Sessiondata.getInstance().setTourOverviews(tdeshoverview);
            Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
            Sessiondata.getInstance().setTourenqiry(tsesinquery);
            Sessiondata.getInstance().setToursubid(Specialtours.get(2).getSub_id());
            Sessiondata.getInstance().setTourid(Specialtours.get(2).getId());
            Sessiondata.getInstance().setLocationname(Specialtours.get(2).getLocation());
            Sessiondata.getInstance().setAddtionalcon(Specialtours.get(2).getAddi_info());
            Sessiondata.getInstance().setCancelceon(Specialtours.get(2).getCancellation_policy());
            Sessiondata.getInstance().setTouroperatorlink(Specialtours.get(2).getTour_opt_info());
            Sessiondata.getInstance().setTouroperatorlinkurl(Specialtours.get(2).getTour_opt_link());
            Sessiondata.getInstance().setExclusions(Specialtours.get(2).getExclusion());
            Sessiondata.getInstance().setDeppoint(Specialtours.get(2).getDeparturepoint());
            Sessiondata.getInstance().setDepdate(Specialtours.get(2).getDeparturedate());
            Sessiondata.getInstance().setDeptime(Specialtours.get(2).getDeparturetime());
            Sessiondata.getInstance().setDuration(Specialtours.get(2).getDuration());
            Sessiondata.getInstance().setReturndetails(Specialtours.get(2).getReturndetails());
            Sessiondata.getInstance().setOve1(Specialtours.get(2).getOverview1());
            Sessiondata.getInstance().setOver2(Specialtours.get(2).getOverview2());
            Imageval img1 = new Imageval();
            img1.setImage(Specialtours.get(2).getImage());
            img1.setId("1");
            imagedetails.add(img1);
            Imageval img2 = new Imageval();
            img2.setImage(Specialtours.get(2).getImagetwo());
            img2.setId("2");
            imagedetails.add(img2);
            Imageval img3 = new Imageval();
            img3.setImage(Specialtours.get(2).getImagethree());
            img3.setId("3");
            imagedetails.add(img3);
            Imageval img4 = new Imageval();
            img4.setImage(Specialtours.get(2).getImagefour());
            img4.setId("3");
            imagedetails.add(img4);
            Sessiondata.getInstance().setImgphotosdetails(imagedetails);
            Fragment fr = new Tourlistdeatails();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }



    }





    private void thingstodo() {

        cidval = "3";

        thlowerlimt = 20;
        thuperlimit = 0;


        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();



        new thingWebPageTask(savethings, cidval, thuperlimit, thlowerlimt,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }


    private class thingWebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {


        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();



        String subcatid,useridval;
        View vs;
        int thuperlimit;
        int thlowerlimt;
        private thingWebPageTask(View vs, String subcatid, int thuperlimit, int thlowerlimt,String useridval) {
            this.subcatid = subcatid;
            this.vs = vs;
            this.thlowerlimt = thlowerlimt;
            this.thuperlimit = thuperlimit;
            this.useridval = useridval;
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
            // Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid, tosno);

            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapiincrement(subcatid, tosno, thuperlimit, thlowerlimt,useridval);
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

            }}}

    private void foodanddrinks() {

        cidval = "1";

        thlowerlimt = 20;
        thuperlimit = 0;



        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //  new WebPageTask(save, cidval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        new WebPageTask(savethings, cidval, thuperlimit, thlowerlimt,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);





    }







    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid,useridval;
        View vs;
        int thuperlimit;
        int thlowerlimt;
        private WebPageTask(View vs, String subcatid, int thuperlimit, int thlowerlimt,String useridval) {
            this.subcatid = subcatid;
            this.vs = vs;
            this.thlowerlimt = thlowerlimt;
            this.thuperlimit = thuperlimit;
            this.useridval = useridval;
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
            // Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid, tosno);

            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapiincrement(subcatid, tosno, thuperlimit, thlowerlimt,useridval);
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



                    Sessiondata.getInstance().setScroolthings(1);
                    Sessiondata.getInstance().setScroolfood(1);


                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);

                }


                else if (c.Status == 0) {
                    d.dismiss();




                    Sessiondata.getInstance().setFoodcatgory(null);

                    Fragment fr = new Exporeallnewui();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);

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
















}