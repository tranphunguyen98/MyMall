package com.tranphunguyen.mymall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.CategoryModel;

import java.lang.reflect.Constructor;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CategoryModel> data;
    private ViewHolder viewHolder;
    private Context context;
    private CategoryAdapter() {

    }

    public CategoryAdapter(List<CategoryModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        viewHolder = new ViewHolder(view);
        context  = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryModel model = data.get(position);

        int id = context.getResources().getIdentifier(model.getIconLink(), "drawable", context.getPackageName());

        viewHolder.categoryIcon.setImageResource(id);
        viewHolder.categoryName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryIcon;
        private TextView categoryName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIcon = itemView.findViewById(R.id.img_category_icon);
            categoryName = itemView.findViewById(R.id.tv_category_name);
        }

    }
}
