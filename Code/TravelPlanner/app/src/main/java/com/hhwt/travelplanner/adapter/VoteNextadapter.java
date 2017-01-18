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

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.City;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;


public class VoteNextadapter extends RecyclerView.Adapter<VoteNextadapter.ViewHolder> implements View.OnClickListener {
    private List<City> items;
    private OnItemClickListener onItemClickListener;
    Context mContext;
    private int lastPosition = -1;
    public VoteNextadapter(List<City> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vote, parent, false);
        mContext = parent.getContext();
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final City item = items.get(position);
        final ViewHolder vh = holder;
        holder.title.setText(item.getCity());
        holder.itemView.setTag(item);
        if ((position % 2) == 0) {
           holder.topcard.setBackgroundColor(ContextCompat.getColor(mContext, R.color.layoutBackground));
            holder.voteLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.layoutBackground));

        } else {
            holder.topcard.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.voteLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }
        if (item.getImg().length() > 0) {
            try {
                final String s = item.getImg();
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                Picasso.with(mContext).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(s)
                                .error(R.drawable.background_default)
                                .placeholder(R.drawable.loading)
                                .into(vh.image, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                    }
                                });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        vh.topcard.startAnimation(animation);
        lastPosition = position;
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
                    onItemClickListener.onItemClick(v, (City) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        SquareImageView image;
        LinearLayout linearlay;
        RobotoTextView title;
        CardView topcard;
        LinearLayout voteLayout;
        public ViewHolder(View v) {
            super(v);
            title = (RobotoTextView) v.findViewById(R.id.title);
            voteLayout = (LinearLayout) v.findViewById(R.id.voteLayout);
            image = (SquareImageView) v.findViewById(R.id.image);
            topcard = (CardView) v.findViewById(R.id.topcard);
        }}
    public interface OnItemClickListener {
        void onItemClick(View view, City viewModel);
    }

}
