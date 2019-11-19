package com.tranphunguyen.mymall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.tranphunguyen.mymall.adapter.DescriptionProductAdapter;
import com.tranphunguyen.mymall.adapter.DetailProductSliderAdapter;
import com.tranphunguyen.mymall.adapter.RatingAdapter;
import com.tranphunguyen.mymall.model.ImageDetailProductModel;
import com.tranphunguyen.mymall.model.RatingModel;

import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    private ViewPager viewPagerImage;
    private FloatingActionButton btnFavorite;
    private TabLayout tabIndicator;

    private ViewPager viewPagerDescriptionProduct;
    private TabLayout tabLayoutDescriptionProduct;
    private RecyclerView rcRating;

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
        viewPagerDescriptionProduct = this.findViewById(R.id.view_pager_description);
        tabLayoutDescriptionProduct = this.findViewById(R.id.tab_layout_description);
        rcRating = this.findViewById(R.id.rc_rating_m);

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

        DescriptionProductAdapter adapter = new DescriptionProductAdapter(getSupportFragmentManager(),0,tabLayoutDescriptionProduct.getTabCount());
        viewPagerDescriptionProduct.setAdapter(adapter);
        viewPagerDescriptionProduct.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutDescriptionProduct));
        tabLayoutDescriptionProduct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerDescriptionProduct.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        List<RatingModel> dataRating = new ArrayList<>();
        dataRating.add(new RatingModel(5,12,50));
        dataRating.add(new RatingModel(4,40,40));
        dataRating.add(new RatingModel(3,30,20));
        dataRating.add(new RatingModel(2,10,10));
        dataRating.add(new RatingModel(1,5,12));

        LinearLayoutManager linearLayoutManagerRating = new LinearLayoutManager(this);
        rcRating.setLayoutManager(linearLayoutManagerRating);
        rcRating.setAdapter(new RatingAdapter(dataRating));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail,menu);
        return true;
    }
}
