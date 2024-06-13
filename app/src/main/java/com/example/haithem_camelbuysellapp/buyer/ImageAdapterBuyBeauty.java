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
import com.example.haithem_camelbuysellapp.UserBeauty;
import com.example.haithem_camelbuysellapp.UserMeat;
import com.example.haithem_camelbuysellapp.seller.ImageAdapterUpdateBeauty;
import com.example.haithem_camelbuysellapp.seller.ImageAdapterUpdateMeat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterBuyBeauty extends RecyclerView.Adapter <ImageAdapterBuyBeauty.ImageViewHolder>{

    private Context mContext;
    private List<UserBeauty> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterBuyBeauty(Context context, List<UserBeauty> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_buy_beauty, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserBeauty uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getItemName());
        holder.textViewPrice.setText("Price: "+uploadCurrent.getItemPrice()+" OMR");
        holder.textViewQuality.setText("Quality:  "+uploadCurrent.getItemQuality());
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
        public TextView textViewQuality;
        public TextView textViewSNAme;
        public TextView textViewSPhone;
        public ImageView imageView;
        public Button btn_cart;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name9);
            textViewPrice= itemView.findViewById(R.id.image_view_price9);
            textViewQuality= itemView.findViewById(R.id.image_view_quality9);
            textViewDetail= itemView.findViewById(R.id.image_view_detail9);
            imageView= itemView.findViewById(R.id.image_view_uploaded9);
            textViewSNAme=itemView.findViewById(R.id.image_view_S_Name9);
            textViewSPhone=itemView.findViewById(R.id.image_view_S_Phone9);
            btn_cart= itemView.findViewById(R.id.btn_add_cart9);

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
