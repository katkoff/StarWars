package com.katkov.training_starwars.ui.movie.list;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public final class MyFragPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> pagesList = new ArrayList<>();

    MyFragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pagesList.get(position);
    }

    @Override
    public int getCount() {
        return pagesList.size();
    }

    public void addPage(Fragment fragment) {
        pagesList.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pagesList.get(position).toString();
    }
}
