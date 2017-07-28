package com.doanchuyennganh.eatio.Repository;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.CommonApi;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Utility;

/**
 * Created by TungHo on 05/13/2017.
 */

public class CommonDataRepository {

    CommonApi api;

    public CommonDataRepository(){
        api = ApiConnection.createService(CommonApi.class);
    }


    /**
     * get all utilities contain name.
     * short by count of fonda, then name.
     * @param name
     * @param callback
     */
    public void getUtilities(String name, ApiRequestCallback<Utility> callback) {
        api.getUtilities(name).enqueue(callback);
    }
}
