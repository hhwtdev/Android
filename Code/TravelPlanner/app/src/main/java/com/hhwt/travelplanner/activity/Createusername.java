package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jeyavijay on 30/11/16.
 */
public class Createusername extends Activity implements View.OnClickListener{

    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";
/*    private static final String REGISTER = "http://www.hhwt.tech/hhwt_webservice/countrynew.php";*/

    private static final String REGISTER = "http://www.hhwt.tech/hhwt_webservice/registernewnov.php";




    public static final String KEY_COUNTRY = "country";
    public static final String KEY_NAME = "name";




    private  static  final String KEY_USERNAMENEW = "username";
    int registerstatus;
    String registermsg;
    SharedPreferences.Editor editor;
    String namevalue,countryj;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();


    private  static final String NAMECHECK   = "http://www.hhwt.tech/hhwt_webservice/usernamecheck.php";

int statusname;
String namemsg;
    String email, names, passwords, countryvaluenice;
    ProgressDialog progressDialog;
ImageView backlogin;
    SharedPreferences sharedpreferences;
    RobotoTextView newxtdashboard,unameworntext;
    RobotoEditText uname;
    String username;
    View nv0;
    String MyPREFERENCES = "HHWT";
View vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createnamehhwt);

        sharedpreferences = Createusername.this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        newxtdashboard = (RobotoTextView) findViewById(R.id.newxtdashboard);
        uname = (RobotoEditText) findViewById(R.id.uname);

        nv0 = (View) findViewById(R.id.nv0);

        unameworntext = (RobotoTextView) findViewById(R.id.unameworntext);
        unameworntext.setVisibility(View.VISIBLE);
        backlogin = (ImageView) findViewById(R.id.backcreateup);

        newxtdashboard.setOnClickListener(this);
        backlogin.findViewById(R.id.backcreateup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });




    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {



        if(v == newxtdashboard){
            newxtdashboard.setTextColor(Color.WHITE);
            newxtdashboard.setBackgroundResource(R.drawable.newlogincurveblue);

            username = uname.getText().toString();


            if(username.equals("")){
                unameworntext.setVisibility(View.VISIBLE);
                unameworntext.setText("Username can't have spaces or special characters");
                //nv1.setBackgroundResource(R.color.viewcolor);
                nv0.setBackgroundResource(R.color.viewcolor);
                return;
            }


            else if(!username.equals("")){
                email = Sessiondata.getInstance().getNewregemail();

                passwords = Sessiondata.getInstance().getNewregpass();
                names = Sessiondata.getInstance().getNewregname();
                countryvaluenice = Sessiondata.getInstance().getNewregcountry();

                usernamecheck();


            }





        }

    }

    private void usernamecheck() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, NAMECHECK, new Response.Listener<String>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(String response) {


                try{
                    JSONObject userobj = new JSONObject(response);

                    statusname = userobj.getInt("status");
                    namemsg = userobj.getString("msg");

                    if(statusname == 1){


                        unameworntext.setVisibility(View.VISIBLE);
                        unameworntext.setText("Username is available");
                        //nv1.setBackgroundResource(R.color.viewcolor);
                        unameworntext.setTextColor(getResources().getColor(R.color.viewcolorgreen));
                        nv0.setBackgroundResource(R.color.viewcolorgreen);
                        Register();
                    }

                    else {

                        unameworntext.setVisibility(View.VISIBLE);
                        unameworntext.setText("Username already exists");
                        //nv1.setBackgroundResource(R.color.viewcolor);
                        unameworntext.setTextColor(getResources().getColor(R.color.seconpluscolor));
                        nv0.setBackgroundResource(R.color.seconpluscolor);




                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },new Response.ErrorListener(){

            /**
             * Callback method that an error has been occurred with the
             * provided error code and optional user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Createusername.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",username);

                return params;
            }


            /* @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,email);
                params.put(KEY_PASSWORD,passwords);
                params.put(KEY_NAME,names);
                params.put(KEY_COUNTRY,countryvaluenice);
                return params;
            }*/
        };

RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);









    }


    private void Register() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        String emailvalue="";
                        try {
                            JSONObject userObject = new JSONObject(response);
                            registerstatus = userObject.getInt("status");
                            registermsg = userObject.getString("msg");
                            namevalue = userObject.getString("name");
                            countryj = userObject.getString("country");

                            editor.putString("name", namevalue);
                            editor.commit();
                            emailvalue = userObject.getString("email");
                            String profileid = emailvalue;
                            SharedPreferences preferencessssr = Createusername.this.getSharedPreferences("tempss", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorssr = preferencessssr.edit();
                            editorssr.putString("namess", profileid);
                            editorssr.commit();
                            SharedPreferences preferencesss = Createusername.this.getSharedPreferences("tempsssssss", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorss = preferencesss.edit();
                            editorss.putString("namesssssemail", emailvalue);
                            editorss.commit();
                            SharedPreferences pre = Createusername.this.getSharedPreferences("temp", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            try {
                                if (userObject.has("name")) {
                                    String name = userObject.getString("name");
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
                            Log.d("Excep", ex.getMessage());
                        }
                        if (registerstatus == 1) {
                            progressDialog.dismiss();
                            String facebooway = "4";
                            SharedPreferences preferencessfbway = Createusername.this.getSharedPreferences("newfb", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsfbway = preferencessfbway.edit();
                            editorsfbway.putString("facebookway", facebooway);
                            editorsfbway.commit();
                            String registerprofilename =  namevalue;
                            SharedPreferences preferencessfbname = Createusername.this.getSharedPreferences("newprofilename", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsname = preferencessfbname.edit();
                            editorsname.putString("registername", registerprofilename);
                            editorsname.commit();
                            String facebookprofileemail =  emailvalue;
                            SharedPreferences preferencessfbprofileemail = Createusername.this.getSharedPreferences("newprofileemail", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                            editorsemail.putString("registeremail", facebookprofileemail);
                            editorsemail.commit();
                            String registerpassword = passwords;
                            SharedPreferences preferencessfbprofileid = Createusername.this.getSharedPreferences("newprofilefpassword", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorspassword = preferencessfbprofileid.edit();
                            editorspassword.putString("registerpassword", registerpassword);
                            editorspassword.commit();
                            String registerdob = "";
                            SharedPreferences preferencessdob = Createusername.this.getSharedPreferences("newprofilefdob", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdob = preferencessdob.edit();
                            editorsdob.putString("registerdob", registerdob);
                            editorsdob.commit();
                            String registerphonenumber = "";
                            SharedPreferences preferencessphno = Createusername.this.getSharedPreferences("newprofilefphonenumber", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphno = preferencessphno.edit();
                            editorsphno.putString("registerphonenumber", registerphonenumber);
                            editorsphno.commit();
                            String registercountry = countryj;
                            SharedPreferences preferencesscountry = Createusername.this.getSharedPreferences("newprofilefcountry", Createusername.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountry = preferencesscountry.edit();
                            editorscountry.putString("registercountry", registercountry);
                            editorscountry.commit();
                            editor.putString("fb_id", emailvalue);
                            editor.commit();


                            Toast.makeText(Createusername.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                           // AlertUtils.SnackbarsuccessAlert(Createusername.this, vv, "Successfully Registered");
                            Runnable task = new Runnable() {
                                public void run() {


                                    Intent n = new Intent(Createusername.this, Newnavigationbottom.class);
                                    n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(n);
                                    Createusername.this.finish();








                                }
                            };
                            worker.schedule(task, 1, TimeUnit.SECONDS);
                        }
                        else if (registerstatus == 0) {
                            progressDialog.dismiss();
                           // AlertUtils.SnackbarsuccessAlert(Createusername.this, vv, "Registration Failed");


                            Toast.makeText(Createusername.this,"Registration Failed",Toast.LENGTH_SHORT).show();

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
                params.put(KEY_USERNAME,email);
                params.put(KEY_PASSWORD,passwords);
                params.put(KEY_NAME,names);
                params.put(KEY_COUNTRY,countryvaluenice);
                params.put(KEY_USERNAMENEW,username);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Createusername.this);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(Createusername.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }}

