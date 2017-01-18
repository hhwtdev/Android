/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhwt.travelplanner.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RatingBar;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.Categorylist;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.ArrayList;
import java.util.List;

public class Allreviewsadapter extends RecyclerView.Adapter<Allreviewsadapter.ViewHolder> implements View.OnClickListener {

    private static Context mContext;
    private List<Categorylist> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private int lastPosition = -1;
    ProgressDialog progressDialog;

    public Allreviewsadapter(Context context, List<Categorylist> items) {

        this.mContext = context;
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_allreviews, parent, false);
        mContext = parent.getContext();
        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Categorylist item = items.get(position);
        AlertUtils.RatingColorGreen(mContext, holder.item_ratingBar);
        holder.item_ratingBar.setRating(Float.parseFloat(item.getLikes()));
        holder.item_ratingBar.setEnabled(false);
        holder.title.setText(item.getName());
        holder.item.setText(item.getPlacename());
        holder.reviews.setText(item.getReviews());
        holder.username.setText(item.getUsername());
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.cd.startAnimation(animation);
        lastPosition = position;
        holder.itemView.setTag(item);


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (Categorylist) v.getTag());
                }
            }, 200);
        }


    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public RobotoTextView title, item, reviews, username;
        public RatingBar item_ratingBar;
        public CardView cd;

        public ViewHolder(View itemView) {
            super(itemView);
            cd = (CardView) itemView.findViewById(R.id.topcard);
            title = (RobotoTextView) itemView.findViewById(R.id.title);
            item = (RobotoTextView) itemView.findViewById(R.id.item);
            username = (RobotoTextView) itemView.findViewById(R.id.username);
            reviews = (RobotoTextView) itemView.findViewById(R.id.reviews);
            item_ratingBar = (RatingBar) itemView.findViewById(R.id.item_ratingBar);
        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, Categorylist viewModel);


    }
}
