package com.yaratech.yaratube.ui.dialog.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.utils.Utils;

/**
 * Created by Vah on 8/12/2018.
 */

public class LoginDialog extends DialogFragment implements View.OnClickListener, LoginContract.View {

    LinearLayout loginByPhone, loginByGoogle;
    LoginContract.Presenter presenter;
    public static final String LOGIN_DIALOG_TAG = "LoginDialog";

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
        presenter = new LoginPresenter(getContext(), this);

        loginByPhone.setOnClickListener(this);
        loginByGoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phonenumber:
                dismissDialog();
                break;
            case R.id.google:
                presenter.loginByGoogle(
                        "",
                        Utils.getDeviceId(getContext()),
                        Utils.getDeviceOS(),
                        Utils.getDeviceModel());
                break;
        }


    }

    @Override
    public void dismissDialog() {
        ((LoginDialog.DismissDialog) getContext()).dismissLoginDialog();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public interface DismissDialog {
        void dismissLoginDialog();
    }
}
