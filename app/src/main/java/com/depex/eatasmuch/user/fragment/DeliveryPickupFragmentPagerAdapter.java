package com.depex.eatasmuch.user.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class DeliveryPickupFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    FragmentManager fragmentManager;
    public DeliveryPickupFragmentPagerAdapter(FragmentManager fm,  List<Fragment> fragments) {
        super(fm);
        fragmentManager=fm;
        this.fragments=fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
