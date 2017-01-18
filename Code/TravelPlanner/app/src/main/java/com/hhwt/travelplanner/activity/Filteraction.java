package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.quinny898.library.persistentsearch.SearchBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeyavijay on 15/10/16.
 */
public class Filteraction extends Fragment implements View.OnClickListener {

    ImageView filter,backclick,toolbarsearch;
    LinearLayout linearvalbottom;
    SearchBox search;
    ViewPager viewPager;
    int i;
    TabLayout tabLayout;
    TextView apptitle;
    TextView countryapptitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.filterfunction,container,false);

        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);

        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.GONE);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);

        search = (SearchBox) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);



        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Filters");





        viewPager = (ViewPager) v.findViewById(R.id.viewpagersexplore);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabsexplore);
        tabLayout.setupWithViewPager(viewPager);





        // setupTabIcons();
        setupTabIconss();

        i = Sessiondata.getInstance().getExplorenewfullpage();


        if(i == 1) {

            TabLayout.Tab tab = tabLayout.getTabAt(0);
            //tab.setText("Things To Do");

            tab.select();




            tabLayout.setTabTextColors(ContextCompat.getColorStateList(getActivity(), R.color.orangeindicatore));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.orangeindicatore));


        }
        else if (i == 2){


            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
        }
        else if (i == 3){


            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();
        }





        return v;
    }





    private void setupTabIconss() {
  /*      LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabssss, null);
        TextView tv1 = (TextView) tabOne.findViewById(R.id.tab);
        tv1.setText("Things To Do");
        ImageView img1 = (ImageView) tabOne.findViewById(R.id.img);
        img1.setImageResource(R.drawable.imageselecter);
        tabOne.setSelected(true);

        tabLayout.getTabAt(0).setCustomView(tabOne);*/

        LinearLayout tabOne2 = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabssss, null);
        TextView tv2 = (TextView) tabOne2.findViewById(R.id.tab);
        tv2.setText("Food & Drinks");
        ImageView img2 = (ImageView) tabOne2.findViewById(R.id.img);
        img2.setImageResource(R.drawable.imageselecterfoodwhite);
        tabLayout.getTabAt(0).setCustomView(tabOne2);

     /*   LinearLayout tabOne3 = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.custom_tabssss, null);
        TextView tv3 = (TextView) tabOne3.findViewById(R.id.tab);
        tv3.setText("Special Deals");
        ImageView img3 = (ImageView) tabOne3.findViewById(R.id.img);
        img3.setImageResource(R.drawable.imageselecterspecialdeals);

        tabLayout.getTabAt(2).setCustomView(tabOne3);*/


    }


    private void setupViewPager(ViewPager viewPager) {
        // ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

     //   adapter.addFrag(new FilterThingsfragment(), "Things To Do");



        adapter.addFrag(new FilterFoodfragment(), "Food & Drinks");
       // adapter.addFrag(new FilterNewuiSpecialdealsFragment(), "Special Deals");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }






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
    }
}
