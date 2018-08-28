package com.yaratech.yaratube.ui.login.verification;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.DBModel.Profile;
import com.yaratech.yaratube.data.model.LoginResponse;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/17/2018.
 */

public class VerificationPresenter implements VerificationContract.Presenter {

    VerificationContract.View view;
    Context context;
    Repository repository;

    public VerificationPresenter(Context context, VerificationContract.View view) {
        this.view = view;
        this.repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }

    @Override
    public void sendVerificationCode(final String mobile, final String deviceId, final String verificationCode,
                                     final String nickname) {

        repository.sendMobileLoginStep2(mobile, deviceId, verificationCode, nickname,
                new DataSource.RemoteDataSourse.LoadDataCallback() {
                    @Override
                    public void onDataLoaded(Object result) {
                        Profile profile = new Profile();
                        LoginResponse loginResponse = (LoginResponse) result;
                        profile.setUserId(loginResponse.getUserId());
                        profile.setFinoToken(loginResponse.getFinoToken());
                        profile.setNickName(loginResponse.getNickname());
                        profile.setUserToken(loginResponse.getToken());
                        profile.setMobile(mobile);
                        Log.e("MOBILE",loginResponse.getToken());
                        repository.saveProfile(profile);
                        repository.saveUserAuthorization(loginResponse.getToken());
                        repository.saveUserLoginStatus(true);
                        view.closeDialog();
                    }

                    @Override
                    public void onMessage(String msg) {
                        view.showMessage(msg);
                    }
                },
                new DataSource.DatabaseSourse.AddToDatabase() {
                    @Override
                    public void saveProfile(Profile profile) {
                        repository.saveProfile(profile);
                    }

                    @Override
                    public void updateProfile(Profile profile) {

                    }
                });
    }

    @Override
    public void setUserLoginStatus(boolean status) {
        repository.saveUserLoginStatus(status);
    }

    @Override
    public void saveUserMobile(String mobile) {
        repository.saveUserMobile(mobile);
    }

    @Override
    public String getUserMobile() {
        return repository.getUserMobile();
    }
}
