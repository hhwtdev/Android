package com.hhwt.travelplanner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.activity.IntroActivity;
import com.hhwt.travelplanner.activity.Web_View;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class RegisterationFragment extends Fragment implements View.OnClickListener{
    String namevalue,countryj;
    RobotoTextView rregisterbutton;
    RobotoEditText emailid, password, conformpassword, name;
    String email, names, passwords, comformpass;
    int registerstatus;
    String registermsg;
    ProgressDialog progressDialog;
    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";
    private Spinner country;
    public static final String KEY_DOB = "dob";
    public static final String KEY_PHONENUMBER = "phonenumber";
    public static final String KEY_COUNTRY = "country";
   // private static final String REGISTER = "http://www.hhwt.tech/hhwt_webservice/registernew.php";


    private static final String REGISTER = "http://www.hhwt.tech/hhwt_webservice/countrynew.php";





String countryseleted,countryvaluenice;
    public static final String KEY_NAME = "name";
    RobotoTextView privatepolicys;
    Activity _A;
    public String fb_id;
    String MyPREFERENCES = "HHWT";
    //Overriden method onCreateView
    SharedPreferences sharedpreferences;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.newregister, container, false);
        _A = getActivity();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        rregisterbutton = (RobotoTextView) v.findViewById(R.id.registerbutton);
        emailid = (RobotoEditText) v.findViewById(R.id.input_remailid);
        name = (RobotoEditText) v.findViewById(R.id.input_rename);
        password = (RobotoEditText) v.findViewById(R.id.input_repassword);
        conformpassword = (RobotoEditText) v.findViewById(R.id.input_reconformepassword);
        rregisterbutton.setOnClickListener(this);
        privatepolicys = (RobotoTextView) v.findViewById(R.id.privatepolicy);
        Spannable wordtoSpan = new SpannableString("By registering you agree to the Privacy Policy");
        wordtoSpan.setSpan(new Privacy(), 32, 46, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privatepolicys.setText(wordtoSpan);
        privatepolicys.setOnClickListener(this);
        country = (Spinner) v.findViewById(R.id.spinner1);
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
        countries.add("Uruguay");
        countries.add("Uzbekistan");
        countries.add("Vanuatu");
        countries.add("Venezuela");
        countries.add("Vietnam");
        countries.add("Yemen");
        countries.add("Zambia");
        countries.add("Zimbabwe");
       /* for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);}
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }*/
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, countries);
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
                        if (countryseleted.equals("Country")) {
                        }
                    }@Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }});return v;
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
            email = emailid.getText().toString();
            names = name.getText().toString();
            passwords = password.getText().toString();
            comformpass = conformpassword.getText().toString();
            countryvaluenice = countryseleted;

             if (null == names || names.equals("")) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter name");
                return;}
                if (null == email || email.equals("")) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter email");
                return;
            }  if (null == passwords || passwords.equals("")) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter Password");
                return;
            }
            if (null == comformpass || comformpass.equals("")) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter conform Password");
                return;
            }
            if(!passwords.equals(comformpass)){
                 AlertUtils.SnackbarerrorAlert(getActivity(), v, "Password not matching");
                 return;
             }
            if (!isValidEmail(email)) {
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Enter valid email");
                return;
            }
            if(countryvaluenice.equals("Country")){
                AlertUtils.SnackbarerrorAlert(getActivity(), v, "Choose your country");
                return;
            }
            else {
                Register(v);
            }
        } else if (v == privatepolicys) {
            Intent intent = new Intent(getActivity(), Web_View.class);
            startActivity(intent);
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
                            SharedPreferences preferencessssr = getActivity().getSharedPreferences("tempss", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorssr = preferencessssr.edit();
                            editorssr.putString("namess", profileid);
                            editorssr.commit();
                            SharedPreferences preferencesss = getActivity().getSharedPreferences("tempsssssss", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorss = preferencesss.edit();
                            editorss.putString("namesssssemail", emailvalue);
                            editorss.commit();
                            SharedPreferences pre = getActivity().getSharedPreferences("temp", getActivity().getApplicationContext().MODE_PRIVATE);
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
                            SharedPreferences preferencessfbway = getActivity().getSharedPreferences("newfb", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsfbway = preferencessfbway.edit();
                            editorsfbway.putString("facebookway", facebooway);
                            editorsfbway.commit();
                            String registerprofilename =  namevalue;
                            SharedPreferences preferencessfbname = getActivity().getSharedPreferences("newprofilename", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsname = preferencessfbname.edit();
                            editorsname.putString("registername", registerprofilename);
                            editorsname.commit();
                            String facebookprofileemail =  emailvalue;
                            SharedPreferences preferencessfbprofileemail = getActivity().getSharedPreferences("newprofileemail", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                            editorsemail.putString("registeremail", facebookprofileemail);
                            editorsemail.commit();
                            String registerpassword = passwords;
                            SharedPreferences preferencessfbprofileid = getActivity().getSharedPreferences("newprofilefpassword", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorspassword = preferencessfbprofileid.edit();
                            editorspassword.putString("registerpassword", registerpassword);
                            editorspassword.commit();
                            String registerdob = "";
                            SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofilefdob", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdob = preferencessdob.edit();
                            editorsdob.putString("registerdob", registerdob);
                            editorsdob.commit();
                            String registerphonenumber = "";
                            SharedPreferences preferencessphno = getActivity().getSharedPreferences("newprofilefphonenumber", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphno = preferencessphno.edit();
                            editorsphno.putString("registerphonenumber", registerphonenumber);
                            editorsphno.commit();
                            String registercountry = countryj;
                            SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilefcountry", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountry = preferencesscountry.edit();
                            editorscountry.putString("registercountry", registercountry);
                            editorscountry.commit();
                            editor.putString("fb_id", emailvalue);
                            editor.commit();
                            AlertUtils.SnackbarsuccessAlert(getActivity(), v, "Successfully Registered");
                            Runnable task = new Runnable() {
                                public void run() {
                                    Intent n = new Intent(getActivity(), IntroActivity.class);
                                    n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(n);
                                    getActivity().finish();
                                }
                            };
                            worker.schedule(task, 1, TimeUnit.SECONDS);
                        }
                        else if (registerstatus == 0) {
                            progressDialog.dismiss();
                            AlertUtils.SnackbarsuccessAlert(getActivity(), v, "Registration Failed");
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
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }}