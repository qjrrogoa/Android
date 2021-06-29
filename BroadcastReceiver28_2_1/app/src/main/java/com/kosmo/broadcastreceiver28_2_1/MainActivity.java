package com.kosmo.broadcastreceiver28_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/*
    [어플리케이션의 매니페스트 파일에 receiver태그로 리시버 등록]
    리시버 등록시 등록한 필터에 지정한  방송을 수신하면
    onReceive메소드에서 또 다른 액티비티로 화면전환
    그리고 전환된 화면에서는 방송 내용에 따라   텍스트 변경.

 */
public class MainActivity extends AppCompatActivity {

    private PowerReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new PowerReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        //아래 코드로 해야 방송을 수신할 수 있다
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //리시버 해제
        unregisterReceiver(receiver);
    }
}