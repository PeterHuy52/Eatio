package com.doanchuyennganh.eatio.presensters.login;

import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.views.IMessageView;
import com.doanchuyennganh.eatio.views.login.LoginView;

/**
 * Created by TungHo on 05/06/2017.
 */

public class LoginPresenterImpl implements LoginPresenter, IMessageView {

    LoginView mView;

    public LoginPresenterImpl(LoginView view){
        mView = view;
    }

    @Override
    public void validateInput(String username, String password) {
        if (RegexUtils.isValidUsername(username.trim()) == false){
            mView.disableLoginBtn();
            return;
        }
        if (RegexUtils.isValidPassword(password.trim()) == false){
            mView.disableLoginBtn();
            return;
        }
        mView.enableLoginBtn();
    }

    @Override
    public void login(String username, String password) {
        UserRepository userRepository = new UserRepository();
        userRepository.login(username.trim(), password.trim(), new ApiRequestCallback<AccessToken>() {
            @Override
            public void responseData(AccessToken data) {
                mView.loginSuccess(data);
            }

            @Override
            public void responseError(Error data) {
                if (data.code == 40101){
                    // sai password
                    mView.loginFail();
                }
                else if (data.code == 40102) {
                    mView.goToVerifyCode();
                }
            }
        });
    }

    @Override
    public void setMessageText(String text, boolean isPositive) {

    }

    @Override
    public void hideMessageText() {

    }
}
