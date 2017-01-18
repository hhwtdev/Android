package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.fragment.Profileview;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

;

/**
 * Created by jeyavijay on 19/03/16.
 */
public class HomeSecondFragment extends Fragment {
    String MyPREFERENCES = "HHWT";
    Hashtable connectFlags;
    ProgressDialog progressDialog;
    int viewstatus;
    String message;
    private static final String ANALATIC = "http://hhwt.tech/hhwt_webservice/pages.php";
    String pname, purl, pfirsrname, plastname;
    String feedbackmsg;
    private static final String PROFILEVIEW = "http://www.hhwt.tech/hhwt_webservice/getprofiledata.php?";
    ArrayList<Profiledetails> ownersigninleadgerdetails;
    public static final String KEY_USERNAME = "profileid";
    String userfbidemail,pageno;
    public HomeSecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //   View v = inflater.inflate(R.layout.secondmenuvallue, container, false);
        // Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));


        View v = inflater.inflate(R.layout.fragment_home_second, container, false);







        SharedPreferences preferencprofileway = getActivity().getSharedPreferences("newfb", getActivity().MODE_PRIVATE);
        String proviewpic = preferencprofileway.getString("facebookway", null);
        Log.d("profilename", "" + proviewpic);

        if(proviewpic.equals("2")){

            SharedPreferences preferencessfbid = getActivity().getSharedPreferences("newprofilefbid", getActivity().MODE_PRIVATE);
            String profilefbid = preferencessfbid.getString("facebookid", null);
            Log.d("profilename", "" + profilefbid);
            userfbidemail = profilefbid;
            Sessiondata.getInstance().setLoginfbid(profilefbid);
            //Toast.makeText(getActivity(), profilefbid, Toast.LENGTH_SHORT).show();


        }
        else if (proviewpic.equals("3")){

            SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
            String profileemail = preferencessemail.getString("registeremail", null);
            Log.d("profileemail", "" + profileemail);
            userfbidemail = profileemail;

            Sessiondata.getInstance().setLoginfbid(profileemail);
            //Toast.makeText(getActivity(), profileemail, Toast.LENGTH_SHORT).show();
        }

        else{

            SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
            String profileemail = preferencessemail.getString("registeremail", null);
            Log.d("profileemail", "" + profileemail);
            userfbidemail = profileemail;
            Sessiondata.getInstance().setLoginfbid(profileemail);

            //Toast.makeText(getActivity(),profileemail,Toast.LENGTH_SHORT).show();
        }
        pageno = "D";
        analytics();

        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_ids", "1");
        editor.commit();

        v.findViewById(R.id.addnewplace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showExploreFragment();
                pageno = "P5";
                analytics();

                Fragment fr = new Addnewpalcefirst();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);



               /* Fragment fr = new Addnewpalce();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);*/


            }
        });
        v.findViewById(R.id.addfeedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showExploreFragment();
                pageno = "P6";
                analytics();
                Fragment fr = new Feedbackview();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
        });


        v.findViewById(R.id.reportbug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showExploreFragment();
                pageno = "P7";
                analytics();
                Fragment fr = new Bugreport();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(fr);
            }
        });


        v.findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showExploreFragment();
                pageno = "P8";
                analytics();
                ownersigninleadgerdetails = new ArrayList<Profiledetails>();
                /*SharedPreferences settings = getActivity().getSharedPreferences("YOUR_PREF_NAME", 0);
                int snowDensity = settings.getInt("SNOW_DENSITY", 0);
*/
                SharedPreferences preferences = getActivity().getSharedPreferences("temp", getActivity().MODE_PRIVATE);
                String name = preferences.getString("name", "");
                Log.d("facebookprofile id", "" + name);

                String CurrentString = name;
                String[] separated = CurrentString.split("_");
                pname = separated[0];

                Log.d("pname", "" + pname);
                purl = separated[1];

                Log.d("purl", "" + purl);

              /*  pfirsrname = separated[2];
                plastname =   separated[3];
*/
                SharedPreferences preferencprofileway = getActivity().getSharedPreferences("newfb", getActivity().MODE_PRIVATE);
                String proviewpic = preferencprofileway.getString("facebookway", null);
                Log.d("profilename", "" + proviewpic);


if(proviewpic.equals("2")){




//facebookname
    SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
    String fprofilename = preferencessname.getString("facebookname", null);
    Log.d("profilename", "" + fprofilename);


    Sessiondata.getInstance().setLoginname(fprofilename);


//facebookpicture
    SharedPreferences preferencesspic = getActivity().getSharedPreferences("newprofilepicture", getActivity().MODE_PRIVATE);
    String profilepicture = preferencesspic.getString("facebookname", null);
    Log.d("profilename", "" + profilepicture);


//facebook  fbid

    SharedPreferences preferencessfbid = getActivity().getSharedPreferences("newprofilefbid", getActivity().MODE_PRIVATE);
    String profilefbid = preferencessfbid.getString("facebookid", null);
    Log.d("profilename", "" + profilefbid);

    Sessiondata.getInstance().setLoginfbid(profilefbid);

//facebookdob
    SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofiledob", getActivity().MODE_PRIVATE);
    String profiledob = preferencessdob.getString("facebookdob", null);
    Log.d("profiledob", "" + profiledob);

    Sessiondata.getInstance().setLogindob(profiledob);
    //facebookemail
    SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
    String profileemail = preferencessemail.getString("facebookemail", null);
    Log.d("profileemail", "" + profileemail);
//facebookphonenumber

    SharedPreferences preferenphonenumber = getActivity().getSharedPreferences("newprofilephomenumber", getActivity().MODE_PRIVATE);
    String profilephonenumber = preferenphonenumber.getString("facebookphonenumberr", null);
    Log.d("profilephonenumber", "" + profilephonenumber);
    Sessiondata.getInstance().setLoginphonenumber(profilephonenumber);

    //facebookcountry
    SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilecountry", getActivity().MODE_PRIVATE);
    String profilenamecountry = preferencesscountry.getString("facebookcountry", null);
    Log.d("facebookcountry", "" + profilenamecountry);

    Sessiondata.getInstance().setLogincountry(profilenamecountry);
    Sessiondata.getInstance().setLoginemail("");
    Sessiondata.getInstance().setLoginpassword("");
    String URL = profilepicture;
    GetXMLTask task = new GetXMLTask();
    // Execute the task
    task.execute(new String[]{URL});






}
else if (proviewpic.equals("3")){
    Sessiondata.getInstance().setFbimage(null);

    //  Sessiondata.getInstance().setFbprofilename(pname);
    SharedPreferences preferencess = getActivity().getSharedPreferences("tempsssssss", getActivity().MODE_PRIVATE);
    plastname = preferencess.getString("namesssssemail", null);
    pfirsrname = pname;

    Sessiondata.getInstance().setFirstname(pfirsrname);
    Sessiondata.getInstance().setLastname(plastname);

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//Registername
    SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
    String fprofilename = preferencessname.getString("registername", null);
    Log.d("profilename", "" + fprofilename);


    Sessiondata.getInstance().setLoginname(fprofilename);

//Registeremail

    SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
    String profileemail = preferencessemail.getString("registeremail", null);
    Log.d("profileemail", "" + profileemail);

    Sessiondata.getInstance().setLoginemail(profileemail);

    Sessiondata.getInstance().setLoginfbid(profileemail);


//Registerpassword

    SharedPreferences preferencesspassword = getActivity().getSharedPreferences("newprofilefpassword", getActivity().MODE_PRIVATE);
    String profilepassword = preferencesspassword.getString("registerpassword", null);
    Log.d("profilepassword", "" + profilepassword);



    Sessiondata.getInstance().setLoginpassword(profilepassword);


///Registerdob

    SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofilefdob", getActivity().MODE_PRIVATE);
    String profiledob = preferencessdob.getString("registerdob", null);
    Log.d("profiledob", "" + profiledob);

    Sessiondata.getInstance().setLogindob(profiledob);

//Registerphonenumber


    SharedPreferences preferencessphonenumber = getActivity().getSharedPreferences("newprofilefphonenumber", getActivity().MODE_PRIVATE);
    String profilephone = preferencessphonenumber.getString("registerphonenumber", null);
    Log.d("profilephone", "" + profilephone);


    Sessiondata.getInstance().setLoginphonenumber(profilephone);
//Registercountry

    SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilefcountry", getActivity().MODE_PRIVATE);
    String profilecountry = preferencesscountry.getString("registercountry", null);
    Log.d("profilecountry", "" + profilecountry);

    Sessiondata.getInstance().setLogincountry(profilecountry);


    Fragment fr = new Profileview();
    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
    fc.replaceFragment(fr);
}
else{
    Sessiondata.getInstance().setFbimage(null);
    //  Sessiondata.getInstance().setFbprofilename(pname);
    SharedPreferences preferencess = getActivity().getSharedPreferences("tempsssssss", getActivity().MODE_PRIVATE);
    plastname = preferencess.getString("namesssssemail", null);
    pfirsrname = pname;

    Sessiondata.getInstance().setFirstname(pfirsrname);
    Sessiondata.getInstance().setLastname(plastname);

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//Registername
    SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
    String fprofilename = preferencessname.getString("registername", null);
    Log.d("profilename", "" + fprofilename);


    Sessiondata.getInstance().setLoginname(fprofilename);

//Registeremail

    SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
    String profileemail = preferencessemail.getString("registeremail", null);
    Log.d("profileemail", "" + profileemail);

    Sessiondata.getInstance().setLoginemail(profileemail);

    Sessiondata.getInstance().setLoginfbid(profileemail);


//Registerpassword

    SharedPreferences preferencesspassword = getActivity().getSharedPreferences("newprofilefpassword", getActivity().MODE_PRIVATE);
    String profilepassword = preferencesspassword.getString("registerpassword", null);
    Log.d("profilepassword", "" + profilepassword);



    Sessiondata.getInstance().setLoginpassword(profilepassword);


///Registerdob

    SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofilefdob", getActivity().MODE_PRIVATE);
    String profiledob = preferencessdob.getString("registerdob", null);
    Log.d("profiledob", "" + profiledob);

    Sessiondata.getInstance().setLogindob(profiledob);

//Registerphonenumber


    SharedPreferences preferencessphonenumber = getActivity().getSharedPreferences("newprofilefphonenumber", getActivity().MODE_PRIVATE);
    String profilephone = preferencessphonenumber.getString("registerphonenumber", null);
    Log.d("profilephone", "" + profilephone);


    Sessiondata.getInstance().setLoginphonenumber(profilephone);
//Registercountry

    SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilefcountry", getActivity().MODE_PRIVATE);
    String profilecountry = preferencesscountry.getString("registercountry", null);
    Log.d("profilecountry", "" + profilecountry);

    Sessiondata.getInstance().setLogincountry(profilecountry);


    Fragment fr = new Profileview();
    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
    fc.replaceFragment(fr);
}






/*
                // new ImageLoadTask();
                if (!purl.equals("@@@@")) {
                   // String URL = purl;


                    SharedPreferences preferencess = getActivity().getSharedPreferences("temps", getActivity().MODE_PRIVATE);
                    String names = preferencess.getString("names", null);

                    Log.d("profilename", "" + names);


                    String CurrentStrin = names;
                    String[] separate = CurrentStrin.split("_");

                    pfirsrname = separate[0];
                    Log.d("Fename", "" + pfirsrname);
                    plastname = separate[1];
                    Log.d("lastname", "" + plastname);


                    Sessiondata.getInstance().setFirstname(pfirsrname);

                    Log.d("F1name", "" + Sessiondata.getInstance().getFirstname());
                    Sessiondata.getInstance().setLastname(plastname);

                    Log.d("l2name", "" + Sessiondata.getInstance().getLastname());


// Create an object for subclass of AsyncTask


     //----------------------------------------------------------------------------------------------------------------------------------------------------------


//facebookname
                    SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
                    String fprofilename = preferencessname.getString("facebookname", null);
                    Log.d("profilename", "" + fprofilename);


Sessiondata.getInstance().setLoginname(fprofilename);


//facebookpicture
                    SharedPreferences preferencesspic = getActivity().getSharedPreferences("newprofilepicture", getActivity().MODE_PRIVATE);
                    String profilepicture = preferencesspic.getString("facebookname", null);
                    Log.d("profilename", "" + profilepicture);


//facebook  fbid

                    SharedPreferences preferencessfbid = getActivity().getSharedPreferences("newprofilefbid", getActivity().MODE_PRIVATE);
                    String profilefbid = preferencessfbid.getString("facebookid", null);
                    Log.d("profilename", "" + profilefbid);

                    Sessiondata.getInstance().setLoginfbid(profilefbid);

//facebookdob
                    SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofiledob", getActivity().MODE_PRIVATE);
                    String profiledob = preferencessdob.getString("facebookdob", null);
                    Log.d("profiledob", "" + profiledob);


                    Sessiondata.getInstance().setLogindob(profiledob);



                    //facebookemail
                    SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
                    String profileemail = preferencessemail.getString("facebookemail", null);
                    Log.d("profileemail", "" + profileemail);



//facebookphonenumber

                    SharedPreferences preferenphonenumber = getActivity().getSharedPreferences("newprofilephomenumber", getActivity().MODE_PRIVATE);
                    String profilephonenumber = preferenphonenumber.getString("facebookphonenumberr", null);
                    Log.d("profilephonenumber", "" + profilephonenumber);


                    Sessiondata.getInstance().setLoginphonenumber(profilephonenumber);

                    //facebookcountry

                    SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilecountry", getActivity().MODE_PRIVATE);
                    String profilenamecountry = preferencesscountry.getString("facebookcountry", null);
                    Log.d("facebookcountry", "" + profilenamecountry);



                    Sessiondata.getInstance().setLogincountry(profilenamecountry);

                    Sessiondata.getInstance().setLoginemail("");
                     Sessiondata.getInstance().setLoginpassword("");




                    String URL = profilepicture;

                    GetXMLTask task = new GetXMLTask();
                    // Execute the task
                    task.execute(new String[]{URL});






                } else {
                    //  Sessiondata.getInstance().setFbprofilename(pname);
                    SharedPreferences preferencess = getActivity().getSharedPreferences("tempsssssss", getActivity().MODE_PRIVATE);
                    plastname = preferencess.getString("namesssssemail", null);
                    pfirsrname = pname;

            Sessiondata.getInstance().setFirstname(pfirsrname);
            Sessiondata.getInstance().setLastname(plastname);

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//Registername
                    SharedPreferences preferencessname = getActivity().getSharedPreferences("newprofilename", getActivity().MODE_PRIVATE);
                    String fprofilename = preferencessname.getString("registername", null);
                    Log.d("profilename", "" + fprofilename);


                    Sessiondata.getInstance().setLoginname(fprofilename);

//Registeremail

                    SharedPreferences preferencessemail = getActivity().getSharedPreferences("newprofileemail", getActivity().MODE_PRIVATE);
                    String profileemail = preferencessemail.getString("registeremail", null);
                    Log.d("profileemail", "" + profileemail);

                    Sessiondata.getInstance().setLoginemail(profileemail);

                    Sessiondata.getInstance().setLoginfbid(profileemail);


//Registerpassword

                    SharedPreferences preferencesspassword = getActivity().getSharedPreferences("newprofilefpassword", getActivity().MODE_PRIVATE);
                    String profilepassword = preferencesspassword.getString("registerpassword", null);
                    Log.d("profilepassword", "" + profilepassword);



              Sessiondata.getInstance().setLoginpassword(profilepassword);


///Registerdob

                    SharedPreferences preferencessdob = getActivity().getSharedPreferences("newprofilefdob", getActivity().MODE_PRIVATE);
                    String profiledob = preferencessdob.getString("registerdob", null);
                    Log.d("profiledob", "" + profiledob);

                    Sessiondata.getInstance().setLogindob(profiledob);

//Registerphonenumber


                    SharedPreferences preferencessphonenumber = getActivity().getSharedPreferences("newprofilefphonenumber", getActivity().MODE_PRIVATE);
                    String profilephone = preferencessphonenumber.getString("registerphonenumber", null);
                    Log.d("profilephone", "" + profilephone);


                    Sessiondata.getInstance().setLoginphonenumber(profilephone);
//Registercountry

                    SharedPreferences preferencesscountry = getActivity().getSharedPreferences("newprofilefcountry", getActivity().MODE_PRIVATE);
                    String profilecountry = preferencesscountry.getString("registercountry", null);
                    Log.d("profilecountry", "" + profilecountry);

                    Sessiondata.getInstance().setLogincountry(profilecountry);


                    Fragment fr = new Profileview();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFragment(fr);
                }*/

            }
        });


        // Inflate the layout for this fragment
        return v;
    }


    // called when Tapjoy connect call succeed

    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    // called when Tapjoy connect call failed

    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }

    //session start
    @Override
    public void onStart() {
        super.onStart();
     //   Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
     //   Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }


    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {


        private ProgressDialog pdia;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(getActivity());
            pdia.setMessage("Loading...");
            pdia.show();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            //  imageView.setImageBitmap(result);
            pdia.dismiss();

            Log.d("Arjunimage", "" + result);
            Sessiondata.getInstance().setFbimage(result);
            Sessiondata.getInstance().setFbprofilename(pname);
           /* Sessiondata.getInstance().setFirstname(pfirsrname);
            Sessiondata.getInstance().setLastname(plastname);*/

            Fragment fr = new Profileview();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);

        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }


    private void analytics() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ANALATIC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("status");
                            message = userObject.getString("msg");
                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                            //Toast.makeText(getActivity(), "page" + pageno, Toast.LENGTH_SHORT).show();
                        } else if (viewstatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                params.put("user_id",userfbidemail);
                params.put("page_id",pageno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
