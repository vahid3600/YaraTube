package com.yaratech.yaratube.ui.login.verification;

/**
 * Created by Vah on 8/17/2018.
 */

public interface VerificationContract {
    interface View{
        void closeDialog();

        void showMessage(String msg);

        void showProgressbar();

        void hideProgressbar();
    }

    interface Presenter{
        void sendVerificationCode(String mobile, String deviceId, String verificationCode,
                                  String nickname);
        void setUserLoginStatus(boolean status);

        void saveUserMobile(String mobile);

        String getUserMobile();
    }
}
