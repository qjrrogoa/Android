package com.kosmo.intent24_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//1]AppCompatActivity상속
public class StartActivity extends AppCompatActivity {
    //2]onCreate()오버 라이딩

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //3]뷰 전개-inflate
        setContentView(R.layout.start_layout);
        //전달받은 인텐트에 저장된 데이타 얻기]
        Intent intent = getIntent();
        String user= intent.getStringExtra("user");
        String pass= intent.getStringExtra("pass");
        //텍스트뷰에 표시
        ((TextView)findViewById(R.id.textStartActivity)).setText("아이디:"+user+",비번:"+pass);
        findViewById(R.id.btnStartActivityFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }/////////
}
