package com.yaratech.yaratube.presenter;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.Utils;
import com.yaratech.yaratube.dagger.component.DaggerGetServices;
import com.yaratech.yaratube.dagger.component.GetServices;
import com.yaratech.yaratube.dagger.module.RetrofitModule;
import com.yaratech.yaratube.dataModels.Category_list;
import com.yaratech.yaratube.dataModels.Store;
import com.yaratech.yaratube.view.CategoryFragmentsInterface;
import com.yaratech.yaratube.view.FragmentsInterface;
import com.yaratech.yaratube.view.HomeFragmentsInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Vah on 8/6/2018.
 */

public class GetData implements GetDataInterface {
    FragmentsInterface fragmentsInterface;
    HomeFragmentsInterface homeFragmentsInterface;
    CategoryFragmentsInterface categoryFragmentsInterface;

    Utils utils = new Utils();
    private Store store;
    private List<Category_list> category_list;

    public GetData(FragmentsInterface fragmentsInterface, HomeFragmentsInterface homeFragmentsInterface) {
        this.fragmentsInterface = fragmentsInterface;
        this.homeFragmentsInterface = homeFragmentsInterface;
    }

    public GetData(FragmentsInterface fragmentsInterface, CategoryFragmentsInterface categoryFragmentsInterface) {
        this.fragmentsInterface = fragmentsInterface;
        this.categoryFragmentsInterface = categoryFragmentsInterface;
    }

    @Override
    public Store getStoreData(Context context) {
        if (utils.isOnline(context)) {
            fragmentsInterface.showLoading();
            GetServices getServices = DaggerGetServices
                    .builder()
                    .retrofitModule(new RetrofitModule())
                    .build();
            Call<Store> storeCall = getServices.getStoreService().getStore();
            storeCall.enqueue(new Callback<Store>() {
                @Override
                public void onResponse(Call<Store> call, Response<Store> response) {
                    fragmentsInterface.hideLoading();
                    if (response.isSuccessful()) {
                        store = response.body();
                        Log.e("Tag", store.getName());
                        homeFragmentsInterface.adaptRecyclerview(store);
                    } else
                        fragmentsInterface.showMessage("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<Store> call, Throwable t) {

                }
            });
        } else
            fragmentsInterface.showMessage("ارتباط اینترنت شما قطع است");
        return store;
    }

    @Override
    public Store getCategoryListData(Context context) {
        if (utils.isOnline(context)) {
            fragmentsInterface.showLoading();
            GetServices getServices = DaggerGetServices
                    .builder()
                    .retrofitModule(new RetrofitModule())
                    .build();
            final Call<List<Category_list>> categoryListCall = getServices.getStoreService().getCategory();
            categoryListCall.enqueue(new Callback<List<Category_list>>() {
                @Override
                public void onResponse(Call<List<Category_list>> call, Response<List<Category_list>> response) {
                    fragmentsInterface.hideLoading();
                    if (response.isSuccessful()) {
                        category_list = response.body();
                        categoryFragmentsInterface.adaptRecyclerview(category_list);
                    } else
                        fragmentsInterface.showMessage("عملیات با خطا مواجه شد!");
                }

                @Override
                public void onFailure(Call<List<Category_list>> call, Throwable t) {

                }
            });
        } else
            fragmentsInterface.showMessage("ارتباط اینترنت شما قطع است");
        return store;
    }
}
