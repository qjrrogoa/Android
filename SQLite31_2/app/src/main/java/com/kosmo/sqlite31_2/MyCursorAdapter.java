package com.kosmo.sqlite31_2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/*
    컬럼의 데이타를 표시하기 위해 텍스트뷰뿐만 아니라 이미지뷰 같은것도 사용하기 위함

    1]CusorAdapter상속
    2]생성자 정의
    3]newView()와 bindView()메소드 오버라이딩-
    커서 어댑터가 아닌 다른 어댑터(BaseAdapter상속)의 getView()기능과 동일
    getView() = newView(껍데기 생성)+bindView(데이타 표시)

 */
public class MyCursorAdapter extends CursorAdapter {

    //생성자 정의]
    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    //리스트뷰에 뿌려줄 아이템(레코드) 뷰(레이아웃) 반환
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //두번째 인자인 ViewGroup는 반드시 null지정
        //방법1]getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.record_layout,null);
        //방법2]LayoutInflater의 from(context)정적 메소드
        //View view = LayoutInflater.from(context).inflate(R.layout.record_layout,null);
        //방법3]View.inflate(Context,레이아웃 리소스아이디,뷰그룹)
        View view = View.inflate(context,R.layout.record_layout,null);
        return view;
    }
    //newView()메소드에서 반환한 view를 인자로 받는다.
    //커서에 있는 데이타를 뷰에 설정한다]
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //아이템용 위젯 얻기]
        TextView tvAge=view.findViewById(R.id.tvage);
        TextView tvDate=view.findViewById(R.id.tvdate);
        TextView tvName=view.findViewById(R.id.tvname);
        TextView tvUser=view.findViewById(R.id.tvuser);
        //텍스트 뷰에 데이타 설정-데이타는 커서에서 얻어온다
        tvAge.setText(cursor.getString(cursor.getColumnIndex("age")));
        tvDate.setText(cursor.getString(cursor.getColumnIndex("regidate")));
        tvName.setText(cursor.getString(cursor.getColumnIndex("name")));
        tvUser.setText(cursor.getString(cursor.getColumnIndex("user")));
    }
}
