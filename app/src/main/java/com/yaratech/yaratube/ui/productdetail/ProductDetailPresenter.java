package com.yaratech.yaratube.ui.productdetail;

import android.content.Context;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailContract.View view;
    private int offset = 0;
    private Repository repository;

    public ProductDetailPresenter(Context context, ProductDetailContract.View view) {
        this.repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
        this.view = view;
    }

    @Override
    public void fetchProductDetailFromRemote(int id) {
        if (view != null)
            view.showLoading();

        repository.getProductDetail(id, new DataSource.RemoteDataSourse.LoadDataCallback() {

            @Override
            public void onDataLoaded(Object result) {
                if (view != null) {
                    view.hideLoading();
                    view.showProductDetail((ProductDetail) result);
                }
            }

            @Override
            public void onMessage(String msg) {
                if (view != null) {
                    view.hideLoading();
                    view.showMessage(msg);
                }
            }
        });

    }

    @Override
    public void cancelProductDetailRequest() {
        repository.cancelGetProductDetailRequest();
    }

    @Override
    public void fetchCommentFromRemote(int id) {
        if (view != null)
            view.showLoading();

        repository.getComment(id, new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                if (view != null) {
                    view.hideLoading();
                    view.showComment((List<Comment>) result);
                }
            }

            @Override
            public void onMessage(String msg) {
                if (view != null) {
                    view.hideLoading();
                    view.showMessage(msg);
                }
            }
        });
    }

    @Override
    public void cancelCommentRequest() {
        repository.cancelGetCommentRequest();
    }

    @Override
    public boolean getUserLoginStatus() {
        return repository.getUserLoginStatus();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isAttached() {
        return view != null;
    }
}
