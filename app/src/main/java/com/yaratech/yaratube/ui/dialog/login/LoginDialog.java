package com.yaratech.yaratube.ui.dialog.login;

import android.content.Context;
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
import com.yaratech.yaratube.ui.Connects;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Vah on 8/12/2018.
 */

public class LoginDialog extends DialogFragment implements LoginContract.View {
    @OnClick(R.id.phonenumber)
    public void LoginByMobile() {
        dismissDialog();
    }

    @OnClick(R.id.google)
    public void LoginByGoogle() {
        presenter.loginByGoogle(
                "",
                Utils.getDeviceId(getContext()),
                Utils.getDeviceOS(),
                Utils.getDeviceModel());
    }

    LinearLayout loginByGoogle;
    Unbinder unbinder;
    LoginContract.Presenter presenter;
    Connects.DismissDialog dismissDialog;
    public static final String LOGIN_DIALOG_TAG = "LoginDialog";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter = new LoginPresenter(getContext(), this);
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof MenuActivity)
            dismissDialog = (Connects.DismissDialog) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        dismissDialog = null;
        super.onDetach();
    }

    public static LoginDialog newInstance() {
        
        Bundle args = new Bundle();
        
        LoginDialog fragment = new LoginDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog.dismiss(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
