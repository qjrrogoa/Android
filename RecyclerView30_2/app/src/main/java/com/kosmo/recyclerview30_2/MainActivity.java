package com.kosmo.recyclerview30_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        //데이타 준비
        List<Item> items = new Vector<>();
        for(int i=1;i <=50;i++) items.add(new Item(i+"번째 제목",R.drawable.rounded));
        //어댑터 생성
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this,items);
        //리사이클뷰와 어댑터 연결
        recyclerView.setAdapter(adapter);
        //레이아웃 설정-세로방향
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //가로방향
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

   
}