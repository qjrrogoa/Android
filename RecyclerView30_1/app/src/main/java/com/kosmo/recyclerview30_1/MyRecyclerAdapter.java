package com.kosmo.recyclerview30_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    //생성자]
    public MyRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }////////////////////

    //하나의 아이템을 표시할 뷰 전개후 ViewHolder객체를 생성해서 반환
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //하나의 아이템을 표시할 뷰 전개
        View itemView =LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        //ViewHolder객체 반환
        return new ViewHolder(itemView);
    }
    //뷰홀더에서 얻은 위젯에 데이타 바인딩
    @Override
    public void onBindViewHolder(@NonNull  MyRecyclerAdapter.ViewHolder holder, int position) {
        //position에 해당하는 데이타(Item) 추출
        Item item=items.get(position);
        //위제에 데이타 바인딩
        holder.imageView.setImageResource(item.getItemImageResId());
        holder.textViewName.setText(item.getItemName());
        holder.textViewDept.setText(item.getItemDept());
        holder.textViewDate.setText(item.getItemDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getItemName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //아이템의 총 수 반환
    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName;
        TextView textViewDept;
        TextView textViewDate;
        CardView cardView;//클릭 이벤트 처리용
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            textViewName = itemView.findViewById(R.id.itemName);
            textViewDept = itemView.findViewById(R.id.itemDept);
            textViewDate = itemView.findViewById(R.id.itemDate);
            cardView = itemView.findViewById(R.id.cardview);
        }////////////////////////////
    }///////////////////////////////
}
