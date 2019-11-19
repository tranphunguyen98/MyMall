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
import com.tranphunguyen.mymall.adapter.HomePageAdapter;
import com.tranphunguyen.mymall.adapter.HorizotalProductAdapter;
import com.tranphunguyen.mymall.adapter.SliderAdapter;
import com.tranphunguyen.mymall.model.CategoryModel;
import com.tranphunguyen.mymall.model.HomePageModel;
import com.tranphunguyen.mymall.model.ProductModel;
import com.tranphunguyen.mymall.model.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private RecyclerView rcHomePage;
    private RecyclerView rcCategory;
//    private RecyclerView rcGridProduct;
//


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
//
        rcHomePage = view.findViewById(R.id.rc_home_pager);
        rcCategory = view.findViewById(R.id.rc_category);

        List<HomePageModel> listHomePageModel = new ArrayList<>();

        String[] listCategoryName = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_name_icon_category);
        String[] listCategoryLink = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_link_icon_category);
        String[] listColorBannerSlide = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_color_banner_slide);
        String[] listImgProduct = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_img_product);

        List<CategoryModel> data = new ArrayList<>();
        List<SliderModel> dataSlider = new ArrayList<>();
        List<ProductModel> dataProduct = new ArrayList<>();

        for(int i = 0; i < listCategoryName.length; i++ ){
            CategoryModel model = new CategoryModel(listCategoryLink[i],listCategoryName[i]);
            SliderModel sliderModel = new SliderModel(listCategoryLink[i], listColorBannerSlide[i]);
            ProductModel productModel = new ProductModel(listImgProduct[i],listCategoryName[i],listCategoryName[i],500.0f);

            dataSlider.add(sliderModel);
            dataProduct.add(productModel);
            data.add(model);
        }

        listHomePageModel.add(new HomePageModel(0,dataSlider));
        listHomePageModel.add(new HomePageModel(1,"Deals of the day1",dataProduct));
        listHomePageModel.add(new HomePageModel(2,"Deals of the day2",dataProduct));
        listHomePageModel.add(new HomePageModel(0,dataSlider));
        listHomePageModel.add(new HomePageModel(1,"Deals of the day3",dataProduct));
        listHomePageModel.add(new HomePageModel(2,"Deals of the day4",dataProduct));
        listHomePageModel.add(new HomePageModel(0,dataSlider));
        listHomePageModel.add(new HomePageModel(1,"Deals of the day5",dataProduct));

        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(getContext());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcCategory.setLayoutManager(layoutManagerCategory);
        CategoryAdapter adapter = new CategoryAdapter(data);
        rcCategory.setAdapter(adapter);

        LinearLayoutManager layoutManagerHomePage = new LinearLayoutManager(getContext());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcHomePage.setLayoutManager(layoutManagerHomePage);
        HomePageAdapter homePageAdapter = new HomePageAdapter(listHomePageModel);
        rcHomePage.setAdapter(homePageAdapter);


        return view;
    }



}