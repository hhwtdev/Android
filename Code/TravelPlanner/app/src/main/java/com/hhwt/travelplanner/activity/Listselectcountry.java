package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jeyavijay on 01/12/16.
 */
public class Listselectcountry extends Activity implements View.OnClickListener{


    String mainname;
    int im;

    ArrayList<String> countries = new ArrayList<String>();
    emphistoryAdapter  mAdapter;
    ListView countrylist;
    ImageView backlogin;
    emphistoryAdapter emphistory;
    ImageView mike;
    ImageView btnSearch, btnLeft;
    EditText mtxt;
    String voiceimage;
    ArrayList<String> mAllData;
    String spnId;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcountryselect);


        countrylist = (ListView) findViewById(R.id.countrylist);
        backlogin = (ImageView) findViewById(R.id.backsignup);

        mAllData = new ArrayList<String>();
        Locale[] locales = Locale.getAvailableLocales();

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

/*

        emphistory = new emphistoryAdapter(Listselectcountry.this, countries);
        countrylist.setAdapter(emphistory);
*/


        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Listselectcountry.this,
                R.layout.simple_spinner_itemcustomcountry, countries);


        countrylist.setAdapter(dataAdapter);


        btnSearch = (ImageView) findViewById(R.id.close);
        btnLeft = (ImageView) findViewById(R.id.btnLeft);
        mike = (ImageView) findViewById(R.id.btnSearch);



        mtxt = (EditText) findViewById(R.id.edSearch);


        voiceimage = mtxt.getText().toString();

        if(voiceimage.equals("")){
            mike.setVisibility(View.VISIBLE);
            btnSearch.setVisibility(View.GONE);

            //  statyicfindplace.setVisibility(View.GONE);
            countrylist.setVisibility(View.VISIBLE);
        }
        else{
            mike.setVisibility(View.GONE);
            btnSearch.setVisibility(View.VISIBLE);
            //statyicfindplace.setVisibility(View.VISIBLE);
            countrylist.setVisibility(View.VISIBLE);
        }




im = 0;









        mtxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


                dataAdapter.getFilter().filter(s.toString());



/*


                ArrayList<String> temp = new ArrayList<String>();
                int textlength = mtxt.getText().length();
                temp.clear();
                for (int i = 0; i < countries.size(); i++)
                {
                    if (textlength <= countries.get(i).length())
                    {
                        if(mtxt.getText().toString().equalsIgnoreCase(
                                (String)
                                        countries.get(i).subSequence(0,
                                                textlength)))
                        {
                            temp.add(countries.get(i));
                        }
                    }
                }




                final ArrayAdapter<String> dataAdapterva = new ArrayAdapter<String>(Listselectcountry.this,
                        R.layout.simple_spinner_itemcustomcountry, temp);

                countrylist.setAdapter(dataAdapterva);
                */
















/*

                for (String string : countries)
                    if (string.toLowerCase().equals(s.toString().toLowerCase())) {
                         pos = countries.indexOf(string);

                    }


                countrylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Sessiondata.getInstance().setCountryselect(countries.get(pos));
                        Intent spinval = new Intent(Listselectcountry.this,Newhhwtwelcomepro.class);
                        startActivity(spinval);
                        finish();



                    }
                });
*/




            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (0 != mtxt.getText().length()) {
                    mike.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);

                    // statyicfindplace.setVisibility(View.VISIBLE);
                    countrylist.setVisibility(View.VISIBLE);
                    spnId = mtxt.getText().toString();

                  //  new Addanewreviews.WebPageTask(mListView,spnId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);





                } else {
                    //  setData();
                    mike.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                    countrylist.setVisibility(View.VISIBLE);


                }
            }
        });





        countrylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String text = (String) countrylist.getItemAtPosition(position);

                //    Sessiondata.getInstance().setCountryselect(countries.get(position));

                Sessiondata.getInstance().setCountryselect(text);


                Intent spinval = new Intent(Listselectcountry.this,Newhhwtwelcomepro.class);
                startActivity(spinval);
                finish();



            }
        });













        btnSearch.setOnClickListener(this);







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



         if (v == btnSearch){
            mtxt.setText("");
            //setData();
        }
    }


    private class emphistoryAdapter extends BaseAdapter {
        Guidlist.ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<String> sing_in_datas;
        private int lastPosition = -1;

        public emphistoryAdapter(Context con, ArrayList<String> singindatas) {
            this.context = con;
            this.sing_in_datas = singindatas;
            mLayoutInflater = (LayoutInflater) Listselectcountry.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                view = mLayoutInflater.inflate(R.layout.simple_spinner_itemcustomcountry, null);
                viewHolder = new Guidlist.ViewHolder();


                viewHolder.name = (RobotoTextView) view.findViewById(R.id.text1);



                view.setTag(viewHolder);
            } else {
                viewHolder = (Guidlist.ViewHolder) view.getTag();
            }
            final Guidlist.ViewHolder viewHolder = (Guidlist.ViewHolder) view.getTag();


            viewHolder.name.setText(sing_in_datas.get(position).toString());

            return view;
        }
    }

    static class ViewHolder {
        RobotoTextView name;

    }

}
