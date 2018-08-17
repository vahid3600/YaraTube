package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vah on 8/17/2018.
 */

public class LoginGoogle {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("is_registered")
    @Expose
    private Object isRegistered;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("fino_token")
    @Expose
    private String finoToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Object isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFinoToken() {
        return finoToken;
    }

    public void setFinoToken(String finoToken) {
        this.finoToken = finoToken;
    }

}