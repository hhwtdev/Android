package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jeyavijay on 10/05/16.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    RobotoTextView login, forgotpass;
    RobotoEditText email, password;
    String emailtext = "", passwordtext = "";
    ProgressDialog progressDialog;
    int loginstatus;
    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";
   // private static final String LOGIN = "http://www.hhwt.tech/hhwt_webservice/loginnew.php";

    private static final String LOGIN = "http://www.hhwt.tech/hhwt_webservice/testingloginnew.php";


    String loginmsg;
    String emailvalue;
    String dateofbirth,phnumber,countryval;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id;
    String MyPREFERENCES = "HHWT";
    String name,pname;
    Activity _A;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.newlogin, container, false);
        _A = getActivity();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        login = (RobotoTextView) v.findViewById(R.id.loginbutton);
        email = (RobotoEditText) v.findViewById(R.id.input_email);
        password = (RobotoEditText) v.findViewById(R.id.input_password);
        forgotpass = (RobotoTextView) v.findViewById(R.id.forgotpass);
        login.setOnClickListener(this);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent in = new Intent(getActivity(),Forgot.class);
                startActivity(in);
            }
        });
        return v;
    }
    @Override
    public void onClick(View v) {
        emailtext = email.getText().toString();
        passwordtext = password.getText().toString();
        if (null == emailtext || emailtext.equals("")) {
            AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter Email id");
            return;
        }
        if (null == passwordtext || passwordtext.equals("")) {
            AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter Password");
            return;
        }
        if (!isEmailValid(emailtext)) {
            AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter valid email");
            return;
        } else {
            Login(v);
        }
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void Login(final View v) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject userObject = new JSONObject(response);
                            loginstatus = userObject.getInt("status");
                            loginmsg = userObject.getString("msg");
                             emailvalue = userObject.getString("email");
                            dateofbirth = userObject.getString("dob");
                            phnumber = userObject.getString("phonenumber");
                            countryval = userObject.getString("country");
                            String profileid = emailvalue;
                            String profileidr = emailvalue;
                            SharedPreferences preferencessssr = getActivity().getSharedPreferences("tempss", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorssr = preferencessssr.edit();
                            editorssr.putString("namess", profileidr);
                            editorssr.commit();
                            SharedPreferences preferencesss = getActivity().getSharedPreferences("tempsssssss", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorss = preferencesss.edit();
                            editorss.putString("namesssssemail", profileid);
                            editorss.commit();
                            SharedPreferences pre = getActivity().getSharedPreferences("temp", getActivity().getApplicationContext().MODE_PRIVATE);
                            try {
                                if (userObject.has("name")) {
                                     name = userObject.getString("name");
                                    editor.putString("name", name);
                                    editor.commit();
                                    if (null != name && !name.isEmpty()) {
                                        SharedPreferences.Editor ed = pre.edit();
                                        name = name + "_@@@@";
                                        ed.putString("name", name);
                                        ed.commit();
                                    }
                                } else {
                                    SharedPreferences.Editor ed = pre.edit();
                                    String name = "Username" + "_@@@@";
                                    ed.putString("name", name);
                                    ed.commit();
                                }
                            } catch (NullPointerException r) {
                                SharedPreferences.Editor ed = pre.edit();
                                String name = "Username" + "_@@@@";
                                ed.putString("name", name);
                                ed.commit();
                            }
                        } catch (Exception ex) {
                        }
                        if (loginstatus == 1) {
                            progressDialog.dismiss();
                            editor.putString("fb_id", emailtext);
                            editor.commit();
                            pname =  name.replace("_@@@@"," ");
                            String facebooway = "3";
                            SharedPreferences preferencessfbway = getActivity().getSharedPreferences("newfb", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsfbway = preferencessfbway.edit();
                            editorsfbway.putString("facebookway", facebooway);
                            editorsfbway.commit();
                            String registerprofilename =  pname;
                            Log.d("registerprofilename",""+registerprofilename);
                            SharedPreferences preferencessfbname = getActivity().getSharedPreferences("newprofilename", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsname = preferencessfbname.edit();
                            editorsname.putString("registername", registerprofilename);
                            editorsname.commit();
                            String facebookprofileemail =  emailvalue;
                            SharedPreferences preferencessfbprofileemail = getActivity().getSharedPreferences("newprofileemail", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                            editorsemail.putString("registeremail", facebookprofileemail);
                            editorsemail.commit();
                            String registerpassword = passwordtext;
                            SharedPreferences preferencessfbprofileid = getActivity().getSharedPreferences("newprofilefpassword", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorspassword = preferencessfbprofileid.edit();
                            editorspassword.putString("registerpassword", registerpassword);
                            editorspassword.commit();
                            String registerdob = dateofbirth;
                            SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofilefdob", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdob = preferencessdob.edit();
                            editorsdob.putString("registerdob", registerdob);
                            editorsdob.commit();
                            String registerphonenumber = phnumber;
                            Log.d("registerphonenumber",""+registerphonenumber);
                            SharedPreferences preferencessphno = getActivity().getSharedPreferences("newprofilefphonenumber", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphno = preferencessphno.edit();
                            editorsphno.putString("registerphonenumber", registerphonenumber);
                            editorsphno.commit();
                            String registercountry = countryval;
                            Log.d("registercountry",""+registercountry);
                            SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilefcountry", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountry = preferencesscountry.edit();
                            editorscountry.putString("registercountry", registercountry);
                            editorscountry.commit();

                            AlertUtils.SnackbarsuccessAlert(getActivity(), v, "Successfully logged in");
                            Runnable task = new Runnable() {
                                public void run() {
                                    Intent n = new Intent(getActivity(), IntroActivity.class);
                                    n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(n);
                                    getActivity().finish();
                                }
                            };
                            worker.schedule(task, 1, TimeUnit.SECONDS);
                        } else if (loginstatus == 0) {
                            progressDialog.dismiss();
                            AlertUtils.SnackbarerrorAlert(getActivity(), v, "Check Your Login");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,emailtext);
                params.put(KEY_PASSWORD,passwordtext);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }}