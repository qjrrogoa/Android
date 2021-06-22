package com.kosmo.recyclerview30_2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

//어댑터의 내부 클래스로 정의하지않고 별도의 외부 클래스로 뷰홀더 정의
public class MyViewHolder extends RecyclerView.ViewHolder {
    RoundedImageView itemImage;
    TextView itemTitle;
    CardView cardView;//이벤트 처리용
    //인자로 전개된 아이템뷰를 받는다
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.itemImage);
        itemTitle = itemView.findViewById(R.id.itemTitle);
        cardView  = itemView.findViewById(R.id.cardview);
    }
}
