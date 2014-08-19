/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.extensions;

import   com.cwh.wb.model.GdkResponse;
import   com.cwh.wb.resolution.ResponseResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameExceptionHandler extends DefaultExceptionHandler {
    private static final Logger logger = Logger.getLogger(GameExceptionHandler.class);

    /**
     * The method will intercept and handle all exceptions that occur when processing the ActionBeans.
     * It will catched the exception, and generate a StackTrace.
     * The stacktrace will be included into the gdkResponse and the gdkResponse will be returned inplace of
     * a successful gdkResponse.
     *
     * @param  rte      The Exception that was caught.
     * @param  request  The incoming Request that invoked the actionBean.
     * @param  response The outgoing Response to the requestee.
     * @return Resolution The handler that will write the response back.
     */
    public Resolution handleGameException(RuntimeException rte, HttpServletRequest request, HttpServletResponse response) {
        logger.error("Exception caught: " + rte, rte);
        return new ResponseResolution("text/xml", this.generateGdkResponseBytes(rte));
    }

    private byte[] generateGdkResponseBytes(Throwable throwable) {
        final GdkResponse   gdkResponse = new GdkResponse();
        // Now set the String StackTrace into the GdkResponse
        gdkResponse.setGameError(this.generateGdkResponseString(new StringBuilder(), throwable).toString());
        return gdkResponse.marshal().toXML().getBytes();
    }


    private StringBuilder generateGdkResponseString(StringBuilder stackTrace, Throwable throwable) {
        final StringBuilder sStackTrace = stackTrace.append("\n").append(throwable.toString()).append("\n");

        for(StackTraceElement stackElement : throwable.getStackTrace()) {
            sStackTrace.append(stackElement).append("\n");
        }

        // Check for Causes
        if(throwable.getCause() != null) {
            this.generateGdkResponseString(sStackTrace.append("Caused By: "), throwable.getCause());
        }

        return stackTrace;
    }
}