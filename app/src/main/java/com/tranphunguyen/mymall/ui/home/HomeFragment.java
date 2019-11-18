package com.tranphunguyen.mymall.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.adapter.CategoryAdapter;
import com.tranphunguyen.mymall.adapter.GridProductAdapter;
import com.tranphunguyen.mymall.adapter.HorizotalProductAdapter;
import com.tranphunguyen.mymall.adapter.SliderAdapter;
import com.tranphunguyen.mymall.model.CategoryModel;
import com.tranphunguyen.mymall.model.ProductModel;
import com.tranphunguyen.mymall.model.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private RecyclerView rcCategory;
    private RecyclerView rcProduct;
    private RecyclerView rcGridProduct;

    private ViewPager bannerSlider;
    private int currentPage = 0;
    private Timer timer;
    List<SliderModel> dataSlider = new ArrayList<>();
    List<ProductModel> dataProduct = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcCategory = view.findViewById(R.id.rc_category);
        rcProduct = view.findViewById(R.id.rc_horizontal_product);
        rcGridProduct = view.findViewById(R.id.rc_grid_product);
        bannerSlider = view.findViewById(R.id.view_pager_banner_slider );

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(getActivity());
        layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(getActivity(),2);
        gridLayoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

        rcCategory.setLayoutManager(layoutManager);
        rcProduct.setLayoutManager(layoutManagerProduct);
        rcGridProduct.setLayoutManager(gridLayoutManagerProduct);

        String[] listCategoryName = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_name_icon_category);
        String[] listCategoryLink = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_link_icon_category);
        String[] listColorBannerSlide = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_color_banner_slide);
        String[] listImgProduct = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_img_product);

        List<CategoryModel> data = new ArrayList<>();


        for(int i = 0; i < listCategoryName.length; i++ ){
            CategoryModel model = new CategoryModel(listCategoryLink[i],listCategoryName[i]);
            SliderModel sliderModel = new SliderModel(listCategoryLink[i], listColorBannerSlide[i]);
            ProductModel productModel = new ProductModel(listImgProduct[i],listCategoryName[i],listCategoryName[i],500.0f);

            dataSlider.add(sliderModel);
            dataProduct.add(productModel);
            data.add(model);
        }

        CategoryAdapter adapter = new CategoryAdapter(data);

        rcCategory.setAdapter(adapter);

        HorizotalProductAdapter productAdapter = new HorizotalProductAdapter(dataProduct);
        rcProduct.setAdapter(productAdapter);

        GridProductAdapter gridProductAdapter = new GridProductAdapter(dataProduct);
        rcGridProduct.setAdapter(gridProductAdapter);

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
        bannerSlider.setPageMargin(20);
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
        },2000,2000);
    }

}