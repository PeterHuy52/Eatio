package com.doanchuyennganh.eatio.feature.leftmenu.view;

import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.feature.base.PView;

/**
 * Created by Nguyen Tan Luan on 4/13/2017.
 */

public interface LeftMenuView extends PView {
    void showUserInfo(UserModel user);
}
