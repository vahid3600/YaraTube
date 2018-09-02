package com.yaratech.yaratube.data.sourse;

import com.yaratech.yaratube.data.model.dbmodel.Profile;

/**
 * Created by Vah on 8/8/2018.
 */

public interface DataSource {

    interface RemoteDataSourse {

        interface LoadDataCallback<T> {
            void onDataLoaded(T result);

            void onMessage(String msg);
        }

        void getHome(LoadDataCallback callback);

        void cancelGetHomeRequest();

        void getCategory(LoadDataCallback callback);

        void cancelGetCategoryRequest();

        void getProductList(int id, int offset, LoadDataCallback callback);

        void cancelGetProductListRequest();

        void getProductDetail(int id, LoadDataCallback callback);

        void cancelGetProductDetailRequest();

        void getComment(int id, LoadDataCallback callback);

        void cancelGetCommentRequest();

        void sendGoogleLogin(String tokenId, String deviceId, String deviceOs,
                             String deviceModel, LoadDataCallback callback);

        void sendMobileLoginStep1(String mobile, String deviceId, String deviceModel,
                                  String deviceOs, String gcm, LoadDataCallback callback,
                                  DatabaseSourse.AddToDatabase addToDatabase);

        void sendMobileLoginStep2(String mobile, String deviceId, String verificationCode,
                                  String nickname, LoadDataCallback callback,
                                  DatabaseSourse.AddToDatabase addToDatabase);

        void sendComment(int productId, String authorization, float rate, String commentText, LoadDataCallback callback);

    }

    interface DatabaseSourse{

        interface AddToDatabase{
            void saveProfile(Profile profile);

            void updateProfile(Profile profile);
        }

        void saveProfile(Profile profile);

        void updateProfile(Profile profile);
    }

    interface PreferencesSource{

        void saveUserLoginState(int state);

        boolean getUserLoginStatus();

        void saveUserLoginStatus(boolean status);

        int getUserLoginState();

        void saveUserMobile(String mobile);

        String getUserMobile();

        void saveUserAuthorization(String authorization);

        String getUserAuthorization();
    }
}
