/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.*;

public abstract class GameRequest {
    protected String requestName = null;

    public String getRequestName() {
        return requestName;
    }

    protected abstract GameRequest unmarshal(Node eGameRequest);

    public static GameRequest unmarshalInstance(Node eGameRequest) {
        GameRequest gameRequest = null;
        try {
            Element xGameRequest = (Element)eGameRequest;
            String  sRequestName = xGameRequest.getAttributeValue("reqName");
            StringBuilder sGameRequest = new StringBuilder();
            sGameRequest.append("com.cwh.sample.slotgame.model.");
            sGameRequest.append( sRequestName.substring(0, sRequestName.indexOf("Req")));
            sGameRequest.append("GameRequest");
            gameRequest = ((GameRequest)Class.forName(sGameRequest.toString()).newInstance()).unmarshal(eGameRequest);
        } catch(Throwable t) {
            throw new RuntimeException("Unable to parse gameRequest: " + t, t);
        }
        return gameRequest;
    }
}