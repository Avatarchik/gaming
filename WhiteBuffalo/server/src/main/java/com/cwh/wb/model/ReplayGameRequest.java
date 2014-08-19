/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import nu.xom.Node;

public class ReplayGameRequest {
    
	private Node masterReplay = null;
	
    protected ReplayGameRequest unmarshal(Node eGameRequest) {
        this.masterReplay = eGameRequest;
        return this;
    }

	public Node getMasterReplay() {
		return masterReplay;
	}

}