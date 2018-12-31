package com.abdelazim.x.signin_firebase_mvp.repository;

import com.abdelazim.x.signin_firebase_mvp.repository.remote.RemoteDatabase;
import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;

public class AppRepository {

    private RemoteDatabase remoteDatabase;

    public AppRepository(SignInContract.PresenterCallbacks presenterCallbacks) {

        remoteDatabase = new RemoteDatabase(presenterCallbacks);
    }

    public void saveDriverInfo(String driverId, String email, String password, String userName, String phoneNumber) {

        remoteDatabase.saveDriverInfo(driverId, email, password, userName, phoneNumber);
    }

    public void getLocation() {

        remoteDatabase.getLocation();
    }
}
