/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.resolution;

import net.sourceforge.stripes.action.Resolution;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseResolution implements Resolution {
    private String contentType     = null;
    private byte[] contentResponse = null;

    public ResponseResolution(String contentType, byte[] contentResponse) {
        this.contentType     = contentType;
        this.contentResponse = contentResponse;
    }

    /**
     * Resolution Method.
     * This method will write the known response content to the rGF system.
     * @link net.sourceforge.stripes.action.Resolution
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream sout = null;
        try {
            // Set the content type, if set
            if(this.contentType != null && this.contentType.length() > 0) {
                response.setContentType(this.contentType);
            }
            // Now write the response out, if set
            if(this.contentResponse != null && this.contentResponse.length > 0) {
                sout = response.getOutputStream();
                sout.write(this.contentResponse);
            }
        } catch(Throwable t) {
            // Log the exception that occurred
            throw new RuntimeException("Unable to execute resolution: " + t, t);
        } finally {
            try { sout.close(); } catch(Throwable t) {}
        }
    }
}