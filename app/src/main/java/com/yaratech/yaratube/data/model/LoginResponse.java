package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vah on 8/24/2018.
 */

public class LoginResponse {

    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("fino_token")
    @Expose
    private String finoToken;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("files_added")
    @Expose
    private List<Object> filesAdded = null;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFinoToken() {
        return finoToken;
    }

    public void setFinoToken(String finoToken) {
        this.finoToken = finoToken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public List<Object> getFilesAdded() {
        return filesAdded;
    }

    public void setFilesAdded(List<Object> filesAdded) {
        this.filesAdded = filesAdded;
    }
}
