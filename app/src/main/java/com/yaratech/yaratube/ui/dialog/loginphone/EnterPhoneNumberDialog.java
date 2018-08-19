package com.yaratech.yaratube.ui.dialog.loginphone;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Vah on 8/12/2018.
 */

public class EnterPhoneNumberDialog extends DialogFragment implements
        PhoneNumberContract.View {

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
    DismissDialog dismissDialog;
    PhoneNumberContract.Presenter presenter;
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
        presenter = new PhoneNumberPresenter(getContext(), this);
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof DismissDialog)
            dismissDialog = (DismissDialog) context;
        else
            throw new ClassCastException("Not Instance OF DismissDialog");
        super.onAttach(context);
    }

    @Override
    public void dismissDialog() {
        ((EnterPhoneNumberDialog.DismissDialog) getContext()).dismissPhoneNumberDialog();
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

    public interface DismissDialog {
        void dismissPhoneNumberDialog();
    }
}
