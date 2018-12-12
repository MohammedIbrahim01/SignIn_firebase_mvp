package com.abdelazim.x.signin_firebase_mvp.signin.model;

import com.abdelazim.x.signin_firebase_mvp.Utils.AuthManager;
import com.abdelazim.x.signin_firebase_mvp.repository.AppRepository;
import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;
import com.abdelazim.x.signin_firebase_mvp.signin.presenter.SignInPresenter;

public class SignInModel implements SignInContract.ModelCallbacks {

    private AppRepository repository;
    private AuthManager authManager;
    private SignInPresenter presenter;

    public SignInModel(SignInPresenter presenter) {
        repository = new AppRepository(this);
        authManager = new AuthManager(this);
        this.presenter = presenter;
    }

    public void signIn(String email, String password) {
        authManager.signIn(email, password);
    }

    public void register(String email, String password, String userName, String phoneNumber) {
        authManager.register(email, password, userName, phoneNumber);
    }

    public void saveDriverInfo(String driverId, String email, String password, String userName, String phoneNumber) {
        repository.saveDriverInfo(driverId, email, password, userName, phoneNumber);
    }

    @Override
    public void onSignInSuccess() {
        presenter.onSignInSuccess();
    }

    @Override
    public void onRegisterSuccess(String driverId, String email, String password, String userName, String phoneNumber) {
        presenter.onRegisterSuccess(driverId, email, password, userName, phoneNumber);
    }

    @Override
    public void onSignInFailure(String failureMessage) {
        presenter.onSignInFailure(failureMessage);
    }

    @Override
    public void onRegisterFailure(String failureMessage) {
        presenter.onRegisterFailure(failureMessage);
    }

    @Override
    public void onSaveDriverSuccess(String userName) {
        presenter.onSaveDriverSuccess(userName);
    }

    @Override
    public void onSaveDriverFailure() {
        presenter.onSaveDriverFailure();
    }
}
