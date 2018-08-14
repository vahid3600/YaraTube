package com.yaratech.yaratube.ui.product_detail;

import android.content.Context;

import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailContract.View view;
    private Repository productDetailRepository;

    public ProductDetailPresenter(Context context, ProductDetailContract.View view){
        this.productDetailRepository = Repository.getINSTANCE(new RemoteDataSource(context));
        this.view = view;
    }

    @Override
    public void fetchProductDetailFromRemote(int id) {
        view.showLoading();

        productDetailRepository.getProductDetail(id, new DataSource.LoadProductDetailCallback() {
            @Override
            public void onProductDetailLoaded(ProductDetail productDetail) {
                view.hideLoading();
                view.showProductDetail(productDetail);
            }

            @Override
            public void onError(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });

    }
}
