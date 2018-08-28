package com.yaratech.yaratube.ui.home.category;

import android.content.Context;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Repository repository;

    CategoryPresenter(Context context, CategoryContract.View view){
        this.repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
        this.view = view;
    }

    @Override
    public void fetchCategoryFromRemote() {
        view.showLoading();

        repository.getCategory(new DataSource.RemoteDataSourse.LoadDataCallback() {

            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();
                view.showListCategory((List<CategoryList>) result);
            }

            @Override
            public void onMessage(String msg) {
                view.hideLoading();
                view.showMessage(msg);
                view.showReload();
            }
        });

    }

    @Override
    public void cancelCategoryRequest() {
        repository.cancelGetCategoryRequest();
    }
}
