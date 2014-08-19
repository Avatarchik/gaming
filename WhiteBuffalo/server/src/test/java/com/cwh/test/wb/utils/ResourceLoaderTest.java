/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.wb.utils;

import com.cwh.wb.utils.ResourceLoader;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.Charset;

import static junit.framework.Assert.*;

public class ResourceLoaderTest {
    @Test
    public void getResourceAsBytesTest() {
        ResourceLoader loader = new ResourceLoader();
        byte[] resourceBytes = loader.getResourceAsBytes("com/cwh/test/wb/resources/sampleResource.xml");

        assertNotNull(resourceBytes);
        assertTrue(resourceBytes.length > 0);

        String resourceString = new String(resourceBytes, Charset.forName("UTF-8"));
        assertTrue(resourceString.contains("Some Sample File"));
    }

    @Test
    public void getResourceAsBytesTest_NotFound() {
        try {
            ResourceLoader loader = new ResourceLoader();

            byte[] resourceBytes = loader.getResourceAsBytes("com/cwh/test/wb/resources/sampleResourceNotFound.xml");
            fail();
        } catch(Throwable t) {
            assertTrue(true);
        }
    }

    @Test
    public void getResourceInputStream_NotFound() {
        try {
            ResourceLoader loader = new ResourceLoader();

            InputStream resourceStream = loader.getResourceInputStream("com/cwh/test/wb/resources/sampleResourceNotFound.xml");
            fail();
        } catch(Throwable t) {
            assertTrue(true);
        }
    }
}
