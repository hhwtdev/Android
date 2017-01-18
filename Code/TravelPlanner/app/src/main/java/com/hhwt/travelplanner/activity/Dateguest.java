package com.hhwt.travelplanner.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.fragment.Specialrequest;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by jeyavijay on 23/07/16.
 */
public class Dateguest extends Fragment implements View.OnClickListener{
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog toDatePickerDialog;
    TextView txtCount3;
    TextView txtCount;
     TextView txtCount2;
    EditText country;
    int viewstatus;
    String message;
    String ids,snoids;
    Button buttonplus1;
    EditText phno;
    String datestring,countryname;
    String adultcount,phonenoo;
    String childcount;
    String infantcount;
    String emailvalue;
    Button buttonplus3;
    TextView next;
    EditText emailhere;
    Button buttonminus3;
    int count1=0;
    ProgressDialog progressDialog;
    TextView tripdate;
    Button buttonplus2;
    Button buttonminus2;

    TextView apptitle;
    int count2=0;
int tsucess;
    Button buttonminus;
    int count3=0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]+";
ImageView backclick;


    LinearLayout linearvalbottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dateguest,container,false);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Tours");


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);




        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        tripdate = (TextView) v.findViewById(R.id.datepickview);
        txtCount =(TextView) v.findViewById(R.id.countfirst);
         buttonplus1= (Button) v.findViewById(R.id.pluse1);
         buttonminus= (Button) v.findViewById(R.id.minus1);
          txtCount2 =(TextView) v.findViewById(R.id.countsecond);
         buttonplus2= (Button) v.findViewById(R.id.pluse2);
         buttonminus2= (Button) v.findViewById(R.id.minus2);
        next = (TextView) v.findViewById(R.id.next);
        phno = (EditText) v.findViewById(R.id.phno);
        emailhere = (EditText) v.findViewById(R.id.emailhere);
        country = (EditText) v.findViewById(R.id.country);
       txtCount3 =(TextView) v.findViewById(R.id.countthird);
         buttonplus3= (Button) v.findViewById(R.id.pluse3);
         buttonminus3= (Button) v.findViewById(R.id.minus3);
        buttonplus3.setOnClickListener(this);
        buttonminus3.setOnClickListener(this);
        buttonplus2.setOnClickListener(this);
        buttonminus2.setOnClickListener(this);
        buttonplus1.setOnClickListener(this);
        buttonminus.setOnClickListener(this);
        tripdate.setInputType(InputType.TYPE_NULL);
        setDateTimeField();
        next.setOnClickListener(this);
        return v;
    }
    private void setDateTimeField() {
        tripdate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        toDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tripdate.setText(dateFormatter.format(newDate.getTime()));

                Log.d("tripedate", "" + tripdate.getText().toString());


            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onClick(View v) {


            if (v == tripdate) {
                toDatePickerDialog.show();
            }
            else if(v == buttonplus3){
                String val3 = txtCount3.getText().toString().trim();

                    int k;
                    k = Integer.parseInt(val3);
                    k = k + 1;
                    txtCount3.setText(String.valueOf(k));
            }

            else if(v == buttonminus3){
                String val3 = txtCount3.getText().toString().trim();
                if(val3.equalsIgnoreCase("0"))   {
                    infantcount = "0";
                    return;
                } else {
                    int k;
                    k = Integer.parseInt(val3);
                    k = k - 1;
                    txtCount3.setText(String.valueOf(k));
                }
            }
            else if(v == buttonplus2){

                String val2 = txtCount2.getText().toString().trim();
                int j;
                    j = Integer.parseInt(val2);
                    j = j + 1;
                    txtCount2.setText(String.valueOf(j));
            }

            else if (v == buttonminus2)
            {String val2 = txtCount2.getText().toString().trim();
                if(val2.equalsIgnoreCase("0"))   {
                    childcount = "0";
                    return;
                } else {
                    int j;
                    j = Integer.parseInt(val2);
                    j = j - 1;
                    txtCount2.setText(String.valueOf(j));
                }}
            else if (v == buttonplus1){
                String val = txtCount.getText().toString().trim();
                int i;
                    i = Integer.parseInt(val);
                    i = i + 1;
                    txtCount.setText(String.valueOf(i));
            }
            else if(v == backclick){
                getFragmentManager().popBackStack();
            }




            else if (v == buttonminus)
            {String val = txtCount.getText().toString().trim();
                if(val.equalsIgnoreCase("0"))   {
                    adultcount = "0";
                    return;
                                } else {
                    int i;
                    i = Integer.parseInt(val);
                    i = i - 1;
                    txtCount.setText(String.valueOf(i));
                }}
            else if (v == next){
                ids = Sessiondata.getInstance().getTourid();
            snoids = Sessiondata.getInstance().getToursubid();
                datestring = tripdate.getText().toString();
                countryname = country.getText().toString();
            phonenoo = phno.getText().toString();
            emailvalue = emailhere.getText().toString();
                if(datestring.equals("Select Date")){
                Toast.makeText(getActivity(),"Select Your Date",Toast.LENGTH_SHORT).show();
                return;
            }
            else{datestring = tripdate.getText().toString();
                }
                adultcount =  txtCount.getText().toString();
                childcount = txtCount2.getText().toString();
                infantcount = txtCount3.getText().toString();
                if(countryname.equals("")){
                    Toast.makeText(getActivity(),"Enter your country",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                countryname = country.getText().toString();
            }

            if(phonenoo.equals("")){
                Toast.makeText(getActivity(),"Enter your PhoneNumber",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                phonenoo = phno.getText().toString();
            }
                if(emailvalue.equals(""))
            {
                Toast.makeText(getActivity(),"Enter your email address",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                if (emailvalue.matches(emailPattern))
                {
                    emailvalue = emailhere.getText().toString();
                }
                else
                {Toast.makeText(getActivity(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

            }Sessiondata.getInstance().setEmid(ids);
                Sessiondata.getInstance().setEmisubid(snoids);
                Sessiondata.getInstance().setEmdate(datestring);
                Sessiondata.getInstance().setEmadult(adultcount);
                Sessiondata.getInstance().setEmchild(childcount);
                Sessiondata.getInstance().setEmemail_id(emailvalue);
                Sessiondata.getInstance().setEmhome_country(countryname);
                Sessiondata.getInstance().setEminfant(infantcount);
                Sessiondata.getInstance().setEmphone_number(phonenoo);
                Sessiondata.getInstance().setEmtourtitle(Sessiondata.getInstance().getTourcontent());
                Fragment fr = new Specialrequest();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);}}


}