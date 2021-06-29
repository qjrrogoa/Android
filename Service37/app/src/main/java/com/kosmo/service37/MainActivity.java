package com.kosmo.service37;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(intent == null){
            intent = new Intent(this,MusicService.class);
            intent.putExtra("kosmo","서비스에서 보낸 데이터");
        }
        //서비스 시작
        startService(intent);
        Log.i("com.kosmo.service","MainActivity의 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(intent != null) {
            stopService(intent);
        }
        Log.i("com.kosmo.service", "MainActivity의 onPause");
    }

}////////////////