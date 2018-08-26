package com.yaratech.yaratube.ui.dialog.logincontainer.selectlogin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.dialog.logincontainer.DialogInteraction;
import com.yaratech.yaratube.ui.dialog.logincontainer.loginphone.LoginPhone;
import com.yaratech.yaratube.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Vah on 8/12/2018.
 */

public class SelectLoginMethod extends Fragment implements SelectLoginMethodContract.View {
    @OnClick(R.id.phonenumber)
    public void LoginByMobile() {
        showLoginByPhoneDialog();
    }

    @OnClick(R.id.google)
    public void LoginByGoogle() {
        presenter.loginByGoogle(
                "",
                Utils.getDeviceId(getContext()),
                Utils.getDeviceOS(),
                Utils.getDeviceModel());
    }

    Unbinder unbinder;
    SelectLoginMethodContract.Presenter presenter;
    DialogInteraction dialogInteraction;
    public static final String LOGIN_DIALOG_TAG = "SelectLoginMethod";

    @Override
    public void onAttach(Context context) {
        Log.e("SelectLoginMethod ","onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("SelectLoginMethod ","onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        Log.e("SelectLoginMethod ","onCreateView");
        return inflater.inflate(R.layout.dialog_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e("SelectLoginMethod ","onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter = new SelectLoginMethodPresenter(getContext(), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("SelectLoginMethod ","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        dialogInteraction = (DialogInteraction) getParentFragment();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.e("SelectLoginMethod ","onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.e("SelectLoginMethod ","onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("SelectLoginMethod ","onResume");
        super.onResume();
    }

    public static SelectLoginMethod newInstance() {

        Bundle args = new Bundle();
        SelectLoginMethod fragment = new SelectLoginMethod();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoginByPhoneDialog() {
        dialogInteraction.showDialog(LoginPhone.newInstance());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        Log.e("SelectLoginMethod ","onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e("SelectLoginMethod ","onDestroyView");
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e("SelectLoginMethod ","onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e("SelectLoginMethod ","onDestroy");
        dialogInteraction = null;
        super.onDetach();
    }

}
