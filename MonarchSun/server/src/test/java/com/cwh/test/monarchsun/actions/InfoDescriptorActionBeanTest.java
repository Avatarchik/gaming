/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.monarchsun.actions;

import com.cwh.monarchsun.actions.InfoDescriptorActionBean;
import net.sourceforge.stripes.mock.MockRoundtrip;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfoDescriptorActionBeanTest extends BaseActionBeanTestMock {

    @Test
    public void testProcessInfoGuideRequest() {
        try {
            // Process the Action
            MockRoundtrip mockTrip = new MockRoundtrip(this.mockContext, "/get_game_descriptor");
            mockTrip.execute();

            InfoDescriptorActionBean actionBean = mockTrip.getActionBean(InfoDescriptorActionBean.class);
            assertNotNull(actionBean);

            // Just looking for a String in the GameDescriptor.xml. Lazy way so I don't have to parse the XML.
            String actionResponse = new String(actionBean.getContentResponse());
            assertTrue(actionResponse.contains("gameId=\"724\""));
            assertTrue(actionResponse.contains("displayGameName=\"Monarch Sun\""));
            // Ensure that the charset is properly set
            assertTrue("text/xml;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }
}