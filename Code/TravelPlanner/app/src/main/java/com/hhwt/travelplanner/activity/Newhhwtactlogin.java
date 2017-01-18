package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.UserRegistrationResponse;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 08/11/16.
 */
public class Newhhwtactlogin extends Activity implements View.OnClickListener {
        RobotoTextView login, forgotpass;
        RobotoEditText email, password;
        String emailtext = "", passwordtext = "";
        ProgressDialog progressDialog;
        int loginstatus;
public static final String KEY_USERNAME = "email";
public static final String KEY_PASSWORD = "password";
// private static final String LOGIN = "http://www.hhwt.tech/hhwt_webservice/loginnew.php";
private ProfileTracker profileTracker;
private static final String LOGIN = "http://www.hhwt.tech/hhwt_webservice/testingloginnew.php";
    private CallbackManager callbackManager;
ImageView backlogin;
    private Newhhwtactlogin.PendingAction pendingAction = Newhhwtactlogin.PendingAction.NONE;
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
    LoginButton fb;
    private final String PENDING_ACTION_BUNDLE_KEY =
            "com.example.hellofacebook:PendingAction";
    private TextView greeting;
    InternetAccessCheck internet;
    String mess;
    Retrofit retrofit;

    RobotoTextView signupregister;
    RobotoTextView passwordworntext,emailworntext;

    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "Canceled");
        }
        @Override
        public void onError(FacebookException error) {
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
            String title = getString(R.string.error);
            String alertMessage = error.getMessage();
            showResult(title, alertMessage);
        }
        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("HelloFacebook", "Success!");
            if (result.getPostId() != null) {
                String title = getString(R.string.success);
                String id = result.getPostId();
                Log.d("Arjunview",""+id);
                String alertMessage = getString(R.string.successfully_posted_post, id);
                showResult(title, alertMessage);
            }
        }
        private void showResult(String title, String alertMessage) {
            new AlertDialog.Builder(Newhhwtactlogin.this)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show();}};




    private enum PendingAction {
        NONE,
        POST_PHOTO,
        PendingAction, POST_STATUS_UPDATE
    }
    private ShareDialog shareDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newloginhhwt);

        _A = Newhhwtactlogin.this;
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");



        internet = new InternetAccessCheck();
        _A = Newhhwtactlogin.this;
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();



        signupregister = (RobotoTextView) findViewById(R.id.signupregister);


        login = (RobotoTextView) findViewById(R.id.loginbuttonbut);
        email = (RobotoEditText) findViewById(R.id.input_email);
        password = (RobotoEditText) findViewById(R.id.input_password);

        backlogin = (ImageView) findViewById(R.id.backlogin);


        passwordworntext = (RobotoTextView) findViewById(R.id.passwordworntext);
        passwordworntext.setVisibility(View.GONE);
        emailworntext = (RobotoTextView) findViewById(R.id.emailworntext);

        emailworntext.setVisibility(View.GONE);





        signupregister.findViewById(R.id.signupregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent n = new Intent(Newhhwtactlogin.this, Signupnew.class);
                startActivity(n);
            }
        });


        backlogin.findViewById(R.id.backlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              finish();
            }
        });




       forgotpass = (RobotoTextView) findViewById(R.id.forgotpassword);
        login.setOnClickListener(this);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent in = new Intent(_A,NewForgot.class);
                startActivity(in);
            }
        });


        {
            FacebookSdk.sdkInitialize(this.getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            if (savedInstanceState != null) {
                String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
                pendingAction = Newhhwtactlogin.PendingAction.valueOf(name);
            }








            fb = (LoginButton) findViewById(R.id.loginbutton);
            fb.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email", "user_birthday", "public_profile", "user_photos"));
            fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    Log.v("LoginActivity", response.toString());
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender,birthday");
                    request.setParameters(parameters);
                    request.executeAsync();

                    if (Profile.getCurrentProfile() == null) {
                        profileTracker = new ProfileTracker() {
                            @Override
                            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                updateUI();
                                handlePendingAction();
                            }
                        };
                    } else {
                        handlePendingAction();
                        updateUI();
                    }
                }

                @Override
                public void onCancel() {
                    if (pendingAction != Newhhwtactlogin.PendingAction.NONE) {
                        showAlert();
                        pendingAction = Newhhwtactlogin.PendingAction.NONE;
                    }
                }

                @Override
                public void onError(FacebookException exception) {
                    if (pendingAction != Newhhwtactlogin.PendingAction.NONE
                            && exception instanceof FacebookAuthorizationException) {
                        showAlert();
                        pendingAction = Newhhwtactlogin.PendingAction.NONE;
                    }

                }

                private void showAlert() {
                    new AlertDialog.Builder(Newhhwtactlogin.this)
                            .setTitle(R.string.cancelled)
                            .setMessage(R.string.permission_not_granted)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                }
            });


            shareDialog = new ShareDialog(this);
            shareDialog.registerCallback(
                    callbackManager,
                    shareCallback);

            greeting = (TextView) findViewById(R.id.greeting);
            canPresentShareDialog = ShareDialog.canShow(
                    ShareLinkContent.class);
            canPresentShareDialogWithPhotos = ShareDialog.canShow(
                    SharePhotoContent.class);

        }

    }






    private void updateUI() {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null) {
            String imageURL = "https://graph.facebook.com/" + profile.getId() + "/picture?type=large";
            String profiledata =  profile.getName()+"-"+profile.getProfilePictureUri(60,60);
            SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", profiledata);
            editor.commit();
            String profiledataname =  profile.getFirstName()+"-"+profile.getLastName();
            SharedPreferences preferencess = getSharedPreferences("temps", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editors = preferencess.edit();
            editors.putString("names", profiledataname);
            editors.commit();
            String profileid = profile.getId();
            SharedPreferences preferencesss = getSharedPreferences("tempss", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editorss = preferencesss.edit();
            editorss.putString("namess", profileid);
            editorss.commit();
            greeting.setText(getString(R.string.hello_user, profile.getFirstName()));
            try {
                UserRegistersapi(greeting, profile.getId(), profile.getName(), "", imageURL);
            } catch (IOException e) {
            }
        } else {
            greeting.setText(null);
        }
    }









    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            profileTracker.stopTracking();
        } catch (NullPointerException e) {
        }
    }




    private void handlePendingAction() {
        Newhhwtactlogin.PendingAction previouslyPendingAction = pendingAction;
        pendingAction = Newhhwtactlogin.PendingAction.NONE;

        switch (previouslyPendingAction) {
            case NONE:
                break;
        }
    }







    public void UserRegistersapi(final View vs, String fbid, String name, String email, String imgurl) throws IOException {
        List<Categorylistmodel> items = new ArrayList<>();

        Boolean success = internet.isNetworkConnected(_A);
        if (success) {
            final ProgressDialog d = new ProgressDialog(_A);
            d.setMessage("Please wait...");
            d.show();
            ApiCall a = retrofit.create(ApiCall.class);
            //   Call<UserRegistrationResponse> call = a.UserRegistersapi(fbid, name, email, imgurl);


            Call<UserRegistrationResponse> call = a.UserRegistersapisss(fbid, name, email, imgurl);

            UserRegistrationResponse c = call.execute().body();
            String anullcheck = c != null ? "Yes"
                    : "no";
            if (anullcheck.equals("Yes")) {
                if (c.status == 1) {
                    String ffb_id = fbid;
                    String profilepic = imgurl;
                    editor.putString("name", name);

                    String newprofilename =  c.name;

                    SharedPreferences preferencessfbname = getSharedPreferences("newprofilename", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorsname = preferencessfbname.edit();
                    editorsname.putString("facebookname", newprofilename);
                    editorsname.commit();
                    editor.putString("image", profilepic);
                    String facebookprofilepic =  profilepic;

                    SharedPreferences preferencessfbprofilepicture = getSharedPreferences("newprofilepicture", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorspicture = preferencessfbprofilepicture.edit();
                    editorspicture.putString("facebookname", facebookprofilepic);
                    editorspicture.commit();
                    editor.commit();
                    editor.putString("fb_id", ffb_id);
                    editor.putString("fb_ids", "1");
                    String facebookid =  fbid;
                    SharedPreferences preferencessfbprofileid = getSharedPreferences("newprofilefbid", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorsfbid = preferencessfbprofileid.edit();
                    editorsfbid.putString("facebookid", facebookid);
                    editorsfbid.commit();
                    editor.commit();
                    editor.putString("email", email);
                    String facebookprofileemail =  email;
                    SharedPreferences preferencessfbprofileemail = getSharedPreferences("newprofileemail", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                    editorsemail.putString("facebookemail", facebookprofileemail);
                    editorsemail.commit();
                    editor.commit();
                    String facebooway =  "2";
                    SharedPreferences preferencessfbway = getSharedPreferences("newfb", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorsfbway = preferencessfbway.edit();
                    editorsfbway.putString("facebookway", facebooway);
                    editorsfbway.commit();
                    String facebookprofiledob =  c.dob.toString();
                    SharedPreferences preferencessfbprofiledob = getSharedPreferences("newprofiledob", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorsdob = preferencessfbprofiledob.edit();
                    editorsdob.putString("facebookdob", facebookprofiledob);
                    editorsdob.commit();
                    String facebookprofilephonenumber =  c.phonenumber;
                    SharedPreferences preferencessfbprofilephonenumber = getSharedPreferences("newprofilephomenumber", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorsphonenumber = preferencessfbprofilephonenumber.edit();
                    editorsphonenumber.putString("facebookphonenumberr", facebookprofilephonenumber);
                    editorsphonenumber.commit();
                    editor.commit();
                    String facebookprofilecountry =  c.country;
                    SharedPreferences preferencessfbprofilecountry = getSharedPreferences("newprofilecountry", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editorscountry = preferencessfbprofilecountry.edit();
                    editorscountry.putString("facebookcountry", facebookprofilecountry);
                    editorscountry.commit();
                    Sessiondata.getInstance().setLoginemail("");
                    Sessiondata.getInstance().setLoginpassword("");
                    editor.commit();
                    d.dismiss();

                   /* IntroActivity   view skipe

                    Intent n = new Intent(Splash_activity.this, IntroActivity.class);
                    startActivity(n);*/




                    Intent n = new Intent(Newhhwtactlogin.this, Newnavigationbottom.class);
                    startActivity(n);


                    finish();
                } else {
                    d.dismiss();
                }}}
    }












    @Override
    public void onClick(View v) {
        emailtext = email.getText().toString();
        passwordtext = password.getText().toString();


        InputMethodManager imm = (InputMethodManager) _A.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(email.getWindowToken(), 0);



        InputMethodManager passwordS = (InputMethodManager) _A.getSystemService(Context.INPUT_METHOD_SERVICE);
        passwordS.hideSoftInputFromWindow(password.getWindowToken(), 0);




        login.setTextColor(Color.WHITE);
        login.setBackgroundResource(R.drawable.newlogincurveblue);








        if (null == emailtext || emailtext.equals("")) {
            AlertUtils.SnackbarerrorAlert(_A, v, "Enter Email id");


            passwordworntext.setVisibility(View.GONE);
            emailworntext.setVisibility(View.VISIBLE);
            emailworntext.setText("Enter email addres");


            return;
        }
        if (null == passwordtext || passwordtext.equals("")) {
           // AlertUtils.SnackbarerrorAlert(_A, v, "Enter Password");

            passwordworntext.setVisibility(View.VISIBLE);
            passwordworntext.setText("Enter Password");
            emailworntext.setVisibility(View.GONE);
            return;
        }
        if (!isEmailValid(emailtext)) {
            //AlertUtils.SnackbarerrorAlert(_A, v, "Enter valid email");

            passwordworntext.setVisibility(View.GONE);
            emailworntext.setVisibility(View.VISIBLE);
            emailworntext.setText("Invalid email addres");
            return;
        }





        else {
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
                            SharedPreferences preferencessssr = _A.getSharedPreferences("tempss", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorssr = preferencessssr.edit();
                            editorssr.putString("namess", profileidr);
                            editorssr.commit();
                            SharedPreferences preferencesss = _A.getSharedPreferences("tempsssssss", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorss = preferencesss.edit();
                            editorss.putString("namesssssemail", profileid);
                            editorss.commit();
                            SharedPreferences pre = _A.getSharedPreferences("temp", _A.getApplicationContext().MODE_PRIVATE);
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
                            SharedPreferences preferencessfbway = _A.getSharedPreferences("newfb", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsfbway = preferencessfbway.edit();
                            editorsfbway.putString("facebookway", facebooway);
                            editorsfbway.commit();
                            String registerprofilename =  pname;
                            Log.d("registerprofilename",""+registerprofilename);
                            SharedPreferences preferencessfbname = _A.getSharedPreferences("newprofilename", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsname = preferencessfbname.edit();
                            editorsname.putString("registername", registerprofilename);
                            editorsname.commit();
                            String facebookprofileemail =  emailvalue;
                            SharedPreferences preferencessfbprofileemail = _A.getSharedPreferences("newprofileemail", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsemail = preferencessfbprofileemail.edit();
                            editorsemail.putString("registeremail", facebookprofileemail);
                            editorsemail.commit();
                            String registerpassword = passwordtext;
                            SharedPreferences preferencessfbprofileid = _A.getSharedPreferences("newprofilefpassword", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorspassword = preferencessfbprofileid.edit();
                            editorspassword.putString("registerpassword", registerpassword);
                            editorspassword.commit();
                            String registerdob = dateofbirth;
                            SharedPreferences preferencessdob = _A.getSharedPreferences("newprofilefdob", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsdob = preferencessdob.edit();
                            editorsdob.putString("registerdob", registerdob);
                            editorsdob.commit();
                            String registerphonenumber = phnumber;
                            Log.d("registerphonenumber",""+registerphonenumber);
                            SharedPreferences preferencessphno = _A.getSharedPreferences("newprofilefphonenumber", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorsphno = preferencessphno.edit();
                            editorsphno.putString("registerphonenumber", registerphonenumber);
                            editorsphno.commit();
                            String registercountry = countryval;
                            Log.d("registercountry",""+registercountry);
                            SharedPreferences preferencesscountry = _A.getSharedPreferences("newprofilefcountry", _A.getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editorscountry = preferencesscountry.edit();
                            editorscountry.putString("registercountry", registercountry);
                            editorscountry.commit();

                            AlertUtils.SnackbarsuccessAlert(_A, v, "Successfully logged in");
                            Runnable task = new Runnable() {
                                public void run() {
                                    Intent n = new Intent(_A, Newnavigationbottom.class);
                                    n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(n);
                                    _A.finish();
                                }
                            };
                            worker.schedule(task, 1, TimeUnit.SECONDS);
                        } else if (loginstatus == 0) {
                            progressDialog.dismiss();
                            AlertUtils.SnackbarerrorAlert(_A, v, "Check Your Login");
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
        RequestQueue requestQueue = Volley.newRequestQueue(_A);
        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(_A);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }}





