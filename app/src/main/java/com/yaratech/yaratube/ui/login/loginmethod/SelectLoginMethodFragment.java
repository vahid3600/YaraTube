package com.yaratech.yaratube.ui.login.loginmethod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.tasks.Task;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.LoginGoogle;
import com.yaratech.yaratube.ui.login.DialogInteraction;
import com.yaratech.yaratube.ui.login.loginphone.LoginPhone;
import com.yaratech.yaratube.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.android.exoplayer2.ExoPlayerLibraryInfo.TAG;

/**
 * Created by Vah on 8/12/2018.
 */

public class SelectLoginMethodFragment extends Fragment implements
        SelectLoginMethodContract.View,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    public static final String LOGIN_DIALOG_TAG = "SelectLoginMethodFragment";
    private static final int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private Unbinder unbinder;
    private SelectLoginMethodContract.Presenter presenter;
    private DialogInteraction dialogInteraction;

    @OnClick(R.id.phonenumber)
    public void LoginByMobile() {
        showLoginByPhoneDialog();
    }

    @OnClick(R.id.google)
    public void LoginByGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter = new SelectLoginMethodPresenter(getContext(), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dialogInteraction = (DialogInteraction) getParentFragment();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(),
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statusCode = result.getStatus().getStatusCode();
            String statusMessage = result.getStatus().getStatusMessage();
            Log.e("result status code", "onActivityResult: " + statusCode + " " + statusMessage);

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            account.getServerAuthCode();
            presenter.loginByGoogle(
                    account.getIdToken(),
                    Utils.getDeviceId(getContext()),
                    Utils.getDeviceOS(),
                    Utils.getDeviceModel());
            presenter.onSuccessGoogleLogin();
            Log.e("Tag ", account.getServerAuthCode() + " " + account.getId() + " " + account.getIdToken());
        } catch (ApiException e) {
            e.printStackTrace();
            Log.e("Tag", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public static SelectLoginMethodFragment newInstance() {

        Bundle args = new Bundle();
        SelectLoginMethodFragment fragment = new SelectLoginMethodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoginByPhoneDialog() {
        dialogInteraction.showDialog(LoginPhone.newInstance());
    }

    @Override
    public void onSuccessGoogleLoginResponseLoaded(LoginGoogle loginGoogle) {
        Log.e("Tag",loginGoogle.getToken());
        dialogInteraction.closeDialog();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        dialogInteraction = null;
        super.onDetach();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showMessage(getString(R.string.no_internet));
    }
}
