package com.yaratech.yaratube.ui.login.verification;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.BuildConfig;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.login.DialogInteraction;
import com.yaratech.yaratube.ui.login.loginphone.LoginPhone;
import com.yaratech.yaratube.utils.Permissions;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Vah on 8/12/2018.
 */

public class VerificationFragment extends Fragment implements
        VerificationContract.View {

    Unbinder unbind;
    DialogInteraction dialogInteraction;
    VerificationContract.Presenter presenter;
    SMSBroadcastReceiver smsBroadcastReceiver;
    SMSBroadcastReceiver.SMSBroadcastListener smsBroadcastListener;
    public static final String VERIFICATION_DIALOG_TAG = "Verification";

    @BindView(R.id.verification_code)
    EditText verificationCode;

    @OnClick(R.id.enter)
    public void sendVerificationCode() {
        presenter.sendVerificationCode(
                presenter.getUserMobile(),
                Utils.getDeviceId(getContext()),
                verificationCode.getText().toString(),
                "");
    }

    @OnClick(R.id.return_back)
    public void showEnterNumberDialog() {
        dialogInteraction.showDialog(LoginPhone.newInstance());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smsBroadcastListener = getSmsListener();
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
        presenter = new VerificationPresenter(getContext(), this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialogInteraction = (DialogInteraction) getParentFragment();
        if (getArguments() != null)
            presenter.saveUserMobile(getArguments().getString(VERIFICATION_DIALOG_TAG));

        if (!Permissions.checkSMSPermissions(getContext())) {
            requestReadAndSendSmsPermission();
        } else
            getMessageFromBroadcast();
    }

    private void getMessageFromBroadcast() {
        smsBroadcastReceiver = new SMSBroadcastReceiver();
        getContext().registerReceiver(smsBroadcastReceiver,
                new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        smsBroadcastReceiver.bindListener(smsBroadcastListener);
    }

    public static VerificationFragment newInstance(String mobileNumber) {

        Bundle args = new Bundle();
        if (mobileNumber != null) {
            args.putString(VERIFICATION_DIALOG_TAG, mobileNumber);
        }
        VerificationFragment fragment = new VerificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void closeDialog() {
        dialogInteraction.closeDialog();
        presenter.setUserLoginStatus(true);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        unbind.unbind();
        if (smsBroadcastReceiver != null)
            smsBroadcastReceiver.unbindListener();
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (smsBroadcastReceiver != null)
            getContext().unregisterReceiver(smsBroadcastReceiver);
    }

    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_SMS)) {
    }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    smsBroadcastReceiver.bindListener(smsBroadcastListener);
                    getMessageFromBroadcast();

                } else {
                    Log.e("permission", " denied");
                }
                return;
            }
        }
    }

    public SMSBroadcastReceiver.SMSBroadcastListener getSmsListener() {
        return new SMSBroadcastReceiver.SMSBroadcastListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextReceived(String text) {
                verificationCode.setText(Integer.toString(Integer.parseInt(text)));
            }
        };

    }
}
