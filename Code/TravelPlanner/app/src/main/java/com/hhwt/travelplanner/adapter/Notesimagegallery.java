package com.hhwt.travelplanner.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.activity.Sessiondata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeyavijay on 08/09/16.
 */
public class Notesimagegallery extends Activity implements View.OnClickListener{
    ImageView closebu;
    String img1,img2,img3;
    List<String> urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notesfullimg);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        closebu = (ImageView) findViewById(R.id.closescreen);

        urls= new ArrayList<>();

        urls.add(""+ Sessiondata.getInstance().getNotesimgfull());
       /* urls.add("" + Sessiondata.getInstance().getNotesimgfull());
        urls.add("" + Sessiondata.getInstance().getNotesimgfull());*/

        viewPager.setAdapter(new Detailgallimageadapter(getApplicationContext(), urls));

        closebu.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == closebu){

            finish();
        }
    }
}
