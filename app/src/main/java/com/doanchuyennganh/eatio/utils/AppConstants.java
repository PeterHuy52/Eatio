package com.doanchuyennganh.eatio.utils;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public class AppConstants {
    public static final String INVALID_ACCOUNT = "Tài khoản này chưa được kích hoạt, vui lòng kích hoạt tài khoản!";

    public interface DateFormatter {
        String SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.'S'Z";//"yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZZZZ"
        String DATE_FORMAT = "yyyy-MM-dd";
    }

    public interface ResponseCode {
        //User error
        int WRONG_USERNAME_OR_PASSWORD = 40101;
        int USERNAME_INVALID = 40401;
        int EMAIL_INVALID = 40402;
        int ACTIVATION_CODE_INVALID = 40102;
        int USERNAME_EXIST = 40901;
        int EMAIL_EXIST = 40902;
        int TOKEN_INVALID = 40300;
        int USER_NOT_FOUND = 40404;
        //Store error
        int USER_IS_NOT_VENDOR = 40301;
        int STORE_NAME_IS_EMPTY = 40010;
        int STORE_GROUP_IS_EMPTY = 40011;
        int STORE_SCALE_IS_EMPTY = 40012;
        int STORE_SCALE_INVALID = 40013;
        int TIME_FORMAT_WRONG = 40014;
        int LOCATION_FORMAT_WRONG = 40015;
        int DATE_FORMAT_WRONG = 40016;
        int STORE_GROUP_NOT_FOUND = 40411;
        int CLOSE_TIME_LESS_THAN_OPEN_TIME = 40914;
        int USER_HAS_NOT_PERMISSION = 40311;

        //Image error
        int IMAGE_NOT_FOUND = 40406;
        int IMAGE_NOT_BELONG_TO_USER = 40906;
        int CAN_NOT_DELETE_PROFILE_IMAGE = 40907;
        int IMAGE_EMPTY= 40007;
        int IMAGE_FORMAT_WRONG=40008;

    }

    public interface VerifyStatus {
        int WAITING_FOR_VERIFY = 1;
        int VERIFY_EXPIRED = 2;
        int VERIFIED = 3;
    }

    public interface ErrorInput {
        /*String USERNAME_EMPTY="The username field must not be empty. Please enter username.";
        String EMAIL_EMPTY="The email field must not be empty. Please enter email.";
        String PASSWORD_EMPTY="The password field must not be empty. Please enter password.";*/
        String USERNAME_INVALID = "Username must have 3-16 character, contain only letter and number";
        String EMAIL_INVALID = "Your email address is invalid. Please enter a valid address.";
        String PASSWORD_INVALID = "Username must have 6-16 character and contain only letter and number";
    }

    public interface DateDefault {
        int MIN_YEAR = 1900;
        int MIN_MONTH = 1;
        int MIN_DAY = 1;
    }

    public interface Gender {
        String MALE = "Male";
        String FEMALE = "Female";
    }

    public static enum ScaleStore {
        SMALL("Vừa và nhỏ, số chỗ < 50", 1),
        NORMAL("vừa và lớn, số chỗ từ 50-100", 2),
        LARGE("Lớn, số chỗ >100", 3);

        private String stringValue;
        private int intValue;

        private ScaleStore(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        public int getIntValue() {
            return intValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

    public static String formatNumber(int value) {
        String output = "";
        if (value < 10) {
            output = "0" + value;
        } else {
            output = "" + value;
        }
        return output;
    }
}
