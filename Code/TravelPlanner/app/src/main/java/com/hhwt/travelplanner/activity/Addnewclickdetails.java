package com.hhwt.travelplanner.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
import com.hhwt.travelplanner.adapter.Base64;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 11/11/16.
 */
public class Addnewclickdetails extends Fragment implements View.OnClickListener {

    LinearLayout linearvalbottom;
    TextView apptitle,pronames;
    ImageView backclick;
    ImageView profileimage;
    private static String file;
    Bitmap bitmap;
String imagevalue;
int i;
    int restatus;
    String remsg;

    String dataelementid,placename;
    Spinner language,timespinner,monthspinner;
String languageselected,timesselected,mothnsselected,describestr,fullreviewstr;

    RobotoEditText describe,fullreview;
    String image;
    String imagemsg, imageurl;

    int imagestatus;
    String profilename,fbemailidpass,rating1;

    public static final String KEY_IMAGE = "image1";

    TextView postlist;
    ImageView image1,image2,image3;

RatingBar feedratingBarplan;

    private static final String IMAGEUPDATE = "http://www.rgmobiles.com/hhwt_webservice/image_url.php";
LinearLayout secondimg,thirdimg;



    private  static  final  String REVIEWPOSTING ="http://www.hhwt.tech/hhwt_webservice/insertcomments.php";

    ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addnewclickdetails,container,false);
        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText(Sessiondata.getInstance().getNewreviewsname());

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        dataelementid  = Sessiondata.getInstance().getReviewdataelementsno();
        placename =  Sessiondata.getInstance().getNewreviewsname();

        secondimg = (LinearLayout) v.findViewById(R.id.secondimg);
        secondimg.setVisibility(View.GONE);


        thirdimg = (LinearLayout) v.findViewById(R.id.thirdimg);
        thirdimg.setVisibility(View.GONE);



        profileimage = (ImageView) v.findViewById(R.id.profile_image);
        pronames = (TextView) v.findViewById(R.id.namelist);

        if (null != Sessiondata.getInstance().getFbimage()) {

           profileimage.setImageBitmap(Sessiondata.getInstance().getFbimage());
           pronames.setText(Sessiondata.getInstance().getLoginname());

        } else {
            profileimage.setVisibility(View.VISIBLE);
            pronames.setText("" + Sessiondata.getInstance().getLoginname());

        }



        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);




        profilename = pronames.getText().toString();
        fbemailidpass = Sessiondata.getInstance().getReviewfbemailid();


        language = (Spinner) v.findViewById(R.id.spinner1);

timespinner = (Spinner) v.findViewById(R.id.spinner3);

        monthspinner = (Spinner) v.findViewById(R.id.spinner2);

        describe = (RobotoEditText) v.findViewById(R.id.describe);

        fullreview = (RobotoEditText) v.findViewById(R.id.fullreview);

        postlist = (TextView) v.findViewById(R.id.postlist);

        feedratingBarplan = (RatingBar) v.findViewById(R.id.feedratingBarplan);

        image1 = (ImageView) v.findViewById(R.id.guimagelist);
        image2 = (ImageView) v.findViewById(R.id.imagesecond);
        image3 = (ImageView) v.findViewById(R.id.imagethird);


        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
image3.setOnClickListener(this);

        postlist.setOnClickListener(this);


        final ArrayList<String> countries = new ArrayList<String>();
        countries.add("Language");
        countries.add("English");
        countries.add("Bahasa Melayu");
        countries.add("Bahasa Indonesia");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.simple_spinner_itemcustomreview, countries);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(dataAdapter);
        language.setSelection(0);
        language.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = language.getSelectedItemPosition();

                        languageselected = countries.get(position);



                        if(languageselected.equals("Language")){
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                        }
                        else{
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                        }




                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {


                    }});








        final ArrayList<String> times = new ArrayList<String>();
        times.add("Time Spent");
        times.add("1 hours");
        times.add("2 hours");
        times.add("3 hours");
        times.add("4 hours");
        times.add("5 hours");
        times.add("6 hours");
        times.add("7 hours");
        times.add("8 hours");

        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.simple_spinner_itemcustomreview, times);
        timesAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timespinner.setAdapter(timesAdapter);
        timespinner.setSelection(0);
        timespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = timespinner.getSelectedItemPosition();
                        timesselected = times.get(position);


                        if(timesselected.equals("Time Spent")){
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                        }
                        else{
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {


                    }});







        final ArrayList<String> mothns = new ArrayList<String>();
        mothns.add("When");
        mothns.add("July 2016");
        mothns.add("August 2016");
        mothns.add("September 2016");
        mothns.add("October 2016");
        mothns.add("November 2016");
        mothns.add("December 2016");
        mothns.add("January 2017");
        mothns.add("February 2017");
        mothns.add("March 2017");
        mothns.add("April 2017");
        mothns.add("May 2017");
        mothns.add("June 2017");
        mothns.add("July 2017");
        mothns.add("August 2017");
        mothns.add("September 2017");
        mothns.add("October 2017");
        mothns.add("November 2017");
        mothns.add("December 2017");







        ArrayAdapter<String> mothnsAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.simple_spinner_itemcustomreview, mothns);





        mothnsAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthspinner.setAdapter(mothnsAdapter);
        monthspinner.setSelection(0);



        monthspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = monthspinner.getSelectedItemPosition();
                        mothnsselected = mothns.get(position);

                        if(mothnsselected.equals("When")){
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                        }
                        else{
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {






                    }});




















        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == backclick){
            getFragmentManager().popBackStack();
        }

        else if(v == postlist){
            describestr = describe.getText().toString();
            fullreviewstr = fullreview.getText().toString();
            rating1=String.valueOf(feedratingBarplan.getRating());

            //placename
         //   dataelementid  profilename  fbemailidpass
          //  mothnsselected; timesselected  languageselected  imagevalue



            if(rating1.equals("0.0")){

                Toast.makeText(getActivity(),"Please select star rating",Toast.LENGTH_LONG).show();
                return;
            }



            if(languageselected.equals("Language")){

                Toast.makeText(getActivity(),"Select Language",Toast.LENGTH_LONG).show();
                return;
            }

            if(mothnsselected.equals("When")){

                Toast.makeText(getActivity(),"Select date of visit",Toast.LENGTH_LONG).show();
                return;
            }



            if(describestr.equals("")){

                Toast.makeText(getActivity(),"Tell us your experience in a few words",Toast.LENGTH_LONG).show();
                return;
            }

            Imageupload();


          /*  AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
            alert.setTitle("Thank you for contributing to the HHWT community!");
            alert.setMessage("Help other Muslim travellers by adding more reviews.");
            alert.setCancelMessage(null);

            alert.setButton("OKAY", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //do somthing or dismiss dialog by                dialog.dismiss();
                    getFragmentManager().popBackStack();

                }
            });

            alert.show();*/




        }


        else if (v == image1) {
i = 0;
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);

        }


        else if (v == image2){

            i = 1;

            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);

        }

        else if (v == image3){

            i = 2;

            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);

        }


    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                if (i == 0) {
                    image1.setImageBitmap(bitmap);
                    secondimg.setVisibility(View.VISIBLE);
                }
                else if (i == 1) {
                    image2.setImageBitmap(bitmap);
                    thirdimg.setVisibility(View.VISIBLE);
                }
                else if(i == 2){

                    image3.setImageBitmap(bitmap);

                }



                String path2 = Environment.getExternalStorageDirectory().toString();

                File fn;
                try {  // Try to Save #1
                    fn = new File("/mnt/sdcard/out3.png");
                    FileOutputStream out = new FileOutputStream(fn);
                  /*  Toast.makeText(getActivity().getApplicationContext(), "In Save",
                            Toast.LENGTH_LONG).show();*/
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                    file = fn.toString();
                    Log.d("filestring", "" + file);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }









    private void Imageupload() {


        if (bitmap != null) {

            image = getStringImage(bitmap);
            Log.d("imagemethod", "" + image);

        } else {
           // Toast.makeText(getActivity(), "Select your image", Toast.LENGTH_SHORT).show();

            image = "";
            imagevalue = "";

withoutimage();





            // Log.d("image",""+)
         //   reviewpassing();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST,IMAGEUPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            imageurl = userObject.getString("image1");
                            Log.d("arjunimage", "" + imageurl);
                            Sessiondata.getInstance().setNotesimage(imageurl);
                            imagestatus = userObject.getInt("status");
                            Log.d("imagestatus", "" + imagestatus);
                            imagemsg = userObject.getString("msg");
                            Log.d("changepinmsg", "" + imagemsg);
                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (imagestatus == 1) {
                            progressDialog.dismiss();

                            imagevalue = Sessiondata.getInstance().getNotesimage();


                            AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                            alert.setTitle("Thank you for contributing to the HHWT community!");
                            alert.setMessage("Help other Muslim travellers by adding more reviews.");
                            alert.setCancelMessage(null);

                            alert.setButton("OKAY", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    //do somthing or dismiss dialog by                dialog.dismiss();


                                    Reviewsposting();
                                }
                            });

                            alert.show();


                          //  reviewpassing();
                        } else if (imagestatus == 0) {
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                Log.d("Mapparams", "" + params);
                // params.put(KEY_USERNAME,username);
                params.put(KEY_IMAGE,image);
                Log.d("parameterparssing", "" + params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

    }

    private void withoutimage() {

        AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
        alert.setTitle("Thank you for contributing to the HHWT community!");
        alert.setMessage("Help other Muslim travellers by adding more reviews.");
        alert.setCancelMessage(null);

        alert.setButton("OKAY", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //do somthing or dismiss dialog by                dialog.dismiss();


                Reviewsposting();
            }
        });

        alert.show();





    }


    private void Reviewsposting() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,REVIEWPOSTING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            restatus = userObject.getInt("status");
                            remsg = userObject.getString("msg");
                        } catch (Exception ex) {
                        }
                        if (restatus == 1) {
                            progressDialog.dismiss();

                            getFragmentManager().popBackStack();
                           // Toast.makeText(getActivity(),"baaaa",Toast.LENGTH_LONG).show();

                        } else if (restatus == 0) {



                            progressDialog.dismiss();
                            getFragmentManager().popBackStack();
                          //  Toast.makeText(getActivity(),"Aaaaa",Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Log.d("Mapparams",""+params);
                params.put("dataelement",dataelementid);
                params.put("fbid",fbemailidpass);
                params.put("name",profilename);
                params.put("reviews",fullreviewstr);
                params.put("reviewname",describestr);
                params.put("placename",placename);
                params.put("rating",rating1);
                params.put("purpose",languageselected);
                params.put("time",mothnsselected);
                params.put("hours",timesselected);
                params.put("image",imagevalue);


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
