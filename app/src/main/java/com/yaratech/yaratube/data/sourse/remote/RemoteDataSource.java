package com.yaratech.yaratube.data.sourse.remote;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.LoginGoogle;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.utils.Util;
import com.yaratech.yaratube.data.model.Store;

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
    public void getHome(final LoadDataCallback callback) {
        if (Util.isOnline(context)) {
            Call<Store> storeCall = Util.getServices().getStoreService().getStore();
            storeCall.enqueue(new Callback<Store>() {
                @Override
                public void onResponse(Call<Store> call, Response<Store> response) {

                    if (response.isSuccessful()) {
                        callback.onDataLoaded(response.body());
                    } else
                        callback.onMessage("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<Store> call, Throwable t) {

                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getCategory(final LoadDataCallback callback) {
        if (Util.isOnline(context)) {

            final Call<List<CategoryList>> categoryListCall = Util.getServices().getStoreService()
                    .getCategory();
            categoryListCall.enqueue(new Callback<List<CategoryList>>() {
                @Override
                public void onResponse(Call<List<CategoryList>> call,
                                       Response<List<CategoryList>> response) {

                    if (response.isSuccessful()) {
                        callback.onDataLoaded(response.body());
                    } else
                        callback.onMessage("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<List<CategoryList>> call, Throwable t) {
                    callback.onMessage("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getProductList(int id, final LoadDataCallback callback) {
        if (Util.isOnline(context)) {
            final Call<List<ProductList>> productListCall = Util.getServices().getStoreService()
                    .getProductList(id);
            productListCall.enqueue(new Callback<List<ProductList>>() {
                @Override
                public void onResponse(Call<List<ProductList>> call,
                                       Response<List<ProductList>> response) {

                    if (response.isSuccessful()) {
                        List<ProductList> productLists = response.body();
                        callback.onDataLoaded(response.body());
                    } else
                        callback.onMessage("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<List<ProductList>> call, Throwable t) {
                    callback.onMessage("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void getProductDetail(int id, final LoadDataCallback callback) {
        if (Util.isOnline(context)) {
            Log.e("tag", id + "");

            final Call<ProductDetail> productDetailCall = Util.getServices().getStoreService()
                    .getProductDetail(id);
            productDetailCall.enqueue(new Callback<ProductDetail>() {
                @Override
                public void onResponse(Call<ProductDetail> call,
                                       Response<ProductDetail> response) {

                    if (response.isSuccessful()) {
                        Log.e("tag", response.body().getDescription());
                        callback.onDataLoaded(response.body());
                    } else {
                        Log.e("tag", response.errorBody().toString());

                        callback.onMessage("عملیات با خطا مواجه شد!");
                    }
                }

                @Override
                public void onFailure(Call<ProductDetail> call, Throwable t) {
                    callback.onMessage("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void sendGoogleLogin(String tokenId, String deviceId, String deviceOs,
                                String deviceModel, final LoadDataCallback callback) {
        if (Util.isOnline(context)) {

            final Call<LoginGoogle> loginGoogleCall = Util.getServices().getStoreService()
                    .sendGoogleLogin(
                            tokenId,
                            deviceId,
                            deviceOs,
                            deviceModel);
            loginGoogleCall.enqueue(new Callback<LoginGoogle>() {
                @Override
                public void onResponse(Call<LoginGoogle> call,
                                       Response<LoginGoogle> response) {

                    if (response.isSuccessful()) {
                        callback.onDataLoaded(response.body());
                    } else {
                        Log.e("tag", response.errorBody().toString());

                        callback.onMessage("عملیات با خطا مواجه شد!");
                    }
                }

                @Override
                public void onFailure(Call<LoginGoogle> call, Throwable t) {
                    callback.onMessage("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void sendMobileLoginStep1(String mobile, String deviceId, String deviceModel,
                                     String deviceOs, String gcm, final LoadDataCallback callback) {
        if (Util.isOnline(context)) {

            final Call<MobileLoginStep1> loginGoogleCall = Util.getServices().getStoreService()
                    .sendMobileLoginStep1(
                            mobile,
                            deviceId,
                            deviceModel,
                            deviceOs,
                            gcm);
            loginGoogleCall.enqueue(new Callback<MobileLoginStep1>() {
                @Override
                public void onResponse(Call<MobileLoginStep1> call,
                                       Response<MobileLoginStep1> response) {

                    if (response.isSuccessful()) {
                        callback.onDataLoaded(response.body());
                        callback.onMessage(response.body().getMessage());
                    } else {
                        Log.e("tag", response.errorBody().toString());

                        callback.onMessage("عملیات با خطا مواجه شد!");
                    }
                }

                @Override
                public void onFailure(Call<MobileLoginStep1> call, Throwable t) {
                    callback.onMessage("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

    @Override
    public void sendMobileLoginStep2(String mobile, String deviceId, String verificationCode,
                                     String nickname, final LoadDataCallback callback) {
        if (Util.isOnline(context)) {

            final Call<LoginGoogle> loginGoogleCall = Util.getServices().getStoreService()
                    .sendMobileLoginStep2(
                            mobile,
                            deviceId,
                            verificationCode,
                            nickname);
            loginGoogleCall.enqueue(new Callback<LoginGoogle>() {
                @Override
                public void onResponse(Call<LoginGoogle> call,
                                       Response<LoginGoogle> response) {

                    if (response.isSuccessful()) {
                        callback.onDataLoaded(response.body());
                    } else {
                        Log.e("tag", response.errorBody().toString());

                        callback.onMessage("عملیات با خطا مواجه شد!");
                    }
                }

                @Override
                public void onFailure(Call<LoginGoogle> call, Throwable t) {
                    callback.onMessage("عملیات با خطا مواجه شد!");
                }
            });
        } else
            callback.onMessage("ارتباط اینترنت شما قطع است");
    }

}

