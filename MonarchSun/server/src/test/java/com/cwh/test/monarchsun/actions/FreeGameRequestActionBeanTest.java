/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.monarchsun.actions;

import com.cwh.test.mocks.MockLocalHttpServletRequest;
import  com.cwh.monarchsun.utils.ResourceLoader;
import net.sourceforge.stripes.mock.MockHttpServletResponse;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class FreeGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/test/monarchsun/resources/gdkRequest_spinFree.xml"));

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
            assertTrue(sResponse.equals("<gdkResponse version=\"2\"><gameState><gameContext><currentPlayState state=\"FREE\" /><currentWager creditValue=\"0.01\" creditWager=\"5\" rtp=\"1\" /><currentResult creditWon=\"0\" /><stickyWild /><lines won=\"0\" /><reels><reel id=\"1\" pos=\"58\">10,10,2,10</reel><reel id=\"2\" pos=\"58\">5,5,3,7</reel><reel id=\"3\" pos=\"0\">2,4,4</reel><reel id=\"4\" pos=\"59\">5,3,7,6</reel><reel id=\"5\" pos=\"50\">10,6,5,6</reel><rePattern>7,3,3,7</rePattern></reels><currentFreeFeature totalSpins=\"5\" usedSpins=\"1\" totalWon=\"0\" /><baseGameStops>1,1,1,1,1</baseGameStops></gameContext></gameState><persistentState><persistentContext /></persistentState><gameResponse><instructions><financial /></instructions><responseToClient><msgdata reqName=\"FreeReq\"><reels><reel id=\"1\" pos=\"58\">10,10,2,10</reel><reel id=\"2\" pos=\"58\">5,5,3,7</reel><reel id=\"3\" pos=\"0\">2,4,4</reel><reel id=\"4\" pos=\"59\">5,3,7,6</reel><reel id=\"5\" pos=\"50\">10,6,5,6</reel><rePattern>7,3,3,7</rePattern></reels><lines won=\"0\" /><creditResults wagered=\"250\" won=\"0\" /><freeSpins total=\"5\" used=\"1\" won=\"0\" /></msgdata></responseToClient></gameResponse></gdkResponse>"));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}