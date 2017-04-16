package com.doanchuyennganh.eatio.feature.leftmenu.presenter;

import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.leftmenu.view.LeftMenuView;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/13/2017.
 */
@EBean
public class LeftMenuPresenterImpl
        extends MainPresenter<LeftMenuView,Navigator,Interactor>
        implements LeftMenuPresenter {
}
