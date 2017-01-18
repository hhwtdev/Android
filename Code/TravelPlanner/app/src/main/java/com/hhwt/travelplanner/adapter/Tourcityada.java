package com.hhwt.travelplanner.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.City;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
/*
import butterknife.BindView;
import butterknife.ButterKnife;*/

/**
 * Created by jeyavijay on 10/08/16.
 */
public class Tourcityada extends RecyclerView.Adapter<Tourcityada.ViewHolder> implements View.OnClickListener {


    private List<City> items;
    private OnItemClickListener onItemClickListener;
    Context mContext;

    public Tourcityada(List<City> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vote, parent, false);
        mContext = parent.getContext();
        //   Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));
        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));
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
            // number is even
            // number is odd
            holder.topcard.setBackgroundColor(ContextCompat.getColor(mContext, R.color.layoutBackground));
            holder.voteLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.layoutBackground));

        } else {
            holder.topcard.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.voteLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }


        Picasso.with(mContext).load(item.getImg()).resize(100, 100)
                .centerCrop().networkPolicy(NetworkPolicy.OFFLINE).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(mContext)
                        .load(item.getImg())
                        .resize(100, 100)
                        .centerCrop()
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








        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, City viewModel);



    }


}
