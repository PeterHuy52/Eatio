package com.doanchuyennganh.eatio.views.fonda.fondalist;

import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.views.base.BaseView;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */

public interface FondaListView extends BaseView{
    void updateFondaListView(Paging<Fonda> paging);
    void showEmptyList();
    void hideEmptyList();
}
