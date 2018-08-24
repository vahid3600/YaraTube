package com.yaratech.yaratube.ui.dialog.verification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.Connects;
import com.yaratech.yaratube.ui.dialog.loginphone.PhoneNumberContract;
import com.yaratech.yaratube.ui.dialog.loginphone.PhoneNumberPresenter;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Vah on 8/12/2018.
 */

public class VerificationDialog extends DialogFragment implements
        VerificationContract.View {

    Unbinder unbind;
    Connects.DismissDialog dismissDialog;
    VerificationContract.Presenter presenter;
    public static final String VERIFICATION_DIALOG_TAG = "Verification";

    @BindView(R.id.verification_code)
    EditText verificationCode;

    @OnClick(R.id.enter)
    public void sendVerificationCode() {
        presenter.sendVerificationCode(
                Utils.getDeviceId(getContext()),
                verificationCode.getText().toString(),
                "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_verification, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbind = ButterKnife.bind(this, view);
        presenter = new VerificationNumberPresenter(getContext(), this);
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof Connects.DismissDialog)
            dismissDialog = (Connects.DismissDialog) context;
        else
            throw new ClassCastException("Not Instance OF DismissDialog");
        super.onAttach(context);
    }

    public static VerificationDialog newInstance() {

        Bundle args = new Bundle();

        VerificationDialog fragment = new VerificationDialog();
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
        dismissDialog();
    }

    @Override
    public void onDestroyView() {
        unbind.unbind();
        super.onDestroyView();
    }
}