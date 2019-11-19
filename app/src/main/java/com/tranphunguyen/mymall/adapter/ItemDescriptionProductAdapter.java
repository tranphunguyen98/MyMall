package com.tranphunguyen.mymall.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.ItemDescriptionProductModel;

import org.w3c.dom.Text;

import java.util.List;

public class ItemDescriptionProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemDescriptionProductModel> data;

    public ItemDescriptionProductAdapter(List<ItemDescriptionProductModel> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_white,parent,false);
                return new WhiteViewHolder(view);
            }
            case 1:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_gray,parent,false);
                return new WhiteViewHolder(view);
            }
            default:
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof WhiteViewHolder) {
            ((WhiteViewHolder) holder).bind(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class WhiteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvValue;

        public WhiteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_description_item);
            tvValue = itemView.findViewById(R.id.tv_content_description_item);
        }

        private void bind(ItemDescriptionProductModel model) {
            tvName.setText(model.getName());
            tvValue.setText(model.getValue());
        }
    }

}
