package com.abdelazim.x.signin_firebase_mvp.Utils;

public class TextUtils {

    public static boolean isValidUserEmail(String email) {

        return email.contains(".com");
    }

    public static boolean isValidPassword(String password) {

        return password.length() >= 6;
    }
}
