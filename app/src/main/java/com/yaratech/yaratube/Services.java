package com.yaratech.yaratube;

import com.yaratech.yaratube.dataModels.Store;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vah on 8/6/2018.
 */

public interface Services {
    @GET("store/16")
    Call<Store> getStore();
}
