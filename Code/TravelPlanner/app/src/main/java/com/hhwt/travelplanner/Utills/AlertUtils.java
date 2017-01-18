package com.hhwt.travelplanner.Utills;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hhwt.travelplanner.R;


/**
 * Created by Mathankumar on 17/03/16.
 */
public class AlertUtils {

    public static void SnackbarerrorAlert(Context c, View view, String message) {
        //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
        //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
        Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        ViewGroup group = (ViewGroup) snack.getView();
        View v = snack.getView();
        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        group.setBackgroundColor(ContextCompat.getColor(c, R.color.cpb_red_dark));

        snack.show();
    }

    public static void SnackbarsuccessAlert(Context c, View view, String message) {
        //  Snackbar.make(v, "You already gave " + likes + " stars", Snackbar.LENGTH_SHORT).show();
        //  totalrate.setText(Html.fromHtml(totalval + "( &#128100;" + numlikes + " )"));
        Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        ViewGroup group = (ViewGroup) snack.getView();
        View v = snack.getView();
        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        group.setBackgroundColor(ContextCompat.getColor(c, R.color.cpb_green_dark));
        snack.show();
    }

    public static void RatingColorGreen(Context c,RatingBar ratingBar){
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(c, R.color.bgorangeui), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(ContextCompat.getColor(c, R.color.bgorangeui), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(ContextCompat.getColor(c, R.color.lightgray), PorterDuff.Mode.SRC_ATOP);

    }



    public static void RatingColorwhite(Context c,RatingBar ratingBar){
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(c, R.color.white), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(ContextCompat.getColor(c, R.color.white), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(ContextCompat.getColor(c, R.color.white), PorterDuff.Mode.SRC_ATOP);

    }





}
