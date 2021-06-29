package com.kosmo.broadcastreceiver38_3_receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {
    //방송 내용이 MY_BROADCAST_ACTION("com.kosmo.broadcastreceiver.MY_BROADCAST_ACTION") 일때 화면 전환
    @Override
    public void onReceive(Context context, Intent intent) {
        //화면 전환용 인텐트
        Intent mainIntent = new Intent(context,MainActivity.class);
        //아래 생략시 방송을 보낸 앱이 맨위에 표시됨.
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainIntent.putExtra("ANOTHER","다른 앱에서 보낸 방송 수신");
        //펜딩인텐트로 액티비티를 실행하겠다라는 의미
        /*
        PendingIntent의 목적은 그것이 포함하고 있는 인텐트를 외부의
        다른 어플리케이션에서 실행할 수 권한을 주는 것
        즉 지연된 인텐트를 만든다
         */
        PendingIntent pendingIntent= PendingIntent.getActivity(context,0,mainIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        //방송보낸 앱에서 버튼 클릭시 그때 수신앱의 메인 액티비티로
        //인텐트를 보낸다
        //수신앱의 메인 액티비티가 Launching된다 즉 onCreate()가 호출된다.
        try {
            pendingIntent.send();
        }
        catch(PendingIntent.CanceledException e){e.printStackTrace();}
    }
}
