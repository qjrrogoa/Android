package com.kosmo.customview10_1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.kosmo.customview10_1.R;

//1]View클래스 상속
public class CustomView extends View {
    //2]Context를 매개변수로 받은 생성자[자바코드용]
    public CustomView(Context context){
        super(context);
    }
    //3]onDraw()메소드 오버라이딩
    @Override
    protected void onDraw(Canvas canvas) {
        //배경색 설정]
       canvas.drawColor(Color.CYAN);
        //붓 종류 설정]
        //경계부분을 부드럽게 처리할 수 있는 붓 사용
        //Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //붓 색상 설정]
        paint.setColor(Color.RED);
        //붓 두께 설정]
        paint.setStrokeWidth(30);//단위는 픽셀
        //점 그리기
        canvas.drawPoint(100,100,paint);
        //선긋기]
        paint.setStrokeWidth(10);
        canvas.drawLine(150,150,500,150,paint);
        //사각형]
        paint.setColor(0x880000FF);
        canvas.drawRect(150,150,500,350,paint);
        //원]
        paint.setColor(Color.argb(255,0,255,0));
        canvas.drawCircle(250,450,100,paint);
        //이미지 그리기]
        //※Paint는 null지정 그래야 이미지 원래 색대로 그려짐
        //원본 크기 그대로 출력
        //1]drawBitmap(Bitmap객체,x좌표,y좌표,Paint객체);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_airplane_mode_alpha);
        //canvas.drawBitmap(bitmap,250,550,null);
        //2]drawBitmap(Bitmap객체,null,Rect dest,Paint객체-null)
        //null지정시 원본이미지 전체가 선택된 후 확대 축소 가능.
        //canvas.drawBitmap(bitmap,null,new Rect(250,550,250+400,550+400),null);
        //3]drawBitmap(Bitmap객체,Rect src,Rect dest,Paint객체)
        //원본의 특정 부분 선택후 확대 축소
        int width=bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i("com.kosmo.customview","width:"+width+",height:"+height);
        canvas.drawBitmap(bitmap,new Rect(0,0,80,80),new Rect(250,550,250+400,550+400),null);
    }
}
