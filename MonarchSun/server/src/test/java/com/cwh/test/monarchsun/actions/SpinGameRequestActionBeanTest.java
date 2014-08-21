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

public class SpinGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/test/monarchsun/resources/gdkRequest_spin.xml"));

    @Test
    public void testSpinGameRequest() {
        try {
            this.mockRequest  = new MockLocalHttpServletRequest("/someContext", "/SpinReq");
            this.mockResponse = new MockHttpServletResponse();
            this.mockRequest.setMockRequestData(SpinGameRequestActionBeanTest.gdkRequest);
            this.mockContext.acceptRequest(mockRequest, mockResponse);

            final String sResponse = this.mockResponse.getOutputString();
            System.out.println(sResponse);
            // This is more of a sanity check. Ensure that the resulting XML should return the following XML(String).
            assertTrue(sResponse.equals("<gdkResponse version=\"2\"><gameState><gameContext><currentPlayState state=\"FINISHED\" /><currentWager creditValue=\"0.01\" creditWager=\"5\" rtp=\"1\" /><currentResult creditWon=\"0\" /><stickyWild /><lines won=\"0\" /><reels><reel id=\"1\" pos=\"50\">3,6,6,6</reel><reel id=\"2\" pos=\"50\">3,5,5,9</reel><reel id=\"3\" pos=\"50\">8,11,11</reel><reel id=\"4\" pos=\"50\">3,5,5,9</reel><reel id=\"5\" pos=\"50\">3,6,6,6</reel><rePattern>3,3,3,3</rePattern></reels><currentFreeFeature totalSpins=\"0\" usedSpins=\"0\" totalWon=\"0\" /><baseGameStops>50,50,50,50,50</baseGameStops></gameContext></gameState><persistentState><persistentContext /></persistentState><gameResponse><instructions><financial><transaction playerId=\"1234\" action=\"BET\" amount=\"2.50\" /><transaction playerId=\"1234\" action=\"RETURN\" amount=\"0.00\" /></financial></instructions><responseToClient><msgdata reqName=\"SpinReq\"><reels><reel id=\"1\" pos=\"50\">3,6,6,6</reel><reel id=\"2\" pos=\"50\">3,5,5,9</reel><reel id=\"3\" pos=\"50\">8,11,11</reel><reel id=\"4\" pos=\"50\">3,5,5,9</reel><reel id=\"5\" pos=\"50\">3,6,6,6</reel><rePattern>3,3,3,3</rePattern></reels><lines won=\"0\" /><creditResults wagered=\"250\" won=\"0\" /><results wagered=\"2.50\" won=\"0.00\" net=\"-2.50\" /><freeSpins total=\"0\" used=\"0\" won=\"0\" /></msgdata></responseToClient></gameResponse></gdkResponse>"));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}