package com.example.trashapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.dataViewHolder> {
    private Context mContext;
    private List<getset> mUploads;



    public Adapter(Context mContext, List<getset> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;

    }

    @NonNull
    @Override
    public dataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cardview,parent,false);
        return new dataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dataViewHolder holder, int position) {
        getset uploadCurrent = mUploads.get(position);
//        typetrash uploadCurrentrash=mtrash.get(position);
        holder.weight.setText(uploadCurrent.getWeight());
        holder.maps.setText(uploadCurrent.getMaps());

        Picasso.get()
                .load(uploadCurrent.getmImageUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }


    public class dataViewHolder extends RecyclerView.ViewHolder{
      public TextView maps,weight;
      public ImageView image;


        public dataViewHolder(@NonNull View itemView) {
            super(itemView);

            maps=itemView.findViewById(R.id.maps_view);
            weight=itemView.findViewById(R.id.weight_view);
            image=itemView.findViewById(R.id.image_view);


        }

    }
}
