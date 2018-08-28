package com.yaratech.yaratube.ui.login;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.login.verification.VerificationFragment;

/**
 * Created by Vah on 8/25/2018.
 */

public class LoginDialogContainerPresenter implements LoginDialogContract.Presenter {
    Repository repository;
    LoginDialogContract.View view;

    public LoginDialogContainerPresenter(Context context, LoginDialogContract.View view){
        this.view = view;
        this.repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }

    @Override
    public void setUserStateLogin(int state) {
        repository.saveUserLoginState(state);
    }

    @Override
    public void checkUserStateLogin() {
        Log.e("Tag",repository.getUserLoginState()+"");
        if (repository.getUserLoginState() == 3)
            view.showVerification(new VerificationFragment());
        else
            view.showLoginDialog();
    }
}
