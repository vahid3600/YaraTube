package com.yaratech.yaratube.ui.home.more;

import android.content.Context;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.ProductDetail;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.data.sourse.Repository;
import com.yaratech.yaratube.data.sourse.local.DatabaseSourse;
import com.yaratech.yaratube.data.sourse.local.PreferencesSourse;
import com.yaratech.yaratube.data.sourse.remote.RemoteDataSource;
import com.yaratech.yaratube.ui.productdetail.ProductDetailContract;

import java.util.List;

/**
 * Created by Vah on 8/8/2018.
 */

public class MorePresenter implements MoreContract.Presenter {

    private Repository repository;

    public MorePresenter(Context context){
        this.repository = Repository.getINSTANCE(
                new RemoteDataSource(context),
                new DatabaseSourse(context),
                new PreferencesSourse(context));
    }

    @Override
    public boolean getUserLoginStatus() {
        return repository.getUserLoginStatus();
    }
}
