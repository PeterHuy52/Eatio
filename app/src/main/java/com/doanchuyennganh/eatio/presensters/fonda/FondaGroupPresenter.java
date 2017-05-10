package com.doanchuyennganh.eatio.presensters.fonda;

import com.doanchuyennganh.eatio.entity.Fonda;

/**
 * Created by TungHo on 05/09/2017.
 */

public interface FondaGroupPresenter {

    void getGroupList();


    void createFonda(String token, Fonda fonda);
}
