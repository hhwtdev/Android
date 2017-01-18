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
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyTripadapter extends RecyclerView.Adapter<MyTripadapter.ViewHolder> {

    private List<CreatedTripModel> items;
    private OnItemClickListener onItemClickListener;
    Context mContext;

    public MyTripadapter(List<CreatedTripModel> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mytrip, parent, false);
        mContext = parent.getContext();
      //  Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));
        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CreatedTripModel item = items.get(position);
        final ViewHolder vh = holder;
        holder.title.setText(item.getTripname());
        String startdate= Date_utill.Dateformatchangeforall(item.getStartingdate());
        String enddate= Date_utill.Dateformatchangeforall(item.getEndingdate());
        String createdon= Date_utill.Dateformatchangeforall(item.getCreatedon());
        holder.tstartdate.setText(startdate);
        holder.tenddate.setText(enddate);
        holder.tcreatedon.setText(createdon);
        holder.tplace.setText(item.getCity());





        if (item.getImg().length() > 0) {
            try {
                final String s = item.getImg();
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                Picasso.with(mContext).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(holder.cityimg, new Callback() {
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
                                .into(vh.cityimg, new Callback() {
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





        holder.itemView.setTag(item);
        holder.click.setTag(item);
        holder.edit.setTag(item);
        holder.itemView.setTag(item);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Give some time to the ripple to finish the effect
                if (onItemClickListener != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onItemClickListener.oneditclick(v, (CreatedTripModel) v.getTag());
                        }
                    }, 200);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Give some time to the ripple to finish the effect
                if (onItemClickListener != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onItemClickListener.onviewclick(v, (CreatedTripModel) v.getTag());
                        }
                    }, 200);
                }
            }
        });









    }

    @Override
    public int getItemCount() {
        return items.size();
    }

 /*   @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onitemclick(v, v.getTag().toString());
                }
            }, 200);
        }
    }*/

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public RobotoTextView title, tstartdate, to, tenddate, tplace, tcreatedon;
        CardView click;
        ImageView edit;
        SquareImageView cityimg;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (RobotoTextView) itemView.findViewById(R.id.title);
            tstartdate = (RobotoTextView) itemView.findViewById(R.id.tstartdate);
            tenddate = (RobotoTextView) itemView.findViewById(R.id.tenddate);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            tplace = (RobotoTextView) itemView.findViewById(R.id.tplace);
            click = (CardView) itemView.findViewById(R.id.click);
            tcreatedon = (RobotoTextView) itemView.findViewById(R.id.tcreatedon);

            cityimg = (SquareImageView) itemView.findViewById(R.id.image);



        }
    }

    public interface OnItemClickListener {

        void onviewclick(View view, CreatedTripModel viewModel);

        void oneditclick(View view, CreatedTripModel viewModel);

    }


}
