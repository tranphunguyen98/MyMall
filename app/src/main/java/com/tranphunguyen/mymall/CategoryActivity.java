package com.tranphunguyen.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.tranphunguyen.mymall.adapter.CategoryAdapter;

import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = this.findViewById(R.id.toolbar_category);

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra(CategoryAdapter.TITLE_INTENT_EXTRA);

        getSupportActionBar().setTitle(title);

        toolbar.setNavigationOnClickListener(view -> this.finish());
    }

}
