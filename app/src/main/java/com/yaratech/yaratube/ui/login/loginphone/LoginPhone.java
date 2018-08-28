package com.yaratech.yaratube.ui.login.loginphone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.login.DialogInteraction;
import com.yaratech.yaratube.ui.login.verification.VerificationFragment;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Vah on 8/12/2018.
 */

public class LoginPhone extends Fragment implements
        LoginPhoneContract.View {

    @OnClick(R.id.save)
    public void sendPhoneNumberRequest() {
        presenter.loginByMobile(
                phoneNumber.getText().toString(),
                Utils.getDeviceId(getContext()),
                Utils.getDeviceModel(),
                Utils.getDeviceOS(),
                "");
    }

    @BindView(R.id.phonenumber)
    EditText phoneNumber;

    Unbinder unbind;
    DialogInteraction dialogInteraction;
    LoginPhoneContract.Presenter presenter;
    public static final String ENTER_PHONE_DIALOG_TAG = "EnterPhone";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_enter_phone_number, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbind = ButterKnife.bind(this, view);
        presenter = new LoginPhonePresenter(getContext(), this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialogInteraction = (DialogInteraction) getParentFragment();
    }

    @Override
    public void onDetach() {
        dialogInteraction = null;
        super.onDetach();
    }

    public static LoginPhone newInstance() {
        
        Bundle args = new Bundle();
        
        LoginPhone fragment = new LoginPhone();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showVerificationDialog() {

        dialogInteraction.showDialog(VerificationFragment.newInstance(phoneNumber.getText().toString()));
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        unbind.unbind();
        super.onDestroyView();
    }
}
