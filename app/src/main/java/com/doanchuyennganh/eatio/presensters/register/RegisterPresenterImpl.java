package com.doanchuyennganh.eatio.presensters.register;

import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.presensters.base.BasePresenter;
import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.views.register.RegisterNavigator;
import com.doanchuyennganh.eatio.views.register.RegisterView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TungHo on 05/07/2017.
 */

public class RegisterPresenterImpl  extends BasePresenter<RegisterView, RegisterNavigator>implements RegisterPresenter {

    UserRepository mUserRepository;

    @Inject
    public RegisterPresenterImpl(UserRepository userRepository) {

        mUserRepository = userRepository;
    }

    @Override
    public void signUp(String username, String email, String password) {

        mUserRepository.signUp(username,email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(verifyStatus -> {
                    if(verifyStatus != null){
                        mNavigator.goToVerifyCode(verifyStatus.userId);
                    }
                }, error->{
                    Error e = getError(error);
                    if(e.code == 40901)
                        mView.usernameExists();
                    else if(e.code == 40902)
                        mView.emailExists();
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
