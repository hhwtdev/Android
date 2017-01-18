package com.hhwt.travelplanner.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;

import android.content.DialogInterface;
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
import android.widget.TimePicker;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.Calendar;


/**
 * Created by jeyavijay on 07/11/16.
 */
public class Openhoursedit extends Fragment implements View.OnClickListener{
    private int mYear, mMonth, mDay, mHour, mMinute;
    ImageView backclick;
    TextView apptitle;
    LinearLayout openhrscval,closinghrsval;
    RobotoTextView openhours,closinghour;

    TextView updatinghours;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.openhoursedit,container,false);
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Update Opening Hours");
        openhours = (RobotoTextView) v.findViewById(R.id.openhours);
        closinghour = (RobotoTextView) v.findViewById(R.id.closinghour);

        updatinghours = (TextView) v.findViewById(R.id.updatinghours);
        updatinghours.setOnClickListener(this);

        openhrscval = (LinearLayout) v.findViewById(R.id.openhrscval);
        closinghrsval = (LinearLayout) v.findViewById(R.id.closinghrsval);

        openhrscval.setOnClickListener(this);
        closinghrsval.setOnClickListener(this);
        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(v == backclick){

            getFragmentManager().popBackStack();
        }

else if (v == openhrscval){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            openhours.setText("     "+hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        else if (v == closinghrsval){

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            closinghour.setText("  "+hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }


        else if(v == updatinghours){






            AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
            alert.setTitle("Thanks for helping out!");
            alert.setMessage("We'll be reviewing your edits for this listing.");
            alert.setCancelMessage(null);

            alert.setButton("OKAY", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //do somthing or dismiss dialog by                dialog.dismiss();
                    getFragmentManager().popBackStack();

                }
            });

            alert.show();
        }


        }





        }


