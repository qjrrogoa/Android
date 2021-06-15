package com.kosmo.touchevent19.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Vector;

//1]View클래스 상속
public class MyTouchDrawing extends View {

    //붓 준비]
    private Paint paint;
    //사용자가 터치한 지점의 좌표를 저장할 컬렉션
    private Vector<Dot> dots = new Vector<>();

    //2-1]XML용 생성자
    public MyTouchDrawing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //붓 생성]
        paint = new Paint();
        //붓 색상 설정]
        paint.setColor(Color.RED);
        //붓 두께 설정]
        paint.setStrokeWidth(20);
        //안티알리아스 설정]
        paint.setAntiAlias(true);
    }
    //그림을 그리기 위해서 onDraw()메소드 오버라이딩]
    @Override
    protected void onDraw(Canvas canvas) {
        //캔바스 배경색 설정]
        canvas.drawColor(Color.argb(100,125,125,125));
        //벡터에 저장된 점의 좌표로 선을 그리자]
        //점이 n개인 경우 n-1개의 선이 그려진다.
        for(int i=0;i < dots.size()-1;i++){
            //Canvas의drawLine(시작점 X좌표, 시작점 Y좌표,끝점 X좌표, 끝점 Y좌표,Paint객체)
            if(dots.get(i).isDrawing()){
                canvas.drawLine(
                        dots.get(i).getxPos(),
                        dots.get(i).getyPos(),
                        dots.get(i+1).getxPos(),
                        dots.get(i+1).getyPos(),
                        paint
                        );
            }
        }
    }
    //콜백 메소드:MyTouchDrawing이라는 내가 만든 뷰 영역을
    //           터치했을때
    //           자동으로 호출되는 콜백 메소드(좌표저장)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            //최초 터치-점의 좌표만 저장]
            dots.add(new Dot((int)event.getX(),(int)event.getY(),false));
            Log.i("com.kosmo.touchevent","[ACTION_DOWN] X좌표:"+event.getX()+",Y좌표:"+event.getY());
        }
        else if(event.getAction()==MotionEvent.ACTION_MOVE){
            //터치후 손가락 이동-점의 좌표 저장하고 그리기위해 true]
            dots.add(new Dot((int)event.getX(),(int)event.getY(),true));
            //invalidate()메소드 호출로 onDraw()호출해서 그린다.
            invalidate();
            Log.i("com.kosmo.touchevent","[ACTION_MOVE] X좌표:"+event.getX()+",Y좌표:"+event.getY());
        }
        return true;//이벤트 전파 방지
    }
}
