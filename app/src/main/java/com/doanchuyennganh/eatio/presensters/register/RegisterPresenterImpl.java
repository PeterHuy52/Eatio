package com.doanchuyennganh.eatio.presensters.register;

import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.VerifyStatus;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.views.register.RegisterView;

/**
 * Created by TungHo on 05/07/2017.
 */

public class RegisterPresenterImpl  implements RegisterPresenter {

    RegisterView mView;

    public RegisterPresenterImpl(RegisterView view) {
        mView = view;
    }

    @Override
    public void signUp(String username, String email, String password) {

        UserRepository model = new UserRepository();
        model.signUp(username, email, password, new ApiRequestCallback<VerifyStatus>() {
            @Override
            public void responseData(VerifyStatus data) {
                mView.goToVerifyCode(data.userId);
            }

            @Override
            public void responseError(Error data) {
                if (data.code == 40901){
                    mView.usernameExists();
                }
                else if (data.code == 40902){
                    mView.emailExists();
                }
            }
        });
    }

    @Override
    public void validateInput(String username, String email, String password, String confirmPwd) {
        if (password.equals(confirmPwd) == false){
            mView.disableRegisterBtn();
            return;
        }
        if (RegexUtils.isValidUsername(username) == false){
            mView.disableRegisterBtn();
            return;
        }
        if (RegexUtils.isValidPassword(password) == false){
            mView.disableRegisterBtn();
            return;
        }
        if (RegexUtils.isValidEmail(email) == false){
            mView.disableRegisterBtn();
            return;
        }
        mView.enableRegisterBtn();
    }

}
