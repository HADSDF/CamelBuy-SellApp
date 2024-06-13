package com.example.haithem_camelbuysellapp.buyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserMeat;
import com.example.haithem_camelbuysellapp.seller.ImageAdapterUpdateMeat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterBuyMeat extends RecyclerView.Adapter <ImageAdapterBuyMeat.ImageViewHolder>{

    private Context mContext;
    private List<UserMeat> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterBuyMeat(Context context, List<UserMeat> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_buy_meat, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserMeat uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getItemName());
        holder.textViewPrice.setText("Price: "+uploadCurrent.getItemPrice()+" OMR");
        holder.textViewWeight.setText("Camel Weight:  "+uploadCurrent.getItemWeight());
        holder.textViewDetail.setText("Item Details: "+uploadCurrent.getItemSpecification());
        holder.textViewSNAme.setText("Seller Name: "+uploadCurrent.getItemSName());
        holder.textViewSPhone.setText("Seller Contact: "+uploadCurrent.getItemSPhone());
        Picasso.get()
                .load(uploadCurrent.getItemUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public TextView textViewWeight;
        public TextView textViewSNAme;
        public TextView textViewSPhone;
        public ImageView imageView;
        public Button btn_cart;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name8);
            textViewPrice= itemView.findViewById(R.id.image_view_price8);
            textViewWeight= itemView.findViewById(R.id.image_view_weight8);
            textViewDetail= itemView.findViewById(R.id.image_view_detail8);
            imageView= itemView.findViewById(R.id.image_view_uploaded8);
            textViewSNAme=itemView.findViewById(R.id.image_view_S_Name8);
            textViewSPhone=itemView.findViewById(R.id.image_view_S_Phone8);
            btn_cart= itemView.findViewById(R.id.btn_add_cart8);

            btn_cart.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }


    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener= listener;
    }
}
