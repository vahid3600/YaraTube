package com.yaratech.yaratube.data.sourse.product_detail;

import com.yaratech.yaratube.data.model.ProductList;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface ProductDetailContract {
    interface View {

        void showListProducts(List<ProductList> productLists);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void fetchProductListFromRemote(int id);
    }
}
