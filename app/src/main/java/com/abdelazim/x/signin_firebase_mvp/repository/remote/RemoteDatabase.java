package com.abdelazim.x.signin_firebase_mvp.repository.remote;

import android.support.annotation.NonNull;

import com.abdelazim.x.signin_firebase_mvp.repository.dataholder.Driver;
import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoteDatabase {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootReference;
    private DatabaseReference driversInfoReference;
    private SignInContract.PresenterCallbacks presenterCallbacks;

    public RemoteDatabase(SignInContract.PresenterCallbacks presenterCallbacks) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootReference = firebaseDatabase.getReference();
        driversInfoReference = rootReference.child("Drivers");
        this.presenterCallbacks = presenterCallbacks;
    }

    public void saveDriverInfo(String driverId, String email, String password, final String userName, String phoneNumber) {

        driversInfoReference.child(driverId).setValue(new Driver(email, password, userName, phoneNumber))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        presenterCallbacks.onSaveDriverSuccess(userName);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        presenterCallbacks.onSaveDriverFailure();
                    }
                });
    }
}
