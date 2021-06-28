package com.kosmo.jsonparse34_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NaverShoppingActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private NaverShoppingAdapter adapter;
    private AlertDialog progressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("네이버 API 사용");
        setContentView(R.layout.naver_layout);
        //위젯 얻기
        recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        String kakaoProduct = intent.getStringExtra("kakaoProduct");

        //프로그래스 다이얼로그창 띄우기
        progressDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(R.layout.progress_layout)
                .create();
        progressDialog.show();

        //Retrofit객체 생성
        retrofit = getRetrofit();
        //Retrofit객체로 naverShoppingService타입 인스턴스화
        NaverShoppingService naverShoppingService = retrofit.create(NaverShoppingService.class);
        //Retrofit객체로 요청을 보내기위한 Call객체 얻기
        Call<NaverShoppingItem> call = naverShoppingService.getProducts(kakaoProduct);
        //Call객체로 비동기 요청 보내고 응답받기
        call.enqueue(new Callback<NaverShoppingItem>() {
            @Override
            public void onResponse(Call<NaverShoppingItem> call, Response<NaverShoppingItem> response) {
                Log.i("com.kosmo.jsonparse","상태코드 : "+response.code());
                if(response.isSuccessful()){
                    try {
                        NaverShoppingItem items = response.body();
                        ObjectMapper mapper = new ObjectMapper();
                        String jsonString = mapper.writeValueAsString(items);
                        Log.i("com.kosmo.jsonparse",jsonString);
                        NaverShoppingAdapter adapter = new NaverShoppingAdapter(NaverShoppingActivity.this,items);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(NaverShoppingActivity.this));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }//catch
                    Toast.makeText(NaverShoppingActivity.this,"네이버 API(검색/쇼핑) 완료",Toast.LENGTH_SHORT).show();
                    //다이얼로그 창 닫기
                    progressDialog.dismiss();
                }//if
                else {
                    Log.i("com.kosmo.jsonparse","오류내용 : "+response.errorBody());
                }//else
            }//onResponse

            @Override
            public void onFailure(Call<NaverShoppingItem> call, Throwable t) {
                Log.i("com.kosmo.jsonparse","오류메세지 : "+t.getMessage());
            }//onFailure
        });//enqueue

    }///onCreate

    private Retrofit getRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                //.addConverterFactory(ScalarsConverterFactory.create()).build();
                .addConverterFactory(JacksonConverterFactory.create()).build();

        return retrofit;
    }//getRetrofit
}///NaverShoppingActivity