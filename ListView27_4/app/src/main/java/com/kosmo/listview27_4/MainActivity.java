package com.kosmo.listview27_4;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyCustomAdapter adapter;
    private List<Item> items = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젝 얻기
        listView = findViewById(R.id.mylistView);
        //데이타 준비
        for(int i=0;i <=50;i++){
            items.add(new Item("코스모"+i,"마켓팅부"+i,"2021-6-22",R.drawable.rounded));
        }
        //어댑터 생성]
        adapter = new MyCustomAdapter(this,items,R.layout.item_layout);
        //어댑터 연결]
        listView.setAdapter(adapter);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),items.get(position).getItemName()+"선택",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}