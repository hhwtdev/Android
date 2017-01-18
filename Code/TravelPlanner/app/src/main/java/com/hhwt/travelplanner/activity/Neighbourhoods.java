package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhwt.travelplanner.R;
import com.quinny898.library.persistentsearch.SearchBox;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeyavijay on 10/12/16.
 */
public class Neighbourhoods extends Fragment implements View.OnClickListener{

    public static Activity _A;
    ImageView backclick;

    SearchBox search;
    TextView countryapptitle;
    LinearLayout linearvalbottom;
    TextView apptitle;
    ImageView filter,toolbarsearch;

    TextView neighbourapplyfilter;

    String allneighbourhoods;
String eunpeong,dobong,nowon,seongbuk,jongno,seodaemun,mapo,yongsan,jung,seongdong,gangnam,songpa,yeongdeungpo,dongjak,gwangjin,seocho,dongdaemun,guro,geumcheon,gangbuk,cheoin,giheung,kowloon,wanchai,central,tuenmun,islands,southern,eastern,kwuntong,shamshuipo,tsuenwan;
CheckBox allneighbourhoodcheck,eunpeongcheckBoxnb1,dobongcheckBoxnb2,nowoncheckBoxnb3,seongbukcheckBoxnb4,jongnocheckBoxnb5,seodaemuncheckBoxnb6, mapocheckBoxnb7,yongsancheckBoxnb8,jungcheckBoxnb9,seongdongcheckBoxnb10,gangnamcheckBoxnb11,songpacheckBoxnb12,yeongdeungpocheckBoxnb13,dongjakcheckBoxnb14,gwangjincheckBoxnb15,seochocheckBoxnb16,dongdaemuncheckBoxnb17,gurocheckBoxnb18,geumcheoncheckBoxnb19,gangbukcheckBoxnb20,cheoincheckBoxnb21,giheungcheckBoxnb22,kowlooncheckBoxnb23,wanchaicheckBoxnb24,centralcheckBoxnb25,tuenmuncheckBoxnb26,islandscheckBoxnb27,southerncheckBoxnb28,easterncheckBoxnb29,kwuntongcheckBoxnb30,shamshuipocheckBoxnb31,tsuenwancheckBoxnb32;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.neighbourhood,container,false);
        _A = getActivity();



        search = (SearchBox) ((Newnavigationbottom) _A).findViewById(R.id.searchbox);
        search.setVisibility(View.GONE);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);

        filter = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.filter);
        filter.setVisibility(View.GONE);

        toolbarsearch = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.search);
        toolbarsearch.setVisibility(View.GONE);


        countryapptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.countryapptitle);
        countryapptitle.setVisibility(View.GONE);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);
        apptitle.setText("Select Neighbourhoods");

        neighbourapplyfilter = (TextView) v.findViewById(R.id.neighbourapplyfilter);
        neighbourapplyfilter.setOnClickListener(this);

        allneighbourhoodcheck = (CheckBox)v.findViewById(R.id.checkBox1);

        eunpeongcheckBoxnb1 = (CheckBox) v.findViewById(R.id.checkBoxnb1);
        dobongcheckBoxnb2 = (CheckBox) v.findViewById(R.id.checkBoxnb2);
        nowoncheckBoxnb3 = (CheckBox) v.findViewById(R.id.checkBoxnb3);
        seongbukcheckBoxnb4 = (CheckBox) v.findViewById(R.id.checkBoxnb4);
        jongnocheckBoxnb5 = (CheckBox) v.findViewById(R.id.checkBoxnb5);
        seodaemuncheckBoxnb6 = (CheckBox) v.findViewById(R.id.checkBoxnb6);
        mapocheckBoxnb7 = (CheckBox) v.findViewById(R.id.checkBoxnb7);
        yongsancheckBoxnb8 = (CheckBox) v.findViewById(R.id.checkBoxnb8);
        jungcheckBoxnb9 = (CheckBox) v.findViewById(R.id.checkBoxnb9);
        seongdongcheckBoxnb10 = (CheckBox) v.findViewById(R.id.checkBoxnb10);
        gangnamcheckBoxnb11 = (CheckBox) v.findViewById(R.id.checkBoxnb11);
        songpacheckBoxnb12 = (CheckBox) v.findViewById(R.id.checkBoxnb12);
        yeongdeungpocheckBoxnb13 = (CheckBox) v.findViewById(R.id.checkBoxnb13);
        dongjakcheckBoxnb14 = (CheckBox) v.findViewById(R.id.checkBoxnb14);
        gwangjincheckBoxnb15 = (CheckBox) v.findViewById(R.id.checkBoxnb15);
        seochocheckBoxnb16 = (CheckBox)    v.findViewById(R.id.checkBoxnb16);
        dongdaemuncheckBoxnb17 = (CheckBox) v.findViewById(R.id.checkBoxnb17);
        gurocheckBoxnb18 = (CheckBox) v.findViewById(R.id.checkBoxnb18);
        geumcheoncheckBoxnb19 = (CheckBox) v.findViewById(R.id.checkBoxnb19);
        gangbukcheckBoxnb20 = (CheckBox) v.findViewById(R.id.checkBoxnb20);
        cheoincheckBoxnb21 = (CheckBox) v.findViewById(R.id.checkBoxnb21);
        giheungcheckBoxnb22 = (CheckBox) v.findViewById(R.id.checkBoxnb22);
        kowlooncheckBoxnb23 = (CheckBox) v.findViewById(R.id.checkBoxnb23);
        wanchaicheckBoxnb24 = (CheckBox) v.findViewById(R.id.checkBoxnb24);
        centralcheckBoxnb25 = (CheckBox) v.findViewById(R.id.checkBoxnb25);
        tuenmuncheckBoxnb26 = (CheckBox) v.findViewById(R.id.checkBoxnb26);
        islandscheckBoxnb27 = (CheckBox) v.findViewById(R.id.checkBoxnb27);
        southerncheckBoxnb28 = (CheckBox) v.findViewById(R.id.checkBoxnb28);
        easterncheckBoxnb29 = (CheckBox) v.findViewById(R.id.checkBoxnb29);
        kwuntongcheckBoxnb30 = (CheckBox) v.findViewById(R.id.checkBoxnb30);
        shamshuipocheckBoxnb31 = (CheckBox) v.findViewById(R.id.checkBoxnb31);
        tsuenwancheckBoxnb32 = (CheckBox) v.findViewById(R.id.checkBoxnb32);





        eunpeong = "'Eunpeong-gu'";
        dobong = "'Dobong-gu'";
        nowon = "'Nowon-gu'";
        seongbuk = "'Seongbuk-gu'";
        jongno = "'Jongno-gu'";
        seodaemun = "'Seodaemun-gu'";
        mapo = "'Mapo-gu'";
        yongsan = "'Yongsan-gu'";
        jung = "'Jung-gu'";
        seongdong = "'Seongdong-gu'";
        gangnam = "'Gangnam-gu'";
        songpa = "'Songpa-gu'";
        yeongdeungpo = "'Yeongdeungpo-gu'";
        dongjak = "'Dongjak-gu'";
        gwangjin = "'Gwangjin-gu'";
        seocho = "'Seocho-gu'";
        dongdaemun = "'Dongdaemun-gu'";
        guro = "'Guro-gu'";
        geumcheon = "'Geumcheon-gu'";
        gangbuk = "'Gangbuk-gu'";
        cheoin = "'Cheoin-gu'";
        giheung = "'Giheung-gu'";
        kowloon = "'Kowloon City'";
        wanchai = "'Wan Chai'";
        central =  "'Central'";
        tuenmun = "'Tuen Mun'";
        islands = "'Islands'";
        southern = "'Southern'";
        eastern = "'Eastern'";
        kwuntong = "'Kwun Tong'";
        shamshuipo = "'Sham Shui Po'";
        tsuenwan = "'Tsuen Wan'";








allneighbourhoodcheck.setChecked(true);


        allneighbourhoodcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allneighbourhoodcheck.isChecked()){

                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;











                }
                else{
                    allneighbourhoods = "''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''";


                }
            }
        });



        eunpeongcheckBoxnb1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(eunpeongcheckBoxnb1.isChecked())
                {
                    eunpeong = "'Eunpeong-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    eunpeong="''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });






        dobongcheckBoxnb2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(dobongcheckBoxnb2.isChecked())
                {
                    dobong = "'Dobong-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    dobong="''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });


        nowoncheckBoxnb3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(nowoncheckBoxnb3.isChecked())
                {
                    nowon = "'Nowon-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    nowon = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });


        seongbukcheckBoxnb4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seongbukcheckBoxnb4.isChecked())
                {
                    seongbuk = "'Seongbuk-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    seongbuk = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });




        jongnocheckBoxnb5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(jongnocheckBoxnb5.isChecked())
                {
                    jongno = "'Jongno-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    jongno = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });




        seodaemuncheckBoxnb6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seodaemuncheckBoxnb6.isChecked())
                {
                    seodaemun = "'Seodaemun-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    seodaemun = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });




        mapocheckBoxnb7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mapocheckBoxnb7.isChecked())
                {
                    mapo = "'Mapo-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    mapo = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });








        yongsancheckBoxnb8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(yongsancheckBoxnb8.isChecked())
                {
                    yongsan = "'Yongsan-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    yongsan = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });








        jungcheckBoxnb9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(jungcheckBoxnb9.isChecked())
                {
                    jung = "'Jung-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    jung = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });




        seongdongcheckBoxnb10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seongdongcheckBoxnb10.isChecked())
                {
                    seongdong = "'Seongdong-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    seongdong = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });








        gangnamcheckBoxnb11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(gangnamcheckBoxnb11.isChecked())
                {
                    gangnam = "'Gangnam-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    gangnam = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });











        songpacheckBoxnb12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(songpacheckBoxnb12.isChecked())
                {
                    songpa = "'Songpa-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    songpa = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });

        yeongdeungpocheckBoxnb13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(yeongdeungpocheckBoxnb13.isChecked())
                {
                    yeongdeungpo = "'Yeongdeungpo-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    yeongdeungpo = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });

        dongjakcheckBoxnb14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(dongjakcheckBoxnb14.isChecked())
                {
                    dongjak = "'Dongjak-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    dongjak = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });











        gwangjincheckBoxnb15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(gwangjincheckBoxnb15.isChecked())
                {
                    gwangjin = "'Gwangjin-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    gwangjin = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });







        seochocheckBoxnb16.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seochocheckBoxnb16.isChecked())
                {
                    seocho = "'Seocho-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    seocho = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });





        dongdaemuncheckBoxnb17.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(dongdaemuncheckBoxnb17.isChecked())
                {
                    dongdaemun = "'Dongdaemun-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    dongdaemun = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });










        gurocheckBoxnb18.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(gurocheckBoxnb18.isChecked())
                {
                    guro = "'Guro-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    guro = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });







        geumcheoncheckBoxnb19.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(geumcheoncheckBoxnb19.isChecked())
                {
                    geumcheon = "'Geumcheon-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    geumcheon = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });










        gangbukcheckBoxnb20.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(gangbukcheckBoxnb20.isChecked())
                {
                    gangbuk = "'Gangbuk-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    gangbuk = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });





        cheoincheckBoxnb21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cheoincheckBoxnb21.isChecked())
                {
                    cheoin = "'Cheoin-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    cheoin = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });










        giheungcheckBoxnb22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(giheungcheckBoxnb22.isChecked())
                {
                    giheung = "'Giheung-gu'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    giheung = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });








        kowlooncheckBoxnb23.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(kowlooncheckBoxnb23.isChecked())
                {
                    kowloon = "'Kowloon City'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    kowloon = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });








        wanchaicheckBoxnb24.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(wanchaicheckBoxnb24.isChecked())
                {
                    wanchai = "'Wan Chai'";


                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    wanchai = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });
















        centralcheckBoxnb25.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(centralcheckBoxnb25.isChecked())
                {
                    central =  "'Central'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    central =  "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });








        tuenmuncheckBoxnb26.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(tuenmuncheckBoxnb26.isChecked())
                {
                    tuenmun = "'Tuen Mun'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    tuenmun = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });














        islandscheckBoxnb27.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(islandscheckBoxnb27.isChecked())
                {
                    islands = "'Islands'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    islands = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });




        southerncheckBoxnb28.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(southerncheckBoxnb28.isChecked())
                {
                    southern = "'Southern'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    southern = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });









        easterncheckBoxnb29.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(easterncheckBoxnb29.isChecked())
                {
                    eastern = "'Eastern'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    eastern = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });




        kwuntongcheckBoxnb30.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(kwuntongcheckBoxnb30.isChecked())
                {
                    kwuntong = "'Kwun Tong'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    kwuntong = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });





        shamshuipocheckBoxnb31.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(shamshuipocheckBoxnb31.isChecked())
                {
                    shamshuipo = "'Sham Shui Po'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    shamshuipo = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });












        tsuenwancheckBoxnb32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(tsuenwancheckBoxnb32.isChecked())
                {
                    tsuenwan = "'Tsuen Wan'";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }
                else
                {
                    tsuenwan = "''";
                    allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

                }

            }
        });







        //allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan;





        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {


                        eunpeong = "'Eunpeong-gu'";
                        dobong = "'Dobong-gu'";
                        nowon = "'Nowon-gu'";
                        seongbuk = "'Seongbuk-gu'";
                        jongno = "'Jongno-gu'";
                        seodaemun = "'Seodaemun-gu'";
                        mapo = "'Mapo-gu'";
                        yongsan = "'Yongsan-gu'";
                        jung = "'Jung-gu'";
                        seongdong = "'Seongdong-gu'";
                        gangnam = "'Gangnam-gu'";
                        songpa = "'Songpa-gu'";
                        yeongdeungpo = "'Yeongdeungpo-gu'";
                        dongjak = "'Dongjak-gu'";
                        gwangjin = "'Gwangjin-gu'";
                        seocho = "'Seocho-gu'";
                        dongdaemun = "'Dongdaemun-gu'";
                        guro = "'Guro-gu'";
                        geumcheon = "'Geumcheon-gu'";
                        gangbuk = "'Gangbuk-gu'";
                        cheoin = "'Cheoin-gu'";
                        giheung = "'Giheung-gu'";
                        kowloon = "'Kowloon City'";
                        wanchai = "'Wan Chai'";
                        central =  "'Central'";
                        tuenmun = "'Tuen Mun'";
                        islands = "'Islands'";
                        southern = "'Southern'";
                        eastern = "'Eastern'";
                        kwuntong = "'Kwun Tong'";
                        shamshuipo = "'Sham Shui Po'";
                        tsuenwan = "'Tsuen Wan'";



                        allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;





                        Sessiondata.getInstance().setNeighboorhoodval(allneighbourhoods);

                       // Toast.makeText(getActivity(),allneighbourhoods,Toast.LENGTH_LONG).show();

                        getFragmentManager().popBackStack();




                        return true;
                    }
                    return false;
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
        if(v == backclick){


            eunpeong = "'Eunpeong-gu'";
            dobong = "'Dobong-gu'";
            nowon = "'Nowon-gu'";
            seongbuk = "'Seongbuk-gu'";
            jongno = "'Jongno-gu'";
            seodaemun = "'Seodaemun-gu'";
            mapo = "'Mapo-gu'";
            yongsan = "'Yongsan-gu'";
            jung = "'Jung-gu'";
            seongdong = "'Seongdong-gu'";
            gangnam = "'Gangnam-gu'";
            songpa = "'Songpa-gu'";
            yeongdeungpo = "'Yeongdeungpo-gu'";
            dongjak = "'Dongjak-gu'";
            gwangjin = "'Gwangjin-gu'";
            seocho = "'Seocho-gu'";
            dongdaemun = "'Dongdaemun-gu'";
            guro = "'Guro-gu'";
            geumcheon = "'Geumcheon-gu'";
            gangbuk = "'Gangbuk-gu'";
            cheoin = "'Cheoin-gu'";
            giheung = "'Giheung-gu'";
            kowloon = "'Kowloon City'";
            wanchai = "'Wan Chai'";
            central =  "'Central'";
            tuenmun = "'Tuen Mun'";
            islands = "'Islands'";
            southern = "'Southern'";
            eastern = "'Eastern'";
            kwuntong = "'Kwun Tong'";
            shamshuipo = "'Sham Shui Po'";
            tsuenwan = "'Tsuen Wan'";

            allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;

            Sessiondata.getInstance().setNeighboorhoodval(allneighbourhoods);
          //  Toast.makeText(getActivity(),allneighbourhoods,Toast.LENGTH_LONG).show();

            getFragmentManager().popBackStack();

        }

        else if(v == neighbourapplyfilter){

            if(allneighbourhoodcheck.isChecked()){

                eunpeong = "'Eunpeong-gu'";
                dobong = "'Dobong-gu'";
                nowon = "'Nowon-gu'";
                seongbuk = "'Seongbuk-gu'";
                jongno = "'Jongno-gu'";
                seodaemun = "'Seodaemun-gu'";
                mapo = "'Mapo-gu'";
                yongsan = "'Yongsan-gu'";
                jung = "'Jung-gu'";
                seongdong = "'Seongdong-gu'";
                gangnam = "'Gangnam-gu'";
                songpa = "'Songpa-gu'";
                yeongdeungpo = "'Yeongdeungpo-gu'";
                dongjak = "'Dongjak-gu'";
                gwangjin = "'Gwangjin-gu'";
                seocho = "'Seocho-gu'";
                dongdaemun = "'Dongdaemun-gu'";
                guro = "'Guro-gu'";
                geumcheon = "'Geumcheon-gu'";
                gangbuk = "'Gangbuk-gu'";
                cheoin = "'Cheoin-gu'";
                giheung = "'Giheung-gu'";
                kowloon = "'Kowloon City'";
                wanchai = "'Wan Chai'";
                central =  "'Central'";
                tuenmun = "'Tuen Mun'";
                islands = "'Islands'";
                southern = "'Southern'";
                eastern = "'Eastern'";
                kwuntong = "'Kwun Tong'";
                shamshuipo = "'Sham Shui Po'";
                tsuenwan = "'Tsuen Wan'";

                allneighbourhoods = eunpeong+","+dobong+","+nowon+","+seongbuk+","+jongno+","+seodaemun+","+mapo+","+yongsan+","+jung+","+seongdong+","+gangnam+","+songpa+","+yeongdeungpo+","+dongjak+","+gwangjin+","+seocho+","+dongdaemun+","+guro+","+geumcheon+","+gangbuk+","+cheoin+","+giheung+","+kowloon+","+wanchai+","+central+","+tuenmun+","+islands+","+southern+","+eastern+","+kwuntong+","+shamshuipo+","+tsuenwan;


            }
            else {



                allneighbourhoodcheck.setChecked(false);




              /*  allneighbourhoods = "''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''"+","+"''";

            }*/


                if (eunpeongcheckBoxnb1.isChecked()) {
                    eunpeong = "'Eunpeong-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    eunpeong = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (dobongcheckBoxnb2.isChecked()) {
                    dobong = "'Dobong-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    dobong = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (nowoncheckBoxnb3.isChecked()) {
                    nowon = "'Nowon-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    nowon = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (seongbukcheckBoxnb4.isChecked()) {
                    seongbuk = "'Seongbuk-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    seongbuk = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (jongnocheckBoxnb5.isChecked()) {
                    jongno = "'Jongno-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    jongno = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (seodaemuncheckBoxnb6.isChecked()) {
                    seodaemun = "'Seodaemun-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    seodaemun = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (mapocheckBoxnb7.isChecked()) {
                    mapo = "'Mapo-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    mapo = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (yongsancheckBoxnb8.isChecked()) {
                    yongsan = "'Yongsan-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    yongsan = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (jungcheckBoxnb9.isChecked()) {
                    jung = "'Jung-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    jung = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (seongdongcheckBoxnb10.isChecked()) {
                    seongdong = "'Seongdong-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    seongdong = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (gangnamcheckBoxnb11.isChecked()) {
                    gangnam = "'Gangnam-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    gangnam = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (songpacheckBoxnb12.isChecked()) {
                    songpa = "'Songpa-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    songpa = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (yeongdeungpocheckBoxnb13.isChecked()) {
                    yeongdeungpo = "'Yeongdeungpo-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    yeongdeungpo = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (dongjakcheckBoxnb14.isChecked()) {
                    dongjak = "'Dongjak-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    dongjak = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (gwangjincheckBoxnb15.isChecked()) {
                    gwangjin = "'Gwangjin-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    gwangjin = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (seochocheckBoxnb16.isChecked()) {
                    seocho = "'Seocho-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    seocho = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (dongdaemuncheckBoxnb17.isChecked()) {
                    dongdaemun = "'Dongdaemun-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    dongdaemun = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (gurocheckBoxnb18.isChecked()) {
                    guro = "'Guro-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    guro = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (geumcheoncheckBoxnb19.isChecked()) {
                    geumcheon = "'Geumcheon-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    geumcheon = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (gangbukcheckBoxnb20.isChecked()) {
                    gangbuk = "'Gangbuk-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    gangbuk = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (cheoincheckBoxnb21.isChecked()) {
                    cheoin = "'Cheoin-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    cheoin = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (giheungcheckBoxnb22.isChecked()) {
                    giheung = "'Giheung-gu'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    giheung = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (kowlooncheckBoxnb23.isChecked()) {
                    kowloon = "'Kowloon City'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    kowloon = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (wanchaicheckBoxnb24.isChecked()) {
                    wanchai = "'Wan Chai'";


                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    wanchai = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (centralcheckBoxnb25.isChecked()) {
                    central = "'Central'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    central = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (tuenmuncheckBoxnb26.isChecked()) {
                    tuenmun = "'Tuen Mun'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    tuenmun = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (islandscheckBoxnb27.isChecked()) {
                    islands = "'Islands'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    islands = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (southerncheckBoxnb28.isChecked()) {
                    southern = "'Southern'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    southern = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (easterncheckBoxnb29.isChecked()) {
                    eastern = "'Eastern'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    eastern = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (kwuntongcheckBoxnb30.isChecked()) {
                    kwuntong = "'Kwun Tong'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    kwuntong = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (shamshuipocheckBoxnb31.isChecked()) {
                    shamshuipo = "'Sham Shui Po'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    shamshuipo = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }


                if (tsuenwancheckBoxnb32.isChecked()) {
                    tsuenwan = "'Tsuen Wan'";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                } else {
                    tsuenwan = "''";
                    allneighbourhoods = eunpeong + "," + dobong + "," + nowon + "," + seongbuk + "," + jongno + "," + seodaemun + "," + mapo + "," + yongsan + "," + jung + "," + seongdong + "," + gangnam + "," + songpa + "," + yeongdeungpo + "," + dongjak + "," + gwangjin + "," + seocho + "," + dongdaemun + "," + guro + "," + geumcheon + "," + gangbuk + "," + cheoin + "," + giheung + "," + kowloon + "," + wanchai + "," + central + "," + tuenmun + "," + islands + "," + southern + "," + eastern + "," + kwuntong + "," + shamshuipo + "," + tsuenwan;

                }
            }

            Sessiondata.getInstance().setNeighboorhoodval(allneighbourhoods);
            getFragmentManager().popBackStack();

        }

    }
}
