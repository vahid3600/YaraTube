package com.yaratech.yaratube.ui.profile;

import android.net.Uri;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.ui.MenuActivity;

import java.util.Date;

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

        String getUserAuthorization();

        void sendImage(String authorization, String path);

        void sendProfileData(String name,
                             String gender,
                             Date birthday);
    }
}
