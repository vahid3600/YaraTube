package com.yaratech.yaratube.ui.login.loginphone;

/**
 * Created by Vah on 8/17/2018.
 */

public interface LoginPhoneContract {
    interface View{
        void showVerificationDialog();

        void showMessage(String msg);

        void showProgressbar();

        void hideProgressbar();
    }

    interface Presenter{
        void loginByMobile(String mobile, String deviceId, String deviceModel,
                           String deviceOs, String gcm);
    }
}
