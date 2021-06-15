package com.kosmo.notification22_1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textview;
    private Button btnAlertBasic;
    private Button btnAlertItems;
    private Button btnAlertRadio;
    private Button btnAlertCheck;
    private Button btnProgress;
    private Button btnCustom;
    /*
  체크박스형태나 목록형 그리고 라디오버튼형으로
  대화상자를 띄울때는 setMessage()대신
  목록형:setItems()
  체크박스형:setMultiChoiceItems()
  라디오버튼형:setSingleChoiceItems()메소드 사용
  setMessage()동시 사용시 setMessage()가 적용됨
  */

    //목록에 뿌려줄 데이타]
    private String[] sports={"축구","배구","농구","야구"};
    //라디오 버튼 형태에서 선택한 스포츠 종목의 인덱스 저장용]
    private int which_radio = -1;
    //체크한 아이템 저장용]
    private boolean[] which_checks = {true,false,true,false};
    //프로그레스 대화상자관련 변수]
    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        initView();
        //기본 대화상자 띄우기]
        btnAlertBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setCancelable(false)//디폴트 true
                        .setIcon(android.R.drawable.ic_menu_call)
                        .setTitle("올레 서비스")
                        .setMessage("올레 서비스에 가입 하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(v.getContext(),"가입절차 진행하겠습니다",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            }
        });
    }///////////////onCreate

    private void initView() {
        textview = (TextView) findViewById(R.id.textview);
        btnAlertBasic = (Button) findViewById(R.id.btnAlertBasic);
        btnAlertItems = (Button) findViewById(R.id.btnAlertItems);
        btnAlertRadio = (Button) findViewById(R.id.btnAlertRadio);
        btnAlertCheck = (Button) findViewById(R.id.btnAlertCheck);
        btnProgress = (Button) findViewById(R.id.btnProgress);
        btnCustom = (Button) findViewById(R.id.btnCustom);
    }
}