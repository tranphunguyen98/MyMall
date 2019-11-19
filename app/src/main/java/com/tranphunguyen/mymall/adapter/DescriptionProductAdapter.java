package com.tranphunguyen.mymall.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tranphunguyen.mymall.DescriptionFragment;
import com.tranphunguyen.mymall.SpecifictionFragment;

public class DescriptionProductAdapter extends FragmentPagerAdapter {

    private int totals;

    public DescriptionProductAdapter(@NonNull FragmentManager fm, int behavior, int totals) {
        super(fm, behavior);
        this.totals = totals;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:{
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                return descriptionFragment;
            }
            case 1:{
                SpecifictionFragment specifictionFragment = new SpecifictionFragment();
                return specifictionFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totals;
    }
}
