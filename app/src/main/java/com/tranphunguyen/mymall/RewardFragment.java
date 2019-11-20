package com.tranphunguyen.mymall;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tranphunguyen.mymall.adapter.ProductMyOrderAdapter;
import com.tranphunguyen.mymall.adapter.RewardAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardFragment extends Fragment {

    private RecyclerView rcReward;
    public RewardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward, container, false);

        rcReward = view.findViewById(R.id.rc_reward);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        rcReward.setLayoutManager(linearLayoutManager);
        rcReward.setAdapter(new RewardAdapter());
        return view;
    }

}
