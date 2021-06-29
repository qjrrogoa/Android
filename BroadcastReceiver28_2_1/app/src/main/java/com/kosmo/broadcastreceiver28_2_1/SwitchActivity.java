package com.kosmo.broadcastreceiver28_2_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//※전원과 관련된 방송 수신시 아래 액티비로 화면 전환
//1]AppCompatActivity상속
public class SwitchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2]레이아웃 전개]
        setContentView(R.layout.switch_layout);
        //3]인텐트 받기
        Intent intent=getIntent();
        //4]안드로이드 OS에서 보낸 방송 내용에 따라
        // 전원 상태를 텍스트뷰에 뿌리기
        ((TextView)findViewById(R.id.textView)).setText(intent.getStringExtra("POWER_STATUS"));
    }
}
