package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.hhwt.travelplanner.fragment.View_itemInfo_Fragment;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.categorylistvalues;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.foodtype;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by jeyavijay on 05/04/16.
 */
public class listofguid extends Fragment implements View.OnClickListener{
    ArrayList<Guidimageurl> emphistorydetails;
    ListView itemlistclick;
    private static final String MYMAPVIEWCLICK = "http://hhwt.tech/hhwt_webservice/get_nearby_elements.php";
    public static final String KEY_USERNAME = "did";

    String snoid;
    int mapclickstatus;
    String mapclickmsg;
    public CreatedTripModel newtrip;
int k;
    String snovaluemap;
    ArrayList<photoss> photosdetails;
    ArrayList<foodclassification> foodclassificationdetails;
    Hashtable connectFlags;
    ArrayList<foodtype> foodtypdetails;

    TextView apptitle;

    ArrayList<NearByelementsModel> nearbyelementsdetails;
    ArrayList<categorylistvalues> categorylistvaluesdetails;
    ProgressDialog progressDialog;
    emphistoryAdapter emphistory;
    LinearLayout linearvalbottom;
    ImageView backclick;
    public static final String EXTRATRIPINFO = "Viewinfo";
    public listofguid() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.listofguid, container, false);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));



        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);


        itemlistclick = (ListView) v.findViewById(R.id.itemclicklist);

        emphistorydetails = Sessiondata.getInstance(). getGuidimagedetails();

        Bundle bundle = this.getArguments();
        newtrip = new CreatedTripModel();

        //Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
      //  Tapjoy.setDebugEnabled(true);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Guides");


        emphistory = new emphistoryAdapter(getActivity(), emphistorydetails);
        itemlistclick.setAdapter(emphistory);



       itemlistclick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              // snoid = Sessiondata.getInstance().getGuidimagedetails().get(position).











             k =  position;









               photosdetails = new ArrayList<photoss>();
               foodclassificationdetails = new ArrayList<foodclassification>();
               foodtypdetails = new ArrayList<foodtype>();
               categorylistvaluesdetails = new ArrayList<categorylistvalues>();
               nearbyelementsdetails = new ArrayList<NearByelementsModel>();
               snovaluemap = Sessiondata.getInstance().getGuidimagedetails().get(position).getDataelementid();
Sessiondata.getInstance().setGuideledataid(snovaluemap);

               mapviewclick();

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
        //Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
      //  Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }

    private void mapviewclick() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, MYMAPVIEWCLICK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
//parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            mapclickstatus = userObject.getInt("Status");
                            mapclickmsg = userObject.getString("msg");
                            JSONArray jsonarray = userObject.getJSONArray("categorylistvalues");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject obj = jsonarray.getJSONObject(i);
                                categorylistvalues datas = new categorylistvalues();
                                datas.setSno(obj.getString("sno"));
                                Log.d("sno", "" + datas.getSno());
                                Sessiondata.getInstance().setSnocreatenewtrip(datas.getSno());
                                Sessiondata.getInstance().setMapclicksno(datas.getSno());
                                datas.setCategoryID(obj.getString("caegoryid"));
                                Log.d("setCategoryID", "" + datas.getCategoryID());
                                Sessiondata.getInstance().setCategoryIDmapclick(datas.getCategoryID());
                                datas.setName(obj.getString("name"));
                                Log.d("name", "" + datas.getName());

                                Sessiondata.getInstance().setMapclickname(datas.getName());
                                Sessiondata.getInstance().setNamemapclick(datas.getName());
                                datas.setDescription(obj.getString("description"));
                                Log.d("status", "" + datas.getDescription());
                                Sessiondata.getInstance().setDescriptionmapclick(datas.getDescription());
                                datas.setFoodc(obj.getString("foodc"));

                                Sessiondata.getInstance().setFoodc(datas.getFoodc());
                                datas.setPrayerc(obj.getString("prayerc"));
                                Sessiondata.getInstance().setPrayerc(datas.getPrayerc());


                                datas.setOpenhrs(obj.getString("openhrs"));
                                Log.d("setOpenhrs", "" + datas.getOpenhrs());

                                Sessiondata.getInstance().setOpenhrsmapclick(datas.getOpenhrs());

                                datas.setClosehrs(obj.getString("closehrs"));
                                Log.d("setClosehrs", "" + datas.getClosehrs());

                                Sessiondata.getInstance().setCloshrsmapclick(datas.getClosehrs());
                                datas.setWebsite(obj.getString("website"));
                                Log.d("website", "" + datas.getWebsite());

                                Sessiondata.getInstance().setWebsitemapclick(datas.getWebsite());
                                datas.setTimeremaining(obj.getString("timeremaining"));
                                Log.d("setTimeremaining", "" + datas.getTimeremaining());


                                datas.setEstimatedtime(obj.getString("estimatedtime"));
                                Log.d("setEstimatedtime", "" + datas.getEstimatedtime());

                                datas.setPricecurrency(obj.getString("pricecurrency"));
                                Log.d("setPricecurrency", "" + datas.getPricecurrency());

                                datas.setPrice(obj.getString("price"));
                                Log.d("setPrice", "" + datas.getPrice());

                                datas.setCountry(obj.getString("country"));
                                Log.d("setCountry", "" + datas.getCountry());

                                datas.setCity(obj.getString("city"));
                                Log.d("setCity", "" + datas.getCity());

                                Sessiondata.getInstance().setCitymapclick(datas.getCity());
                                datas.setState(obj.getString("state"));
                                Log.d("setState", "" + datas.getState());

                                datas.setAddress(obj.getString("address"));
                                Log.d("setAddress", "" + datas.getAddress());

                                Sessiondata.getInstance().setMapclickaddress(datas.getAddress());
                                datas.setPostalcode(obj.getString("postalcode"));

                                Log.d("setPostalcode", "" + datas.getPostalcode());
                                Sessiondata.getInstance().setPostalclickmapclick(datas.getPostalcode());
                                datas.setMap(obj.getString("map"));

                                Log.d("setMap", "" + datas.getMap());

                                datas.setPhone(obj.getString("phone"));
                                Log.d("setPhone", "" + datas.getPhone());

                                Sessiondata.getInstance().setPhonemapclick(datas.getPhone());
                                datas.setHhwtlink(obj.getString("hhwtlink"));

                                Log.d("setHhwtlink", "" + datas.getHhwtlink());
                                datas.setActivity(obj.getString("activity"));
                                Log.d("setActivity", "" + datas.getActivity());

                                datas.setWeight(obj.getString("weight"));
                                Log.d("setWeight", "" + datas.getWeight());

                                datas.setDistrict(obj.getString("district"));
                                Log.d("setDistrict", "" + datas.getDistrict());

                                datas.setLatitude(obj.getString("latitude"));
                                Log.d("setLatitude", "" + datas.getLatitude());

                                Sessiondata.getInstance().setLatitudemapclick(datas.getLatitude());
                                datas.setLongitude(obj.getString("longitude"));

                                Log.d("setLongitude", "" + datas.getLongitude());
                                Sessiondata.getInstance().setLongitudeemapclick(datas.getLongitude());

                                datas.setRating(obj.getString("rating"));
                                Log.d("setRating", "" + datas.getRating());
                                Sessiondata.getInstance().setMapratingclick(datas.getRating());


                                JSONArray jsonarray1 = obj.getJSONArray("photos");


                                for (int j = 0; j < jsonarray1.length(); j++) {
                                    JSONObject obj1 = jsonarray1.getJSONObject(j);
                                    photoss datas1 = new photoss();
                                    datas1.setId(obj1.getString("id"));
                                    Log.d("setId", "" + datas1.getId());
                                    Sessiondata.getInstance().setPhotoidmapclick(datas1.getId());
                                    datas1.setPhotourl(obj1.getString("photourl"));
                                    Log.d("setPhotourl", "" + datas1.getPhotourl());


                                    datas1.setCredits(obj1.getString("credits"));
                                    Log.d("setCredits", "" + datas1.getCredits());
                                    datas1.setCrediturl(obj1.getString("crediturl"));
                                    Log.d("setCrediturl", "" + datas1.getCrediturl());

                                    datas1.setBigurl(obj1.getString("bigurl"));
                                    photosdetails.add(datas1);
                                    Sessiondata.getInstance().setPhotosdetails(photosdetails);
                                    Sessiondata.getInstance().setPhotourlmapclick(Sessiondata.getInstance().getPhotosdetails().get(j).getPhotourl());
                                    Log.d("Photourl", "" + Sessiondata.getInstance().getPhotourlmapclick());
                                }

                                JSONArray jsonarray2 = obj.getJSONArray("foodclassification");


                                for (int k = 0; k < jsonarray2.length(); k++) {
                                    JSONObject obj2 = jsonarray2.getJSONObject(k);
                                    foodclassification datas1 = new foodclassification();
                                    datas1.setFoodclassificationvalues(obj2.getString("foodclassificationvalues"));
                                    Log.d("setFoodclassificationvalues", "" + datas1.getFoodclassificationvalues());
                                    foodclassificationdetails.add(datas1);
                                    Sessiondata.getInstance().setFoodclassificationdetails(foodclassificationdetails);
                                }

                                JSONArray jsonarray3 = obj.getJSONArray("foodtype");


                                for (int l = 0; l < jsonarray3.length(); l++) {
                                    JSONObject obj3 = jsonarray3.getJSONObject(l);
                                    foodtype datas1 = new foodtype();
                                    datas1.setFoodtypevalues(obj3.getString("foodtypevalues"));
                                    Log.d("setFoodtypevalues", "" + datas1.getFoodtypevalues());
                                    foodtypdetails.add(datas1);
                                    Sessiondata.getInstance().setFoodtype(foodtypdetails);
                                }


                                datas.setTags(obj.getString("tags"));
                                Log.d("setTags", "" + datas.getTags());
                                datas.setFoodttags(obj.getString("foodttags"));

                                datas.setFoodctags(obj.getString("foodctags"));
                                datas.setTours(obj.getString("tours"));

                                Sessiondata.getInstance().setTagmapclick(datas.getTags());

                                categorylistvaluesdetails.add(datas);

                                Sessiondata.getInstance().setCategorylistmodeldetails(categorylistvaluesdetails);


                                categorylistvaluesdetails.get(i).setCtm(newtrip);


                            }

                            JSONArray jsonarraysub = userObject.getJSONArray("nearbyelements");
                            for (int z = 0; z < jsonarraysub.length(); z++) {
                                JSONObject obj = jsonarraysub.getJSONObject(z);
                                NearByelementsModel datas = new NearByelementsModel();
                                datas.setCategoryid(obj.getString("categoryid"));
                                Log.d("setCategoryid", "" + datas.getCategoryid());
                                datas.setDistrict(obj.getString("district"));
                                Log.d("setDistrict", "" + datas.getDistrict());
                                datas.setTotalno(obj.getString("totalno"));
                                Log.d("totalno", "" + datas.getTotalno());
                                Sessiondata.getInstance().setCtmvalue(newtrip);
                                nearbyelementsdetails.add(datas);
                                Sessiondata.getInstance().setNearByelementsModeldetails(nearbyelementsdetails);
                            }


                            Log.d("Photodetails", "" + Sessiondata.getInstance().getPhotosdetails());
                            Log.d("Foodclassificationdetails", "" + Sessiondata.getInstance().getFoodclassificationdetails());
                            Log.d("Foodtypedetails", "" + Sessiondata.getInstance().getFoodtype());
                            Log.d("NearByelementsModeldetails", "" + Sessiondata.getInstance().getNearByelementsModeldetails());
                            Log.d("Categorylistmodeldetails", "" + Sessiondata.getInstance().getCategorylistmodeldetails());
                            // Log.d("second SIZE", String.valueOf(ownersigninleadgerdetails.size()));


                        } catch (Exception ex) {
                            Log.d("Excep", ex.getMessage());
                        }


                        if (mapclickstatus == 1) {
                            progressDialog.dismiss();
                            // Toast.makeText(MainActivity.this, mapclickmsg, Toast.LENGTH_LONG).show();

/*

                            Sessiondata.getInstance().setUpdateresult(0);


                            Sessiondata.getInstance().setFtplistval(7);
                            Fragment fr = new NewUiView_itemInfo_Fragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(EXTRATRIPINFO, categorylistvaluesdetails);
                            fr.setArguments(bundle);
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);
*/








/*
                            Sessiondata.getInstance().setUpdateresult(0);



                            Sessiondata.getInstance().setFtplistval(7);
                            Fragment fr = new NewUiView_itemInfo_Fragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(EXTRATRIPINFO,  categorylistvaluesdetails.get(i));
                            fr.setArguments(bundle);
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);}*/





                        Sessiondata.getInstance().setUpdateresult(1);

                        Fragment fr = new View_itemInfo_Fragment();
                        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                        fc.replaceFragment(fr);
                    }


                         else if (mapclickstatus == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), mapclickmsg, Toast.LENGTH_LONG).show();

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
                params.put(KEY_USERNAME,snovaluemap);

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
        ArrayList<Guidimageurl> sing_in_datas;
        private int lastPosition = -1;

        public emphistoryAdapter( Context con, ArrayList<Guidimageurl> singindatas) {
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
            if(null == view) {
                view = mLayoutInflater.inflate(R.layout.guidlistitemclicklist, null);
                viewHolder = new ViewHolder();


                viewHolder.name = (RobotoTextView) view.findViewById(R.id.namelist);

                viewHolder.imagelist = (SquareImageView) view.findViewById(R.id.guimagelist);
                viewHolder.acti = (RobotoTextView) view.findViewById(R.id.activitylist);

                viewHolder.cd = (CardView) view.findViewById(R.id.topcard);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();



            viewHolder.name.setText(sing_in_datas.get(position).getName());


            viewHolder.acti.setText(sing_in_datas.get(position).getActivity());




            Guidimageurl newsItem = (Guidimageurl) sing_in_datas.get(position);





            if (newsItem.getPhoto().length() > 0) {
                try {
                    final String s = newsItem.getPhoto();
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
        RobotoTextView acti;
    }







}
