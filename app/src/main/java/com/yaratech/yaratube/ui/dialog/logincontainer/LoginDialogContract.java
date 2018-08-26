package com.yaratech.yaratube.ui.dialog.logincontainer;

/**
 * Created by Vah on 8/25/2018.
 */

public interface LoginDialogContract {
    interface View{
        void showLoginDialog();

        void showLoginPhone();

        void showVerification();
    }

    interface Presenter{

        void setUserStateLogin(int state);

        void checkUserStateLogin();
    }
}
