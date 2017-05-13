package com.doanchuyennganh.eatio.presensters.fonda;

/**
 * Created by TungHo on 05/13/2017.
 */

public interface UtilitiesPresenter {

    void getUtilities(String input);

    void addFondaUtilities(String token, int fondaId, int utilityId);

    void addFondaUtilities(String token, int fondaId, String utilityName);

    /**
     * Get danh sách utilities của một fonda
     * @param fondaId
     */
    void getFondaUtilities(int fondaId);

    /**
     * Update description của một fonda-utility
     * @param mToken
     * @param fondaId
     * @param id
     * @param description
     */
    void updateFondaUtility(String mToken, int fondaId, int id, String description);

    /**
     * Xoá một utils khỏi một fonda (không phải xoá hẳn util khỏi hệ thống)
     * @param mToken
     * @param fondaId
     * @param id
     */
    void removeFondaUtility(String mToken, int fondaId, int id);
}
