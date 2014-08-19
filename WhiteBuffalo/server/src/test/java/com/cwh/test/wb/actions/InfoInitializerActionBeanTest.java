/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.wb.actions;

import com.cwh.wb.actions.InfoInitializerActionBean;
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
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel1_1_at_0_129\" size=\"1\" min=\"0\" max=\"129\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel2_1_at_0_199\" size=\"1\" min=\"0\" max=\"199\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel3_1_at_0_199\" size=\"1\" min=\"0\" max=\"199\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel4_1_at_0_199\" size=\"1\" min=\"0\" max=\"199\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel5_1_at_0_149\" size=\"1\" min=\"0\" max=\"149\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReelRE_1_at_0_499\" size=\"1\" min=\"0\" max=\"499\""));

            assertTrue(actionResponse.contains("pool id=\"freeSpinReel1_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel2_1_at_0_199\" size=\"1\" min=\"0\" max=\"199\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel3_1_at_0_199\" size=\"1\" min=\"0\" max=\"199\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel4_1_at_0_199\" size=\"1\" min=\"0\" max=\"199\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel5_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReelRE_1_at_0_399\" size=\"1\" min=\"0\" max=\"399\""));

            // Ensure that the charset is properly set
            assertTrue("text/xml;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }
}