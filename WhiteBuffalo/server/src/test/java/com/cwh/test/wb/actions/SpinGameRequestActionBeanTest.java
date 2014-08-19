/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.wb.actions;

import com.cwh.test.mocks.MockLocalHttpServletRequest;
import  com.cwh.wb.utils.ResourceLoader;
import net.sourceforge.stripes.mock.MockHttpServletResponse;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class SpinGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/test/wb/resources/gdkRequest_spin.xml"));

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
            assertTrue(sResponse.equals("<gdkResponse version=\"2\"><gameState><gameContext><currentPlayState state=\"FINISHED\" /><currentWager creditValue=\"0.01\" creditWager=\"5\" rtp=\"2\" /><currentResult creditWon=\"250\" /><lines won=\"250\"><line id=\"9\" won=\"50\" icon=\"10\" positions=\"1,1;2,1;2,2;3,1\" /><line id=\"17\" won=\"200\" icon=\"9\" positions=\"3,2;3,4;4,1;4,2;5,1;5,3\" /></lines><reels><reel id=\"1\" pos=\"107\">10,3,6</reel><reel id=\"2\" pos=\"123\">10,10,5,7</reel><reel id=\"3\" pos=\"119\">10,9,8,9</reel><reel id=\"4\" pos=\"111\">9,9,4,11</reel><reel id=\"5\" pos=\"111\">9,6,9</reel><rePattern>1,1,1</rePattern></reels><currentFreeFeature totalSpins=\"0\" usedSpins=\"0\" totalWon=\"0\" /><baseGameStops>107,123,119,111,111</baseGameStops></gameContext></gameState><persistentState><persistentContext /></persistentState><gameResponse><instructions><financial><transaction playerId=\"1234\" action=\"BET\" amount=\"2.50\" /><transaction playerId=\"1234\" action=\"RETURN\" amount=\"2.50\" /></financial></instructions><responseToClient><msgdata reqName=\"SpinReq\"><reels><reel id=\"1\" pos=\"107\">10,3,6</reel><reel id=\"2\" pos=\"123\">10,10,5,7</reel><reel id=\"3\" pos=\"119\">10,9,8,9</reel><reel id=\"4\" pos=\"111\">9,9,4,11</reel><reel id=\"5\" pos=\"111\">9,6,9</reel><rePattern>1,1,1</rePattern></reels><lines won=\"250\"><line id=\"9\" won=\"50\" icon=\"10\" positions=\"1,1;2,1;2,2;3,1\" /><line id=\"17\" won=\"200\" icon=\"9\" positions=\"3,2;3,4;4,1;4,2;5,1;5,3\" /></lines><creditResults wagered=\"250\" won=\"250\" /><results wagered=\"2.50\" won=\"2.50\" net=\"0.00\" /><freeSpins total=\"0\" used=\"0\" won=\"0\" /></msgdata></responseToClient></gameResponse></gdkResponse>"));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}