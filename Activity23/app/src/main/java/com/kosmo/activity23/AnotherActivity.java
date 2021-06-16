package com.kosmo.activity23;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//1]AppCompatActivity
public class AnotherActivity extends AppCompatActivity {
    //2]onCreate 오버라이딩(매개변수 하나짜리)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //3]레이아웃 전개:setContentView(레이아웃 리소스 아이디 혹은 View객체)
        setContentView(R.layout.another_layout);
        Log.i(MainActivity.TAG,"AnotherActivity의 onCreate() invoked");
        findViewById(R.id.btnBackToTheMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재 액티비티가 메모리상에 제거됨.즉 destroy됨.
                //강제종료가 아닌 정상종료:
                //정상 종료이기때문에 onSaveInstanceState()가
                //호출안됨.
                //onPause()->onStop()->onDestory()
                finish();
            }
        });
    }//onCreate
    //4]반드시 매니페스트 파일에 액티비티 등록
    // <activity android:name=".AnotherActivity"  android:label="또다른 액티비티"/>
    // label미 설정시 MainActivity의 레이블 값이 표시됨.

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MainActivity.TAG,"AnotherActivity의 onStart() invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MainActivity.TAG,"AnotherActivity의 onResume() invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MainActivity.TAG,"AnotherActivity의 onPause() invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MainActivity.TAG,"AnotherActivity의 onStop() invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(MainActivity.TAG,"AnotherActivity의 onRestart() invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.TAG,"AnotherActivity의 onDestroy() invoked");
    }
    //생명주기에 속하지 않은 콜백 메소드

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(MainActivity.TAG,"AnotherActivity의 onSaveInstanceState() invoked");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(MainActivity.TAG,"AnotherActivity의 onRestoreInstanceState() invoked");
    }
}
