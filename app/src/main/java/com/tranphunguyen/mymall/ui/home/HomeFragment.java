package com.tranphunguyen.mymall.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tranphunguyen.mymall.R;
import com.tranphunguyen.mymall.adapter.CategoryAdapter;
import com.tranphunguyen.mymall.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rc_category);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);

        String[] listCategoryName = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_name_icon_category);
        String[] listCategoryLink = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.list_link_icon_category);

        List<CategoryModel> data = new ArrayList<>();

        for(int i = 0; i < listCategoryName.length; i++ ){
            CategoryModel model = new CategoryModel(listCategoryLink[i],listCategoryName[i]);
            data.add(model);
        }

        CategoryAdapter adapter = new CategoryAdapter(data);

        recyclerView.setAdapter(adapter);
        return view;
    }


}