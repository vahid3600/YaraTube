package com.yaratech.yaratube.ui.login.loginmethod;

/**
 * Created by Vah on 8/17/2018.
 */

public interface SelectLoginMethodContract {
    interface View {

        void showLoginByPhoneDialog();

        void showMessage(String msg);
    }

    interface Presenter {

        void loginByGoogle(String tokenId, String deviceId, String deviceOs,
                           String deviceModel);
    }
}
