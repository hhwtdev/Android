package com.hhwt.travelplanner.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hhwt.travelplanner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathankumar on 26/11/15.
 */

public class Spinneradapter extends BaseAdapter {
    private List<String> mItems = new ArrayList<>();
    private List<String> mCode = new ArrayList<>();
    Activity a;

    public void clear() {
        mItems.clear();
        mCode.clear();
    }


    public void addItem(String yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<String> yourObjectList, List<String> yourCodeList, Activity _a) {
        mItems.addAll(yourObjectList);
        mCode.addAll(yourCodeList);
        a = _a;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = a.getLayoutInflater().inflate(R.layout.spinneritems, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        String s = getCode(position);
       /* if (textView.getText().toString().equalsIgnoreCase("Singapore")) {

            try {
                MainActivity.countrycode.setText("+65");
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (textView.getText().toString().equalsIgnoreCase("Malaysia")) {
            try {
                MainActivity.countrycode.setText("+60");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (textView.getText().toString().equalsIgnoreCase("Indonesia")) {

            try {
                MainActivity.countrycode.setText("+62");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = a.getLayoutInflater().inflate(R.layout.
                    spinnernotdropdpwn, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        String s = getCode(position);
    /*    if (textView.getText().toString().equalsIgnoreCase("Singapore")) {

            try {
                MainActivity.countrycode.setText("+65");
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (textView.getText().toString().equalsIgnoreCase("Malaysia")) {
            try {
                MainActivity.countrycode.setText("+60");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (textView.getText().toString().equalsIgnoreCase("Indonesia")) {

            try {
                MainActivity.countrycode.setText("+62");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        return view;
    }

    public String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : "";
    }

    public String getCode(int position) {
        return position >= 0 && position < mCode.size() ? mCode.get(position) : "";
    }
}