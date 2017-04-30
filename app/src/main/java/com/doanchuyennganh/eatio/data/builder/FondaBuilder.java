package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.CulinaryEntity;
import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.entity.SaleEntity;
import com.doanchuyennganh.eatio.data.entity.UtilityEntity;
import com.doanchuyennganh.eatio.data.model.CulinaryModel;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.data.model.SaleModel;
import com.doanchuyennganh.eatio.data.model.UtilityModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaBuilder extends BaseModelBuilder<FondaModel, FondaEntity> {

    @Bean
    ImageBuilder mImageBuilder;
    @Bean
    UtilityBuilder mUtilityBuilder;
    @Bean
    CulinaryBuilder mCulinaryBuilder;
    @Bean
    SaleBuilder mSaleBuilder;
    @Bean
    CommentBuilder mCommentBuilder;
    @Bean
    FondaGroupBuilder mFondaGroupBuilder;
    @Bean
    LocationBuilder mLocationBuilder;

    @Override
    public FondaModel buildFrom(FondaEntity entity) {
        if (entity == null)
            return null;
        FondaModel fondaModel = new FondaModel();
        fondaModel.setName(entity.name);
        fondaModel.setCategory_id(entity.category_id);
        fondaModel.setOpenTime(entity.openTime);
        fondaModel.setCloseTime(entity.closeTime);
        fondaModel.setOpen_day(entity.open_day);
        fondaModel.setScale(entity.scale);
        fondaModel.setPhone_1(entity.phone_1);
        fondaModel.setPhone_2(entity.phone_2);
        fondaModel.setActive(entity.active);
        fondaModel.setCommentCount(entity.commentCount);
        if(entity.imageEntity!=null) {
            fondaModel.setFondaAvatar(mImageBuilder.buildFrom(entity.imageEntity));
        }
        if(entity.locationEntity!=null) {
            fondaModel.setLocation(mLocationBuilder.buildFrom(entity.locationEntity));
        }
        if(entity.utilityEntities.size()>0) {
            ArrayList<UtilityModel> utilityModels = new ArrayList<>();
            for (UtilityEntity utilityEntity : entity.utilityEntities) {
                utilityModels.add(mUtilityBuilder.buildFrom(utilityEntity));
            }
            fondaModel.setFondaUtilities(utilityModels);
        }
        if(entity.culinaryEntities.size()>0) {
            ArrayList<CulinaryModel> culinaryModels = new ArrayList<>();
            for (CulinaryEntity culinaryEntity : entity.culinaryEntities) {
                culinaryModels.add(mCulinaryBuilder.buildFrom(culinaryEntity));
            }
            fondaModel.setFondaCulinaries(culinaryModels);
        }
        if(entity.saleEntities.size()>0) {
            ArrayList<SaleModel> saleModels = new ArrayList<>();
            for (SaleEntity saleEntity : entity.saleEntities) {
                saleModels.add(mSaleBuilder.buildFrom(saleEntity));
            }
            fondaModel.setFondaSales(saleModels);
        }
        /*ArrayList<CommentModel> commentModels = new ArrayList<>();
        for (CommentEntity commentEntity : entity.commentEntities) {
            commentModels.add(mCommentBuilder.buildFrom(commentEntity));
        }
        fondaModel.setCommentModels(commentModels);*/
        fondaModel.setFondaGroup(mFondaGroupBuilder.buildFrom(entity.groupEntity));

        return fondaModel;
    }
}
