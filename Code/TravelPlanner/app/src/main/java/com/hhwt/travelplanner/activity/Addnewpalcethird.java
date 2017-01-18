package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by jeyavijay on 11/04/16.
 */
public class Addnewpalcethird extends Fragment implements View.OnClickListener{
    //GoogleMap.OnMapClickListener
  //  MapView mMapView;
    //private GoogleMap googleMap;
    SharedPreferences sharedPreferences;
    //TextView locationTv;
    EditText addressdata;

    String addressvalue;
    String userid;

    int locationCount = 0;
    Button submitplace;
    int i =0;
    String locations;
    String remove = "null";
    String addlocation;
    Hashtable connectFlags;
    ProgressDialog progressDialog;
    int feedbackstatus;
    String feedbackmsg;
String maincategory,subcategeroy,finalsubcategory;

    String addname,adddescription;
    TextView apptitle;

    LinearLayout linearvalbottom;
ImageView backclick;

    private static final String ADDPLACE = "http://hhwt.tech/hhwt_webservice/addnewerplace.php";

    public static final String KEY_USERID = "userid";
    public static final String KEY_NAME= "name";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_SUBCATEGORY = "scategory";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PHOTO = "photo";
    public static final String KEY_ADDRESS = "address";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addlistingmap, container, false);
        submitplace = (Button)v.findViewById(R.id.addnewsubmit);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Add a new place");



        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);



        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


      //  mMapView = (MapView) v.findViewById(R.id.mapViewcurrent);


       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        //Tapjoy.setDebugEnabled(true);

       // locationTv = (TextView)v.findViewById(R.id.latlongLocation);
        addressdata = (EditText)v.findViewById(R.id.address);
        submitplace.setOnClickListener(this);


       /* mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = mMapView.getMap();
        googleMap.setMyLocationEnabled(false);
        sharedPreferences = getActivity().getSharedPreferences("location", 0);
        locationCount = sharedPreferences.getInt("locationCount", 0);
        String zoom = sharedPreferences.getString("zoom", "0");
        String lat = "";
        String lng = "";
        lat = sharedPreferences.getString("lat","0");
        lng = sharedPreferences.getString("lng","0");
        onMapReady(googleMap);
       // googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(Float.parseFloat(zoom)));

        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMapClickListener(this);*/

        return v;
    }

   /* private void drawMarker(LatLng point){
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);


        googleMap.addMarker(new MarkerOptions()
                .position(point)
                .title("")
                .snippet("")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));

    }
*/

   /* public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.5665, 126.9780);
        // googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/


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
       // Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
        //Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }

    @Override
    public void onClick(View v) {

         if (v == submitplace) {

             addname = Sessiondata.getInstance().getAddname();
             Log.d("addname",""+addname);
             adddescription = Sessiondata.getInstance().getAdddescription();
             Log.d("adddescription",""+adddescription);
             addressvalue = addressdata.getText().toString();
             Log.d("addressvalue",""+addressvalue);
             SharedPreferences preferences= getActivity().getSharedPreferences("tempss", getActivity().MODE_PRIVATE);
             String name=preferences.getString("namess",null);
             Log.d("facebookprofile id",""+name);
             userid = name;
             Log.d("userid",""+userid);
             if(addlocation != null){
                 Log.d("addlocation",""+addlocation);
             }
             else
             {
                 /*Toast.makeText(getActivity(),
                         "Click Your Place in Map", Toast.LENGTH_SHORT).show();*/

             }


             if(Sessiondata.getInstance().getMaincategory() == null) {
                 maincategory = "";
             }
             else {
                 maincategory = Sessiondata.getInstance().getMaincategory();
                 Log.d("maincategory", "" + maincategory);
             }
             if(Sessiondata.getInstance().getSubcategreyvalues() == null){
                 finalsubcategory = "";
             }
             else {
                 subcategeroy = Sessiondata.getInstance().getSubcategreyvalues();
                 finalsubcategory = subcategeroy.replace(remove, "");
                 Log.d("subcategory", "" + finalsubcategory);
             }
             //getActivity().finish();

             Addplacefinal();


         }
        else if(v == backclick){
             getFragmentManager().popBackStack();
         }

    }


    private void Addplacefinal() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ADDPLACE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                           /* feedbackstatus = userObject.getInt("status");
                            Log.d("feedbackstatus", "" + feedbackstatus);
                            feedbackmsg = userObject.getString("msg");
                            Log.d("feedbackmsg", "" + feedbackmsg);
                            */

                            feedbackstatus = userObject.getInt("success");
                            Log.d("feedbackstatus", "" + feedbackstatus);
                            feedbackmsg = userObject.getString("message");
                            Log.d("feedbackmsg", "" + feedbackmsg);




                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (feedbackstatus == 1) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),
                                    "Success! We'll add it to our list after review.", Toast.LENGTH_SHORT).show();

                            Intent in = new Intent(getActivity(),Newnavigationbottom.class);
                            startActivity(in);

                             getActivity().finish();

                           /* Fragment fr = new First_Page_Fragment();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);*/
                        } else if (feedbackstatus == 0) {
                            progressDialog.dismiss();
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
                params.put(KEY_USERID,userid);
                params.put(KEY_NAME,addname);
                params.put(KEY_LOCATION,"");
                params.put(KEY_CATEGORY,maincategory);
                params.put(KEY_SUBCATEGORY,finalsubcategory);
                params.put(KEY_DESCRIPTION,adddescription);
                params.put(KEY_PHOTO,"");
                params.put(KEY_ADDRESS,addressvalue);
                Log.d("parameterparssing",""+params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        Log.d("Result array",""+requestQueue);
        Log.d("Result final",""+stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }



    /*public void callBackDataFromAsyncTask(String address) {
        //addressText.setText(address);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }*/


    /*@Override
    public void onMapClick(LatLng point) {
        googleMap.clear();
        //Adding a new marker to the current pressed position we are also making the draggable true
        googleMap.addMarker(new MarkerOptions()
                .position(point)
                .draggable(true));
       // locationTv.setText("You are at [" + Double.toString(point.latitude) + " ; " + Double.toString(point.longitude) + " ]");
        //locations = Double.toString(point.latitude)+","+Double.toString(point.longitude);
        addlocation = locations;

       // Toast.makeText(getActivity(), "Place added Successfully", Toast.LENGTH_SHORT).show();
    }*/
}
