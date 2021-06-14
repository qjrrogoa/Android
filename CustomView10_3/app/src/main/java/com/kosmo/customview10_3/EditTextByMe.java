package com.kosmo.customview10_3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

//1] 기존 뷰 상속
public class EditTextByMe extends AppCompatEditText {
    //2]생성자 정의
    //자바코드에서 new연산자로 직접 EditTextByMe 객체 생성시 호출
    public EditTextByMe(Context context) {
        super(context);
        Log.i("com.kosmo.customview","자바코드용 생성자");
    }

    //XML에 등록된 위젯이 생성될 때 호출되는 생성
    public EditTextByMe(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("com.kosmo.customview","xml용 생성자");
    }
    //3]인터페이스 정의(내부 인터페이스)
    public interface OnTextLengthListener{
        //추상 메서드]
        //매개변수 : 사용자가 입력한 문자열 갯수
        void onTextLength(int textLength);
    }

    //4] 인터페이스 타입의 리스너 선언
    private OnTextLengthListener onTextLengthListener;
    public void setOnTextLengthListener(OnTextLengthListener onTextLengthListener) {
        this.onTextLengthListener = onTextLengthListener;
    }

    @Override
    protected void onTextChanged(
            CharSequence text, //에디트 텍스트에 입력된 문자열
            int start, //추가한 문자열의 시작 인덱스
            int lengthBefore, //삭제한 문자열의 갯
            int lengthAfter)  //추가한 문자열의 갯
        {
            //EditText에 기존 기능(TextChanged) 유지하면서 나만의 새로운 기능 추가]
            Log.i("com.kosmo.customview",text.toString());
            if(onTextLengthListener !=null)
                //즉 사용자가 EditText에 텍스트를 입력할때마다
                //입력한 문자열의 길이를 알아내도록 한는 나만의 에디트 텍스트
                onTextLengthListener.onTextLength(text.toString().length());
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
