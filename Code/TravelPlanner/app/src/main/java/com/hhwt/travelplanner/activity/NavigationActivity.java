package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.fragment.Vote_Next_Fragment;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import java.util.Hashtable;

public class NavigationActivity extends AppCompatActivity implements  FragmentChangeListener {
    private static String TAG = NavigationActivity.class.getSimpleName();

   // FragmentDrawer.FragmentDrawerListener,
    private Toolbar mToolbar;
    FrameLayout container;
    ProgressDialog progressDialog;
    int viewstatus;
    private CreatedTripModel ctm;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    View currentView;
    ImageView apptitle;
    Hashtable connectFlags;
    String MyPREFERENCES = "HHWT";

    private static final String GUIDLIDTVIEW = "http://www.hhwt.tech/hhwt_webservice/guidesfetch.php";
    private void clearBackStack() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_ids", "1");
        editor.commit();

        Sessiondata.getInstance().setToolvalnewui(0);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);





        container = (FrameLayout) findViewById(R.id.container_body);
        apptitle = (ImageView) findViewById(R.id.apptitle);
        apptitle.findViewById(R.id.apptitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackStack();
            }
        });
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        fragment = new First_Page_Fragment();
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("");
        }
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
  /*  @Override
    public void onDrawerItemSelected(View view, int position) {
    }*/

    public void onConnectSuccess() {

    }
    public void onConnectFailure() {
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new Vote_Next_Fragment();
                title = getString(R.string.title_explore);
                break;
            case 1:
                fragment = new DestinationFragment();
                title = getString(R.string.title_createtrip);
                break;
            case 2:
                fragment = new MyTrip_Fragment();
                title = getString(R.string.title_mytrip);
                break;
            case 3:
                fragment = new City_Guide_Fragment();
                title = getString(R.string.title_guides);
                break;
            default:
                break;}
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager manager = getSupportFragmentManager();
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
            if (!fragmentPopped) { //fragment not in back stack, create it.
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container_body, fragment);
                ft.addToBackStack(backStateName);
                ft.commit();
                getSupportActionBar().setTitle("");
            }
            getSupportActionBar().setTitle("");
        }
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
            getSupportActionBar().setTitle("");
        }}}