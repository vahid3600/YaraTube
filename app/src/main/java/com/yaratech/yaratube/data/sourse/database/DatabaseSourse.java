package com.yaratech.yaratube.data.sourse.database;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.DBModel.Profile;
import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.ui.MenuActivity;

import static com.yaratech.yaratube.utils.Utils.USER_LOGIN_STATE_KEY;

/**
 * Created by Vah on 8/24/2018.
 */

public class DatabaseSourse implements DataSource.DatabaseSourse {
    AppDatabase appDatabase;

    public DatabaseSourse(Context context) {

        appDatabase = AppDatabase.getAppDatabase(context);
    }

    @Override
    public void getMobile(final DataSource.DatabaseSourse.GetMobileCallback callback) {
        callback.loadMobileCallback(appDatabase.profileDao().getMobile()
                .get(appDatabase.profileDao().getMobile().size() - 1));
    }

    @Override
    public void saveProfile(final Profile profile) {
        appDatabase.profileDao().insertProfile(profile);
    }

    @Override
    public void updateProfile(final Profile profile) {
        appDatabase.profileDao().updateProfile(profile);
    }

    @Override
    public void saveUserLoginState(int state) {
        MenuActivity.USER_LOGIN_STATE.edit()
                .putInt(USER_LOGIN_STATE_KEY, state)
                .apply();
    }

    @Override
    public int getUserLoginState() {
        return MenuActivity.USER_LOGIN_STATE.getInt(USER_LOGIN_STATE_KEY, 1);
    }
}
