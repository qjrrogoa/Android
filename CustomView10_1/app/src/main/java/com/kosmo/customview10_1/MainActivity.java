package com.kosmo.customview10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kosmo.customview10_1.view.CustomView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 XML파일로 뷰 전개]
        //setContentView(R.layout으로 시작하는 리소스아이디);
        //setContentView(R.layout.activity_main);
        //내가 만든 뷰로 뷰 전개
        setContentView(new CustomView(this));
    }
}