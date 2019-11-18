package com.tranphunguyen.mymall.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.SliderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModel> data;

    public SliderAdapter(List<SliderModel> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_banner,container,false);
        ImageView imgBanner = view.findViewById(R.id.img_banner_slider);
        ConstraintLayout container_banner = view.findViewById(R.id.container_banner_slider);

        int id = container.getContext().getResources().getIdentifier(data.get(position).getBanner(), "drawable", container.getContext().getPackageName());

        imgBanner.setImageResource(id);
        container_banner.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(data.get(position).getColor())));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
