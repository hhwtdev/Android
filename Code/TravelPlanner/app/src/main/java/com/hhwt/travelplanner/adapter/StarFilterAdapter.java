package com.hhwt.travelplanner.adapter;

/**
 * Created by Mathankumar on 23/03/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.model.Starsfilter;
import com.hhwt.travelplanner.widgets.font.MaterialDesignIconsTextView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.ArrayList;
import java.util.List;

public class StarFilterAdapter extends RecyclerView.Adapter<StarFilterAdapter.ViewHolder> implements View.OnClickListener {
    public List<Starsfilter> ItemList;
    public static List<Integer> ids = new ArrayList<>();
    private Context mContext;

    public StarFilterAdapter(Context context, List<Starsfilter> feedItemList) {
        this.ItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_typefilter, null);
        ViewHolder mh = new ViewHolder(v, true);
        v.setOnClickListener(this);
        return mh;
    }

    public void updateList(List<Starsfilter> data) {
        ItemList = data;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(final View vv) {
        ViewHolder v = (ViewHolder) vv.getTag();
        Starsfilter day = (Starsfilter) v.selecteddays.getTag();
        int position = (int) v.CheckBoxtoselect.getTag();
        if (v.CheckBoxtoselect.getText() == mContext.getString(R.string.material_icon_check_empty)) {
            v.CheckBoxtoselect
                    .setText(mContext.getString(R.string.material_icon_check_full));
            Starsfilter days = ItemList.get(position);
            days.setSelectedStarcount(true);
            if (!ids.contains(days.getID())) {
                ids.add(day.getID());

            }

        } else {
            v.CheckBoxtoselect
                    .setText(mContext.getString(R.string.material_icon_check_empty));
            Starsfilter days = ItemList.get(position);
            days.setSelectedStarcount(false);
            if (ids.contains(days.getID())) {
                int positio = ids.indexOf(days.getID());
                ids.remove(positio);
            }
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RobotoTextView selecteddays;
        public MaterialDesignIconsTextView CheckBoxtoselect;

        ViewHolder(View v, Boolean s) {
            super(v);

            selecteddays = (RobotoTextView) v.findViewById(R.id.selecteddays);
            CheckBoxtoselect = (MaterialDesignIconsTextView) v.findViewById(R.id.CheckBoxtoselect);
           /* if (s) {
            } else {
                personame.bringToFront();
            }*/

        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder v, int i) {
        Starsfilter days = ItemList.get(i);

        v.selecteddays.setText(Html.fromHtml(days.getStarcount()));
        v.selecteddays.setTag(days);
        v.CheckBoxtoselect.setTag(i);
        if (days.getSelectedStarcount()) {
            v.CheckBoxtoselect
                    .setText(mContext.getString(R.string.material_icon_check_full));
            if (!ids.contains(days.getID())) {
                ids.add(days.getID());
            }
        } else {
            v.CheckBoxtoselect
                    .setText(mContext.getString(R.string.material_icon_check_empty));
            if (ids.contains(days.getID())) {
                int position = ids.indexOf(days.getID());
                ids.remove(position);
            }
        }
        v.itemView.setTag(v);
    }

    @Override
    public int getItemCount() {
        return (null != ItemList ? ItemList.size() : 0);
    }

}
