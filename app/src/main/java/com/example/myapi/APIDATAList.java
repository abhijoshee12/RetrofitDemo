package com.example.myapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIDATAList extends AppCompatActivity {


    RecyclerView rvv;

    ArrayList<ItemData> myItemList;
    ItemListAdapter itemListAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apidatalist);

        rvv = findViewById(R.id.rvv);
        LinearLayoutManager llm = new LinearLayoutManager(APIDATAList.this);
        rvv.setHasFixedSize(true);
        rvv.setLayoutManager(llm);

        myItemList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.15:8080/myapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MyItems> myItemsCall = retrofitAPI.uploadProduct();

        myItemsCall.enqueue(new Callback<MyItems>() {
            @Override
            public void onResponse(Call<MyItems> call, Response<MyItems> response) {
                if (response.isSuccessful()) {
                    try {
                        MyItems myItems = response.body();
                        List<ItemData> itemDataList = myItems.getItemData();
//                    myItemList.addAll(itemDataList);

//                    if (myItems != null && myItems.getItemData() != null) {
//                        List<ItemData> itemDataList = myItems.getItemData();
                        for (ItemData item : itemDataList) {
                            Log.d("Item", "Id: " + item.getId() + ", Title: " + item.getTitle() + ", Image: " + item.getImage() + ", Description: " + item.getDescription());
                            myItemList.add(item);
                        }
                        itemListAdapter1 = new ItemListAdapter(APIDATAList.this, myItemList);
                        rvv.setAdapter(itemListAdapter1);
                    }catch (Exception e){
                        Log.d("ExDDDD" ,""+e);
                    }

//                    } else {
//                        Log.d("MYDATA", "Response body is null or empty");
//                    }
                } else {
                    Log.d("ACCC", "Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyItems> call, Throwable t) {
                Log.d("AASSASASASAS", "" + t);
            }
        });

//        Call<MyItems> myItemsCall = retrofitAPI.uploadProduct();
//        myItemsCall.enqueue(new Callback<MyItems>() {
//            @Override
//            public void onResponse(Call<MyItems> call, Response<MyItems> response) {
//                if (response.isSuccessful()) {
////                    for (int i = 0; i < response.body().size(); i++) {
////                        MyItems myItems = response.body().get(i);
////                        myItemList.add(myItems);
////                    }
//                    MyItems myItems = response.body();
//                    itemListAdapter1 = new ItemListAdapter(APIDATAList.this, myItemList);
//                    rvv.setAdapter(itemListAdapter1);
//                    if (myItems != null) {
//                        Log.d("MYDATA", myItems.toString());
//
////                        itemListAdapter.notifyDataSetChanged();
//                    } else {
//                        Log.d("MYDATA", "Response body is null");
//                    }
//
//                } else {
//                    Log.d("ACCC", "Response failed: " + response.code());
//                }
////                List<MyItems> myItems = response.body();
//
////                if (response.body() != null) {
////
//////                    ArrayList<MyItems> myItems = response.body();
////
////
////                    Log.d("ABCCCCC", ""+response.body());
////                    for (int i = 0; i < response.body().size(); i++) {
////
////                        MyItems myItems = new MyItems(response.body().get(i).getId(),response.body().get(i).getTitle(),response.body().get(i).getImage(),response.body().get(i).getDescription());
////                        myItemList.add(myItems);
////                    }
////                    itemListAdapter.notifyDataSetChanged();
////
////                }
//
//
////                if (!response.isSuccessful()) {
////                    Log.e("API Error", "Code: " + response.code());
////                    return;
////                }
//
//
////                if (response.isSuccessful()) {
////
////                    MyItems myItems = response.body();
////                }
//
//
////                    myItemList.addAll(myItems);
//
//
////                    if (myItems != null) {
////                        Log.d("ACCC", "Done");
////
////                        LinearLayoutManager llm = new LinearLayoutManager(APIDATAList.this);
////                        rvv.setLayoutManager(llm);
////                        rvv.setAdapter(new ItemListAdapter(APIDATAList.this, myItems));
////                    } else {
////                        Log.d("Response", "Empty response body");
////                    }
////                } else {
////                    Log.d("ResponseOn", "Unsuccessful: " + response.message());
////                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<MyItems> call, Throwable t) {
//
//                Log.d("AASSASASASAS",""+t);
//            }
//        });
    }


}