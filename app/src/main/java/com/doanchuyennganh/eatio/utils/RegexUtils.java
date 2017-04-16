package com.doanchuyennganh.eatio.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Nguyen Tan Luan on 4/9/2017.
 */

public class RegexUtils {

    private static final Pattern USERNAME_PATTERN=Pattern.compile("^[a-z0-9_-]{3,16}$");
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^[a-z0-9_-]{6,18}$");

    public static boolean invalidUsername(String username){
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean invalidPassword(String password){
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean invalidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
