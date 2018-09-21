package com.yaratech.yaratube.utils;

import android.support.v4.app.Fragment;

import com.yaratech.yaratube.data.model.Product;

/**
 * Created by Vah on 8/19/2018.
 */

public interface OnItemClick {

    public interface OnProductItemClick {
        void onClick(Product product);
    }
}
