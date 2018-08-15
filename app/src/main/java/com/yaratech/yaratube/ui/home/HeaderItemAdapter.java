package com.yaratech.yaratube.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yaratech.yaratube.data.model.Headeritem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 8/15/2018.
 */

public class HeaderItemAdapter extends FragmentPagerAdapter {

    List<Headeritem> headeritems = new ArrayList<>();
    public HeaderItemAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Headeritem> headeritems) {
        this.headeritems = headeritems;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderFragmentViewHolder.newInstance(headeritems.get(position).getFeatureAvatar().getXxxdpi());
    }

    @Override
    public int getCount() {
        return headeritems.size();
    }
}