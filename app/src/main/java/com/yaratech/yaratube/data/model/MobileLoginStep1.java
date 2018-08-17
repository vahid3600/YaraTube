package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vah on 8/17/2018.
 */

public class MobileLoginStep1 {

    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private int error;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

}