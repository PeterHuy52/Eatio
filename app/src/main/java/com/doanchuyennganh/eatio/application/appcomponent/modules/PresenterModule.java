package com.doanchuyennganh.eatio.application.appcomponent.modules;

import com.doanchuyennganh.eatio.presensters.fonda.FondaDetailPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.FondaDetailPresenterImpl;
import com.doanchuyennganh.eatio.presensters.fonda.FondaGroupPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.FondaGroupPresenterImpl;
import com.doanchuyennganh.eatio.presensters.fonda.UtilitiesPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.UtilitiesPresenterImpl;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenterImpl;
import com.doanchuyennganh.eatio.presensters.fonda.fondaphoto.FondaPhotoPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.fondaphoto.FondaPhotoPresenterImpl;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenter;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenterImpl;
import com.doanchuyennganh.eatio.presensters.map.LocationPresenter;
import com.doanchuyennganh.eatio.presensters.map.LocationPresenterImpl;
import com.doanchuyennganh.eatio.presensters.map.MapPresenter;
import com.doanchuyennganh.eatio.presensters.map.MapPresenterImpl;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenter;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenterImpl;
import com.doanchuyennganh.eatio.presensters.register.RegisterPresenter;
import com.doanchuyennganh.eatio.presensters.register.RegisterPresenterImpl;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswordPresenterImpl;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswrodPresenter;
import com.doanchuyennganh.eatio.presensters.splash.SplashPresenter;
import com.doanchuyennganh.eatio.presensters.splash.SplashPresenterImpl;
import com.doanchuyennganh.eatio.presensters.verifycode.VerifyCodePresenter;
import com.doanchuyennganh.eatio.presensters.verifycode.VerifyCodePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lap10515 on 28/07/2017.
 */
@Module
public class PresenterModule {
    //Fonda Presenter
    @Provides
    public FondaListPresenter provideFondaListPresenterModule(FondaListPresenterImpl fondaListPresenter){
        return fondaListPresenter;
    }

    @Provides
    public FondaDetailPresenter provideFondaDetailPresenterModule(FondaDetailPresenterImpl fondaDetailPresenter){
        return fondaDetailPresenter;
    }

    @Provides
    public FondaGroupPresenter provideFondaGroupPresenterModule(FondaGroupPresenterImpl fondaGroupPresenter){
        return fondaGroupPresenter;
    }

    @Provides
    public FondaPhotoPresenter provideFondaPhotoPresenterModule(FondaPhotoPresenterImpl fondaPhotoPresenter){
        return fondaPhotoPresenter;
    }

    @Provides
    public UtilitiesPresenter provideUltilitiesPresenterModule(UtilitiesPresenterImpl utilitiesPresenter){
        return utilitiesPresenter;
    }

    //User presenter
    @Provides
    public LoginPresenter provideLoginPresenterModule(LoginPresenterImpl loginPresenter){
        return loginPresenter;
    }

    @Provides
    public RegisterPresenter provideRegisterPresenterModule(RegisterPresenterImpl registerPresenter){
        return registerPresenter;
    }

    @Provides
    public ProfilePresenter provideProfilePresenterModule(ProfilePresenterImpl profilePresenter){
        return profilePresenter;
    }

    @Provides
    public ResetPasswrodPresenter provideResetPresenterPasswordPresenterModule(ResetPasswordPresenterImpl resetPasswordPresenter){
        return resetPasswordPresenter;
    }

    @Provides
    public VerifyCodePresenter provideVerifyCodePresenterModule(VerifyCodePresenterImpl verifyCodePresenter){
        return verifyCodePresenter;
    }
    //Location Presenter
    @Provides
    public LocationPresenter provideLocationPresenterModule(LocationPresenterImpl locationPresenter){
        return locationPresenter;
    }

    @Provides
    public MapPresenter provideMapPresenterModule(MapPresenterImpl mapPresenter){
        return mapPresenter;
    }

    //Splash Presenter
    @Provides
    public SplashPresenter provideSplashPresenterModule(SplashPresenterImpl splashPresenter){
        return splashPresenter;
    }

}
