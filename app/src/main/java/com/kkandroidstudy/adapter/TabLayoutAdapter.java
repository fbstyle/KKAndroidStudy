package com.kkandroidstudy.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by shiyan on 2016/9/28.
 */
public class TabLayoutAdapter extends PagerAdapter {
    private List<ImageView> list;
    private String[] tabTitles;

    public TabLayoutAdapter(List<ImageView> list,String[] tabTitles) {
        this.list = list;
        this.tabTitles = tabTitles;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
