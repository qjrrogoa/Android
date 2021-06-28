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

public class KakaoAdapter extends RecyclerView.Adapter<KakaoAdapter.ViewHolder> {

    private Context context;
    private KakaoVisionItem items;

    //인자 생성자
    public KakaoAdapter(Context context, KakaoVisionItem items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView = View.inflate(context,R.layout.photo_layout,null); //[X]
        //카드뷰 사용시 반드시 parent는 지정해야한다.
        View itemView = LayoutInflater.from(context).inflate(R.layout.kakao_layout,parent,false);
        //View itemView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.photo_layout,parent,false);
        return new ViewHolder(itemView);
    }///onCreateViewHolder

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemProductName.setText(items.getResult().getObjects().get(position).getProductName());
        holder.itemScore.setText(String.format("정확도 : %f",items.getResult().getObjects().get(position).getScore()));
        holder.itemCoordinates.setText(String.format("좌표 X1:%f, X2:%f, Y1:%f, Y2:%f",items.getResult().getObjects().get(position).getX1(),items.getResult().getObjects().get(position).getX2(),items.getResult().getObjects().get(position).getY1(),items.getResult().getObjects().get(position).getY2()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"네이버 쇼핑으로 뷰전환",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,NaverShoppingActivity.class);
                intent.putExtra("kakaoProduct",items.getResult().getObjects().get(position).getProductName());
                context.startActivity(intent);
            }//onClick
        });//setOnClickListener
    }///onBindViewHolder

    @Override
    public int getItemCount() {
        return items.getResult().getObjects().size();
    }///getItemCount

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView itemProductName;
        TextView itemScore;
        TextView itemCoordinates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemProductName = itemView.findViewById(R.id.itemProductName);
            itemScore = itemView.findViewById(R.id.itemScore);
            itemCoordinates = itemView.findViewById(R.id.itemCoordinates);
        }///ViewHolder
    }////ViewHolder

}////PhotoAdapter