package com.kkandroidstudy.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.TabLayoutAdapter;
import com.kkandroidstudy.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutViewPagerActivity extends Activity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<ImageView> imageViewList = new ArrayList<>();
    private String[] tabTitles;
    private int[] imgs = {R.mipmap.test1, R.mipmap.test2, R.mipmap.test3};
    private TabLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_view_pager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        initData();

        adapter = new TabLayoutAdapter(imageViewList, tabTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void initData() {
        tabTitles = getResources().getStringArray(R.array.tabtitles);
        for (int i = 0; i < tabTitles.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ScreenUtil.getScreenWidth(this), ScreenUtil.getScreenHeight(this)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[i % 3]);
            imageViewList.add(imageView);
        }
    }
}
