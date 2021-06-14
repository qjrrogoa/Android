package com.kosmo.customview10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnShort,btnLong,btnCustom;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //앱 타이틀 설정
        setTitle("토스테에 커스텀 뷰 적용");
        setContentView(R.layout.activity_main);

        //위잿 얻기
        btnShort = findViewById(R.id.short_message_button);
        btnLong = findViewById(R.id.long_message_button);
        btnCustom = findViewById(R.id.custom_message_button);
        editText = findViewById(R.id.edt_message);

        //리스너 부착]
        btnShort.setOnClickListener(listener);
        btnLong.setOnClickListener(listener);
        btnCustom.setOnClickListener(listener);
    }
    //이벤트 핸들러
    private View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
           String message= editText.getText().toString();
           if(message.trim().equals("")){
               Toast.makeText(v.getContext(),"메시지를 입력하세요",Toast.LENGTH_SHORT).show();
               return;
           }
           if(v.getId()==R.id.short_message_button){
               Toast.makeText(v.getContext(),message,Toast.LENGTH_SHORT).show();
           }
           else if(v==btnLong){
               Toast.makeText(v.getContext(),message,Toast.LENGTH_LONG).show();
           }
           else{
                //내가 만든 레이아웃을 토스트에 적용
               //레이아웃용 xml을 메모리에 전개] - inflate한다
               //방법1]View의 정적 메서드 inflate()
               //View view = View.inflate(v.getContext(),R.layout.custom_toast,null);

               //방법2] 시스템 서비스 사용:Object get SystemService(Context String형 상수)
               //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               //View view = inflater.inflate(R.layout.custom_toast,null);

               //방법3] LayoutInflater의 정적 메서드인 FROM()메서드 사용
               View view = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_toast,null);
               TextView textView=view.findViewById(R.id.textView);
               textView.setText(message);
               textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
               textView.setTextColor(Color.GREEN);

               Toast toast = new Toast(v.getContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(view);
               toast.show();

           }
        }/////onClick
    };///

}