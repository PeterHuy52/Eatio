package com.doanchuyennganh.eatio.views.fonda;

import com.doanchuyennganh.eatio.entity.Fonda;

/**
 * Created by TungHo on 05/11/2017.
 */

public interface FondaDetailView {

    void showInvalidId();

    void setFonda(Fonda data);

    void updateView();

    String getOpendayText(int open_day);
}
