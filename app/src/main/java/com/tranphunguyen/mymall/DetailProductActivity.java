package com.tranphunguyen.mymall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.tranphunguyen.mymall.adapter.DetailProductSliderAdapter;
import com.tranphunguyen.mymall.model.ImageDetailProductModel;

import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    private ViewPager viewPagerImage;
    private FloatingActionButton btnFavorite;
    private TabLayout tabIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Toolbar toolbar = this.findViewById(R.id.toolbar_detail_product);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPagerImage = this.findViewById(R.id.view_pager_image_detail_product);
        btnFavorite = this.findViewById(R.id.float_btn_favorite);
        tabIndicator = this.findViewById(R.id.tablayout_indicator);

        String[] listCategoryName = getResources().getStringArray(R.array.list_name_icon_category);
        String[] listCategoryLink = getResources().getStringArray(R.array.list_link_icon_category);
        String[] listColorBannerSlide = getResources().getStringArray(R.array.list_color_banner_slide);
        String[] listImgProduct = getResources().getStringArray(R.array.list_img_product);

        List<ImageDetailProductModel> data = new ArrayList<>();

        for(int i = 0; i < listCategoryName.length; i++ ){
            ImageDetailProductModel model = new ImageDetailProductModel(listCategoryLink[i]);
            data.add(model);
        }

        viewPagerImage.setAdapter(new DetailProductSliderAdapter(data));

        tabIndicator.setupWithViewPager(viewPagerImage,true);

        btnFavorite.setOnClickListener(view -> {
            if(!btnFavorite.isSelected()) {
                btnFavorite.setSelected(true);
                btnFavorite.setImageResource(R.drawable.ic_favorite_active);
            } else {
                btnFavorite.setSelected(false);
                btnFavorite.setImageResource(R.drawable.ic_favorite_inactive);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail,menu);
        return true;
    }
}
