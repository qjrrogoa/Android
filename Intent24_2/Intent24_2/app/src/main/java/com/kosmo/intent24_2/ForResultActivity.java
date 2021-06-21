package com.kosmo.intent24_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForResultActivity extends AppCompatActivity {

    //리절트 코드]
    //아이디 오류
    public static final int RESULT_CODE_ID_FAIL = 1000;
    //비번오류
    public static final int RESULT_CODE_PWD_FAIL = 1001;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forresult_layout);
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
                //아이디가 "KIM"이고 비밀번호가 "1234"회원이라고 가정]
                //※setResult(int 리절트코드,intent)메소드 호출-
                // 이 메소드 호출과 동시에
                //인텐트를 보낸 액티비티의 onActivityResult()가 호출됨.
                if(!"KIM".equals(user)){
                    intent.putExtra("IDFAIL","아이디가 틀려요");
                    setResult(RESULT_CODE_ID_FAIL,intent);
                }
                else if(!"1234".equals(pass)){
                    intent.putExtra("PWDFAIL","비번이 틀려요");
                    setResult(RESULT_CODE_PWD_FAIL,intent);
                }
                else{
                    intent.putExtra("SUCCESS",user+"님 방가방가!!!");
                    setResult(Activity.RESULT_OK,intent);
                }
                finish();
            }
        });
    }/////////////////onCreate
}
