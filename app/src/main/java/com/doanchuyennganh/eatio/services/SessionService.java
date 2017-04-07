package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.data.model.User;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public interface SessionService {
    void setCurrentUser(User user);
    User getCurrentUser();

    void saveCurrentUserId(int userId);
    int loadCurrentUserId();
}
