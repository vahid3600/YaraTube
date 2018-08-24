package com.yaratech.yaratube.data.sourse.database;

import android.content.Context;

import com.yaratech.yaratube.data.model.DBModel.Profile;
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
    public String getMobile() {
        final String[] mobile = new String[1];
                new Thread(new Runnable() {
            @Override
            public void run() {
                mobile[0] = appDatabase.profileDao().getMobile();
            }
        }).start();
        return mobile[0];
    }

    @Override
    public void setProfile(final Profile profile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.profileDao().insertProfile(profile);
            }
        }).start();
    }

    @Override
    public void updateProfile(final Profile profile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.profileDao().updateProfile(profile);
            }
        }).start();
    }
}
