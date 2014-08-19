package com.cwh.wb.actions;

import com.cwh.wb.model.ReplayGameRequest;
import com.cwh.wb.model.ReplayGameResponse;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/replay_game")
public class ReplayActionBean extends AbstractGameRequestActionBean {
    
    @DefaultHandler
    public Resolution getAboutInformation() {
        try {
        	final ReplayGameRequest replayRequest     = (ReplayGameRequest)this.gdkRequest.getReplay();
        	ReplayGameResponse replayGameResponse = new ReplayGameResponse();
        	this.setContentResponse(replayGameResponse.parseReplay(replayRequest.getMasterReplay()).getBytes());
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Request: " + t, t);
        }
        return this.getResolution();
    }
}