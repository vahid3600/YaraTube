package com.yaratech.yaratube.ui.dialog.logincontainer.verification;

import android.content.Context;

import com.yaratech.yaratube.data.model.DBModel.Profile;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/17/2018.
 */

public class VerificationNumberPresenter implements VerificationContract.Presenter {

    VerificationContract.View view;
    Context context;
    Repository repository;

    public VerificationNumberPresenter(Context context, VerificationContract.View view) {
        this.view = view;
        this.repository = Repository.getINSTANCE(new RemoteDataSource(context),
                new DatabaseSourse(context));
    }

    @Override
    public void sendVerificationCode(final String deviceId, final String verificationCode,
                                     final String nickname) {

        repository.getMobile(new DataSource.DatabaseSourse.GetMobileCallback() {
            @Override
            public void loadMobileCallback(String mobile) {
                repository.sendMobileLoginStep2(mobile , deviceId, verificationCode, nickname,
                        new DataSource.RemoteDataSourse.LoadDataCallback() {
                            @Override
                            public void onDataLoaded(Object result) {
                                view.closeDialog();
                            }

                            @Override
                            public void onMessage(String msg) {
                                view.showMessage(msg);
                            }
                        }, new DataSource.DatabaseSourse.AddToDatabase() {
                            @Override
                            public void saveProfile(Profile profile) {

                            }

                            @Override
                            public void updateProfile(Profile profile) {
                                repository.updateProfile(profile);
                            }
                        });
            }
        });
    }
}
