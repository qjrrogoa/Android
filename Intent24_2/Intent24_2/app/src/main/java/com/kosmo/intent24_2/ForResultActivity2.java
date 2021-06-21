package com.kosmo.intent24_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForResultActivity2 extends AppCompatActivity {

    public static final int RESULT_CODE_TEST=1000;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forresult2_layout);
        //전달받은 인텐트에 저장된 데이타 얻기]
        Intent intent = getIntent();
        String user= intent.getStringExtra("user");
        String pass= intent.getStringExtra("pass");
        //텍스트뷰에 표시
        ((TextView)findViewById(R.id.textStartActivityForResult2))
                .setText("아이디:"+user+",비번:"+pass);
        findViewById(R.id.btnStartActivityForResultFinish2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("TEST","요청코드 추가 테스트 입니다");
                //setResult(RESULT_CODE_TEST,intent);
                finish();
            }
        });
    }
}
