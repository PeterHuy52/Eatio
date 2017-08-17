package com.doanchuyennganh.eatio.application.appcomponent;

import com.doanchuyennganh.eatio.application.appcomponent.modules.ApiModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.AppModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.DatabaseModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.PresenterModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.RepositoryModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.RetrofitModule;
import com.doanchuyennganh.eatio.views.fonda.createfonda.CreateFondaActivity;
import com.doanchuyennganh.eatio.views.fonda.FondaDetailActivity;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListFragment;
import com.doanchuyennganh.eatio.views.fonda.fondaphoto.FondaPhotoActivity;
import com.doanchuyennganh.eatio.views.fonda.fondaphoto.FullPhotoActivity;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaQuickSearchActivity;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaSearchActivity;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaSearchDetailActivity;
import com.doanchuyennganh.eatio.views.home.HomeActivity;
import com.doanchuyennganh.eatio.views.home.LeftMenuFragment;
import com.doanchuyennganh.eatio.views.login.LoginActivity;
import com.doanchuyennganh.eatio.views.mapactivity.SelectLocationActivity;
import com.doanchuyennganh.eatio.views.profile.ProfileActivity;
import com.doanchuyennganh.eatio.views.register.RegisterActivity;
import com.doanchuyennganh.eatio.views.resetpassword.ResetPasswordActivity;
import com.doanchuyennganh.eatio.views.splash.SplashActivity;
import com.doanchuyennganh.eatio.views.verifycode.VerifyCodeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lap10515 on 28/07/2017.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        RetrofitModule.class,
        DatabaseModule.class,
        PresenterModule.class,
        RepositoryModule.class,
})
public interface AppComponent {
    //Inject Activity
    void inject(CreateFondaActivity createFondaActivity);
    void inject(FondaDetailActivity fondaDetailActivity);
    void inject(FondaPhotoActivity fondaPhotoActivity);
    void inject(FullPhotoActivity fullPhotoActivity);
    void inject(HomeActivity homeActivity);
    void inject(LoginActivity loginActivity);
    void inject(ProfileActivity profileActivity);
    void inject(RegisterActivity registerActivity);
    void inject(ResetPasswordActivity resetPasswordActivity);
    void inject(VerifyCodeActivity verifyCodeActivity);
    void inject(SplashActivity splashActivity);
    void inject(SelectLocationActivity selectLocationActivity);
    void inject(FondaSearchDetailActivity activity);
    void inject(FondaSearchActivity activity);
    void inject(FondaQuickSearchActivity activity);

    //Inject Fragment
    void inject(FondaListFragment fondaListFragment);
    void inject(LeftMenuFragment leftMenuFragment);
}
