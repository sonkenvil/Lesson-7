package com.framgia.nguyenson.lesson7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder>{
    private ArrayList<Repos> mArrayList;
    private Context mContext;

    public ReposAdapter(ArrayList<Repos> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mArrayList.get(position).getOwner().getAvatarURL())
                .into(holder.mImage);
        holder.mId.setText(String.valueOf(mArrayList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mId;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.imageView);
            mId = itemView.findViewById(R.id.text_id);
        }
    }
}
