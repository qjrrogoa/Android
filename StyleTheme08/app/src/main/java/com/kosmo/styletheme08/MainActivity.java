package com.kosmo.styletheme08;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
            themes.xml에 내가 만든 테마를 자바코드로 적용]-setTheme
            내가 만든 리소스는 R.java(R클래스)에 자동으로 등록됨
            고로 내가 만든 자원은 R.리소스종류로 접근한다
         */
        super.onCreate(savedInstanceState);
        //setTheme(R.style.ThemeMadeByMe); //Theme를 정의한 xml로 적용으로 액션바 없앤것
        //getSupportActionBar().hide(); //자바코드로 액션바 없애기
        /*
            안드로이드에서 제공하는 리소스 사용시는
            자바코드 :android.R시작 레이아웃용 xml:@android로 시작
         */
        //※액션바(타이틀바)만 제거
        //setTheme(android.R.style.Theme_Material_Light_NoActionBar);
        //액션바(타이블바) 및 상태바 제거
        //setTheme(android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        //레이아웃 전개
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        Button btnLandscape = findViewById(R.id.landscape);
        Button btnPortrait = findViewById(R.id.portrait);
        //위젯에 리스너 부착
        btnLandscape.setOnClickListener(listener);
        btnPortrait.setOnClickListener(listener);
    }
    //이벤트 핸들러 정의
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.landscape)
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            else
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    };
}