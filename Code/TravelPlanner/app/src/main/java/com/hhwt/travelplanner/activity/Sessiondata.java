package com.hhwt.travelplanner.activity;

import android.graphics.Bitmap;

import com.hhwt.travelplanner.model.Categorylist;
import com.hhwt.travelplanner.model.Categorylistmodel;
import com.hhwt.travelplanner.model.Categorylistmodelmytrip;
import com.hhwt.travelplanner.model.Categorylistneibourhood;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.model.Imageval;
import com.hhwt.travelplanner.model.NearByelementsModel;
import com.hhwt.travelplanner.model.categorylistvalues;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeyavijay on 21/03/16.
 */
public class Sessiondata {
    private static Sessiondata myObj;

    public int getMtrip() {
        return mtrip;
    }

    public void setMtrip(int mtrip) {
        this.mtrip = mtrip;
    }

    private  int mtrip;

    public int getToolvalnewui() {
        return toolvalnewui;
    }

    public void setToolvalnewui(int toolvalnewui) {
        this.toolvalnewui = toolvalnewui;
    }

    private  int toolvalnewui;


    public String getDataelementtripis() {
        return dataelementtripis;
    }

    public void setDataelementtripis(String dataelementtripis) {
        this.dataelementtripis = dataelementtripis;
    }

    private  String dataelementtripis;

    public String getNeighboorhoodval() {
        return neighboorhoodval;
    }

    public void setNeighboorhoodval(String neighboorhoodval) {
        this.neighboorhoodval = neighboorhoodval;
    }

    private  String neighboorhoodval;




    public String getCountryselect() {
        return countryselect;
    }

    public void setCountryselect(String countryselect) {
        this.countryselect = countryselect;
    }

    private  String countryselect;


    public String getNewregemail() {
        return newregemail;
    }

    public void setNewregemail(String newregemail) {
        this.newregemail = newregemail;
    }

    private  String newregemail;

    public String getNewregpass() {
        return newregpass;
    }

    public void setNewregpass(String newregpass) {
        this.newregpass = newregpass;
    }

    private  String newregpass;

    public String getNewregname() {
        return newregname;
    }

    public void setNewregname(String newregname) {
        this.newregname = newregname;
    }

    private  String newregname;


    public String getNewregcountry() {
        return newregcountry;
    }

    public void setNewregcountry(String newregcountry) {
        this.newregcountry = newregcountry;
    }

    private  String newregcountry;




    public int getScroolthings() {
        return scroolthings;
    }

    public void setScroolthings(int scroolthings) {
        this.scroolthings = scroolthings;
    }

    private  int scroolthings;

    public int getScroolfood() {
        return scroolfood;
    }

    public void setScroolfood(int scroolfood) {
        this.scroolfood = scroolfood;
    }

    private  int scroolfood;

    public String getCitytitle() {
        return citytitle;
    }

    public void setCitytitle(String citytitle) {
        this.citytitle = citytitle;
    }

    private  String citytitle;


    public String getCitydetails() {
        return citydetails;
    }

    public void setCitydetails(String citydetails) {
        this.citydetails = citydetails;
    }

    private  String citydetails;


    public List<Categorylistmodel> getReviewsearchresult() {
        return reviewsearchresult;
    }

    public void setReviewsearchresult(List<Categorylistmodel> reviewsearchresult) {
        this.reviewsearchresult = reviewsearchresult;
    }

    private  List<Categorylistmodel> reviewsearchresult;


    public String getNewreviewsname() {
        return newreviewsname;
    }

    public void setNewreviewsname(String newreviewsname) {
        this.newreviewsname = newreviewsname;
    }

    private  String newreviewsname;

    public String getReviewfbemailid() {
        return reviewfbemailid;
    }

    public void setReviewfbemailid(String reviewfbemailid) {
        this.reviewfbemailid = reviewfbemailid;
    }

    private  String reviewfbemailid;



    public String getReviewdataelementsno() {
        return reviewdataelementsno;
    }

    public void setReviewdataelementsno(String reviewdataelementsno) {
        this.reviewdataelementsno = reviewdataelementsno;
    }

    private  String reviewdataelementsno;



    public int getExplorenewfullpage() {
        return explorenewfullpage;
    }

    public void setExplorenewfullpage(int explorenewfullpage) {
        this.explorenewfullpage = explorenewfullpage;
    }

    private  int explorenewfullpage;

    public int getBackfilter() {
        return backfilter;
    }

    public void setBackfilter(int backfilter) {
        this.backfilter = backfilter;
    }

    private  int backfilter;

    public int getTripeditupdate() {
        return tripeditupdate;
    }

    public void setTripeditupdate(int tripeditupdate) {
        this.tripeditupdate = tripeditupdate;
    }

    private  int tripeditupdate;


    public Categorylistmodel getSugesteditlistview() {
        return sugesteditlistview;
    }

    public void setSugesteditlistview(Categorylistmodel sugesteditlistview) {
        this.sugesteditlistview = sugesteditlistview;
    }

    private  Categorylistmodel sugesteditlistview;









    public String getNewuinearbyfoodcount() {
        return newuinearbyfoodcount;
    }

    public void setNewuinearbyfoodcount(String newuinearbyfoodcount) {
        this.newuinearbyfoodcount = newuinearbyfoodcount;
    }

    private  String newuinearbyfoodcount;


    public String getFoodc() {
        return foodc;
    }

    public void setFoodc(String foodc) {
        this.foodc = foodc;
    }

    private  String foodc;

    public String getPrayerc() {
        return prayerc;
    }

    public void setPrayerc(String prayerc) {
        this.prayerc = prayerc;
    }

    private  String prayerc;

    public String getNewuinearbythingstodocount() {
        return newuinearbythingstodocount;
    }

    public void setNewuinearbythingstodocount(String newuinearbythingstodocount) {
        this.newuinearbythingstodocount = newuinearbythingstodocount;
    }

    private  String newuinearbythingstodocount;


    public String getNewuinearbyprayerscount() {
        return newuinearbyprayerscount;
    }

    public void setNewuinearbyprayerscount(String newuinearbyprayerscount) {
        this.newuinearbyprayerscount = newuinearbyprayerscount;
    }

    private  String newuinearbyprayerscount;


    public ArrayList<Categorylistmodel> getPrayerfrontdatanear() {
        return prayerfrontdatanear;
    }

    public void setPrayerfrontdatanear(ArrayList<Categorylistmodel> prayerfrontdatanear) {
        this.prayerfrontdatanear = prayerfrontdatanear;
    }

    private ArrayList<Categorylistmodel> prayerfrontdatanear;


    public ArrayList<Categorylistmodel> getFoodfrontdatanear() {
        return foodfrontdatanear;
    }

    public void setFoodfrontdatanear(ArrayList<Categorylistmodel> foodfrontdatanear) {
        this.foodfrontdatanear = foodfrontdatanear;
    }

    private ArrayList<Categorylistmodel> foodfrontdatanear;


    public ArrayList<Categorylist> getSimplevalueend() {
        return simplevalueend;
    }

    public void setSimplevalueend(ArrayList<Categorylist> simplevalueend) {
        this.simplevalueend = simplevalueend;
    }

    private ArrayList<Categorylist> simplevalueend;












    public ArrayList<Categorylistmodel> getThingsfrontdatanear() {
        return thingsfrontdatanear;
    }

    public void setThingsfrontdatanear(ArrayList<Categorylistmodel> thingsfrontdatanear) {
        this.thingsfrontdatanear = thingsfrontdatanear;
    }

    private ArrayList<Categorylistmodel> thingsfrontdatanear;





    public String getDeppoint() {
        return deppoint;
    }

    public void setDeppoint(String deppoint) {
        this.deppoint = deppoint;
    }

    private String deppoint;

    public int getFtplistval() {
        return ftplistval;
    }

    public void setFtplistval(int ftplistval) {
        this.ftplistval = ftplistval;
    }

    private  int  ftplistval;


    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    private String cityname;


    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    private String countryname;

    public CreatedTripModel getReviewstrip() {
        return reviewstrip;
    }

    public void setReviewstrip(CreatedTripModel reviewstrip) {
        this.reviewstrip = reviewstrip;
    }

    private CreatedTripModel reviewstrip;



    public CreatedTripModel getCtripresult() {
        return ctripresult;
    }

    public void setCtripresult(CreatedTripModel ctripresult) {
        this.ctripresult = ctripresult;
    }

    private CreatedTripModel ctripresult;

    public String getNotesimgfull() {
        return notesimgfull;
    }

    public void setNotesimgfull(String notesimgfull) {
        this.notesimgfull = notesimgfull;
    }

    private String notesimgfull;
    public String getGuideledataid() {
        return guideledataid;
    }

    public void setGuideledataid(String guideledataid) {
        this.guideledataid = guideledataid;
    }

    private String guideledataid;

    public String getSnocreatenewtrip() {
        return snocreatenewtrip;
    }

    public void setSnocreatenewtrip(String snocreatenewtrip) {
        this.snocreatenewtrip = snocreatenewtrip;
    }

    private String snocreatenewtrip;


    public CreatedTripModel getCreatedatelist() {
        return createdatelist;
    }

    public void setCreatedatelist(CreatedTripModel createdatelist) {
        this.createdatelist = createdatelist;
    }

    private CreatedTripModel createdatelist;



    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    private String loginname;

    public String getLoginemail() {
        return loginemail;
    }

    public void setLoginemail(String loginemail) {
        this.loginemail = loginemail;
    }

    private String loginemail;


    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }

    private String loginpassword;



    public String getLoginpicture() {
        return loginpicture;
    }

    public void setLoginpicture(String loginpicture) {
        this.loginpicture = loginpicture;
    }

    private String loginpicture;

    public String getLoginfbid() {
        return loginfbid;
    }

    public void setLoginfbid(String loginfbid) {
        this.loginfbid = loginfbid;
    }

    private String loginfbid;

    public String getLogindob() {
        return logindob;
    }

    public void setLogindob(String logindob) {
        this.logindob = logindob;
    }

    private String logindob;

    public String getLoginphonenumber() {
        return loginphonenumber;
    }

    public void setLoginphonenumber(String loginphonenumber) {
        this.loginphonenumber = loginphonenumber;
    }

    private String loginphonenumber;

    public String getLogincountry() {
        return logincountry;
    }

    public void setLogincountry(String logincountry) {
        this.logincountry = logincountry;
    }

    private String logincountry;

    public String getEmid() {
        return emid;
    }

    public void setEmid(String emid) {
        this.emid = emid;
    }

    private String emid;

    public String getEmisubid() {
        return emisubid;
    }

    public void setEmisubid(String emisubid) {
        this.emisubid = emisubid;
    }

    private String emisubid;

    public String getEmdate() {
        return emdate;
    }

    public void setEmdate(String emdate) {
        this.emdate = emdate;
    }

    private String emdate;

    public String getEmadult() {
        return emadult;
    }

    public void setEmadult(String emadult) {
        this.emadult = emadult;
    }

    private String emadult;

    public String getEmchild() {
        return emchild;
    }

    public void setEmchild(String emchild) {
        this.emchild = emchild;
    }

    private String emchild;

    public String getEminfant() {
        return eminfant;
    }

    public void setEminfant(String eminfant) {
        this.eminfant = eminfant;
    }

    private String eminfant;

    public String getEmhome_country() {
        return emhome_country;
    }

    public void setEmhome_country(String emhome_country) {
        this.emhome_country = emhome_country;
    }

    private String emhome_country;

    public String getEmphone_number() {
        return emphone_number;
    }

    public void setEmphone_number(String emphone_number) {
        this.emphone_number = emphone_number;
    }

    private String emphone_number;

    public String getEmemail_id() {
        return ememail_id;
    }

    public void setEmemail_id(String ememail_id) {
        this.ememail_id = ememail_id;
    }

    private String ememail_id;

    public String getEmtourtitle() {
        return emtourtitle;
    }

    public void setEmtourtitle(String emtourtitle) {
        this.emtourtitle = emtourtitle;
    }

    private String emtourtitle;



    public String getReturndetails() {
        return returndetails;
    }

    public void setReturndetails(String returndetails) {
        this.returndetails = returndetails;
    }

    private String returndetails;
    public String getDepdate() {
        return depdate;
    }

    public void setDepdate(String depdate) {
        this.depdate = depdate;
    }

    private String depdate;

    public String getDeptime() {
        return deptime;
    }

    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    private String deptime;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private String duration;


    public String getWebidchange() {
        return webidchange;
    }

    public void setWebidchange(String webidchange) {
        this.webidchange = webidchange;
    }

    private String webidchange;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    private String website;


    public String getTourimage() {
        return tourimage;
    }

    public void setTourimage(String tourimage) {
        this.tourimage = tourimage;
    }

    private String tourimage;

    public String getTouroperatorlink() {
        return touroperatorlink;
    }

    public void setTouroperatorlink(String touroperatorlink) {
        this.touroperatorlink = touroperatorlink;
    }

    private String touroperatorlink;


    public String getTouroperatorlinkurl() {
        return touroperatorlinkurl;
    }

    public void setTouroperatorlinkurl(String touroperatorlinkurl) {
        this.touroperatorlinkurl = touroperatorlinkurl;
    }

    private String touroperatorlinkurl;


    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    private String tourid;


    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    private String locationname;

    public String getCancelceon() {
        return cancelceon;
    }

    public void setCancelceon(String cancelceon) {
        this.cancelceon = cancelceon;
    }

    private String cancelceon;

    public String getAddtionalcon() {
        return addtionalcon;
    }

    public void setAddtionalcon(String addtionalcon) {
        this.addtionalcon = addtionalcon;
    }

    private String addtionalcon;



    public String getOve1() {
        return ove1;
    }

    public void setOve1(String ove1) {
        this.ove1 = ove1;
    }

    private String ove1;

    public String getOver2() {
        return over2;
    }

    public void setOver2(String over2) {
        this.over2 = over2;
    }

    private String over2;


    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    private String exclusions;

    public String getToursubid() {
        return toursubid;
    }

    public void setToursubid(String toursubid) {
        this.toursubid = toursubid;
    }

    private String toursubid;


    public String getTourrate() {
        return tourrate;
    }

    public void setTourrate(String tourrate) {
        this.tourrate = tourrate;
    }

    private String tourrate;

    public String getTourcuren() {
        return tourcuren;
    }

    public void setTourcuren(String tourcuren) {
        this.tourcuren = tourcuren;
    }

    private String tourcuren;
    public String getTourreview() {
        return tourreview;
    }

    public void setTourreview(String tourreview) {
        this.tourreview = tourreview;
    }

    private String tourreview;

    public String getTourcontent() {
        return tourcontent;
    }

    public void setTourcontent(String tourcontent) {
        this.tourcontent = tourcontent;
    }

    private String tourcontent;

    public String getTourOverviews() {
        return tourOverviews;
    }

    public void setTourOverviews(String tourOverviews) {
        this.tourOverviews = tourOverviews;
    }

    private String tourOverviews;

    public String getTourloOverviews() {
        return tourloOverviews;
    }

    public void setTourloOverviews(String tourloOverviews) {
        this.tourloOverviews = tourloOverviews;
    }

    private String tourloOverviews;

    public String getTourenqiry() {
        return tourenqiry;
    }

    public void setTourenqiry(String tourenqiry) {
        this.tourenqiry = tourenqiry;
    }

    private String tourenqiry;

    public String getToursno() {
        return toursno;
    }

    public void setToursno(String toursno) {
        this.toursno = toursno;
    }

    private String toursno;


    public ArrayList<Categorylistmodelmytrip> getNotesno() {
        return notesno;
    }

    public void setNotesno(ArrayList<Categorylistmodelmytrip> notesno) {
        this.notesno = notesno;
    }

    private ArrayList<Categorylistmodelmytrip> notesno;
    public String getNotesimage() {
        return notesimage;
    }

    public void setNotesimage(String notesimage) {
        this.notesimage = notesimage;
    }

    private  String notesimage;


    public String getSnonotes() {
        return snonotes;
    }

    public void setSnonotes(String snonotes) {
        this.snonotes = snonotes;
    }

    private  String snonotes;

    public int getMapimage() {
        return mapimage;
    }

    public void setMapimage(int mapimage) {
        this.mapimage = mapimage;
    }

    private  int mapimage;
    public int getSavetrip() {
        return savetrip;
    }

    public void setSavetrip(int savetrip) {
        this.savetrip = savetrip;
    }

    private  int savetrip;
    public String getMaincategory() {
        return maincategory;
    }

    public void setMaincategory(String maincategory) {
        this.maincategory = maincategory;
    }

    private String maincategory;

    public String getSubcategreyvalues() {
        return subcategreyvalues;
    }

    public void setSubcategreyvalues(String subcategreyvalues) {
        this.subcategreyvalues = subcategreyvalues;
    }

    private String subcategreyvalues;

    public int getSuvalue() {
        return suvalue;
    }

    public void setSuvalue(int suvalue) {
        this.suvalue = suvalue;
    }

    private int suvalue;



    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname;
    }

    private  String addname;

    public String getAdddescription() {
        return adddescription;
    }

    public void setAdddescription(String adddescription) {
        this.adddescription = adddescription;
    }

    private  String adddescription;

    public int getNearstaus() {
        return nearstaus;
    }

    public void setNearstaus(int nearstaus) {
        this.nearstaus = nearstaus;
    }

    int nearstaus;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String firstname;


    public String getGuiditemclickguid() {
        return guiditemclickguid;
    }

    public void setGuiditemclickguid(String guiditemclickguid) {
        this.guiditemclickguid = guiditemclickguid;
    }

    private String guiditemclickguid;

    public String getGuiditemclicktips() {
        return guiditemclicktips;
    }

    public void setGuiditemclicktips(String guiditemclicktips) {
        this.guiditemclicktips = guiditemclicktips;
    }

    private String guiditemclicktips;


    public String getGuiditemclickimagesmain() {
        return guiditemclickimagesmain;
    }

    public void setGuiditemclickimagesmain(String guiditemclickimagesmain) {
        this.guiditemclickimagesmain = guiditemclickimagesmain;
    }

    private String guiditemclickimagesmain;

    public String getGuiditemcimage() {
        return guiditemcimage;
    }

    public void setGuiditemcimage(String guiditemcimage) {
        this.guiditemcimage = guiditemcimage;
    }

    private String guiditemcimage;

    public Bitmap getMapmarkerview() {
        return mapmarkerview;
    }

    public void setMapmarkerview(Bitmap mapmarkerview) {
        this.mapmarkerview = mapmarkerview;
    }

    private  Bitmap mapmarkerview;


    public String getGuiditemclickdescription() {
        return guiditemclickdescription;
    }

    public void setGuiditemclickdescription(String guiditemclickdescription) {
        this.guiditemclickdescription = guiditemclickdescription;
    }

    private String guiditemclickdescription;

    public String getMapclicksno() {
        return mapclicksno;
    }

    public void setMapclicksno(String mapclicksno) {
        this.mapclicksno = mapclicksno;
    }

    private String mapclicksno;

    public String getMapclickname() {
        return mapclickname;
    }

    public void setMapclickname(String mapclickname) {
        this.mapclickname = mapclickname;
    }

    private String mapclickname;

    public String getGuidimgurl() {
        return guidimgurl;
    }

    public void setGuidimgurl(String guidimgurl) {
        this.guidimgurl = guidimgurl;
    }

    private String guidimgurl;





    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private  String lastname;

    public String getDescriptionmapclick() {
        return descriptionmapclick;
    }

    public void setDescriptionmapclick(String descriptionmapclick) {
        this.descriptionmapclick = descriptionmapclick;
    }

    private String descriptionmapclick;


    public String getLatitudemapclick() {
        return latitudemapclick;
    }

    public void setLatitudemapclick(String latitudemapclick) {
        this.latitudemapclick = latitudemapclick;
    }

    private String latitudemapclick;


    public String getLongitudeemapclick() {
        return longitudeemapclick;
    }

    public void setLongitudeemapclick(String longitudeemapclick) {
        this.longitudeemapclick = longitudeemapclick;
    }

    private String longitudeemapclick;


    public String getPhonemapclick() {
        return phonemapclick;
    }

    public void setPhonemapclick(String phonemapclick) {
        this.phonemapclick = phonemapclick;
    }

    public String getCloshrsmapclick() {
        return closhrsmapclick;
    }

    public void setCloshrsmapclick(String closhrsmapclick) {
        this.closhrsmapclick = closhrsmapclick;
    }

    private String closhrsmapclick;

    public String getPhotoidmapclick() {
        return photoidmapclick;
    }

    public void setPhotoidmapclick(String photoidmapclick) {
        this.photoidmapclick = photoidmapclick;
    }

    private String photoidmapclick;

    public String getOpenhrsmapclick() {
        return openhrsmapclick;
    }


    public String getTagmapclick() {
        return tagmapclick;
    }


    public String getPhotourlmapclick() {
        return photourlmapclick;
    }

    public void setPhotourlmapclick(String photourlmapclick) {
        this.photourlmapclick = photourlmapclick;
    }

    private String photourlmapclick;

    public void setTagmapclick(String tagmapclick) {
        this.tagmapclick = tagmapclick;
    }

    private String tagmapclick;


    public CreatedTripModel getCtmvalue() {
        return ctmvalue;
    }

    public CreatedTripModel setCtmvalue(CreatedTripModel ctmvalue) {
        this.ctmvalue = ctmvalue;
        return ctmvalue;
    }

    public CreatedTripModel ctmvalue;

    public void setOpenhrsmapclick(String openhrsmapclick) {
        this.openhrsmapclick = openhrsmapclick;
    }

    private String openhrsmapclick;
    private String phonemapclick;

    public String getWebsitemapclick() {
        return websitemapclick;
    }

    public void setWebsitemapclick(String websitemapclick) {
        this.websitemapclick = websitemapclick;
    }

    private String websitemapclick;

    public String getCitymapclick() {
        return citymapclick;
    }

    public void setCitymapclick(String citymapclick) {
        this.citymapclick = citymapclick;
    }

    private String citymapclick;

    public String getPostalclickmapclick() {
        return postalclickmapclick;
    }

    public void setPostalclickmapclick(String postalclickmapclick) {
        this.postalclickmapclick = postalclickmapclick;
    }

    private String postalclickmapclick;

    public String getMapclickaddress() {
        return mapclickaddress;
    }

    public void setMapclickaddress(String mapclickaddress) {
        this.mapclickaddress = mapclickaddress;
    }

    private String mapclickaddress;

    public String getCategoryIDmapclick() {
        return CategoryIDmapclick;
    }

    public void setCategoryIDmapclick(String categoryIDmapclick) {
        CategoryIDmapclick = categoryIDmapclick;
    }

    public String getNamemapclick() {
        return namemapclick;
    }

    public void setNamemapclick(String namemapclick) {
        this.namemapclick = namemapclick;
    }

    private String namemapclick;

    private String CategoryIDmapclick;
    public String getMapratingclick() {
        return mapratingclick;
    }

    public void setMapratingclick(String mapratingclick) {
        this.mapratingclick = mapratingclick;
    }

    private String mapratingclick;

    public String getSnomapvalue() {
        return snomapvalue;
    }

    public void setSnomapvalue(String snomapvalue) {
        this.snomapvalue = snomapvalue;
    }

    private String snomapvalue;



    public String getSnoid() {
        return snoid;
    }

    public void setSnoid(String snoid) {
        this.snoid = snoid;
    }

    private String snoid;

    public String getAlreadynotes() {
        return alreadynotes;
    }

    public void setAlreadynotes(String alreadynotes) {
        this.alreadynotes = alreadynotes;
    }

    private String alreadynotes;

    public Bitmap getFbimage() {
        return fbimage;
    }

    public void setFbimage(Bitmap fbimage) {
        this.fbimage = fbimage;
    }

    private  Bitmap fbimage;


    public Bitmap getGuidimage() {
        return guidimage;
    }

    public void setGuidimage(Bitmap guidimage) {
        this.guidimage = guidimage;
    }

    private  Bitmap guidimage;
    public String getFbprofilename() {
        return fbprofilename;
    }

    public void setFbprofilename(String fbprofilename) {
        this.fbprofilename = fbprofilename;
    }

    private  String fbprofilename;
    public String getRatingmapoc() {
        return Ratingmapoc;
    }

    public void setRatingmapoc(String ratingmapoc) {
        Ratingmapoc = ratingmapoc;
    }

    private String Ratingmapoc;

    public int getFrameadd() {
        return frameadd;
    }

    public void setFrameadd(int frameadd) {
        this.frameadd = frameadd;
    }

    public int getUpdateresult() {
        return updateresult;
    }

    public void setUpdateresult(int updateresult) {
        this.updateresult = updateresult;
    }

    private int updateresult;
    private int frameadd;
    private Sessiondata(){

    }


    public static Sessiondata getInstance(){
        if(myObj == null){
            myObj = new Sessiondata();
        }
        return myObj;
    }


    public ArrayList<Profiledetails> getProfilelist() {
        return profilelist;
    }

    public void setProfilelist(ArrayList<Profiledetails> profilelist) {
        this.profilelist = profilelist;
    }

    private ArrayList<Profiledetails> profilelist;


    public ArrayList<foodclassification> getFoodclassificationdetails() {
        return foodclassificationdetails;
    }

    public void setFoodclassificationdetails(ArrayList<foodclassification> foodclassificationdetails) {
        this.foodclassificationdetails = foodclassificationdetails;
    }

    private ArrayList<foodclassification> foodclassificationdetails;


    public ArrayList<com.hhwt.travelplanner.model.foodtype> getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(ArrayList<com.hhwt.travelplanner.model.foodtype> foodtype) {
        this.foodtype = foodtype;
    }

    private ArrayList<com.hhwt.travelplanner.model.foodtype> foodtype;


    public ArrayList<NearByelementsModel> getNearByelementsModeldetails() {
        return NearByelementsModeldetails;
    }

    public void setNearByelementsModeldetails(ArrayList<NearByelementsModel> nearByelementsModeldetails) {
        NearByelementsModeldetails = nearByelementsModeldetails;
    }

    private ArrayList<NearByelementsModel> NearByelementsModeldetails;


    public ArrayList<categorylistvalues> getCategorylistmodeldetails() {
        return Categorylistmodeldetails;
    }

    public void setCategorylistmodeldetails(ArrayList<categorylistvalues> categorylistmodeldetails) {
        Categorylistmodeldetails = categorylistmodeldetails;
    }

    private ArrayList<categorylistvalues> Categorylistmodeldetails;


    public ArrayList<categorylistvalues> getFoCategorylistmodeldetails() {
        return foCategorylistmodeldetails;
    }

    public void setFoCategorylistmodeldetails(ArrayList<categorylistvalues> foCategorylistmodeldetails) {
        this.foCategorylistmodeldetails = foCategorylistmodeldetails;
    }

    private ArrayList<categorylistvalues> foCategorylistmodeldetails;

    public ArrayList<photoss> getPhotosdetails() {
        return photosdetails;
    }

    public void setPhotosdetails(ArrayList<photoss> photosdetails) {
        this.photosdetails = photosdetails;
    }

    private ArrayList<photoss> photosdetails;


    public ArrayList<Imageval> getImgphotosdetails() {
        return imgphotosdetails;
    }

    public void setImgphotosdetails(ArrayList<Imageval> imgphotosdetails) {
        this.imgphotosdetails = imgphotosdetails;
    }

    private ArrayList<Imageval> imgphotosdetails;

    public ArrayList<Guidimageurl> getGuidimagedetails() {
        return guidimagedetails;
    }

    public void setGuidimagedetails(ArrayList<Guidimageurl> guidimagedetails) {
        this.guidimagedetails = guidimagedetails;
    }

    private ArrayList<Guidimageurl> guidimagedetails;

    public ArrayList<Categorylistneibourhood> getCategoryneibourho() {
        return Categoryneibourho;
    }

    public void setCategoryneibourho(ArrayList<Categorylistneibourhood> categoryneibourho) {
        Categoryneibourho = categoryneibourho;
    }

    private ArrayList<Categorylistneibourhood> Categoryneibourho;

    public ArrayList<Tourdetails> getTourdetails() {
        return tourdetails;
    }

    public void setTourdetails(ArrayList<Tourdetails> tourdetails) {
        this.tourdetails = tourdetails;
    }

    private ArrayList<Tourdetails> tourdetails;

    public ArrayList<Imageval> getImgdetal() {
        return imgdetal;
    }

    public void setImgdetal(ArrayList<Imageval> imgdetal) {
        this.imgdetal = imgdetal;
    }

    private ArrayList<Imageval> imgdetal;

    public ArrayList<viewclick> getViewdetails() {
        return viewdetails;
    }

    public void setViewdetails(ArrayList<viewclick> viewdetails) {
        this.viewdetails = viewdetails;
    }

    private ArrayList<viewclick> viewdetails;


    public ArrayList<Guidview> getGuidlistdetails() {
        return guidlistdetails;
    }

    public void setGuidlistdetails(ArrayList<Guidview> guidlistdetails) {
        this.guidlistdetails = guidlistdetails;
    }

    private ArrayList<Guidview> guidlistdetails;

    public ArrayList<Categorylistmodel> getFoodcatgory() {
        return foodcatgory;
    }

    public void setFoodcatgory(ArrayList<Categorylistmodel> foodcatgory) {
        this.foodcatgory = foodcatgory;
    }

    private ArrayList<Categorylistmodel> foodcatgory;


    public ArrayList<Categorylistmodel> getFoodcatgorynew() {
        return foodcatgorynew;
    }

    public void setFoodcatgorynew(ArrayList<Categorylistmodel> foodcatgorynew) {
        this.foodcatgorynew = foodcatgorynew;
    }

    private ArrayList<Categorylistmodel> foodcatgorynew;


    public ArrayList<Categorylistmodel> getThingstodonewuimain() {
        return thingstodonewuimain;
    }

    public void setThingstodonewuimain(ArrayList<Categorylistmodel> thingstodonewuimain) {
        this.thingstodonewuimain = thingstodonewuimain;
    }

    private ArrayList<Categorylistmodel> thingstodonewuimain;

    public ArrayList<Categorylistmodel> getThingstodonewuimainnew() {
        return thingstodonewuimainnew;
    }

    public void setThingstodonewuimainnew(ArrayList<Categorylistmodel> thingstodonewuimainnew) {
        this.thingstodonewuimainnew = thingstodonewuimainnew;
    }

    private ArrayList<Categorylistmodel> thingstodonewuimainnew;

    public ArrayList<Categorylistmodel> getScroolthingstodonewuimain() {
        return scroolthingstodonewuimain;
    }

    public void setScroolthingstodonewuimain(ArrayList<Categorylistmodel> scroolthingstodonewuimain) {
        this.scroolthingstodonewuimain = scroolthingstodonewuimain;
    }

    private ArrayList<Categorylistmodel> scroolthingstodonewuimain;

    public ArrayList<Categorylistmodel> getScroolfoodanddrinksnewuimain() {
        return scroolfoodanddrinksnewuimain;
    }

    public void setScroolfoodanddrinksnewuimain(ArrayList<Categorylistmodel> scroolfoodanddrinksnewuimain) {
        this.scroolfoodanddrinksnewuimain = scroolfoodanddrinksnewuimain;
    }

    private ArrayList<Categorylistmodel> scroolfoodanddrinksnewuimain;
}
