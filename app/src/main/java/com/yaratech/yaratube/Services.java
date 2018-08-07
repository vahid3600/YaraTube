package com.yaratech.yaratube;

import com.yaratech.yaratube.dataModels.Category_list;
import com.yaratech.yaratube.dataModels.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vah on 8/6/2018.
 */

public interface Services {
    @GET("store/16")
    Call<Store> getStore();

    @GET("category/16/463")
    Call<List<Category_list>> getCategory();
}
