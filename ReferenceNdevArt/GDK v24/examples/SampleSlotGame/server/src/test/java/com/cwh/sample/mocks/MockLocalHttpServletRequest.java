/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.mocks;

import net.sourceforge.stripes.mock.MockHttpServletRequest;

import javax.servlet.ServletInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * This class is a mock that will allow us to feed the stripes frameworth with a POST request.
 * 
 */
public class MockLocalHttpServletRequest extends MockHttpServletRequest {
    private byte[] mockRequestData = null;

    public MockLocalHttpServletRequest(String contextPath, String servletPath) {
        super(contextPath, servletPath);
    }

    public void setMockRequestData(String requestData) {
       this.mockRequestData = requestData.getBytes();
    }

    public byte[] getMockRequestData() {
        return mockRequestData;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // Generate a new ServletInputStream
        return new MockServletInputStream(this.mockRequestData);
    }

    private class MockServletInputStream extends ServletInputStream {
        private BufferedInputStream bis = null;

        private MockServletInputStream(byte[] mockRequestData) {
            this.bis = new BufferedInputStream(new ByteArrayInputStream(mockRequestData));
        }

        @Override
        public int read() throws IOException {
            return this.bis.read();
        }

        @Override
        public int readLine(byte[] buffer, int offset, int length) throws IOException {
            return this.bis.read(buffer, offset, length);
        }
    }
}