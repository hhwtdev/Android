package com.hhwt.travelplanner.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.activity.Tourdetails;
import com.hhwt.travelplanner.fragment.Tourlistdeatails;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.Imageval;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Tourlist extends Fragment implements View.OnClickListener{


    emphistoryAdapter emphistory;
    public static final String KEY_USERID = "id";
int tsucess;
ListView save;
    ArrayList<Tourdetails> photosdetails;
String tdetailsimage,tdetailsrate,tdetailsreviews,tdescontent,tdeshoverview,tdlosoverview,tsesinquery;
    ArrayList<Tourdetails> tourdetailsvalue;
    ArrayList<Imageval> imagedetails;
String tosno;
    private static final String TOURDETAILS = "http://hhwt.tech/hhwt_webservice/placecontent.php";

    TextView apptitle;
LinearLayout linearvalbottom;

    ImageView backclick;
    ProgressDialog progressDialog;
    public Tourlist() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toursortfilter, container, false);
        save = (ListView) v.findViewById(R.id.rvsave);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Tours");

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);



        imagedetails = new ArrayList<Imageval>();
        tourdetailsvalue = Sessiondata.getInstance().getTourdetails();
        emphistory = new emphistoryAdapter(getActivity(), tourdetailsvalue);
        save.setAdapter(emphistory);
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
        return v;
    }



    @Override
    public void onClick(View v) {
         if (v == backclick){
            getFragmentManager().popBackStack();

        }


    }

    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<Tourdetails> sing_in_datas;
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
                view = mLayoutInflater.inflate(R.layout.newtourlist, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.conteny);
                viewHolder.tourimage = (ImageView) view.findViewById(R.id.main_backdrops);
                viewHolder.rate = (TextView) view.findViewById(R.id.rate);
                viewHolder.bgm = (TextView) view.findViewById(R.id.bgm);
                viewHolder.rates = (LinearLayout) view.findViewById(R.id.rates);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.name.setText(sing_in_datas.get(position).getContent());
            viewHolder.bgm.setText("from "+sing_in_datas.get(position).getCurrence());
            viewHolder.rate.setText(""+sing_in_datas.get(position).getRate());
            Tourdetails newsItem = (Tourdetails) sing_in_datas.get(position);




         /*   Glide.with(getActivity())
                    .load(newsItem.getImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.background_default)
                    .into(viewHolder.tourimage);*/





            if (newsItem.getImage().length() > 0) {
                try {
                    final String s = newsItem.getImage();
                    Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.tourimage, new Callback() {
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
            });
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
        TextView bgm;
        LinearLayout rates;
    }
}
