package com.kosmo.broadcastreceiver38_3_sender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/*
    버튼 클릭시 내가 직접 다른 어플리케이션에 방송 보내기
    방법1]앱이 종료된 상태(destroy)에서는 방송수신 불가

    sender:
        sendBroadcast(인텐트);
    recever:
        registerReceiver(receiver,filter)
    방법2]앱이 종료상태일때도 수신가능
    sender:
        sendBroadcast(인텐트,"사용자 정의 퍼미션");
        매니페스프 파일에
        <permission android:name="사용자 정의 퍼미션" android:protectionLevel="signature"/>
        <uses-permission android:name="사용자 정의 퍼미션"/>

    recever:
        매니페스트에
        <uses-permission android:name="사용자 정의 퍼미션"/>
         <receiver android:name="리시버"
                  android:permission="사용자 정의 퍼미션"
            >
            <intent-filter>
                <action android:name="방송액션"/>
            </intent-filter>
        </receiver>

      registerReceiver(receiver,filter) 자바코드 미 사용
 */
public class MainActivity extends AppCompatActivity {
    //방송 내용 즉 내가 보낸 방송 액션 정의]
    public static final String MY_BROADCAST_ACTION="com.kosmo.broadcastreceiver.MY_BROADCAST_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //버튼 클릭시 방송 내보내기:sendBroadcast(Intent,[퍼미션])메소드
    public void broadcast(View view){
        Intent intent = new Intent();
        //방송내용 설정
        intent.setAction(MY_BROADCAST_ACTION);
        //방송 송출
        //방법1] 수신자 앱이 destroy시에는 수신 못함
        //sendBroadcast(intent);
        //방법2]수신자 앱이 destroy시에도 수신 가능
        sendBroadcast(intent,"com.kosmo.broadcastreceiver.MY_PERMISSION");
    }
}