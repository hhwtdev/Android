package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.adapter.UinewCategorylistadapter;
import com.hhwt.travelplanner.fragment.View_itemInfo_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.interfaces.SearchInterface;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
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
 * Created by jeyavijay on 22/09/16.
 */
public class ExplorenewThingsfragment extends Fragment  implements View.OnClickListener{
    public static final int RESULT_OK = -1;
    String subcatid;
    public static Activity _A;
    public String fb_id, _CityName, _CityID, _CityImage;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String MyPREFERENCES = "HHWT";
    String mess;
    ImageView toolbarsearch;
    private RecyclerView.LayoutManager mLayoutManager;
    public int sortitemposition = 0;
    public static int clickeventcheck = 0;
    RecyclerView categoryitems;
    Retrofit retrofit;

    public static final String EXTRATRIPINFO = "Viewinfo";
    public CreatedTripModel newtrip;
    emphistoryAdapter emphistory;
    private CreatedTripModel ctm;
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    ArrayList<Categorylistmodel> tourdetailsvalue;
   // RelativeLayout parentview;
    ListView save;
    ImageView filter;
    int i;
    public SearchBox search;
    ImageView backclick;
    String cidval;
    int thlowerlimt;
    int thuperlimit;
    int pageCount = 1;
    View footer;
    View  footerView;
    View savethings;
    String tosno;
    ArrayList<Categorylistmodel> tourdetailsvaluescrool;
int uppval = 20;
    int total;
    String useridval;



/*
    @Override
    public void onFavItemClick(View view, Categorylistmodel viewModel) {
        if (clickeventcheck == 0) {
            clickeventcheck = 1;

         //   Sessiondata.getInstance().getCtripresult();
          //  Log.d("tripval", "" + Sessiondata.getInstance().getCtripresult());

           // viewModel.setCtm(Sessiondata.getInstance().getCtripresult());



            viewModel.setCtm(newtrip);

            showViewDetFragment(viewModel);
            //  toolbarsearch.setVisibility(View.GONE);

        }
    }*/


    boolean loadingMore = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.explorethingstodoui,container,false);



        _A=getActivity();
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        newtrip = new CreatedTripModel();
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


      //  parentview = (RelativeLayout) v.findViewById(R.id.parentview);

       /* toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.VISIBLE);
*/
       /* toolbarsearch = (ImageView) v.findViewById(R.id.search);
        toolbarsearch.setVisibility(View.VISIBLE);

        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);
*/




        useridval = Sessiondata.getInstance().getReviewfbemailid();











            save = (ListView) v.findViewById(R.id.categoryitems);

       /* // Add footer view
        footer = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.progressdown, null, false);
        save.addFooterView(footer);
*/

        //add the footer before adding the adapter, else the footer will not load!
          footerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loadingview, null, false);
         save.addFooterView(footerView);

        tourdetailsvalue = Sessiondata.getInstance().getThingstodonewuimain();

        if(tourdetailsvalue!=null){
            emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
            save.setAdapter(emphistory);
        }
        else{


            View layouttoast = inflater.inflate(R.layout.toastcustom, (ViewGroup) v.findViewById(R.id.toastcustom));
            ((TextView) layouttoast.findViewById(R.id.texttoast)).setText("Sorry, we're still populating our database for Attractions!");

            Toast mytoast = new Toast(getActivity());
            mytoast.setView(layouttoast);
            mytoast.setDuration(Toast.LENGTH_LONG);
            mytoast.show();
        }




if(Sessiondata.getInstance().getScroolthings() == 1) {


    // Implementing scroll refresh
    save.setOnScrollListener(new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        @Override
        public void onScroll(AbsListView absListView, int firstItem, int visibleItemCount, int totalItems) {
            Log.e("Get position", "--firstItem:" + firstItem + "  visibleItemCount:" + visibleItemCount + "  totalItems:" + totalItems + "  pageCount:" + pageCount);
               /*  total = firstItem + visibleItemCount;


                // Total array list i have so it
                if (pageCount >= 1) {

                    if (total == totalItems) {

                        tosno = Sessiondata.getInstance().getToursno();
                        uppval = uppval + 20;
                        tourdetailsvaluescrool = new ArrayList<Categorylistmodel>();
                        thingstodo();
                        pageCount++;

                    }



                 else{
                        Log.e("more", "moreload");
                        save.removeFooterView(footer);
                    }

                } else {
                    Log.e("hide footer", "footer hide");
                    save.removeFooterView(footer);
                }
            }
        });
*/


            //what is the bottom item that is visible
            int lastInScreen = firstItem + visibleItemCount;

            //  Toast.makeText(getActivity(),String.valueOf(lastInScreen),Toast.LENGTH_LONG).show();

            //is the bottom item visible & not loading more already? Load more!

            //  Toast.makeText(getActivity(),String.valueOf(totalItems),Toast.LENGTH_LONG).show();

            if (pageCount <= 4) {

                if ((lastInScreen == totalItems) && !(loadingMore)) {


                    //Reset the array that holds the new items
                    tourdetailsvaluescrool = new ArrayList<Categorylistmodel>();

                    tosno = Sessiondata.getInstance().getToursno();
                    uppval = uppval + 10;
                    thingstodo();
                } else {
                    Log.e("hide footer", "footer hide");
                    save.removeFooterView(footerView);
                }


            } else {
                Log.e("more", "moreload");
                save.removeFooterView(footerView);


            }


        }

    });


}


        else
{

    save.removeFooterView(footerView);
}



        save.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                tourdetailsvalue.get(position).setCtm(newtrip);


                Sessiondata.getInstance().setSnocreatenewtrip(tourdetailsvalue.get(position).getSno());
                Sessiondata.getInstance().setUpdateresult(0);
                Sessiondata.getInstance().setFtplistval(4);




                Fragment fr = new NewUiView_itemInfo_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRATRIPINFO, tourdetailsvalue.get(position));
                fr.setArguments(bundle);
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);}
            //showViewDetFragment();



        });


   /*     Fragment fr = new View_itemInfo_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRATRIPINFO, tourdetailsvalue.get(position));
                fr.setArguments(bundle);
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);}




        });*/








        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == backclick){

            int i;
            i = Sessiondata.getInstance().getBackfilter();

            if (i == 1) {
                getFragmentManager().popBackStack();

            }
            else {
                Fragment fr = new New_Explore_activities_Fragment();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
        }
    }








    private Runnable loadMoreListItems = new Runnable() {

        @Override
        public void run() {

            //Set flag so we cant load new items 2 at the same time
            loadingMore = true;





            //Simulate a delay, delete this on a production environment!
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
            }






            tourdetailsvaluescrool  = Sessiondata.getInstance().getScroolthingstodonewuimain();


if(tourdetailsvaluescrool.equals(null)){

}




      /*      //Get 10 new list items
            for (int i = 0; i < 10; i++) {

                //Fill the item with some bogus information
                myListItems.add("item" + i);
            }*/

            else {

    for (int i = 0; i < tourdetailsvaluescrool.size(); i++) {
        tourdetailsvalue.add(tourdetailsvaluescrool.get(i));
    }


}



            //Done! now continue on the UI thread
            getActivity().runOnUiThread(returnRes);
        }
    };

    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {

            //Loop through the new items and add them to the adapter
            if (tourdetailsvaluescrool != null && tourdetailsvaluescrool.size() > 0) {

                for (int i = 0; i < tourdetailsvaluescrool.size(); i++)
                    //adapter.add(myListItems.get(i));
                tourdetailsvalue.add(tourdetailsvaluescrool.get(i));
            }

            //Update the Application title
          //  setTitle("Never ending List with " + String.valueOf(adapter.getCount()) + " items");

            //Tell to the adapter that changes have been made, this will cause the list to refresh
            emphistory.notifyDataSetChanged();

            save.setAdapter(emphistory);



         //   save.smoothScrollToPosition(emphistory.getCount() -1);

/*
            tourdetailsvaluescrool  = Sessiondata.getInstance().getScroolthingstodonewuimain();
            for (int i = 0; i < tourdetailsvaluescrool.size(); i++) {
                tourdetailsvalue.add(tourdetailsvaluescrool.get(i));
            }

            emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
            emphistory.notifyDataSetChanged();
            save.setAdapter(emphistory);
            //save.setSelection(total);*/







            //Done loading more.
            loadingMore = false;
        }

    };


















    private void thingstodo() {

        cidval = "3";

        thlowerlimt = 20;
        thuperlimit = uppval;

       // Toast.makeText(getActivity(),String.valueOf(thuperlimit),Toast.LENGTH_LONG).show();


        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();



        new thingWebPageTask(savethings, cidval, thuperlimit, thlowerlimt,useridval).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }


    private class thingWebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {


       // ProgressDialog d = new ProgressDialog(_A);
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

          //  d.setMessage("Please wait...");
           // d.show();
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


                            Sessiondata.getInstance().setScroolthingstodonewuimain((ArrayList<Categorylistmodel>) items);

                           // Sessiondata.getInstance().setThingstodonewuimain((ArrayList<Categorylistmodel>) items);


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





                    }
                   // d.dismiss();

/*
                    tourdetailsvaluescrool  = Sessiondata.getInstance().getScroolthingstodonewuimain();
                    for (int i = 0; i < tourdetailsvaluescrool.size(); i++) {
                        tourdetailsvalue.add(tourdetailsvaluescrool.get(i));
                    }

                    emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
                    emphistory.notifyDataSetChanged();
                    save.setAdapter(emphistory);
                    //save.setSelection(total);*/


//start a new thread for loading the items in the list
                    Thread thread = new Thread(null, loadMoreListItems);
                    thread.start();

                }

                else if (c.Status == 0) {
                  //  d.dismiss();

                    Sessiondata.getInstance().setThingstodonewuimain(null);
                    save.removeFooterView(footerView);
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




/*

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
          //  parentview.setVisibility(View.VISIBLE);
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


        tourdetailsvalue = Sessiondata.getInstance().getThingstodonewuimain();

        if(tourdetailsvalue!=null){
            emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
            save.setAdapter(emphistory);
        }
        else{

        }




    }
*/







    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        String Prayercount,foodscount;
        int height = 0;

        ArrayList<Categorylistmodel> sing_in_datas;
        private int lastPosition = -1;
        public emphistoryAdapter(Context con, ArrayList<Categorylistmodel> singindatas) {
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
        @Override public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            if (null == view) {
                view = mLayoutInflater.inflate(R.layout.newadapter_categorylist, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.title);
                viewHolder.tourimage = (SquareImageView) view.findViewById(R.id.image);
                viewHolder.subtitle = (RobotoTextView) view.findViewById(R.id.subtitle);
                viewHolder.address = (RobotoTextView) view.findViewById(R.id.address);
                viewHolder.cd = (CardView) view.findViewById(R.id.topcard);
                viewHolder.item_ratingBar = (RatingBar) view.findViewById(R.id.item_ratingBar);
                viewHolder.linearlay = (LinearLayout) view.findViewById(R.id.linearlay);
                viewHolder.thingstodofirstfloat = (TextView) view.findViewById(R.id.thingstodofirstfloat);
                viewHolder.nopray = (TextView) view.findViewById(R.id.nopray);
                viewHolder.specialdealsavalible = (LinearLayout) view.findViewById(R.id.specialdealsavalible);
                viewHolder.specialdelascountthingstodo = (TextView) view.findViewById(R.id.specialdelascountthingstodo);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();


            viewHolder.name.setText(sing_in_datas.get(position).getName());
            viewHolder.subtitle.setText(sing_in_datas.get(position).getActivity());


            if(sing_in_datas.get(position).getTours().equals("0")){

                viewHolder.specialdealsavalible.setVisibility(View.GONE);
            }
            else {

                viewHolder.specialdealsavalible.setVisibility(View.VISIBLE);
                viewHolder.specialdelascountthingstodo.setText(sing_in_datas.get(position).getTours() + "  Special Deals Available");
            }




            foodscount = sing_in_datas.get(position).getFoodc();

            if(foodscount.equals("0")){
                viewHolder.address.setText("No nearby food place");

            }
            else {
                viewHolder.address.setText(foodscount +" nearby food place");

            }



            //viewHolder.address.setText(sing_in_datas.get(position).getDescription());


















            Prayercount = sing_in_datas.get(position).getPrayerc();

            if(Prayercount.equals("0"))
            {
                viewHolder.nopray.setText("No prayer space nearby");

            }

            else {

                viewHolder.nopray.setText(Prayercount+" prayer space nearby");

            }








            AlertUtils.RatingColorGreen(getActivity(), viewHolder.item_ratingBar);
            try {
                viewHolder.item_ratingBar.setRating(Float.parseFloat(sing_in_datas.get(position).getRating()));

            } catch (NumberFormatException e) {

            }

            viewHolder.thingstodofirstfloat.setText(sing_in_datas.get(position).getRating());
            final ViewHolder vh = viewHolder;
            vh.linearlay.post(new Runnable() {
                public void run() {
                    height = vh.linearlay.getHeight();
                }
            });






          /*  if (sing_in_datas.get(position).getPhotos().size() > 0) {
                try {
                    final String s = sing_in_datas.get(position).getPhotos().get(0).getPhotourl();
                    //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                    if (height != 0) {
                        Picasso.with(getActivity()).load(s).resize(height, height)
                                .centerCrop().networkPolicy(NetworkPolicy.OFFLINE).into( viewHolder.tourimage, new Callback() {
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
                                        .into( viewHolder.tourimage, new Callback() {
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
                        Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into( viewHolder.tourimage, new Callback() {
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
                                        .into( viewHolder.tourimage, new Callback() {
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
            }*/





       /*     Glide.with(getActivity())
                    .load(sing_in_datas.get(position).getPhotos().get(0).getPhotourl())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.background_default)
                    .into(viewHolder.tourimage);*/






            if (sing_in_datas.get(position).getPhotos().size() > 0) {
                try {
                    final String s = sing_in_datas.get(position).getPhotos().get(0).getPhotourl();
                    //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                    if (height != 0) {
                        Picasso.with(getActivity()).load(s).resize(height, height)
                                .centerCrop().networkPolicy(NetworkPolicy.OFFLINE).into( viewHolder.tourimage, new Callback() {
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
                                        .into( viewHolder.tourimage, new Callback() {
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
                        Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into( viewHolder.tourimage, new Callback() {
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
                                        .into( viewHolder.tourimage, new Callback() {
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









            Animation animation = AnimationUtils.loadAnimation(getActivity(),
                    (position > lastPosition) ? R.anim.up_from_bottom
                            : R.anim.down_from_top);
            viewHolder.cd.startAnimation(animation);
            lastPosition = position;








   /*         viewHolder.rates.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    tdetailsimage = tourdetailsvalue.get(position).getImage();
                    tdetailsrate = tourdetailsvalue.get(position).getRate();
                    tdetailsreviews = tourdetailsvalue.get(position).getReviews();
                    tdescontent =  tourdetailsvalue.get(position).getContent();
                    tdeshoverview =   tourdetailsvalue.get(position).getOverviews();
                    tdlosoverview =  tourdetailsvalue.get(position).getLong_overviews();
                    tsesinquery =  tourdetailsvalue.get(position).getEnquiry();
                    Sessiondata.getInstance().setWebidchange(tourdetailsvalue.get(position).getId());
                    Sessiondata.getInstance().setWebsite(tourdetailsvalue.get(position).getWebsite());
                    Sessiondata.getInstance().setTourimage(tdetailsimage);
                    Sessiondata.getInstance().setTourcontent(tdescontent);
                    Sessiondata.getInstance().setTourcuren(tourdetailsvalue.get(position).getCurrence());
                    Sessiondata.getInstance().setTourrate(tdetailsrate);
                    Sessiondata.getInstance().setTourreview(tdetailsreviews);
                    Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                    Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                    Sessiondata.getInstance().setTourenqiry(tsesinquery);
                    Sessiondata.getInstance().setToursubid(tourdetailsvalue.get(position).getSub_id());
                    Sessiondata.getInstance().setTourid(tourdetailsvalue.get(position).getId());
                    Sessiondata.getInstance().setLocationname(tourdetailsvalue.get(position).getLocation());
                    Sessiondata.getInstance().setAddtionalcon(tourdetailsvalue.get(position).getAddi_info());
                    Sessiondata.getInstance().setCancelceon(tourdetailsvalue.get(position).getCancellation_policy());
                    Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalue.get(position).getTour_opt_info());
                    Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalue.get(position).getTour_opt_link());
                    Sessiondata.getInstance().setOve1(tourdetailsvalue.get(position).getOverview1());
                    Sessiondata.getInstance().setOver2(tourdetailsvalue.get(position).getOverview2());
                    Imageval img1 = new Imageval();
                    img1.setImage(tourdetailsvalue.get(position).getImage());
                    img1.setId("1");
                    imagedetails.add(img1);
                    Imageval img2 = new Imageval();
                    img2.setImage(tourdetailsvalue.get(position).getImagetwo());
                    img2.setId("2");
                    imagedetails.add(img2);
                    Imageval img3 = new Imageval();
                    img3.setImage(tourdetailsvalue.get(position).getImagethree());
                    img3.setId("3");
                    imagedetails.add(img3);
                    Imageval img4 = new Imageval();
                    img4.setImage(tourdetailsvalue.get(position).getImagefour());
                    img4.setId("3");
                    imagedetails.add(img4);
                    Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                    Fragment fr = new Tourlistdeatails();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);}
            });*/
            /*viewHolder.tourimage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    tdetailsimage = tourdetailsvalue.get(position).getImage();
                    tdetailsrate = tourdetailsvalue.get(position).getRate();
                    tdetailsreviews = tourdetailsvalue.get(position).getReviews();
                    tdescontent =  tourdetailsvalue.get(position).getContent();
                    tdeshoverview =   tourdetailsvalue.get(position).getOverviews();
                    tdlosoverview =  tourdetailsvalue.get(position).getLong_overviews();
                    tsesinquery =  tourdetailsvalue.get(position).getEnquiry();
                    Sessiondata.getInstance().setWebidchange(tourdetailsvalue.get(position).getId());
                    Sessiondata.getInstance().setWebsite(tourdetailsvalue.get(position).getWebsite());
                    Sessiondata.getInstance().setTourimage(tdetailsimage);
                    Sessiondata.getInstance().setTourcontent(tdescontent);
                    Sessiondata.getInstance().setTourcuren(tourdetailsvalue.get(position).getCurrence());
                    Sessiondata.getInstance().setTourrate(tdetailsrate);
                    Sessiondata.getInstance().setTourreview(tdetailsreviews);
                    Sessiondata.getInstance().setTourOverviews(tdeshoverview);
                    Sessiondata.getInstance().setTourloOverviews(tdlosoverview);
                    Sessiondata.getInstance().setTourenqiry(tsesinquery);
                    Sessiondata.getInstance().setToursubid(tourdetailsvalue.get(position).getSub_id());
                    Sessiondata.getInstance().setTourid(tourdetailsvalue.get(position).getId());
                    Sessiondata.getInstance().setLocationname(tourdetailsvalue.get(position).getLocation());
                    Sessiondata.getInstance().setAddtionalcon(tourdetailsvalue.get(position).getAddi_info());
                    Sessiondata.getInstance().setCancelceon(tourdetailsvalue.get(position).getCancellation_policy());
                    Sessiondata.getInstance().setTouroperatorlink(tourdetailsvalue.get(position).getTour_opt_info());
                    Sessiondata.getInstance().setTouroperatorlinkurl(tourdetailsvalue.get(position).getTour_opt_link());
                    Sessiondata.getInstance().setOve1(tourdetailsvalue.get(position).getOverview1());
                    Sessiondata.getInstance().setOver2(tourdetailsvalue.get(position).getOverview2());
                    Imageval img1 = new Imageval();
                    img1.setImage(tourdetailsvalue.get(position).getImage());
                    img1.setId("1");
                    imagedetails.add(img1);
                    Imageval img2 = new Imageval();
                    img2.setImage(tourdetailsvalue.get(position).getImagetwo());
                    img2.setId("2");
                    imagedetails.add(img2);
                    Imageval img3 = new Imageval();
                    img3.setImage(tourdetailsvalue.get(position).getImagethree());
                    img3.setId("3");
                    imagedetails.add(img3);
                    Imageval img4 = new Imageval();
                    img4.setImage(tourdetailsvalue.get(position).getImagefour());
                    img4.setId("3");
                    imagedetails.add(img4);
                    Sessiondata.getInstance().setImgphotosdetails(imagedetails);
                    Fragment fr = new Tourlistdeatails();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);}});*/


            return view;}}
    static class ViewHolder {
        TextView name,thingstodofirstfloat,nopray,specialdelascountthingstodo;
        SquareImageView tourimage;
        RobotoTextView subtitle,address;
        public CardView cd;
        public RatingBar item_ratingBar;
        public LinearLayout linearlay;
       LinearLayout specialdealsavalible;
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



                    Fragment fr = new New_Explore_activities_Fragment();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);


                    return true;
                }
                return false;
            }
        });

    }














    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {







        } catch (Exception we) {
            Log.d("Exe", "" + we);
        }
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








    public void showViewDetFragment(Categorylistmodel viewModel) {
        Sessiondata.getInstance().setSnocreatenewtrip(viewModel.getSno());
        Sessiondata.getInstance().setUpdateresult(0);
Sessiondata.getInstance().setFtplistval(4);


       /* Fragment fr = new NewUiView_itemInfo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);*/




        Fragment fr = new View_itemInfo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);



    }
}


