package com.yaratech.yaratube.ui.home.dashboard;

import android.content.Context;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/8/2018.
 */

public class StorePresenter implements StoreContract.Presenter {

    private StoreContract.View view;
    private Repository homeRepository;

    public StorePresenter(Context context, StoreContract.View view){
        this.homeRepository = Repository.getINSTANCE(new RemoteDataSource(context),
                new DatabaseSourse(context));
        this.view = view;
    }
    @Override
    public void fetchHomeFromRemote() {
        view.showLoading();

        homeRepository.getHome(new DataSource.RemoteDataSourse.LoadDataCallback() {

            @Override
            public void onDataLoaded(Object result) {
                view.hideLoading();
                view.showListHome((Store) result);
            }

            @Override
            public void onMessage(String msg) {
                view.hideLoading();
                view.showMessage(msg);
                view.showReload();
            }
        });
    }

    @Override
    public void cancelHomeRequest() {
        homeRepository.cancelGetHomeRequest();
    }
}
