package com.yaratech.yaratube.data.sourse.remote;

/**
 * Created by Vah on 8/8/2018.
 */

public interface DataSource {

    interface LoadDataCallback<T> {
        void onDataLoaded(T result);

        void onError(String msg);
    }

    void getHome(LoadDataCallback callback);

    void getCategory(LoadDataCallback callback);

    void getProductList(int id, LoadDataCallback callback);

    void getProductDetail(int id, LoadDataCallback callback);
}
