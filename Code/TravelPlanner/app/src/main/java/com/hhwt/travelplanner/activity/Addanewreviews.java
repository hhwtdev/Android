package com.hhwt.travelplanner.activity;

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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.CategorylistValuesResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.model.tourdetails;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 11/11/16.
 */
public class Addanewreviews extends Fragment implements View.OnClickListener {
    Reviewsearch emphistory;
    Retrofit retrofit;
    ListView mListView;
    // MySimpleSearchAdapter mAdapter;
    ImageView btnSearch, btnLeft;
    EditText mtxt;
String voiceimage;
    RobotoTextView notfindplace;
    LinearLayout  statyicfindplace;

    LinearLayout linearvalbottom;
    TextView apptitle;
    ImageView backclick;
ImageView mike;
    String mess;

    String spnId;
    LinearLayout addnewplace;

    TextView reiewsaddtext;

    List<Categorylistmodel> emphistorydetails;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.addanewreviewstart, container, false);
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));



        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Add a Review");
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();








addnewplace = (LinearLayout) v.findViewById(R.id.reviewadd);


        addnewplace.setOnClickListener(this);



        reiewsaddtext = (TextView) v.findViewById(R.id.reiewsadd);




        statyicfindplace = (LinearLayout) v.findViewById(R.id.findplace);
        notfindplace = (RobotoTextView) v.findViewById(R.id.notfindplace);


//        statyicfindplace.setOnClickListener(this);



        mListView = (ListView) v.findViewById(R.id.mListView);
         // mAdapter = new MySimpleSearchAdapter(this);



        btnSearch = (ImageView) v.findViewById(R.id.close);
        btnLeft = (ImageView) v.findViewById(R.id.btnLeft);
        mike = (ImageView) v.findViewById(R.id.btnSearch);



        mtxt = (EditText) v.findViewById(R.id.edSearch);


        voiceimage = mtxt.getText().toString();

if(voiceimage.equals("")){
    mike.setVisibility(View.VISIBLE);
    btnSearch.setVisibility(View.GONE);
    notfindplace.setVisibility(View.VISIBLE);
  //  statyicfindplace.setVisibility(View.GONE);
    mListView.setVisibility(View.GONE);
}
        else{
    mike.setVisibility(View.GONE);
    btnSearch.setVisibility(View.VISIBLE);
    notfindplace.setVisibility(View.GONE);
    //statyicfindplace.setVisibility(View.VISIBLE);
    mListView.setVisibility(View.VISIBLE);
}



        mtxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (0 != mtxt.getText().length()) {
                    mike.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                    notfindplace.setVisibility(View.GONE);
                   // statyicfindplace.setVisibility(View.VISIBLE);
                    mListView.setVisibility(View.VISIBLE);
                     spnId = mtxt.getText().toString();
                    reiewsaddtext.setText("Add   "+"''"+spnId+"''");

                    new Addanewreviews.WebPageTask(mListView,spnId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                  //  mListView.setOnItemClickListener(this);


                    //setSearchResult(spnId);
                } else {
                    //  setData();
                    mike.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                    notfindplace.setVisibility(View.VISIBLE);
                    //statyicfindplace.setVisibility(View.GONE);
                    mListView.setVisibility(View.GONE);
                    reiewsaddtext.setText("Add a new place");

                }
            }
        });
        btnLeft.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        //setData();








        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


              Sessiondata.getInstance().setNewreviewsname(Sessiondata.getInstance().getReviewsearchresult().get(position).getName());
            Sessiondata.getInstance().setReviewdataelementsno(Sessiondata.getInstance().getReviewsearchresult().get(position).getSno());

reviewsplit();




            }
        });



        return v;
    }

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



            String URL = profilepicture;
            Addanewreviews.GetXMLTask task = new Addanewreviews.GetXMLTask();
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




            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mtxt.getWindowToken(), 0);

            Fragment fc = new Addnewclickdetails();
            FragmentChangeListener fr = (FragmentChangeListener) getActivity();
            fr.replaceFragment(fc);

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


            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mtxt.getWindowToken(), 0);


            Fragment fc = new Addnewclickdetails();
            FragmentChangeListener fr = (FragmentChangeListener) getActivity();
            fr.replaceFragment(fc);


        }









    }



    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {


        private ProgressDialog pdia;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(getActivity());
            pdia.setMessage("Loading...");
            pdia.show();
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
            pdia.dismiss();

            Log.d("Arjunimage", "" + result);
            Sessiondata.getInstance().setFbimage(result);
            //Sessiondata.getInstance().setFbprofilename(pname);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mtxt.getWindowToken(), 0);

            Fragment fc = new Addnewclickdetails();
            FragmentChangeListener fr = (FragmentChangeListener) getActivity();
            fr.replaceFragment(fc);

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





    public void setData() {
        /*mAllData = new ArrayList<String>();
        mAdapter = new MySimpleSearchAdapter(this);
        for (int i = 0; i < str.length; i++) {
            mAdapter.addItem(str[i]);
            mAllData.add(str[i]);
        }
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(mAdapter);*/
    }






    public void setSearchResult(String str) {
       /* mAdapter = new MySimpleSearchAdapter(this);
        for (String temp : mAllData) {
            if (temp.toLowerCase().contains(str.toLowerCase())) {
                mAdapter.addItem(temp);
            }
        }
        mListView.setAdapter(mAdapter);*/
    }

   /* @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
*/










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

        else if (v == btnSearch){
            mtxt.setText("");
            //setData();
        }

        else if (v == btnLeft){

        }

        else if(v == addnewplace)
        {

            Fragment fr = new Newaddlistdetais();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }



    }












    private class WebPageTask extends AsyncTask<Void, CategorylistValuesResponse, CategorylistValuesResponse> {
        ProgressDialog d = new ProgressDialog(getActivity());
        List<Categorylistmodel> items = new ArrayList<>();
        String subcatid;
        View vs;
        String district;
        private WebPageTask(View vs, String subcatid) {
            this.subcatid = subcatid;
            this.vs = vs;
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
            Call<CategorylistValuesResponse> call = a.reviewsearch(subcatid);
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

                        Sessiondata.getInstance().setReviewsearchresult(items);





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
                        //adapter;

                        emphistorydetails = Sessiondata.getInstance().getReviewsearchresult();
                        emphistory = new Reviewsearch(getActivity(), emphistorydetails);
                        mListView.setAdapter(emphistory);

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
                    snack.show();

                    mListView.setVisibility(View.GONE);

                }}}}











    private class Reviewsearch extends BaseAdapter {
       ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        List<Categorylistmodel> sing_in_datas;
        private int lastPosition = -1;

        public Reviewsearch(Context con, List<Categorylistmodel> singindatas) {
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
                view = mLayoutInflater.inflate(R.layout.reviewsearch, null);
                viewHolder = new ViewHolder();


                viewHolder.name = (RobotoTextView) view.findViewById(R.id.first);

                viewHolder.imagelist = (ImageView) view.findViewById(R.id.guimage);
viewHolder.subname = (RobotoTextView) view.findViewById(R.id.second);





                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();


            viewHolder.name.setText(sing_in_datas.get(position).getName());

            viewHolder.subname.setText(sing_in_datas.get(position).getActivity());

            Categorylistmodel newsItem = (Categorylistmodel) sing_in_datas.get(position);


            if (newsItem.getPhotos().size() > 0) {
                try {
                    final String s = newsItem.getPhotos().get(0).getPhotourl();
                    //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                    Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.imagelist, new Callback() {
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
                                    .into(viewHolder.imagelist, new Callback() {
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


            Animation animation = AnimationUtils.loadAnimation(context,
                    (position > lastPosition) ? R.anim.up_from_bottom
                            : R.anim.down_from_top);

            lastPosition = position;
            return view;
        }
    }

    static class ViewHolder {
        RobotoTextView name;
        ImageView imagelist;

        RobotoTextView subname;
    }



}
