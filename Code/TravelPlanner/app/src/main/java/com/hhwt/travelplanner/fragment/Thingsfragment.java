package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.activity.Exporeallnewui;
import com.hhwt.travelplanner.activity.NewUiView_itemInfo_Fragment;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.adapter.UinewCategorylistadapter;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
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
public class Thingsfragment extends Fragment implements  UinewCategorylistadapter.OnItemClickListener,View.OnClickListener{
    String subcatid;
    public static Activity _A;
    public String fb_id, _CityName, _CityID, _CityImage;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String MyPREFERENCES = "HHWT";
    String mess;
    View vs;
    String tosno;
    String cidval;
    View savethings;
    View save;

    int thlowerlimt;
    int thuperlimit;
    TextView thingstodofirstfloat,thingstodosecondfloat,thingstodothirdfloat;
    private RecyclerView.LayoutManager mLayoutManager;
    public int sortitemposition = 0;
    public static int clickeventcheck = 0;
    //RecyclerView categoryitems;
    Retrofit retrofit;
    ImageView backclick;
LinearLayout specialdealsavalible3;
    public CreatedTripModel newtrip;
    UinewCategorylistadapter adapter;
    ArrayList<Categorylistmodel> Specialtours;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    RatingBar item_ratingBar,item_ratingBartwo,item_ratingBarthree;
    CardView onetopcard,twotopcard,thirdtopcard;
    TextView title,subtitle,address;
    SquareImageView imagespecal1,imagetwo,imagethree;
    TextView planyourtour,titletwo,subtitletwo,addresstwo,titlethree,subtitlethree,addressthree;
TextView nopray,nopray2,nopray3;

    String Prayercountthree,Prayercounttwo,Prayercount,foodscount,foodcounttwo,foodcountthree,useridval;

    LinearLayout specialdealsavalible2,specialdealsavalible1;

    public Thingsfragment() {

    }


    TextView specialdelascountthingstodo3;
    TextView specialdelascountthingstodo2,specialdelascountthingstodo1;
    public static final String EXTRATRIPINFO = "Viewinfo";

    @Override
    public void onFavItemClick(View view, Categorylistmodel viewModel) {
        if (clickeventcheck == 0) {
            clickeventcheck = 1;

            viewModel.setCtm(newtrip);
         //   showViewDetFragment(viewModel);


        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _A=getActivity();


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        View v = inflater.inflate(R.layout.thingsfragment_one, container, false);




        useridval = Sessiondata.getInstance().getReviewfbemailid();




       // newtrip = Sessiondata.getInstance().getCtripresult();






        Specialtours = Sessiondata.getInstance().getThingstodonewuimainnew();

        if(Specialtours!=null) {

            planyourtour = (TextView) v.findViewById(R.id.planyourtour);
            planyourtour.setOnClickListener(this);



            imagespecal1 = (SquareImageView) v.findViewById(R.id.image);

            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
            address = (TextView) v.findViewById(R.id.address);

            specialdealsavalible3 = (LinearLayout) v.findViewById(R.id.specialdealsavalible3);

            specialdealsavalible2 = (LinearLayout) v.findViewById(R.id.specialdealsavalible2);
            specialdelascountthingstodo2 = (TextView) v.findViewById(R.id.specialdelascountthingstodo2);

            specialdealsavalible1 = (LinearLayout) v.findViewById(R.id.specialdealsavalible1);


            specialdelascountthingstodo3 = (TextView) v.findViewById(R.id.specialdelascountthingstodo3);




            thingstodofirstfloat = (TextView) v.findViewById(R.id.thingstodofirstfloat);

            item_ratingBar = (RatingBar) v.findViewById(R.id.item_ratingBar);
            imagetwo = (SquareImageView) v.findViewById(R.id.imagetwo);
            titletwo = (TextView) v.findViewById(R.id.titletwo);
            subtitletwo = (TextView) v.findViewById(R.id.subtitletwo);
            addresstwo = (TextView) v.findViewById(R.id.addresstwo);

            thingstodosecondfloat = (TextView) v.findViewById(R.id.thingstodosecondfloat);

            item_ratingBartwo = (RatingBar) v.findViewById(R.id.item_ratingBartwo);
            imagethree = (SquareImageView) v.findViewById(R.id.imagethree);
            titlethree = (TextView) v.findViewById(R.id.titlethree);
            subtitlethree = (TextView) v.findViewById(R.id.subtitlethree);
            addressthree = (TextView) v.findViewById(R.id.addressthree);
            item_ratingBarthree = (RatingBar) v.findViewById(R.id.item_ratingBarthree);
            thingstodothirdfloat = (TextView) v.findViewById(R.id.thingstodothirdfloat);

            nopray = (TextView) v.findViewById(R.id.nopray);
            nopray2 = (TextView) v.findViewById(R.id.nopray2);
            nopray3 = (TextView) v.findViewById(R.id.nopray3);






            onetopcard = (CardView) v.findViewById(R.id.onetopcard);
            onetopcard.setOnClickListener(this);
            twotopcard = (CardView) v.findViewById(R.id.twotopcard);
            twotopcard.setOnClickListener(this);
            thirdtopcard = (CardView) v.findViewById(R.id.thirdtopcard);
            thirdtopcard.setOnClickListener(this);




            newtrip = new CreatedTripModel();


            planyourtour = (TextView) v.findViewById(R.id.planyourtour);
            planyourtour.setOnClickListener(this);

            String newsItem = Specialtours.get(0).getPhotos().get(0).getPhotourl();
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

            title.setText(""+Specialtours.get(0).getName());
            subtitle.setText(""+Specialtours.get(0).getActivity());



            foodscount = Specialtours.get(0).getFoodc();

            if(foodscount.equals("0")){
                address.setText("No nearby food place");

            }
            else {
                address.setText(foodscount +" nearby food place");

            }





            if(Specialtours.get(0).getTours().equals("0")){

                specialdealsavalible1.setVisibility(View.GONE);
            }
            else {

                specialdealsavalible1.setVisibility(View.VISIBLE);
                specialdelascountthingstodo1.setText(Specialtours.get(0).getTours() + "  Special Deals Available");

            }






            Prayercount = Specialtours.get(0).getPrayerc();

if(Prayercount.equals("0"))
{
    nopray.setText("No prayer space nearby");

}

 else {

    nopray.setText(Prayercount+" prayer space nearby");

}


            Prayercounttwo = Specialtours.get(1).getPrayerc();


            if(Prayercounttwo.equals("0"))
            {
                nopray2.setText("No prayer space nearby");

            }

            else {

                nopray2.setText(Prayercounttwo+" prayer space nearby");

            }


            Prayercountthree = Specialtours.get(2).getPrayerc();




            if(Prayercountthree.equals("0"))
            {
                nopray3.setText("No prayer space nearby");

            }

            else {

                nopray3.setText(Prayercountthree+" prayer space nearby");

            }






            AlertUtils.RatingColorGreen(getActivity(), item_ratingBar);
            try {
                item_ratingBar.setRating(Float.parseFloat(Specialtours.get(0).getRating()));
            } catch (NumberFormatException e) {

            }

            thingstodofirstfloat.setText(Specialtours.get(0).getRating());



            String newsItem1 = Specialtours.get(1).getPhotos().get(0).getPhotourl();

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

            titletwo.setText(""+Specialtours.get(1).getName());
            subtitletwo.setText(""+Specialtours.get(1).getActivity());



            foodcounttwo = Specialtours.get(1).getFoodc();


            if(foodcounttwo.equals("0")){
                addresstwo.setText("No nearby food place");

            }
            else {
                addresstwo.setText(foodcounttwo +" nearby food place");

            }



            if(Specialtours.get(1).getTours().equals("0")){

                specialdealsavalible2.setVisibility(View.GONE);
            }
            else {

                specialdealsavalible2.setVisibility(View.VISIBLE);
                specialdelascountthingstodo2.setText(Specialtours.get(1).getTours() + "  Special Deals Available");

            }












            AlertUtils.RatingColorGreen(getActivity(), item_ratingBartwo);
            try {
                item_ratingBartwo.setRating(Float.parseFloat(Specialtours.get(1).getRating()));
            } catch (NumberFormatException e) {

            }

            thingstodosecondfloat.setText(Specialtours.get(1).getRating());







            String newsItem2 = Specialtours.get(2).getPhotos().get(0).getPhotourl();
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




            titlethree.setText(""+Specialtours.get(2).getName());
            subtitlethree.setText(""+Specialtours.get(2).getActivity());

            addressthree.setText(""+Specialtours.get(2).getDescription());





            foodcountthree = Specialtours.get(2).getFoodc();


            if(foodcountthree.equals("0")){
                addressthree.setText("No nearby food place");

            }
            else {
                addressthree.setText(foodcountthree +" nearby food place");

            }









            AlertUtils.RatingColorGreen(getActivity(), item_ratingBarthree);
            try {
                item_ratingBarthree.setRating(Float.parseFloat(Specialtours.get(2).getRating()));
            } catch (NumberFormatException e) {

            }

            thingstodothirdfloat.setText(Specialtours.get(2).getRating());








            if(Specialtours.get(2).getTours().equals("0")){

                specialdealsavalible3.setVisibility(View.GONE);
            }
            else {

                specialdealsavalible3.setVisibility(View.VISIBLE);

                specialdelascountthingstodo3.setText(Specialtours.get(2).getTours() + "  Special Deals Available");

            }









        }

        else{

            onetopcard = (CardView) v.findViewById(R.id.onetopcard);
            onetopcard.setVisibility(View.GONE);

            twotopcard = (CardView) v.findViewById(R.id.twotopcard);
            twotopcard.setVisibility(View.GONE);
            thirdtopcard = (CardView) v.findViewById(R.id.thirdtopcard);
            thirdtopcard.setVisibility(View.GONE);


            View layouttoast = inflater.inflate(R.layout.toastcustom, (ViewGroup) v.findViewById(R.id.toastcustom));
            ((TextView) layouttoast.findViewById(R.id.texttoast)).setText("Sorry, we're still populating our database for Attractions!");

            Toast mytoast = new Toast(getActivity());
            mytoast.setView(layouttoast);
            mytoast.setDuration(Toast.LENGTH_LONG);
            mytoast.show();

        }


        return v;

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(v == planyourtour) {

            Sessiondata.getInstance().setExplorenewfullpage(1);

            tosno = Sessiondata.getInstance().getToursno();
            thingstodo();
           /* Fragment fr = new Exporeallnewui();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/

        }





        else if (v == onetopcard)
        {

            Specialtours.get(0).setCtm(newtrip);

            Sessiondata.getInstance().setSnocreatenewtrip(Specialtours.get(0).getSno());
            Sessiondata.getInstance().setUpdateresult(0);
            Sessiondata.getInstance().setFtplistval(4);



          //  Fragment fr = new View_itemInfo_Fragment();


            Fragment fr = new NewUiView_itemInfo_Fragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRATRIPINFO, Specialtours.get(0));
            fr.setArguments(bundle);
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

        else if (v == twotopcard)

        {




            Specialtours.get(1).setCtm(newtrip);

            Sessiondata.getInstance().setSnocreatenewtrip(Specialtours.get(1).getSno());
            Sessiondata.getInstance().setUpdateresult(0);
            Sessiondata.getInstance().setFtplistval(4);

           // Fragment fr = new View_itemInfo_Fragment();

            Fragment fr = new NewUiView_itemInfo_Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRATRIPINFO, Specialtours.get(1));
            fr.setArguments(bundle);
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);

        }


        else if (v == thirdtopcard){

            Specialtours.get(2).setCtm(newtrip);

            Sessiondata.getInstance().setSnocreatenewtrip(Specialtours.get(2).getSno());
            Sessiondata.getInstance().setUpdateresult(0);
            Sessiondata.getInstance().setFtplistval(4);

           // Fragment fr = new View_itemInfo_Fragment();


            Fragment fr = new NewUiView_itemInfo_Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRATRIPINFO, Specialtours.get(2));
            fr.setArguments(bundle);
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


        new WebPageTask(savethings, cidval, thuperlimit, thlowerlimt).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);





    }







    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid;
        View vs;
        int thuperlimit;
        int thlowerlimt;
        private WebPageTask(View vs, String subcatid, int thuperlimit, int thlowerlimt) {
            this.subcatid = subcatid;
            this.vs = vs;
            this.thlowerlimt = thlowerlimt;
            this.thuperlimit = thuperlimit;
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
