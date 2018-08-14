package com.yaratech.yaratube.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

/**
 * Created by Vah on 8/12/2018.
 */

public class LoginDialog extends DialogFragment implements View.OnClickListener {

    LinearLayout loginByPhone, loginByGoogle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginByPhone = view.findViewById(R.id.phonenumber);
        loginByGoogle = view.findViewById(R.id.google);

        loginByPhone.setOnClickListener(this);
        loginByGoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phonenumber:
                MenuActivity.USER_LOGIN.edit().putBoolean("USER_LOGIN", true).apply();
                break;
            case R.id.google:

                break;
        }


    }
}
