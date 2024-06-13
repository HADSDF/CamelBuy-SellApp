package com.example.haithem_camelbuysellapp.seller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserCheckStatistics;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<UserCheckStatistics> itemList;

    public ItemAdapter(List<UserCheckStatistics> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        UserCheckStatistics item = itemList.get(position);
        holder.itemName.setText(item.getStatItemName());
        holder.sellerName.setText(item.getStatSellerName());
        holder.itemQuantity.setText(String.valueOf(item.getStatQuantity()));
        holder.date.setText(item.getStatDate());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, sellerName, itemQuantity, date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            sellerName = itemView.findViewById(R.id.sellerName);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            date = itemView.findViewById(R.id.date);
        }
    }
}
