package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.fragment.Reviews_Choose_CategoryItem_list;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

/**
 * Created by govindaraj on 04/06/16.
 */

public class ReviewsCreateActivity extends AppCompatActivity implements FragmentChangeListener {
    private Toolbar mToolbar;
    public static final String EXTRATRIPINFO = "Viewinfo";
    private CreatedTripModel ctm;
    ImageView toolbarsearch, toolbaredit;
    RobotoTextView toolbarcancel,toolbarsave;

    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new Reviews_Choose_CategoryItem_list();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRATRIPINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) this;
        fc.replaceFragment(fr);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        ctm = new CreatedTripModel();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarsearch = (ImageView)findViewById(R.id.search);
        toolbaredit = (ImageView) findViewById(R.id.edit);
        toolbarcancel= (RobotoTextView) findViewById(R.id.cancel);
        toolbarsave= (RobotoTextView) findViewById(R.id.submit);
        toolbarsave.setVisibility(View.VISIBLE);
        toolbarcancel.setVisibility(View.VISIBLE);
        toolbaredit.setVisibility(View.GONE);
        toolbarsearch.setVisibility(View.GONE);
        RobotoEditText place=(RobotoEditText)findViewById(R.id.place);
        place.setInputType(InputType.TYPE_NULL);
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctm.setCategoryID("1");
                ctm.setCategorytype("Restaurant info");
                showguideFragment(ctm);
                // Sessiondata.getInstance().setMapimage(1);
            }
        });
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container_body, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
            //     getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return true;
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
