package com.kosmo.resource09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/*
res디렉토리]

   drawable - 이미지,xml파일
   values-문자열,배열,색상,크기등을 정의한 xml파일

   [레이아웃용 xml(activity_main.xml)에서 res폴더에 있는 리소스참조시]

   drawable디렉토리:@drawale/이미지파일명

   values디렉토리:
   문자열-@string/name속성값
   색상-@color/name속성값
   크기-@dimen/name속성값

   [자바소스에서 참조시]
   1]Resources resource = getResources();
   drawable디렉토리:
   resource.getDrawable(R.drawable시작하는 리소스아이디)
   values디렉토리:
   문자열-resource.getString(R.string시작하는 리소스 아읻)
   색상-resources.getColor() 혹은 ContextCompat.getColor(Context,R.color시작하는 리소스아이디)
   크기-resource.getDimension(R.dimen시작하는 리소스아이디)
*/
/*
    cannot resolve symbol~ 해결법
    1. Build메뉴
       Clean project->Rebuild Project
    2. File 메뉴
       Sync Project with Gradle Files
    3. File 메뉴
       Invalidate Cache/Restart
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        TextView textView = findViewById(R.id.codeTextview);
        ImageView imageView = findViewById(R.id.codeImageview);
        Button button = findViewById(R.id.button);
        //1]res폴더에 정의된 모든 리소스(비트맵,XML등)를
        // 얻어 올수 있는 클래스:Resources-getResources()메소드로 얻는다.
        Resources resources= getResources();
         /*※values폴더의 strings.xml과
             drawable폴더에 있는 이미지 접근시 직접
             getString()이나 getDrawable()로 직접 접근 가능- Resources객체 불필요*/
        Log.i("com.kosmo.resource09",
                String.format("Resources객체 미 사용:%s,Resources객체 사용:%s",
                        getString(R.string.codeMessage),
                        resources.getString(R.string.codeMessage)));
        //2]자바코드로 위젯의 속성 설정
        //android:text속성]
        //2-1]setText(int resId)사용
        //textView.setText(R.string.codeMessage);
        textView.setText(getString(R.string.codeMessage));
        //android:textSize속성]
        //2-3]setTextSize(int unit,float size)
        //숫자 직접 지정
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        //아래는 20sp보다 더 크게 표시됨
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,resources.getDimension(R.dimen.xmlDimen));
        /*
         dimens.xml에서 sp로 설정한 값을 자바코드로 적용시 아래 코드로 읽어와야
        크기가 똑같이 적용됨 즉 숫자로직접 지정했을때와 같다
        ※ getDimension() 메소드가 pixel 형태로 리턴한다
          dimens.xml에 sp라고 명시시
          getDimension () 은 알아서 pixel로 변환해서 리턴
          */
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,resources.getDimension(R.dimen.xmlDimen));
        //2-4]setTextColor(int color) 사용
        //textView.setTextColor(Color.RED);
        //textView.setTextColor(resources.getColor(R.color.xmlColor));
        textView.setTextColor(ContextCompat.getColor(this,R.color.xmlColor));
        //android:src속성 설정]
        //방법1]ImageView객체.setImageResource(int 리소스 아이디)
        //imageView.setImageResource(R.drawable.picture_emergency);
        //방법2]ImageView객체.setImageDrawable(Drawable)
        //imageView.setImageDrawable(resources.getDrawable(R.drawable.picture_emergency,null));
        //방법3]ImageView객체.setImageBitmap(Bitmap)
        //방법3-1]
        //Bitmap bitmap=((BitmapDrawable)getDrawable(R.drawable.picture_emergency)).getBitmap();
        //imageView.setImageBitmap(bitmap);
        //방법3-2]BitmapFactory.decodeResource(Resources객체,리소스 아이디)
        //방법1이나 방법3-2주로 사용
        imageView.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.picture_emergency));
        //버튼에 리스너 부착]
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*
                int형 배열 얻어 올때-resources.getIntArray(int 리소스아이디)
                String형 배열 얻어 올때-resources.getStringArray(int 리소스아이디)
                */
                Toast.makeText(v.getContext(), Arrays.toString(resources.getIntArray(R.array.intArray)),Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(),Arrays.toString(resources.getStringArray(R.array.stringArray)),Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(),Arrays.toString(resources.getStringArray(R.array.imageFor)),Toast.LENGTH_SHORT).show();
            }
        });

    }/////////////onCreate
}////////////////class