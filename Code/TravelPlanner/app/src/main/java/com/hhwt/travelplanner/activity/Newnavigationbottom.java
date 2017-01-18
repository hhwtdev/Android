package com.hhwt.travelplanner.activity;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.fragment.Touractivity;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.BottomBarFragment;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.roughike.bottombar.OnMenuTabSelectedListener;
import com.roughike.bottombar.OnTabSelectedListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import io.branch.referral.Branch;
import io.branch.referral.util.BranchEvent;

/**
 * Created by jeyavijay on 09/09/16.
 */
public class Newnavigationbottom extends AppCompatActivity implements FragmentChangeListener,View.OnClickListener{
    private TabLayout mTabLayout;
LinearLayout explorevi,guidvi,tripvi,profilevi,morevi;
    private int[] mTabsIcons = {
            R.drawable.com_facebook_button_icon,
            R.drawable.search_48,
            R.drawable.share};
    String pname, purl, pfirsrname, plastname;
    private Toolbar mToolbar;
    FrameLayout container;
    ProgressDialog progressDialog;

ImageView backclick;

    int viewstatus;
    private CreatedTripModel ctm;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    View currentView;
    TextView apptitle;
    Hashtable connectFlags;
    ImageView filter;
    TextView countryapptitle;

    ImageView staricon,tripicon,touricon,exploreicon,moreicon;
    TextView startext,triptext,tourtext,exploretext,moretext;
    String MyPREFERENCES = "HHWT";
LinearLayout btmhide;
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
        setContentView(R.layout.navibottom);


        Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(Newnavigationbottom.this));
        btmhide = (LinearLayout) findViewById(R.id.btmhide);

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_ids", "1");
        editor.commit();
        Sessiondata.getInstance().setToolvalnewui(1);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        countryapptitle = (TextView) findViewById(R.id.countryapptitle);

        countryapptitle.setVisibility(View.GONE);
        backclick = (ImageView) findViewById(R.id.backar);
        backclick.setVisibility(View.GONE);

        filter = (ImageView) findViewById(R.id.filter);
        filter.setVisibility(View.GONE);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);




        staricon = (ImageView) findViewById(R.id.icon2);
        startext =(TextView) findViewById(R.id.title2);
        tripicon = (ImageView) findViewById(R.id.icon3);
        triptext = (TextView) findViewById(R.id.title3);

        touricon = (ImageView) findViewById(R.id.icon4);
        tourtext = (TextView) findViewById(R.id.title4);
        exploreicon = (ImageView) findViewById(R.id.icon);
        exploretext = (TextView) findViewById(R.id.title);
        moreicon = (ImageView) findViewById(R.id.icon5);

        moretext = (TextView) findViewById(R.id.title5);


        container = (FrameLayout) findViewById(R.id.container_body);
        explorevi = (LinearLayout) findViewById(R.id.explorevi);
        guidvi = (LinearLayout) findViewById(R.id.guidvi);
        tripvi = (LinearLayout) findViewById(R.id.tripvi);
        profilevi = (LinearLayout) findViewById(R.id.profilevi);

        morevi = (LinearLayout) findViewById(R.id.morevi);



        explorevi.setOnClickListener(this);
        guidvi.setOnClickListener(this);
        tripvi.setOnClickListener(this);
        profilevi.setOnClickListener(this);
        morevi.setOnClickListener(this);


        apptitle = (TextView) findViewById(R.id.apptitle);
        apptitle.findViewById(R.id.apptitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackStack();
            }
        });
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        fragment = new Gridcityes();
        if (savedInstanceState == null) {

            exploreicon.setImageResource(R.drawable.explorenew);
            exploretext.setTextColor(Color.parseColor("#00cccb"));

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("");
        }


    }













       /* // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }

        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public final int PAGE_COUNT = 3;

        private final String[] mTabsTitle = {"Explore", "Guides", "Profile"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public View getTabView(int position) {
            // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
            View view = LayoutInflater.from(Newnavigationbottom.this).inflate(R.layout.custom_tab, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(mTabsTitle[position]);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageResource(mTabsIcons[position]);
            return view;
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return Vote_Next_Fragment.newInstance(1);

                case 1:
                    return City_Guide_Fragment.newInstance(2);
                case 2:
                    return Vote_Next_Fragment.newInstance(3);

            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsTitle[position];
        }
    }*/


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
        }}

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if (v == explorevi) {
            staricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            startext.setTextColor(Color.parseColor("#7f7f7f"));


            tripicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            triptext.setTextColor(Color.parseColor("#7f7f7f"));

            touricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            tourtext.setTextColor(Color.parseColor("#7f7f7f"));

            //   exploreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.neuiblueclor));

            exploreicon.setImageResource(R.drawable.explorenew);
            exploretext.setTextColor(Color.parseColor("#00cccb"));

            moreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            moretext.setTextColor(Color.parseColor("#7f7f7f"));


            Fragment fr = new Gridcityes();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fr);
            fragmentTransaction.commit();
            /*FragmentChangeListener fc = (FragmentChangeListener) getApplicationContext();

            fc.replaceFragment(fr);*/


        } else if (v == guidvi) {


            staricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.neuiblueclor));
            startext.setTextColor(Color.parseColor("#00cccb"));
            tripicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            triptext.setTextColor(Color.parseColor("#7f7f7f"));

            touricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            tourtext.setTextColor(Color.parseColor("#7f7f7f"));

            //   exploreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));

            exploreicon.setImageResource(R.drawable.explore);
            exploretext.setTextColor(Color.parseColor("#7f7f7f"));

            moreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            moretext.setTextColor(Color.parseColor("#7f7f7f"));

            Fragment fr = new City_Guide_Fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fr);
            fragmentTransaction.commit();
        } else if (v == tripvi) {

            staricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            startext.setTextColor(Color.parseColor("#7f7f7f"));
            tripicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.neuiblueclor));
            triptext.setTextColor(Color.parseColor("#00cccb"));

            touricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            tourtext.setTextColor(Color.parseColor("#7f7f7f"));

            //exploreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));

            exploreicon.setImageResource(R.drawable.explore);

            exploretext.setTextColor(Color.parseColor("#7f7f7f"));

            moreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            moretext.setTextColor(Color.parseColor("#7f7f7f"));

            Fragment fr = new MyTrip_Fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fr);
            fragmentTransaction.commit();
        } else if (v == profilevi) {

            staricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            startext.setTextColor(Color.parseColor("#7f7f7f"));
            tripicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            triptext.setTextColor(Color.parseColor("#7f7f7f"));

            touricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.neuiblueclor));
            tourtext.setTextColor(Color.parseColor("#00cccb"));

            // exploreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            exploreicon.setImageResource(R.drawable.explore);
            exploretext.setTextColor(Color.parseColor("#7f7f7f"));

            moreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            moretext.setTextColor(Color.parseColor("#7f7f7f"));


            Fragment fr = new Touractivity();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fr);
            fragmentTransaction.commit();
        } else if (v == morevi) {


            moreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.neuiblueclor));
            moretext.setTextColor(Color.parseColor("#00cccb"));

            staricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            startext.setTextColor(Color.parseColor("#7f7f7f"));

            tripicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            triptext.setTextColor(Color.parseColor("#7f7f7f"));

            touricon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));
            tourtext.setTextColor(Color.parseColor("#7f7f7f"));

            //exploreicon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.newuigray));

            exploreicon.setImageResource(R.drawable.explore);
            exploretext.setTextColor(Color.parseColor("#7f7f7f"));



            //----------------------------------------------------------------------------------------------

                    SharedPreferences preferences = Newnavigationbottom.this.getSharedPreferences("temp", Newnavigationbottom.this.MODE_PRIVATE);
                    String name = preferences.getString("name", "");
                    Log.d("facebookprofile id", "" + name);

                    String CurrentString = name;
                    String[] separated = CurrentString.split("_");
                    pname = separated[0];

                    Log.d("pname", "" + pname);
                    purl = separated[1];

                    Log.d("purl", "" + purl);

              /*  pfirsrname = separated[2];
                plastname =   separated[3];
*/
                    SharedPreferences preferencprofileway = Newnavigationbottom.this.getSharedPreferences("newfb", Newnavigationbottom.this.MODE_PRIVATE);
                    String proviewpic = preferencprofileway.getString("facebookway", null);
                    Log.d("profilename", "" + proviewpic);


                    if (proviewpic.equals("2")) {


//facebookname
                        SharedPreferences preferencessname = Newnavigationbottom.this.getSharedPreferences("newprofilename", Newnavigationbottom.this.MODE_PRIVATE);
                        String fprofilename = preferencessname.getString("facebookname", null);
                        Log.d("profilename", "" + fprofilename);


                        Sessiondata.getInstance().setLoginname(fprofilename);


//facebookpicture
                        SharedPreferences preferencesspic = Newnavigationbottom.this.getSharedPreferences("newprofilepicture", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilepicture = preferencesspic.getString("facebookname", null);
                        Log.d("profilename", "" + profilepicture);


//facebook  fbid

                        SharedPreferences preferencessfbid = Newnavigationbottom.this.getSharedPreferences("newprofilefbid", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilefbid = preferencessfbid.getString("facebookid", null);
                        Log.d("profilename", "" + profilefbid);

                        Sessiondata.getInstance().setLoginfbid(profilefbid);

//facebookdob
                        SharedPreferences preferencessdob = Newnavigationbottom.this.getSharedPreferences("newprofiledob", Newnavigationbottom.this.MODE_PRIVATE);
                        String profiledob = preferencessdob.getString("facebookdob", null);
                        Log.d("profiledob", "" + profiledob);

                        Sessiondata.getInstance().setLogindob(profiledob);
                        //facebookemail
                        SharedPreferences preferencessemail = Newnavigationbottom.this.getSharedPreferences("newprofileemail", Newnavigationbottom.this.MODE_PRIVATE);
                        String profileemail = preferencessemail.getString("facebookemail", null);
                        Log.d("profileemail", "" + profileemail);
//facebookphonenumber

                        SharedPreferences preferenphonenumber = Newnavigationbottom.this.getSharedPreferences("newprofilephomenumber", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilephonenumber = preferenphonenumber.getString("facebookphonenumberr", null);
                        Log.d("profilephonenumber", "" + profilephonenumber);
                        Sessiondata.getInstance().setLoginphonenumber(profilephonenumber);

                        //facebookcountry
                        SharedPreferences preferencesscountry = Newnavigationbottom.this.getSharedPreferences("newprofilecountry", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilenamecountry = preferencesscountry.getString("facebookcountry", null);
                        Log.d("facebookcountry", "" + profilenamecountry);

                        Sessiondata.getInstance().setLogincountry(profilenamecountry);
                        Sessiondata.getInstance().setLoginemail("");
                        Sessiondata.getInstance().setLoginpassword("");
                        String URL = profilepicture;
                        GetXMLTask task = new GetXMLTask();
                        // Execute the task
                        task.execute(new String[]{URL});


                    } else if (proviewpic.equals("3")) {
                        Sessiondata.getInstance().setFbimage(null);

                        //  Sessiondata.getInstance().setFbprofilename(pname);
                        SharedPreferences preferencess = Newnavigationbottom.this.getSharedPreferences("tempsssssss", Newnavigationbottom.this.MODE_PRIVATE);
                        plastname = preferencess.getString("namesssssemail", null);
                        pfirsrname = pname;

                        Sessiondata.getInstance().setFirstname(pfirsrname);
                        Sessiondata.getInstance().setLastname(plastname);

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//Registername
                        SharedPreferences preferencessname = Newnavigationbottom.this.getSharedPreferences("newprofilename", Newnavigationbottom.this.MODE_PRIVATE);
                        String fprofilename = preferencessname.getString("registername", null);
                        Log.d("profilename", "" + fprofilename);


                        Sessiondata.getInstance().setLoginname(fprofilename);

//Registeremail

                        SharedPreferences preferencessemail = Newnavigationbottom.this.getSharedPreferences("newprofileemail", Newnavigationbottom.this.MODE_PRIVATE);
                        String profileemail = preferencessemail.getString("registeremail", null);
                        Log.d("profileemail", "" + profileemail);

                        Sessiondata.getInstance().setLoginemail(profileemail);

                        Sessiondata.getInstance().setLoginfbid(profileemail);


//Registerpassword

                        SharedPreferences preferencesspassword = Newnavigationbottom.this.getSharedPreferences("newprofilefpassword", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilepassword = preferencesspassword.getString("registerpassword", null);
                        Log.d("profilepassword", "" + profilepassword);


                        Sessiondata.getInstance().setLoginpassword(profilepassword);


///Registerdob

                        SharedPreferences preferencessdob = Newnavigationbottom.this.getSharedPreferences("newprofilefdob", Newnavigationbottom.this.MODE_PRIVATE);
                        String profiledob = preferencessdob.getString("registerdob", null);
                        Log.d("profiledob", "" + profiledob);

                        Sessiondata.getInstance().setLogindob(profiledob);

//Registerphonenumber


                        SharedPreferences preferencessphonenumber = Newnavigationbottom.this.getSharedPreferences("newprofilefphonenumber", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilephone = preferencessphonenumber.getString("registerphonenumber", null);
                        Log.d("profilephone", "" + profilephone);


                        Sessiondata.getInstance().setLoginphonenumber(profilephone);
//Registercountry

                        SharedPreferences preferencesscountry = Newnavigationbottom.this.getSharedPreferences("newprofilefcountry", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilecountry = preferencesscountry.getString("registercountry", null);
                        Log.d("profilecountry", "" + profilecountry);

                        Sessiondata.getInstance().setLogincountry(profilecountry);


                        Fragment fr = new Moreprofeed();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body,fr);
                        fragmentTransaction.commit();
                    } else {
                        Sessiondata.getInstance().setFbimage(null);
                        //  Sessiondata.getInstance().setFbprofilename(pname);
                        SharedPreferences preferencess = Newnavigationbottom.this.getSharedPreferences("tempsssssss", Newnavigationbottom.this.MODE_PRIVATE);
                        plastname = preferencess.getString("namesssssemail", null);
                        pfirsrname = pname;

                        Sessiondata.getInstance().setFirstname(pfirsrname);
                        Sessiondata.getInstance().setLastname(plastname);

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//Registername
                        SharedPreferences preferencessname = Newnavigationbottom.this.getSharedPreferences("newprofilename", Newnavigationbottom.this.MODE_PRIVATE);
                        String fprofilename = preferencessname.getString("registername", null);
                        Log.d("profilename", "" + fprofilename);


                        Sessiondata.getInstance().setLoginname(fprofilename);

//Registeremail

                        SharedPreferences preferencessemail = Newnavigationbottom.this.getSharedPreferences("newprofileemail", Newnavigationbottom.this.MODE_PRIVATE);
                        String profileemail = preferencessemail.getString("registeremail", null);
                        Log.d("profileemail", "" + profileemail);

                        Sessiondata.getInstance().setLoginemail(profileemail);

                        Sessiondata.getInstance().setLoginfbid(profileemail);


//Registerpassword

                        SharedPreferences preferencesspassword = Newnavigationbottom.this.getSharedPreferences("newprofilefpassword", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilepassword = preferencesspassword.getString("registerpassword", null);
                        Log.d("profilepassword", "" + profilepassword);


                        Sessiondata.getInstance().setLoginpassword(profilepassword);


///Registerdob

                        SharedPreferences preferencessdob = Newnavigationbottom.this.getSharedPreferences("newprofilefdob", Newnavigationbottom.this.MODE_PRIVATE);
                        String profiledob = preferencessdob.getString("registerdob", null);
                        Log.d("profiledob", "" + profiledob);

                        Sessiondata.getInstance().setLogindob(profiledob);

//Registerphonenumber


                        SharedPreferences preferencessphonenumber = Newnavigationbottom.this.getSharedPreferences("newprofilefphonenumber", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilephone = preferencessphonenumber.getString("registerphonenumber", null);
                        Log.d("profilephone", "" + profilephone);


                        Sessiondata.getInstance().setLoginphonenumber(profilephone);
//Registercountry

                        SharedPreferences preferencesscountry = Newnavigationbottom.this.getSharedPreferences("newprofilefcountry", Newnavigationbottom.this.MODE_PRIVATE);
                        String profilecountry = preferencesscountry.getString("registercountry", null);
                        Log.d("profilecountry", "" + profilecountry);

                        Sessiondata.getInstance().setLogincountry(profilecountry);


                        Fragment fr = new Moreprofeed();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body,fr);
                        fragmentTransaction.commit();
                    }


                }







            }














    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {


        private ProgressDialog pdia;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(Newnavigationbottom.this);
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
            Sessiondata.getInstance().setFbprofilename(pname);
           /* Sessiondata.getInstance().setFirstname(pfirsrname);
            Sessiondata.getInstance().setLastname(plastname);*/

            Fragment fr = new Moreprofeed();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body,fr);
            fragmentTransaction.commit();

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



}



