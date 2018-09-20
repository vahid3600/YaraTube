package com.yaratech.yaratube.ui.home.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 8/15/2018.
 */

public class HeaderItemAdapter extends FragmentPagerAdapter {

    private List<Product> headerItems = new ArrayList<>();
    HeaderItemAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Product> headerItems) {
        this.headerItems = headerItems;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderViewHolderFragment.newInstance(headerItems.get(position));
    }

    @Override
    public int getCount() {
        return headerItems.size();
    }
}