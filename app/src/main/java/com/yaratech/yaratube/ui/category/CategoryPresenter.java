package com.yaratech.yaratube.ui.category;

import android.content.Context;

import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.home.HomeContract;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Repository categoryRepository;

    public CategoryPresenter(Context context, CategoryContract.View view){
        this.categoryRepository = Repository.getINSTANCE(new RemoteDataSource(context));
        this.view = view;
    }

    @Override
    public void fetchCategoryFromRemote() {
        view.showLoading();

        categoryRepository.getCategory(new DataSource.LoadCatetoryCallback() {
            @Override
            public void onCategoryLoaded(List<Category_list> categoryList) {
                view.hideLoading();
                view.showListCategory(categoryList);
            }

            @Override
            public void onError(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });

    }
}
