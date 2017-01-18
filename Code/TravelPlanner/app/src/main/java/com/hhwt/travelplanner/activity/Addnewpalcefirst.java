package com.hhwt.travelplanner.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhwt.travelplanner.R;

import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.io.FileNotFoundException;
import java.util.Hashtable;

/**
 * Created by jeyavijay on 11/04/16.
 */
public class Addnewpalcefirst extends Fragment implements View.OnClickListener{
    EditText addplacename;
    RobotoTextView submitplacenext;
    String placename,descriptionadd;
    EditText adddescription;
    Hashtable connectFlags;
    private ImageView image1;
    int i;
    ImageView image2,image3,image4;
    TextView apptitle;
    LinearLayout linearvalbottom;

    ImageView backclick;

    public Addnewpalcefirst() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addaddressfirst, container, false);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        addplacename = (EditText)v.findViewById(R.id.addplawcmd);
        submitplacenext = (RobotoTextView)v.findViewById(R.id.nextfirst);
        adddescription = (EditText)v.findViewById(R.id.adddescription);
        image1 = (ImageView)v.findViewById(R.id.viewImage);
        image2 = (ImageView)v.findViewById(R.id.viewImage2);
        image3 = (ImageView)v.findViewById(R.id.viewImage3);
        image4 = (ImageView)v.findViewById(R.id.viewImage4);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Add a new place");

        submitplacenext.setOnClickListener(this);
        image1.setOnClickListener(new Button.OnClickListener() {

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                i  = 0;
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }

        });
        image2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1;
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        image3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        image4.setOnClickListener(new Button.OnClickListener() {

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                i = 3;
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        return v;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                if (i == 0) {
                    image1.setImageBitmap(bitmap);
                    Log.d("image1", "" + bitmap);
                } else if (i == 1) {
                    image2.setImageBitmap(bitmap);
                } else if (i == 2) {
                    image3.setImageBitmap(bitmap);
                } else if (i == 3) {
                    image4.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}}
    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }
    public void onStart() {
        super.onStart();
    }
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onClick(View v) {
        if(v == submitplacenext) {
            placename = addplacename.getText().toString();
            Sessiondata.getInstance().setAddname(placename);
            descriptionadd = adddescription.getText().toString();
            Sessiondata.getInstance().setAdddescription(descriptionadd);
            Fragment fr = new Addnewpalcesecond();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);}

else if (v == backclick){
            getFragmentManager().popBackStack();
        }

    }}
