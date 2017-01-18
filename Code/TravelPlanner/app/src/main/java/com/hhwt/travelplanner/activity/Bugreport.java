package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by jeyavijay on 18/03/16.
 */
public class Bugreport extends Fragment implements View.OnClickListener {

    Button bugsubmit;
    EditText bugcomment;
    ProgressDialog progressDialog;
    public static final String KEY_USERNAME = "contents";
    private static final String BUGREPORT = "http://www.hhwt.tech/hhwt_webservice/reportabug.php";
    String comment;
    int feedbackstatus;
    String feedbackmsg;
    Hashtable connectFlags;
    LinearLayout linearvalbottom;
    ImageView backclick;
    TextView apptitle;
    public Bugreport() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reportbug, container, false);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Report a bug");


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        bugsubmit = (Button)v.findViewById(R.id.bugsubmit);
        bugcomment = (EditText)v.findViewById(R.id.bugtext);
        bugsubmit.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        if(v == bugsubmit){
             comment = bugcomment.getText().toString();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            Bugreports();
        }
        else if(v == backclick){
            getFragmentManager().popBackStack();
        }
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

    //session end
    @Override
    public void onStop() {
        super.onStop();
    }
    private void Bugreports() {
        final String username = comment;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,BUGREPORT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            feedbackstatus = userObject.getInt("status");
                            Log.d("feedbackstatus", "" + feedbackstatus);
                            feedbackmsg = userObject.getString("msg");
                            Log.d("feedbackmsg", "" + feedbackmsg);
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
                params.put(KEY_USERNAME,username);
                return params;}
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }
}
