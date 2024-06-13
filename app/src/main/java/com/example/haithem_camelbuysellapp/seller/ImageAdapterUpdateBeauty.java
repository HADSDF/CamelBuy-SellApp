package com.example.haithem_camelbuysellapp.seller;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterUpdateBeauty extends RecyclerView.Adapter <ImageAdapterUpdateBeauty.ImageViewHolder>{

    private Context mContext;
    private List<UserBeauty> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterUpdateBeauty(Context context, List<UserBeauty> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_update_beauty, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserBeauty uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getItemName());
        holder.textViewPrice.setText("Price: "+uploadCurrent.getItemPrice()+" OMR");
        holder.textViewQuality.setText("Quality:  "+uploadCurrent.getItemQuality());
        holder.textViewDetail.setText("Item Details: "+uploadCurrent.getItemSpecification());
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
        public ImageView imageView;
        public Button btn_update;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name2);
            textViewPrice= itemView.findViewById(R.id.image_view_price2);
            textViewQuality= itemView.findViewById(R.id.image_view_quality2);
            textViewDetail= itemView.findViewById(R.id.image_view_detail2);
            imageView= itemView.findViewById(R.id.image_view_uploaded2);
            btn_update= itemView.findViewById(R.id.update_item2);

            btn_update.setOnClickListener(this);

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
