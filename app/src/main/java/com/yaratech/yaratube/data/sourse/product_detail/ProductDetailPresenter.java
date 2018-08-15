package com.yaratech.yaratube.data.sourse.product_detail;

import android.content.Context;

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
    private Repository productListRepository;

    public ProductDetailPresenter(Context context, ProductDetailContract.View view){
        this.productListRepository = Repository.getINSTANCE(new RemoteDataSource(context));
        this.view = view;
    }

    @Override
    public void fetchProductListFromRemote(int id) {
        view.showLoading();

        productListRepository.getProductList(id, new DataSource.LoadDataCallback() {

            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();
                view.showListProducts((List<ProductList>) result);
            }

            @Override
            public void onError(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });

    }
}
