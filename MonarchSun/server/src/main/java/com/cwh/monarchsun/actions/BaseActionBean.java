/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.monarchsun.actions;

import  com.cwh.monarchsun.resolution.ResponseResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;

abstract class BaseActionBean implements ActionBean {
    protected final Logger logger;

    private ActionBeanContext context = null;

    private String contentType     = "text/xml;charset=utf-8";
    private byte[] contentRequest  = null;
    private byte[] contentResponse = null;

    public BaseActionBean() {
        this.logger = Logger.getLogger(this.getClass());
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContentResponse() {
        return contentResponse;
    }

    public void setContentResponse(byte[] contentResponse) {
        this.contentResponse = contentResponse;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public ActionBeanContext getContext() {
        return this.context;
    }

    /**
     * This method will read the incoming data post from the request.
     * @return byte[] The byte array that represents the request data. This may be null.
     */
    protected byte[] getContentRequest() {
        final byte[]                buffer    = new byte[256];
        final ByteArrayOutputStream baoStream = new ByteArrayOutputStream(256);
        BufferedInputStream         biStream  = null;
        Integer                     length    = null;

        try {
            // Only parse the request, if this request has not been parsed already
            if(this.contentRequest == null) {
                biStream = new BufferedInputStream(this.context.getRequest().getInputStream());
                // Loop thru and see whats to be read in from the request
                while((length = biStream.read(buffer)) > 0) {
                    baoStream.write(buffer, 0, length);
                }
                // Once everything is read from the input stream, set the ContentRequest
                this.contentRequest = baoStream.toByteArray();
            }
        } catch(Throwable t) {
            throw new RuntimeException("Unable to parse the content from the request: " + t, t);
        } finally {
            try { biStream.close(); } catch(Throwable t) {}
        }
        this.logger.debug("[ContentRequest]: " + new String(this.contentRequest));
        return this.contentRequest;
    }

    /**
     * This method will return a Stripes resolution that will have the content type and response set.
     * This is the last step in the process of the gameRequest.
     * @return Resolution. A completed resolution that is ready to respond to the FlashClient.
     */
    protected Resolution getResolution() {
        try {
            this.logger.debug("[ContentResponse]: " + new String(this.contentResponse));
            return new ResponseResolution(this.getContentType(), this.getContentResponse());
        } catch(Throwable t) {
            throw new RuntimeException("Unable to create a Resolution: " + t, t);
        }
    }
}