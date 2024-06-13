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
import com.example.haithem_camelbuysellapp.UserCamel;
import com.example.haithem_camelbuysellapp.seller.ImageAdapterUpdateCamel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterBuyCamel extends RecyclerView.Adapter <ImageAdapterBuyCamel.ImageViewHolder>{

    private Context mContext;
    private List<UserCamel> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterBuyCamel(Context context, List<UserCamel> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_buy_camel, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserCamel uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getItemName());
        holder.textViewPrice.setText("Price: "+uploadCurrent.getItemPrice()+" OMR");
        holder.textViewHeight.setText("Camel Height:  "+uploadCurrent.getItemHeight());
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
        public TextView textViewHeight;
        public TextView textViewWeight;
        public TextView textViewSNAme;
        public TextView textViewSPhone;
        public ImageView imageView;
        public Button btn_cart;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name7);
            textViewPrice= itemView.findViewById(R.id.image_view_price7);
            textViewHeight= itemView.findViewById(R.id.image_view_height7);
            textViewWeight= itemView.findViewById(R.id.image_view_weight7);
            textViewDetail= itemView.findViewById(R.id.image_view_detail7);
            imageView= itemView.findViewById(R.id.image_view_uploaded7);
            textViewSNAme=itemView.findViewById(R.id.image_view_S_Name7);
            textViewSPhone=itemView.findViewById(R.id.image_view_S_Phone7);
            btn_cart= itemView.findViewById(R.id.btn_add_cart7);

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
