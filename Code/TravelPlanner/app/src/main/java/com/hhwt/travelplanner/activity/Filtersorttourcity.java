package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.hhwt.travelplanner.fragment.Touractivity;
import com.hhwt.travelplanner.fragment.Tourlist;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.AdapterView.OnClickListener;
import static android.widget.AdapterView.OnItemSelectedListener;
public  class Filtersorttourcity extends Fragment implements OnClickListener{

    emphistoryAdapter emphistory;
    emphistoryAdapters emphistorys;
String tosno;

     List<String> acategories = new ArrayList<String>();
    private static final String TOURDETAILS = "http://hhwt.tech/hhwt_webservice/shortlist_tour.php";
    Spinner aspinnerpickup;
    public static final String KEY_USERID = "id";
    public static final String KEY_SELLING_RATE = "short_type";
    public static final String KEY_TOURTYPE = "tour_type";
     List<String> categoriesdrop = new ArrayList<String>();
    ArrayList<Tourdetails> photosdetails;
    ProgressDialog progressDialog;
    TextView donefilter;
    String sellingval;
    TextView clearall;
    String sortby;
    int tsucess;
    String tourtype,tourtypevalue;
    Spinner spinnerdrop;
    public  Filtersorttourcity(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filtersorttourcity,container,false);
        aspinnerpickup = (Spinner) v.findViewById(R.id.spinner);
        spinnerdrop = (Spinner) v.findViewById(R.id.spinnerdrop);
        clearall = (TextView) v.findViewById(R.id.clearall);
        donefilter = (TextView) v.findViewById(R.id.donefilter);
        donefilter.setOnClickListener(this);
        tosno = Sessiondata.getInstance().getToursno();
        acategories.add("Sort By");
        acategories.add("Top Selling");
        acategories.add("Rating (High - Low)");
        acategories.add("Rating (Low - High)");
        acategories.add("Price (High - Low)");
        acategories.add("Price (Low - High)");
        emphistory = new emphistoryAdapter(getActivity(), (ArrayList<String>) acategories);
        aspinnerpickup.setAdapter(emphistory);
        aspinnerpickup.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = aspinnerpickup.getSelectedItemPosition();
                        sortby = acategories.get(position);
                        if (sortby.equals("Top Selling")) {
                            sellingval = "5";
                        } else if (sortby.equals("Rating (High - Low)")) {
                            sellingval = "4";
                        } else if (sortby.equals("Rating (Low - High)")) {
                            sellingval = "3";
                        } else if (sortby.equals("Price (High - Low)")) {
                            sellingval = "2";
                        } else if (sortby.equals("Price (Low - High)")) {
                            sellingval = "1";
                        } else if (sortby.equals("Sort By")) {sellingval = "0";
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }}
        );
        clearall.setOnClickListener(this);
        categoriesdrop.add("Tour Type");
        categoriesdrop.add("Day Tours");
        categoriesdrop.add("Tour Packages");
        categoriesdrop.add("Cruises, Saling & Water Tours");
        categoriesdrop.add("Cultural & Theme Tours");
        categoriesdrop.add("Family Friendly");
        categoriesdrop.add("Food Tours");
        categoriesdrop.add("Holiday & Seasonal Tours");
        categoriesdrop.add("Multi-Day & Extended Tours");
        emphistorys = new emphistoryAdapters(getActivity(), (ArrayList<String>) categoriesdrop);
        spinnerdrop.setAdapter(emphistorys);
        spinnerdrop.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spinnerdrop.getSelectedItemPosition();
                        tourtype = categoriesdrop.get(position);
                        if(tourtype.equals("Day Tours")){
                            tourtypevalue = "Day Tours";
                        }
                        else if(tourtype.equals("Tour Packages")){
                            tourtypevalue = "Tour Packages";}
                        else if(tourtype.equals("Cruises, Saling & Water Tours")){
                            tourtypevalue = "Cruises, Saling & Water Tours";
                        }
                        else if(tourtype.equals("Cultural & Theme Tours")){
                            tourtypevalue = "Cultural & Theme Tours";
                        } else if(tourtype.equals("Family Friendly")){
                            tourtypevalue = "Family Friendly";}
                        else if(tourtype.equals("Food Tours")){
                            tourtypevalue = "Food Tours";}
                        else if(tourtype.equals("Holiday & Seasonal Tours")){
                            tourtypevalue = "Holiday & Seasonal Tours";}
                        else if(tourtype.equals("Multi-Day & Extended Tours")){
                            tourtypevalue = "Multi-Day & Extended Tours";
                        }
                        else if(tourtype.equals("Tour Type")){
                            tourtypevalue = "Tour Type";
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });
        return v;
    }
    @Override
    public void onClick(View v) {
        if(v == clearall){
            acategories.add("Sort By");
            acategories.add("Top Selling");
            acategories.add("Rating (High - Low)");
            acategories.add("Rating (Low - High)");
            acategories.add("Price (High - Low)");
            acategories.add("Price (Low - High)");
            emphistory = new emphistoryAdapter(getActivity(), (ArrayList<String>) acategories);
            aspinnerpickup.setAdapter(emphistory);
            aspinnerpickup.setOnItemSelectedListener(
                    new OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                            int position = aspinnerpickup.getSelectedItemPosition();
                            sortby = acategories.get(position);
                            if(sortby.equals("Top Selling")){
                                sellingval = "5";
                            }
                            else if(sortby.equals("Rating (High - Low)")){
                                sellingval = "4";}
                            else if(sortby.equals("Rating (Low - High)")){
                                sellingval = "3";}
                            else if(sortby.equals("Price (High - Low)")){
                                sellingval = "2";}
                            else if(sortby.equals("Price (Low - High)")){
                                sellingval = "1";}
                            else if (sortby.equals("Sort By")){sellingval = "0";
                            }}
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                        }
                    });
            categoriesdrop.add("Tour Type");
            categoriesdrop.add("Day Tours");
            categoriesdrop.add("Tour Packages");
            categoriesdrop.add("Cruises, Saling & Water Tours");
            categoriesdrop.add("Cultural & Theme Tours");
            categoriesdrop.add("Family Friendly");
            categoriesdrop.add("Food Tours");
            categoriesdrop.add("Holiday & Seasonal Tours");
            categoriesdrop.add("Multi-Day & Extended Tours");
            emphistorys = new emphistoryAdapters(getActivity(), (ArrayList<String>) categoriesdrop);
            spinnerdrop.setAdapter(emphistorys);
            spinnerdrop.setOnItemSelectedListener(
                    new OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                            int position = spinnerdrop.getSelectedItemPosition();
                            tourtype = categoriesdrop.get(position);
                            if(tourtype.equals("Day Tours")){
                                tourtypevalue = "Day Tours";
                            }
                            else if(tourtype.equals("Tour Packages")){
                                tourtypevalue = "Tour Packages";}
                            else if(tourtype.equals("Cruises, Saling & Water Tours")){
                                tourtypevalue = "Cruises, Saling & Water Tours";}
                            else if(tourtype.equals("Cultural & Theme Tours")){
                                tourtypevalue = "Cultural & Theme Tours";} else if(tourtype.equals("Family Friendly")){
                                tourtypevalue = "Family Friendly";}
                            else if(tourtype.equals("Food Tours")){
                                tourtypevalue = "Food Tours";}
                            else if(tourtype.equals("Holiday & Seasonal Tours")){
                                tourtypevalue = "Holiday & Seasonal Tours";}
                            else if(tourtype.equals("Multi-Day & Extended Tours")){
                                tourtypevalue = "Multi-Day & Extended Tours";}
                            else if(tourtype.equals("Tour Type")){
                                tourtypevalue = "Tour Type";}
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }});} else if(v == donefilter){
            if(sellingval.equals(acategories.get(0)) && tourtypevalue.equals(categoriesdrop.get(0))){
    Toast.makeText(getActivity().getApplicationContext(), "Select All fields", Toast.LENGTH_SHORT).show();
    return;
} else if(tourtypevalue.equals(categoriesdrop.get(0)))
  {
      Toast.makeText(getActivity().getApplicationContext(), "Select All fields", Toast.LENGTH_SHORT).show();
      return;

  }photosdetails = new ArrayList<Tourdetails>();
            Tourcitydetails();}
    }
    private void Tourcitydetails() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,TOURDETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            tsucess = userObject.getInt("success");
                            if(tsucess == 1) {
                                JSONArray jsonarray1 = userObject.getJSONArray("shorted_datas");
                                for (int i = 0; i < jsonarray1.length(); i++) {
                                    JSONObject obj2 = jsonarray1.getJSONObject(i);
                                    Tourdetails datas = new Tourdetails();
                                    datas.setSno(obj2.getString("sno"));
                                    datas.setId(obj2.getString("id"));
                                    datas.setSub_id(obj2.getString("sub_id"));
                                    datas.setImage(obj2.getString("image_one"));
                                    datas.setImagetwo(obj2.getString("image_two"));
                                    datas.setImagethree(obj2.getString("image_three"));
                                    datas.setImagefour(obj2.getString("image_four"));
                                    datas.setLocation(obj2.getString("location"));
                                    datas.setAddi_info(obj2.getString("addi_info"));
                                    datas.setTour_opt_info(obj2.getString("tour_opt_info"));
                                    datas.setTour_opt_link(obj2.getString("tour_opt_link"));
                                    datas.setCancellation_policy(obj2.getString("cancellation_policy"));
                                    datas.setSelling(obj2.getString("selling_rate"));
                                    datas.setTour_type(obj2.getString("tour_type"));
                                    datas.setRate(obj2.getString("rate"));
                                    datas.setCurrence(obj2.getString("currency"));
                                    datas.setContent(obj2.getString("content"));
                                    datas.setReviews(obj2.getString("reviews"));
                                    datas.setOverviews(obj2.getString("overviews"));
                                    datas.setLong_overviews(obj2.getString("long_overviews"));
                                    datas.setEnquiry(obj2.getString("enquiry"));
                                    datas.setOverview1(obj2.getString("overview_one"));
                                    datas.setOverview2(obj2.getString("overview_two"));
                                    photosdetails.add(datas);
                                    Sessiondata.getInstance().setTourdetails(photosdetails);
                                }
                                progressDialog.dismiss();
                                Fragment fr = new Tourlist();
                                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                                fc.replaceFragment(fr);}
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "No datas's available", Toast.LENGTH_LONG).show();
                                Fragment fr = new Touractivity();
                                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                                fc.replaceFragment(fr);
                            }
                        } catch (Exception ex) {
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
                params.put(KEY_USERID,tosno);
                params.put(KEY_SELLING_RATE,sellingval);
                params.put(KEY_TOURTYPE,tourtypevalue);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }
    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<String> sing_in_datas;
        public emphistoryAdapter(Context con, ArrayList<String> singindatas) {
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
                view = mLayoutInflater.inflate(R.layout.startplacelist, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.startpl);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.name.setText(sing_in_datas.get(position).toString());
            return view;}}
    static class ViewHolder {
        TextView name;
        public CardView cd;
    }
    private class emphistoryAdapters extends BaseAdapter {
        ViewHolders viewHolders;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<String> sing_in_datas;
        public emphistoryAdapters(Context con, ArrayList<String> singindatas) {
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
                view = mLayoutInflater.inflate(R.layout.startplacelist, null);
                viewHolders = new ViewHolders();
                viewHolders.name = (TextView) view.findViewById(R.id.startpl);
                view.setTag(viewHolders);
            } else {
                viewHolders = (ViewHolders) view.getTag();
            }
            final ViewHolders viewHolder = (ViewHolders) view.getTag();
            viewHolder.name.setText(sing_in_datas.get(position).toString());
            return view;}}
    public static class ViewHolders {
        TextView name;
        public CardView cd;
    }
}