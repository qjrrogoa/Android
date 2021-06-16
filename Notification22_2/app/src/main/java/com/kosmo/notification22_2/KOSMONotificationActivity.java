package com.kosmo.notification22_2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//노티클릭시 전환할 화면 제어용 액티비티
public class KOSMONotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바(타이틀바) 없애기]
        //getSupportActionBar().hide();
        //뷰전개
        setContentView(R.layout.notification_layout);
        //인텐트를 통해서 전달된 메시지 얻기
        String messsge=getIntent().getStringExtra("kosmo");
        ((TextView)findViewById(R.id.notiText)).setText(messsge);
    }//////////////
}
