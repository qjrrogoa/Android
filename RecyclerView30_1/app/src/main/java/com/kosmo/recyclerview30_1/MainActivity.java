package com.kosmo.recyclerview30_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;
    private List<Item> items = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        //데이타 준비
        for(int i=0;i <=50;i++){
            items.add(new Item("코스모"+i,"마켓팅부"+i,"2021-6-22",R.drawable.rounded));
        }
        //어댑터 생성
        adapter = new MyRecyclerAdapter(this,items);
        //리사이클러뷰와 어댑터 연결
        recyclerView.setAdapter(adapter);
        //레이아웃 매니저 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//디폴트 는 세로방향
        //가로 방향으로 바꾸기
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        //2은  가로방향 카드 갯수 즉 컬럼수
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
}