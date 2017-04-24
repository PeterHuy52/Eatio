package com.doanchuyennganh.eatio.feature.createfonda.presenter;

        import com.doanchuyennganh.eatio.data.model.FondaModel;
        import com.doanchuyennganh.eatio.feature.base.Presenter;
        import com.doanchuyennganh.eatio.feature.createfonda.view.FondaNavigator;
        import com.doanchuyennganh.eatio.feature.createfonda.view.FondaView;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface FondaPresenter extends Presenter<FondaView,FondaNavigator> {
    void createFonda(String token, FondaModel fondaModel);
    void prepareFondaInfo();
}
