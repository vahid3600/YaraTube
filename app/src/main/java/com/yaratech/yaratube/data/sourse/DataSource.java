package com.yaratech.yaratube.data.sourse.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.model.Store;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public interface DataSource {

    interface LoadStoreCallback {
        void onStoreLoaded(Store store);

        void onError(String msg);
    }

    interface LoadCatetoryCallback {
        void onCategoryLoaded(List<Category_list> categoryList);

        void onError(String msg);
    }

    void getHome(LoadStoreCallback callback);

    void getCategory(LoadCatetoryCallback callback);

}
