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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class InitGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/test/monarchsun/resources/gdkRequest_init.xml"));

    @Test
    public void testSpinGameRequest() {
        try {
            this.mockRequest  = new MockLocalHttpServletRequest("/someContext", "/InitReq");
            this.mockResponse = new MockHttpServletResponse();
            this.mockRequest.setMockRequestData(InitGameRequestActionBeanTest.gdkRequest);
            this.mockContext.acceptRequest(mockRequest, mockResponse);

            final String sResponse = this.mockResponse.getOutputString();
            System.out.println(sResponse);

            // This is more of a sanity check. Ensure that the resulting XML should return the following XML(String).
            //Checks if the state is a NEW game
            assertTrue(sResponse.contains("currentPlayState state=\"NEW\""));
            assertTrue(sResponse.contains("currentResult creditWon=\"0\""));
            assertTrue(sResponse.contains("currentFreeFeature totalSpins=\"0\" usedSpins=\"0\" totalWon=\"0\""));

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}