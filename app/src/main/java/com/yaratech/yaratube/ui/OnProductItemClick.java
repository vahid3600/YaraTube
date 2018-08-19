package com.yaratech.yaratube.ui;

import com.yaratech.yaratube.data.model.Product;

/**
 * Created by Vah on 8/18/2018.
 */

public interface OnProductItemClick<T> {
    void onClick(T product);
}
