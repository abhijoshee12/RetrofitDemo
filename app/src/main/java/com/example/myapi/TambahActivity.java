package com.example.myapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    private EditText etJudul, etPenulis, etTahun;
    private Button btnTambah;
    private String judul, penulis, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etJudul = findViewById(R.id.etJudul);
        etPenulis = findViewById(R.id.etPenulis);
        etTahun = findViewById(R.id.etTahun);
        btnTambah = findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = etJudul.getText().toString();
                penulis = etPenulis.getText().toString();
                tahun = etTahun.getText().toString();

                if(judul.trim().equals("")){
                    etJudul.setError("Judul harus diisi!");
                }
                else if(penulis.trim().equals("")){
                    etPenulis.setError("Penulis harus diisi!");
                }
                else if(tahun.trim().equals("")){
                    etTahun.setError("Tahun terbit harus diisi!");
                }
                else {
                    sendData();
                }
            }
        });

    }

    private void sendData() {
        APIRequestData apiRequestData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> postData = apiRequestData.ardSendData(judul, penulis, tahun);

        postData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode: " +kode+ " | Pesan: " +pesan, Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }
}