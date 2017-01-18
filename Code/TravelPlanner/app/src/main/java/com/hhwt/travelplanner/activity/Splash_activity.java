
package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Retrofit.ApiCall;
import com.hhwt.travelplanner.Retrofit.UserRegistrationResponse;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.BranchEvent;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mathankumar on 11/02/16.
 */
public class Splash_activity extends AppCompatActivity implements View.OnClickListener{
    public static final String PREFS_NAME = "MyApp_Settings";
    private static final String PERMISSION = "publish_actions";
    private Toolbar mToolbar;
    private static final Location SEATTLE_LOCATION = new Location("") {
        {
            setLatitude(47.6097);
            setLongitude(-122.3331);
        }
    };

    RobotoTextView privatepolicys;
    RobotoTextView singup;




    @Override
    public void onStart() {
        super.onStart();
        Branch branch = Branch.getInstance();

        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...

                } else {
                    Log.i("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }










    class Privacy extends ClickableSpan { //clickable span
        public void onClick(View textView) {
            Intent privacypolicy = new Intent(_A, Web_View.class);
            Bundle b = new Bundle();
            privacypolicy.putExtras(b);
            startActivity(privacypolicy);
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(_A, R.color.blackcolorview));//set text color
            ds.setUnderlineText(false); // set to false to remove underline
        }
    }



    TextView Loginregister;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    public String fb_id;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private final String PENDING_ACTION_BUNDLE_KEY =
            "com.example.hellofacebook:PendingAction";
    private ProfilePictureView profilePictureView;
    private TextView greeting;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private ShareDialog shareDialog;
    Hashtable connectFlags;
    LoginButton fb;
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
            new AlertDialog.Builder(Splash_activity.this)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show();}};

    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(Splash_activity.this));
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fb_id = sharedpreferences.getString("fb_ids", "0");
        editor = sharedpreferences.edit();
        if (fb_id.equals("0"))
        {FacebookSdk.sdkInitialize(this.getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            if (savedInstanceState != null) {
                String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
                pendingAction = PendingAction.valueOf(name);
            }
            //setContentView(R.layout.activity_splash_fb);



            setContentView(R.layout.activity_splash_fbnew);

            Branch.getInstance(getApplicationContext()).userCompletedAction(BranchEvent.SHARE_STARTED);



            privatepolicys = (RobotoTextView) findViewById(R.id.privatepolicy);
            Spannable wordtoSpan = new SpannableString("By signing up, you agree to our Terms of Use");
            wordtoSpan.setSpan(new Splash_activity.Privacy(), 32, 44, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            privatepolicys.setText(wordtoSpan);
            privatepolicys.setOnClickListener(this);

            singup = (RobotoTextView) findViewById(R.id.singup);
            singup.setOnClickListener(this);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
             Loginregister = (TextView) findViewById(R.id.loginandregister);
             Loginregister.setOnClickListener(this);
            internet = new InternetAccessCheck();
            _A = Splash_activity.this;
            mess = getResources().getString(R.string.base_url);
            retrofit = new Retrofit.Builder()
                    .baseUrl(mess)
                    .addConverterFactory(GsonConverterFactory.create()).build();

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
                        updateUI();}}
                @Override
                public void onCancel() {
                    if (pendingAction != PendingAction.NONE) {
                        showAlert();
                        pendingAction = PendingAction.NONE;
                    }
                }@Override
                public void onError(FacebookException exception) {
                    if (pendingAction != PendingAction.NONE
                            && exception instanceof FacebookAuthorizationException) {
                        showAlert();
                        pendingAction = PendingAction.NONE;
                    }

                }
                private void showAlert() {
                    new AlertDialog.Builder(Splash_activity.this)
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
          /*  profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
            profilePictureView.setVisibility(View.GONE);*/
            greeting = (TextView) findViewById(R.id.greeting);
            canPresentShareDialog = ShareDialog.canShow(
                    ShareLinkContent.class);
            canPresentShareDialogWithPhotos = ShareDialog.canShow(
                    SharePhotoContent.class);
        }
        else

        {setContentView(R.layout.activity_splash);
            /*Intent n = new Intent(Splash_activity.this, NavigationActivity.class);
            startActivity(n);*/

            Intent n = new Intent(Splash_activity.this, Newnavigationbottom.class);
            startActivity(n);
            finish();
        }

    }
    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
        if (fb_id.equals("0")) {
            updateUI();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            profileTracker.stopTracking();
        } catch (NullPointerException e) {
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


          /*  Toast.makeText(Splash_activity.this,profile.getId(),Toast.LENGTH_LONG).show();

            Toast.makeText(Splash_activity.this,profile.getName(),Toast.LENGTH_LONG).show();
            Toast.makeText(Splash_activity.this,imageURL,Toast.LENGTH_LONG).show();*/

            try {
                UserRegistersapi(greeting, profile.getId(), profile.getName(), "", imageURL);
            } catch (IOException e) {
            }
        } else {
            greeting.setText(null);
        }
    }

    private void handlePendingAction() {
        PendingAction previouslyPendingAction = pendingAction;
        pendingAction = PendingAction.NONE;

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




                    Intent n = new Intent(Splash_activity.this, Newnavigationbottom.class);
                    startActivity(n);


                    finish();
                } else {
                    d.dismiss();
                }}}
    }
    @Override
    public void onClick(View v) {

        if (fb_id.equals("0")) {
           if (v == Loginregister) {
               String profileid = "1";
                Log.d("arjunid", "" + profileid);
                SharedPreferences preferencesss = getSharedPreferences("tempsssss", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editorss = preferencesss.edit();
                editorss.putString("namesssss", profileid);
                editorss.commit();
              /* Intent n = new Intent(Splash_activity.this, LoginRegister.class);
                startActivity(n);*/
               Intent n = new Intent(Splash_activity.this, Newhhwtactlogin.class);
               startActivity(n);


            }
        }
        else if(!fb_id.equals("0")){
            if (v == Loginregister) {
                String profileid = "1";
                Log.d("arjunid", "" + profileid);
                SharedPreferences preferencesss = getSharedPreferences("tempsssss", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editorss = preferencesss.edit();
                editorss.putString("namesssss", profileid);
                editorss.commit();
              /*  Intent n = new Intent(Splash_activity.this, LoginRegister.class);
                startActivity(n);*/


                Intent n = new Intent(Splash_activity.this, Newhhwtactlogin.class);
                startActivity(n);





            }

        }
        else {
            setContentView(R.layout.activity_splash);
            /*Intent n = new Intent(Splash_activity.this, NavigationActivity.class);
            startActivity(n);*/

            Intent n = new Intent(Splash_activity.this, Newnavigationbottom.class);
            startActivity(n);

            finish();
        }

         if(v == privatepolicys) {
            Intent intent = new Intent(Splash_activity.this, Web_View.class);
            startActivity(intent);
        }

        if(v == singup){




         /*   Fragment fr = new RegisterationFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fr);
            fragmentTransaction.commit();*/
            Intent n = new Intent(Splash_activity.this, Signupnew.class);
            startActivity(n);
        }




    }

}


