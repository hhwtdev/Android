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
import android.widget.RelativeLayout;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.List;

public class Savetripdatelistadapter extends RecyclerView.Adapter<Savetripdatelistadapter.ViewHolder> {

    private List<String> items;
    private OnItemClickListener onItemClickListener;
    Context mContext;

    public Savetripdatelistadapter(List<String> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_savetripbydate, parent, false);
        mContext = parent.getContext();
       // Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String item = items.get(position);
        if (position < items.size() - 1) {
            holder.visiblethis.setVisibility(View.VISIBLE);
            holder.hidethis.setVisibility(View.GONE);
            final String dateis = Date_utill.Dateformatchange(item);
            String days[] = dateis.split("_");
            holder.title.setText(days[2]);
            holder.subtitle.setText(days[1]);
            holder.address.setText(days[0]);
            holder.itemView.setTag(item);
            holder.click.setTag(item);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
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
                }
            });
        } else {
            holder.visiblethis.setVisibility(View.GONE);
            holder.hidethis.setVisibility(View.VISIBLE);
            holder.itemView.setTag(item);
            holder.click.setTag(item);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    // Give some time to the ripple to finish the effect
                    if (onItemClickListener != null) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onItemClickListener.onaddclick(v, v.getTag().toString());
                            }
                        }, 200);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public RobotoTextView title, subtitle, address;
        CardView click;
        RelativeLayout hidethis, visiblethis;

        public ViewHolder(View itemView) {
            super(itemView);
            visiblethis = (RelativeLayout) itemView.findViewById(R.id.visiblethis);
            hidethis = (RelativeLayout) itemView.findViewById(R.id.hidethis);
            title = (RobotoTextView) itemView.findViewById(R.id.title);
            subtitle = (RobotoTextView) itemView.findViewById(R.id.subtitle);
            address = (RobotoTextView) itemView.findViewById(R.id.address);
            click = (CardView) itemView.findViewById(R.id.click);
        }
    }

    public interface OnItemClickListener {

        void onitemclick(View view, String viewModel);
        void onaddclick(View view, String viewModel);

    }


}
