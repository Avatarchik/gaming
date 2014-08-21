/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.monarchsun.actions;

import com.cwh.monarchsun.actions.InfoInitializerActionBean;
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

            assertTrue(actionResponse.contains("pool id=\"freeSpinReel1_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel2_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel3_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel4_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReel5_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"freeSpinReelRE_1_at_0_309\" size=\"1\" min=\"0\" max=\"259\""));

            assertTrue(actionResponse.contains("pool id=\"mainSpinReel921_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel922_1_at_0_98\" size=\"1\" min=\"0\" max=\"98\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel923_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel924_1_at_0_98\" size=\"1\" min=\"0\" max=\"98\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel925_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel92RE_1_at_0_98\" size=\"1\" min=\"0\" max=\"98\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel941_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel942_1_at_0_97\" size=\"1\" min=\"0\" max=\"97\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel943_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel944_1_at_0_97\" size=\"1\" min=\"0\" max=\"97\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel945_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel94RE_1_at_0_98\" size=\"1\" min=\"0\" max=\"98\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel951_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel952_1_at_0_97\" size=\"1\" min=\"0\" max=\"97\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel953_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel954_1_at_0_97\" size=\"1\" min=\"0\" max=\"97\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel955_1_at_0_99\" size=\"1\" min=\"0\" max=\"99\""));
            assertTrue(actionResponse.contains("pool id=\"mainSpinReel95RE_1_at_0_98\" size=\"1\" min=\"0\" max=\"98\""));

            // Ensure that the charset is properly set
            assertTrue("text/xml;charset=utf-8".equals(actionBean.getContentType()));
        } catch(Throwable t) {
            fail();
        }
    }
}