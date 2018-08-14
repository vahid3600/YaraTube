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
    public void getHome(LoadStoreCallback callback) {
        remoteDataSource.getHome(callback);
    }

    @Override
    public void getCategory(LoadCatetoryCallback callback) {
        remoteDataSource.getCategory(callback);
    }

    @Override
    public void getProductList(int id, LoadProductListCallback callback) {
        remoteDataSource.getProductList(id, callback);
    }

    public void getImageFromGalery() {

    }

    public void getImageFromCamera() {

    }

    public interface GetImage{
        void onCamera();

        void onGalery();
    }
}
