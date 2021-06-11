package com.kosmo.linearlayout02_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이이웃용 XML파일 불 필요.아래코드 주석
        //setContentView(R.layout.activity_main);
        setTitle("자바코드로 화면 구성");
        //1]LinearLayout객체 생성
        LinearLayout layout = new LinearLayout(this);
        //2]오리엔테이션 설정
        layout.setOrientation(LinearLayout.HORIZONTAL);
        //3]리니어레이아웃의 가로폭/세로폭 설정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //3-1]LayoutParams를 LinearLayout객체에 적용
        layout.setLayoutParams(params);
        layout.setBackgroundColor(Color.rgb(255,255,0));
        //3-2]컨텐츠 정렬-가운데 정렬
        layout.setGravity(Gravity.CENTER);
        //4]Button타입 선언
        Button btnOne,btnTwo;
        //5]Button타입 객체 생성
        btnOne = new Button(this);
        btnTwo = new Button(this);
        //6]버튼들의 속성 설정
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        btnOne.setLayoutParams(btnParams);
        btnOne.setText("버튼1");
        btnOne.setTextColor(Color.RED);
        btnOne.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        //※버튼에 가로/세로폭 미 적용시
        //디폴트가 가로폭 ,세로폭은 WRAP_CONTENT임.
        btnTwo.setText("버튼2");
        //버튼에 리스너 부착]
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == btnOne)
                    Toast.makeText(v.getContext(),"첫번째 버튼입니다",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(v.getContext(),"두번째 버튼입니다",Toast.LENGTH_SHORT).show();
            }
        };

        btnOne.setOnClickListener(listener);
        btnTwo.setOnClickListener(listener);

        //5]레이아웃에 버튼 부착:addView()
        layout.addView(btnOne);
        layout.addView(btnTwo);
        //6]레이아웃 전개
        setContentView(layout);
    }///////////////////
}