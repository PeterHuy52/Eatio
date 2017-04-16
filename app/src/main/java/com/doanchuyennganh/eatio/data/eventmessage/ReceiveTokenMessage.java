package com.doanchuyennganh.eatio.data.eventmessage;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class ReceiveTokenMessage {
    public AccessTokenEntity mToken;

    public ReceiveTokenMessage(AccessTokenEntity mToken) {
        this.mToken = mToken;
    }
}
