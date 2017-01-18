package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.fragment.Profileview;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by jeyavijay on 13/08/16.
 */
public class Changecountryprofile extends Fragment implements View.OnClickListener{
    //EditText changecountrytxt;

    Spinner changecountrytxts;

   // private static final String CHANGECOUNTRY = "http://hhwt.tech/hhwt_webservice/updateprofile.php";

    private static final String CHANGECOUNTRY = "http://hhwt.tech/hhwt_webservice/testingupdateprofile.php";

String countryseleted;
    public static final String FBID ="fb_id";
    public static final String IMAGEURL ="imgurl";
    public static final String NAME ="name";
    public static final String EMAIL ="email";
    public static final String PASSWORD ="password";
    public static final String DOB ="dob";
    public static final String PHONENUMBER ="phonenumber";
    public static final String COUNTRY ="country";
    int viewstatus;
    String message;
    ProgressDialog progressDialog;
String pfbid,pimgurl,pname,pemail,password,dob,phonenumber,countrryva;
    String con;
    Button changecountry,cancel;
    TextView apptitle;
    String countryname;
    ImageView backclick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.changecountryprofile,container,false);
       // changecountrytxt = (EditText) v.findViewById(R.id.changecountrytxt);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Edit country");


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        changecountrytxts = (Spinner) v.findViewById(R.id.changecountrytxt);



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
        changecountrytxts.setAdapter(dataAdapter);
        changecountrytxts.setSelection(0);
        changecountrytxts.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = changecountrytxts.getSelectedItemPosition();

                        countryseleted = countries.get(position);
                        if (countryseleted.equals("Country")) {
                        }
                    }@Override
                     public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }});




   //FDGDG



        changecountry = (Button) v.findViewById(R.id.changecountry);
        cancel = (Button) v.findViewById(R.id.cancelcountry);
        con = Sessiondata.getInstance().getLogincountry();
        if(con.equals("")){
            cancel.setText("Add Country");
           // changecountrytxt.setText(""+Sessiondata.getInstance().getLogincountry());
            changecountry.setVisibility(View.GONE);
            changecountrytxts.setFocusable(false);
            changecountrytxts.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            changecountrytxts.setClickable(false);
            cancel.setVisibility(View.VISIBLE);
            cancel.setOnClickListener(this);
            changecountry.setOnClickListener(this);
        }
else {cancel.setText("Change Country");
           // changecountrytxt.setText(""+Sessiondata.getInstance().getLogincountry());
            changecountry.setVisibility(View.GONE);
            changecountrytxts.setFocusable(false);
            changecountrytxts.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            changecountrytxts.setClickable(false);
            cancel.setVisibility(View.VISIBLE);
            cancel.setOnClickListener(this);
            changecountry.setOnClickListener(this);
        }
        return v;
    }
    @Override
    public void onClick(View v) {
        if(v == changecountry){
            countryname = countryseleted;
            if(countryname.equals(""))
            {Toast.makeText(getActivity(),"Enter the Country",Toast.LENGTH_LONG).show();
                return;
            }

         else if(countryname.equals("Country")){

                Toast.makeText(getActivity(),"Choose your country",Toast.LENGTH_LONG).show();
                return;
            }

            else {
                pfbid = Sessiondata.getInstance().getLoginfbid();
                pimgurl = "";
                pname = Sessiondata.getInstance().getLoginname();
                pemail = Sessiondata.getInstance().getLoginemail();
                password = Sessiondata.getInstance().getLoginpassword();
                dob = Sessiondata.getInstance().getLogindob();
                phonenumber = Sessiondata.getInstance().getLoginphonenumber();
                countrryva = countryname;
                Sessiondata.getInstance().setLogincountry(countrryva);
                changecountry();
            }} else if (v == cancel) {
            changecountry.setVisibility(View.VISIBLE);
          //  changecountrytxt.setFocusable(true);
          //  changecountrytxt.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
           // changecountrytxt.setClickable(true);
           // changecountrytxt.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            cancel.setVisibility(View.GONE);
        }

        else if (v == backclick) {
            getFragmentManager().popBackStack();
        }
    }
    private void changecountry() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,CHANGECOUNTRY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("success");
                            message = userObject.getString("message");
                        } catch (Exception ex) {
                        }
                        if (viewstatus == 1) {
                            progressDialog.dismiss();
                            String facebookprofilecountry =  countrryva;
                            SharedPreferences preferencessfbprofilecountry = getActivity().getSharedPreferences("newprofilecountry", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountry = preferencessfbprofilecountry.edit();
                            editorscountry.putString("facebookcountry", facebookprofilecountry);
                            editorscountry.commit();
                            String registercountry = countrryva;
                            SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilefcountry", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountryre = preferencesscountry.edit();
                            editorscountryre.putString("registercountry", registercountry);
                            editorscountryre.commit();
                            String facebookprofilephonenumber =  phonenumber;
                            SharedPreferences preferencessfbprofilephonenumber = getActivity().getSharedPreferences("newprofilephomenumber", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphonenumber = preferencessfbprofilephonenumber.edit();
                            editorsphonenumber.putString("facebookphonenumberr", facebookprofilephonenumber);
                            editorsphonenumber.commit();
                            String registerphonenumber = phonenumber;
                            SharedPreferences preferencessphno = getActivity().getSharedPreferences("newprofilefphonenumber", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphno = preferencessphno.edit();
                            editorsphno.putString("registerphonenumber", registerphonenumber);
                            editorsphno.commit();
                            String facebookprofiledob =  dob;
                            SharedPreferences preferencessfbprofiledob = getActivity().getSharedPreferences("newprofiledob", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdob = preferencessfbprofiledob.edit();
                            editorsdob.putString("facebookdob", facebookprofiledob);
                            editorsdob.commit();
                            String registerdob = dob;
                            SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofilefdob", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdobre = preferencessdob.edit();
                            editorsdobre.putString("registerdob", registerdob);
                            editorsdobre.commit();
                            String newprofilename =  pname;
                            SharedPreferences preferencessfbname = getActivity().getSharedPreferences("newprofilename", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsname = preferencessfbname.edit();
                            editorsname.putString("facebookname", newprofilename);
                            editorsname.commit();
                            String registerprofilename =  pname;
                            SharedPreferences preferencessfbnamere = getActivity().getSharedPreferences("newprofilename", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsnamere = preferencessfbnamere.edit();
                            editorsnamere.putString("registername", registerprofilename);
                            editorsnamere.commit();
                            String registerpassword = password;
                            SharedPreferences preferencessfbprofileid = getActivity().getSharedPreferences("newprofilefpassword", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorspassword = preferencessfbprofileid.edit();
                            editorspassword.putString("registerpassword", registerpassword);
                            editorspassword.commit();
                            String facebookprofileemail =  pemail;
                            SharedPreferences preferencessfbprofileemail = getActivity().getSharedPreferences("newprofileemail", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                            editorsemail.putString("registeremail", facebookprofileemail);
                            editorsemail.commit();
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(changecountrytxts.getWindowToken(), 0);
                            Fragment fr = new Profileview();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);
                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                             Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();}}},
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
                params.put(FBID,pfbid);
                params.put(IMAGEURL,pimgurl);
                params.put(NAME,pname);
                params.put(EMAIL,pemail);
                params.put(PASSWORD,password);
                params.put(DOB,dob);
                params.put(PHONENUMBER,phonenumber);
                params.put(COUNTRY,countrryva);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }}
