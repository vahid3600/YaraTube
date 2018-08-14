package com.yaratech.yaratube.data.sourse.remote;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.utils.Util;
import com.yaratech.yaratube.dagger.component.DaggerGetServices;
import com.yaratech.yaratube.dagger.component.GetServices;
import com.yaratech.yaratube.dagger.module.RetrofitModule;
import com.yaratech.yaratube.data.model.Store;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vah on 8/8/2018.
 */

public class RemoteDataSource implements DataSource {

    private Context context;

    public RemoteDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void getHome(final LoadStoreCallback callback) {
        if (Util.isOnline(context)) {
            Call<Store> storeCall = Util.getServices().getStoreService().getStore();
            storeCall.enqueue(new Callback<Store>() {
                @Override
                public void onResponse(Call<Store> call, Response<Store> response) {

                    if (response.isSuccessful()) {
                        callback.onStoreLoaded(response.body());
                    } else
                        callback.onError("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<Store> call, Throwable t) {

                }
            });
        } else
            callback.onError("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getCategory(final LoadCatetoryCallback callback) {
        if (Util.isOnline(context)) {

            final Call<List<Category_list>> categoryListCall = Util.getServices().getStoreService()
                    .getCategory();
            categoryListCall.enqueue(new Callback<List<Category_list>>() {
                @Override
                public void onResponse(Call<List<Category_list>> call,
                                       Response<List<Category_list>> response) {

                    if (response.isSuccessful()) {
                        callback.onCategoryLoaded(response.body());
                    } else
                        callback.onError("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<List<Category_list>> call, Throwable t) {
                    callback.onError("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onError("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getProductList(int id, final LoadProductListCallback callback) {
        if (Util.isOnline(context)) {
            final Call<List<ProductList>> productListCall = Util.getServices().getStoreService()
                    .getProductList(id);
            productListCall.enqueue(new Callback<List<ProductList>>() {
                @Override
                public void onResponse(Call<List<ProductList>> call,
                                       Response<List<ProductList>> response) {

                    if (response.isSuccessful()) {
                        List<ProductList> productLists = response.body();
                        callback.onProductListLoaded(response.body());
                    } else
                        callback.onError("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<List<ProductList>> call, Throwable t) {
                    callback.onError("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onError("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getProductDetail(int id, final LoadProductDetailCallback callback) {
        if (Util.isOnline(context)) {
            Log.e("tag",id+"");

            final Call<ProductDetail> productDetailCall = Util.getServices().getStoreService()
                    .getProductDetail(id);
            productDetailCall.enqueue(new Callback<ProductDetail>() {
                @Override
                public void onResponse(Call<ProductDetail> call,
                                       Response<ProductDetail> response) {

                    if (response.isSuccessful()) {
                        Log.e("tag",response.body().getDescription());
                        callback.onProductDetailLoaded(response.body());
                    } else{
                        Log.e("tag",response.errorBody().toString());

                        callback.onError("عملیات با خطا مواجه شد!");}
                }

                @Override
                public void onFailure(Call<ProductDetail> call, Throwable t) {
                    callback.onError("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onError("ارتباط اینترنت شما قطع است");
    }

}

