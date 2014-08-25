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

public class InitGameRequestActionBeanTest extends BaseActionBeanTestMock {
    private static final ResourceLoader rLoader    = new ResourceLoader();
    private static final String         gdkRequest = new String(rLoader.getResourceAsBytes("com/cwh/sample/resources/gdkRequest_init.xml"));

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
            assertTrue(sResponse.contains("<infoOnly />"));
            assertTrue(sResponse.contains("<msgdata reqName=\"InitReq\"><stars total=\"10\" /></msgdata>"));
            assertTrue(sResponse.contains("<persistentState><persistentContext><numberSeven count=\"10\" /></persistentContext></persistentState>"));
            
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}