package com.example.myapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListAdapterHolder> {

    Context context;
    List<ItemData> myItems;

    public ItemListAdapter(Context context, List<ItemData> myItems) {
        this.context = context;
        this.myItems = myItems;
    }

    @NonNull
    @Override
    public ItemListAdapter.ItemListAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new ItemListAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ItemListAdapterHolder holder, int position) {

        ItemData myItems1 = myItems.get(position);
        holder.tttile.setText(myItems1.getTitle());
        holder.desccc.setText(myItems1.getDescription());
        Glide.with(context).load(myItems1.getImage()).into(holder.myImg);

    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    class ItemListAdapterHolder extends RecyclerView.ViewHolder{

        ImageView myImg;
        TextView tttile,desccc;

        public ItemListAdapterHolder(@NonNull View itemView) {
            super(itemView);

            myImg = itemView.findViewById(R.id.myImg);
            tttile = itemView.findViewById(R.id.tttile);
            desccc = itemView.findViewById(R.id.desccc);


        }
    }
}
