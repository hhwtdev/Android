package com.hhwt.travelplanner.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.activity.Guidlist;
import com.hhwt.travelplanner.activity.Guidview;
import com.hhwt.travelplanner.model.Categorylist;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jeyavijay on 05/12/16.
 */
public class Reviesvalue extends BaseAdapter{
    Reviesvalue.ViewHolder viewHolder;
    Context context;
    ArrayList<Categorylist> values;
LayoutInflater lyout = null;



    public Reviesvalue(FragmentActivity activity, ArrayList<Categorylist> values) {

        this.context = activity;
        this.values = values;
lyout = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return values.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return values.get(position);

    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        if(null == convertView){

            convertView = lyout.inflate(R.layout.reviewid, null);
viewHolder = new ViewHolder();
            viewHolder.profilename = (RobotoTextView) convertView.findViewById(R.id.datereviewfirstnamelist);
            viewHolder.weakago = (RobotoTextView) convertView.findViewById(R.id.datereviewfirstdays);
            viewHolder.ratingview = (RatingBar) convertView.findViewById(R.id.datereviewfirstitem_ratingBar);

            viewHolder.title = (RobotoTextView) convertView.findViewById(R.id.datereviewfirsttitle);
            viewHolder.subtitle = (RobotoTextView) convertView.findViewById(R.id.datereviewfirstsubtitle);
            viewHolder.likenumber = (RobotoTextView) convertView.findViewById(R.id.firstslikecont);
            viewHolder.profileimage = (ImageView) convertView.findViewById(R.id.datereviewfirstprofile_image);
            viewHolder.likeimage = (ImageView) convertView.findViewById(R.id.likecountrycont);

            convertView.setTag(viewHolder);

        }

        else {

            convertView = (View) convertView.getTag();
        }
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.profilename.setText(values.get(position).getUsername());


      //  viewHolder.weakago.setText(values.get(position).);

        AlertUtils.RatingColorGreen(context, viewHolder.ratingview);
        try {
            viewHolder.ratingview.setRating(Float.parseFloat(values.get(position).getRating()));
        } catch (NumberFormatException e) {

        }
        viewHolder.title.setText(values.get(position).getName());
        viewHolder.subtitle.setText(values.get(position).getLdescription());
viewHolder.likenumber.setText(values.get(position).getLikescount());









        Categorylist newsItem = (Categorylist) values.get(position);


        if (newsItem.getImage().length() > 0) {
            try {
                final String s = newsItem.getImage();
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.profileimage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(s)
                                .error(R.drawable.background_default)
                                .placeholder(R.drawable.loading)
                                .into(viewHolder.profileimage, new Callback() {
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




if(values.get(position).getUserliked().equals("0")) {

    viewHolder.likeimage.setImageResource(R.drawable.likeup);

}
        else{

}








        return convertView;
    }

    public class ViewHolder {
        RobotoTextView profilename;
        RobotoTextView weakago;
        ImageView profileimage;
        RatingBar ratingview;
        RobotoTextView title;
        RobotoTextView subtitle;
        RobotoTextView likenumber;
        ImageView likeimage;

    }
}
