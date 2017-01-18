package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.adapter.UinewCategorylistadapter;
import com.hhwt.travelplanner.fragment.Tourlistdeatails;
import com.hhwt.travelplanner.fragment.View_itemInfo_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.Imageval;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
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
public class NewuiSpecialdealsFragment extends Fragment  implements
        UinewCategorylistadapter.OnItemClickListener {

    String subcatid;
    public static Activity _A;
    public String fb_id, _CityName, _CityID, _CityImage;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String MyPREFERENCES = "HHWT";
    String mess;
    ListView save;
    emphistoryAdapter emphistory;
    ArrayList<Tourdetails> tourdetailsvalue;
    ArrayList<Imageval> imagedetails;
    String tdetailsimage,tdetailsrate,tdetailsreviews,tdescontent,tdeshoverview,tdlosoverview,tsesinquery;
    private RecyclerView.LayoutManager mLayoutManager;
    public int sortitemposition = 0;
    public static int clickeventcheck = 0;
    //RecyclerView categoryitems;
    Retrofit retrofit;
    public CreatedTripModel newtrip;
    ImageView filter;
    int i;
    UinewCategorylistadapter adapter;
    public static final String EXTRATRIPINFO = "Viewinfo";
    public static List<Categorylistmodel> itemsfinal = new ArrayList<>();
    private CreatedTripModel ctm;

    @Override
    public void onFavItemClick(View view, Categorylistmodel viewModel) {
        if (clickeventcheck == 0) {
            clickeventcheck = 1;
            viewModel.setCtm(newtrip);
            showViewDetFragment(viewModel);
            //  toolbarsearch.setVisibility(View.GONE);

        }
    }


    public NewuiSpecialdealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.newspecialdealsnewfragment_one, container, false);
        _A = getActivity();


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));




        Bundle bundle = this.getArguments();
        newtrip = new CreatedTripModel();
        ctm = new CreatedTripModel();
        ctm.setCategoryID("2");
        ctm.setCategorytype("Prayers info");





        i = Sessiondata.getInstance().getExplorenewfullpage();


        if (i == 1) {

            filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
            filter.setVisibility(View.GONE);

        }

        else if (i == 2){
            filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
            filter.setVisibility(View.VISIBLE);
        }


        else if (i == 3){
            filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
            filter.setVisibility(View.GONE);
        }









        save = (ListView) v.findViewById(R.id.categoryitems);

        imagedetails = new ArrayList<Imageval>();
        tourdetailsvalue = Sessiondata.getInstance().getTourdetails();

        if(tourdetailsvalue!=null){

            emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
            save.setAdapter(emphistory);
        }
        else{
            View layouttoast = inflater.inflate(R.layout.toastcustom, (ViewGroup) v.findViewById(R.id.toastcustom));
            ((TextView) layouttoast.findViewById(R.id.texttoast)).setText("No special deals at the moment. Please check back later");

            Toast mytoast = new Toast(getActivity());
            mytoast.setView(layouttoast);
            mytoast.setDuration(Toast.LENGTH_LONG);
            mytoast.show();
        }


        save.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                Sessiondata.getInstance().setExclusions(tourdetailsvalue.get(position).getExclusion());
                Sessiondata.getInstance().setDeppoint(tourdetailsvalue.get(position).getDeparturepoint());
                Sessiondata.getInstance().setDepdate(tourdetailsvalue.get(position).getDeparturedate());
                Sessiondata.getInstance().setDeptime(tourdetailsvalue.get(position).getDeparturetime());
                Sessiondata.getInstance().setDuration(tourdetailsvalue.get(position).getDuration());
                Sessiondata.getInstance().setReturndetails(tourdetailsvalue.get(position).getReturndetails());
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
                fc.replaceFragment(fr);
            }
        });

/*
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        categoryitems = (RecyclerView) v.findViewById(R.id.categoryitems);*/

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
           /* subcatid = "2";
            _CityID = "1";
            new WebPageTask(categoryitems, subcatid, _CityID).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);*/
        } catch (Exception we) {
            Log.d("Exe", "" + we);
        }
    }

    public void onConnectSuccess() {

    }

    // called when Tapjoy connect call failed
    public void onConnectFailure() {

    }




   // UinewCategorylistadapter


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







    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<Tourdetails> sing_in_datas;
        int height = 0;

        private int lastPosition = -1;
        public emphistoryAdapter(Context con, ArrayList<Tourdetails> singindatas) {
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
                view = mLayoutInflater.inflate(R.layout.specialdealslist, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.conteny);
                viewHolder.tourimage = (ImageView) view.findViewById(R.id.main_backdrops);
                viewHolder.rate = (TextView) view.findViewById(R.id.rate);
                viewHolder.linearlay = (LinearLayout) view.findViewById(R.id.linearlay);
             //   viewHolder.bgm = (TextView) view.findViewById(R.id.bgm);
               // viewHolder.rates = (LinearLayout) view.findViewById(R.id.rates);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }





            final ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.name.setText(sing_in_datas.get(position).getContent());
           // viewHolder.bgm.setText("from " +sing_in_datas.get(position).getCurrence());
            viewHolder.rate.setText("from " +sing_in_datas.get(position).getCurrence()+ "  "+ sing_in_datas.get(position).getRate());
            Tourdetails newsItem = (Tourdetails) sing_in_datas.get(position);



/*
            Glide.with(getActivity())
                    .load(newsItem.getImage())
                    .into(viewHolder.tourimage);*/


          /*  Glide.with(getActivity())
                    .load(newsItem.getImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.background_default)
                    .into(viewHolder.tourimage);*/

            final NewuiSpecialdealsFragment.ViewHolder vh = viewHolder;
            vh.linearlay.post(new Runnable() {
                public void run() {
                    height = vh.linearlay.getHeight();
                }
            });



        /*    if (newsItem.getImage().length() > 0) {
                try {
                    final String s = newsItem.getImage();
                    Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.tourimage,new Callback() {
                        @Override
                        public void onSuccess() {
                        }
                        @Override
                        public void onError() {
                            Picasso.with(context)
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(viewHolder.tourimage, new Callback() {
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
*/







            if (newsItem.getImage().length() > 0) {
                try {
                    final String s = newsItem.getImage();
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





























/*
            viewHolder.rates.setOnClickListener(new View.OnClickListener() {
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
            viewHolder.tourimage.setOnClickListener(new View.OnClickListener() {
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
                    fc.replaceFragment(fr);}});
            return view;}}
    static class ViewHolder {
        TextView name;
        ImageView tourimage;
        TextView rate;
        public LinearLayout linearlay;
      //  TextView bgm;
        //LinearLayout rates;
    }































    /*public void Changeadapter(List<Categorylistmodel> items, int value) {
        if (value == 1) {
            mLayoutManager = new LinearLayoutManager(getActivity());
            categoryitems.setLayoutManager(mLayoutManager);
            adapter = new UinewCategorylistadapter(items);
            adapter.setOnItemClickListener(this);
            categoryitems.setAdapter(adapter);
        } else {
            mLayoutManager = new LinearLayoutManager(getActivity());
            categoryitems.setLayoutManager(mLayoutManager);
            adapter = new UinewCategorylistadapter(items);
            adapter.setOnItemClickListener(this);
            categoryitems.setAdapter(adapter);
        }
    }


    public void Categorylist(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Changeadapter(items, value);
    }

    public void Categorylistdesc(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        Collections.reverse(items);
        Changeadapter(items, value);
    }

    public void Categorylistmaxstart(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        Collections.reverse(items);
        Changeadapter(items, value);
    }

    public void Categorylistminstar(List<Categorylistmodel> items, int value) {
        Collections.sort(items, new Comparator<Categorylistmodel>() {
            public int compare(Categorylistmodel v1, Categorylistmodel v2) {
                return v1.getRating().compareTo(v2.getRating());
            }
        });
        Changeadapter(items, value);
    }


    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(_A);
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid;
        String _CityID;
        View vs;

        private WebPageTask(View vs, String s, String subcatid) {
            this.subcatid = subcatid;
            this._CityID = s;
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
            Call<CategorylistValuesResponse> call = a.CategorylistValuenewsapi(subcatid, _CityID);
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
                            if (dd.photosres.size() > 0) {
                                for (CategorylistValuesResponse.photosr s : dd.photosres) {
                                    photoitems.add(new photoss(s.id, s.photourl, s.credits));
                                }
                            }
                            if (dd.foodclassificationres.size() > 0) {
                                for (CategorylistValuesResponse.foodclassificationr s : dd.foodclassificationres) {
                                    fooditems.add(new foodclassification(s.foodclassificationvalues));
                                }
                            }
                            items.add(new Categorylistmodel(dd.sno, dd.caegoryid, dd.name, dd.description, dd.openhrs, dd.closehrs, dd.website, dd.timeremaining, dd.estimatedtime, dd.pricecurrency, dd.price, dd.country, dd.city, dd.state, dd.address, dd.postalcode, dd.map, dd.phone, dd.hhwtlink, dd.activity, dd.weight, dd.district, dd.latitude, dd.longitude, dd.weight, photoitems, fooditems, "", dd.tags));
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
                        categoryitems.setVisibility(View.VISIBLE);
                        //  toolbarsearch.setVisibility(View.VISIBLE);
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
                        //toolbarsearch.setVisibility(View.INVISIBLE);
                        categoryitems.setVisibility(View.INVISIBLE);
                    }

                 *//*   adapter = new Categorylistadapter(items);
                    // adapter.setOnItemClickListener(this);
                    categoryitems.setAdapter(adapter);*//*
                    d.dismiss();
                } else {
                    if (items.size() > 0) {
                        // toolbarsearch.setVisibility(View.VISIBLE);
                        categoryitems.setVisibility(View.VISIBLE);
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
                        // toolbarsearch.setVisibility(View.INVISIBLE);
                        categoryitems.setVisibility(View.INVISIBLE);
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
                }
            }
        }
    }*/


    public void showViewDetFragment(Categorylistmodel viewModel) {
        Sessiondata.getInstance().setSnocreatenewtrip(viewModel.getSno());
        Sessiondata.getInstance().setUpdateresult(0);
        Sessiondata.getInstance().setFtplistval(6);

        Fragment fr = new View_itemInfo_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, viewModel);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

}
