package com.kosmo.broadcastreceiver38_3_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //방법1일때
    private MyBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //방법1]매니페스트에 receiver태그 및 uses-permission태그 등록 불필요
        /*
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kosmo.broadcastreceiver.MY_BROADCAST_ACTION");
        registerReceiver(receiver,filter);*/


        Intent intent = getIntent();
        if(intent.getStringExtra("ANOTHER") !=null){
            ((TextView)findViewById(R.id.textView)).setText(intent.getStringExtra("ANOTHER"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}