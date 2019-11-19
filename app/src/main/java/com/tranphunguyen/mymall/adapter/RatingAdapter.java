package com.tranphunguyen.mymall.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.RatingModel;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<RatingModel> data;

    public RatingAdapter(List<RatingModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder) {
            ((ViewHolder) holder).bind(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvStart;
        private ProgressBar progressBar;
        private TextView tvAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStart = itemView.findViewById(R.id.tv_start_number);
            progressBar = itemView.findViewById(R.id.prg_rating);
            tvAmount = itemView.findViewById(R.id.tv_amount_rating);
        }

        private void bind(RatingModel model) {
            tvStart.setText(String.valueOf(model.getStart()));
            progressBar.setProgress(model.getPercentProgress());
            tvAmount.setText(String.valueOf(model.getAmount()));

            if(model.getStart() == 2 || model.getStart() == 3) {
                progressBar.setProgressTintList(ColorStateList.valueOf(itemView.getResources().getColor(R.color.yellow)));
            }
            if(model.getStart() == 1) {
                progressBar.setProgressTintList(ColorStateList.valueOf(itemView.getResources().getColor(R.color.colorPrimary)));

            }
        }
    }
}
