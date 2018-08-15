package com.yaratech.yaratube.data.sourse.remote;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.utils.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Vah on 8/6/2018.
 */

public interface Service {
    @GET("store/"+ Util.STORE_ID)
    Call<Store> getStore();

    @GET("category/"+ Util.STORE_ID +"/463")
    Call<List<CategoryList>> getCategory();

    @GET("listproducts/{category_id}")
    Call<List<ProductList>> getProductList(@Path("category_id") int categoryId);

    @GET("product/{product_id}")
    Call<ProductDetail> getProductDetail(@Path("product_id") int productId);

    @FormUrlEncoded
    @POST("profile")
    Call<List<CategoryList>> sendProfile();
}
