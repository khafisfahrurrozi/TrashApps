package com.example.trashapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class trashAdapter extends RecyclerView.Adapter<trashAdapter.trashViewHolder>{
    final String type1="Household";
    final String type2="Organic";
    final String type3="Automotive";
    final String type4="Dangerous";
    final String type5="Liquid";
    final String type6="Metal";
    final String type7="Glass";
    final String type8="Contruction";

    private List<typetrash> mtrash;
    private Context mContext;

    public trashAdapter(List<typetrash> mtrash, Context mContext) {
        this.mtrash = mtrash;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public trashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cardview,parent,false);
        return new trashViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull trashViewHolder holder, int position) {

        typetrash uploadCurrent = mtrash.get(position);
//        if ((uploadCurrent.getHousehold()).equals(type1)){
            holder.trashtype1.setText(uploadCurrent.getHousehold());
//        }
//        if ((uploadCurrent.getOrganic()).equals(type2)){
//            holder.trashtype2.setText(uploadCurrent.getOrganic());
//        }
//        if ((uploadCurrent.getAutomotive()).equals(type3)){
//            holder.trashtype3.setText(uploadCurrent.getAutomotive());
//        }
//        if ((uploadCurrent.getDanger()).equals(type4)){
//            holder.trashtype4.setText(uploadCurrent.getDanger());
//        }
//        if ((uploadCurrent.getLiquid()).equals(type5)){
//            holder.trashtype5.setText(uploadCurrent.getLiquid());
//        }
//        if ((uploadCurrent.getMetal()).equals(type6)){
//            holder.trashtype6.setText(uploadCurrent.getMetal());
//        }
//        if ((uploadCurrent.getGlass()).equals(type7)){
//            holder.trashtype7.setText(uploadCurrent.getGlass());
//        }
//        if ((uploadCurrent.getContruction()).equals(type8)){
//            holder.trashtype8.setText(uploadCurrent.getContruction());
//        }

    }

    @Override
    public int getItemCount() {
        return mtrash.size();
    }

    public class trashViewHolder extends RecyclerView.ViewHolder {
        public TextView trashtype1,trashtype2,trashtype3,trashtype4,trashtype5,trashtype6,trashtype7,trashtype8;

        public trashViewHolder(@NonNull View itemView) {
            super(itemView);
         trashtype1=itemView.findViewById(R.id.type1);
         trashtype2=itemView.findViewById(R.id.type2);
         trashtype3=itemView.findViewById(R.id.type3);
         trashtype4=itemView.findViewById(R.id.type4);
         trashtype5=itemView.findViewById(R.id.type5);
         trashtype6=itemView.findViewById(R.id.type6);
         trashtype7=itemView.findViewById(R.id.type7);
         trashtype8=itemView.findViewById(R.id.type8);
        }
    }
}
