package com.yaratech.yaratube.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

import com.yaratech.yaratube.dagger.component.DaggerGetServices;
import com.yaratech.yaratube.dagger.component.GetServices;
import com.yaratech.yaratube.dagger.module.RetrofitModule;

/**
 * Created by Vah on 8/6/2018.
 */

public class Util {
    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final int STORE_ID = 16;

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static GetServices getServices(){
        GetServices getServices = DaggerGetServices
                .builder()
                .retrofitModule(new RetrofitModule())
                .build();

        return getServices;
    }

    public static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_STORAGE_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceOS(){
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceModel(){
        return Build.MODEL;
    }
}
