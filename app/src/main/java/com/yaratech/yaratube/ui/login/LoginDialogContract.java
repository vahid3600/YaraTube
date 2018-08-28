package com.yaratech.yaratube.ui.login;

import android.support.v4.app.Fragment;

/**
 * Created by Vah on 8/25/2018.
 */

public interface LoginDialogContract {
    interface View{
        void showLoginDialog();

        void showLoginPhone();

        void showVerification(Fragment fragment);
    }

    interface Presenter{

        void setUserStateLogin(int state);

        void checkUserStateLogin();
    }
}
