package com.yaratech.yaratube.ui.profile;

import android.net.Uri;

import com.yaratech.yaratube.data.model.GetProfileResponse;
import com.yaratech.yaratube.data.model.ProfileResponse;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.utils.PresenterInteractions;

import java.util.Date;

/**
 * Created by Vah on 8/6/2018.
 */

public interface ProfileContract {

    interface View {

        void updateImage(Uri uri);

        void onDataLoad(GetProfileResponse getProfileResponse);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends PresenterInteractions{

        void getProfileData(String authorization);

        String getUserAuthorization();

        void sendImage(String authorization, String path);

        void sendProfileData(String authorization,
                             String name,
                             String gender,
                             String birthday);
    }
}
