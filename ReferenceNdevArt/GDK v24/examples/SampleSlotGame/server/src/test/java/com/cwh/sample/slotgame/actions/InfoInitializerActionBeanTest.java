/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.actions;

import net.sourceforge.stripes.mock.MockRoundtrip;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfoInitializerActionBeanTest extends BaseActionBeanTestMock {

    @Test
    public void testProcessInfoInitializeRequest() {
        try {
            // Process the Action
            MockRoundtrip mockTrip = new MockRoundtrip(this.mockContext, "/initialize_game");
            mockTrip.execute();

            InfoInitializerActionBean actionBean = mockTrip.getActionBean(InfoInitializerActionBean.class);
            assertNotNull(actionBean);

            // Just looking for a String in the GameInitializer.xml. Lazy way so I don't have to parse the XML.
            String actionResponse = new String(actionBean.getContentResponse());
            assertTrue(actionResponse.contains("pool id=\"POOL_0_14\" size=\"5\" min=\"0\" max=\"14\""));
            // Ensure that the charset is properly set
            assertTrue("text/xml;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }
}