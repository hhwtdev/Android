package com.hhwt.travelplanner.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.adapter.Base64;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
 * Created by jeyavijay on 07/11/16.
 */
public class Suggestesedit extends Fragment implements View.OnClickListener{

    private static final String SUGESTLISTING = "http://www.hhwt.tech/hhwt_webservice/suggestnewplace.php";

    ProgressDialog progressDialog;
    int nlistingstatus;

    String nlistingmsg,userid,cidsending;
    String subcategorysending,subcatgery2;

    TextView sugestlist;
  //  CheckBox halmeal,seafood,vegtrian,alcoholserved;

    Categorylistmodel sug;

    int value = -1;


    int thingsvalue = -1;

    int listingtypevalue = -1;

    LinearLayout linearvalbottom;
    TextView apptitle,postlist;
    ImageView backclick;
    private static String file;
    Bitmap bitmap;
    Spinner listing,subspinnerprayer;
    String  listingseleted,listingvaluenice,subfoodanddrinkselected,subfooddrinkvalue,subprayerseleted,subprayervalue,subthingsvalue;
    LinearLayout subcategoryfoodanddrink,subcategoryprayer,subcategorythingstodo;
    Spinner subspinnerfoodanddrinks,subspinnerthinstodo;

    String halmealstr,seafoodstr,vegtrianstr,alcoholservedstr,subthingstodoselected,fcsting,thigstringsubcatgory,category;

    int i;
    RobotoEditText contactnumber,weburl,pricerange,egclosedays,addresslisting,nameofplace,decripplace,foodclassificationtext;

    ImageView image1;

    String phonenumber,weburlstring,pricerangestring,egclosestring,addressstring,openhoursstring,closinghourstring,nameofplacestring,decripplacestring;


    RobotoEditText openhours,closinghour;

String listingstring,subcatgery1,cidval;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.suggestedit,container,false);






        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Suggest Edits");

        sugestlist = (TextView) v.findViewById(R.id.sugestlist);

        sugestlist.setOnClickListener(this);


        subcategoryfoodanddrink = (LinearLayout) v.findViewById(R.id.subcategoryfoodanddrink);
        subcategoryfoodanddrink.setVisibility(View.GONE);

        subcategorythingstodo = (LinearLayout) v.findViewById(R.id.subcategorythingstodo);
        subcategorythingstodo.setVisibility(View.GONE);
        subcategoryprayer = (LinearLayout) v.findViewById(R.id.subcategoryprayer);
        subcategoryprayer.setVisibility(View.GONE);
      /*  halmeal = (CheckBox)v.findViewById(R.id.checkBox1);
        seafood = (CheckBox)v.findViewById(R.id.checkBox2);
        vegtrian = (CheckBox) v.findViewById(R.id.checkBox3);
        alcoholserved = (CheckBox) v.findViewById(R.id.checkBox4);*/



        sug = Sessiondata.getInstance().getSugesteditlistview();








        contactnumber = (RobotoEditText) v.findViewById(R.id.contactnumber);
        weburl = (RobotoEditText) v.findViewById(R.id.weburl);
        pricerange = (RobotoEditText) v.findViewById(R.id.pricerange);
        egclosedays = (RobotoEditText) v.findViewById(R.id.egclose);
        addresslisting = (RobotoEditText) v.findViewById(R.id.addresslisting);
        openhours = (RobotoEditText) v.findViewById(R.id.openhours);
        closinghour = (RobotoEditText) v.findViewById(R.id.closinghour);
     //   nameofplace = (RobotoEditText) v.findViewById(R.id.nature);
    //    decripplace = (RobotoEditText) v.findViewById(R.id.decripplace);
        foodclassificationtext = (RobotoEditText) v.findViewById(R.id.foodclassificationtext);

        image1 = (ImageView) v.findViewById(R.id.guimagelist);



        listing = (Spinner) v.findViewById(R.id.spinner1);


       // image1.setOnClickListener(this);
        subspinnerfoodanddrinks = (Spinner) v.findViewById(R.id.subspinnerfoodanddrinks);

        subspinnerprayer = (Spinner) v.findViewById(R.id.subspinnerprayer);


        subspinnerthinstodo = (Spinner) v.findViewById(R.id.subspinnerthinstodo);




        if(sug.getCategoryID().equals("1")){

            listingstring = "Food & Drinks";

        }
        else if (sug.getCategoryID().equals("2")){

            listingstring = "Prayer Spaces";
            subcategoryprayer.setVisibility(View.GONE);
        }

        else if (sug.getCategoryID().equals("3")){


            listingstring = "Things To Do";
        }





        final ArrayList<String> countries = new ArrayList<String>();
        countries.add("Listing Type");
        countries.add("Things To Do");
        countries.add("Food & Drinks");
        countries.add("Prayer Spaces");





        if(listingtypevalue == -1){
            countries.add(listingstring);
        }

        for(int lsty=0; lsty < countries.size(); lsty++){

            if(listingstring.equals(countries.get(lsty))){

                listingtypevalue = lsty;
            }
        }


        fcsting = sug.getFoodttags();

        final ArrayList<String> foodlist = new ArrayList<String>();
        foodlist.add("Category");
        foodlist.add("Fusion");
        foodlist.add("Vegetarian");
        foodlist.add("Chinese");
        foodlist.add("Japanese");
        foodlist.add("Korean");
        foodlist.add("Taiwanese");
        foodlist.add("Hong Kong Cuisine");
        foodlist.add("Philippine");
        foodlist.add("Singaporean");


        if(value == -1){
            foodlist.add(fcsting);
        }

        for(int fc=0; fc < foodlist.size(); fc++){

            if(fcsting.equals(foodlist.get(fc))){

                value = fc;
            }
        }




        thigstringsubcatgory = sug.getActivity();



        final ArrayList<String> thinstodo = new ArrayList<String>();
        thinstodo.add("Category");
        thinstodo.add("Amusement Parks");
        thinstodo.add("Boat Tours & Water Sports");
        thinstodo.add("Classes & Workshops");
        thinstodo.add("Fun & Games");
        thinstodo.add("Museums");
        thinstodo.add("Nature & Parks");
        thinstodo.add("Nightlife");
        thinstodo.add("Outdoor Activities");
        thinstodo.add("Sights & Landmarks");


        if(thingsvalue == -1){

            thinstodo.add(thigstringsubcatgory);
        }


for(int thingssubcat = 0; thingssubcat < thinstodo.size(); thingssubcat++){

    if(thigstringsubcatgory.equals(thinstodo.get(thingssubcat))){


        thingsvalue = thingssubcat;

    }
}









        photoss newsItem = sug.getPhotos().get(0);

        if (newsItem.getPhotourl().length() > 0) {
            try {
                final String s = newsItem.getPhotourl();
                Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(image1, new Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                        Picasso.with(getActivity())
                                .load(s)
                                .error(R.drawable.background_default)
                                .placeholder(R.drawable.loading)
                                .into(image1, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


















        foodclassificationtext.setText(sug.getFoodctags());

        addresslisting.setText(sug.getAddress());
        openhours.setText(sug.getOpenhrs());
        closinghour.setText(sug.getClosehrs());
        egclosedays.setText(sug.getTimeremaining());
        pricerange.setText(sug.getPrice()+" "+sug.getPricecurrency());
        contactnumber.setText(sug.getPhone());
        weburl.setText(sug.getWebsite());



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, countries);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listing.setAdapter(dataAdapter);
        listing.setSelection(listingtypevalue);
        listing.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = listing.getSelectedItemPosition();

                        listingseleted = countries.get(position);
                        if (listingseleted.equals("Listing Type")) {
                            subcategoryfoodanddrink.setVisibility(View.GONE);
                            subcategoryprayer.setVisibility(View.GONE);
                            subcategorythingstodo.setVisibility(View.GONE);
                            subfoodanddrinkselected = "";
                            subprayerseleted = "";
                            subthingstodoselected = "";
                            cidval = "";
                            subcatgery2 = "";

                        }

                        else if (listingseleted.equals("Things To Do")){
                            subcategoryfoodanddrink.setVisibility(View.GONE);
                            subcategoryprayer.setVisibility(View.GONE);
                            subcategorythingstodo.setVisibility(View.VISIBLE);

                            subfoodanddrinkselected = "";
                            subprayerseleted = "";

                            cidval = "3";
                            subcatgery2 = "";



                            ArrayAdapter<String> dataAdapterfoodsub = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_item, thinstodo);
                            dataAdapterfoodsub
                                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            subspinnerthinstodo.setAdapter(dataAdapterfoodsub);
                            subspinnerthinstodo.setSelection(thingsvalue);
                            subspinnerthinstodo.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {


                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            int positionsfood = subspinnerthinstodo.getSelectedItemPosition();

                                            subthingstodoselected = thinstodo.get(positionsfood);


                                        }


                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {


                                        }});











                        }
                        else if (listingseleted.equals("Food & Drinks")){
                            subcategoryprayer.setVisibility(View.GONE);
                            subcategoryfoodanddrink.setVisibility(View.VISIBLE);
                            subcategorythingstodo.setVisibility(View.GONE);

                            subprayerseleted = "";
                            subthingstodoselected = "";
                            cidval = "1";


                            subcatgery2 = foodclassificationtext.getText().toString();


                            ArrayAdapter<String> dataAdapterfoodsub = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_item, foodlist);
                            dataAdapterfoodsub
                                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            subspinnerfoodanddrinks.setAdapter(dataAdapterfoodsub);
                            subspinnerfoodanddrinks.setSelection(value);
                            subspinnerfoodanddrinks.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {


                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            int positionsfood = subspinnerfoodanddrinks.getSelectedItemPosition();

                                            subfoodanddrinkselected = foodlist.get(positionsfood);


                                        }


                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {


                                        }});





/*
                            //First CheckBox
                            halmeal.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(halmeal.isChecked())
                                    {
                                        halmealstr = "'Halal meat'";
                                        // foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }
                                    else
                                    {
                                        halmealstr="''";
                                        //   foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }

                                }
                            });


                            //First CheckBox
                            seafood.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(seafood.isChecked())
                                    {
                                        seafoodstr = "'Seafood'";
                                        // foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }
                                    else
                                    {
                                        seafoodstr="''";
                                        //   foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }

                                }
                            });




                            //First CheckBox
                            vegtrian.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(vegtrian.isChecked())
                                    {
                                        vegtrianstr = "'Vegetarian'";
                                        // foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }
                                    else
                                    {
                                        vegtrianstr="''";
                                        //   foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }

                                }
                            });



                            //First CheckBox
                            alcoholserved.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(alcoholserved.isChecked())
                                    {
                                        alcoholservedstr = "'Vegetarian'";
                                        // foodcval = AmusementParks+","+Landmark+","+Museums;
                                        // Toast.makeText(getActivity(),alcoholservedstr,Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        alcoholservedstr="''";
                                        //   foodcval = AmusementParks+","+Landmark+","+Museums;
                                    }

                                }
                            });*/




                        }
                        else if(listingseleted.equals("Prayer Spaces")) {

                            subcategoryfoodanddrink.setVisibility(View.GONE);

                            //PRAYERSPACE SUBCATOGORIES
                            subcategoryprayer.setVisibility(View.GONE);
                            subcategorythingstodo.setVisibility(View.GONE);

                            subfoodanddrinkselected = "";
                            subthingstodoselected = "";

                            cidval = "2";


                            final ArrayList<String> prayer = new ArrayList<String>();
                            prayer.add("Category");
                            prayer.add("Prayer Rooms");
                            prayer.add("Mosque");


                            ArrayAdapter<String> dataAdapterprayersub = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_item, prayer);
                            dataAdapterprayersub
                                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            subspinnerprayer.setAdapter(dataAdapterprayersub);
                            subspinnerprayer.setSelection(0);
                            subspinnerprayer.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {


                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            int positionsfood = subspinnerprayer.getSelectedItemPosition();

                                            subprayerseleted = prayer.get(positionsfood);


                                        }


                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {


                                        }});

                        }
                    }@Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }});








        return v;
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                image1.setImageBitmap(bitmap);
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

        else if (v == sugestlist){

             SharedPreferences preferences= getActivity().getSharedPreferences("tempss", getActivity().MODE_PRIVATE);
             String name=preferences.getString("namess",null);
             userid = name;



             if(listingseleted.equals("Listing Type")){

                 category = "";
             }
             else{
                 category = listingseleted;

             }



             if(subfoodanddrinkselected.equals("")){

             }
             else{
                 subcatgery1   = subfoodanddrinkselected;
             }



             if(subprayerseleted.equals("")){

             }
             else{
                 subcatgery1   = subprayerseleted;
             }

             if(subthingstodoselected.equals("")){

             }
             else{
                 subcatgery1   = subthingstodoselected;
             }


             cidsending = cidval;

             subcategorysending =  subcatgery2;









//             spinner main
           //  listingvaluenice = listingseleted;
             // Toast.makeText(getActivity(),listingvaluenice,Toast.LENGTH_LONG).show();
             //spinnerfoodsubcatogiory
             subfooddrinkvalue   = subfoodanddrinkselected;
//spinnerPrayersubcatogiory
             subprayervalue   = subprayerseleted;
//spinnerthingstodosubcatogiory
             subthingsvalue = subthingstodoselected;


            nameofplacestring = "Suggested edit";
             decripplacestring = "Suggested edit";
             addressstring = addresslisting.getText().toString();
             openhoursstring = openhours.getText().toString();
             closinghourstring = closinghour.getText().toString();

             phonenumber = contactnumber.getText().toString();
             weburlstring =  weburl.getText().toString();
             pricerangestring = pricerange.getText().toString();
             egclosestring = egclosedays.getText().toString();


             Sugestededit();
         }



        else if (v == image1) {

             Intent intent = new Intent(Intent.ACTION_PICK,
                     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(intent, 0);

         }






    }









    private void Sugestededit() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,SUGESTLISTING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            nlistingstatus = userObject.getInt("success");
                            nlistingmsg = userObject.getString("message");
                        } catch (Exception ex) {
                        }
                        if (nlistingstatus == 1) {
                            progressDialog.dismiss();

                            AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                            alert.setTitle("Thanks for helping out!");
                            alert.setMessage("We'll be reviewing your edits for this listing.");
                            alert.setCancelMessage(null);

                            alert.setButton("OKAY", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    //do somthing or dismiss dialog by                dialog.dismiss();
                                    getFragmentManager().popBackStack();

                                }
                            });

                            alert.show();


                        } else if (nlistingstatus == 0) {
/*Toast.makeText(getActivity(),userid,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),nameofplacestring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),category,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),subcatgery1,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),decripplacestring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),addressstring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),subcategorysending,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),openhoursstring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),closinghourstring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),egclosestring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),pricerangestring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),phonenumber,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),weburlstring,Toast.LENGTH_LONG).show();*/

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
                params.put("userid",userid);
                params.put("name",nameofplacestring);
                params.put("location","");
                params.put("category",category);
                params.put("sub_category_1",subcatgery1);
                params.put("description",decripplacestring);
                params.put("address",addressstring);
                params.put("cid",cidsending);
                params.put("sub_category_2",subcategorysending);
                params.put("opening_hours",openhoursstring);
                params.put("closing_hours",closinghourstring);
                params.put("time_remarks",egclosestring);
                params.put("price",pricerangestring);
                params.put("contact_number",phonenumber);
                params.put("website",weburlstring);

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
