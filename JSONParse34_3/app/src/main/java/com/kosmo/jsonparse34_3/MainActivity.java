package com.kosmo.jsonparse34_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//네이버 쇼핑 검색 API  (네이버 개발자 센터 → Documents → 서비스 API탭의 '검색' → 쇼핑 → 오픈 API 이용 신청
//Client ID(X-Naver-Client-Id) : oND3hWoBb9TRRqYJ1H3Y
//Client Secret(X-Naver-Client-Secret) : MqMeCzzA85
//GET(JSON) : https://openapi.naver.com/v1/search/shop.json

/*
  ┌────────────┬─────────┬────────┬──────────────┬───────────────────────────────────────────┐
  │ 요청 변수명 │  타입   │필수여부 │   기본값     │   설명                                     │
  ├────────────┼─────────┼────────┼──────────────┼───────────────────────────────────────────┤
  │   query	   │ string  │   Y    │     -        │ 검색을 원하는 문자열로서 UTF-8로 인코딩한다. │
  ├────────────┼─────────┼────────┼──────────────┼───────────────────────────────────────────┤
  │  display   │ integer │   N    │ 10(기본값),  │                                           │
  │            │         │        │  100(최대)   │   검색 결과 출력 건수 지정                  │
  ├────────────┼─────────┼────────┼──────────────┼───────────────────────────────────────────┤
  │   start    │ integer │   N    │ 1(기본값),   │ 검색 시작 위치로 최대 1000까지 가능          │
  │            │         │        │  1000(최대)  │                                            │
  ├────────────┼─────────┼────────┼──────────────┼───────────────────────────────────────────┤
  │    sort    │ string  │   N    │ sim(기본값), │ 정렬 옵션: sim (유사도순), date (날짜순),    │
  │            │         │        │ date,asc,dsc │  asc(가격오름차순) ,dsc(가격내림차순)       │
  └────────────┴─────────┴────────┴──────────────┴───────────────────────────────────────────┘
*/

//카카오 REST API KEY(네이버쇼핑연동) : d9d322e1504db133ed5d8f55ead96eb9
// POST https://dapi.kakao.com/v2/vision/product/detect
// (헤더에 넣을 인증키) Authorization: KakaoAK {REST_API_KEY}
// 필수 : image(Binary), image_url(String) 중 하나

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("OPEN API 연습");
        setContentView(R.layout.activity_main);
    }///onCreate

    public void getKakaoProducts(View view){
        //Kakao_main.xml 화면전환
        Intent intent = new Intent(this,KakaoActivity.class);
        startActivity(intent);
    }///getKakaoProducts

}////MainActivity