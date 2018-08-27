package com.yaratech.yaratube.data.model.DBModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Vah on 8/23/2018.
 */

@Entity(tableName = "profile")
public class Profile {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nickname")
    private String nickName;

    @ColumnInfo(name = "fino_token")
    private String finoToken;

    public String getFinoToken() {
        return finoToken;
    }

    public void setFinoToken(String finoToken) {
        this.finoToken = finoToken;
    }

    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    @ColumnInfo(name = "user_id")
    private double userId;

    @ColumnInfo(name = "date_of_birth")
    private String dateOfBirth;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "avatar")
    private String avatar;

    @ColumnInfo(name = "mobile")
    private String mobile;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "is_submitted")
    private boolean isSubmited;

    @ColumnInfo(name = "user_token")
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubmited() {
        return isSubmited;
    }

    public void setSubmited(boolean submited) {
        isSubmited = submited;
    }
}
