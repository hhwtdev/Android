package com.hhwt.travelplanner.activity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.adapter.Base64;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 02/11/16.
 */
public class Newaddlistdetais extends Fragment implements View.OnClickListener{
CheckBox halmeal,seafood,vegtrian,alcoholserved,halalcertified,muslimowned,prayerfacilities,mosquenearby,kosher;
    LinearLayout linearvalbottom;
    TextView apptitle,postlist;
    ImageView backclick;
    private static String file;
    Bitmap bitmap;
    int nlistingstatus;

    String nlistingmsg;

    ProgressDialog progressDialog;
    Spinner listing,subspinnerprayer;
String  listingseleted,listingvaluenice,subfoodanddrinkselected,subfooddrinkvalue,subprayerseleted,subprayervalue,subthingsvalue,userid;
    LinearLayout subcategoryfoodanddrink,subcategoryprayer,subcategorythingstodo;
Spinner subspinnerfoodanddrinks,subspinnerthinstodo;

    String halmealstr,seafoodstr,vegtrianstr,alcoholservedstr,subthingstodoselected,halalcertifiedstr,muslimownedstr,prayerfacilitiesstr,mosquenearbystr,kosherstr;
    private int mYear, mMonth, mDay, mHour, mMinute;
int i;
    RobotoEditText contactnumber,weburl,pricerange,egclosedays,addresslisting,nameofplace,decripplace;

    RobotoTextView openhours,closinghour;
ImageView image1;

    private static final String NEWLISTING = "http://hhwt.tech/hhwt_webservice/addnewplace.php";

String cidval,cidsending,subcategorysending;
    String phonenumber,weburlstring,pricerangestring,egclosestring,addressstring,openhoursstring,closinghourstring,nameofplacestring,decripplacestring;

    String subcatgery2,subcatgery1,category;

    LinearLayout closinghrsval,openhrscval;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.addnewlistsearclist,container,false);

        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);
        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

        subcategoryfoodanddrink = (LinearLayout) v.findViewById(R.id.subcategoryfoodanddrink);
        subcategoryfoodanddrink.setVisibility(View.GONE);

        subcategorythingstodo = (LinearLayout) v.findViewById(R.id.subcategorythingstodo);
        subcategorythingstodo.setVisibility(View.GONE);
        subcategoryprayer = (LinearLayout) v.findViewById(R.id.subcategoryprayer);
        subcategoryprayer.setVisibility(View.GONE);
        halmeal = (CheckBox)v.findViewById(R.id.checkBox1);
        seafood = (CheckBox)v.findViewById(R.id.checkBox2);
        vegtrian = (CheckBox) v.findViewById(R.id.checkBox3);
        alcoholserved = (CheckBox) v.findViewById(R.id.checkBox4);
        halalcertified = (CheckBox) v.findViewById(R.id.checkBox7);

        muslimowned = (CheckBox) v.findViewById(R.id.checkBox8);
        prayerfacilities = (CheckBox) v.findViewById(R.id.checkBox9);
mosquenearby = (CheckBox) v.findViewById(R.id.checkBox5);
        kosher = (CheckBox) v.findViewById(R.id.checkBox6);

        apptitle.setText("New Listing");
        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

        contactnumber = (RobotoEditText) v.findViewById(R.id.contactnumber);
        weburl = (RobotoEditText) v.findViewById(R.id.weburl);
        pricerange = (RobotoEditText) v.findViewById(R.id.pricerange);
        egclosedays = (RobotoEditText) v.findViewById(R.id.egclose);
        addresslisting = (RobotoEditText) v.findViewById(R.id.addresslisting);
        openhours = (RobotoTextView) v.findViewById(R.id.openhours);
        closinghour = (RobotoTextView) v.findViewById(R.id.closinghour);
        nameofplace = (RobotoEditText) v.findViewById(R.id.nature);
        decripplace = (RobotoEditText) v.findViewById(R.id.decripplace);


        openhrscval = (LinearLayout) v.findViewById(R.id.openhrscval);
        closinghrsval = (LinearLayout) v.findViewById(R.id.closinghrsval);

        openhrscval.setOnClickListener(this);
        closinghrsval.setOnClickListener(this);




        image1 = (ImageView) v.findViewById(R.id.guimagelist);

        postlist = (TextView) v.findViewById(R.id.postlist);
        postlist.setOnClickListener(this);
        listing = (Spinner) v.findViewById(R.id.spinner1);


        image1.setOnClickListener(this);
        subspinnerfoodanddrinks = (Spinner) v.findViewById(R.id.subspinnerfoodanddrinks);

        subspinnerprayer = (Spinner) v.findViewById(R.id.subspinnerprayer);


        subspinnerthinstodo = (Spinner) v.findViewById(R.id.subspinnerthinstodo);








        final ArrayList<String> countries = new ArrayList<String>();
        countries.add("Listing Type");
        countries.add("Things To Do");
        countries.add("Food & Drinks");
        countries.add("Prayer Spaces");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, countries);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listing.setAdapter(dataAdapter);
        listing.setSelection(0);
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

                            cidval = "";
                            halmealstr = "";
                            seafoodstr = "";
                            vegtrianstr = "";
                            alcoholservedstr = "";
                            halalcertifiedstr = "";
                            muslimownedstr = "";
                            prayerfacilitiesstr = "";
                            mosquenearbystr = "";
                            kosherstr = "";

                            subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                            subfoodanddrinkselected = "";
                            subprayerseleted = "";
                            subthingstodoselected = "";
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));

                        }

                        else if (listingseleted.equals("Things To Do")){


                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                            subcategoryfoodanddrink.setVisibility(View.GONE);
                            subcategoryprayer.setVisibility(View.GONE);
                            subcategorythingstodo.setVisibility(View.VISIBLE);
cidval = "3";

                            halmealstr = "";
                            seafoodstr = "";
                            vegtrianstr = "";
                            alcoholservedstr = "";
                            halalcertifiedstr = "";
                            muslimownedstr = "";
                            prayerfacilitiesstr = "";

                            mosquenearbystr = "";
                            kosherstr = "";

                            subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;



                            subfoodanddrinkselected = "";
                            subprayerseleted = "";


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

                            ArrayAdapter<String> dataAdapterfoodsub = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_item, thinstodo);
                            dataAdapterfoodsub
                                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            subspinnerthinstodo.setAdapter(dataAdapterfoodsub);
                            subspinnerthinstodo.setSelection(0);
                            subspinnerthinstodo.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {


                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            int positionsfood = subspinnerthinstodo.getSelectedItemPosition();

                                            subthingstodoselected = thinstodo.get(positionsfood);



                                            if(subthingstodoselected.equals("Category")){
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                                            }
                                            else{
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                            }

                                        }


                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {


                                        }});











                        }
                        else if (listingseleted.equals("Food & Drinks")){
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                            subcategoryprayer.setVisibility(View.GONE);
                            subcategoryfoodanddrink.setVisibility(View.VISIBLE);
                            subcategorythingstodo.setVisibility(View.GONE);

                            subprayerseleted = "";
                            subthingstodoselected = "";


                            cidval = "1";


                           /* halmealstr = "Halal meat";
                            seafoodstr = "Seafood";
                            vegtrianstr = "Vegetarian";
                            alcoholservedstr = "Alcohol Served";*/


                            halmealstr = "";
                            seafoodstr = "";
                            vegtrianstr = "";
                            alcoholservedstr = "";
                            halalcertifiedstr = "";
                            muslimownedstr = "";
                            prayerfacilitiesstr = "";
                            mosquenearbystr = "";
                            kosherstr = "";






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

                            ArrayAdapter<String> dataAdapterfoodsub = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_item, foodlist);
                            dataAdapterfoodsub
                                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            subspinnerfoodanddrinks.setAdapter(dataAdapterfoodsub);
                            subspinnerfoodanddrinks.setSelection(0);
                            subspinnerfoodanddrinks.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {


                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            int positionsfood = subspinnerfoodanddrinks.getSelectedItemPosition();

                                            subfoodanddrinkselected = foodlist.get(positionsfood);


                                            if(subfoodanddrinkselected.equals("Category")){
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                                            }
                                            else{
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                            }




                                        }


                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {


                                        }});



                            //First CheckBox
                            halalcertified.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(halalcertified.isChecked())
                                    {
                                        halalcertifiedstr = "Halal certified";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }
                                    else
                                    {
                                        halalcertifiedstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }

                                }
                            });



                            //First CheckBox
                            muslimowned.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(muslimowned.isChecked())
                                    {
                                        muslimownedstr = "Muslim owned";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }
                                    else
                                    {
                                        muslimownedstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }

                                }
                            });





                            //First CheckBox
                            prayerfacilities.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(prayerfacilities.isChecked())
                                    {
                                        prayerfacilitiesstr = "Prayer facilities";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }
                                    else
                                    {
                                        prayerfacilitiesstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }

                                }
                            });





                            //First CheckBox
                            mosquenearby.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(mosquenearby.isChecked())
                                    {
                                        mosquenearbystr = "Mosque nearby";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }
                                    else
                                    {
                                        mosquenearbystr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }

                                }
                            });




                            //First CheckBox
                            kosher.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(kosher.isChecked())
                                    {
                                        kosherstr = "Mosque nearby";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }
                                    else
                                    {
                                        kosherstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }

                                }
                            });






                            //First CheckBox
                            halmeal.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(halmeal.isChecked())
                                    {
                                        halmealstr = "Halal meat";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }
                                    else
                                    {
                                        halmealstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }

                                }
                            });


                            //First CheckBox
                            seafood.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(seafood.isChecked())
                                    {
                                        seafoodstr = "Seafood";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }
                                    else
                                    {
                                        seafoodstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }

                                }
                            });




                            //First CheckBox
                            vegtrian.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(vegtrian.isChecked())
                                    {
                                        vegtrianstr = "Vegetarian";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }
                                    else
                                    {
                                        vegtrianstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }

                                }
                            });



                            //First CheckBox
                            alcoholserved.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(alcoholserved.isChecked())
                                    {
                                        alcoholservedstr = "Alcohol Served";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;

                                    }
                                    else
                                    {
                                        alcoholservedstr="";
                                        subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                                    }

                                }
                            });




                        }
                        else if(listingseleted.equals("Prayer Spaces")) {
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                            subcategoryfoodanddrink.setVisibility(View.GONE);
                            subcategoryprayer.setVisibility(View.VISIBLE);
                            subcategorythingstodo.setVisibility(View.GONE);
                            cidval = "2";



                            halmealstr = "";
                            seafoodstr = "";
                            vegtrianstr = "";
                            alcoholservedstr = "";
                            halalcertifiedstr = "";
                            muslimownedstr = "";
                            prayerfacilitiesstr = "";
                            mosquenearbystr = "";
                            kosherstr = "";

                            subcatgery2 = halmealstr+","+seafoodstr+","+vegtrianstr+","+alcoholservedstr+","+halalcertifiedstr+","+muslimownedstr+","+prayerfacilitiesstr+","+mosquenearbystr+","+kosherstr;
                            subfoodanddrinkselected = "";
                            subthingstodoselected = "";




                            final ArrayList<String> prayer = new ArrayList<String>();
                            prayer.add("Category");
                            prayer.add("Prayer room");
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
                                            if(subprayerseleted.equals("Category")){
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                                            }
                                            else{
                                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                            }


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



        if (v == image1) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }





        else if (v == openhrscval){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            openhours.setText("     "+hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        else if (v == closinghrsval){

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            closinghour.setText("  "+hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }








        else if (v == postlist){



            SharedPreferences preferences= getActivity().getSharedPreferences("tempss", getActivity().MODE_PRIVATE);
            String name=preferences.getString("namess",null);
            userid = name;

           cidsending = cidval;


            subcategorysending =  subcatgery2;


           // Toast.makeText(getActivity(),subcategorysending,Toast.LENGTH_LONG).show();




        //    Toast.makeText(getActivity(),cidsending,Toast.LENGTH_LONG).show();

//             spinner main
           // listingvaluenice = listingseleted;
//


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






//spinnerPrayersubcatogiory
            // subprayervalue   = subprayerseleted;
//spinnerthingstodosubcatogiory
           //  subthingsvalue = subthingstodoselected;


             nameofplacestring = nameofplace.getText().toString();
            decripplacestring = decripplace.getText().toString();
             addressstring = addresslisting.getText().toString();

             openhoursstring = openhours.getText().toString();

             closinghourstring = closinghour.getText().toString();





             phonenumber = contactnumber.getText().toString();
             weburlstring =  weburl.getText().toString();
             pricerangestring = pricerange.getText().toString();
             egclosestring = egclosedays.getText().toString();



            if (nameofplacestring.equals("")){
                //AlertUtils.SnackbarerrorAlert(getActivity(), v, "Tell us the name of this place");
                Toast.makeText(getActivity(),"Tell us the name of this place",Toast.LENGTH_LONG).show();
                return;
            }

             if(listingseleted.equals("Listing Type")){
                // AlertUtils.SnackbarerrorAlert(getActivity(), v, "Choose a Listing Type");
                 Toast.makeText(getActivity(),"Choose a Listing Type",Toast.LENGTH_LONG).show();
                 return;
             }

            if(subcatgery1.equals("Category")){

               // AlertUtils.SnackbarerrorAlert(getActivity(), v, "Insert a category");
                Toast.makeText(getActivity(),"Insert a category",Toast.LENGTH_LONG).show();
                return;
            }


            if(listingseleted.equals("Food & Drinks") && subcategorysending.equals(",,,,,,,,")) {


                //AlertUtils.SnackbarerrorAlert(getActivity(), v, "What is the food classification for his place? (Halal certified etc.)");



                Toast.makeText(getActivity(),"What is the food classification for his place? (Halal certified etc.)",Toast.LENGTH_LONG).show();

                return;
            }





             else {
                 Newlisting();
             }
         }

    }
















    private void Newlisting() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,NEWLISTING,
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
                            Toast.makeText(getActivity(),"Thanks for your contribution! We’ll review your submission", Toast.LENGTH_LONG).show();

                            getFragmentManager().popBackStack();


                        } else if (nlistingstatus == 0) {

                            /*Toast.makeText(getActivity(),userid,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),nameofplacestring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),category,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),subcatgery1,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),decripplacestring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),addressstring,Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(),subcatgery2,Toast.LENGTH_LONG).show();
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
