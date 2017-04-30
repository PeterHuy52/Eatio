package com.doanchuyennganh.eatio.feature.fondadetail.interactor;

import com.doanchuyennganh.eatio.api.response.FondaResponse;
import com.doanchuyennganh.eatio.data.builder.FondaBuilder;
import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.FondaService;
import com.doanchuyennganh.eatio.services.Impl.FondaServiceImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */
@EBean
public class FondaDetailInteractorImpl extends BaseInteractor implements FondaDetailInteractor {

    @Bean(FondaServiceImpl.class)
    FondaService mFondaService;

    @Bean
    FondaBuilder mBuilder;

    @Override
    public void getDetailFonda(int fondaId, final InteractorCallback<FondaModel> callback) {
        mFondaService.getDetailFonda(fondaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FondaResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            callback.onError(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(FondaResponse fondaResponse) {
                        FondaEntity entity=fondaResponse.fondaEntity;
                        if(entity==null)
                            return;
                        FondaModel fonda=mBuilder.buildFrom(entity);
                        callback.onSuccess(fonda);
                    }
                });
    }
}
