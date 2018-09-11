package com.yaratech.yaratube.ui.login.loginmethod;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.yaratech.yaratube.data.model.LoginGoogle;

/**
 * Created by Vah on 8/17/2018.
 */

public interface SelectLoginMethodContract {
    interface View {

        void showLoginByPhoneDialog();

        void onSuccessGoogleLoginResponseLoaded(LoginGoogle loginGoogle);

        void showMessage(String msg);
    }

    interface Presenter {

        void loginByGoogle(String tokenId, String deviceId, String deviceOs,
                           String deviceModel);

        void onSuccessGoogleLogin();
    }
}
