package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by jeyavijay on 01/09/16.
 */
public class Forgetpassworduser extends Fragment implements View.OnClickListener{
    int success;
    String msg;
    ProgressDialog progressDialog;
    String emailtext = "";
    RobotoTextView forcancel,forok;
    RobotoEditText email;
    private static final String FORGOTPASS = "http://hhwt.tech/hhwt_webservice/forgotpasswordhhwt.php";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forgetpasslayout,container,false);

        forcancel = (RobotoTextView) v.findViewById(R.id.forcancel);
        forok = (RobotoTextView) v.findViewById(R.id.forok);
        email = (RobotoEditText) v.findViewById(R.id.input_email);

        forok.setOnClickListener(this);
        forcancel.setOnClickListener(this);

        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(v == forcancel) {

            getActivity().finish();
        }
        else if(v == forok) {

            emailtext = email.getText().toString();

            if (null == emailtext || emailtext.equals("")) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter Email id");
                return;
            }
            if (!isEmailValid(emailtext)) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter valid email");
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

    progressDialog.dismiss();
    Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    getActivity().finish();

}
else if (success == 0) {
    progressDialog.dismiss();
    Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

}


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    }

    }){

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> param = new HashMap<String, String>();
                param.put("emailid",emailtext);

                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
