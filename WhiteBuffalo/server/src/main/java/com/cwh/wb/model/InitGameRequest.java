/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import nu.xom.Element;
import nu.xom.Node;

public class InitGameRequest extends GameRequest {
    @Override
    protected GameRequest unmarshal(Node eGameRequest) {
        Element xGameRequest = (Element)eGameRequest;
        this.requestName = xGameRequest.getAttributeValue("reqName");
        return this;
    }
}