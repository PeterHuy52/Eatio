package com.doanchuyennganh.eatio.feature.store.interactor;

import com.doanchuyennganh.eatio.api.response.StoreGroupResponse;
import com.doanchuyennganh.eatio.api.response.StoreResponse;
import com.doanchuyennganh.eatio.data.builder.StoreBuilder;
import com.doanchuyennganh.eatio.data.builder.StoreGroupBuilder;
import com.doanchuyennganh.eatio.data.entity.StoreGroupEntity;
import com.doanchuyennganh.eatio.data.model.StoreGroupModel;
import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.StoreServiceImpl;
import com.doanchuyennganh.eatio.services.StoreService;

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
public class StoreInteractorImpl extends BaseInteractor implements StoreInteractor {

    @Bean(StoreServiceImpl.class)
    StoreService mStoreService;

    @Bean
    StoreModel mStoreModel;

    @Bean
    StoreBuilder mBuilder;

    @Bean
    StoreGroupBuilder mStoregroupBuilder;
    @Override
    public void createStore(String token, StoreModel storeModel, final InteractorCallback<StoreModel> callback) {
        mStoreService.createStore(token,storeModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoreResponse>() {
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
                    public void onNext(StoreResponse storeResponse) {
                        mStoreModel=mBuilder.buildFrom(storeResponse.storeEntity);
                        callback.onSuccess(mStoreModel);
                    }
                });
    }

    @Override
    public void getStoreGroups(final InteractorCallback<ArrayList<StoreGroupModel>> callback) {
        mStoreService.getStoreGroups("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoreGroupResponse>() {
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
                    public void onNext(StoreGroupResponse storeGroupResponse) {
                        ArrayList<StoreGroupModel> storeGroupModels=new ArrayList<>();
                        for(StoreGroupEntity entity:storeGroupResponse.storeGroupEntities)
                        {
                            StoreGroupModel model=mStoregroupBuilder.buildFrom(entity);
                            storeGroupModels.add(model);
                        }
                        callback.onSuccess(storeGroupModels);
                    }
                });
    }
}
