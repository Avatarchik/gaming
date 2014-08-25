/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.extensions;

import com.cwh.sample.slotgame.resolution.ResponseResolution;
import mockit.Deencapsulation;
import net.sourceforge.stripes.mock.MockHttpServletRequest;
import net.sourceforge.stripes.mock.MockHttpServletResponse;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GameExceptionHandlerTest {
    @Test
    public void testHandleException() {
        try {
            final GameExceptionHandler    handler      = new GameExceptionHandler();
            final MockHttpServletRequest  mockRequest  = new MockHttpServletRequest("", "");
            final MockHttpServletResponse mockResponse = new MockHttpServletResponse();
            final RuntimeException        aException   = new RuntimeException("ForcedException");

            ResponseResolution aResolution = (ResponseResolution)handler.handleGameException(aException, mockRequest, mockResponse);
            final String contentType  = Deencapsulation.getField(aResolution, "contentType");
            final byte[] contentBytes = Deencapsulation.getField(aResolution, "contentResponse");

            // Grab the Response
            final String sResponse = new String(contentBytes, "UTF-8");
            // Check the Response for assertions
            assertTrue(sResponse.contains("ForcedException"));
        } catch(Throwable t) {
            fail();
        }
    }

    @Test
    public void testHandleException_withCause() {
        try {
            final GameExceptionHandler    handler      = new GameExceptionHandler();
            final MockHttpServletRequest  mockRequest  = new MockHttpServletRequest("", "");
            final MockHttpServletResponse mockResponse = new MockHttpServletResponse();
            final RuntimeException        aException   = new RuntimeException("ForcedException");
            final RuntimeException        bException   = new RuntimeException("ForcedExceptionB", aException);

            ResponseResolution aResolution = (ResponseResolution)handler.handleGameException(bException, mockRequest, mockResponse);
            final String contentType  = Deencapsulation.getField(aResolution, "contentType");
            final byte[] contentBytes = Deencapsulation.getField(aResolution, "contentResponse");

            // Grab the Response
            final String sResponse = new String(contentBytes, "UTF-8");
            // Check the Response for assertions
            assertTrue(sResponse.contains("ForcedException"));
            assertTrue(sResponse.contains("ForcedExceptionB"));
        } catch(Throwable t) {
            fail();
        }
    }
}