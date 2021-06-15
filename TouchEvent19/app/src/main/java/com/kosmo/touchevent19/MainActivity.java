package com.kosmo.touchevent19;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonClick;
    private Button buttonTouch;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        initView();
        /*
        ※뷰(화면) 클릭시(터치시)에 액션을 취하려면  보통 onClickListener를
          붙인다.
          단,뷰(화면) 클릭시(터치시)에 그림을 그릴때는 onTouchListener부착
          고로 버튼에는 onClickListener를 부착하는게 유리하다.
         */
        buttonClick.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Log.i("com.kosmo.touchevent","버튼에서 클릭 이벤트 발생");
               }
           }
        );
        buttonTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Log.i("com.kosmo.touchevent","버튼에서 터치(DOWN) 이벤트 발생");
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    Log.i("com.kosmo.touchevent","버튼에서 터치(UP) 이벤트 발생");
                }
                else if(event.getAction()==MotionEvent.ACTION_MOVE){
                    Log.i("com.kosmo.touchevent","버튼에서 터치(MOVE) 이벤트 발생");
                }

                //※버튼은 터치 이벤트를 false를 반환하든 true를
                //반환하든 이벤트 전달이 안된다.
                return false;
            }
        });

        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //※버튼을 제외한 대부분의 뷰는
                //true반환시 다른 뷰에 사용자의 터치 이벤트가 전달안됨.
                //false반환시는 다른 뷰에도 터치 이벤트가 전달됨.
                Log.i("com.kosmo.touchevent","이미지뷰에서 터치 이벤트 발생");
                return true;
            }
        });

    }
    //컨텍스트의 다른 뷰에서 발생한 터치 이벤트가 전달되는지
    //판단하기위해 오버라이딩
    //콜백 메소드 오버라이딩]-Activity의 onTouchEvent() 콜백 메소드
    //컨텍스트의 화면 터치시마다(클릭) 자동으로 호출되는 메소드
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("com.kosmo.touchevent","컨텍스트에서 터치 이벤트 발생");
        //컨텍스트에서 터치 이벤트 처리 완료하겠다라는 의미]
        return true;
    }

    private void initView() {
        buttonClick = findViewById(R.id.button_click);
        buttonTouch = findViewById(R.id.button_touch);
        imageview = findViewById(R.id.imageview);
    }
}