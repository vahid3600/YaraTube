package com.yaratech.yaratube.ui.profile;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.utils.Utils;

import java.util.Date;

/**
 * Created by Vah on 8/8/2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private Repository profileRepository;
    Context context;

    public
    ProfilePresenter(Context context, ProfileContract.View view){
        this.profileRepository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
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
    public String getUserAuthorization() {
        return profileRepository.getUserAuthorization();
    }


    @Override
    public void fetchProfileCamera() {
        MenuActivity menuActivity = new MenuActivity();
    }

    @Override
    public void sendImage(String authorization, String path) {
        view.showLoading();
        profileRepository.sendImage(authorization, path, new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();

            }

            @Override
            public void onMessage(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });
    }

    @Override
    public void sendProfileData(final String name,final String gender,final Date birthday) {
        view.showLoading();
        Log.e("Tag","1");
//        profileRepository.sendProfile(null, null, null,null, null, null, null, null, null, new DataSource.RemoteDataSourse.LoadDataCallback() {
//            @Override
//            public void onDataLoaded(Object result) {
//                Log.e("Tag","2");
//                profileRepository.sendProfile(name, birthday, gender, null, null, null, Utils.getDeviceId(context), Utils.getDeviceModel(), Utils.getDeviceOS(), null, new DataSource.RemoteDataSourse.LoadDataCallback() {
//                    @Override
//                    public void onDataLoaded(Object result) {
//                        Log.e("Tag","1");
//                        view.hideLoading();
//                    }
//
//                    @Override
//                    public void onMessage(String msg) {
//                        view.hideLoading();
//                        view.showMessage(msg);
//                    }
//                });
//            }
//
//            @Override
//            public void onMessage(String msg) {
//                Log.e("Tag","4");
//            }
//        });
    }
}
