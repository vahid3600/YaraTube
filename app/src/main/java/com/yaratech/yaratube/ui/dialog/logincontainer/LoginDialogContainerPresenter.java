package com.yaratech.yaratube.ui.dialog.logincontainer;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.dialog.logincontainer.verification.VerificationDialog;

/**
 * Created by Vah on 8/25/2018.
 */

public class LoginDialogContainerPresenter implements LoginDialogContract.Presenter {
    Repository repository;
    LoginDialogContract.View view;

    public LoginDialogContainerPresenter(Context context, LoginDialogContract.View view){
        this.view = view;
        this.repository = Repository.getINSTANCE(new RemoteDataSource(context)
                , new DatabaseSourse(context));
    }

    @Override
    public void setUserStateLogin(int state) {
        repository.saveUserLoginState(state);
    }

    @Override
    public void checkUserStateLogin() {
        Log.e("Tag",repository.getUserLoginState()+"");
        if (repository.getUserLoginState() == 3)
            view.showVerification(new VerificationDialog());
        else
            view.showLoginDialog();
    }
}
