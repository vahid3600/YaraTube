package com.yaratech.yaratube.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yaratech.yaratube.dagger.component.DaggerGetServices;
import com.yaratech.yaratube.dagger.component.GetServices;
import com.yaratech.yaratube.dagger.module.RetrofitModule;

/**
 * Created by Vah on 8/6/2018.
 */

public class Util {
    public static final String BASE_URL = "https://api.vasapi.click/";

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
}
