package com.yaratech.yaratube.ui.dialog.verification;

import android.content.Context;

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
    public void sendVerificationCode(String deviceId, String verificationCode,
                                     String nickname) {

        repository.sendMobileLoginStep2(repository.getMobile(), deviceId, verificationCode, nickname,
                new DataSource.RemoteDataSourse.LoadDataCallback() {
                    @Override
                    public void onDataLoaded(Object result) {
                        view.dismissDialog();
                    }

                    @Override
                    public void onMessage(String msg) {
                        view.showMessage(msg);
                    }
                });
    }
}
