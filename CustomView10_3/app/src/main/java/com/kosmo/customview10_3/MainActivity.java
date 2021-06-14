package com.kosmo.customview10_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int inputCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 용 xml전개]
        setContentView(R.layout.activity_main);

        //위젯 얻기
        TextView textView = findViewById(R.id.textview);
        EditText editText = findViewById(R.id.basic_edittext);
        LinearLayout layout = findViewById(R.id.activity_main);
        //1]내가 만든 EditText를 자바코드로 부착하는 경우
        //EditTextByMe editTextByMe = new EditTextByMe(this);
        //editTextByMe.setHint("커스텀 에디트 텍스트");
        //레이아웃에 부착 :addView(View view)
        //layout.addView(editTextByMe);

        //2]xml에 내가 만든 EditText를 태그로 등록한 경우
        EditTextByMe editTextByMe = findViewById(R.id.editTextMe);

        //기본 에디트 텍스트의 텍스트변화 코딩
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("com.kosmo.customview","[beforeTextChanged]");
                Log.i("com.kosmo.customview",String.format("s:%s,start:%s,count:%s,after:%s",s,start,count,after));
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*
                s:에디트 텍스트에 입력됨 문자열
                start:새로 추가된 문자열의 시작 인덱스
                before:삭제된 문자열의 갯수
                count:새로 추가된 문자열의 갯수
                 */
                //입력된 문자열의 갯수 가져오기
                inputCount += count- before;
                //텍스트뷰에 출력
                textView.setText(String.valueOf(inputCount));
                Log.i("com.kosmo.customview","[onTextChanged]");
                Log.i("com.kosmo.customview",String.format("s:%s,start:%s,count:%s,before:%s",s,start,count,before));
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.i("com.kosmo.customview","[afterTextChanged]");
                Log.i("com.kosmo.customview",String.format("s:%s",s));
            }
        });
        //나만의 에디트 텍스트의 텍스트변화 코딩
        editTextByMe.setOnTextLengthListener(new EditTextByMe.OnTextLengthListener() {
            @Override
            public void onTextLength(int textLength) {
                textView.setText(String.valueOf(textLength));
            }
        });
    }
}