package com.doanchuyennganh.eatio.utils;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public class AppConstants {

    public interface DateFormatter {
        String SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.'S'Z"; //"yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZZZZ"
    }
    public interface ResponseCode {
        int WRONG_USERNAME_OR_PASSWORD = 40101;
        int USERNAME_INVALID = 40401;
        int EMAIL_INVALID = 40402;
        int ACTIVATION_CODE_INVALID = 40102;
        int USERNAME_EXIST = 40901;
        int EMAIL_EXIST = 40902;
    }

    public interface VerifyStatus{
        int WAITING_FOR_VERIFY = 1;
        int VERIFY_EXPIRED = 2;
        int VERIFIED = 3;
    }
}
