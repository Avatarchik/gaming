package com.cwh.monarchsun.actions;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import nu.xom.*;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: NCraig
 * Date: 3/21/14
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */

@UrlBinding("/validate_profile_parameters")
public class ValidateProfileParametersActionBean extends BaseActionBean
{
    public final static String FILE_PATH = "com/cwh/monarchsun/resources/profile/error/";
    public final static String FILE_EXTENSION = ".xml";
    public final static String TAG_ELEM_NAME = "name";
    public final static String TAG_ELEM_VALUE = "value";
    public final static String TAG_ATTR_STATUS = "status";
    public final static String TAG_ELEM_ERRORS = "errors";
    public final static String TAG_STATUS_ERROR = "validationErrors";
    public final static String TAG_STATUS_OK = "ok";
    public final static String TAG_ELEM_PARAM = "param";
    public final static String TAG_ELEM_PARAMATTRIBUTE = "paramAttribute";
    public final static String TAG_ATTR_COINVALUE = "coinValue";
    public final static String TAG_ATTR_LEVELVALUE = "level";
    public final static String TAG_ATTR_MINBETVALUE = "minBet";
    public final static String TAG_ATTR_MAXBETVALUE = "maxBet";
    public final static String TAG_ATTR_AUTOSPINVALUE = "autoSpinValue";

    public final static String ERROR_ID_INVALID = "INVALID";
    public final static String ERROR_ID_INVALIDAUTOSPIN = "INVALIDAUTOSPIN";
    public final static String ERROR_ID_TOOMANYDECIMALS = "TOOMANYDECIMALS";
    public final static String ERROR_ID_EMPTY = "EMPTY";
    public final static String ERROR_ID_DUPLICATE = "DUPLICATE";
    private static final List<String> ALLOWABLE_RTP_STRING = Arrays.asList("1", "2", "3");

    private String lang = "en";  //default
    private String info = null;
    private Element errorsXML = null;
    private final static Logger logger = Logger.getLogger(ValidateProfileParametersActionBean.class);

    public void setProfileParams(String profileParams) {
        this.info = profileParams;
    }

    public void setLang(String lang){
        this.lang = lang;
    }

    @DefaultHandler
    public Resolution validateProfileParams() {
        try {
            Element requestBodyEle = convertStringToElement(info);
            logger.debug("validate_profile_parameters: request = " + requestBodyEle.toXML());

            String coinValuesError = null;
            String reformattedCoinValue = null;

            String levelValuesError = null;
            String levelValueStr = null;

            String minBetValuesError = null;
            String minBetValueStr = null;

            String maxBetValuesError = null;
            String maxBetValueStr = null;

            String autoSpinValuesError = null;
            String reformattedAutoSpin = null;


            // Validate Coin Values ==========================================
            try
            {
                String coinValueStr = extractInputCoinValuesFromRequest(requestBodyEle);
                reformattedCoinValue = validateAndReformat(coinValueStr);
            } catch (Exception e) {    // in case of exception, we will construct an error response and send back
                coinValuesError = e.getMessage();
            }
            //================================================================

            // Validate Level ==========================================
            try
            {
                levelValueStr = ExtractProfileLevel(requestBodyEle);
            }
            catch (Exception e)
            {
                levelValuesError = e.getMessage();
            }
            //================================================================

            // Validate Min Bet ==========================================
            try
            {
                minBetValueStr = validateMoney(requestBodyEle, TAG_ATTR_MINBETVALUE);
            }
            catch (Exception e)
            {
                minBetValuesError = e.getMessage();
            }
            //================================================================

            // Validate Max Bet ==========================================
            try
            {
                maxBetValueStr = validateMoney(requestBodyEle, TAG_ATTR_MAXBETVALUE);
            }
            catch (Exception e)
            {
                maxBetValuesError = e.getMessage();
            }
            //================================================================

            // Validate AutoSpin Values ==========================================
            try
            {
                String autoSpinValueStr = extractInputAutoSpinValuesFromRequest(requestBodyEle);
                reformattedAutoSpin = validateAndReformatAutoSpinValues(autoSpinValueStr);
            } catch (Exception e) {    // in case of exception, we will construct an error response and send back
                autoSpinValuesError = e.getMessage();
            }
            //==========================================================

            Element response = new Element("response");
            if (coinValuesError != null || levelValuesError != null || minBetValuesError != null || maxBetValuesError != null)
            {
                Element xmlElement = new Element(TAG_ELEM_ERRORS);

                if (coinValuesError != null)
                {
                    Element current = new Element(TAG_ELEM_PARAMATTRIBUTE);
                    current.addAttribute(new Attribute(TAG_ELEM_NAME, TAG_ATTR_COINVALUE));
                    current.appendChild(coinValuesError);
                    xmlElement.appendChild(current);
                    response.addAttribute(new Attribute(TAG_ATTR_STATUS, TAG_STATUS_ERROR));
                }

                if (autoSpinValuesError != null)
                {
                    Element current = new Element(TAG_ELEM_PARAMATTRIBUTE);
                    current.addAttribute(new Attribute(TAG_ELEM_NAME, TAG_ATTR_AUTOSPINVALUE));
                    current.appendChild(autoSpinValuesError);
                    xmlElement.appendChild(current);
                    response.addAttribute(new Attribute(TAG_ATTR_STATUS, TAG_STATUS_ERROR));
                }

                if (levelValuesError != null)
                {
                    Element current = new Element(TAG_ELEM_PARAMATTRIBUTE);
                    current.addAttribute(new Attribute(TAG_ELEM_NAME, TAG_ATTR_LEVELVALUE));
                    current.appendChild(levelValuesError);
                    xmlElement.appendChild(current);
                    response.addAttribute(new Attribute(TAG_ATTR_STATUS, TAG_STATUS_ERROR));
                }

                if (minBetValuesError != null)
                {
                    Element current = new Element(TAG_ELEM_PARAMATTRIBUTE);
                    current.addAttribute(new Attribute(TAG_ELEM_NAME, TAG_ATTR_MINBETVALUE));
                    current.appendChild(minBetValuesError);
                    xmlElement.appendChild(current);
                    response.addAttribute(new Attribute(TAG_ATTR_STATUS, TAG_STATUS_ERROR));
                }

                if (maxBetValuesError != null)
                {
                    Element current = new Element(TAG_ELEM_PARAMATTRIBUTE);
                    current.addAttribute(new Attribute(TAG_ELEM_NAME, TAG_ATTR_MAXBETVALUE));
                    current.appendChild(maxBetValuesError);
                    xmlElement.appendChild(current);
                    response.addAttribute(new Attribute(TAG_ATTR_STATUS, TAG_STATUS_ERROR));
                }

                response.appendChild(xmlElement);
            } else
            {
                response.addAttribute(new Attribute(TAG_ATTR_STATUS, TAG_STATUS_OK));

                Element paramEle = new Element(TAG_ELEM_PARAM);
                paramEle.addAttribute(new Attribute(TAG_ATTR_COINVALUE, reformattedCoinValue));
                paramEle.addAttribute(new Attribute(TAG_ATTR_LEVELVALUE, levelValueStr));
                paramEle.addAttribute(new Attribute(TAG_ATTR_MINBETVALUE, minBetValueStr));
                paramEle.addAttribute(new Attribute(TAG_ATTR_MAXBETVALUE, maxBetValueStr));
                paramEle.addAttribute(new Attribute(TAG_ATTR_AUTOSPINVALUE, reformattedAutoSpin));
                response.appendChild(paramEle);
            }

            logger.debug("validate_profile_parameters: Response = " + response.toXML());
            setContentResponse(response.toXML().getBytes());
        } catch (Throwable t) {
            throw new RuntimeException("Error in profile validator: " + t, t);
        }
        return this.getResolution();
    }

    private int RetrieveFirstChildElementIndex(Elements elements, String elementName)
    {
        int valueIndex = -1;
        for (int paramAttributeCounter = 0; paramAttributeCounter < elements.size(); paramAttributeCounter++)
        {
            if (elements.get(paramAttributeCounter).getAttribute(TAG_ELEM_NAME).getValue().equals(elementName))
            {
                valueIndex = paramAttributeCounter;
                break;
            }
        }

        return valueIndex;
    }

    private String validateMoney(Element requestElem, String targetAttribute) throws Exception 
    {
        Elements paramAttributes = requestElem.getChildElements(TAG_ELEM_PARAMATTRIBUTE);
        int valueIndex = RetrieveFirstChildElementIndex(paramAttributes, targetAttribute);

        if ( valueIndex == -1)
        {
            throw new Exception("invalid profile param -- no value in REQUEST=" + requestElem.toXML());
        }

        Elements value = paramAttributes.get( valueIndex).getChildElements(TAG_ELEM_VALUE);
        if (value == null || value.size() != 1) {
            throw new Exception("invalid profile param -- not exactly 1 value in REQUEST=" + requestElem.toXML());
        }

        String trimValue = value.get(0).getValue().trim();

        BigDecimal coin;
        try {
            coin = new BigDecimal(trimValue);
        } catch (Exception e) {
            throw new Exception(getErrorMsg(ERROR_ID_INVALID, trimValue));
        }

        if (coin.scale() > 2) {
            throw new Exception(getErrorMsg(ERROR_ID_TOOMANYDECIMALS, trimValue));
        }
        if (coin.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception(getErrorMsg(ERROR_ID_INVALID, trimValue));
        }

        return trimValue;
    }

    private String ExtractProfileLevel(Element requestElem) throws Exception
    {
        Elements paramAttributes = requestElem.getChildElements(TAG_ELEM_PARAMATTRIBUTE);
        int levelValueParameterIndex = RetrieveFirstChildElementIndex(paramAttributes, TAG_ATTR_LEVELVALUE);

        if (levelValueParameterIndex == -1)
        {
            throw new Exception("invalid profile param -- no Level Value in REQUEST=" + requestElem.toXML());
        }

        Elements value = paramAttributes.get(levelValueParameterIndex).getChildElements(TAG_ELEM_VALUE);
        if (value == null || value.size() != 1) {
            throw new Exception("invalid profile param -- not exactly 1 Level Value in REQUEST=" + requestElem.toXML());
        }

        String trimValue = value.get(0).getValue().trim();

        if (!ALLOWABLE_RTP_STRING.contains(trimValue))
        {
            throw new Exception("invalid profile param -- " + trimValue + "is not a valid level REQUEST=" + requestElem.toXML());
        }

        return trimValue;
    }
    public String extractInputAutoSpinValuesFromRequest(Element requestElem) throws Exception
    {
        Elements paramAttributes = requestElem.getChildElements(TAG_ELEM_PARAMATTRIBUTE);
        int autoSpinValueParameterIndex = RetrieveFirstChildElementIndex(paramAttributes, TAG_ATTR_AUTOSPINVALUE);

        if (autoSpinValueParameterIndex == -1)
        {
            throw new Exception("invalid profile param -- no AutoSpin Values in REQUEST=" + requestElem.toXML());
        }

        Elements value = paramAttributes.get(autoSpinValueParameterIndex).getChildElements(TAG_ELEM_VALUE);
        if (value == null || value.size() != 1) {
            throw new Exception("invalid profile param -- not exactly 1 value in REQUEST=" + requestElem.toXML());
        }

        return value.get(0).getValue();
    }

    public String validateAndReformatAutoSpinValues(String autoSpinValueList) throws Exception {
        StringTokenizer valuesList = new StringTokenizer(autoSpinValueList, ",");
        if (valuesList == null || valuesList.countTokens() < 1) {
            throw new Exception(getErrorMsg(ERROR_ID_EMPTY, "no autospin value found"));
        }

        if(valuesList.countTokens() > 10) {
            throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, "more than 10 autospin values found"));
        }

        HashSet<String> validatedAutoSpinList = new HashSet<String>();
        StringBuffer reformattedAutoSpinValueString = new StringBuffer("");
        int pastValue = 0;
        do {
            String value = valuesList.nextToken().trim();
            Integer autoSpins;
            BigDecimal autoSpinsDecimal;
            try {
                autoSpins = new Integer(value);
                autoSpinsDecimal = new BigDecimal(value);
            } catch (Exception e) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, value));
            }

            if(autoSpinsDecimal.compareTo(new BigDecimal(autoSpins)) != 0) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, value));
            }

            if (autoSpins >= 1000) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, value));
            }
            if (autoSpins <= 0) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, value));
            }
            if(autoSpins < pastValue) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, "values must be ordered in ascending order"));
            }
            pastValue = autoSpins;

            if (!reformattedAutoSpinValueString.toString().equals("")) {  // append a comma delimiter unless it's the first autoSpins value in the list
                reformattedAutoSpinValueString.append(",");
            }

            reformattedAutoSpinValueString.append(value);

            // check to make sure no repeated autoSpin value -- we can achieve that by checking on the formatted coin value which would be normalized to the same presentation if the value is the same.
            if (!validatedAutoSpinList.add(value)) {  // if add method failed, it's an indication of repeated value
                throw new Exception(getErrorMsg(ERROR_ID_INVALIDAUTOSPIN, value));
            }

            logger.debug("newly formatted autoSpin value string: " + reformattedAutoSpinValueString);
        } while (valuesList.hasMoreTokens());

        return reformattedAutoSpinValueString.toString();
    }


    public String extractInputCoinValuesFromRequest(Element requestElem) throws Exception
    {
        Elements paramAttributes = requestElem.getChildElements(TAG_ELEM_PARAMATTRIBUTE);
        int coinValueParameterIndex = RetrieveFirstChildElementIndex(paramAttributes, TAG_ATTR_COINVALUE);

        if (coinValueParameterIndex == -1)
        {
            throw new Exception("invalid profile param -- no Coin Values in REQUEST=" + requestElem.toXML());
        }

        Elements value = paramAttributes.get(coinValueParameterIndex).getChildElements(TAG_ELEM_VALUE);
        if (value == null || value.size() != 1) {
            throw new Exception("invalid profile param -- not exactly 1 value in REQUEST=" + requestElem.toXML());
        }

        return value.get(0).getValue();
    }

    public String validateAndReformat(String coinValueList) throws Exception {
        StringTokenizer valuesList = new StringTokenizer(coinValueList, ",");
        if (valuesList == null || valuesList.countTokens() < 1) {
            throw new Exception(getErrorMsg(ERROR_ID_EMPTY, "no coin value found"));
        }

        HashSet<String> validatedCoinsList = new HashSet<String>();
        StringBuffer reformattedCoinValueString = new StringBuffer("");
        do {
            String value = valuesList.nextToken().trim();
            BigDecimal coin;
            try {
                coin = new BigDecimal(value);
            } catch (Exception e) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALID, value));
            }

            if (coin.scale() > 2) {
                throw new Exception(getErrorMsg(ERROR_ID_TOOMANYDECIMALS, value));
            }
            if (coin.compareTo(BigDecimal.ZERO) <= 0) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALID, value));
            }

            // this coin value is valid, so now reformat it to fixed to 2 decimal places and add to the resulting string:
            if (!reformattedCoinValueString.toString().equals("")) {  // append a comma delimiter unless it's the first coin in the list
                reformattedCoinValueString.append(",");
            }

            BigDecimal formattedCoin;
            try {
                formattedCoin = coin.setScale(2, RoundingMode.UNNECESSARY);
                reformattedCoinValueString.append(formattedCoin.toPlainString());  // RoundingMode.UNNECESSARY here serves as a double check to make sure the value is indeed less than 2 decimal places
            } catch (Exception e) {
                throw new Exception(getErrorMsg(ERROR_ID_INVALID, value));
            }

            // check to make sure no repeated coin value -- we can achieve that by checking on the formatted coin value which would be normalized to the same presentation if the value is the same.
            if (!validatedCoinsList.add(formattedCoin.toPlainString())) {  // if add method failed, it's an indication of repeated value
                throw new Exception(getErrorMsg(ERROR_ID_DUPLICATE, value));
            }

            logger.debug("newly formatted coinvalue string: " + reformattedCoinValueString);
        } while (valuesList.hasMoreTokens());

        return reformattedCoinValueString.toString();
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


    public String getErrorMsg(String errCode, String coin) {
        String finalErrMsg = null;
        String errMsg = "";

        if (errorsXML == null) {
            errorsXML = getErrorsXML();  // need only get once
        }

        Elements allEntries = errorsXML.getChildElements("error");
        for (int i = 0; i < allEntries.size(); i++) {
            if (allEntries.get(i).getAttributeValue("id").equalsIgnoreCase(errCode)) {
                errMsg = allEntries.get(i).getValue().trim();
            }
        }
        finalErrMsg = errMsg + "[" + coin + "]";
        return finalErrMsg;
    }

    public Element getErrorsXML() {
        InputStream input;
        Document doc;

        try {
            final Class<ValidateProfileParametersActionBean> beanClass = ValidateProfileParametersActionBean.class;
            final ClassLoader loader = beanClass.getClassLoader();
            final String resource = FILE_PATH + lang + FILE_EXTENSION;
            input = loader.getResourceAsStream(resource);
            byte[] fileData = new byte[input.available()];
            input.read(fileData);
            String err = new String(fileData, "UTF-8");
            InputStream is = new ByteArrayInputStream(err.getBytes());
            Builder parser = new Builder();
            doc = parser.build(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct the error message: " + e, e);
        }
        return doc.getRootElement();
    }
}