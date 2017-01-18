package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.fragment.View_itemInfo_Fragment;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.categorylistvalues;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.foodtype;
import com.hhwt.travelplanner.model.photoss;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Mapvinavivisible extends Fragment implements View.OnClickListener {
    int current;
    GoogleMap googleMap;
    SharedPreferences sharedPreferences;
    int locationCount = 0;
    public static final String EXTRAVIEWINFO = "Viewinfo";
    public static final String EXTRATRIPINFO = "Viewinfo";
    Fragment fragment = null;
    LatLng coordinate;
    Retrofit retrofit;
    public static Activity _A;
    String titlename = "";
    categorylistvalues tata;
    MapView mMapView;
    ImageView backclick;
    int x;
    private static final String MYMAPVIEWCLICK = "http://hhwt.tech/hhwt_webservice/get_nearby_elements.php";
    public static final String KEY_USERNAME = "did";
    int mapclickstatus;
    String mapclickmsg;
    String imagereal = "";
    String mess;
    String snvalue, vreak;
    String ratingview = "";
    private RequestQueue mRequestQueue;
    ArrayList<photoss> photosdetails;
    ArrayList<foodclassification> foodclassificationdetails;
    ArrayList<foodtype> foodtypdetails;
    ArrayList<categorylistvalues> categorylistvaluesdetails;
    String snovaluemap;
    InternetAccessCheck internet;
    ArrayList<NearByelementsModel> nearbyelementsdetails;
    ProgressDialog progressDialog;
    String snoid = "";
    List<Categorylistmodel> items = new ArrayList<>();
    HashMap<String, Integer> mMarkers = new HashMap<String, Integer>();
    Hashtable connectFlags;
    private HashMap<String, String> markers;
    public CreatedTripModel newtrip;
    TextView apptitle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.multiplemap, container, false);


        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("HHWT");


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);








        mMapView = (MapView) v.findViewById(R.id.maps);
        markers = new HashMap<String, String>();
        internet = new InternetAccessCheck();
        Bundle bundle = this.getArguments();
        newtrip = (CreatedTripModel) bundle.getSerializable(CreateTripFragment.EXTRATRIPINFO);
        mess = getResources().getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(mess)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Sessiondata.getInstance().getFoodcatgory();
        Log.d("LatitudeArjun", "" + Sessiondata.getInstance().getFoodcatgory());
        _A = getActivity();
        mRequestQueue = Volley.newRequestQueue(_A);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = mMapView.getMap();
        googleMap.setMyLocationEnabled(false);
        sharedPreferences = getActivity().getSharedPreferences("location", 0);
        locationCount = Sessiondata.getInstance().getFoodcatgory().size();
        if (locationCount != 0) {
            String lat = "";
            String lng = "";
            for (int i = 0; i < locationCount; i++) {
                lat = Sessiondata.getInstance().getFoodcatgory().get(i).getLatitude().replace("@", "");
                lng = Sessiondata.getInstance().getFoodcatgory().get(i).getLongitude();
                titlename = Sessiondata.getInstance().getFoodcatgory().get(i).getName();
                ratingview = Sessiondata.getInstance().getFoodcatgory().get(i).getRating();
                snoid = Sessiondata.getInstance().getFoodcatgory().get(i).getSno();
                drawMarker(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));

            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))));
            coordinate = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 12);
            googleMap.animateCamera(yourLocation);
        }
        return v;
    }

    private void drawMarker(LatLng point) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("First Location");
        markerOptions.snippet("This Is Test Location");
        markerOptions.position(point);
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                arg0.getId();
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View myContentView = getActivity().getLayoutInflater().inflate(
                        R.layout.custommarker, null);
                TextView tvTitle = ((TextView) myContentView
                        .findViewById(R.id.title));
                tvTitle.setText(marker.getTitle());
                RatingBar tvSnippet = ((RatingBar) myContentView
                        .findViewById(R.id.snippet));
                tvSnippet.setRating(Float.parseFloat(marker.getSnippet()));
                ImageView popUpImage = (ImageView) myContentView.findViewById(R.id.imagemarker);
                Log.d("imageurl", "" + imagereal);
                mMarkers.put(marker.getId(), Integer.valueOf(snoid));
                return myContentView;
            }
        });
        googleMap.addMarker(new MarkerOptions()
                .position(point)
                .title(snoid + "-" + titlename)
                .snippet(String.valueOf(Float.parseFloat(ratingview)))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                // TODO Auto-generated method stub
                int id = mMarkers.get(arg0.getId());
                Log.d("Mapid", "" + id);
                Log.d("Mapidparameter", "" + snoid);
                String title = arg0.getTitle();
                Log.d("titlename", "" + title);
                String[] separated = title.split("-");
                vreak = separated[0];
                Sessiondata.getInstance().setSnomapvalue(vreak);
                Log.d("snoooo", "" + vreak);
                photosdetails = new ArrayList<photoss>();
                foodclassificationdetails = new ArrayList<foodclassification>();
                foodtypdetails = new ArrayList<foodtype>();
                categorylistvaluesdetails = new ArrayList<categorylistvalues>();
                nearbyelementsdetails = new ArrayList<NearByelementsModel>();
                snovaluemap = Sessiondata.getInstance().getSnomapvalue();
                Sessiondata.getInstance().setGuideledataid("Map");
                mapviewclick();

            }
        });

    }

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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == backclick){
            getFragmentManager().popBackStack();
        }
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

            Sessiondata.getInstance().setMapmarkerview(result);


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

    private void mapviewclick() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MYMAPVIEWCLICK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject userObject = new JSONObject(response);
                            mapclickstatus = userObject.getInt("Status");
                            mapclickmsg = userObject.getString("msg");
                            JSONArray jsonarray = userObject.getJSONArray("categorylistvalues");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject obj = jsonarray.getJSONObject(i);
                                categorylistvalues datas = new categorylistvalues();
                                datas.setSno(obj.getString("sno"));
                                Sessiondata.getInstance().setSnocreatenewtrip(datas.getSno());

                                Sessiondata.getInstance().setMapclicksno(datas.getSno());
                                datas.setCategoryID(obj.getString("categoryid"));
                                Sessiondata.getInstance().setCategoryIDmapclick(datas.getCategoryID());
                                datas.setName(obj.getString("name"));
                                Sessiondata.getInstance().setMapclickname(datas.getName());
                                Sessiondata.getInstance().setNamemapclick(datas.getName());
                                datas.setDescription(obj.getString("description"));
                                Sessiondata.getInstance().setDescriptionmapclick(datas.getDescription());
                                datas.setOpenhrs(obj.getString("openhrs"));
                                Sessiondata.getInstance().setOpenhrsmapclick(datas.getOpenhrs());
                                datas.setClosehrs(obj.getString("closehrs"));
                                Sessiondata.getInstance().setCloshrsmapclick(datas.getClosehrs());
                                datas.setWebsite(obj.getString("website"));
                                Sessiondata.getInstance().setWebsitemapclick(datas.getWebsite());
                                datas.setTimeremaining(obj.getString("timeremaining"));
                                datas.setEstimatedtime(obj.getString("estimatedtime"));
                                datas.setPricecurrency(obj.getString("pricecurrency"));
                                datas.setPrice(obj.getString("price"));
                                datas.setCountry(obj.getString("country"));
                                datas.setCity(obj.getString("city"));
                                Sessiondata.getInstance().setCitymapclick(datas.getCity());
                                datas.setState(obj.getString("state"));
                                datas.setAddress(obj.getString("address"));
                                Sessiondata.getInstance().setMapclickaddress(datas.getAddress());
                                datas.setPostalcode(obj.getString("postalcode"));
                                Sessiondata.getInstance().setPostalclickmapclick(datas.getPostalcode());
                                datas.setMap(obj.getString("map"));
                                datas.setPhone(obj.getString("phone"));
                                Sessiondata.getInstance().setPhonemapclick(datas.getPhone());
                                datas.setHhwtlink(obj.getString("hhwtlink"));
                                datas.setActivity(obj.getString("activity"));
                                datas.setWeight(obj.getString("weight"));
                                datas.setDistrict(obj.getString("district"));
                                datas.setLatitude(obj.getString("latitude"));
                                Sessiondata.getInstance().setLatitudemapclick(datas.getLatitude());
                                datas.setLongitude(obj.getString("longitude"));
                                Sessiondata.getInstance().setLongitudeemapclick(datas.getLongitude());
                                datas.setRating(obj.getString("rating"));
                                Sessiondata.getInstance().setMapratingclick(datas.getRating());
                                JSONArray jsonarray1 = obj.getJSONArray("photos");
                                for (int j = 0; j < jsonarray1.length(); j++) {
                                    JSONObject obj1 = jsonarray1.getJSONObject(j);
                                    photoss datas1 = new photoss();
                                    datas1.setId(obj1.getString("id"));
                                    Sessiondata.getInstance().setPhotoidmapclick(datas1.getId());
                                    datas1.setPhotourl(obj1.getString("photourl"));
                                    datas1.setCredits(obj1.getString("credits"));
                                    datas1.setCrediturl(obj1.getString("crediturl"));
                                    photosdetails.add(datas1);
                                    Sessiondata.getInstance().setPhotosdetails(photosdetails);
                                    Sessiondata.getInstance().setPhotourlmapclick(Sessiondata.getInstance().getPhotosdetails().get(j).getPhotourl());
                                }
                                JSONArray jsonarray2 = obj.getJSONArray("foodclassification");
                                for (int k = 0; k < jsonarray2.length(); k++) {
                                    JSONObject obj2 = jsonarray2.getJSONObject(k);
                                    foodclassification datas1 = new foodclassification();
                                    datas1.setFoodclassificationvalues(obj2.getString("foodclassificationvalues"));
                                    foodclassificationdetails.add(datas1);
                                    Sessiondata.getInstance().setFoodclassificationdetails(foodclassificationdetails);
                                }
                                JSONArray jsonarray3 = obj.getJSONArray("foodtype");
                                for (int l = 0; l < jsonarray3.length(); l++) {
                                    JSONObject obj3 = jsonarray3.getJSONObject(l);
                                    foodtype datas1 = new foodtype();
                                    datas1.setFoodtypevalues(obj3.getString("foodtypevalues"));
                                    foodtypdetails.add(datas1);
                                    Sessiondata.getInstance().setFoodtype(foodtypdetails);
                                }
                                datas.setTags(obj.getString("tags"));
                                Sessiondata.getInstance().setTagmapclick(datas.getTags());
                                Sessiondata.getInstance().setCtmvalue(newtrip);
                                datas.setCtm(newtrip);
                                categorylistvaluesdetails.add(datas);
                                Sessiondata.getInstance().setCategorylistmodeldetails(categorylistvaluesdetails);
                            }
                            JSONArray jsonarraysub = userObject.getJSONArray("nearbyelements");
                            for (int z = 0; z < jsonarraysub.length(); z++) {
                                JSONObject obj = jsonarraysub.getJSONObject(z);
                                NearByelementsModel datas = new NearByelementsModel();
                                datas.setCategoryid(obj.getString("categoryid"));
                                datas.setDistrict(obj.getString("district"));
                                datas.setTotalno(obj.getString("totalno"));
                                nearbyelementsdetails.add(datas);
                                Sessiondata.getInstance().setNearByelementsModeldetails(nearbyelementsdetails);
                            }
                        } catch (Exception ex) {

                        }
                        if (mapclickstatus == 1) {
                            progressDialog.dismiss();
                            Sessiondata.getInstance().setUpdateresult(1);
                            showViewDetFragment();
                        } else if (mapclickstatus == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), mapclickmsg, Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, snovaluemap);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }

    public void showViewDetFragment() {
        Fragment fr = new View_itemInfo_Fragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
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
    }

}
