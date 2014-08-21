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

import static junit.framework.Assert.fail;
import static org.junit.Assert.*;

public class ResumeGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/test/monarchsun/resources/gdkRequest_resume.xml"));

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
            assertTrue(sResponse.equals("<gdkResponse version=\"2\"><gameResponse><instructions><financial /></instructions><responseToClient><msgdata reqName=\"ResumeGameReq\"><mainReelsConfig><reel id=\"1\">12,12,12,12,10,10,3,8,8,8,3,10,10,10,12,12,12,12,5,5,5,8,3,8,8,10,3,10,10,10,12,12,12,6,6,6,10,5,5,12,12,12,12,12,9,9,4,7,7,7,3,6,6,6,9,9,3,8,8,2,10,10,6,6,6,4,10,10,5,10,5,3,5,5,5,7,7,7,4,6,6,3,3,3,3,5,4,8,8,8,2,8,8,3,5,5,3,9,9,4</reel><reel id=\"2\">12,12,12,12,6,6,3,9,9,8,8,3,3,3,3,7,7,11,9,9,12,12,12,12,4,10,10,3,5,5,3,9,9,11,7,7,12,12,12,12,4,10,10,2,5,5,12,12,12,12,3,5,5,9,11,7,7,7,12,12,12,12,5,5,5,3,7,7,11,9,9,12,12,12,12,12,7,7,7,7,6,6,6,9,9,9,11,7,7,6,6,4,8,8,2,9,6,6</reel><reel id=\"3\">2,10,10,11,9,9,9,1,9,3,6,6,6,3,10,10,10,11,11,11,9,9,3,5,5,5,3,4,4,4,4,8,1,8,8,11,7,11,7,6,6,6,8,8,8,4,4,4,8,8,8,11,11,11,7,7,5,5,5,7,7,1,10,10,10,10,9,9,9,3,2,6,6,3,6,6,7,1,7,5,5,3,4,4,3,4,6,3,6,6,7,1,7,5,5,5,5,6,5,6</reel><reel id=\"4\">12,12,12,12,6,6,3,9,9,8,8,3,3,3,3,7,7,11,9,9,12,12,12,12,4,10,10,3,5,5,3,9,9,11,7,7,12,12,12,12,4,10,10,2,5,5,12,12,12,12,3,5,5,9,11,7,7,7,12,12,12,12,5,5,5,3,7,7,11,9,9,12,12,12,12,12,7,7,7,7,6,6,6,9,9,9,11,7,7,6,6,4,8,8,2,9,6,6</reel><reel id=\"5\">12,12,12,12,10,10,3,8,8,8,3,10,10,10,12,12,12,12,5,5,5,8,3,8,8,10,3,10,10,10,12,12,12,6,6,6,10,5,5,12,12,12,12,12,9,9,4,7,7,7,3,6,6,6,9,9,3,8,8,2,10,10,6,6,6,4,10,10,5,10,5,3,5,5,5,7,7,7,4,6,6,3,3,3,3,5,4,8,8,8,2,8,8,3,5,5,3,9,9,4</reel></mainReelsConfig><freeSpinReelsConfig><reel id=\"1\">12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,6,6,8,8,3,3,8,3,8,6,6,5,5,8,8,8,8,10,10,10,5,5,5,9,9,4,7,7,10,7,10,6,5,6,5,5,3,3,10,10,2,10,10,6,6,3,4,3,6,8,6,5,5,5,3,5,3,7,7,7,4,6,6,6,3,3,5,4,8,8,8,2,5,5,3,10,3,6,6,4</reel><reel id=\"2\">12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,7,7,10,3,10,9,8,3,3,3,5,5,5,7,6,6,4,8,5,10,5,3,3,9,3,6,6,6,3,4,10,10,2,5,5,3,7,6,6,6,3,5,5,9,9,5,9,5,5,3,9,9,7,6,7,3,6,6,9,3,9,9,3,7,7,7,4,8,8,8,2,9,9,6,6</reel><reel id=\"3\">2,4,4,8,8,1,8,3,3,3,3,8,5,5,9,1,9,9,6,6,7,3,3,3,3,3,6,6,4,6,5,7,7,1,9,9,5,5,6,4,6,8,8,1,10,10,4,10,1,9,9,10,10,7,7,9,9,9,1,10,10,6,6,3,3,3,3,8,8,4,2,5,5,7,7,7,1,7,5,5,5,3,3,3,3,3,6,6,6,8,8,1,7,7,5,5,5,10,10,10</reel><reel id=\"4\">12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,7,7,10,3,10,9,8,3,3,3,5,5,5,7,6,6,4,8,5,10,5,3,3,9,3,6,6,6,3,4,10,10,2,5,5,3,7,6,6,6,3,5,5,9,9,5,9,5,5,3,9,9,7,6,7,3,6,6,9,3,9,9,3,7,7,7,4,8,8,8,2,9,9,6,6</reel><reel id=\"5\">12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,6,6,8,8,3,3,8,3,8,6,6,5,5,8,8,8,8,10,10,10,5,5,5,9,9,4,7,7,10,7,10,6,5,6,5,5,3,3,10,10,2,10,10,6,6,3,4,3,6,8,6,5,5,5,3,5,3,7,7,7,4,6,6,6,3,3,5,4,8,8,8,2,5,5,3,10,3,6,6,4</reel></freeSpinReelsConfig><reels><reel id=\"1\" pos=\"0\">1,1,1,1</reel><reel id=\"2\" pos=\"0\">1,1,1,1</reel><reel id=\"3\" pos=\"0\">1,1,1,1</reel><reel id=\"4\" pos=\"0\">1,1,1,1</reel><reel id=\"5\" pos=\"0\">1,1,1,1</reel><rePattern>1,7,7</rePattern></reels><lines won=\"200\"><line id=\"3\" won=\"100\" icon=\"1\" positions=\"1,3;2,3;3,3;4,3;5,3\" /><line id=\"4\" won=\"100\" icon=\"1\" positions=\"1,1;2,2;3,3;4,2;5,1\" /></lines><creditResults wagered=\"250\" won=\"300\" /><initialWager coinValue=\"0.01\" creditWager=\"5\" /><freeSpins total=\"0\" used=\"0\" won=\"0\" /><baseGameStops>1,1,1,1,1</baseGameStops></msgdata></responseToClient></gameResponse></gdkResponse>"));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}