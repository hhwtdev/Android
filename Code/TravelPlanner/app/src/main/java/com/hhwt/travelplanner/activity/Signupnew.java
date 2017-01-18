package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jeyavijay on 08/11/16.
 */
public class Signupnew extends Activity implements View.OnClickListener{




    String namevalue,countryj;
    RobotoTextView rregisterbutton;
    RobotoEditText emailid, password, conformpassword;
    String email, passwords, comformpass;
    int registerstatus;
    String registermsg;
    ProgressDialog progressDialog;
    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";
    private Spinner country;
    public static final String KEY_COUNTRY = "country";
    // private static final String REGISTER = "http://www.hhwt.tech/hhwt_webservice/registernew.php";
    private static final String REGISTER = "http://www.hhwt.tech/hhwt_webservice/countrynew.php";
  //  String countryseleted,countryvaluenice;
    public static final String KEY_NAME = "name";
    RobotoTextView privatepolicys;
    Activity _A;
    public String fb_id;
    String MyPREFERENCES = "HHWT";
    //Overriden method onCreateView
    SharedPreferences sharedpreferences;
    RobotoTextView passwordworntext,emailworntext;


    SharedPreferences.Editor editor;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    class Privacy extends ClickableSpan { //clickable span
        public void onClick(View textView) {
            Intent privacypolicy = new Intent(_A, Web_View.class);
            Bundle b = new Bundle();
            privacypolicy.putExtras(b);
            startActivity(privacypolicy);
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(_A, R.color.cpb_blue));//set text color
            ds.setUnderlineText(true); // set to false to remove underline
        }
    }


View nv1,nv2;
    ImageView backlogin;
    RobotoTextView loginregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupnewmain);

        loginregister = (RobotoTextView) findViewById(R.id.loginregister);

        loginregister.setOnClickListener(this);
        backlogin = (ImageView) findViewById(R.id.backsignup);
       // nv0 = (View) findViewById(R.id.nv0);
        nv1 = (View) findViewById(R.id.nv1);
        nv2 = (View) findViewById(R.id.nv2);
      //  nv3 = (View) findViewById(R.id.nv4);

        backlogin.findViewById(R.id.backsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        _A = Signupnew.this;
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        rregisterbutton = (RobotoTextView) findViewById(R.id.registerbutton);
        emailid = (RobotoEditText) findViewById(R.id.input_remailid);
       // name = (RobotoEditText) findViewById(R.id.input_rename);
        password = (RobotoEditText) findViewById(R.id.input_repassword);
        rregisterbutton.setOnClickListener(this);


        passwordworntext = (RobotoTextView) findViewById(R.id.passwordworntext);
        passwordworntext.setVisibility(View.GONE);
        emailworntext = (RobotoTextView) findViewById(R.id.emailworntext);

        emailworntext.setVisibility(View.GONE);


       /* nameworntext = (RobotoTextView) findViewById(R.id.nameworntext);
        nameworntext.setVisibility(View.GONE);*/

       /* countrywrong = (RobotoTextView) findViewById(R.id.countrywrong);
        countrywrong.setVisibility(View.GONE);*/

      /*  country = (Spinner) findViewById(R.id.spinner1);
        Locale[] locales = Locale.getAvailableLocales();
        final ArrayList<String> countries = new ArrayList<String>();
        countries.add("Country");
        countries.add("Singapore");
        countries.add("Malaysia");
        countries.add("Brunei");
        countries.add("Indonesia");
        countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("Andorra");
        countries.add("Angola");
        countries.add("Antigua and Barbuda");
        countries.add("Argentina");
        countries.add("Armenia");
        countries.add("Aruba");
        countries.add("Australia");
        countries.add("Austria");
        countries.add("Azerbaijan");
        countries.add("Bahamas, The");
        countries.add("Bahrain");
        countries.add("Bangladesh");
        countries.add("Barbados");
        countries.add("Belarus");
        countries.add("Belgium");
        countries.add("Belize");
        countries.add("Benin");
        countries.add("Bhutan");
        countries.add("Bolivia");
        countries.add("Bosnia and Herzegovina");
        countries.add("Botswana");
        countries.add("Brazil");
        countries.add("Bulgaria");
        countries.add("Burkina Faso");
        countries.add("Burma");
        countries.add("Burundi");
        countries.add("Cambodia");
        countries.add("Cameroon");
        countries.add("Canada");
        countries.add("Cabo Verde");
        countries.add("Central African Republic");
        countries.add("Chad");
        countries.add("Chile");
        countries.add("China");
        countries.add("Colombia");
        countries.add("Comoros");
        countries.add("Congo, Democratic Republic of the");
        countries.add("Congo, Republic of the");
        countries.add("Costa Rica");
        countries.add("Cote d'Ivoire");
        countries.add("Croatia");
        countries.add("Cuba");
        countries.add("Curacao");
        countries.add("Cyprus");
        countries.add("Czech Republic");
        countries.add("Denmark");
        countries.add("Djibouti");
        countries.add("Dominica");
        countries.add("Dominican Republic");
        countries.add("East Timor (see Timor-Leste)");
        countries.add("Ecuador");
        countries.add("Egypt");
        countries.add("El Salvador");
        countries.add("Equatorial Guinea");
        countries.add("Eritrea");
        countries.add("Estonia");
        countries.add("Ethiopia");
        countries.add("Fiji");
        countries.add("Finland");
        countries.add("France");
        countries.add("Gabon");
        countries.add("Gambia, The");
        countries.add("Georgia");
        countries.add("Germany");
        countries.add("Ghana");
        countries.add("Greece");
        countries.add("Grenada");
        countries.add("Guatemala");
        countries.add("Guinea");
        countries.add("Guinea-Bissau");
        countries.add("Guyana");
        countries.add("Haiti");
        countries.add("Holy See");
        countries.add("Honduras");
        countries.add("Hong Kong");
        countries.add("Hungary");
        countries.add("Iceland");
        countries.add("India");
        countries.add("Iran");
        countries.add("Iraq");
        countries.add("Ireland");
        countries.add("Israel");
        countries.add("Italy");
        countries.add("Jamaica");
        countries.add("Japan");
        countries.add("Jordan");
        countries.add("Kazakhstan");
        countries.add("Kenya");
        countries.add("Kiribati");
        countries.add("Korea, North");
        countries.add("Korea, South");
        countries.add("Kosovo");
        countries.add("Kuwait");
        countries.add("Kyrgyzstan");
        countries.add("Laos");
        countries.add("Latvia");
        countries.add("Lebanon");
        countries.add("Lesotho");
        countries.add("Liberia");
        countries.add("Libya");
        countries.add("Liechtenstein");
        countries.add("Lithuania");
        countries.add("Luxembourg");
        countries.add("Macau");
        countries.add("Macedonia");
        countries.add("Madagascar");
        countries.add("Malawi");
        countries.add("Maldives");
        countries.add("Mali");
        countries.add("Malta");
        countries.add("Marshall Islands");
        countries.add("Mauritania");
        countries.add("Mauritius");
        countries.add("Mexico");
        countries.add("Micronesia");
        countries.add("Moldova");
        countries.add("Monaco");
        countries.add("Mongolia");
        countries.add("Montenegro");
        countries.add("Morocco");
        countries.add("Mozambique");
        countries.add("Namibia");
        countries.add("Nauru");
        countries.add("Nepal");
        countries.add("Netherlands");
        countries.add("Netherlands Antilles");
        countries.add("New Zealand");
        countries.add("Nicaragua");
        countries.add("Niger");
        countries.add("Nigeria");
        countries.add("North Korea");
        countries.add("Norway");
        countries.add("Oman");
        countries.add("Pakistan");
        countries.add("Palau");
        countries.add("Palestinian Territories");
        countries.add("Panama");
        countries.add("Papua New Guinea");
        countries.add("Paraguay");
        countries.add("Peru");
        countries.add("Philippines");
        countries.add("Poland");
        countries.add("Portugal");
        countries.add("Qatar");
        countries.add("Romania");
        countries.add("Russia");
        countries.add("Rwanda");
        countries.add("Saint Kitts and Nevis");
        countries.add("Saint Lucia");
        countries.add("Saint Vincent and the Grenadines");
        countries.add("Samoa");
        countries.add("San Marino");
        countries.add("Sao Tome and Principe");
        countries.add("Saudi Arabia");
        countries.add("Senegal");
        countries.add("Serbia");
        countries.add("Seychelles");
        countries.add("Sierra Leone");
        countries.add("Sint Maarten");
        countries.add("Slovakia");
        countries.add("Slovenia");
        countries.add("Solomon Islands");
        countries.add("Somalia");
        countries.add("South Africa");
        countries.add("South Korea");
        countries.add("South Sudan");
        countries.add("Spain ");
        countries.add("Sri Lanka");
        countries.add("Sudan");
        countries.add("Suriname");
        countries.add("Swaziland");
        countries.add("Sweden");
        countries.add("Switzerland");
        countries.add("Syria");
        countries.add("Taiwan");
        countries.add("Tajikistan");
        countries.add("Tanzania");
        countries.add("Thailand");
        countries.add("Timor-Leste");
        countries.add("Togo");
        countries.add("Tonga");
        countries.add("Trinidad and Tobago");
        countries.add("Tunisia");
        countries.add("Turkey");
        countries.add("Turkmenistan");
        countries.add("Tuvalu");
        countries.add("Uganda");
        countries.add("Ukraine");
        countries.add("United Arab Emirates");
        countries.add("United Kingdom");
        countries.add("United State of America");
        countries.add("Uruguay");
        countries.add("Uzbekistan");
        countries.add("Vanuatu");
        countries.add("Venezuela");
        countries.add("Vietnam");
        countries.add("Yemen");
        countries.add("Zambia");
        countries.add("Zimbabwe");
       *//* for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);}
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }*//*
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Signupnew.this,
                R.layout.simple_spinner_itemcustom, countries);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(dataAdapter);
        country.setSelection(0);
        country.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = country.getSelectedItemPosition();

                        countryseleted = countries.get(position);


                        if(countryseleted.equals("Country")){
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                        }
                        else{
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                        }





                    }@Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }});

*/














    }



    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }






    @Override
    public void onClick(View v) {
        if (v == rregisterbutton) {



            InputMethodManager imm = (InputMethodManager) _A.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(emailid.getWindowToken(), 0);
/*
            InputMethodManager namess = (InputMethodManager) _A.getSystemService(Context.INPUT_METHOD_SERVICE);
            namess.hideSoftInputFromWindow(name.getWindowToken(), 0);*/

            InputMethodManager passwordS = (InputMethodManager) _A.getSystemService(Context.INPUT_METHOD_SERVICE);
            passwordS.hideSoftInputFromWindow(password.getWindowToken(), 0);

            rregisterbutton.setTextColor(Color.WHITE);
            rregisterbutton.setBackgroundResource(R.drawable.newlogincurveblue);










            email = emailid.getText().toString();
         //   names = name.getText().toString();
            passwords = password.getText().toString();
            comformpass = password.getText().toString();
          //  countryvaluenice = countryseleted;

           /* if (null == names || names.equals("")) {
              //  AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Enter name");
              //  nv0.setBackgroundResource(R.color.seconpluscolor);
                nv2.setBackgroundResource(R.color.viewcolor);
                nv1.setBackgroundResource(R.color.viewcolor);
               // nv3.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.GONE);
                emailworntext.setVisibility(View.GONE);
                nameworntext.setVisibility(View.VISIBLE);
                nameworntext.setText("Enter name");
               // countrywrong.setVisibility(View.GONE);

                return;}*/
            if (null == email || email.equals("")) {
               // AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Enter email");
                nv1.setBackgroundResource(R.color.seconpluscolor);
                //nv0.setBackgroundResource(R.color.viewcolor);
                nv2.setBackgroundResource(R.color.viewcolor);
               // nv3.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.GONE);
                emailworntext.setVisibility(View.VISIBLE);
                emailworntext.setText("Enter email addres");
              //  nameworntext.setVisibility(View.GONE);
              //  countrywrong.setVisibility(View.GONE);
                return;
            }  if (null == passwords || passwords.equals("")) {
                //AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Enter Password");
                nv2.setBackgroundResource(R.color.seconpluscolor);
                nv1.setBackgroundResource(R.color.viewcolor);
           //     nv0.setBackgroundResource(R.color.viewcolor);
               // nv3.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.VISIBLE);
                passwordworntext.setText("Enter Password");
                emailworntext.setVisibility(View.GONE);
               // nameworntext.setVisibility(View.GONE);
               // countrywrong.setVisibility(View.GONE);
                return;
            }


            if(passwords.length() < 6){
               // AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Password needs to have a minimum of 6 characters");
                nv2.setBackgroundResource(R.color.seconpluscolor);
                nv1.setBackgroundResource(R.color.viewcolor);
               // nv0.setBackgroundResource(R.color.viewcolor);
                //nv3.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.VISIBLE);
                passwordworntext.setText("Password needs to have a minimum of 6 characters");
                emailworntext.setVisibility(View.GONE);
               // nameworntext.setVisibility(View.GONE);
               // countrywrong.setVisibility(View.GONE);
                return;
            }


            if(!passwords.equals(comformpass)){
                //AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Password not matching");
                nv2.setBackgroundResource(R.color.seconpluscolor);
                nv1.setBackgroundResource(R.color.viewcolor);
             //   nv0.setBackgroundResource(R.color.viewcolor);
                //nv3.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.VISIBLE);
                passwordworntext.setText("Password needs to have a minimum of 6 characters");
                emailworntext.setVisibility(View.GONE);
              //  nameworntext.setVisibility(View.GONE);
                return;
            }
            if (!isValidEmail(email)) {
               // AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Enter valid email");
                nv1.setBackgroundResource(R.color.seconpluscolor);
              //  nv0.setBackgroundResource(R.color.viewcolor);
                nv2.setBackgroundResource(R.color.viewcolor);
               // nv3.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.GONE);
                emailworntext.setVisibility(View.VISIBLE);
                emailworntext.setText("Invalid email addres");
               // nameworntext.setVisibility(View.GONE);
               // countrywrong.setVisibility(View.GONE);

                return;
            }


          /*  if(countryvaluenice.equals("Country")){
                nv3.setBackgroundResource(R.color.seconpluscolor);
                nv1.setBackgroundResource(R.color.viewcolor);
                nv0.setBackgroundResource(R.color.viewcolor);
                nv2.setBackgroundResource(R.color.viewcolor);
                passwordworntext.setVisibility(View.GONE);
                emailworntext.setVisibility(View.GONE);
                nameworntext.setVisibility(View.GONE);
                countrywrong.setVisibility(View.VISIBLE);
                countrywrong.setText("Choose your country");

                //AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Choose your country");



                return;
            }
*/









            else {


                Sessiondata.getInstance().setNewregemail(email);
                Sessiondata.getInstance().setNewregpass(passwords);

                Sessiondata.getInstance().setCountryselect("Country");

                //Register(v);
                Intent n = new Intent(Signupnew.this, Newhhwtwelcomepro.class);
                startActivity(n);



            }
        } else if(v == loginregister){

            Intent n = new Intent(Signupnew.this, Newhhwtactlogin.class);
            startActivity(n);

            //  finish();
        }}
















    private void Register(final View v) {

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
                            SharedPreferences preferencessssr = Signupnew.this.getSharedPreferences("tempss", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorssr = preferencessssr.edit();
                            editorssr.putString("namess", profileid);
                            editorssr.commit();
                            SharedPreferences preferencesss = Signupnew.this.getSharedPreferences("tempsssssss", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorss = preferencesss.edit();
                            editorss.putString("namesssssemail", emailvalue);
                            editorss.commit();
                            SharedPreferences pre = Signupnew.this.getSharedPreferences("temp", Signupnew.this.getApplicationContext().MODE_PRIVATE);
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
                            SharedPreferences preferencessfbway = Signupnew.this.getSharedPreferences("newfb", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsfbway = preferencessfbway.edit();
                            editorsfbway.putString("facebookway", facebooway);
                            editorsfbway.commit();
                            String registerprofilename =  namevalue;
                            SharedPreferences preferencessfbname = Signupnew.this.getSharedPreferences("newprofilename", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsname = preferencessfbname.edit();
                            editorsname.putString("registername", registerprofilename);
                            editorsname.commit();
                            String facebookprofileemail =  emailvalue;
                            SharedPreferences preferencessfbprofileemail = Signupnew.this.getSharedPreferences("newprofileemail", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                            editorsemail.putString("registeremail", facebookprofileemail);
                            editorsemail.commit();
                            String registerpassword = passwords;
                            SharedPreferences preferencessfbprofileid = Signupnew.this.getSharedPreferences("newprofilefpassword", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorspassword = preferencessfbprofileid.edit();
                            editorspassword.putString("registerpassword", registerpassword);
                            editorspassword.commit();
                            String registerdob = "";
                            SharedPreferences preferencessdob = Signupnew.this.getSharedPreferences("newprofilefdob", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdob = preferencessdob.edit();
                            editorsdob.putString("registerdob", registerdob);
                            editorsdob.commit();
                            String registerphonenumber = "";
                            SharedPreferences preferencessphno = Signupnew.this.getSharedPreferences("newprofilefphonenumber", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphno = preferencessphno.edit();
                            editorsphno.putString("registerphonenumber", registerphonenumber);
                            editorsphno.commit();
                            String registercountry = countryj;
                            SharedPreferences preferencesscountry = Signupnew.this.getSharedPreferences("newprofilefcountry", Signupnew.this.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountry = preferencesscountry.edit();
                            editorscountry.putString("registercountry", registercountry);
                            editorscountry.commit();
                            editor.putString("fb_id", emailvalue);
                            editor.commit();
                            AlertUtils.SnackbarsuccessAlert(Signupnew.this, v, "Successfully Registered");
                            Runnable task = new Runnable() {
                                public void run() {


                                 /*   Intent n = new Intent(Signupnew.this, Newnavigationbottom.class);
                                    n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(n);
                                    Signupnew.this.finish();*/



                                    Intent n = new Intent(Signupnew.this, Newhhwtwelcomepro.class);
                                    n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(n);
                                    Signupnew.this.finish();




                                }
                            };
                            worker.schedule(task, 1, TimeUnit.SECONDS);
                        }
                        else if (registerstatus == 0) {
                            progressDialog.dismiss();
                            AlertUtils.SnackbarsuccessAlert(Signupnew.this, v, "Registration Failed");
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
               // params.put(KEY_NAME,names);
               // params.put(KEY_COUNTRY,countryvaluenice);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Signupnew.this);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(Signupnew.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }}







