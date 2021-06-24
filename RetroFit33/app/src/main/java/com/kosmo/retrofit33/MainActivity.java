package com.kosmo.retrofit33;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;
    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        TextView textView = findViewById(R.id.textView);
        progressDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(R.layout.progress_layout).create();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //프로그레스바 띄우기
                progressDialog.show();

                //데이타는 Rest API서버(https://jsonplaceholder.typicode.com/)
                //로 뷰터 JSON으로 받기:Retrofit 사용
                getDataFromServer();
            }
        });



    }//////////onCreate()
    private void getDataFromServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                //.addConverterFactory(GsonConverterFactory.create()).build();//구글의 Gson라이브러리 설정
                .addConverterFactory(JacksonConverterFactory.create()).build();
        //Retrofit객체로 JsonPlaceService타입의 객체 생성
        JsonPlaceService jsonPlaceService = retrofit.create(JsonPlaceService.class);
        //서버로 요청보내고 응답받기위한 Call객체 생성
        Call<List<JsonPlaceDTO>> call = jsonPlaceService.getPosts();
        //비동기로 요청보내고 응답 받기
        call.enqueue(new Callback<List<JsonPlaceDTO>>() {
            //서버로부터 응답을 받을때 자동 호출되는 콜백 메소드
            @Override
            public void onResponse(Call<List<JsonPlaceDTO>> call, Response<List<JsonPlaceDTO>> response) {
                if(response.isSuccessful()){//정상적인 응답시 즉 상태코드 200
                    List<JsonPlaceDTO> posts=response.body();
                    SystemClock.sleep(2000);//너무 빨리 프레스바창이 사라져서 2초 딜레이
                    dataBinding(posts);

                    Headers headers=response.headers();
                    Log.i("com.kosmo.retrofit","총 글수:"+posts.size());
                    Log.i("com.kosmo.retrofit","상태코드:"+response.code());
                    Log.i("com.kosmo.retrofit","응답헤더:"+headers.get("Content-Type"));

                }
                else{
                    Log.i("com.kosmo.retrofit",response.errorBody().toString());
                }
            }
            //서버로 부터 응답 실패
            @Override
            public void onFailure(Call<List<JsonPlaceDTO>> call, Throwable t) {
                Log.i("com.kosmo.retrofit","Failure:"+t.getMessage());
            }
        });

    }
    private void dataBinding(List<JsonPlaceDTO> posts){
        //어댑터 생성
        adapter = new MyRecyclerAdapter(this,posts);
        //리사이클러뷰와 어댑터 연결
        recyclerView.setAdapter(adapter);
        //레이아웃 매니저 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //프로그레스바 죽이기
        progressDialog.dismiss();
    }///////////////

}