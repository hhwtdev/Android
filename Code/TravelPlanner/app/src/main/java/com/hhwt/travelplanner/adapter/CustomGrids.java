package com.hhwt.travelplanner.adapter;/*
package com.hhwt.travelplanner.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.City;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by jeyavijay on 10/09/16.
 *//*

public class CustomGrids extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
     List<City> build_cities;
    ViewHolder viewHolder;
    private OnItemClickListener onItemClickListener;
    LayoutInflater inflater;

    public CustomGrids(List<City> items) {
        this.build_cities = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public CustomGrids(Context c, ArrayList<City> build_cities) {
        mContext = c;
        this.build_cities = build_cities;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return build_cities.size();
    }

    @Override
    public Object getItem(int position) {
        return build_cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {






        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.citygirdlist, null);
            viewHolder.myImageViewText = (TextView) convertView.findViewById(R.id.myImageViewText);
            viewHolder.myImageView = (ImageView)convertView.findViewById(R.id.myImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final ViewHolder viewholder = (ViewHolder)convertView.getTag();
        viewHolder.myImageViewText.setText(build_cities.get(position).getCity());
        if(!build_cities.get(position).getImg().equalsIgnoreCase("")) {
            Picasso.with(mContext).load(build_cities.get(position).getImg()).into(viewHolder.myImageView);
        }
        return convertView;
    }
    static class ViewHolder {
        TextView myImageViewText;
        ImageView myImageView;
    }
    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (City) v.getTag());
                }
            }, 200);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, City viewModel);
    }
}
*/
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.activity.Categorylistcity;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomGrids extends BaseAdapter{
    private Context mContext;
   List<Categorylistcity> build_cities;
    ViewHolder viewHolder;
    LayoutInflater inflater;

    public CustomGrids(Context c, ArrayList<Categorylistcity> build_cities) {
        mContext = c;
        this.build_cities = build_cities;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return build_cities.size();
    }

    @Override
    public Object getItem(int position) {
        return build_cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.citygirdlist, null);
            viewHolder.myImageViewText = (TextView) convertView.findViewById(R.id.myImageViewText);
            viewHolder.myImageView = (ImageView)convertView.findViewById(R.id.myImageView);
            viewHolder.secontcountry = (TextView)convertView.findViewById(R.id.secontcountry);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final ViewHolder viewholder = (ViewHolder)convertView.getTag();
        viewHolder.myImageViewText.setText(build_cities.get(position).getCity());
        viewHolder.secontcountry.setText(build_cities.get(position).getCountry());
        if(!build_cities.get(position).getImg().equalsIgnoreCase("")) {
            Picasso.with(mContext).load(build_cities.get(position).getImg()).into(viewHolder.myImageView);
        }
        return convertView;
    }
    static class ViewHolder {
        TextView myImageViewText,secontcountry;
        ImageView myImageView;
    }
}
