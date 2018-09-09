package com.yaratech.yaratube.ui;

import android.content.Context;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/26/2018.
 */

public class MenuActivityPresenter implements MenuActivityContract.Presenter {

    Repository repository;

    public MenuActivityPresenter(Context context) {
        repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }

    @Override
    public boolean getUserLoginStatus() {
        return repository.getUserLoginStatus();
    }

    @Override
    public void signOut() {
        repository.saveUserLoginStatus(false);
        repository.deleteProfile();
<<<<<<< HEAD
        repository.saveUserLoginState(1);
=======
>>>>>>> daa60e45d156f167875e34092ae171504d245a65
    }

}
