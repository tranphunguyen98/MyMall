package com.tranphunguyen.mymall.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.model.HomePageModel;
import com.tranphunguyen.mymall.model.ProductModel;
import com.tranphunguyen.mymall.model.SliderModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomePageModel> data;
    private Context context;

    public HomePageAdapter(List<HomePageModel> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_banner,parent,false);
                return new SliderBannerViewHolder(view);
            }
            case HomePageModel.HORIZONTAL_PRODUCT: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_horizontal_product,parent,false);
                return new HorizontalProductViewHolder(view);
            }
            case HomePageModel.GRID_PRODUCT: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_product,parent,false);
                return new GridProductViewHolder(view);
            }
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (data.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER: {
                if(holder instanceof SliderBannerViewHolder) {
                    ((SliderBannerViewHolder) holder).settingSlider(data.get(position).getListSliderModel());
                }
            }
            case HomePageModel.HORIZONTAL_PRODUCT: {
                if(holder instanceof HorizontalProductViewHolder) {
                    ((HorizontalProductViewHolder) holder).settingRecycler(data.get(position).getListProductModel());
                    ((HorizontalProductViewHolder) holder).tvTitle.setText(data.get(position).getTitle());
                }
            }
            case HomePageModel.GRID_PRODUCT: {
                if(holder instanceof GridProductViewHolder) {
                    ((GridProductViewHolder) holder).settingRecycler(data.get(position).getListProductModel());
                    ((GridProductViewHolder) holder).tvTitle.setText(data.get(position).getTitle());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SliderBannerViewHolder extends RecyclerView.ViewHolder {
        private ViewPager viewPagerBanner;
        private int currentPage = 0;
        private Timer timer;

        public SliderBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPagerBanner = itemView.findViewById(R.id.view_pager_banner_slider);

        }

        private void settingSlider(List<SliderModel> listSliderModel) {

            viewPagerBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

            viewPagerBanner.setPageMargin(20);

            SliderAdapter sliderAdapter = new SliderAdapter(listSliderModel);

            viewPagerBanner.setAdapter(sliderAdapter);

            startBannerSlider(listSliderModel.size());
        }

        private void startBannerSlider(int size) {
            Handler handler = new Handler();
            Runnable update = () -> {
                if(currentPage >= size) {
                    currentPage = 0;
                }
                viewPagerBanner.setCurrentItem(currentPage++,true);
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            },20000,20000);
        }
    }
    class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcProduct;
        private TextView tvTitle;
        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            rcProduct = itemView.findViewById(R.id.rc_horizontal_product);
            tvTitle = itemView.findViewById(R.id.tv_title_horizontal_product);
        }
        private void settingRecycler(List<ProductModel> listProductModel) {

        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(itemView.getContext());
        layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

        rcProduct.setLayoutManager(layoutManagerProduct);

        HorizotalProductAdapter productAdapter = new HorizotalProductAdapter(listProductModel);
        rcProduct.setAdapter(productAdapter);

        }
    }
    class GridProductViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcProduct;
        private TextView tvTitle;
        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            rcProduct = itemView.findViewById(R.id.rc_grid_product);
            tvTitle = itemView.findViewById(R.id.tv_title_grid_product);
        }
        private void settingRecycler(List<ProductModel> listProductModel) {

            GridLayoutManager layoutManagerProduct = new GridLayoutManager(itemView.getContext(),2);
            layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

            rcProduct.setLayoutManager(layoutManagerProduct);

            GridProductAdapter productAdapter = new GridProductAdapter(listProductModel);
            rcProduct.setAdapter(productAdapter);

        }
    }
}
