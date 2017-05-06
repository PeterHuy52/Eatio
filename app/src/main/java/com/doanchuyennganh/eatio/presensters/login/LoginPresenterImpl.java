package com.doanchuyennganh.eatio.presensters.login;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.models.UserModel;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.views.login.LoginView;

/**
 * Created by TungHo on 05/06/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

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
        UserModel userModel = new UserModel();
        userModel.login(username.trim(), password.trim(), new ApiRequestCallback<AccessToken>() {
            @Override
            public void responseData(AccessToken data) {
                loginSuccess(data);
            }
        });
    }

    @Override
    public void loginSuccess(AccessToken accessToken) {
        mView.savePrefAccessToken(accessToken.token);
        mView.savePrefUserId(accessToken.userId);
        mView.goToHome();
    }
}
