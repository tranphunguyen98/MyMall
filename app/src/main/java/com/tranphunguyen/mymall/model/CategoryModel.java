package com.tranphunguyen.mymall.model;

public class CategoryModel {
    private String IconLink;
    private String Name;

    public CategoryModel(String iconLink, String name) {
        IconLink = iconLink;
        Name = name;
    }

    public String getIconLink() {
        return IconLink;
    }

    public void setIconLink(String iconLink) {
        IconLink = iconLink;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
