package com.kosmo.listview27_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//1]ListActivity상속-가장 빠르게 아이템(데이타)을 목록 형태로 표시하고자 할때
//                   레이아웃 전개도 필요없다
public class MainActivity extends ListActivity/*AppCompatActivity*/ {
    //2]데이타용
    private String[] items ={"지리산","설악산","덕유산","태백산","비슬산","대둔산","치악산","한라산","성인봉","북한산","도봉산","관악산","사패산","응봉산"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //아이템(데이타)의 목록만 표시할때 주석처리(레이아웃 전개)
        setContentView(R.layout.activity_main);
        //3]어댑터 생성
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        //4]연결-ListActivity상속시는 setAdapter()가 아니나 setListAdapter()로 연결
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //xml레이아웃으로 헤더와 푸터를 붙이지 않고
        //자바코드로 헤더와 푸터를 붙인 경우 position하고 id 값이 다르다
        //position:클릭한 아이템의 인덱스 값(헤더는 0 푸터는 배열의 length혹은 length+1(헤더포함시))
        //id:실제 데이타 아이템의 인덱스값(0부터 시작:헤더나 푸터는 -1)
        Toast.makeText(this,String.format("position:%s,id:%s,%s 선택",position,id,items[(int)id]),Toast.LENGTH_SHORT).show();
    }////////////
}