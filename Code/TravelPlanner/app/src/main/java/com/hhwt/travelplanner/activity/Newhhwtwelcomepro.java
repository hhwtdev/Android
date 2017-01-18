package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.widgets.font.RobotoEditText;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jeyavijay on 30/11/16.
 */
public class Newhhwtwelcomepro extends Activity implements View.OnClickListener{

    RobotoTextView nextwelpro,countrywrong,nameworntext,spinnencountry;
    Spinner country;
    ImageView  backlogin;

    RobotoEditText name;

    String countryseleted,countryvaluenice,names;
View nv1,nv0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcomeprofile);
        nv1 = (View) findViewById(R.id.nv4);
        nv0 = (View) findViewById(R.id.nv0);
        backlogin = (ImageView) findViewById(R.id.backsignup);
        nextwelpro = (RobotoTextView)  findViewById(R.id.newxt);
        name = (RobotoEditText) findViewById(R.id.input_rename);


        spinnencountry = (RobotoTextView) findViewById(R.id.spinnencountry);
        spinnencountry.setText(Sessiondata.getInstance().getCountryselect());


        spinnencountry.setOnClickListener(this);


        nextwelpro.setOnClickListener(this);
        countrywrong = (RobotoTextView) findViewById(R.id.countrywrong);
        countrywrong.setVisibility(View.GONE);

        nameworntext = (RobotoTextView) findViewById(R.id.nameworntext);
        nameworntext.setVisibility(View.GONE);




      /*  country = (Spinner) findViewById(R.id.spinner1);
        Locale[] locales = Locale.getAvailableLocales();
        final ArrayList<String> countries = new ArrayList<String>();
        countries.add("Country");
        countries.add("Singapore");
        countries.add("Malaysia");
        countries.add("Brunei");
        countries.add("Indonesia");
        countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("Andorra");
        countries.add("Angola");
        countries.add("Antigua and Barbuda");
        countries.add("Argentina");
        countries.add("Armenia");
        countries.add("Aruba");
        countries.add("Australia");
        countries.add("Austria");
        countries.add("Azerbaijan");
        countries.add("Bahamas, The");
        countries.add("Bahrain");
        countries.add("Bangladesh");
        countries.add("Barbados");
        countries.add("Belarus");
        countries.add("Belgium");
        countries.add("Belize");
        countries.add("Benin");
        countries.add("Bhutan");
        countries.add("Bolivia");
        countries.add("Bosnia and Herzegovina");
        countries.add("Botswana");
        countries.add("Brazil");
        countries.add("Bulgaria");
        countries.add("Burkina Faso");
        countries.add("Burma");
        countries.add("Burundi");
        countries.add("Cambodia");
        countries.add("Cameroon");
        countries.add("Canada");
        countries.add("Cabo Verde");
        countries.add("Central African Republic");
        countries.add("Chad");
        countries.add("Chile");
        countries.add("China");
        countries.add("Colombia");
        countries.add("Comoros");
        countries.add("Congo, Democratic Republic of the");
        countries.add("Congo, Republic of the");
        countries.add("Costa Rica");
        countries.add("Cote d'Ivoire");
        countries.add("Croatia");
        countries.add("Cuba");
        countries.add("Curacao");
        countries.add("Cyprus");
        countries.add("Czech Republic");
        countries.add("Denmark");
        countries.add("Djibouti");
        countries.add("Dominica");
        countries.add("Dominican Republic");
        countries.add("East Timor (see Timor-Leste)");
        countries.add("Ecuador");
        countries.add("Egypt");
        countries.add("El Salvador");
        countries.add("Equatorial Guinea");
        countries.add("Eritrea");
        countries.add("Estonia");
        countries.add("Ethiopia");
        countries.add("Fiji");
        countries.add("Finland");
        countries.add("France");
        countries.add("Gabon");
        countries.add("Gambia, The");
        countries.add("Georgia");
        countries.add("Germany");
        countries.add("Ghana");
        countries.add("Greece");
        countries.add("Grenada");
        countries.add("Guatemala");
        countries.add("Guinea");
        countries.add("Guinea-Bissau");
        countries.add("Guyana");
        countries.add("Haiti");
        countries.add("Holy See");
        countries.add("Honduras");
        countries.add("Hong Kong");
        countries.add("Hungary");
        countries.add("Iceland");
        countries.add("India");
        countries.add("Iran");
        countries.add("Iraq");
        countries.add("Ireland");
        countries.add("Israel");
        countries.add("Italy");
        countries.add("Jamaica");
        countries.add("Japan");
        countries.add("Jordan");
        countries.add("Kazakhstan");
        countries.add("Kenya");
        countries.add("Kiribati");
        countries.add("Korea, North");
        countries.add("Korea, South");
        countries.add("Kosovo");
        countries.add("Kuwait");
        countries.add("Kyrgyzstan");
        countries.add("Laos");
        countries.add("Latvia");
        countries.add("Lebanon");
        countries.add("Lesotho");
        countries.add("Liberia");
        countries.add("Libya");
        countries.add("Liechtenstein");
        countries.add("Lithuania");
        countries.add("Luxembourg");
        countries.add("Macau");
        countries.add("Macedonia");
        countries.add("Madagascar");
        countries.add("Malawi");
        countries.add("Maldives");
        countries.add("Mali");
        countries.add("Malta");
        countries.add("Marshall Islands");
        countries.add("Mauritania");
        countries.add("Mauritius");
        countries.add("Mexico");
        countries.add("Micronesia");
        countries.add("Moldova");
        countries.add("Monaco");
        countries.add("Mongolia");
        countries.add("Montenegro");
        countries.add("Morocco");
        countries.add("Mozambique");
        countries.add("Namibia");
        countries.add("Nauru");
        countries.add("Nepal");
        countries.add("Netherlands");
        countries.add("Netherlands Antilles");
        countries.add("New Zealand");
        countries.add("Nicaragua");
        countries.add("Niger");
        countries.add("Nigeria");
        countries.add("North Korea");
        countries.add("Norway");
        countries.add("Oman");
        countries.add("Pakistan");
        countries.add("Palau");
        countries.add("Palestinian Territories");
        countries.add("Panama");
        countries.add("Papua New Guinea");
        countries.add("Paraguay");
        countries.add("Peru");
        countries.add("Philippines");
        countries.add("Poland");
        countries.add("Portugal");
        countries.add("Qatar");
        countries.add("Romania");
        countries.add("Russia");
        countries.add("Rwanda");
        countries.add("Saint Kitts and Nevis");
        countries.add("Saint Lucia");
        countries.add("Saint Vincent and the Grenadines");
        countries.add("Samoa");
        countries.add("San Marino");
        countries.add("Sao Tome and Principe");
        countries.add("Saudi Arabia");
        countries.add("Senegal");
        countries.add("Serbia");
        countries.add("Seychelles");
        countries.add("Sierra Leone");
        countries.add("Sint Maarten");
        countries.add("Slovakia");
        countries.add("Slovenia");
        countries.add("Solomon Islands");
        countries.add("Somalia");
        countries.add("South Africa");
        countries.add("South Korea");
        countries.add("South Sudan");
        countries.add("Spain ");
        countries.add("Sri Lanka");
        countries.add("Sudan");
        countries.add("Suriname");
        countries.add("Swaziland");
        countries.add("Sweden");
        countries.add("Switzerland");
        countries.add("Syria");
        countries.add("Taiwan");
        countries.add("Tajikistan");
        countries.add("Tanzania");
        countries.add("Thailand");
        countries.add("Timor-Leste");
        countries.add("Togo");
        countries.add("Tonga");
        countries.add("Trinidad and Tobago");
        countries.add("Tunisia");
        countries.add("Turkey");
        countries.add("Turkmenistan");
        countries.add("Tuvalu");
        countries.add("Uganda");
        countries.add("Ukraine");
        countries.add("United Arab Emirates");
        countries.add("United Kingdom");
        countries.add("United State of America");
        countries.add("Uruguay");
        countries.add("Uzbekistan");
        countries.add("Vanuatu");
        countries.add("Venezuela");
        countries.add("Vietnam");
        countries.add("Yemen");
        countries.add("Zambia");
        countries.add("Zimbabwe");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Newhhwtwelcomepro.this,
                R.layout.simple_spinner_itemcustom, countries);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(dataAdapter);
        country.setSelection(0);
        country.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = country.getSelectedItemPosition();

                        countryseleted = countries.get(position);


                        if(countryseleted.equals("Country")){
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.parseColor("#a8a8a8"));
                        }
                        else{
                            ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                        }





                    }@Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }});

*/



        backlogin.findViewById(R.id.backsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });



















    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if(v == nextwelpro){
            nextwelpro.setTextColor(Color.WHITE);
            nextwelpro.setBackgroundResource(R.drawable.newlogincurveblue);


            InputMethodManager passwordS = (InputMethodManager) Newhhwtwelcomepro.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            passwordS.hideSoftInputFromWindow(name.getWindowToken(), 0);



            names = name.getText().toString();
            //countryvaluenice = countryseleted;

            countryvaluenice =  spinnencountry.getText().toString();




            if (null == names || names.equals("")) {
                //  AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Enter name");
                nv1.setBackgroundResource(R.color.viewcolor);
                nv0.setBackgroundResource(R.color.seconpluscolor);
                nameworntext.setVisibility(View.VISIBLE);
                nameworntext.setText("Enter name");
                countrywrong.setVisibility(View.GONE);

                return;}






            if(countryvaluenice.equals("Country")){
                nv1.setBackgroundResource(R.color.seconpluscolor);
                nv0.setBackgroundResource(R.color.viewcolor);
                nameworntext.setVisibility(View.GONE);
                countrywrong.setVisibility(View.VISIBLE);
                countrywrong.setText("Choose your country");

                //AlertUtils.SnackbarerrorAlert(Signupnew.this, v, "Choose your country");



                return;
            }



            Sessiondata.getInstance().setNewregname(names);
            Sessiondata.getInstance().setNewregcountry(countryvaluenice);

            Intent create = new Intent(Newhhwtwelcomepro.this,Createusername.class);
            startActivity(create);


        }


        else if (v == spinnencountry){

            Intent spinval = new Intent(Newhhwtwelcomepro.this,Listselectcountry.class);
            startActivity(spinval);


        }
    }








/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.


            Intent in = new Intent(Newhhwtwelcomepro.this,Signupnew.class);
            startActivity(in);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }*/


}
