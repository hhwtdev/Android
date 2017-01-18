package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * Created by jeyavijay on 01/04/16.
 */
public class Guidlist extends Fragment implements View.OnClickListener{
    emphistoryAdapter emphistory;
    ListView guidview;
    String URL;
    ArrayList<Guidview> emphistorydetails;

    String snopass;
    public static final String KEY_USERID = "sno";
    private static final String GUIDLIDTVIEWitem = "http://everestitservices.com/hhwt_webservice/guidesfetchbyid.php?";
   //private static final String GUIDLIDTVIEW = "http://www.hhwt.tech/hhwt_webservice/guidesfetch.php";



    private static final String GUIDLIDTVIEW = "http://everestitservices.com/hhwt_webservice/guidesfetch.php";

ImageView backclick;
TextView apptitle;

    ProgressDialog progressDialog;
    int viewstatus;
    ArrayList<Guidview> imageurl;
    Retrofit retrofit;
    InternetAccessCheck internet;
    public static Activity _A;
    String mess;
    Hashtable connectFlags;
    String MyPREFERENCES = "HHWT";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public String fb_id, _CityName, _CityID, _CityImage;
    ArrayList<Guidimageurl> photosdetails;
    ArrayList<Guidview> viewdetails;

    LinearLayout linearvalbottom;

    public Guidlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewdetails = new ArrayList<Guidview>();
        photosdetails = new ArrayList<Guidimageurl>();
        View v = inflater.inflate(R.layout.guidlisthandling, container, false);


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);





        _A = getActivity();
        sharedpreferences = _A.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        fb_id = sharedpreferences.getString("fb_id", "0");
        _CityID = sharedpreferences.getString("sno", "0");
        _CityName = sharedpreferences.getString("city", "0");
        _CityImage = sharedpreferences.getString("img", "0");
        internet = new InternetAccessCheck();


        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Guides");

        guidview = (ListView) v.findViewById(R.id.guidvaluelist);
        viewriewcomment(0);


       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
        // Tapjoy.setDebugEnabled(true);


        guidview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sessiondata.getInstance().setGuiditemclickguid(Sessiondata.getInstance().getGuidlistdetails().get(position).getGuides());
                Sessiondata.getInstance().setGuiditemclickdescription(Sessiondata.getInstance().getGuidlistdetails().get(position).getDescription());
                Sessiondata.getInstance().setGuiditemclicktips(Sessiondata.getInstance().getGuidlistdetails().get(position).getTips());
                Sessiondata.getInstance().setGuiditemclickimagesmain(Sessiondata.getInstance().getGuidlistdetails().get(position).getGuidecover());

                snopass = Sessiondata.getInstance().getGuidlistdetails().get(position).getSno();


                photosdetails = new ArrayList<Guidimageurl>();
                viewriewcomment();


//        Sessiondata.getInstance().setGuiditemcimage(Sessiondata.getInstance().getGuidimagedetails().get(position).getPhoto());





       /* String URLS = Sessiondata.getInstance().getGuidimagedetails().get(position).getPhoto();





        itemclickXMLTask task = new itemclickXMLTask();
        // Execute the task
        task.execute(new String[] {URLS});

*/


            }
        });


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
      //  Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
       // Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }

    private void viewriewcomment() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GUIDLIDTVIEWitem,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("status");
                            Log.d("status", "" + viewstatus);
                            /*empprofilemsg = userObject.getString("msg");
                            Log.d("msg",""+empprofilemsg);
*/

                            JSONArray jsonarray1 = userObject.getJSONArray("result");

                            for (int i = 0; i < jsonarray1.length(); i++) {
                                JSONObject obj2 = jsonarray1.getJSONObject(i);
                                Guidimageurl datas = new Guidimageurl();
                                datas.setDataelementid(obj2.getString("dataelementid"));

                                Log.d("setId", "" + datas.getDataelementid());
                                datas.setName(obj2.getString("name"));
                                Log.d("setPhotourl", "" + datas.getName());
                                datas.setActivity(obj2.getString("activity"));
                                Log.d("setCredits", "" + datas.getActivity());
                                datas.setPhoto(obj2.getString("photo"));
                                Log.d("setCrediturl", "" + datas.getPhoto());
                                Sessiondata.getInstance().setGuidimgurl(datas.getPhoto());
                                photosdetails.add(datas);
                                Sessiondata.getInstance().setGuidimagedetails(photosdetails);
                                Log.d("Photourl", "" + Sessiondata.getInstance().getGuidimagedetails());
                            }


                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                                                      progressDialog.dismiss();

                            Fragment fr = new Guidlistitem();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);

                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                            // Toast.makeText(getApplicationContext(), empprofilemsg, Toast.LENGTH_LONG).show();

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
                params.put(KEY_USERID, snopass);
                params.put("cityid", _CityID);
                Log.d("paramsusername", "" + params);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
//initialize the progress dialog and show it
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
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
    }


    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<Guidview> sing_in_datas;
        private int lastPosition = -1;

        public emphistoryAdapter(Context con, ArrayList<Guidview> singindatas) {
            this.context = con;
            this.sing_in_datas = singindatas;
            mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return sing_in_datas.size();
        }

        @Override
        public Object getItem(int position) {
            return sing_in_datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (null == view) {
                view = mLayoutInflater.inflate(R.layout.guidlistadd, null);
                viewHolder = new ViewHolder();


                viewHolder.name = (RobotoTextView) view.findViewById(R.id.guidlist);

                viewHolder.imagelist = (SquareImageView) view.findViewById(R.id.guimage);
                viewHolder.cd = (CardView) view.findViewById(R.id.topcard);


                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();


            viewHolder.name.setText(sing_in_datas.get(position).getGuides());

            Guidview newsItem = (Guidview) sing_in_datas.get(position);


            if (newsItem.getGuidecover().length() > 0) {
                try {
                    final String s = newsItem.getGuidecover();
                    //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                    Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.imagelist, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(context)
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(viewHolder.imagelist, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                            //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                        }
                                    });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            Animation animation = AnimationUtils.loadAnimation(context,
                    (position > lastPosition) ? R.anim.up_from_bottom
                            : R.anim.down_from_top);
            viewHolder.cd.startAnimation(animation);
            lastPosition = position;
            return view;
        }
    }

    static class ViewHolder {
        RobotoTextView name;
        SquareImageView imagelist;
        public CardView cd;
    }


    class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.appicon);
                        imageView.setImageDrawable(placeholder);
                    }
                }
            }
        }

    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
           /* if (statusCode != HttpStatus.SC_OK) {
                return null;
            }*/

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }


    private void viewriewcomment(int i) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GUIDLIDTVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            viewstatus = userObject.getInt("status");
                            Log.d("status", "" + viewstatus);
                            /*empprofilemsg = userObject.getString("msg");
                            Log.d("msg",""+empprofilemsg);
*/

                            JSONArray jsonarray = userObject.getJSONArray("msg");


                            for (int j = 0; j < jsonarray.length(); j++) {
                                JSONObject obj1 = jsonarray.getJSONObject(j);
                                Guidview datas1 = new Guidview();
                                datas1.setSno(obj1.getString("sno"));

                                datas1.setGuides(obj1.getString("guides"));

                                datas1.setGuidecover(obj1.getString("guidecover"));

                                datas1.setDescription(obj1.getString("description"));

                                datas1.setTips(obj1.getString("tips"));

                                viewdetails.add(datas1);
                                Sessiondata.getInstance().setGuidlistdetails(viewdetails);


                            }


                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (viewstatus == 1) {
                            progressDialog.dismiss();


                            emphistorydetails = Sessiondata.getInstance().getGuidlistdetails();
                            emphistory = new emphistoryAdapter(getActivity(), emphistorydetails);
                            guidview.setAdapter(emphistory);


                       /*     FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);*/

                        } else if (viewstatus == 0) {
                            progressDialog.dismiss();
                            // Toast.makeText(getApplicationContext(), empprofilemsg, Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(_A, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("cityid", _CityID);
                Log.d("Mapparams", "" + params);
                //params.put("","");
                Log.d("paramsusername", "" + params);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(_A);
        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
//initialize the progress dialog and show it
        progressDialog = new ProgressDialog(_A);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }

}