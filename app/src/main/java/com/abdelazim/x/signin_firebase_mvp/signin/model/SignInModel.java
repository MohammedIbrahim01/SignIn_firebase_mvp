package com.abdelazim.x.signin_firebase_mvp.signin.model;

import com.abdelazim.x.signin_firebase_mvp.Utils.AuthManager;
import com.abdelazim.x.signin_firebase_mvp.repository.AppRepository;
import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;

public class SignInModel {

    private AppRepository repository;
    private AuthManager authManager;

    public SignInModel(SignInContract.PresenterCallbacks presenterCallbacks) {
        repository = new AppRepository(presenterCallbacks);
        authManager = new AuthManager(presenterCallbacks);
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

    public void getLocation() {

        repository.getLocation();
    }
}
