package com.yaratech.yaratube.ui.dialog.verification;

/**
 * Created by Vah on 8/17/2018.
 */

public interface VerificationContract {
    interface View{
        void dismissDialog();

        void showMessage(String msg);
    }

    interface Presenter{
        void sendVerificationCode(String deviceId, String verificationCode,
                                  String nickname);
    }
}
