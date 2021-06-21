package com.kosmo.fileio26_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //이미지 리소스 아이디 저장 배열]
    private int[] resIds={R.raw.pic1,R.raw.pic2,R.raw.pic3};
    // 커스텀 뷰용]
    private CustomView customView;
    //현재 인덱스 저장용]
    private int currentIndex;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기-xml에 커스텀뷰 붙일때
        customView = findViewById(R.id.customview);
        //커스텀뷰의 resId값 초기화]
        customView.resId =resIds[0];
        //커스텀뷰의 invalidate()호출 -onDraw()메소드로가 호출됨
        customView.invalidate();
        //다이얼로그 생성
        dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_lock_power_off)
                .setTitle("알림 메시지")
                .setPositiveButton("확인",null)
                .create();

    }

    public void previous(View view){
        if(currentIndex==0){
            dialog.setMessage("이전 이미지가 없어요");
            dialog.show();
            return;
        }
        //현재 인덱스 감소-이전
        currentIndex--;
        //현재 인덱스로 커스텀뷰의 resId값 설정]
        customView.resId=resIds[currentIndex];
        //커스텀뷰의 invalidate()호출 -onDraw()메소드로가 호출됨
        customView.invalidate();
    }
    public void next(View view){
        if(currentIndex==resIds.length-1){
            dialog.setMessage("다음 이미지가 없어요");
            dialog.show();
            return;
        }
        //현재 인덱스 증가
        currentIndex++;
        //현재 인덱스로 커스텀뷰의 resId값 설정]
        customView.resId=resIds[currentIndex];
        //커스텀뷰의 invalidate()호출 -onDraw()메소드로가 호출됨
        customView.invalidate();
    }
}