package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.CustomApplicationClass;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.fragment.Profileview;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.SquareImageView;

/**
 * Created by jeyavijay on 19/09/16.
 */
public class Moreprofeed extends Fragment implements View.OnClickListener{
LinearLayout addnewpla,addreviewss,profileclick;
    TextView feedbackmore,reportabug;
    SquareImageView profileimage;
    TextView pronames;
    TextView apptitle;
    LinearLayout linearvalbottom;
ImageView backclick;

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.moreview, container,false);

        CustomApplicationClass.getInstance().trackScreenView("More");


      //  Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("More");


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.GONE);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.VISIBLE);


        addnewpla = (LinearLayout) v.findViewById(R.id.addnewpla);

        feedbackmore = (TextView) v.findViewById(R.id.feedbackmore);

        reportabug = (TextView) v.findViewById(R.id.reportabug);

        profileimage = (SquareImageView) v.findViewById(R.id.guimagelist);

        pronames = (TextView) v.findViewById(R.id.namelist);

        addreviewss = (LinearLayout) v.findViewById(R.id.addreviewss);
        profileclick = (LinearLayout) v.findViewById(R.id.profileclick);

        feedbackmore.setOnClickListener(this);
        addnewpla.setOnClickListener(this);
        addreviewss.setOnClickListener(this);
        reportabug.setOnClickListener(this);
        profileclick.setOnClickListener(this);





        if (null != Sessiondata.getInstance().getFbimage()) {

            profileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
            pronames.setText(Sessiondata.getInstance().getLoginname());

        } else {
            profileimage.setVisibility(View.VISIBLE);
            pronames.setText("" + Sessiondata.getInstance().getLoginname());

        }





        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        CustomApplicationClass.getInstance().trackScreenView("More");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if(v == addnewpla) {


/*

           Fragment fr = new Addnewpalcefirst();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
*/
         //   Addnewpalcefirst

            Fragment fr = new Newaddlistdetais();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);


        }

        else if(v == feedbackmore) {

            Fragment fr = new Feedbackview();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

        else if (v == reportabug){

            Fragment fr = new Bugreport();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

else if (v == addreviewss) {


           /* Fragment fr = new Review_Select_City_Fragment();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/

            Fragment fr = new Addanewreviews();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);




        }

        else if (v == profileclick) {

            Fragment fr = new Profileview();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);

        }

    }
}
