package com.yaratech.yaratube.data.sourse;


import com.yaratech.yaratube.data.model.dbmodel.Profile;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

public class Repository implements
        DataSource.RemoteDataSourse,
        DataSource.DatabaseSourse,
        DataSource.PreferencesSource {

    private static Repository INSTANCE = null;
    private RemoteDataSource remoteDataSource;
    private DatabaseSourse databaseSourse;
    private PreferencesSourse preferencesSourse;

    private Repository(DataSource.RemoteDataSourse remoteDataSource,
                       DataSource.DatabaseSourse databaseSourse,
                       DataSource.PreferencesSource preferencesSource) {

        if (remoteDataSource instanceof RemoteDataSource)
            this.remoteDataSource = (RemoteDataSource) remoteDataSource;
        if (databaseSourse instanceof DatabaseSourse)
            this.databaseSourse = (DatabaseSourse) databaseSourse;
        if (preferencesSource instanceof PreferencesSourse)
            this.preferencesSourse = (PreferencesSourse) preferencesSource;
    }

    public static Repository getINSTANCE(DataSource.RemoteDataSourse remoteDataSource,
                                         DataSource.DatabaseSourse databaseSourse,
                                         DataSource.PreferencesSource preferencesSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(
                    remoteDataSource,
                    databaseSourse,
                    preferencesSource);
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
    public void getProductList(int id, int offset, LoadDataCallback callback) {
        remoteDataSource.getProductList(id, offset, callback);
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
    public void sendGoogleLogin(
            String tokenId,
            String deviceId,
            String deviceOs,
            String deviceModel,
            LoadDataCallback callback) {
        remoteDataSource.sendGoogleLogin(
                tokenId,
                deviceId,
                deviceOs,
                deviceModel,
                callback);
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
    public void sendComment(int productId, String authorization, float rate, String commentText, LoadDataCallback callback) {
        remoteDataSource.sendComment(productId, authorization, rate, commentText, callback);
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
        preferencesSourse.saveUserLoginState(state);
    }

    @Override
    public boolean getUserLoginStatus() {
        return preferencesSourse.getUserLoginStatus();
    }

    @Override
    public void saveUserLoginStatus(boolean status) {
        preferencesSourse.saveUserLoginStatus(status);
    }

    @Override
    public int getUserLoginState() {
        return preferencesSourse.getUserLoginState();
    }

    @Override
    public void saveUserMobile(String mobile) {
        preferencesSourse.saveUserMobile(mobile);
    }

    @Override
    public String getUserMobile() {
        return preferencesSourse.getUserMobile();
    }

    @Override
    public void saveUserAuthorization(String authorization) {
        preferencesSourse.saveUserAuthorization(authorization);
    }

    @Override
    public String getUserAuthorization() {
        return preferencesSourse.getUserAuthorization();
    }
}
