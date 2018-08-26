package com.yaratech.yaratube.ui;

import android.content.Context;

import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/26/2018.
 */

public class MenuActivityPresenter implements MenuActivityContract.Presenter {

    Repository repository;

    public MenuActivityPresenter(Context context) {
        repository = Repository.getINSTANCE(new RemoteDataSource(context),
                new DatabaseSourse(context));
    }

    @Override
    public boolean getUserLoginStatus() {
        return repository.getUserLoginStatus();
    }

    @Override
    public void setUserLoginStatus(boolean status) {
        repository.saveUserLoginStatus(status);
    }
}
