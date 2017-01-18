package com.hhwt.travelplanner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhwt.travelplanner.activity.LoginFragment;
import com.hhwt.travelplanner.fragment.RegisterationFragment;

/**
 * Created by jeyavijay on 10/05/16.
 */
public class Pageradapter extends FragmentStatePagerAdapter {


    int tabCount;


    public Pageradapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }


    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                LoginFragment tab1 = new LoginFragment();
                return tab1;
            case 1:
                RegisterationFragment tab2 = new RegisterationFragment();
                return tab2;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }
}
