package com.tranphunguyen.mymall.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.adapter.CategoryAdapter;
import com.tranphunguyen.mymall.adapter.SliderAdapter;
import com.tranphunguyen.mymall.model.CategoryModel;
import com.tranphunguyen.mymall.model.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewPager bannerSlider;
    private int currentPage = 0;
    private Timer timer;
    List<SliderModel> dataSlider = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rc_category);
        bannerSlider = view.findViewById(R.id.view_pager_banner_slider );

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);

        String[] listCategoryName = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_name_icon_category);
        String[] listCategoryLink = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_link_icon_category);
        String[] listColorBannerSlide = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_color_banner_slide);

        List<CategoryModel> data = new ArrayList<>();


        for(int i = 0; i < listCategoryName.length; i++ ){
            CategoryModel model = new CategoryModel(listCategoryLink[i],listCategoryName[i]);
            SliderModel sliderModel = new SliderModel(listCategoryLink[i], listColorBannerSlide[i]);

            dataSlider.add(sliderModel);
            data.add(model);
        }

        CategoryAdapter adapter = new CategoryAdapter(data);

        recyclerView.setAdapter(adapter);

        SliderAdapter sliderAdapter = new SliderAdapter(dataSlider);
        bannerSlider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bannerSlider.setPageMargin(32);
        bannerSlider.setAdapter(sliderAdapter);

        startBannerSlider();

        return view;
    }

    private void startBannerSlider() {
        Handler handler = new Handler();
        Runnable update = () -> {
            if(currentPage >= dataSlider.size()) {
                currentPage = 0;
            }
            bannerSlider.setCurrentItem(currentPage++,true);
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },1000,1000);
    }

}