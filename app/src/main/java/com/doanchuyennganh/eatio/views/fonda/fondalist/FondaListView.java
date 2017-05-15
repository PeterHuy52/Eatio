package com.doanchuyennganh.eatio.views.fonda.fondalist;

import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Fonda;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */

public interface FondaListView {
    void updateFondaListView(Paging<Fonda> paging);
}
