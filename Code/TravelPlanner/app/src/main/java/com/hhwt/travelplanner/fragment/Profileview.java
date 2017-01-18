package com.hhwt.travelplanner.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.activity.Changecountryprofile;
import com.hhwt.travelplanner.activity.Changedateofbirth;
import com.hhwt.travelplanner.activity.Changepassword;
import com.hhwt.travelplanner.activity.Changephoneprofile;
import com.hhwt.travelplanner.activity.Editprofilename;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Profiledetails;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.activity.Splash_activity;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by jeyavijay on 19/03/16.
 */
public class Profileview extends Fragment implements View.OnClickListener{
    RobotoTextView pronames,reproname;
    ImageView profileimage;
    LinearLayout viewA;
    String Name, FEmail;
    SharedPreferences sharedpreferences;
    TextView editname,reeditname;
    String MyPREFERENCES = "HHWT";
    Button logoutbure,logoutbufb;
    LinearLayout passwordclick,dateofbirthclick,emailclick,phoneclick,countryclick;
    SharedPreferences.Editor editor;
    TextView pemail,ppassword,pdateofbirth,pphone,pcountry;
    String proviewpic;
TextView apptitle;
    public String fb_id;
    ImageView backclick;
    SquareImageView  regprofileimage;
    Hashtable connectFlags;
    ArrayList<Profiledetails> ownersigninleadgerdetails;
    public Profileview() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.provileview, container, false);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Profile");
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));



        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);



        viewA = (LinearLayout) v.findViewById(R.id.viewA);
        profileimage = (ImageView) v.findViewById(R.id.profile_image);
        pronames = (RobotoTextView) v.findViewById(R.id.proname);
        logoutbufb = (Button) v.findViewById(R.id.logoutbufb);
        pemail = (TextView) v.findViewById(R.id.emailadd);


        regprofileimage = (SquareImageView) v.findViewById(R.id.guimagelist);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        ppassword = (TextView) v.findViewById(R.id.password);
        pdateofbirth = (TextView) v.findViewById(R.id.dateofbirth);
        pphone = (TextView) v.findViewById(R.id.phone);
        pcountry = (TextView) v.findViewById(R.id.country);
        SharedPreferences preferencprofileway = getActivity().getSharedPreferences("newfb", getActivity().MODE_PRIVATE);
         proviewpic = preferencprofileway.getString("facebookway", null);
        editname = (TextView) v.findViewById(R.id.editname);
        passwordclick = (LinearLayout) v.findViewById(R.id.pa);
        dateofbirthclick = (LinearLayout) v.findViewById(R.id.da);
        emailclick = (LinearLayout) v.findViewById(R.id.ea);
        phoneclick = (LinearLayout) v.findViewById(R.id.ph);
        countryclick = (LinearLayout) v.findViewById(R.id.co);
        phoneclick.setOnClickListener(this);
        passwordclick.setOnClickListener(this);
        editname.setOnClickListener(this);
        dateofbirthclick.setOnClickListener(this);
        countryclick.setOnClickListener(this);
        if (null != Sessiondata.getInstance().getFbimage()) {
            viewA.setVisibility(View.VISIBLE);
            emailclick.setVisibility(View.GONE);
            passwordclick.setVisibility(View.GONE);
            regprofileimage.setVisibility(View.GONE);
            profileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
            pronames.setText(Sessiondata.getInstance().getLoginname());
            pdateofbirth.setText(""+Sessiondata.getInstance().getLogindob());
            pphone.setText("" + Sessiondata.getInstance().getLoginphonenumber());
            pcountry.setText(""+Sessiondata.getInstance().getLogincountry());
            logoutbufb.setOnClickListener(this);
        } else {
            emailclick.setVisibility(View.VISIBLE);
            passwordclick.setVisibility(View.VISIBLE);
            regprofileimage.setVisibility(View.VISIBLE);
            profileimage.setVisibility(View.GONE);
            logoutbufb.setOnClickListener(this);
            pronames.setText("" + Sessiondata.getInstance().getLoginname());
            pemail.setText(""+ Sessiondata.getInstance().getLoginemail());
            ppassword.setText(""+Sessiondata.getInstance().getLoginpassword());
            pdateofbirth.setText(""+Sessiondata.getInstance().getLogindob());
            pphone.setText(""+Sessiondata.getInstance().getLoginphonenumber());
            pcountry.setText(""+Sessiondata.getInstance().getLogincountry());
            viewA.setVisibility(View.VISIBLE);
        }
        return v;}
    public void onConnectSuccess() {

    }
    public void onConnectFailure() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {

        super.onStop();
    }
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            Log.d("Profiledetails", "" + profile);
            // Facebook Email address
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Log.v("LoginActivity Response ", response.toString());

                            try {
                                Name = object.getString("name");
                                FEmail = object.getString("email");
                                Log.v("Email = ", " " + FEmail);
                                Toast.makeText(getActivity(), "Name " + Name, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }
        @Override
        public void onCancel() {
            LoginManager.getInstance().logOut();

        }
        @Override
        public void onError(FacebookException e) {
        }};
    @Override
    public void onClick(View v) {

        if(v == editname) {

            Fragment fr = new Editprofilename();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }
        else if (v == passwordclick) {
            Fragment fr = new Changepassword();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);

        }
        else if (v == dateofbirthclick){

            Fragment fr = new Changedateofbirth();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }
        else if (v == phoneclick){

            Fragment fr = new Changephoneprofile();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }
        else if (v == countryclick) {
            Fragment fr = new Changecountryprofile();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);
        }
        else if (v == logoutbure){
            Intent in = new Intent(getActivity(),Splash_activity.class);
            startActivity(in);
            getActivity().finish();
        }

        else if (v == backclick){
            getFragmentManager().popBackStack();
        }
        else if (v == logoutbufb){

           if(proviewpic.equals("2")) {
               SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = prefs.edit();
               editor.putString("fb_ids", "0");
               editor.commit();
               LoginManager.getInstance().logOut();
               Intent in = new Intent(getActivity(),Splash_activity.class);
               startActivity(in);
               getActivity().finish();
           }
            else {
               SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = prefs.edit();
               editor.putString("fb_ids", "0");
               editor.commit();
               Intent in = new Intent(getActivity(), Splash_activity.class);
               startActivity(in);
               getActivity().finish();
           }}}}
