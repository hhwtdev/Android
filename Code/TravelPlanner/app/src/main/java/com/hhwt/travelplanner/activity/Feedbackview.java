package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RatingBar;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by jeyavijay on 18/03/16.
 */
public class Feedbackview extends Fragment implements View.OnClickListener{
    RatingBar feedratingbar1;
    RatingBar feedratingbar3;
    RatingBar feedratingbar2;
    Button feedsubmit;
    ProgressDialog progressDialog;
    String rating1,rating2,rating3;
    public static final String KEY_USERNAME = "contents";
    Hashtable connectFlags;
    public static final String KEY_USERID = "name";
    public static final String KEY_QUESTION1 = "feedbackone";
    public static final String KEY_QUESTION2 = "feedbacktwo";
    public static final String KEY_QUESTION3 = "feedbackthree";
    public static final String KEY_QUESTION4 = "feedbackfour";
    public static final String KEY_QUESTION5 = "feedbackfive";
    EditText question4,question5;
    String ques4,ques5,userid;
    int feedbackstatus;
    String feedbackmsg;
    TextView apptitle;

    ImageView backclick;

    LinearLayout linearvalbottom;


    private static final String FEEDBACK = "http://hhwt.tech/hhwt_webservice/feedback.php";
    public Feedbackview() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feedbackscreen, container, false);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Add a feedback");

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        feedratingbar1 = (RatingBar)v.findViewById(R.id.feedratingBarplan);
        feedratingbar2 = (RatingBar)v.findViewById(R.id.feedratingBarprayer);
        feedratingbar3 = (RatingBar)v.findViewById(R.id.feedratingBarmyfriend);
        question4 = (EditText)v.findViewById(R.id.liketosee);
        question5 = (EditText)v.findViewById(R.id.teamsee);
        feedsubmit = (Button)v.findViewById(R.id.feedsubmit);
       feedsubmit.setOnClickListener(this);
        return v;
    }
    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onClick(View v) {
        if(v == feedsubmit){
            SharedPreferences preferences= getActivity().getSharedPreferences("tempss", getActivity().MODE_PRIVATE);
            String name=preferences.getString("namess",null);
            userid = name;
            rating1=String.valueOf(feedratingbar1.getRating());
            rating2=String.valueOf(feedratingbar2.getRating());
            rating3=String.valueOf(feedratingbar3.getRating());
            ques4 = question4.getText().toString();
            ques5 = question5.getText().toString();
            Feedbackcustomer();
        }

        else if(v == backclick) {
            getFragmentManager().popBackStack();
        }
    }

    private void Feedbackcustomer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, FEEDBACK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            feedbackstatus = userObject.getInt("success");
                            feedbackmsg = userObject.getString("message");
                        } catch (Exception ex) {
                        }
                        if (feedbackstatus == 1) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), feedbackmsg, Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getActivity(),Newnavigationbottom.class);
                            startActivity(in);
                            getActivity().finish();
                        } else if (feedbackstatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Log.d("Mapparams",""+params);
                params.put(KEY_USERID,userid);
                params.put(KEY_QUESTION1,rating1);
                params.put(KEY_QUESTION2,rating2);
                params.put(KEY_QUESTION3,rating3);
                params.put(KEY_QUESTION4,ques4);
                params.put(KEY_QUESTION5,ques5);
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