package com.doanchuyennganh.eatio.presensters.resetpassword;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.User;
import com.doanchuyennganh.eatio.models.UserModel;
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
        model.resendPassword(email, username, new ApiRequestCallback<User>() {
            @Override
            public void responseData(User data) {
                if (data != null){
                    mView.resendPasswordSuccess();
                }
            }
        });
    }
}
