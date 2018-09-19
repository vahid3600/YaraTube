package com.yaratech.yaratube.ui.profile;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.GetProfileResponse;
import com.yaratech.yaratube.data.model.ProfileResponse;
import com.yaratech.yaratube.data.model.dbmodel.Profile;
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

    public ProfilePresenter(Context context, ProfileContract.View view) {
        this.profileRepository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
        this.context = context;
        this.view = view;
    }


    @Override
    public void getProfileData(String authorization) {
        if (view != null)
            view.showLoading();
        profileRepository.getProfile(authorization, new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                if (view != null) {
                    view.hideLoading();
                    view.onDataLoad((GetProfileResponse) result);
                }
            }

            @Override
            public void onMessage(String msg) {
                if (view != null) {
                    view.hideLoading();
                    view.showMessage(msg);
                }
            }
        });
    }

    @Override
    public Profile getProfileFromDB() {
        return profileRepository.getProfile();
    }

    @Override
    public String getUserAuthorization() {
        return profileRepository.getUserAuthorization();
    }


    @Override
    public void sendImage(String authorization, String path) {
        if (view != null)
            view.showLoading();
        Log.e("presenter", authorization + " " + path);
        profileRepository.sendImage(authorization, path, new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();
                view.updateImage((ProfileResponse) result);
            }

            @Override
            public void onMessage(String msg) {
                if (view != null) {
                    view.hideLoading();
                    view.showMessage(msg);
                }
            }
        });
    }

    @Override
    public void sendProfileData(String authorization, final String name, final String gender, final String birthday) {
        if (view != null)
            view.showLoading();

        profileRepository.sendProfile(
                authorization,
                name,
                birthday,
                gender,
                Utils.getDeviceId(context),
                Utils.getDeviceModel(),
                Utils.getDeviceOS(),
                new DataSource.RemoteDataSourse.LoadDataCallback() {
                    @Override
                    public void onDataLoaded(Object result) {
                        if (view != null)
                            view.hideLoading();

                        Profile profile = new Profile();
                        ProfileResponse profileResponse = (ProfileResponse) result;
                        profile.setDateOfBirth((String) profileResponse.getData().getDateOfBirth());
                        profile.setNickName(profileResponse.getData().getNickname());
                        profile.setAvatar((String) profileResponse.getData().getAvatar());
                        profile.setGender((String) profileResponse.getData().getGender());
                        profileRepository.updateProfile(profile);
                    }

                    @Override
                    public void onMessage(String msg) {
                        if (view != null) {
                            view.hideLoading();
                            view.showMessage(msg);
                        }
                    }
                });
    }

    @Override
    public void signOut() {
        profileRepository.saveUserLoginStatus(false);
        profileRepository.deleteProfile();
        profileRepository.saveUserLoginState(1);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isAttached() {
        return view != null;
    }
}
