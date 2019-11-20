package com.tranphunguyen.mymall;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tranphunguyen.mymall.adapter.ProductCartAdapter;
import com.tranphunguyen.mymall.adapter.ProductMyOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends Fragment {

    private RecyclerView rcProductMyOrder;
    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        rcProductMyOrder = view.findViewById(R.id.rc_my_order);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        rcProductMyOrder.setLayoutManager(linearLayoutManager);
        rcProductMyOrder.setAdapter(new ProductMyOrderAdapter());
        return view;
    }

}
