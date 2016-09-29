package com.kkandroidstudy.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.DirectionalViewPager;
import android.os.Bundle;

import com.kkandroidstudy.R;
import com.kkandroidstudy.adapter.DirectionalViewPagerAdapter;
import com.kkandroidstudy.fragment.PagerFourFragment;
import com.kkandroidstudy.fragment.PagerOneFragment;
import com.kkandroidstudy.fragment.PagerThreeFragment;
import com.kkandroidstudy.fragment.PagerTwoFragment;

import java.util.ArrayList;
import java.util.List;

public class DirectionalViewPagerActivity extends FragmentActivity {

    private DirectionalViewPager directionalViewPager;
    private List<Fragment> list = new ArrayList<>();
    private DirectionalViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directional_view_pager);

        directionalViewPager = (DirectionalViewPager) findViewById(R.id.directionalViewPager);

        initData();

    }

    private void initData() {
        PagerOneFragment pagerOneFragment = PagerOneFragment.newInstance();
        list.add(pagerOneFragment);

        PagerTwoFragment pagerTwoFragment = PagerTwoFragment.newInstance();
        list.add(pagerTwoFragment);

        PagerThreeFragment pagerThreeFragment = PagerThreeFragment.newInstance();
        list.add(pagerThreeFragment);

        PagerFourFragment pagerFourFragment = PagerFourFragment.newInstance();
        list.add(pagerFourFragment);
        adapter = new DirectionalViewPagerAdapter(getSupportFragmentManager(), list);

        directionalViewPager.setAdapter(adapter);
    }
}
