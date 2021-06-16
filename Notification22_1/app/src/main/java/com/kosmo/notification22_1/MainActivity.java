package com.kosmo.notification22_1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    //방법1-안드로이드에서 제공하는 CountDownTimer클래스 사용
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        initView();

        //방법1-안드로이드에서 제공하는 CountDownTimer클래스 사용
        //첫번째 인자:1000분의 1초단위. 지정한 시간후에 onFinish()가 호출됨
        //두번째 인자:1000분의 1초단위. 지정한 간격시간마다 onTick()이 호출됨
        timer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("com.kosmo.notification","1초마다 호출됨");
            }
            @Override
            public void onFinish() {
                //3초후에 프로그레스 다이얼로그 닫기
                progressDialog.dismiss();
            }
        };

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
        });///////////////////////////////////
        //목록형 대화상자 띄우기]
        btnAlertItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_lock_power_off)
                        .setTitle("당신이 좋아하는 스포츠는?")
                        .setItems(sports, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //which:선택한 아이템의 인덱스
                                Toast.makeText(v.getContext(),"당신이 좋아하는 스포츠는 "+sports[which],Toast.LENGTH_SHORT).show();
                            }
                        })
                        //.setMessage("내가 좋아하는 스포츠는 축구")
                        .setNegativeButton("취소", null)
                        .show();

            }
        });/////////////////////////////////////////
        //라디오형 대화상자-하나만 선택 가능]
        btnAlertRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_menu_save)
                        .setTitle("당신이 좋아하는 스포츠는?")
                        //두번째 인자는 체크된 상태로 보여줄 아이템의 인덱스값
                        //-1이면 아무것도 선택이 안된 상태
                        .setSingleChoiceItems(sports, -1, new DialogInterface.OnClickListener() {
                            //which:선택한 아이템의 인덱스
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(v.getContext(),"which"+which,Toast.LENGTH_SHORT).show();
                                which_radio=which;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//which: 무조건 값이 -1
                                if(which_radio !=-1)
                                    Toast.makeText(v.getContext(),"당신이 좋아하는 스포츠는 "+sports[which_radio],Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
            }
        });///////////////////////////////
        //체크박스형 대화상자-여러개 선택 가능]
        btnAlertCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_menu_compass)
                        .setTitle("What Sports Do you like?")
                        //new boolean[]-두번째 인자는 선택된 상태로 보여줄 boolean형 배열
                        .setMultiChoiceItems(sports, which_checks, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                which_checks[which]=isChecked;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            //which: 무조건 값이 -1
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String checked="";
                                for(int i=0;i < which_checks.length;i++){
                                    if(which_checks[i]) checked+=sports[i]+" ";
                                }
                                Toast.makeText(v.getContext(),checked,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소",null)
                        .create()
                        .show();
            }
        });/////////////////////////////////////
        //진행대화상자 띄우기]
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.빌더로 AlertDialog생성
                progressDialog = new AlertDialog.Builder(v.getContext())
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("로그인")
                        .setView(R.layout.progress_layout)
                        .create();
                //2.AlertDialog의 show()로 보이기
                progressDialog.show();
                //방법1-안드로이드에서 제공하는 CountDownTimer클래스 사용해서
                //     자동으로 3초후에 닫기
                //timer.start();
                //방법2]Handler사용
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {//3초후에 실행됨
                        progressDialog.dismiss();
                    }
                }, 3000);
            }
        });
        //커스텀 대화상자]
        /*
        1.layout폴더에 사용자 정의용 xml생성
        2.getLayoutInflater()메소드로 LayoutInflater객첵 얻어서 inflate한다.
        3.AlertDialog.Builder객체의 setView()메소드로
          2번에서 전개한 레이아웃 뷰 설정
         */
        btnCustom.setOnClickListener(new View.OnClickListener() {
            private AlertDialog customDialog;

            @Override
            public void onClick(View v) {
                //커스텀 대화상자 레이아웃 전개
                View view = View.inflate(v.getContext(),R.layout.custom_layout,null);
                //인플레이트된 뷰로 findViewById()
                EditText editText=view.findViewById(R.id.editText);
                Button btnOk = view.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        //대화창 닫기
                        customDialog.dismiss();

                    }
                });


                customDialog=new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_menu_gallery)
                        .setTitle("커스터마이징 대화상자")
                        //.setView(R.layout.custom_layout) 이벤트 처리 하지 않을때 그대로 뷰만 보여줄때
                        .setView(view)//이벤트 처리시
                        .create();
                customDialog.show();
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