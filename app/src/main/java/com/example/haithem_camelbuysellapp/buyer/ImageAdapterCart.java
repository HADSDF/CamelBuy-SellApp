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
import com.example.haithem_camelbuysellapp.UserCart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterCart extends RecyclerView.Adapter <ImageAdapterCart.ImageViewHolder>{

    private Context mContext;
    private List<UserCart> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterCart(Context context, List<UserCart> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_cart, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserCart uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getCartName());
        holder.textViewPrice.setText("Price: "+uploadCurrent.getCartPrice()+" OMR");
        holder.textViewDetail.setText("Item Details: "+uploadCurrent.getCartDetail());
        holder.textViewSNAme.setText("Seller Name: "+uploadCurrent.getItemSName());
        holder.textViewSPhone.setText("Seller Contact: "+uploadCurrent.getItemSPhone());
        Picasso.get()
                .load(uploadCurrent.getCartUrl())
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
        public TextView textViewSNAme;
        public TextView textViewSPhone;
        public ImageView imageView;
        public Button btn_pay;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name10);
            textViewPrice= itemView.findViewById(R.id.image_view_price10);
            textViewDetail= itemView.findViewById(R.id.image_view_detail10);
            imageView= itemView.findViewById(R.id.image_view_uploaded10);
            textViewSNAme=itemView.findViewById(R.id.image_view_S_Name10);
            textViewSPhone=itemView.findViewById(R.id.image_view_S_Phone10);
            btn_pay= itemView.findViewById(R.id.btn_buy_10);

            btn_pay.setOnClickListener(this);

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
