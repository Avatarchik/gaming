/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunFreeSpinsReelsConfig;
import com.cwh.monarchsun.MonarchSunConfig.MonarchSunReelsConfig;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

public class InitGameResponse extends GameResponse {
	
	Integer rtpLevel = 0;
	
	@Override
    public Node marshal() {
        Element eMsgdata = new Element("msgdata");
        eMsgdata.addAttribute(new Attribute("reqName", this.requestName));
        
        Element eReelsMain        = new Element("mainReelsConfig");
        Marshalling.ReelsMapMarshal(MonarchSunReelsConfig.getReels(), eReelsMain, rtpLevel);
        eMsgdata.appendChild(eReelsMain);
        
        Element eReelsFreeSpins       = new Element("freeSpinReelsConfig");
        Marshalling.ReelsMapMarshal(MonarchSunFreeSpinsReelsConfig.getReels(), eReelsFreeSpins, rtpLevel);
        eMsgdata.appendChild(eReelsFreeSpins);
        
        Element eInitStops       = new Element("initStops");
        Marshalling.InitStopsMarshal(eInitStops, rtpLevel);
        eMsgdata.appendChild(eInitStops);

        return eMsgdata;
    }

	public void setRTPLevel(Integer rtpLevel) {
		this.rtpLevel = rtpLevel;
	}
}