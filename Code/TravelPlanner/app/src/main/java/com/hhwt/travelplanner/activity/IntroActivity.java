package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.model.introviewpager;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Mathankumar on 02/03/16.
 */
public class   IntroActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout slider;
    ArrayList<HashMap<String, Integer>> wordList = new ArrayList<HashMap<String, Integer>>();
    Activity _A;
    Hashtable connectFlags;
    RobotoTextView next;
  //  RobotoTextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_page);
        _A = IntroActivity.this;
        slider = (SliderLayout) findViewById(R.id.slider);
       // skip = (RobotoTextView) findViewById(R.id.skip);
        next = (RobotoTextView) findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);


        //Tapjoy.connect(this.getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, IntroActivity.this);
       // Tapjoy.setDebugEnabled(true);



   /*     photoitems.add(new introviewpager(2, R.drawable.two));
        photoitems.add(new introviewpager(3, R.drawable.three));
        photoitems.add(new introviewpager(4, R.drawable.four));
*/


        List<introviewpager> photoitems = new ArrayList<>();
        photoitems.add(new introviewpager(1, R.drawable.one));
        photoitems.add(new introviewpager(2, R.drawable.newtwodiscover));
        photoitems.add(new introviewpager(3, R.drawable.newexploremapview));
        photoitems.add(new introviewpager(4, R.drawable.newsaveplanning));
        photoitems.add(new introviewpager(5, R.drawable.newnotes));
        photoitems.add(new introviewpager(6, R.drawable.newbringt));
        photoitems.add(new introviewpager(7, R.drawable.newseetheworld));

        for (introviewpager s : photoitems) {
            HashMap<String, Integer> url_maps = new HashMap<String, Integer>();
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


        next.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent n = new Intent(IntroActivity.this, NavigationActivity.class);
                startActivity(n);
                */

                Intent n = new Intent(IntroActivity.this, Newnavigationbottom.class);
                startActivity(n);
                finish();
            }
        });
    }

    public void set(ArrayList<HashMap<String, Integer>> url_maps) {
        slider.removeAllSliders();
        for (int i = 0; i <= url_maps.size() - 1; i++) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map = url_maps.get(i);
            TextSliderView textSliderView = new TextSliderView(_A);
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .image(map.get("name"))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            slider.addSlider(textSliderView);
        }

    }

    // called when Tapjoy connect call succeed

    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    // called when Tapjoy connect call failed

    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }
    //session start
    @Override
    protected void onStart() {
        super.onStart();
      //  Tapjoy.onActivityStart(this);
    }

    //session end
    @Override
    protected void onStop() {
       // Tapjoy.onActivityStop(this);
        super.onStop();
    }








    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        System.out.println("Position is in on scroll :" + position);
    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("Position is page:" + position);
        if (position == 6) {
            next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
