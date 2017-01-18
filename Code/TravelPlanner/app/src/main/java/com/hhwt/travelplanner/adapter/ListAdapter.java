package com.hhwt.travelplanner.adapter;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.model.Categorylistneibourhood;

/**
 * Created by jeyavijay on 21/12/16.
 */

public class ListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Categorylistneibourhood> objects;

    public ListAdapter(Context context, ArrayList<Categorylistneibourhood> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.neigo, parent, false);
        }

        Categorylistneibourhood p = getProduct(position);

        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.getDistrict());

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.box);
        return view;
    }

    Categorylistneibourhood getProduct(int position) {
        return ((Categorylistneibourhood) getItem(position));
    }

    public ArrayList<Categorylistneibourhood> getBox() {
        ArrayList<Categorylistneibourhood> box = new ArrayList<Categorylistneibourhood>();
        for (Categorylistneibourhood p : objects) {
            if (p.box)
                box.add(p);
        }
        return box;
    }

    OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };
}
