package com.tranphunguyen.mymall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.ImageDetailProductModel;

import java.util.List;

public class DetailProductSliderAdapter extends PagerAdapter {
    List<ImageDetailProductModel> data;

    public DetailProductSliderAdapter(List<ImageDetailProductModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_image_detail_product,container,false);
        ImageView imageView = view.findViewById(R.id.img_product_detail);
        int id = container.getContext().getResources().getIdentifier(data.get(position).getLink(),"drawable",container.getContext().getPackageName());
        imageView.setImageResource(id);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
