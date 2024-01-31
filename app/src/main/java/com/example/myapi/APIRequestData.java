package com.example.myapi;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("fetch.php")
    Call<ResponseModel> ardFetchData();

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> ardSendData(
            @Field("judul") String judul,
            @Field("penulis") String penulis,
            @Field("tahun_terbit") String tahun
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("judul") String judul,
            @Field("penulis") String penulis,
            @Field("tahun_terbit") String tahun
    );
}
