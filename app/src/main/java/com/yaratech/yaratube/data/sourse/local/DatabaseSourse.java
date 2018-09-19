package com.yaratech.yaratube.data.sourse.local;

import android.content.Context;

import com.yaratech.yaratube.data.model.dbmodel.Profile;
import com.yaratech.yaratube.data.sourse.DataSource;

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

    @Override
    public Profile getProfile() {
        return appDatabase.profileDao().getProfileData();
    }

    @Override
    public void deleteProfile() {
        appDatabase.profileDao().deleteProfile();
    }

}
