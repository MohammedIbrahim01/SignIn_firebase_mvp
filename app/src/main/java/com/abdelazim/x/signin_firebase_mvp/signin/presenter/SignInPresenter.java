package com.abdelazim.x.signin_firebase_mvp.signin.presenter;

import android.content.DialogInterface;

import com.abdelazim.x.signin_firebase_mvp.Utils.TextUtils;
import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;
import com.abdelazim.x.signin_firebase_mvp.signin.model.SignInModel;

public class SignInPresenter implements SignInContract.presenter, SignInContract.AuthenticationCallbacks, SignInContract.RepositoryCallbacks {

    private SignInModel model;
    private SignInContract.View view;

    public SignInPresenter(SignInContract.View view) {

        model = new SignInModel(this);
        this.view = view;
    }

    @Override
    public void signInClicked() {
        view.showSignInDialog();
    }

    @Override
    public void registerClicked() {
        view.showRegisterDialog();
    }

    @Override
    public void dialogSignInClicked(DialogInterface dialog, String email, String password) {

        if (!TextUtils.isValidUserEmail(email)) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid email");
            return;
        }
        if (!TextUtils.isValidPassword(password)) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid password");
            return;
        }

        model.signIn(email, password);
        view.hideDialog(dialog);
    }

    @Override
    public void dialogRegisterClicked(DialogInterface dialog, String email, String password, String userName, String phoneNumber) {

        if (!TextUtils.isValidUserEmail(email)) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid email");
            return;
        }
        if (!TextUtils.isValidPassword(password)) {
            view.hideDialog(dialog);
            view.showSnackBar("invalid password");
            return;
        }

        model.register(email, password, userName, phoneNumber);
        view.hideDialog(dialog);
    }

    @Override
    public void dialogCancelClicked(DialogInterface dialog) {

        view.hideDialog(dialog);
    }

    @Override
    public void onSignInSuccess() {
        view.showToast("signed in");
        view.gotoMapActivity();
    }

    @Override
    public void onRegisterSuccess(String driverId, String email, String password, String userName, String phoneNumber) {
        model.saveDriverInfo(driverId, email, password, userName, phoneNumber);
    }

    @Override
    public void onSignInFailure(String failureMessage) {
        view.showSnackBar(failureMessage);
    }

    @Override
    public void onRegisterFailure(String failureMessage) {
        view.showSnackBar(failureMessage);
    }

    @Override
    public void onSaveDriverSuccess(String userName) {
        view.showToast("welcome, " + userName);
        view.gotoMapActivity();
    }

    @Override
    public void onSaveDriverFailure() {
        view.showSnackBar("check your internet connection");
    }
}
