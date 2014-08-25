/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.actions;

import com.cwh.sample.mocks.MockLocalHttpServletRequest;
import com.cwh.sample.slotgame.utils.ResourceLoader;
import net.sourceforge.stripes.mock.MockHttpServletResponse;
import org.junit.Test;

import static junit.framework.Assert.fail;

public class ResumeGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/sample/resources/gdkRequest_resume.xml"));

    @Test
    public void testSpinGameRequest() {
        try {
            this.mockRequest  = new MockLocalHttpServletRequest("/someContext", "/resume_game");
            this.mockResponse = new MockHttpServletResponse();
            this.mockRequest.setMockRequestData(ResumeGameRequestActionBeanTest.gdkRequest);
            this.mockContext.acceptRequest(mockRequest, mockResponse);

            final String sResponse = this.mockResponse.getOutputString();
            System.out.println(sResponse);
            // This is more of a sanity check. Ensure that the resulting XML should return the following XML(String).
//            assertTrue(sResponse.equals("<gdkResponse><gameState><gameContext><currentPlayState state=\"FREE\" /><currentWager creditValue=\"0.01\" creditPerLine=\"5\" creditWager=\"25\" /><currentResult creditWon=\"100\" /><currentReels><reel id=\"1\">1,1,1</reel><reel id=\"2\">1,1,6</reel><reel id=\"3\">1,5,6</reel><reel id=\"4\">5,1,6</reel><reel id=\"5\">1,1,1</reel></currentReels><currentFreeFeature totalSpins=\"10\" usedSpins=\"0\" totalWon=\"0\" /></gameContext></gameState><persistentState><persistentContext><numberSeven count=\"10\" /></persistentContext></persistentState><gameResponse><instructions><financial><transaction playerId=\"1234\" action=\"BET\" amount=\"0.25\" /></financial></instructions><responseToClient><msgdata reqName=\"SpinReq\"><reels><reel id=\"1\">1,1,1</reel><reel id=\"2\">1,1,6</reel><reel id=\"3\">1,5,6</reel><reel id=\"4\">5,1,6</reel><reel id=\"5\">1,1,1</reel></reels><lines won=\"100\"><line id=\"5\" won=\"100\" icon=\"1\" positions=\"0,2;1,1;2,0;3,1;4,2\" /></lines><creditResults wagered=\"25\" won=\"100\" /><stars total=\"10\" /><freeSpins total=\"10\" used=\"0\" won=\"0\" /></msgdata></responseToClient></gameResponse></gdkResponse>"));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}