package com.yaratech.yaratube.ui.dialog.loginphone;

/**
 * Created by Vah on 8/17/2018.
 */

public interface PhoneNumberContract {
    interface View{
        void dismissDialog();

        void showMessage(String msg);
    }

    interface Presenter{
        void loginByMobile(String mobile, String deviceId, String deviceModel,
                           String deviceOs, String gcm);
    }
}
