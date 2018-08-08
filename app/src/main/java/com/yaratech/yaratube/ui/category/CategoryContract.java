package com.yaratech.yaratube.ui.category;

import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.model.Store;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface CategoryContract {
    interface View {

        void showListCategory(List<Category_list> category_list);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void fetchCategoryFromRemote();
    }

    void adaptRecyclerview(List<Category_list> category_list);
}
