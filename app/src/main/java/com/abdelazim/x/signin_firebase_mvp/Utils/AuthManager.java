package com.abdelazim.x.signin_firebase_mvp.Utils;

import android.support.annotation.NonNull;

import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthManager {

    private SignInContract.PresenterCallbacks presenterCallbacks;
    private FirebaseAuth firebaseAuth;

    public AuthManager(SignInContract.PresenterCallbacks presenterCallbacks) {

        this.presenterCallbacks = presenterCallbacks;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void signIn(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        presenterCallbacks.onSignInSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        presenterCallbacks.onSignInFailure(e.getMessage());
                    }
                });
    }

    public void register(final String email, final String password, final String userName, final String phoneNumber) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        presenterCallbacks.onRegisterSuccess(firebaseAuth.getUid(), email, password, userName, phoneNumber);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        presenterCallbacks.onRegisterFailure(e.getMessage());
                    }
                });
    }
}
