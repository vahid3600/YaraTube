package com.yaratech.yaratube.ui.login.loginmethod;

import android.content.Context;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/17/2018.
 */

public class SelectLoginMethodPresenter implements SelectLoginMethodContract.Presenter {

    SelectLoginMethodContract.View view;
    Context context;
    Repository loginRepository;

    public SelectLoginMethodPresenter(Context context, SelectLoginMethodContract.View view) {
        this.view = view;
        this.loginRepository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }


    @Override
    public void loginByGoogle(String tokenId, String deviceId, String deviceOs, String deviceModel) {

        loginRepository.sendGoogleLogin(tokenId, deviceId, deviceOs, deviceModel,
                new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                view.showLoginByPhoneDialog();
            }

            @Override
            public void onMessage(String msg) {
                view.showMessage(msg);
            }
        });
    }
}
