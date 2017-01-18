package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.hhwt.travelplanner.adapter.ListAdapter;
import com.hhwt.travelplanner.model.Categorylistneibourhood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 21/12/16.
 */
public class Neighbourhoodsnew extends Fragment implements View.OnClickListener{
    public static Activity _A;
ProgressDialog progressDialog;
    ArrayList<Categorylistneibourhood> Categorylistparam;
    int staus;
    String message,tosno;
    ArrayList<Categorylistneibourhood> products = new ArrayList<Categorylistneibourhood>();
    ListAdapter boxAdapter;
ListView lvMain;
private  static final String CITYNEARBOURHOOD = "http://hhwt.tech/hhwt_webservice/get_district.php";
TextView neighbourapplyfilter;
String neifhbouvalue;

    ArrayList<Categorylistneibourhood> productsval = new ArrayList<Categorylistneibourhood>();


    ArrayList<String> boxname;



    CheckBox allneighbourhoodcheck;
String allneigval;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.neighbourhoodnew, container, false);
        _A = getActivity();

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
        lvMain = (ListView) v.findViewById(R.id.lvMain);
        neighbourapplyfilter = (TextView) v.findViewById(R.id.neighbourapplyfilter);
        allneighbourhoodcheck = (CheckBox)v.findViewById(R.id.checkBox1);




        neighbourapplyfilter.setOnClickListener(this);


        boxname = new ArrayList<>();


        tosno = Sessiondata.getInstance().getToursno();
        Categorylistparam = new ArrayList<Categorylistneibourhood>();
        fillData();







        allneighbourhoodcheck.setChecked(true);


        allneighbourhoodcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(allneighbourhoodcheck.isChecked()){

                    //workfull

                    String result = "";
                    boolean first = true;
                    productsval = Sessiondata.getInstance().getCategoryneibourho();

                    for(int i =0;i<productsval.size(); i++){

                        allneigval = productsval.get(i).getDistrict();
                        boxname.add(allneigval);
                    }


                    String frnames[]=boxname.toArray(new String[boxname.size()]);

                    for(String k: frnames)
                    {
                        System.out.println(k);
                        if (first) {
                            result += "'" +k+ "'";
                            first = false;
                        } else {
                            result += ","+"'"+k+"'";
                        }
                    }

                    if (!result.isEmpty()) {
                        neifhbouvalue = result;
                    } else {
                        neifhbouvalue = "' '";
                    }

                    //Toast.makeText(getActivity(),neifhbouvalue, Toast.LENGTH_LONG).show();


                }

                else{

                allneighbourhoodcheck.setChecked(false);
                    String result = "";
                boolean first = true;
                int totalAmount = 0;
                for (Categorylistneibourhood p : boxAdapter.getBox()) {
                    if (p.box) {

                        if (first) {
                            result += "'" + p.getDistrict() + "'";
                            first = false;
                        } else {
                            result += "," + "'" + p.getDistrict() + "'";
                        }


                    }
                }
                    if (!result.isEmpty()) {
                    neifhbouvalue = result;
                } else {
                    neifhbouvalue = "' '";
                }


            }







            }
        });






        return v;

    }












   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tosno = Sessiondata.getInstance().getToursno();
        Categorylistparam = new ArrayList<Categorylistneibourhood>();
        fillData();

    }
*/
    private void fillData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CITYNEARBOURHOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject json = new JSONObject(response);

                     staus = json.getInt("Status");
                      message = json.getString("msg");
                    JSONArray jarray = json.getJSONArray("categorylist");
for(int i =0; i<jarray.length();i++){

    JSONObject cararray = jarray.getJSONObject(i);
    Categorylistneibourhood citypass = new Categorylistneibourhood();
    citypass.setDistrict(cararray.getString("district"));
    Categorylistparam.add(citypass);

    Sessiondata.getInstance().setCategoryneibourho(Categorylistparam);
}

if(staus == 1){
    progressDialog.dismiss();
    //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    products = Sessiondata.getInstance().getCategoryneibourho();
    boxAdapter = new ListAdapter(getActivity(), products);

    lvMain.setAdapter(boxAdapter);
}

                    else {
    progressDialog.dismiss();
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
}



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("cityid",tosno);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }

    public void showResult(View v) {
        String result = "";
        int totalAmount=0;
        for (Categorylistneibourhood p : boxAdapter.getBox()) {
            if (p.box){
                result = p.getDistrict();

            }
        }
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(v == neighbourapplyfilter){


            if(allneighbourhoodcheck.isChecked()){

                //workfull

                String result = "";
                boolean first = true;
                        productsval = Sessiondata.getInstance().getCategoryneibourho();

                for(int i =0;i<productsval.size(); i++){

                    allneigval = productsval.get(i).getDistrict();
                    boxname.add(allneigval);
                }


                String frnames[]=boxname.toArray(new String[boxname.size()]);

                for(String k: frnames)
                {
                    System.out.println(k);
                    if (first) {
                        result += "'" +k+ "'";
                        first = false;
                    } else {
                        result += ","+"'"+k+"'";
                    }
                }

                if (!result.isEmpty()) {
                    neifhbouvalue = result;
                } else {
                    neifhbouvalue = "' '";
                }

                //Toast.makeText(getActivity(),neifhbouvalue, Toast.LENGTH_LONG).show();


            }
            else {


                allneighbourhoodcheck.setChecked(false);


                String result = "";
                boolean first = true;
                int totalAmount = 0;
                for (Categorylistneibourhood p : boxAdapter.getBox()) {
                    if (p.box) {
                  /*  result += "\n" +p.getDistrict();*/

                        //   result += "\n" +p.getDistrict();

                        //result += "'"+p.getDistrict()+"'"+","+"\n";


                        if (first) {
                            result += "'" + p.getDistrict() + "'";
                            first = false;
                        } else {
                            result += "," + "'" + p.getDistrict() + "'";
                        }


                    }
                }


                if (!result.isEmpty()) {
                    neifhbouvalue = result;
                } else {
                    neifhbouvalue = "' '";
                }


            }




            Sessiondata.getInstance().setNeighboorhoodval(neifhbouvalue);

        //    Toast.makeText(getActivity(),   Sessiondata.getInstance().getNeighboorhoodval(), Toast.LENGTH_LONG).show();
            getFragmentManager().popBackStack();

        }


    }
}
