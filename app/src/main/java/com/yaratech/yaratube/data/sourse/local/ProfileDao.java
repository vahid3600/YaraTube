package com.yaratech.yaratube.data.sourse.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yaratech.yaratube.data.model.dbmodel.Profile;

/**
 * Created by Vah on 8/24/2018.
 */

@Dao
public interface ProfileDao {

    @Insert
    void insertProfile(Profile... profile);

    @Update
    void updateProfile(Profile profile);

    @Query("select count(*) " +
            "from profile")
    int getProfile();

    @Query("delete " +
            "from profile " +
            "where id=0")
    void deleteProfile();

    @Query("select * " +
            "from profile")
    Profile getProfileData();
}
