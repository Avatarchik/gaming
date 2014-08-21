/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.test.monarchsun.actions;

import com.cwh.test.mocks.MockLocalHttpServletRequest;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockHttpServletResponse;
import net.sourceforge.stripes.mock.MockServletContext;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseActionBeanTestMock {
    protected MockServletContext          mockContext  = null;
    protected MockLocalHttpServletRequest mockRequest  = null;
    protected MockHttpServletResponse     mockResponse = null;

    @Before
    public void setupStripesMock() {
        // Setup the Mock Context
        Map<String, String> filterParams = new HashMap<String, String>();
        filterParams.put("ActionResolver.Packages", " com.cwh.monarchsun.actions");
//		filterParams.put("LocalePicker.Locales",    "en");
		filterParams.put("Extension.Packages",      " com.cwh.monarchsun.extensions");
        this.mockContext = new MockServletContext("someContext");
        this.mockContext.addFilter(StripesFilter.class,      "StripesFilter",     filterParams);
        this.mockContext.setServlet(DispatcherServlet.class, "StripesDispatcher", null);
    }
}