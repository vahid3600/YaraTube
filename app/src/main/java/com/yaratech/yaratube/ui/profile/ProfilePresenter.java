package com.yaratech.yaratube.ui.profile;

import android.content.Context;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.MenuActivity;

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

    @Override
    public void fetchProfileGalery() {
//        profileRepository.getImageFromGalery(context, new DataSource.LoadImageGaleryCallback() {
//            @Override
//            public void onImageLoaded(List<CategoryList> categoryList) {
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
        profileRepository.getImageFromCamera();
    }

    @Override
    public void sendProfileData() {

    }
}
