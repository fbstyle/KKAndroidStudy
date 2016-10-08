package com.kkandroidstudy.activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.AppBarLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPartialRefreshActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;

    private AppBarLayoutAdapter adapter;

    private List<String> list = new ArrayList<>();

    private int position = 0;

    //刷新
    private Button btn_refresh;
    //增加
    private Button btn_add;
    //删除
    private Button btn_remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_partial_refresh);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_refresh.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new AppBarLayoutAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 'A'; i < 'Z'; i++) {
            list.add("" + (char) i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                //关闭动画
                ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                list.set(0, "刷新");
                adapter.notifyItemChanged(0);
                break;
            case R.id.btn_add:
                list.add(0, "增加");
                adapter.notifyItemInserted(0);
                recyclerView.smoothScrollToPosition(0);
                break;
            case R.id.btn_remove:
                list.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount() - position);
                break;
        }
    }
}
