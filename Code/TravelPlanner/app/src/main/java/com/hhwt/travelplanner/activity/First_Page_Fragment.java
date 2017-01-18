package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.widgets.font.MaterialDesignIconsTextView;

import java.util.Hashtable;

/**
 * Created by Mathankumar on 06/04/16.
 */
public class First_Page_Fragment extends Fragment {
    String MyPREFERENCES = "HHWT";
    Hashtable connectFlags;
    private PagerAdapter mPagerAdapter;
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */

    MaterialDesignIconsTextView activity_wizard_possition;
    public static View v;

    public First_Page_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setNavigator() {
        String navigation = "";
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            if (i == mPager.getCurrentItem()) {
                navigation += getString(R.string.material_icon_point_full)
                        + "  ";
            } else {
                navigation += getString(R.string.material_icon_point_empty)
                        + "  ";
            }
        }
        activity_wizard_possition.setText(navigation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activityfirsatpage, container, false);




       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        // Tapjoy.setDebugEnabled(true);
        initialize(v);
        // Inflate the layout for this fragment
        return v;
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
    public void onStart() {
        super.onStart();
      //  Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
       // Tapjoy.onActivityStop(getActivity());
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


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {

                case 0: // Fragment # 0 - This will show FirstFragment

                    return new HomeFIrstFragment();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new HomeSecondFragment();

                case 2: // Fragment # 0 - This will show FirstFragment different title
                    return new HomeThirdFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void initialize(View r) {
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) r.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        activity_wizard_possition = (MaterialDesignIconsTextView) r.findViewById(R.id.activity_wizard_possition);
        mPager.setCurrentItem(0);
        setNavigator();
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                setNavigator();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (v != null) {
            initialize(v);

        }

    }
}
