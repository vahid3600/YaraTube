package com.yaratech.yaratube.ui.productlist;

import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface ProductListContract {
    interface View {

        void showListProducts(List<Product> productList);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void fetchProductListFromRemote(int id);

        void cancelProductListRequest();
    }
}
