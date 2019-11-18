package com.tranphunguyen.mymall.model;

public class SliderModel {
    private String banner;
    private String color;

    public SliderModel(String banner, String color) {
        this.banner = banner;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SliderModel(String banner) {
        this.banner = banner;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
