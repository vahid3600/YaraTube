package com.yaratech.yaratube.data.sourse.remote;

/**
 * Created by Vah on 8/8/2018.
 */

public interface DataSource {

    interface LoadDataCallback<T> {
        void onDataLoaded(T result);

        void onMessage(String msg);
    }

    void getHome(LoadDataCallback callback);

    void getCategory(LoadDataCallback callback);

    void getProductList(int id, LoadDataCallback callback);

    void getProductDetail(int id, LoadDataCallback callback);

    void getComment(int id, LoadDataCallback callback);

    void sendGoogleLogin(String tokenId, String deviceId, String deviceOs,
                         String deviceModel, LoadDataCallback callback);

    void sendMobileLoginStep1(String mobile, String deviceId, String deviceModel,
                              String deviceOs, String gcm, LoadDataCallback callback);

    void sendMobileLoginStep2(String mobile, String deviceId, String verificationCode,
                         String nickname, LoadDataCallback callback);
}
