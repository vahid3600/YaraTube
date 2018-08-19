package com.yaratech.yaratube.ui.home.category;

import com.yaratech.yaratube.data.model.CategoryList;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface CategoryContract {
    interface View {

        void showListCategory(List<CategoryList> category_list);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void fetchCategoryFromRemote();

        void cancelCategoryRequest();
    }
}
