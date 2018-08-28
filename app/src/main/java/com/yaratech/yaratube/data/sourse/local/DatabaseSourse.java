package com.yaratech.yaratube.data.sourse.local;

import android.content.Context;

import com.yaratech.yaratube.data.model.DBModel.Profile;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.ui.MenuActivity;

import static com.yaratech.yaratube.utils.Utils.LOGIN_KEY;
import static com.yaratech.yaratube.utils.Utils.USER_LOGIN_STATE_KEY;
import static com.yaratech.yaratube.utils.Utils.USER_MOBILE_KEY;

/**
 * Created by Vah on 8/24/2018.
 */

public class DatabaseSourse implements DataSource.DatabaseSourse {
    AppDatabase appDatabase;

    public DatabaseSourse(Context context) {
        appDatabase = AppDatabase.getAppDatabase(context);
    }

    @Override
    public void saveProfile(final Profile profile) {
        appDatabase.profileDao().insertProfile(profile);
    }

    @Override
    public void updateProfile(final Profile profile) {
        appDatabase.profileDao().updateProfile(profile);
    }

}
