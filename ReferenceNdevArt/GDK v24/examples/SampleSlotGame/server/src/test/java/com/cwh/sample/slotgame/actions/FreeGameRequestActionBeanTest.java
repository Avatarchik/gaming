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

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class FreeGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/sample/resources/gdkRequest_spinFree.xml"));

    @Test
    public void testSpinGameRequest() {
        try {
            this.mockRequest  = new MockLocalHttpServletRequest("/someContext", "/FreeReq");
            this.mockResponse = new MockHttpServletResponse();
            this.mockRequest.setMockRequestData(FreeGameRequestActionBeanTest.gdkRequest);
            this.mockContext.acceptRequest(mockRequest, mockResponse);

            final String sResponse = this.mockResponse.getOutputString();
            System.out.println(sResponse);
            // This is more of a sanity check. Ensure that the resulting XML should return the following XML(String).
            assertTrue(sResponse.equals("<gdkResponse version=\"2\"><gameState><gameContext><currentPlayState state=\"FREE\" /><currentWager creditValue=\"0.01\" creditPerLine=\"5\" creditWager=\"5\" /><currentResult creditWon=\"150\" /><lines won=\"100\"><line id=\"5\" won=\"100\" icon=\"1\" positions=\"0,2;1,1;2,0;3,1;4,2\" /></lines><reels><reel id=\"1\" pos=\"0\">1,1,1</reel><reel id=\"2\" pos=\"9\">1,1,6</reel><reel id=\"3\" pos=\"8\">1,5,6</reel><reel id=\"4\" pos=\"9\">5,1,6</reel><reel id=\"5\" pos=\"0\">1,1,1</reel></reels><currentFreeFeature totalSpins=\"20\" usedSpins=\"1\" totalWon=\"100\" /></gameContext></gameState><persistentState><persistentContext><numberSeven count=\"10\" /></persistentContext></persistentState><gameResponse><instructions><financial /></instructions><responseToClient><msgdata reqName=\"FreeReq\"><reels><reel id=\"1\" pos=\"0\">1,1,1</reel><reel id=\"2\" pos=\"9\">1,1,6</reel><reel id=\"3\" pos=\"8\">1,5,6</reel><reel id=\"4\" pos=\"9\">5,1,6</reel><reel id=\"5\" pos=\"0\">1,1,1</reel></reels><lines won=\"100\"><line id=\"5\" won=\"100\" icon=\"1\" positions=\"0,2;1,1;2,0;3,1;4,2\" /></lines><creditResults wagered=\"5\" won=\"150\" /><stars total=\"10\" /><freeSpins total=\"20\" used=\"1\" won=\"100\" /></msgdata></responseToClient></gameResponse></gdkResponse>"));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}