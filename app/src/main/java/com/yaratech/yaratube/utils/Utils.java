package com.yaratech.yaratube.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.dagger.component.DaggerGetServices;
import com.yaratech.yaratube.dagger.component.GetServices;
import com.yaratech.yaratube.dagger.module.RetrofitModule;

import static com.yaratech.yaratube.ui.home.HomeFragment.BASE_FRAGMENT_TAG;

/**
 * Created by Vah on 8/6/2018.
 */

public class Utils {

    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final int STORE_ID = 16;
    public static final String LOGIN_KEY = "USER_LOGIN";
    public static final String USER_LOGIN_STATE_KEY = "USER_LOGIN_STATE";
    public static final String USER_MOBILE_KEY = "USER_MOBILE";
    public static final String USER_AUTHORIZATION_KEY = "USER_AUTHORIZATION";
    public static final int PRODUCT_LIST_LIMIT = 10;
    public static final int COMMENT_LIMIT = 5;

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static GetServices getServices() {
        GetServices getServices = DaggerGetServices
                .builder()
                .retrofitModule(new RetrofitModule())
                .build();

        return getServices;
    }

    public static void addFragment(int container, FragmentManager fragmentManager, Fragment fragment, String tag, boolean addToBackStack) {
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(container, fragment, tag);
            fragmentTransaction.attach(fragment);
            if (addToBackStack)
                fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }
    }

    public static void replaceFragment(int container, FragmentManager fragmentManager, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment, tag);
        fragmentTransaction.attach(fragment);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public static void removeFragment(FragmentManager fragmentManager, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentByTag(tag));
        fragmentTransaction.commit();
    }


    public static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_STORAGE_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceOS() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }
}
