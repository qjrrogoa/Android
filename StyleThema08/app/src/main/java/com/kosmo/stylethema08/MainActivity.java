package com.kosmo.stylethema08;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*thems.xml에 내가 만든 테마를 자바코드로 적용] -setTheme
        내가 만든 리소스 R.java(R클래스)에 자동으로 등록됨
        고로 내가 만든 자원은 R.리소스 종류로 접근한다.
         */
        super.onCreate(savedInstanceState);
        //setTheme(R.style.ThemeMadeByMe);//Theme를 정의한 xml로 적용으로 액션바 없애
        //getSupportActionBar().hide();//자바코드로 액션바 없애기
        /*안드로이드에서 제공하는 리소스 사용시는
        자바코드:android.R시작 레잉웃용 xml:@android로 시작
         */
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_main);
    }
}