package com.kosmo.broadcastreceiver28_2_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

//1]BoardcastReceiver상속
public class PowerReceiver extends BroadcastReceiver {
    //2]onReceive()오버라이딩-방송 수신할때마다 자동 호출되는 메소드
    //intent -안드로이드 OS에서 보낸 방송 내용이 저장되 있다.
    @Override
    public void onReceive(Context context, Intent intent) {
        //화면 전환용 인텐트 생성]
        Intent switchIntent = new Intent(context,SwitchActivity.class);

        if(intent.getAction()==Intent.ACTION_POWER_CONNECTED){
            switchIntent.putExtra("POWER_STATUS","POWER IS CONNECTED");
        }
        else if(intent.getAction()==Intent.ACTION_POWER_DISCONNECTED){
            switchIntent.putExtra("POWER_STATUS","POWER IS DISCONNECTED");
        }
        //SwitchActivity 화면 destory시(백키)
        //MainActivity도 삭제 하려면
        //AndoridManifest파일의 Activity태그에
        //android:noHistory="true"속성 추가
        //혹은 ((AppCompatActivity)context).finish();
        context.startActivity(switchIntent);
        //MainActivity를 destrory
        // ((AppCompatActivity)context).finish();
    }
}
