package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 01/09/16.
 */
public class Forgot extends Activity implements View.OnClickListener{
    int success;
    String msg;
    ProgressDialog progressDialog;
    String emailtext = "";
    RobotoTextView forcancel,forok;
    RobotoEditText email;
    private static final String FORGOTPASS = "http://hhwt.tech/hhwt_webservice/forgotpasswordhhwt.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpasslayout);


        forcancel = (RobotoTextView) findViewById(R.id.forcancel);
        forok = (RobotoTextView) findViewById(R.id.forok);
        email = (RobotoEditText) findViewById(R.id.input_email);

        forok.setOnClickListener(this);
        forcancel.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == forcancel) {

         finish();
        }
        else if(v == forok) {

            emailtext = email.getText().toString();

            if (null == emailtext || emailtext.equals("")) {
                AlertUtils.SnackbarerrorAlert(getApplicationContext(), v, "Enter Email id");
                return;
            }
            if (!isEmailValid(emailtext)) {
                AlertUtils.SnackbarerrorAlert(getApplicationContext(), v, "Enter valid email");
                return;
            }

            forgotpass();

        }
    }


    private void forgotpass() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, FORGOTPASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Vollyarjunresponse", "" + response);

                try {
                    JSONObject userobject = new JSONObject(response);

                    success = userobject.getInt("success");

                    msg = userobject.getString("message");




                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                if(success == 1)
                {

                    //progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                finish();

                }
                else if (success == 0) {
                   // progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }

                }){

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> param = new HashMap<String, String>();
                param.put("emailid",emailtext);

                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
      /*  progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();*/

    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
