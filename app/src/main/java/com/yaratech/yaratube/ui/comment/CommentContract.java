package com.yaratech.yaratube.ui.comment;

/**
 * Created by Vah on 8/28/2018.
 */

public interface CommentContract {

    interface View{
        void closeDialog();

        void showMessage(String msg);
    }

    interface Presenter{

        String getUserAuthorization();

        void sendComment(int productId, String authorization, float rate, String comment);
    }
}
