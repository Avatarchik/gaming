/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.utils;

import java.io.InputStream;

public class ResourceLoader {

    /**
     * Method will retrieve the classLoader and attempt to load the specified
     * resource.
     *
     * @param  resource The resource to be loaded
     * @return byte[] that will represent the the read resource specified.
     */
    public byte[] getResourceAsBytes(final String resource) {
        byte[]      byteResource = null;
        InputStream inputStream  = null;
        try {
            inputStream  = this.getResourceInputStream(resource);
            byteResource = new byte[inputStream.available()];
            // Read in the resource file
            inputStream.read(byteResource);
        } catch (Throwable t) {
            throw new RuntimeException("Error loading resource file(" + resource + "). Cause: " + t, t);
        } finally {
            try { inputStream.close(); } catch (Exception e) {}
        }
        return byteResource;
    }

    /**
     * Method will retrieve the classLoader and attempt to load the specified
     * resource.
     *
     * @param  resource    The resource to be loaded
     * @return InputStream will be returned for the resource
     */
    public InputStream getResourceInputStream(final String resource) {
        InputStream inputStream  = null;
        ClassLoader classLoader  = null;
        try {
            classLoader  = this.getClass().getClassLoader();
            inputStream  = classLoader.getResourceAsStream(resource);
        } catch (Throwable t) {
            throw new RuntimeException("Error loading resource file(" + resource + "). Cause: " + t, t);
        }
        return inputStream;
    }
}
