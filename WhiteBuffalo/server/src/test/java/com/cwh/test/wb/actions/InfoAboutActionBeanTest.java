/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.wb.actions;

import com.cwh.wb.actions.InfoAboutActionBean;
import net.sourceforge.stripes.mock.MockRoundtrip;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertTrue;

public class InfoAboutActionBeanTest extends BaseActionBeanTestMock {

    @Test
    public void testProcessInfoAboutRequest() {
        try {
            // Process the Action
            MockRoundtrip mockTrip = new MockRoundtrip(this.mockContext, "/about_info");
            mockTrip.setParameter("level", "1");
            mockTrip.execute();

            InfoAboutActionBean actionBean = mockTrip.getActionBean(InfoAboutActionBean.class);
            assertNotNull(actionBean);

            // Assert that Level was read properly
            assertTrue("1".equalsIgnoreCase(actionBean.getLevel()));
            // Assert the About Info retrieved is correct
            String actionResponse = new String(actionBean.getContentResponse());
            // Just looking for a String in the GameAboutInfo.xml. Lazy way so I don't have to parse the XML.
            assertTrue(actionResponse.contains("game name=\"WhiteBuffalo\" type=\"0\" desc=\"Legend of the White Buffalo\""));
            assertTrue("text/xml;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}