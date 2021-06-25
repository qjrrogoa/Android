package com.kosmo.jsonparse34_2;

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

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context context;
    private List<PhotoItem> items;

    public PhotoAdapter(Context context, List<PhotoItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        //View itemView = View.inflate(context,R.layout.photo_layout,null);
        //반드시 parent지정. 위는 사용하지 말것
        //View itemView = LayoutInflater.from(context).inflate(R.layout.photo_layout,parent,false);
        View itemView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.photo_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  PhotoAdapter.ViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position).getTitle());
        holder.itemId.setText(items.get(position).getId());
        //https://square.github.io/picasso/
        Picasso.get().load(items.get(position).getThumbnailUrl()).into(holder.itemImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,items.get(position).getAlbumId(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView itemImage;
        TextView itemId;
        TextView itemTitle;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            itemImage=itemView.findViewById(R.id.itemImage);
            itemId = itemView.findViewById(R.id.itemId);
            itemTitle=itemView.findViewById(R.id.itemTitle);

        }
    }
}
