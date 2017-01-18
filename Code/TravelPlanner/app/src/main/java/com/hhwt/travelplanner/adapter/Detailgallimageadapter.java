package com.hhwt.travelplanner.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jeyavijay on 08/09/16.
 */
public class Detailgallimageadapter extends PagerAdapter {
    Context c;
    private List<String> _imagePaths;
    private LayoutInflater inflater;
    boolean isImageFitToScreen;

    public Detailgallimageadapter(Context c, List<String> imagePaths) {
        this._imagePaths = imagePaths;
        this.c = c;
    }

    @Override
    public int getCount() {
        return this._imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imgDisplay;
        TextView pageindicatore;

        inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.galleryitem, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imageView1);
     //   pageindicatore = (TextView) viewLayout.findViewById(R.id.pagenumber1);

      /*  Picasso.with(c).load(_imagePaths.get(position)).into(imgDisplay);
        (container).addView(viewLayout);*/


        if (_imagePaths.get(position).length() > 0) {
            try {
                final String s = _imagePaths.get(position);
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                Picasso.with(c).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(imgDisplay, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(c)
                                .load(s)
                                .error(R.drawable.com_facebook_profile_picture_blank_square)
                                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                                .into(imgDisplay, new Callback() {
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
            (container).addView(viewLayout);
        }


        int pagenumberTxt=position + 1;


        try {
            if(pagenumberTxt == 1){
               // pageindicatore.setText("1 of 3");

            }
            else if(pagenumberTxt == 2){
              //  pageindicatore.setText("2 of 3");

            }else if(pagenumberTxt == 3){

              //  pageindicatore.setText("3 of 3");

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





       /* imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    imgDisplay.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    imgDisplay.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    imgDisplay.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    imgDisplay.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });*/



        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((RelativeLayout) object);

    }
}

