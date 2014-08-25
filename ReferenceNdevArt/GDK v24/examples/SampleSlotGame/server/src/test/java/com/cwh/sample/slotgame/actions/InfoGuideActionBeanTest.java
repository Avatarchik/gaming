/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.actions;

import net.sourceforge.stripes.mock.MockRoundtrip;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfoGuideActionBeanTest extends BaseActionBeanTestMock {

    @Ignore
    @Test
    public void testProcessInfoGuideRequest() {
        try {
            // Process the Action
            MockRoundtrip mockTrip = new MockRoundtrip(this.mockContext, "/gaming_guide");
            mockTrip.setParameter("lang", "en");
            mockTrip.execute();

            InfoGuideActionBean actionBean = mockTrip.getActionBean(InfoGuideActionBean.class);
            assertNotNull(actionBean);

            // Assert that Lang was read properly
            assertTrue("en".equalsIgnoreCase(actionBean.getLang()));
            // Assert the About Info retrieved is correct
            String actionResponse = new String(actionBean.getContentResponse());
            // Just looking for a String in the GameAboutInfo.xml. Lazy way so I don't have to parse the XML.
            assertTrue(actionResponse.contains("Blah Blah Blah Gaming Guide Blah Blah Blah"));
            // Ensure that the charset is properly set
            assertTrue("text/html;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }

    @Ignore
    @Test
    public void testProcessInfoGuideRequest_differentLang() {
        try {
            // Process the Action
            MockRoundtrip mockTrip = new MockRoundtrip(this.mockContext, "/gaming_guide");
            mockTrip.setParameter("lang", "cn");
            mockTrip.execute();

            InfoGuideActionBean actionBean = mockTrip.getActionBean(InfoGuideActionBean.class);
            assertNotNull(actionBean);

            // Assert that Lang was read properly
            assertTrue("cn".equalsIgnoreCase(actionBean.getLang()));
            // Assert the About Info retrieved is correct
            String actionResponse = new String(actionBean.getContentResponse());
            // Just looking for a String in the GameAboutInfo.xml. Lazy way so I don't have to parse the XML.
            assertTrue(actionResponse.contains("Blah Blah Blah Gaming Guide Blah Blah Blah"));
            // Ensure that the charset is properly set
            assertTrue("text/html;charset=gb2312".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }

    @Ignore
    @Test
    public void testProcessInfoGuideRequest_nonExistingLang() {
        try {
            // Process the Action
            MockRoundtrip mockTrip = new MockRoundtrip(this.mockContext, "/gaming_guide");
            mockTrip.setParameter("lang", "zz");
            mockTrip.execute();

            InfoGuideActionBean actionBean = mockTrip.getActionBean(InfoGuideActionBean.class);
            assertNotNull(actionBean);

            // Assert that Lang was read properly
            assertTrue("en".equalsIgnoreCase(actionBean.getLang()));
            // Assert the About Info retrieved is correct
            String actionResponse = new String(actionBean.getContentResponse());
            // Just looking for a String in the GameAboutInfo.xml. Lazy way so I don't have to parse the XML.
            assertTrue(actionResponse.contains("Blah Blah Blah Gaming Guide Blah Blah Blah"));
            // Ensure that the charset is properly set
            assertTrue("text/html;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }
}