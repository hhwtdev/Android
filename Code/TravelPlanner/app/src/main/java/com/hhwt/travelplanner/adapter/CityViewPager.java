package com.hhwt.travelplanner.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.City;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.ArrayList;
import java.util.List;

/*import butterknife.BindView;
import butterknife.ButterKnife;*/

/**
 * Created by govindaraj on 29/06/16.
 */
public class CityViewPager extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ImageView cityImage;
    RobotoTextView cityName;
    LinearLayout mytrip;
    private List<City> city = new ArrayList<City>();

    public CityViewPager(Context context, List<City> city) {
        mContext = context;
        this.city = city;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return city.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.adapter_city, container, false);
        //ButterKnife.bind(this, itemView);
        City _c = city.get(position);
        cityName.setText(_c.getCity());
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}