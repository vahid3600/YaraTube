package com.yaratech.yaratube.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.login.loginphone.LoginPhoneFragment;
import com.yaratech.yaratube.ui.login.loginmethod.SelectLoginMethodFragment;
import com.yaratech.yaratube.ui.login.verification.VerificationFragment;

import static com.yaratech.yaratube.ui.login.loginphone.LoginPhoneFragment.ENTER_PHONE_DIALOG_TAG;
import static com.yaratech.yaratube.ui.login.loginmethod.SelectLoginMethodFragment.LOGIN_DIALOG_TAG;
import static com.yaratech.yaratube.ui.login.verification.VerificationFragment.VERIFICATION_DIALOG_TAG;

/**
 * Created by Vah on 8/25/2018.
 */

public class LoginDialogContainer extends DialogFragment implements
        LoginDialogContract.View,
        DialogInteraction {

    private LoginDialogContract.Presenter presenter;
    public static String LOGIN_DIALOG_CONTAINER_TAG = "LoginDialogContainer";

    public LoginDialogContainer() {

    }

    public static void newInstance(FragmentManager fragmentManager) {

        Bundle args = new Bundle();
        LoginDialogContainer fragment = new LoginDialogContainer();
        fragment.setArguments(args);
        fragment.show(fragmentManager, LOGIN_DIALOG_CONTAINER_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_dialog_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new LoginDialogContainerPresenter(getContext(), this);
        presenter.checkUserStateLogin();
    }

    @Override
    public void showLoginDialog() {
        Log.e("showLoginDialog",getChildFragmentManager()+"");
        getChildFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.dialog_container,
                        SelectLoginMethodFragment.newInstance(),
                        LOGIN_DIALOG_TAG)
                .commit();
        presenter.setUserStateLogin(1);
    }

    @Override
    public void showLoginPhone() {
        Log.e("showLoginPhone",getChildFragmentManager()+"");
        getChildFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.dialog_container,
                        LoginPhoneFragment.newInstance(),
                        ENTER_PHONE_DIALOG_TAG)
                .commit();
        presenter.setUserStateLogin(2);

    }

    @Override
    public void showVerification(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.dialog_container,
                        fragment,
                        VERIFICATION_DIALOG_TAG)
                .commit();
        presenter.setUserStateLogin(3);
    }

    @Override
    public void showDialog(Fragment fragment) {
        if (fragment instanceof LoginPhoneFragment) {
            showLoginPhone();
        } else if (fragment instanceof VerificationFragment) {
            showVerification(fragment);
        }
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        Log.e("LoginDialogContainer ","onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e("LoginDialogContainer ","onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e("LoginDialogContainer ","onDetach");
        super.onDetach();
    }
}
