package com.hhwt.travelplanner.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 23/07/16.
 */
public class Specialrequest extends Fragment implements View.OnClickListener{
    TextView send;
EditText specialreport;
String spvalue,idval;
    public static final String ID ="id";
    private static final String DATEGUEST = "http://hhwt.tech/hhwt_webservice/tour.php";
    public static final String SUBID ="sub_id";
    public static final String TOURTITLE ="tour_title";
    public static final String SPECIALREQUEST ="special_request";
    public static final String DATE ="date";
    public static final String ADULT ="adult";
    public static final String CHILD ="child";
    public static final String INFANT ="infant";
    public static final String HOMECOUNTRY ="home_country";
    public static final String PHONENUMBER ="phone_number";
    public static final String EMAILID ="email_id";
    ProgressDialog progressDialog;

    ImageView backclick;
    LinearLayout linearvalbottom;

    TextView apptitle;
    int viewstatus;
    String message,ids,snoids,datestring,adultcount,childcount,infantcount,countryname,phonenoo,emailvalue,tourtile,specialrequest;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.specialrequest,container,false);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Tours");

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


        send = (TextView) v.findViewById(R.id.send);
        specialreport = (EditText) v.findViewById(R.id.specialreport);
        send.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {

        if(v == send){
            spvalue = specialreport.getText().toString();
            if (spvalue.equals("")){
                spvalue = " ";

            }
            else {
                spvalue = specialreport.getText().toString();
            }
            ids= Sessiondata.getInstance().getEmid();
            snoids= Sessiondata.getInstance().getEmisubid();
            datestring=Sessiondata.getInstance().getEmdate();
            adultcount=Sessiondata.getInstance().getEmadult();
            childcount=Sessiondata.getInstance().getEmchild();
            infantcount=Sessiondata.getInstance().getEminfant();
            countryname=Sessiondata.getInstance().getEmhome_country();
            phonenoo=Sessiondata.getInstance().getEmphone_number();
            emailvalue=Sessiondata.getInstance().getEmemail_id();
            tourtile=Sessiondata.getInstance().getEmtourtitle();
            specialrequest=spvalue;
            viewriewcomment();
        }

    else if(v == backclick){
            getFragmentManager().popBackStack();
        }

    }
    private void viewriewcomment() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,DATEGUEST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("success");
                            message = userObject.getString("message");

                        } catch (Exception ex) {
                        }
                        if (viewstatus == 1) {
                            progressDialog.dismiss();
                            Fragment fr = new Sucesstour();
                            FragmentChangeListener fc =  (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);
                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                params.put(ID,ids);
                params.put(SUBID,snoids);
                params.put(DATE,datestring);
                params.put(ADULT,adultcount);
                params.put(CHILD,childcount);
                params.put(INFANT,infantcount);
                params.put(HOMECOUNTRY,countryname);
                params.put(PHONENUMBER,phonenoo);
                params.put(EMAILID,emailvalue);
                params.put(TOURTITLE,tourtile);
                params.put(SPECIALREQUEST,specialrequest);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }
}
