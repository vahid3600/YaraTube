package com.yaratech.yaratube.ui.productlist;

import android.content.Context;

import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class ProductListPresenter implements ProductListContract.Presenter {

    private ProductListContract.View view;
    private Repository productListRepository;

    public ProductListPresenter(Context context, ProductListContract.View view) {
        this.productListRepository = Repository.getINSTANCE(new RemoteDataSource(context),
                new DatabaseSourse(context));
        this.view = view;
    }

    @Override
    public void fetchProductListFromRemote(int id) {
        view.showLoading();

        productListRepository.getProductList(id, new DataSource.RemoteDataSourse.LoadDataCallback() {

            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();
                view.showListProducts((List<Product>) result);
            }

            @Override
            public void onMessage(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });

    }

    @Override
    public void cancelProductListRequest() {
        productListRepository.cancelGetProductListRequest();
    }
}
