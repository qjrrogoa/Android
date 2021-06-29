package com.kosmo.service37;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

//1]Service 상속
public class MusicService extends Service {

    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        //지역 서비스 : 내가 만든 앱에서 실행되는 서비스 무조건 Null 반환
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("com.kosmo.service","서비스의 onCreate");
        super.onCreate();
        player = MediaPlayer.create(getApplicationContext(),R.raw.kalimba);   
    }
    //onCreate()후 호출되는 메소드] - 실제 서비스 실행 코드 작성

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("com.kosmo.service","서비스의 onStartCommand");
        //서비스 시작 코드 작성]
        //미디어 재생
        if(player != null && !player.isPlaying())
            player.start();
        //서비스 시작시 보낸 데이터 얻기
        String message = intent.getStringExtra("kosmo");
        Log.i("com.kosmo.service","액티비티에서 보낸 데이터 : "+message);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("com.kosmo.service","서비스의 onDestroy");
        //미디어 재생 중지
        if(player !=null && player.isPlaying())
            player.stop();
        //MediaPlayser 객체 자원 해제]
        player.release();
    }
}
