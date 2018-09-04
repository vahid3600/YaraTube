package com.yaratech.yaratube.ui.productlist;

import android.support.v7.util.DiffUtil;

import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 9/4/2018.
 */

class DiffUtilfCallBack extends DiffUtil.Callback {


    private List<Product> oldProducts = new ArrayList<>();
    private List<Product> newProducts = new ArrayList<>();

    DiffUtilfCallBack(List<Product> oldProducts, List<Product> newProducts) {
        this.oldProducts = oldProducts;
        this.newProducts = newProducts;
    }

    @Override
    public int getOldListSize() {
        return oldProducts.size();
    }

    @Override
    public int getNewListSize() {
        return newProducts.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProducts.get(oldItemPosition).getId()
                == newProducts.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProducts.get(oldItemPosition).equals(newProducts.get(newItemPosition));
    }

    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}