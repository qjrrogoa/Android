package com.kosmo.viewflipper16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 숨기기]
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        Button btnNext = findViewById(R.id.btn_next);
        Button btnPrev = findViewById(R.id.btn_prev);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        flipper = findViewById(R.id.flipper);
        //플립핑 간격 설정-자동으로 플립핑시]
        flipper.setFlipInterval(1000);
        //이벤트 핸들러 객체 생성
        EventHandler handler = new EventHandler();
        //버튼에 리스너 부착]
        btnNext.setOnClickListener(handler);
        btnPrev.setOnClickListener(handler);
        btnStart.setOnClickListener(handler);
        btnStop.setOnClickListener(handler);
    }
    //내부 멤버 클래스로 이벤트 핸들러 만들기
    private class EventHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btn_next) flipper.showNext();
            else if(v.getId() == R.id.btn_prev) flipper.showPrevious();
            else if(v.getId()==R.id.btn_start) flipper.startFlipping();//자동 플립핑
            else flipper.stopFlipping();//자동 플립핑 중지
        }
    }


}