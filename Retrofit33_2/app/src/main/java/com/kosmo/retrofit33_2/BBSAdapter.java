package com.kosmo.retrofit33_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BBSAdapter extends RecyclerView.Adapter<BBSAdapter.ViewHolder> {

    private Context context;
    private List<BBSDto> items;
    //인자 생성자를 통해 Context및 데이타 받기
    public BBSAdapter(Context context, List<BBSDto> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //View itemView=View.inflate(context,R.layout.json_item_layout,null);
        View itemView = LayoutInflater.from(context).inflate(R.layout.json_item_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  BBSAdapter.ViewHolder holder, int position) {
        BBSDto item=items.get(position);

        holder.itemNo.setText(item.getNo());

        holder.itemName.setText(item.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String postDate=dateFormat.format(new Date(Long.parseLong(item.getPostDate())));
        holder.itemPostDate.setText(postDate);
        holder.itemTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }//////////////

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemNo;
        TextView itemName;
        TextView itemTitle;
        TextView itemPostDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNo = itemView.findViewById(R.id.itemNo);

            itemName = itemView.findViewById(R.id.itemName);
            itemPostDate = itemView.findViewById(R.id.itemPostDate);
            itemTitle = itemView.findViewById(R.id.itemTitle);
        }
    }
}
