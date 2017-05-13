package com.doanchuyennganh.eatio.views.fonda;

import com.doanchuyennganh.eatio.entity.Fonda;

/**
 * Created by TungHo on 05/11/2017.
 */

public interface FondaDetailView extends FondaEdit{

    void showInvalidId();

    void setFonda(Fonda data);

    void updateView();
    void refresh();

    String getOpendayText(int open_day);

    void call(String phonNumber);

    void callBtnClick();
    void nameTvClick();
    void addressTvClick();
    void openTimeClick();
    void closeTimeClick();
    void openDayClick();
//    void addUtilsClick();
//    void utilsAcceptBtnClick();
//    void utilsTextChanged();

}
