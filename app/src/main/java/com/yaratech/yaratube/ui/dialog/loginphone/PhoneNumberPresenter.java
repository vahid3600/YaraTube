package com.yaratech.yaratube.ui.dialog.loginphone;

import android.content.Context;

import com.yaratech.yaratube.data.model.DBModel.Profile;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/17/2018.
 */

public class PhoneNumberPresenter implements PhoneNumberContract.Presenter {

    PhoneNumberContract.View view;
    Context context;
    Repository phoneRepository;

    public PhoneNumberPresenter(Context context, PhoneNumberContract.View view) {
        this.view = view;
        this.phoneRepository = Repository.getINSTANCE(new RemoteDataSource(context),
                new DatabaseSourse(context));
    }

    @Override
    public void loginByMobile(String mobile, String deviceId, String deviceModel,
                              String deviceOs, String gcm) {
        phoneRepository.sendMobileLoginStep1(mobile, deviceId, deviceModel, deviceOs, gcm,
                new DataSource.RemoteDataSourse.LoadDataCallback() {
                    @Override
                    public void onDataLoaded(Object result) {
                        view.dismissDialog();
                    }

                    @Override
                    public void onMessage(String msg) {
                        view.showMessage(msg);
                    }
                }
                , new DataSource.DatabaseSourse.AddToDatabase() {
                    @Override
                    public void saveProfile(Profile profile) {
                        phoneRepository.setProfile(profile);
                    }
                });
    }
}
