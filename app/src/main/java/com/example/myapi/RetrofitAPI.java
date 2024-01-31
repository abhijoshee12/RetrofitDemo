package com.example.myapi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {


    //    DELETE
//    @DELETE("users/{id}")
//    retrofit2.Call<MyItems> uploadProduct(
//            @Path("id") int ItemId
//    );
//    Update
//    @PUT("users/{2}")
//    retrofit2.Call<MyItems> uploadProduct(
//            @Body MyItems myItems
//    );
//    Add
//    @POST("myapi/insert_api.php")
//    retrofit2.Call<MyItems> uploadProduct(
////         @Body MyItems myItems
//            @Query("title") String title,
//            @Query("description") String description
//    );
    @Multipart
    @POST("insert_api.php")
    retrofit2.Call<MyItems> uploadProduct(@Part("title") RequestBody title, @Part MultipartBody.Part image,
                                          @Part("description") RequestBody description);
//@Multipart
//@POST("update_api.php")
//retrofit2.Call<MyItems> uploadProduct(
//        @Part("id") int id,@Part("title") RequestBody title, @Part MultipartBody.Part image,
//                                      @Part("description") RequestBody description);

//    @DELETE("delete_api.php/{id}")
//    retrofit2.Call<MyItems> deleteProduct(@Query("id") int id);
//    SELECT

    @GET("select_api.php")
    retrofit2.Call<MyItems> uploadProduct();
//@GET("products/1")
//    retrofit2.Call<MyItems> uploadProduct();





}
