package com.cwh.wb.actions;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import nu.xom.*;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@UrlBinding("/get_profile_parameters")
public class GetProfileParametersActionBean extends BaseActionBean {
    private static final String FILEPATH = "com/cwh/wb/resources/profile/profileParams/";
    private String lang = "en";
    private String info = null;
    private final static Logger logger = Logger.getLogger(GetProfileParametersActionBean.class);

    private static final Map<String, String> LANG_CHARSET = new HashMap<String, String>() {{
        this.put("en", "utf-8");
    }};

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setParam(String param) {
        this.info = param;
    }

    @DefaultHandler
    public Resolution getProfileParams() {
        try {
            String response = loadProfileParameters();
            setContentResponse(response.getBytes());
        } catch (Throwable t) {
            throw new RuntimeException("Error loading game profile parameters: " + t, t);
        }
        return this.getResolution();
    }

    private String loadProfileParameters() {
        String descriptor = null;

        if (info == null) {
            InputStream input = null;
            try {
                final Class<GetProfileParametersActionBean> beanClass = GetProfileParametersActionBean.class;
                final ClassLoader loader = beanClass.getClassLoader();
                final String resource = FILEPATH + lang + ".xml";
                input = loader.getResourceAsStream(resource);
                byte[] fileData = new byte[input.available()];
                input.read(fileData);
                descriptor = new String(fileData, LANG_CHARSET.get(this.lang));
            } catch (Throwable t) {
                throw new RuntimeException("Error loading profile parameters. Cause: " + t, t);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    // Ignored on purpose because there's nothing more I can do here.
                }
            }
        } else {
            Element requestBodyEle = convertStringToElement(info);
            descriptor = loadProfileParameters(requestBodyEle);
        }
        return descriptor;
    }

    private String loadProfileParameters(Element requestBodyEle) {
        String response = null;
        InputStream input = null;
        try {
            final Class<GetProfileParametersActionBean> beanClass = GetProfileParametersActionBean.class;
            final ClassLoader loader = beanClass.getClassLoader();
            final String resource = FILEPATH + lang + ".xml";
            input = loader.getResourceAsStream(resource);
            byte[] fileData = new byte[input.available()];
            input.read(fileData);
            String descriptor = new String(fileData, LANG_CHARSET.get(this.lang));
            Element profileParametersEle = convertStringToElement(descriptor);
            response = copyOverValues(requestBodyEle, profileParametersEle);

        } catch (Throwable t) {
            throw new RuntimeException("Error loading profile parameters. Cause: " + t, t);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                // Ignored on purpose because there's nothing more I can do here.
            }
        }
        return response;
    }

    public String copyOverValues(Element requestBodyEle, Element profileParametersEle) {
        String response = null;
        logger.debug("copyOverValues: requestBodyEle=" + requestBodyEle.toXML() + ", profileParametersEle=" + profileParametersEle.toXML());

        for (int i = 0; i < requestBodyEle.getAttributeCount(); i++) {
            Attribute profileParam = requestBodyEle.getAttribute(i);
            String profileParamId = profileParam.getQualifiedName();
            String profileParamValue = profileParam.getValue();
            response = insertIntoProfile(profileParamId, profileParamValue, profileParametersEle);
        }
        return response;
    }

    public String insertIntoProfile(String profileParamId, String profileParamValue, Element profileParametersEle) {
        String response = null;

        logger.debug("insertIntoProfile: profileParametersEle=" + profileParametersEle.toXML() + ", profileParamId=" + profileParamId + ", profileParamValue=" + profileParamValue);
        for (int i = 0; i < profileParametersEle.getChildElements().size(); i++) {
            Element param = profileParametersEle.getChildElements().get(i);
            if (param.getAttributeValue("name").equalsIgnoreCase(profileParamId)) {
                param.getFirstChildElement("value").appendChild(profileParamValue);
            }
        }
        response = profileParametersEle.toXML();
        return response;
    }

    public Element convertStringToElement(String req) {
        InputStream is = new ByteArrayInputStream(req.getBytes());
        Builder parser = new Builder();
        Document doc;

        try {
            doc = parser.build(is);
        } catch (ParsingException e) {
            throw new RuntimeException("Parsing Exception: " + e, e);
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e, e);
        }
        Element e = doc.getRootElement();
        return e;
    }
}
