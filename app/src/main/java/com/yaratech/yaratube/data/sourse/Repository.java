package com.yaratech.yaratube.data.sourse;


import com.yaratech.yaratube.data.model.DBModel.Profile;
import com.yaratech.yaratube.data.sourse.database.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

public class Repository implements DataSource.RemoteDataSourse,
        DataSource.DatabaseSourse {

    private static Repository INSTANCE = null;
    private RemoteDataSource remoteDataSource;
    private DatabaseSourse databaseSourse;

    private Repository(DataSource.RemoteDataSourse remoteDataSource,
                       DataSource.DatabaseSourse databaseSourse) {
        //no instance
        if (remoteDataSource instanceof RemoteDataSource
                && databaseSourse instanceof DatabaseSourse)
            this.remoteDataSource = (RemoteDataSource) remoteDataSource;
        this.databaseSourse = (DatabaseSourse) databaseSourse;
    }

    public static Repository getINSTANCE(DataSource.RemoteDataSourse remoteDataSource,
                                         DataSource.DatabaseSourse databaseSourse) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource, databaseSourse);
        }
        return INSTANCE;
    }

    @Override
    public void getHome(LoadDataCallback callback) {
        remoteDataSource.getHome(callback);
    }

    @Override
    public void cancelGetHomeRequest() {
        remoteDataSource.cancelGetHomeRequest();
    }

    @Override
    public void getCategory(LoadDataCallback callback) {
        remoteDataSource.getCategory(callback);
    }

    @Override
    public void cancelGetCategoryRequest() {
        remoteDataSource.cancelGetCategoryRequest();
    }

    @Override
    public void getProductList(int id, LoadDataCallback callback) {
        remoteDataSource.getProductList(id, callback);
    }

    @Override
    public void cancelGetProductListRequest() {
        remoteDataSource.cancelGetProductListRequest();
    }

    @Override
    public void getProductDetail(int id, LoadDataCallback callback) {
        remoteDataSource.getProductDetail(id, callback);
    }

    @Override
    public void cancelGetProductDetailRequest() {
        remoteDataSource.cancelGetProductDetailRequest();
    }

    @Override
    public void getComment(int id, LoadDataCallback callback) {
        remoteDataSource.getComment(id, callback);
    }

    @Override
    public void cancelGetCommentRequest() {
        remoteDataSource.cancelGetCommentRequest();
    }

    @Override
    public void sendGoogleLogin(String tokenId, String deviceId, String deviceOs,
                                String deviceModel, LoadDataCallback callback) {
        remoteDataSource.sendGoogleLogin(tokenId, deviceId, deviceOs, deviceModel, callback);
    }

    @Override
    public void sendMobileLoginStep1(
            String mobile,
            String deviceId,
            String deviceModel,
            String deviceOs,
            String gcm,
            LoadDataCallback callback,
            AddToDatabase addToDatabase) {
        remoteDataSource.sendMobileLoginStep1(
                mobile,
                deviceId,
                deviceModel,
                deviceOs,
                gcm,
                callback,
                addToDatabase);
    }

    @Override
    public void sendMobileLoginStep2(
            String mobile,
            String deviceId,
            String verificationCode,
            String nickname,
            LoadDataCallback callback,
            AddToDatabase addToDatabase) {
        remoteDataSource.sendMobileLoginStep2(
                mobile,
                deviceId,
                verificationCode,
                nickname,
                callback,
                addToDatabase);
    }

    @Override
    public void saveProfile(Profile profile) {
        databaseSourse.saveProfile(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        databaseSourse.updateProfile(profile);
    }

    @Override
    public void saveUserLoginState(int state) {
        databaseSourse.saveUserLoginState(state);
    }

    @Override
    public boolean getUserLoginStatus() {
        return databaseSourse.getUserLoginStatus();
    }

    @Override
    public void saveUserLoginStatus(boolean status) {
        databaseSourse.saveUserLoginStatus(status);
    }

    @Override
    public int getUserLoginState() {
        return databaseSourse.getUserLoginState();
    }

    @Override
    public void saveUserMobile(String mobile) {
        databaseSourse.saveUserMobile(mobile);
    }

    @Override
    public String getUserMobile() {
        return databaseSourse.getUserMobile();
    }
}
