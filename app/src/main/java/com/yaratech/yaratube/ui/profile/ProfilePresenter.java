package com.yaratech.yaratube.ui.profile;

import android.content.Context;

import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.ui.category.CategoryContract;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private Repository profileRepository;
    Context context;

    public
    ProfilePresenter(Context context, ProfileContract.View view){
        this.profileRepository = Repository.getINSTANCE(new RemoteDataSource(context));
        this.view = view;
    }

//    @Override
//    public void fetchCategoryFromRemote() {
//        view.showLoading();
//
//        categoryRepository.getCategory(new DataSource.LoadCatetoryCallback() {
//            @Override
//            public void onCategoryLoaded(List<Category_list> categoryList) {
//                view.hideLoading();
//                view.showListCategory(categoryList);
//            }
//
//            @Override
//            public void onError(String msg) {
//                view.hideLoading();
//                view.showMessage(msg);
//            }
//        });
//
//    }

    @Override
    public void fetchProfileGalery() {
//        profileRepository.getImageFromGalery(context, new DataSource.LoadImageGaleryCallback() {
//            @Override
//            public void onImageLoaded(List<Category_list> categoryList) {
//
//            }
//
//            @Override
//            public void onError(String msg) {
//
//            }
//        });
    }

    @Override
    public void fetchProfileCamera() {
        MenuActivity menuActivity = new MenuActivity();
        profileRepository.getImageFromCamera(menuActivity);
    }

    @Override
    public void sendProfileData() {

    }
}
