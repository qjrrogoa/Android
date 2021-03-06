package com.kosmo.jsonparse34_3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NaverShoppingAdapter extends RecyclerView.Adapter<NaverShoppingAdapter.ViewHolder> {

    private Context context;
    private NaverShoppingItem items;

    //인자 생성자
    public NaverShoppingAdapter(Context context, NaverShoppingItem items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView = View.inflate(context,R.layout.photo_layout,null); //[X]
        //카드뷰 사용시 반드시 parent는 지정해야한다.
        View itemView = LayoutInflater.from(context).inflate(R.layout.naver_shopping_layout,parent,false);
        //View itemView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.photo_layout,parent,false);
        return new ViewHolder(itemView);
    }///onCreateViewHolder

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTitle.setText(items.getItems().get(position).getTitle());
        holder.itemPrice.setText(String.format("최고가 : %s, 최저가 : %s",items.getItems().get(position).getHprice(),items.getItems().get(position).getLprice()));
        holder.itemMaker.setText(String.format("제조사 : %s(%s)",items.getItems().get(position).getBrand(),items.getItems().get(position).getMaker()));

        //https://square.github.io/picasso/
        Picasso.get().load(items.getItems().get(position).getImage()).into(holder.itemImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,String.valueOf(items.getItems().get(position).getTitle())+"의 상세 페이지로 이동",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ProductWebActivity.class);
                intent.putExtra("link",items.getItems().get(position).getLink());
                context.startActivity(intent);
            }//onClick
        });//setOnClickListener
    }///onBindViewHolder

    @Override
    public int getItemCount() {
        return items.getItems().size();
    }///getItemCount

    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView itemImage;
        TextView itemMaker;
        TextView itemTitle;
        TextView itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemMaker = itemView.findViewById(R.id.itemMaker);
            itemPrice = itemView.findViewById(R.id.itemPrice);

        }///ViewHolder
    }////ViewHolder

}////PhotoAdapter