package com.doanchuyennganh.eatio.feature.createfonda.interactor;

import com.doanchuyennganh.eatio.api.response.FondaGroupResponse;
import com.doanchuyennganh.eatio.api.response.FondaResponse;
import com.doanchuyennganh.eatio.data.builder.FondaBuilder;
import com.doanchuyennganh.eatio.data.builder.FondaGroupBuilder;
import com.doanchuyennganh.eatio.data.entity.FondaGroupEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.data.model.FondaGroupModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.FondaServiceImpl;
import com.doanchuyennganh.eatio.services.FondaService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class FondaInteractorImpl extends BaseInteractor implements FondaInteractor {

    @Bean(FondaServiceImpl.class)
    FondaService mFondaService;

    @Bean
    FondaModel mFondaModel;

    @Bean
    FondaBuilder mBuilder;

    @Bean
    FondaGroupBuilder mFondagroupBuilder;
    @Override
    public void createFonda(String token, FondaModel fondaModel, final InteractorCallback<FondaModel> callback) {
        mFondaService.createFonda(token, fondaModel)
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
                        mFondaModel =mBuilder.buildFrom(fondaResponse.fondaEntity);
                        callback.onSuccess(mFondaModel);
                    }
                });
    }

    @Override
    public void getFondaGroups(final InteractorCallback<ArrayList<FondaGroupModel>> callback) {
        mFondaService.getFondaGroups("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FondaGroupResponse>() {
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
                    public void onNext(FondaGroupResponse fondaGroupResponse) {
                        ArrayList<FondaGroupModel> storeGroupModels=new ArrayList<>();
                        for(FondaGroupEntity entity: fondaGroupResponse.storeGroupEntities)
                        {
                            FondaGroupModel model=mFondagroupBuilder.buildFrom(entity);
                            storeGroupModels.add(model);
                        }
                        callback.onSuccess(storeGroupModels);
                    }
                });
    }
}
