package com.kosmo.intent24_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends AppCompatActivity {
    //요청코드]
    //※※※요청코드는 반드시 0 이상인 값을 설정
    //      그렇지 않으면 onActivityResult()가 호출이 안된다.
    //      즉 다시 데이타를 못받는다.
    public static final int REQUEST_CODE_IS_MEMBER = 0;
    public static final int REQUEST_CODE_TEST = 1000;


    private TextView textviewMain;
    private EditText edituser;
    private EditText editpass;
    private Button btnStartActivity;
    private Button btnStartActivityforResult;
    private Button btnStartActivityforResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        initView();
        //리스너 부착]
        btnStartActivity.setOnClickListener(handler);
        btnStartActivityforResult.setOnClickListener(handler);
        btnStartActivityforResult2.setOnClickListener(handler);

    }
    //이벤트 핸들러]
    private View.OnClickListener handler= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //사용자 입력값 받기
            String user = edituser.getText().toString();
            String pass = editpass.getText().toString();
            //인텐트 생성
            Intent intent = new Intent(v.getContext(),StartActivity.class);
            //아이디와 비밀번호 저장
            intent.putExtra("user",user);
            intent.putExtra("pass",pass);
            if(v.getId() ==R.id.btnStartActivity){
                startActivity(intent);
            }
            else if(v.getId()==R.id.btnStartActivityforResult){
                //데이타 전달 및 결과값을 다시 받기]
                /*
                1]startActivityForResult()메소드 로 인텐트 전달
                2]onActivityResult()메소드 오버라이딩
                 */
                //startActivityForResult(Intent,int 요청코드)
                /*
                    Intent :전달할 Intent 객체
                    int  요청코드:인텐트를 받을 액티비티에서 식별자로 사용하거나
                                  혹은 인텐트를 전달한 액티비티에서 식별자로 사용
                 */
                 intent.setClass(v.getContext(),ForResultActivity.class);
                 //startActivityForResult(intent,REQUEST_CODE_IS_MEMBER);
                 //API LEVEL 30이후 코딩(11버전 2020년 9월 8일 정식으로 출시)
                //요청 코드 불필요,onActivityResult 불필요
                resultLauncher.launch(intent);
            }
            else{
                intent.setClass(v.getContext(),ForResultActivity2.class);
                //startActivityForResult(intent,REQUEST_CODE_TEST);
                testResultLauncher.launch(intent);
            }

        }
    };

    private void initView() {
        textviewMain = (TextView) findViewById(R.id.textviewMain);
        edituser = (EditText) findViewById(R.id.edituser);
        editpass = (EditText) findViewById(R.id.editpass);
        btnStartActivity = (Button) findViewById(R.id.btnStartActivity);
        btnStartActivityforResult = (Button) findViewById(R.id.btnStartActivityforResult);
        btnStartActivityforResult2 = (Button) findViewById(R.id.btnStartActivityforResult2);
    }
    //다시 결과값을 받기 위해서는 아래 메소드 오버라이딩
    //즉 인텐트를 전달받은 액티비티에서 setResult()메소드 호출시
    //아래 메소드가 자동으로 호출된다.
     /*
    requestCode:내가 보낸 인텐트 확인용(요청코드)
    resultCode:인텐트를 받은 액티비티에서 보낸 코드
    data:인텐트를 받은 액티비티에서 보낸 인텐트
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //내가 보낸 요청코드를 받은 액티비티인지 판단.
        if(requestCode == REQUEST_CODE_IS_MEMBER){
            switch(resultCode){
                case ForResultActivity.RESULT_CODE_ID_FAIL:
                    textviewMain.setText(data.getStringExtra("IDFAIL"));
                    break;
                case ForResultActivity.RESULT_CODE_PWD_FAIL:
                    textviewMain.setText(data.getStringExtra("PWDFAIL"));
                    break;
                default:
                    textviewMain.setText(data.getStringExtra("SUCCESS"));
            }
        }
        else if(requestCode==REQUEST_CODE_TEST){
            textviewMain.setText(data.getStringExtra("TEST"));
        }
    }///////////////////////////onActivityResult

    //API LEVEL 30 이후 코딩 방식 - startActivityForResult() Deprecated / onActivityResult()오버라이딩 불필요
    ActivityResultLauncher<Intent> resultLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    switch(result.getResultCode()){
                        case ForResultActivity.RESULT_CODE_ID_FAIL:
                            textviewMain.setText(result.getData().getStringExtra("IDFAIL"));
                            break;
                        case ForResultActivity.RESULT_CODE_PWD_FAIL:
                            textviewMain.setText(result.getData().getStringExtra("PWDFAIL"));
                            break;
                        default:
                            textviewMain.setText(result.getData().getStringExtra("SUCCESS"));
                    }
                }
            });

    ActivityResultLauncher<Intent> testResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            textviewMain.setText(result.getData().getStringExtra("TEST"));
        }
    });

}