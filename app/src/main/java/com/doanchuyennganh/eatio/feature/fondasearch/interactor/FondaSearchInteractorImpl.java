package com.doanchuyennganh.eatio.feature.fondasearch.interactor;

import com.doanchuyennganh.eatio.api.response.FondaCollectionResponse;
import com.doanchuyennganh.eatio.data.builder.FondaBuilder;
import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.FondaService;
import com.doanchuyennganh.eatio.services.Impl.FondaServiceImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */
@EBean
public class FondaSearchInteractorImpl extends BaseInteractor implements FondaSearchInteractor {

    @Bean(FondaServiceImpl.class)
    FondaService mFondaService;

    @Bean
    FondaBuilder mBuilder;
    @Override
    public void getFondasByName(String keyword, final InteractorCallback<ArrayList<FondaModel>> callback) {
        mFondaService.getListFonda(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FondaCollectionResponse>() {
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
                    public void onNext(FondaCollectionResponse response) {
                        ArrayList<FondaEntity> entities = response.fondaResponse.fondaEntities;
                        ArrayList<FondaModel> fondaModels = new ArrayList<FondaModel>();
                        for (FondaEntity fondaEntity : entities) {
                            fondaModels.add(mBuilder.buildFrom(fondaEntity));
                        }
                        callback.onSuccess(fondaModels);
                    }
                });
    }
}
