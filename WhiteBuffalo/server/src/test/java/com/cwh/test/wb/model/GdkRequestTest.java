/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.wb.model;

import com.cwh.wb.model.GameState;
import com.cwh.wb.model.GdkRequest;
import com.cwh.wb.model.SpinGameRequest;
import com.cwh.wb.utils.ResourceLoader;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import org.junit.Test;

import java.io.StringReader;

import static junit.framework.Assert.*;

public class GdkRequestTest {
    private static final ResourceLoader rLoader         = new ResourceLoader();
    private static final byte[]         gdkRequest_spin = rLoader.getResourceAsBytes("com/cwh/test/wb/resources/gdkRequest_spin.xml");
    private static final byte[]         gdkRequest_init = rLoader.getResourceAsBytes("com/cwh/test/wb/resources/gdkRequest_init.xml");

    @Test
    public void testParseRequest_spinReq() {
        try {
            Document xRequest = new Builder().build(new StringReader(new String(gdkRequest_spin)));
            Element eRequest = xRequest.getRootElement();

            GdkRequest gdkRequest = new GdkRequest().unmarshal(eRequest);

            assertEquals(1234, gdkRequest.getPlayer().getPlayerId().intValue());

            assertEquals(GameState.PLAY_STATE.NEW, gdkRequest.getGameState().getPlayState());
            assertEquals(0.01d, gdkRequest.getGameState().getCreditValue().doubleValue());
            //assertEquals(5,     gdkRequest.getGameState().getCreditPerLine().intValue());
            assertEquals(5,     gdkRequest.getGameState().getCreditWager().intValue());
            assertEquals(50,    gdkRequest.getGameState().getCreditWonTotal().intValue());

            assertEquals(1000, gdkRequest.getProfile().getProfileId().intValue());
            assertEquals(2000, gdkRequest.getProfile().getAccountId().intValue());
            assertEquals(2,    gdkRequest.getProfile().getCurrencyScale().intValue());
            assertTrue("GBP".equals(gdkRequest.getProfile().getCurrencyCode()));
            assertTrue("0.01,0.05,0.10,0.25".equals(gdkRequest.getProfile().getParameter("coinValue")));
            assertTrue("0.01"   .equals(gdkRequest.getProfile().getParameter("minBet")));
            assertTrue("1000.00".equals(gdkRequest.getProfile().getParameter("maxBet")));

            assertEquals(2,  gdkRequest.getRandomPools().nextSeq("poolA").intValue());
            assertEquals(13, gdkRequest.getRandomPools().nextSeq("poolA").intValue());
            assertEquals(4,  gdkRequest.getRandomPools().nextSeq("poolA").intValue());
            assertEquals(1,  gdkRequest.getRandomPools().nextSeq("poolB").intValue());
            assertEquals(34, gdkRequest.getRandomPools().nextSeq("poolB").intValue());
            assertEquals(5,  gdkRequest.getRandomPools().nextSeq("poolA").intValue());
            assertEquals(4,  gdkRequest.getRandomPools().nextSeq("poolB").intValue());

            assertTrue("SpinReq".equals(gdkRequest.getGameRequest().getRequestName()));
            assertEquals(0.01,  ((SpinGameRequest)gdkRequest.getGameRequest()).getCoinValue().doubleValue());
            assertEquals(5,     ((SpinGameRequest)gdkRequest.getGameRequest()).getCreditWager().intValue());
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void testParseRequest_initReq() {
        try {
            Document xRequest = new Builder().build(new StringReader(new String(gdkRequest_init)));
            Element eRequest = xRequest.getRootElement();

            GdkRequest gdkRequest = new GdkRequest().unmarshal(eRequest);

            assertEquals(1234, gdkRequest.getPlayer().getPlayerId().intValue());

            assertEquals(GameState.PLAY_STATE.NEW, gdkRequest.getGameState().getPlayState());
            assertEquals(0.00d, gdkRequest.getGameState().getCreditValue().doubleValue());
            //assertEquals(0,     gdkRequest.getGameState().getCreditPerLine().intValue());
            assertEquals(0,     gdkRequest.getGameState().getCreditWager().intValue());
            assertEquals(0,     gdkRequest.getGameState().getCreditWonTotal().intValue());

            assertEquals(1000,  gdkRequest.getProfile().getProfileId().intValue());
            assertEquals(2000,  gdkRequest.getProfile().getAccountId().intValue());
            assertEquals(2,     gdkRequest.getProfile().getCurrencyScale().intValue());
            assertTrue("GBP".equals(gdkRequest.getProfile().getCurrencyCode()));
            assertTrue("0.01,0.05,0.10,0.25".equals(gdkRequest.getProfile().getParameter("coinValue")));
            assertTrue("0.01"   .equals(gdkRequest.getProfile().getParameter("minBet")));
            assertTrue("1000.00".equals(gdkRequest.getProfile().getParameter("maxBet")));

            assertTrue("InitReq".equals(gdkRequest.getGameRequest().getRequestName()));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}