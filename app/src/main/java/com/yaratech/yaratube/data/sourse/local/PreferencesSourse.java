package com.yaratech.yaratube.data.sourse.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.yaratech.yaratube.data.sourse.DataSource;
import com.yaratech.yaratube.ui.MenuActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.yaratech.yaratube.utils.Utils.LOGIN_KEY;
import static com.yaratech.yaratube.utils.Utils.USER_LOGIN_STATE_KEY;
import static com.yaratech.yaratube.utils.Utils.USER_MOBILE_KEY;

/**
 * Created by Vah on 8/28/2018.
 */

public class PreferencesSourse implements DataSource.PreferencesSource {
    public static SharedPreferences USER_LOGIN;
    public static SharedPreferences USER_LOGIN_STATE;
    public static SharedPreferences USER_MOBILE;

    public PreferencesSourse(Context context) {
        USER_LOGIN = context.getSharedPreferences(LOGIN_KEY, MODE_PRIVATE);
        USER_LOGIN_STATE = context.getSharedPreferences(USER_LOGIN_STATE_KEY, MODE_PRIVATE);
        USER_MOBILE = context.getSharedPreferences(USER_MOBILE_KEY, MODE_PRIVATE);
    }

    @Override
    public void saveUserLoginState(int state) {
        USER_LOGIN_STATE.edit()
                .putInt(USER_LOGIN_STATE_KEY, state)
                .apply();
    }

    @Override
    public boolean getUserLoginStatus() {
        return USER_LOGIN.getBoolean(LOGIN_KEY, false);
    }

    @Override
    public void saveUserLoginStatus(boolean status) {
        USER_LOGIN.edit()
                .putBoolean(LOGIN_KEY, true)
                .apply();
    }

    @Override
    public int getUserLoginState() {
        return USER_LOGIN_STATE.getInt(USER_LOGIN_STATE_KEY, 1);
    }

    @Override
    public void saveUserMobile(String mobile) {
        USER_MOBILE.edit()
                .putString(USER_MOBILE_KEY, mobile)
                .apply();
    }

    @Override
    public String getUserMobile() {
        return USER_MOBILE.getString(USER_MOBILE_KEY, "");
    }


}
