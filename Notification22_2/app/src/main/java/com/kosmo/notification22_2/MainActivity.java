package com.kosmo.notification22_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {


    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //오레오 부터 아래 코드 추가해야 함 시작
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_ID","CHANNEL_NAME",importance);
        notificationChannel.enableLights(true);//스마트폰에 노티가 도착했을때 빛을 표시할지 안할지 설정
        notificationChannel.setLightColor(Color.RED);//위 true설정시 빛의 색상
        notificationChannel.enableVibration(true);//노티 도착시 진동 설정
        notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,100});//진동 시간(1000분의 1초)
        //오레오 부터 아래 코드 추가해야 함 끝
        //노피케이션 매니저와 연결
        notificationManager.createNotificationChannel(notificationChannel);
    }/////////////////
    //기본형]
    public void basicNotification(View view){
        //NotificationCompat.Builder객체 생성
        NotificationCompat.Builder builder=createNotificationBuilder();
        //실행할 펜딩 인텐트 설정
        builder.setContentIntent(createPendingIntent());
        //Notification객체 생성
        Notification notification=builder.build();
        //통지하기
        //NotificationManager의 notify()메소드로 Notification객체 등록
        //notify(Notification을 구분하기 위한 구분자,Notification객체);
        notificationManager.notify(1,notification);
    }
    /*
   제목은 setBigContentTitle이 보인다
   내용은 기본의 setContentTitle
   노티를 아래로 드래그시 InboxStyle의 내용으로 변경된다.
  */
    //확장형]
    public void extendNotification(View view){
        //NotificationCompat.Builder객체 생성
        NotificationCompat.Builder builder=createNotificationBuilder();
        //InboxStyle을 Notification의 스타일로 사용하기위한
        //InboxStyle객체 생성
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setBigContentTitle("자바과정 커리큘럼");//제목
        inboxStyle.setSummaryText("커리큘럼");//노티 스몰 아이콘 옆에 표시됨
        //내용은 addLine()으로 추가
        inboxStyle.addLine("자바");
        inboxStyle.addLine("스프링");
        inboxStyle.addLine("안드로이드");
        builder.setStyle(inboxStyle);

        //실행할 펜딩 인텐트 설정
        builder.setContentIntent(createPendingIntent());
        //Notification객체 생성
        Notification notification=builder.build();
        //통지하기
        //NotificationManager의 notify()메소드로 Notification객체 등록
        //notify(Notification을 구분하기 위한 구분자,Notification객체);
        notificationManager.notify(2,notification);

    }
    /*
    제목과 내용이 커스텀 레이아웃으로 모두
    대체되서 보이게 하자
   */
    //커스텀형]
    public void customNotification(View view){
        //NotificationCompat.Builder객체 생성
        NotificationCompat.Builder builder=createNotificationBuilder();
        //getPackageName() : package이름을 반환하는 메소드
        @SuppressLint("RemoteViewLayout") RemoteViews remoteViews= new RemoteViews(getPackageName(),R.layout.custom_layout);

        remoteViews.setImageViewIcon(R.id.img, Icon.createWithResource(this, android.R.drawable.ic_menu_camera));
        remoteViews.setTextViewText(R.id.mytitle,"제목입니다");
        remoteViews.setTextColor(R.id.mytitle,Color.RED);
        remoteViews.setTextViewTextSize(R.id.mytitle, TypedValue.COMPLEX_UNIT_SP,10);
        remoteViews.setTextViewText(R.id.mymessage,"내용입니다\r\n내용입니다\r\n내용입니다");
        builder.setContent(remoteViews);
        //실행할 펜딩 인텐트 설정
        builder.setContentIntent(createPendingIntent());
        //Notification객체 생성
        Notification notification=builder.build();
        //통지하기
        //NotificationManager의 notify()메소드로 Notification객체 등록
        //notify(Notification을 구분하기 위한 구분자,Notification객체);
        notificationManager.notify(3,notification);
    }
    //위3개 버튼 이벤트에 공통으로 적용할 메소드:펜딩 인텐트 생성용
    public PendingIntent createPendingIntent(){
        //1]상태바에 표시된 노티 클릭시 Notification객체에 등록할 인텐트 생성
        //1-1]노티 클릭시 이동할 화면정보를 가진 인텐트 생성
        //두번째 인자:노티클릭시 전환할 화면 클래스(즉 액티비티)
        //매니페스트에 액티비티 등록(KOSMONotificationActivity)
        Intent intent = new Intent(this,KOSMONotificationActivity.class);
        //1-2]인텐트에 메시지 저장
        intent.putExtra("kosmo","한국 소프트웨어 인재개발원");
        //PendingIntent.FLAG_UPDATE_CURRENT:펜딩 인텐트가 이미 존재한다면 이를
        // 그대로 유지하면서 추가 데이터를
        //만 업데이트 한다
        return PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }////////////////
    //위 3개의 버튼 클릭시 Notification객체를 생성하기 위해 공통으로 사용할 메소드
    private NotificationCompat.Builder createNotificationBuilder(){
        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
        NotificationCompat.Builder  builder=new NotificationCompat.Builder(this,"CHANNEL_ID")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setLargeIcon(largeIcon)
                .setContentTitle("한국 소프트웨어 인재개발원")//노티 클릭시 보이는 제목
                .setContentText("가산 디지털 단지역에 위치하고 있는 훈련기관입니다")
                .setTicker("KOSMO")//상태바에 표시되는 티커
                .setAutoCancel(true)//노티 클릭시 상태바에서 자동으로 사라지도록 설정
                .setWhen(SystemClock.currentThreadTimeMillis())//노티 전달 시간
                .setDefaults(Notification.DEFAULT_VIBRATE);//노티시 알림 방법
        return builder;
    }//////////////

}