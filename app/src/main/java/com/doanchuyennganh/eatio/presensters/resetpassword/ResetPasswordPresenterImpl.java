package com.doanchuyennganh.eatio.presensters.resetpassword;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.User;
import com.doanchuyennganh.eatio.models.UserModel;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.views.resetpassword.ResetPasswordView;

/**
 * Created by TungHo on 05/06/2017.
 */

public class ResetPasswordPresenterImpl implements ResetPasswrodPresenter {

    ResetPasswordView mView;



    public ResetPasswordPresenterImpl(ResetPasswordView view) {
        mView = view;
    }

    @Override
    public void resendPassword(String username, String email) {
        UserModel model = new UserModel();
        model.resendPassword(username.trim(), email.trim(), new ApiRequestCallback<User>() {
            @Override
            public void responseData(User data) {
                mView.resendPasswordSuccess();
            }

            @Override
            public void responseError(Error data) {
                if (data.code == 40401){
                    mView.wrongUsername();
                }
                else if (data.code == 40402){
                    mView.wrongEmail();
                }
            }
        });
    }

    @Override
    public void validateInput(String username, String email) {
        if (RegexUtils.isValidUsername(username.trim()) == false){
            mView.disableActionBtn();
            return;
        }
//        if (RegexUtils.isValidEmail(email.trim()) == false){
//            mView.disableActionBtn();
//            return;
//        }
        mView.enableActionBtn();
    }
}
