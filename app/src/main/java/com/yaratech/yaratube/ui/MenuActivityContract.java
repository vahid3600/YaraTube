package com.yaratech.yaratube.ui;

/**
 * Created by Vah on 8/26/2018.
 */

public interface MenuActivityContract {

    interface Presenter{

        boolean getUserLoginStatus();

        void setUserLoginStatus(boolean status);
    }
}
