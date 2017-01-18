package com.hhwt.travelplanner.Retrofit;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Mathankumar on 07/02/16.
 */
public interface ApiCall {

    @FormUrlEncoded
    @POST("register_webservice.php")
    Call<UserRegistrationResponse> UserRegistersapi(@Field("fb_id") String fb_id, @Field("name") String name, @Field("email") String email, @Field("imgurl") String imgurl);
    //,@Field("dob") String dob, @Field("phonenumber") String phonenumber, @Field("country") String country


    @FormUrlEncoded
    @POST("testingregisterfacebook.php")
    Call<UserRegistrationResponse> UserRegistersapisss(@Field("fb_id") String fb_id, @Field("name") String name, @Field("email") String email, @Field("imgurl") String imgurl);
    //,@Field("dob") String dob, @Field("phonenumber") String phonenumber, @Field("country") String country




    @FormUrlEncoded
    @POST("testing.php")
    Call<UserRegistrationResponse> UserRegistersapiss(@Field("fb_id") String fb_id, @Field("name") String name, @Field("email") String email, @Field("imgurl") String imgurl);
    //,@Field("dob") String dob, @Field("phonenumber") String phonenumber, @Field("country") String country

    @FormUrlEncoded
    @POST("newfblogin.php")
    Call<UserRegistrationResponse> UserRegistersapis(@Field("fb_id") String fb_id, @Field("name") String name, @Field("email") String email, @Field("imgurl") String imgurl, @Field("dob") String dob, @Field("phonenumber") String phonenumber, @Field("country") String country);


    @FormUrlEncoded
    @POST("get_category_value.php")
    Call<CategorylistValuesResponse> CategorylistValuesapi(@Field("cid") String cat_id);

    @FormUrlEncoded
    @POST("get_category_value_new.php")
    Call<CategorylistValuesResponse> CategorylistValuenewsapi(@Field("cid") String cat_id, @Field("cityid") String cityid);


   /* @FormUrlEncoded
    @POST("getcatlist.php")
    Call<CategorylistValuesResponse> CategorylistValuenewsapiincrement(@Field("cid") String cat_id, @Field("cityid") String cityid, @Field("upperlimit") int upperlimit, @Field("lowerlimit") int lowerlimit);*/



    @FormUrlEncoded
    @POST("getcatlistnew.php")
    Call<CategorylistValuesResponse> CategorylistValuenewsapiincrement(@Field("cid") String cat_id, @Field("cityid") String cityid, @Field("upperlimit") int upperlimit, @Field("lowerlimit") int lowerlimit,@Field("userid") String userid);









/*    @FormUrlEncoded
    @POST("getcatcity.php")
    Call<CategorylistValuesResponse> CategorylistValuenewsapinew(@Field("cid") String cat_id, @Field("cityid") String cityid);*/



    @FormUrlEncoded
    @POST("getcatcitynew.php")
    Call<CategorylistValuesResponse> CategorylistValuenewsapinew(@Field("cid") String cat_id, @Field("cityid") String cityid,@Field("userid") String userid);



   /* @FormUrlEncoded
    @POST("filterresult.php")
    Call<CategorylistValuesResponse> filterresultnewuiapi(@Field("cid") String cat_id, @Field("cityid") String cityid,@Field("rating") String weight,@Field("foodc") String foodc,@Field("foodt") String foottype);







    @FormUrlEncoded
    @POST("filterresult.php")
    Call<CategorylistValuesResponse> foodfilterresultnewuiapi(@Field("cid") String cat_id, @Field("cityid") String cityid,@Field("rating") String weight,@Field("foodc") String foodc,@Field("foodt") String foottype);
*/




    @FormUrlEncoded
    @POST("filterresultnew.php")
    Call<CategorylistValuesResponse> filterresultnewuiapi(@Field("cid") String cat_id, @Field("cityid") String cityid,@Field("weight") String weight,@Field("foodc") String foodc,@Field("foodt") String foottype,@Field("userid") String userid,@Field("district") String district);







    @FormUrlEncoded
    @POST("filterresultnew.php")
    Call<CategorylistValuesResponse> foodfilterresultnewuiapi(@Field("cid") String cat_id, @Field("cityid") String cityid,@Field("weight") String weight,@Field("foodc") String foodc,@Field("foodt") String foottype,@Field("userid") String userid,@Field("district") String district);






    @FormUrlEncoded
    @POST("searchreviews.php")
    Call<CategorylistValuesResponse> reviewsearch(@Field("searchvalue") String searchvalue);






/*    @FormUrlEncoded
    @POST("search.php")
    Call<CategorylistValuesResponse> Searchvalnewsapi(@Field("cityid") String cityid, @Field("catgry") String catgry,@Field("searchvalue") String searchvalue);*/




    @FormUrlEncoded
    @POST("searchnew.php")
    Call<CategorylistValuesResponse> Searchvalnewsapi(@Field("cityid") String cityid, @Field("catgry") String catgry,@Field("searchvalue") String searchvalue,@Field("userid") String userid);





    @FormUrlEncoded
    @POST("trip_complete_details.php")
    Call<MytriplistValuesResponse> MytripCategoriesapi(@Field("fb_id") String fb_id, @Field("tripid") String tripid);

    @FormUrlEncoded
    @POST("trip_register_new.php")
    Call<TripRegisterResponse> TripRegister(@Field("fb_id") String fb_id, @Field("tripname") String tripname, @Field("sdate") String sdate, @Field("edate") String edate, @Field("tripdes") String tripdes, @Field("cityid") String cityid);

    @FormUrlEncoded
    @POST("trip_timingdetails.php")
    Call<TripRegisterResponse> Triptodo(@Field("fb_id") String fb_id, @Field("tripid") String tripid, @Field("stime") String stime, @Field("etime") String etime, @Field("ttdate") String ttdate, @Field("dataelementid") String dataelementid);

    @FormUrlEncoded
    @POST("user_trip_details.php")
    Call<MyTripResponse> User_trip_details(@Field("fb_id") String fb_id);

    @FormUrlEncoded
    @POST("user_trip_details_new.php")
    Call<MyTripResponse> Explore_User_trip_details(@Field("fb_id") String fb_id,@Field("cityid") String city_id);

    @FormUrlEncoded
    @POST("get_nearby_elements.php")
    Call<NearbyelementResponse> GetNearByelements(@Field("did") String did);

    @FormUrlEncoded
    @POST("delete_trip.php")
    Call<TripRegisterResponse> DeleteTrip(@Field("fb_id") String fb_id, @Field("tripid") String tripid);

    @FormUrlEncoded
    @POST("get_category_value_bydistrict.php")
    Call<CategorylistValuesResponse> CategorylistValuesapiNearby(@Field("cid") String cat_id, @Field("district") String district);

    @FormUrlEncoded
    @POST("search.php")
    Call<CategorylistValuesResponse> searchCategorylistValuesapi(@Field("searchvalue") String searchvalue);


    @FormUrlEncoded
    @POST("update_tripdetails.php")
    Call<TripRegisterResponse> TripDateUpdate(@Field("fb_id") String fb_id, @Field("tripid") String tripid, @Field("tripname") String tripname, @Field("sdate") String sdate, @Field("edate") String edate, @Field("tripdes") String tripdes);


    @GET("fetchreviews.php")
    Call<Allreviews> ViewAllreviews();


    @GET("tovote.php")
    Call<UserRegistrationResponse> ToVote(@QueryMap Map<String, String> options);

    @GET("insertcomments.php")
    Call<UserRegistrationResponse> Insertcommentsapi(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST("delete_trip_timing.php")
    Call<TripRegisterResponse> DeleteTripitem(@Field("fb_id") String fb_id, @Field("tripid") String tripid, @Field("date") String date, @Field("dataelementid") String dataelementid);

    @GET("get_cities.php")
    Call<AllCItyListPojo> GetAllcity();

    @GET("tourcities.php")
    Call<AllCItyListPojo> GetAllcitys();


    @GET("get_vote_cities.php")
    Call<VoteCityPojo> GetAllcityforvote();

    @POST("tovote.php")
    Call<VoteCityPojoss> votealreadyrvote(@Field("cityid") String fb_id, @Field("userid") String tripid);

}
