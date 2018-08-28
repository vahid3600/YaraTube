package com.yaratech.yaratube.data.sourse.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.yaratech.yaratube.data.model.DBModel.Profile;

/**
 * Created by Vah on 8/23/2018.
 */

@Database(entities = {Profile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private final static String DATABASE_NAME = "profile_db.db";
    private static AppDatabase appDatabase;
    public abstract ProfileDao profileDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
}
