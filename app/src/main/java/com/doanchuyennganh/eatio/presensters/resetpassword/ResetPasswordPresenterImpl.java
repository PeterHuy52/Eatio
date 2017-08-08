package com.doanchuyennganh.eatio.presensters.resetpassword;

import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.presensters.base.BasePresenter;
import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.views.resetpassword.ResetPasswordView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TungHo on 05/06/2017.
 */

public class ResetPasswordPresenterImpl extends BasePresenter<ResetPasswordView, Navigator> implements ResetPasswrodPresenter {

    UserRepository mUserRepository;

    @Inject
    public ResetPasswordPresenterImpl(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public void resendPassword(String username, String email) {
        mUserRepository.resendPassword(username, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> mView.resendPasswordSuccess()
                        , throwable -> {
                            Error error = getError(throwable);
                            if (error.code == 40401) {
                                mView.wrongUsername();
                            } else if (error.code == 40402) {
                                mView.wrongEmail();
                            }
                        });
    }

    @Override
    public void validateInput(String username, String email) {
        if (RegexUtils.isValidUsername(username.trim()) == false) {
            mView.disableActionBtn();
            return;
        }
        if (RegexUtils.isValidEmail(email.trim()) == false) {
            mView.disableActionBtn();
            return;
        }
        mView.enableActionBtn();
    }
}
