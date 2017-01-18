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
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 13/08/16.
 */
public class Changepassword extends Fragment implements View.OnClickListener{
    EditText newpassword,currentpass;
    String newpasswords;
   // private static final String CHANGECOUNTRY = "http://hhwt.tech/hhwt_webservice/updateprofile.php";

    private static final String CHANGECOUNTRY = "http://hhwt.tech/hhwt_webservice/testingupdateprofile.php";


    public static final String FBID = "fb_id";
    public static final String IMAGEURL = "imgurl";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DOB = "dob";
    public static final String PHONENUMBER = "phonenumber";
    public static final String COUNTRY = "country";
    int viewstatus;
    ImageView backclick;
    TextView apptitle;
    String message;
    ProgressDialog progressDialog;
    String pfbid, pimgurl, pname, pemail, password, dob, phonenumber, countrryva;
    Button changepassword,cancelpass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.changepasswordpro,container,false);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Edit password");

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        newpassword = (EditText) v.findViewById(R.id.newpassword);
        changepassword = (Button) v.findViewById(R.id.changepassword);
        cancelpass = (Button) v.findViewById(R.id.cancelpass);
        newpassword.setText(""+Sessiondata.getInstance().getLoginpassword());
        changepassword.setVisibility(View.GONE);
        newpassword.setFocusable(false);
        newpassword.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
        newpassword.setClickable(false);
        cancelpass.setVisibility(View.VISIBLE);
        changepassword.setOnClickListener(this);
        cancelpass.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        if (v == changepassword) {
            newpasswords = newpassword.getText().toString();
            if(newpasswords.equals("")){
                Toast.makeText(getActivity(),"Enter the Change password",Toast.LENGTH_LONG).show();
                return;
            }
            else {
                pfbid = Sessiondata.getInstance().getLoginfbid();
                pimgurl = "";
                pname = Sessiondata.getInstance().getLoginname();
                pemail = Sessiondata.getInstance().getLoginemail();
                password = newpasswords;
                Sessiondata.getInstance().setLoginpassword(password);
                dob = Sessiondata.getInstance().getLogindob();
                phonenumber = Sessiondata.getInstance().getLoginphonenumber();
                countrryva = Sessiondata.getInstance().getLogincountry();
                changepass();
            }

        }
        else if (v == backclick){
            getFragmentManager().popBackStack();
        }

        else if (v == cancelpass) {
            changepassword.setVisibility(View.VISIBLE);
            newpassword.setFocusable(true);
            newpassword.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
            newpassword.setClickable(true);
            newpassword.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            cancelpass.setVisibility(View.GONE);
        }}
    private void changepass() {
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
                            //don't forget this
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
                            imm.hideSoftInputFromWindow(newpassword.getWindowToken(), 0);

                            Fragment fr = new Profileview();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);



                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                        }
                    }
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
    }

}


