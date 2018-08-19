package com.yaratech.yaratube.ui.dialog.loginphone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.utils.Utils;

/**
 * Created by Vah on 8/12/2018.
 */

public class EnterPhoneNumberDialog extends DialogFragment implements View.OnClickListener,
        PhoneNumberContract.View {

    LinearLayout save;
    EditText phoneNumber;
    PhoneNumberContract.Presenter presenter;
    public static final String ENTER_PHONE_DIALOG_TAG = "EnterPhone";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_enter_phone_number, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        save = view.findViewById(R.id.save);
        phoneNumber = view.findViewById(R.id.phonenumber);
        presenter = new PhoneNumberPresenter(getContext(), this);


        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                presenter.loginByMobile(
                        phoneNumber.getText().toString(),
                        Utils.getDeviceId(getContext()),
                        Utils.getDeviceModel(),
                        Utils.getDeviceOS(),
                        "");
//                MenuActivity.USER_LOGIN.edit().putBoolean("USER_LOGIN", true).apply();
                break;
        }
    }

    @Override
    public void dismissDialog() {
        ((EnterPhoneNumberDialog.DismissDialog)getContext()).dismissPhoneNumberDialog();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        dismissDialog();
    }

    public interface DismissDialog {
        void dismissPhoneNumberDialog();
    }
}