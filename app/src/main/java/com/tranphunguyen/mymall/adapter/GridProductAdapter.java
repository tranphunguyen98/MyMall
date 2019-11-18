package com.tranphunguyen.mymall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.ProductModel;

import java.util.List;

public class GridProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductModel> data;
    private Context context;

    public GridProductAdapter(List<ProductModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_product,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductModel model = data.get(position);

        if(holder instanceof ViewHolder) {
            int idImage = context.getResources().getIdentifier(model.getUrlImage(),"drawable",context.getPackageName());
            ((ViewHolder) holder).imgProduct.setImageResource(idImage);
            ((ViewHolder) holder).tvNameProduct.setText(model.getName());
            ((ViewHolder) holder).tvDescriptionProduct.setText(model.getDescription());
            ((ViewHolder) holder).tvPriceProduct.setText(String.valueOf(model.getPrice()) + "$");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView tvNameProduct;
        private TextView tvDescriptionProduct;
        private TextView tvPriceProduct;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvNameProduct = itemView.findViewById(R.id.tv_name_product);
            tvDescriptionProduct = itemView.findViewById(R.id.tv_description_product);
            tvPriceProduct = itemView.findViewById(R.id.tv_price_product);
        }
    }
}
