package com.kosmo.recyclerview30_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Item> items;

    public MyRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //아이템뷰 전개
        View itemView=LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        //뷰홀더 객체 생성후 반환
        return new MyViewHolder(itemView);
    }
    //데이터 바인딩
    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        //position인덱스의 데이타
        Item item = items.get(position);
        //위의 데이터로 바인딩
        ((MyViewHolder)holder).itemImage.setImageResource(item.getItemImageResId());
        ((MyViewHolder)holder).itemTitle.setText(item.getItemTitle());
        ((MyViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getItemTitle(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
