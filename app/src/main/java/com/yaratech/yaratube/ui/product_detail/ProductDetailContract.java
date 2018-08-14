package com.yaratech.yaratube.ui.product_detail;

import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface ProductDetailContract {
    interface View {

        void showProductDetail(ProductDetail productDetail);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void fetchProductDetailFromRemote(int id);
    }
}
