package com.kkandroidstudy.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.AppBarLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class AppBarLayoutActivity extends Activity {
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    private AppBarLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);

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
