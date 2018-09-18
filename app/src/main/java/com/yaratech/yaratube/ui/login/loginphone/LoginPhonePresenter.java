package com.yaratech.yaratube.ui.login.loginphone;

import android.content.Context;

import com.yaratech.yaratube.data.model.dbmodel.Profile;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/17/2018.
 */

public class LoginPhonePresenter implements LoginPhoneContract.Presenter {

    LoginPhoneContract.View view;
    Repository phoneRepository;

    public LoginPhonePresenter(Context context, LoginPhoneContract.View view) {
        this.view = view;
        this.phoneRepository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }

    @Override
    public void loginByMobile(String mobile, String deviceId, String deviceModel,
                              String deviceOs, String gcm) {
        view.showProgressbar();
        phoneRepository.sendMobileLoginStep1(mobile, deviceId, deviceModel, deviceOs, gcm,
                new DataSource.RemoteDataSourse.LoadDataCallback() {
                    @Override
                    public void onDataLoaded(Object result) {
                        view.hideProgressbar();
                        view.showVerificationDialog();
                    }

                    @Override
                    public void onMessage(String msg) {
                        view.hideProgressbar();
                        view.showMessage(msg);
                    }
                }
                , new DataSource.DatabaseSourse.AddToDatabase() {
                    @Override
                    public void saveProfile(Profile profile) {
                        phoneRepository.saveProfile(profile);
                    }

                    @Override
                    public void updateProfile(Profile profile) {

                    }
                });
    }
}
