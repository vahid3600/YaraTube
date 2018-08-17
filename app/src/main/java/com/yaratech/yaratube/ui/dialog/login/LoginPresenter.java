package com.yaratech.yaratube.ui.dialog.login;

import android.content.Context;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/17/2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;
    Context context;
    Repository loginRepository;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.view = view;
        this.loginRepository = Repository.getINSTANCE(new RemoteDataSource(context));
    }


    @Override
    public void loginByGoogle(String tokenId, String deviceId, String deviceOs, String deviceModel) {

        loginRepository.sendGoogleLogin(tokenId, deviceId, deviceOs, deviceModel,
                new DataSource.LoadDataCallback() {
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
