package com.yaratech.yaratube.data.sourse;



import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

public class Repository implements DataSource {

    private static Repository INSTANCE = null;
    private RemoteDataSource remoteDataSource;

    private Repository(DataSource remoteDataSource) {
        //no instance
        if (remoteDataSource instanceof RemoteDataSource) {
            this.remoteDataSource = (RemoteDataSource) remoteDataSource;
        } else {
        }

    }

    public static Repository getINSTANCE(DataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getHome(LoadStoreCallback callback) {
        remoteDataSource.getHome(callback);
    }

    @Override
    public void getCategory(LoadCatetoryCallback callback) {
        remoteDataSource.getCategory(callback);
    }
}
