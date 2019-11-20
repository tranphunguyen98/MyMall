package com.tranphunguyen.mymall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranphunguyen.mymall.R;

public class ProductCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public int getItemViewType(int position) {
        if(position >= 4) return 1;
        else return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_total_cart,parent,false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_cart,parent,false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
