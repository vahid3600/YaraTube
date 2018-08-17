package com.yaratech.yaratube.ui.dialog.login;

/**
 * Created by Vah on 8/17/2018.
 */

public interface LoginContract {
    interface View{
        void dismissDialog();

        void showMessage(String msg);
    }

    interface Presenter{
        void loginByGoogle(String tokenId, String deviceId, String deviceOs,
                           String deviceModel);
    }
}
