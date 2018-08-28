package com.yaratech.yaratube.ui.comment;

import android.content.Context;

import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;

/**
 * Created by Vah on 8/28/2018.
 */

public class CommentPresenter implements CommentContract.Presenter {

    CommentContract.View view;
    Repository repository;

    public CommentPresenter(Context context, CommentContract.View view) {
        this.view = view;
        repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }

    @Override
    public String getUserAuthorization() {
        return repository.getUserAuthorization();
    }

    @Override
    public void sendComment(int productId, String authorization, float rate, String comment) {
        repository.sendComment(productId, authorization, rate, comment, new DataSource.RemoteDataSourse.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object result) {
                view.closeDialog();
            }

            @Override
            public void onMessage(String msg) {
                view.showMessage(msg);
            }
        });
    }
}
