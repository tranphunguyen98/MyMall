package com.tranphunguyen.mymall.model;

import com.tranphunguyen.mymall.adapter.HomePageAdapter;
import com.tranphunguyen.mymall.model.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class HomePageModel {

    public final static int BANNER_SLIDER = 0;
    public final static int HORIZONTAL_PRODUCT = 1;
    public final static int GRID_PRODUCT = 2;

    private int type;
    private List<SliderModel> listSliderModel;

    private String title;
    private List<ProductModel> listProductModel;

    public HomePageModel(int type, List<SliderModel> listSliderModel) {
        this.type = type;
        this.listSliderModel = listSliderModel;
    }

    public HomePageModel(int type, String title, List<ProductModel> listProductModel) {
        this.type = type;
        this.title = title;
        this.listProductModel = listProductModel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getListSliderModel() {
        return listSliderModel;
    }

    public void setListSliderModel(List<SliderModel> listSliderModel) {
        this.listSliderModel = listSliderModel;
    }

    public List<ProductModel> getListProductModel() {
        return listProductModel;
    }

    public void setListProductModel(List<ProductModel> listProductModel) {
        this.listProductModel = listProductModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
