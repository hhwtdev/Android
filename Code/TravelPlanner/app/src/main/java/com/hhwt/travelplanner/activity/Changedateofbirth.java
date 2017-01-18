package com.hhwt.travelplanner.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
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
import com.hhwt.travelplanner.fragment.Profileview;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by jeyavijay on 13/08/16.
 */
public class Changedateofbirth extends Fragment implements View.OnClickListener{
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    Button changedateofbirth,canceldateofbirth;
    String dateoffbirth;
    TextView tripdate;
   // private static final String CHANGECOUNTRY = "http://hhwt.tech/hhwt_webservice/updateprofile.php";

    private static final String CHANGECOUNTRY = "http://hhwt.tech/hhwt_webservice/testingupdateprofile.php";
    public static final String FBID ="fb_id";
    public static final String IMAGEURL ="imgurl";
    public static final String NAME ="name";
    public static final String EMAIL ="email";
    public static final String PASSWORD ="password";
    public static final String DOB ="dob";
    public static final String PHONENUMBER ="phonenumber";
    public static final String COUNTRY ="country";
    int viewstatus;
    TextView  apptitle;
    ImageView backclick;
    String message;
    ProgressDialog progressDialog;
    String pfbid,pimgurl,pname,pemail,password,dob,phonenumbers,countrryva;
    String dobe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.changedateofbirthprofile,container,false);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Edit date of birth");

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        tripdate = (TextView) v.findViewById(R.id.datepickview);
        changedateofbirth = (Button) v.findViewById(R.id.changedateofbirth);
        canceldateofbirth = (Button) v.findViewById(R.id.canceldateofbirth);
        dobe = Sessiondata.getInstance().getLogindob();
        if(dobe.equals("")){
            canceldateofbirth.setText("Add Date of Birth");
            tripdate.setText("" + Sessiondata.getInstance().getLogindob());
            changedateofbirth.setVisibility(View.GONE);
            tripdate.setFocusable(false);
            tripdate.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            tripdate.setClickable(false);
            canceldateofbirth.setVisibility(View.VISIBLE);
            changedateofbirth.setOnClickListener(this);
            canceldateofbirth.setOnClickListener(this);
            tripdate.setInputType(InputType.TYPE_NULL);
        }
        else {
            canceldateofbirth.setText("Change Date Of Birth");
            tripdate.setText("" + Sessiondata.getInstance().getLogindob());
            changedateofbirth.setVisibility(View.GONE);
            tripdate.setFocusable(false);
            tripdate.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            tripdate.setClickable(false);
            canceldateofbirth.setVisibility(View.VISIBLE);
            changedateofbirth.setOnClickListener(this);
            canceldateofbirth.setOnClickListener(this);
            tripdate.setInputType(InputType.TYPE_NULL);
        }
        return v;
    }
    private void setDateTimeField() {
        tripdate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        toDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tripdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onClick(View v) {
        if (v == tripdate) {
            toDatePickerDialog.show();
        }
        else if(v == changedateofbirth){
            dateoffbirth = tripdate.getText().toString();
            if(dateoffbirth.equals(""))
            {Toast.makeText(getActivity(),"Enter the date of birth",Toast.LENGTH_LONG).show();
                return;
            }
            else {
                pfbid = Sessiondata.getInstance().getLoginfbid();
                pimgurl = "";
                pname = Sessiondata.getInstance().getLoginname();
                pemail = Sessiondata.getInstance().getLoginemail();
                password = Sessiondata.getInstance().getLoginpassword();
                dob = dateoffbirth;
                Sessiondata.getInstance().setLogindob(dob);
                phonenumbers = Sessiondata.getInstance().getLoginphonenumber();
                countrryva = Sessiondata.getInstance().getLogincountry();
                phonenumberchanging();
            }
        }
        else if (v == canceldateofbirth) {
            setDateTimeField();
            changedateofbirth.setVisibility(View.VISIBLE);
            tripdate.setFocusable(true);
            tripdate.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
            tripdate.setClickable(true);
            tripdate.requestFocus();
            canceldateofbirth.setVisibility(View.GONE);
        }

        else if(v == backclick){
            getFragmentManager().popBackStack();
        }
    }
    private void phonenumberchanging() {
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
                            String facebookprofilephonenumber =  phonenumbers;
                            SharedPreferences preferencessfbprofilephonenumber = getActivity().getSharedPreferences("newprofilephomenumber", getActivity().getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphonenumber = preferencessfbprofilephonenumber.edit();
                            editorsphonenumber.putString("facebookphonenumberr", facebookprofilephonenumber);
                            editorsphonenumber.commit();
                            String registerphonenumber = phonenumbers;
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
                            Fragment fr = new Profileview();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);
                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        }}
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
                params.put(FBID,pfbid);
                params.put(IMAGEURL,pimgurl);
                params.put(NAME,pname);
                params.put(EMAIL,pemail);
                params.put(PASSWORD,password);
                params.put(DOB,dob);
                params.put(PHONENUMBER,phonenumbers);
                params.put(COUNTRY,countrryva);
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