package com.hhwt.travelplanner.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeyavijay on 16/09/16.
 */
public class UinewCategorylistadapter extends RecyclerView.Adapter<UinewCategorylistadapter.ViewHolder> implements View.OnClickListener {

    private List<Categorylistmodel> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public static Context mContext;
    List<photoss> photoitems = new ArrayList<>();
    List<foodclassification> fooditems = new ArrayList<>();
    private int lastPosition = -1;
    int height = 0;

    public UinewCategorylistadapter(List<Categorylistmodel> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // Clean all elements of the recycler
    public void clearData() {
        items.clear();
        notifyDataSetChanged();
    }

    public void AddallData(List<Categorylistmodel> item) {
        items.addAll(item);
        notifyDataSetChanged();
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newadapter_categorylist, parent, false);
        mContext = parent.getContext();
        //  Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));

        v.setOnClickListener(this);
        return new ViewHolder(v);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        final Categorylistmodel item = items.get(position);
        //   AlertUtils.RatingColorGreen(mContext,holder.item_ratingBar);
        AlertUtils.RatingColorGreen(mContext, holder.item_ratingBar);
        try {
            holder.item_ratingBar.setRating(Float.parseFloat(item.getRating()));

        } catch (NumberFormatException e) {

        }
        holder.title.setText(item.getName());
        holder.subtitle.setText(item.getActivity());
        holder.address.setText(item.getDescription());

        final ViewHolder vh = holder;
        vh.linearlay.post(new Runnable() {
            public void run() {
                height = vh.linearlay.getHeight();
            }
        });
        if (item.photos.size() > 0) {
            try {
                final String s = item.getPhotos().get(0).getPhotourl();
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                if (height != 0) {
                    Picasso.with(mContext).load(s).resize(height, height)
                            .centerCrop().networkPolicy(NetworkPolicy.OFFLINE).into(holder.image, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(mContext)
                                    .load(s)
                                    .resize(height, height)
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
                } else {
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

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
                    onItemClickListener.onFavItemClick(v, (Categorylistmodel) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public RobotoTextView title, subtitle, address;
        public RatingBar item_ratingBar;
        public SquareImageView image;
        public CardView cd;
        public LinearLayout linearlay;

        public ViewHolder(View itemView) {
            super(itemView);
            cd = (CardView) itemView.findViewById(R.id.topcard);
            linearlay = (LinearLayout) itemView.findViewById(R.id.linearlay);
            image = (SquareImageView) itemView.findViewById(R.id.image);
            title = (RobotoTextView) itemView.findViewById(R.id.title);
            subtitle = (RobotoTextView) itemView.findViewById(R.id.subtitle);
            address = (RobotoTextView) itemView.findViewById(R.id.address);
            item_ratingBar = (RatingBar) itemView.findViewById(R.id.item_ratingBar);


        }
    }

    public interface OnItemClickListener {

        void onFavItemClick(View view, Categorylistmodel viewModel);

    }
}
