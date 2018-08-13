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

    public void getImageFromGalery(Activity activity, DataSource.LoadImageGaleryCallback callback) {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(context.ac, Manifest.permission.CAMERA))
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        activity.startActivityForResult(Intent.createChooser(intent, "Select File"));

    }

    public void getImageFromCamera(MenuActivity menuActivity) {


        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        Uri imageUri = Uri.fromFile(photo);
        menuActivity.startActivityForResult(intent, 100);
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
//                    1);
//        }
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File file = new File(Environment.getExternalStorageDirectory(),
//                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
//        Uri uri = Uri.fromFile(file);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        intent.putExtra("return-data", true);
//        activity.startActivityForResult(intent, 0);
    }
}
