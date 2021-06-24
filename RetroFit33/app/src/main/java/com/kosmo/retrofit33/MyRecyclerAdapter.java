package com.kosmo.retrofit33;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<JsonPlaceDTO> items;
    //생성자]
    public MyRecyclerAdapter(Context context, List<JsonPlaceDTO> items) {
        this.context = context;
        this.items = items;
    }////////////////////

    //하나의 아이템을 표시할 뷰 전개후 ViewHolder객체를 생성해서 반환
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //하나의 아이템을 표시할 뷰 전개
        View itemView =LayoutInflater.from(context).inflate(R.layout.jsonplacedto_layout,parent,false);
        //ViewHolder객체 반환
        return new ViewHolder(itemView);
    }
    //뷰홀더에서 얻은 위젯에 데이타 바인딩
    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        //position에 해당하는 데이타(Item) 추출
        JsonPlaceDTO item=items.get(position);
        for(JsonPlaceDTO dto:items){
            Log.i("com.kosmo.retrofit",String.format("어댑터-번호:%s,제목:%s",dto.getId(),dto.getTitle()));
        }
        //배경색 지정
        if(position % 2==0)
            holder.itemLayout.setBackgroundColor(0x22b8860b);
        else
            holder.itemLayout.setBackgroundColor(0x22FA8072);
        //위제에 데이타 바인딩
        holder.textUserId.setText(String.valueOf(item.getUserId()));
        holder.textId.setText(String.valueOf(item.getId()));
        holder.textTitle.setText(item.getTitle());
        holder.textBody.setText(item.getBody());
        Log.i("com.kosmo.retrofit","제목:"+item.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //아이템의 총 수 반환
    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textUserId;
        TextView textId;
        TextView textTitle;
        TextView textBody;
        CardView cardView;//클릭 이벤트 처리용
        LinearLayout itemLayout;//배경색 처리용
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            textUserId = itemView.findViewById(R.id.itemUserId);
            textId = itemView.findViewById(R.id.itemId);
            textTitle = itemView.findViewById(R.id.itemTitle);
            textBody = itemView.findViewById(R.id.itemBody);
            cardView = itemView.findViewById(R.id.cardview);
            itemLayout = itemView.findViewById(R.id.itemLayout);
        }////////////////////////////
    }///////////////////////////////
}
