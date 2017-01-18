package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

/**
 * Created by jeyavijay on 22/07/16.
 */
public class Makeonenquiry extends Fragment implements View.OnClickListener{
    TextView okgotit;
    EditText adddescription;
    LinearLayout linearvalbottom;
    ImageView backclick;

    TextView apptitle;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.makeonenquirer,container,false);


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));


okgotit = (TextView) v.findViewById(R.id.okgotit);
        adddescription = (EditText) v.findViewById(R.id.adddescription);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Tours");

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);





        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


        okgotit.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == okgotit){

           Fragment fr = new Dateguest();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }

        else if (v == backclick){
            getFragmentManager().popBackStack();

        }
    }
}
