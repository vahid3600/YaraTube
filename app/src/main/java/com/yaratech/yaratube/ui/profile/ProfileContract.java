package com.yaratech.yaratube.ui.profile;

import android.net.Uri;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.ui.MenuActivity;

/**
 * Created by Vah on 8/6/2018.
 */

public interface ProfileContract {

    interface View{

        void updateImage(Uri uri);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter{

        void fetchProfileGalery();

        void fetchProfileCamera();

        void sendProfileData();
    }
}
