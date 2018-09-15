package com.yaratech.yaratube.ui.profile;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.GetProfileResponse;
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
        this.context = context;
        this.view = view;
    }



    @Override
    public void getProfileData(String authorization) {
        view.showLoading();
        profileRepository.getProfile(authorization, new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();
                view.onDataLoad((GetProfileResponse) result);
            }

            @Override
            public void onMessage(String msg) {
                view.hideLoading();
                view.showMessage(msg);
            }
        });
    }

    @Override
    public String getUserAuthorization() {
        return profileRepository.getUserAuthorization();
    }


    @Override
    public void sendImage(String authorization, String path) {
        view.showLoading();
        Log.e("presenter",authorization+" "+path);
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
    public void sendProfileData(String authorization, final String name,final int gender,final String birthday) {
        view.showLoading();
        String userGender = "";
        switch (gender){
            case 0:
                userGender = "men";
                break;
            case 1:
                userGender = "women";
                break;
        }
        Log.e("Tag",userGender+" "+gender);
                profileRepository.sendProfile(
                        authorization,
                        name,
                        birthday,
                        userGender,
                        Utils.getDeviceId(context),
                        Utils.getDeviceModel(),
                        Utils.getDeviceOS(),
                        new DataSource.RemoteDataSourse.LoadDataCallback() {
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
