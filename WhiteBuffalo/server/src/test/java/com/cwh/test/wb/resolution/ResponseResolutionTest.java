/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.wb.resolution;

import com.cwh.wb.resolution.ResponseResolution;
import net.sourceforge.stripes.mock.MockHttpServletResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseResolutionTest {
    @Test
    public void testExecuteResolution() {
        try {
            final String contentType     = "text/xml;charset=utf-8";
            final String contentResponse = "Some Response That Should Be Written Out";
            // Setup to call ResponseResolution to test
            MockHttpServletResponse mockResponse = new MockHttpServletResponse();
            ResponseResolution      resolution   = new ResponseResolution(contentType, contentResponse.getBytes());
            // Execute the resolution
            resolution.execute(null, mockResponse);
            // Grab the contentType from the response
            String resolutionContentType = mockResponse.getContentType();
            assertNotNull(resolutionContentType);
            assertTrue(resolutionContentType.length() > 0);
            assertTrue(contentType.equals(resolutionContentType));
            // Grab the content from the response
            String resolutionResponse = mockResponse.getOutputString();
            assertNotNull(resolutionResponse);
            assertTrue(resolutionResponse.length() > 0);
            assertTrue(contentResponse.equals(resolutionResponse));
        } catch(Throwable t) {
            fail();
        }
    }

    @Test
    public void testExecuteResolution_noData() {
        try {
            final String contentType     = "";
            final String contentResponse = "";
            // Setup to call ResponseResolution to test
            MockHttpServletResponse mockResponse = new MockHttpServletResponse();
            ResponseResolution      resolution   = new ResponseResolution(contentType, contentResponse.getBytes());
            // Execute the resolution
            resolution.execute(null, mockResponse);
            // Grab the contentType from the response
            String resolutionContentType = mockResponse.getContentType();
            assertTrue(resolutionContentType.length() > 0);
            assertTrue("text/html".equals(resolutionContentType));
            // Grab the content from the response
            String resolutionResponse = mockResponse.getOutputString();
            assertNotNull(resolutionResponse);
            assertEquals(0, resolutionResponse.length());
        } catch(Throwable t) {
            fail();
        }
    }

    @Test
    public void testExecuteResolution_nullData() {
        try {
            // Setup to call ResponseResolution to test
            MockHttpServletResponse mockResponse = new MockHttpServletResponse();
            ResponseResolution      resolution   = new ResponseResolution(null, null);
            // Execute the resolution
            resolution.execute(null, mockResponse);
            // Grab the contentType from the response
            String resolutionContentType = mockResponse.getContentType();
            assertTrue(resolutionContentType.length() > 0);
            assertTrue("text/html".equals(resolutionContentType));
            // Grab the content from the response
            String resolutionResponse = mockResponse.getOutputString();
            assertNotNull(resolutionResponse);
            assertEquals(0, resolutionResponse.length());
        } catch(Throwable t) {
            fail();
        }
    }
}