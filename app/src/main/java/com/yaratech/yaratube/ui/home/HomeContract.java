package com.yaratech.yaratube.ui.home;

import com.yaratech.yaratube.data.model.Store;

/**
 * Created by Vah on 8/6/2018.
 */

public interface HomeContract {

    interface View{

        void showListHome(Store store);

        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter{

        void fetchHomeFromRemote();
    }
}
