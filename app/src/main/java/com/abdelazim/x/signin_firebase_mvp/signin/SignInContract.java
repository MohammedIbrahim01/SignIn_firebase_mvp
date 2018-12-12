package com.abdelazim.x.signin_firebase_mvp.signin;

import android.content.DialogInterface;

public interface SignInContract {

    interface presenter {

        void signInClicked();

        void registerClicked();

        void dialogSignInClicked(DialogInterface dialog, String email, String password);

        void dialogRegisterClicked(DialogInterface dialog, String email, String password, String userName, String phoneNumber);

        void dialogCancelClicked(DialogInterface dialog);
    }

    interface View {

        void showSignInDialog();

        void showRegisterDialog();

        void hideDialog(DialogInterface dialog);

        void showSnackBar(String message);

        void showToast(String message);

        void gotoMapActivity();
    }

    interface AuthenticationCallbacks {

        void onSignInSuccess();

        void onRegisterSuccess(String driverId, String email, String password, String userName, String phoneNumber);

        void onSignInFailure(String failureMessage);

        void onRegisterFailure(String failureMessage);
    }

    interface RepositoryCallbacks {

        void onSaveDriverSuccess(String userName);

        void onSaveDriverFailure();
    }
}
