/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.monarchsun.utils;

import com.cwh.monarchsun.utils.ThreadSession;
import org.junit.Test;

import static junit.framework.Assert.*;

public class ThreadSessionTest {
    
//    @Test
//    public void testThreadSessionCleared() {
//        try {
//            ThreadSession tSession = ThreadSession.getCurrentSession();
//            assertNotNull(tSession);
//            String shouldNotExist = tSession.get(ThreadSession.KEYS.CURR_CODE);
//            assertNull(shouldNotExist);
//
//        } catch(Throwable t) {
//            fail();
//        }
//    }

    @Test
    public void testThreadSessionSetWithValues() {
        try {
            ThreadSession tSession = ThreadSession.getCurrentSession();
            assertNotNull(tSession);
            tSession.add(ThreadSession.KEYS.CURR_CODE, "someValue");

            String shouldExist = tSession.get(ThreadSession.KEYS.CURR_CODE);
            assertNotNull(shouldExist);
            assertTrue("someValue".equals(shouldExist));

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

//    @Test
//    public void testThreadSessionPreReset() {
//        try {
//            ThreadSession tSession = ThreadSession.getCurrentSession();
//            assertNotNull(tSession);
//
//            String shouldExist = tSession.get(ThreadSession.KEYS.CURR_CODE);
//            assertNotNull(shouldExist);
//            assertTrue("someValue".equals(shouldExist));
//
//            tSession.reset();
//            String shouldNotExist = tSession.get(ThreadSession.KEYS.CURR_CODE);
//            assertNull(shouldNotExist);
//        } catch(Throwable t) {
//            fail();
//        }
//    }

    @Test
    public void testThreadSessionReset() {
        try {
            ThreadSession tSession = ThreadSession.getCurrentSession();
            assertNotNull(tSession);

            tSession.reset();
            String shouldNotExist = tSession.get(ThreadSession.KEYS.CURR_CODE);
            assertNull(shouldNotExist);
        } catch(Throwable t) {
            fail();
        }
    }
}