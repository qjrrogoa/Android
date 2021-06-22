package com.kosmo.gridview28;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//1]BaseAdapter상속
public class CustomAdapter extends BaseAdapter {

    //그리드뷰가 위치하는 컨텍스트
    private Context context;
    //영화 제목
    private String[] movies;
    //이미지 리소스 아이디
    private int[] resIds;
    //생성자]
    public CustomAdapter(Context context, String[] movies, int[] resIds) {
        this.context = context;
        this.movies = movies;
        this.resIds = resIds;
    }
    //2]4개 메소드 오버라이딩
    //position에 해당하는 아이템의 아이디값
    @Override
    public long getItemId(int position) {
        return position;
    }
    //position에 해당하는 아이템 반환
    @Override
    public Object getItem(int position) {
        return movies[position];
    }
    //아이템 총 갯수 반환]
    @Override
    public int getCount() {
        return movies.length;
    }
    //하나의 아이템용 뷰 반환 그리드뷰에 표시할 하나의 아이템용
    //뷰 반환
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView=View.inflate(context,R.layout.item_layout,null);
        }
        //위젯 얻고  position인덱스에 해당하는 각 위젯의 데이타 설정
        ImageView imageView=convertView.findViewById(R.id.movieicon);
        TextView textView = convertView.findViewById(R.id.movietitle);
        //position에 위치한 데이타로 뷰 설정
        imageView.setImageResource(resIds[position]);
        textView.setText(movies[position]);
        /*
        //convertView에 리스너 부착
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼로그용 레이아웃 전개
                View dialogView=View.inflate(context,R.layout.dialog_layout,null);
                //다이얼 로그용 이미지에 클릭한 해당 이미지 설정]
                ImageView movieDialog=dialogView.findViewById(R.id.movieDialog);
                movieDialog.setImageResource(resIds[position]);

                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_menu_search)
                        .setTitle(movies[position])
                        .setView(dialogView)
                        .show();

            }
        });
        */
        return convertView;
    }
}
