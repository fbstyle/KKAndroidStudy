package com.kkandroidstudy.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.AppBarLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    private AppBarLayoutAdapter adapter;
    private Toolbar toolbar;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);

        toolbar= (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("许嵩");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initData();

        adapter = new AppBarLayoutAdapter(this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);


    }

    private void initData() {
        for (int i = 'A'; i < 'Z'; i++) {
            list.add("" + (char) i);
        }
    }
}
