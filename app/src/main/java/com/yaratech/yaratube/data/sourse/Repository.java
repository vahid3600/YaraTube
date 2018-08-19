package com.yaratech.yaratube.data.sourse;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.yaratech.yaratube.data.sourse.remote.DataSource;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

import java.io.File;

public class Repository implements DataSource {

    GetImage getImage;
    private static Repository INSTANCE = null;
    private RemoteDataSource remoteDataSource;

    private Repository(DataSource remoteDataSource) {
        //no instance
        if (remoteDataSource instanceof RemoteDataSource) {
            this.remoteDataSource = (RemoteDataSource) remoteDataSource;
        }
        this.getImage = getImage;
    }

    public static Repository getINSTANCE(DataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource);
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
    public void sendMobileLoginStep1(String mobile, String deviceId, String deviceModel,
                                     String deviceOs, String gcm, LoadDataCallback callback) {
        remoteDataSource.sendMobileLoginStep1(mobile, deviceId, deviceModel, deviceOs,
                gcm, callback);
    }

    @Override
    public void sendMobileLoginStep2(String mobile, String deviceId, String verificationCode,
                                     String nickname, LoadDataCallback callback) {
        remoteDataSource.sendMobileLoginStep2(mobile, deviceId, verificationCode, nickname,
                callback);
    }

    public void getImageFromGalery() {

    }

    public void getImageFromCamera() {

    }

    public interface GetImage {
        void onCamera();

        void onGalery();
    }
}
