package com.kosmo.jsonparse34_3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NaverShoppingAdapter adapter;
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
        NaverShoppingService naverService=retrofit.create(NaverShoppingService.class);
        //PhotoService타입 객체로 요청을 보내기위한 Call객체 얻기
        Call<String> call= naverService.getProducts("스마트폰");
        //call객체로 비동기 요청 보내고 응답받기
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("com.kosmo.api", ""+response.code());
                if(response.isSuccessful()) {
                    Log.d("com.kosmo.api", response.body());
                    progressDialog.dismiss();
                }
                else{
                    Log.d("com.kosmo.api", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("com.kosmo.api",t.getMessage());
            }
        });
    }//////////

    private Retrofit getRetrofit(){
        retrofit =new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(ScalarsConverterFactory.create()).build();

        return retrofit;
    }///////////////////


}