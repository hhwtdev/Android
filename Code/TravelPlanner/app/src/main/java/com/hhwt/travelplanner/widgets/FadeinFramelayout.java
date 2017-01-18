package com.hhwt.travelplanner.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.hhwt.travelplanner.R;

/**
 * Created by Mathankumar on 20/11/15.
 */
public class FadeinFramelayout extends FrameLayout
{
    Context context;
    Animation inAnimation;
    Animation outAnimation;

    public FadeinFramelayout(Context context)
    {
        super(context);
        this.context = context;
        initAnimations();

    }

    public FadeinFramelayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initAnimations();
    }

    public FadeinFramelayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
        initAnimations();
    }

    private void initAnimations()
    {
        inAnimation = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.fade_in);
        outAnimation = (Animation) AnimationUtils.loadAnimation(context, R.anim.fadeout);
    }

    public void show()
    {
        if (isVisible()) return;
        show(true);
    }

    public void show(boolean withAnimation)
    {
        if (withAnimation) this.startAnimation(inAnimation);
        this.setVisibility(View.VISIBLE);
    }

    public void hide()
    {
        if (!isVisible()) return;
        hide(true);
    }

    public void hide(boolean withAnimation)
    {
        if (withAnimation) this.startAnimation(outAnimation);
        this.setVisibility(View.GONE);
    }

    public boolean isVisible()
    {
        return (this.getVisibility() == View.VISIBLE);
    }

    public void overrideDefaultInAnimation(Animation inAnimation)
    {
        this.inAnimation = inAnimation;
    }

    public void overrideDefaultOutAnimation(Animation outAnimation)
    {
        this.outAnimation = outAnimation;
    }
}