package com.kosmo.jsonparse34_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private AlertDialog progressDialog;
    private static Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        //프로그래스 다이얼로그창 띄우기
        progressDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(R.layout.progress_layout)
                .create();
        progressDialog.show();
        ///Retrofit객체 생성
        retrofit = getRetrofit();
        //Retrofit객체로 PhotoService타입 인스턴스화
        PhotoService photoService=retrofit.create(PhotoService.class);
        //PhotoService타입 객체로 요청을 보내기위한 Call객체 얻기
        Call<List<PhotoItem>> call= photoService.getPhotos();
        //call객체로 비동기 요청 보내고 응답받기
        call.enqueue(new Callback<List<PhotoItem>>() {
            @Override
            public void onResponse(Call<List<PhotoItem>> call, Response<List<PhotoItem>> response) {
                if(response.isSuccessful()){
                    List<PhotoItem> items=response.body();
                    PhotoAdapter adapter = new PhotoAdapter(MainActivity.this,items);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    //다이얼로그창 죽이기
                    progressDialog.dismiss();
                }
                else{}
            }
            @Override
            public void onFailure(Call<List<PhotoItem>> call, Throwable t) {}
        });
    }//////////

    private Retrofit getRetrofit(){
        retrofit =new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(JacksonConverterFactory.create()).build();

        return retrofit;
    }///////////////////


}