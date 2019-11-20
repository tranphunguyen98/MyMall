package com.tranphunguyen.mymall;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tranphunguyen.mymall.adapter.ProductCartAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView rcProductCart;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        rcProductCart = view.findViewById(R.id.rc_product_cart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        rcProductCart.setLayoutManager(linearLayoutManager);
        rcProductCart.setAdapter(new ProductCartAdapter());
        return view;
    }

}
