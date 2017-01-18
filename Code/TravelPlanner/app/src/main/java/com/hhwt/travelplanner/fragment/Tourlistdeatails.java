package com.hhwt.travelplanner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.activity.Makeonenquiry;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.fragment.Webchange;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.Imageval;
import com.quinny898.library.persistentsearch.SearchBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jeyavijay on 21/07/16.
 */
public class Tourlistdeatails extends Fragment implements View.OnClickListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener{
    LinearLayout overview;
    RatingBar item_ratingBar;
    SliderLayout slider;
TextView conteny,reviewtext,rate,overviewmain,bgm,locationname,share,checkavailability,depaturepoint,depaturedate,depaturetime,returndetails,duration;
    ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
    TextView apptitle;
    TextView daytour,firstdes,seconddes,thirddes,cancelation,additionalinformation,thirdexclusion;
    LinearLayout rateview;
    String webchange;
String sharlink;
    ImageView backclick;
    ImageView toolbarsearch;
    LinearLayout linearvalbottom;
    SearchBox search;
    ImageView filter;
    public  Tourlistdeatails(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toudetals, container, false);
        overview = (LinearLayout) v.findViewById(R.id.overview);
        conteny = (TextView) v.findViewById(R.id.conteny);
        bgm =(TextView) v.findViewById(R.id.bgm);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Tours");


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);

        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);



        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);




        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);



        locationname = (TextView) v.findViewById(R.id.locationname);
        slider = (SliderLayout) v.findViewById(R.id.slider);
        checkavailability = (TextView) v.findViewById(R.id.checkavailability);
        share = (TextView) v.findViewById(R.id.share);
        rateview = (LinearLayout) v.findViewById(R.id.rateview);

        daytour = (TextView) v.findViewById(R.id.daytour);
        firstdes = (TextView) v.findViewById(R.id.firstdes);
        seconddes = (TextView) v.findViewById(R.id.seconddes);
        thirddes = (TextView) v.findViewById(R.id.thirddes);

        thirdexclusion = (TextView) v.findViewById(R.id.thirdexclusion);

        cancelation = (TextView) v.findViewById(R.id.cancelation);
        additionalinformation = (TextView) v.findViewById(R.id.additionalinformation);

        depaturepoint = (TextView) v.findViewById(R.id.depaturepoint);
        depaturedate = (TextView) v.findViewById(R.id.depaturedate);
        depaturetime = (TextView) v.findViewById(R.id.depaturetime);
        returndetails = (TextView) v.findViewById(R.id.returndetails);
        duration = (TextView) v.findViewById(R.id.duration);
        duration.setText(""+ Sessiondata.getInstance().getDuration());
        webchange = Sessiondata.getInstance().getWebidchange();
        if(webchange.equals("1")){

            daytour.setText("Go to website");
            checkavailability.setText("Go to website");

        }
        else {
            daytour.setText("Make an Enquiry!");
            checkavailability.setText("Make an Enquiry!");
        }

        rateview.setOnClickListener(this);
        daytour.setOnClickListener(this);
        checkavailability.setOnClickListener(this);
        share.setOnClickListener(this);
        returndetails.setText(""+Sessiondata.getInstance().getReturndetails());
        depaturepoint.setText(""+Sessiondata.getInstance().getDeppoint());
        depaturedate.setText(""+ Sessiondata.getInstance().getDepdate());
        depaturetime.setText(""+Sessiondata.getInstance().getDeptime());
        rate = (TextView) v.findViewById(R.id.rate);
        overviewmain = (TextView) v.findViewById(R.id.overviewmain);
        locationname.setText(""+Sessiondata.getInstance().getLocationname());
        bgm.setText("from "+Sessiondata.getInstance().getTourcuren());
        overviewmain.setText(""+Sessiondata.getInstance().getTourOverviews());
        rate.setText(""+Sessiondata.getInstance().getTourrate());
        conteny.setText("" + Sessiondata.getInstance().getTourcontent());
        overview.setOnClickListener(this);
        cancelation.setText("" + Sessiondata.getInstance().getCancelceon());
        additionalinformation.setText("" + Sessiondata.getInstance().getAddtionalcon());
        thirddes.setText(""+Sessiondata.getInstance().getOver2());
        thirdexclusion.setText(""+Sessiondata.getInstance().getExclusions());
        seconddes.setText(""+Sessiondata.getInstance().getOve1());
        firstdes.setText("" + Sessiondata.getInstance().getTourloOverviews());
        if (null != Sessiondata.getInstance().getImgphotosdetails() && Sessiondata.getInstance().getImgphotosdetails().size() > 0) {
            List<Imageval> Photos = Sessiondata.getInstance().getImgphotosdetails();
            for (Imageval s : Photos) {
                HashMap<String, String> url_maps = new HashMap<String, String>();
                url_maps.put("name", s.getImage());
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

        return v;
    }
    public void set(ArrayList<HashMap<String, String>> url_maps) {
        slider.removeAllSliders();
        for (int i = 0; i <= url_maps.size() - 1; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map = url_maps.get(i);
            TextSliderView textSliderView = new TextSliderView(getActivity());
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
    public void onClick(View v) {
        if(v == overview){
        }
        else if(v == daytour){
            if (webchange.equals("1")){
                Fragment fr = new Webchange();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
            else {
                Fragment fr = new Makeonenquiry();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
        }

        else if (v == checkavailability){
            if (webchange.equals("1")){
                Fragment fr = new Webchange();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
            else {
                Fragment fr = new Makeonenquiry();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
        }


        else if (v == rateview){
            if (webchange.equals("1")){
                Fragment fr = new Webchange();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
            else {
                Fragment fr = new Makeonenquiry();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
        }

        else if (v == share) {
            sharlink = Sessiondata.getInstance().getTourcontent()+"\n"+Sessiondata.getInstance().getTouroperatorlinkurl()+"\n"+Sessiondata.getInstance().getTouroperatorlink();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, sharlink);
            startActivity(Intent.createChooser(sharingIntent,"Share using"));
        }

      else if (v == backclick){
            getFragmentManager().popBackStack();

        }


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

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
}


