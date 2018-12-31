package com.abdelazim.x.signin_firebase_mvp.signin.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abdelazim.x.signin_firebase_mvp.R;
import com.abdelazim.x.signin_firebase_mvp.map.MapActivity;
import com.abdelazim.x.signin_firebase_mvp.signin.SignInContract;
import com.abdelazim.x.signin_firebase_mvp.signin.presenter.SignInPresenter;

public class SignInActivity extends AppCompatActivity implements SignInContract.View, View.OnClickListener {

    private SignInPresenter presenter;
    private Button signinButton, registerButton;
    private AlertDialog signInDialog;
    private AlertDialog registerDialog;
    private LinearLayout rootLinearLayout;
    private View signinLayout, registerLayout;
    private EditText emailEditTextSi, passwordEditTextSi,
            emailEditTextRe, passwordEditTextRe, userNameEditText, phoneNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        presenter = new SignInPresenter(this);
        initViews();
    }

    private void initViews() {

        rootLinearLayout = findViewById(R.id.root_linearLayout);
        signinButton = findViewById(R.id.signin_button);
        registerButton = findViewById(R.id.register_button);

        signinLayout = LayoutInflater.from(this).inflate(R.layout.signin_dialog_layout, null);
        emailEditTextSi = signinLayout.findViewById(R.id.email_editText_si);
        passwordEditTextSi = signinLayout.findViewById(R.id.password_editText_si);

        registerLayout = LayoutInflater.from(this).inflate(R.layout.register_dialog_layout, null);
        emailEditTextRe = registerLayout.findViewById(R.id.email_editText_re);
        passwordEditTextRe = registerLayout.findViewById(R.id.password_editText_re);
        userNameEditText = registerLayout.findViewById(R.id.userName_editText);
        phoneNumberEditText = registerLayout.findViewById(R.id.phoneNumber_editText);

        signInDialog = getSignInDialog();
        registerDialog = getRegisterDialog();


        signinButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void showSignInDialog() {

        signInDialog.show();
    }

    @Override
    public void showRegisterDialog() {

        registerDialog.show();
    }


    @Override
    public void hideDialog(DialogInterface dialog) {
        dialog.dismiss();
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(rootLinearLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoMapActivity() {
        startActivity(new Intent(SignInActivity.this, MapActivity.class));
        finish();
    }

    private AlertDialog getSignInDialog() {
        return new AlertDialog.Builder(this)
                .setView(signinLayout)
                .setTitle("Sign in")
                .setMessage("enter correct data")
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.dialogSignInClicked(dialog, emailEditTextSi.getText().toString(),
                                passwordEditTextSi.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.dialogCancelClicked(dialog);
                    }
                })
                .create();
    }


    private AlertDialog getRegisterDialog() {
        return new AlertDialog.Builder(this)
                .setView(registerLayout)
                .setTitle("Register")
                .setMessage("enter correct data")
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.dialogRegisterClicked(dialog, emailEditTextRe.getText().toString(), passwordEditTextRe.getText().toString(),
                                userNameEditText.getText().toString(), phoneNumberEditText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.dialogCancelClicked(dialog);
                    }
                }).create();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_button:
                presenter.signInClicked();
                break;
            case R.id.register_button:
                presenter.registerClicked();
                break;
        }
    }
}
