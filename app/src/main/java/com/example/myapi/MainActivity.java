package com.example.myapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    EditText ndescription, nname;

    ImageView myImg;
    Button submit;
    String filePathName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nname = findViewById(R.id.nname);
        ndescription = findViewById(R.id.ndescription);
        myImg = findViewById(R.id.myImg);
        submit = findViewById(R.id.submit);
        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Hello","response.message()");
                try {




                    RequestBody nname1 = RequestBody.create(MultipartBody.FORM,nname.getText().toString());
                    RequestBody ndescription1 = RequestBody.create(MultipartBody.FORM,ndescription.getText().toString());
//                    RequestBody ndescription1 = RequestBody.create(MultipartBody.FORM, String.valueOf(1));
//                    String ndescription1 = ndescription.getText().toString();
//                String imageFile = "http://images-market.epizy.com/mwproject/mwproject/admin/image/images0THYPVSS.jpg";

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.15:8080/myapi/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

//                    try {
//                        String a = nname.getText().toString();
//                        String b = ndescription.getText().toString();
//                        Gson gson = new Gson();
//                        JsonReader reader = new JsonReader(new StringReader(a));
//
//                        reader.setLenient(true); // Set lenient mode
//                        MyItems yourClassObject = gson.fromJson(reader,MyItems.class);
//                    } catch (JsonSyntaxException e) {
//                        e.printStackTrace();
//                        // Handle the malformed JSON exception
//                    }
//                    Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl("http://images-market.epizy.com/Uploadme/marodata/")
//                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
//                            .build();
//                    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


                    String fileName = "imageFile";

                    // Create a File object from the file URI
//                    String realPath = getRealPathFromURI(filePath);
//                    File file = new File(realPath);
//
//                    RequestBody requestFile =
//                            RequestBody.create(MediaType.parse(getContentResolver().getType(filePath)), file);
//                    MultipartBody.Part imagePart =
//                            MultipartBody.Part.createFormData("imageFile", file.getName(), requestFile);

                    // Create a new MyItems object with title and description
//                    MyItems myItems = new MyItems(nname1, ndescription1);

                    // Make the Retrofit API call to upload the image and data

                    File file = new File(getRealPathFromURI(filePath));
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

                    Call<MyItems> myItemsCall = retrofitAPI.uploadProduct(nname1,imagePart,ndescription1);
                    myItemsCall.enqueue(new Callback<MyItems>() {
                        @Override
                        public void onResponse(Call<MyItems> call, Response<MyItems> response) {
                        if (response.isSuccessful()) {

                            MyItems myItems = response.body();
                            if (myItems != null) {
//                                String description = myItems.getDescription();
                                ndescription.setText(response.message());
                            } else {
                                Log.d("Response", "Empty response body");
                            }
                        } else {
                            Log.d("Response", "Unsuccessful: " + response.message());
                        }
                            String res = String.valueOf(response.body());
                            if (response.code()==200){

                                Log.d("Hello","response.message()");
                            }
                        }

                        @Override
                        public void onFailure(Call<MyItems> call, Throwable t) {
                            Log.d("ResponseOn:", "" + t);
                            ndescription.setText(""+t);
                            Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch (Exception e){

                    Log.d("acbbc",""+e);
                }

            }
        });

    }private String getRealPathFromURI(Uri contentURI) {
        String realPath;
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) {
                realPath = contentURI.getPath(); // For file:// scheme
            } else {
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                realPath = cursor.getString(index);
                cursor.close();
            }
        } else {
            realPath = contentURI.getPath();
        }
        return realPath;
    }



    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore
                        .Images
                        .Media.getBitmap(getContentResolver(), filePath);
                myImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void selectImg() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
}