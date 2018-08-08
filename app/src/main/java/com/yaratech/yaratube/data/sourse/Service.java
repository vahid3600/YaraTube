package com.yaratech.yaratube.data.sourse.remote;

import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vah on 8/6/2018.
 */

public interface Service {
    @GET("store/16")
    Call<Store> getStore();

    @GET("category/16/463")
    Call<List<Category_list>> getCategory();
}
