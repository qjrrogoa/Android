package com.kosmo.framelayout05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("프레임 레이아웃");
        setContentView(R.layout.activity_main);
        //위젯 얻기
        Button btnFrame1 = findViewById(R.id.btnFrame1);
        Button btnFrame2 = findViewById(R.id.btnFrame2);
        Button btnFrame3 = findViewById(R.id.btnFrame3);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        LinearLayout frame1 = findViewById(R.id.frame1);
        Button frame2 = findViewById(R.id.frame2);
        Button frame3 = findViewById(R.id.frame3);
        //방법2:레이아웃의 메소드 이용
        frameLayout.removeAllViews();//부착된 모든 뷰 제거
        //첫번째 화면 부착
        frameLayout.addView(frame1);




        //리스너 부착:방법1(두번째 및 세번째화면을 visibilty속성을 invisibe) 및 방법2용
        btnFrame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //방법1 -뷰의  visibility속성 이용 즉 android:visibility="invisible"사용시
               /* if(frame1.getVisibility()==View.INVISIBLE){
                    frame1.setVisibility(View.VISIBLE);
                }
                frame2.setVisibility(View.INVISIBLE);
                frame3.setVisibility(View.INVISIBLE);*/
                //방법2
                frameLayout.removeAllViews();
                frameLayout.addView(frame1);
            }
        });
        btnFrame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //방법1 -뷰의  visibility속성 이용 즉 android:visibility="invisible"사용시
               /* if(frame2.getVisibility()==View.INVISIBLE){
                    frame2.setVisibility(View.VISIBLE);
                }
                frame1.setVisibility(View.INVISIBLE);
                frame3.setVisibility(View.INVISIBLE);*/
                //방법2
                frameLayout.removeAllViews();
                frameLayout.addView(frame2);
            }
        });
        btnFrame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //방법1 -뷰의  visibility속성 이용 즉 android:visibility="invisible"사용시
                /*if(frame3.getVisibility()==View.INVISIBLE){
                    frame3.setVisibility(View.VISIBLE);
                }
                frame2.setVisibility(View.INVISIBLE);
                frame1.setVisibility(View.INVISIBLE);*/
                //방법2
                frameLayout.removeAllViews();
                frameLayout.addView(frame3);
            }
        });



    }
}