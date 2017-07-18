package eu.paysera.api.bean.impl;

import eu.paysera.api.bean.BasePayseraResponse;
import eu.paysera.api.bean.PayseraResponseParameter;
import eu.paysera.api.bean.PayseraSettingsProvider;
import eu.paysera.api.helper.PayseraExceptionHelper;
import eu.paysera.api.helper.PayseraResponseStatusHelper;
import eu.paysera.api.helper.PayseraStringHelper;
import eu.paysera.api.helper.PayseraUrlhelper;

import java.util.Map;
import org.apache.log4j.Logger;

public class BasePayseraResponseImpl implements BasePayseraResponse {

    private Logger LOGGER = Logger.getLogger(BasePayseraResponseImpl.class);

    private Map<String, String[]> httpQuery;

    private String data;

    private String ss1;

    private String ss2;

    private String paymentId;

    private Map<String, String> paramMap;

    public Map<String, String[]> getHttpQuery() {
        return this.httpQuery;
    }

    public Map<String, String> getParamMap() {
        return this.paramMap;
    }

    public String getData() {
        return this.data;
    }

    public String getSs1() {
        return this.ss1;
    }

    public String getSs2() {
        return this.ss2;
    }

    public void setHttpQuery(Map<String, String[]> httpQuery) {
        this.httpQuery = httpQuery;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSs1(String ss1) {
        this.ss1 = ss1;
    }

    public void setSs2(String ss2) {
        this.ss2 = ss2;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public String getParameter(PayseraResponseParameter property) {
        String retVal = null;
        if ((this.paramMap != null) && (property != null)) {
            String propertyCode = property.getCode();
            if ((propertyCode != null) && (!propertyCode.isEmpty()) && (this.paramMap.containsKey(propertyCode))) {
                retVal = (String) this.paramMap.get(propertyCode);
            }
        }

        return retVal;
    }

    public Double getDoubleParameter(PayseraResponseParameter property) {
        String retValAsString = getParameter(property);
        Double retVal = null;
        if (retValAsString != null) {
            try {
                retVal = Double.valueOf(retValAsString);
            } catch (NumberFormatException e) {
                String message = " NumberFormatException -> origin value: " + retValAsString;
                this.LOGGER.error(message, e);
            }
        }
        return retVal;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void populateBasePayseraResponseImpl(Map<String, String[]> httpQuery, PayseraSettingsProvider settingsProvider, boolean parseData) {
        setHttpQuery(httpQuery);

        String data = PayseraUrlhelper.getData(httpQuery);
        String ss1 = PayseraUrlhelper.getSs1(httpQuery);
        String ss2 = PayseraUrlhelper.getSs2(httpQuery);

        String paymentId = PayseraUrlhelper.getId(httpQuery);
        String password = settingsProvider.getProjectPassword(paymentId);

        setData(data);
        setSs1(ss1);
        setSs2(ss2);
        setPaymentId(paymentId);
        if (parseData) {
            setParamMap(parseParamMap(this, password));
        }
    }

    protected Map<String, String> parseParamMap(BasePayseraResponseImpl response, String password) {
        Map<String, String> retVal = null;
        if (response != null) {
            String data = response.getData();
            String ss1 = response.getSs1();
            String ss2 = response.getSs2();
            if (!PayseraStringHelper.isEmpty(data)) {
                validateSs1(ss1, data, password);
                validateSs2(ss2, data, password);
                retVal = PayseraUrlhelper.getParameters(data);
            }
        }
        return retVal;
    }

    protected void validateSs1(String ss1, String data, String password) {
        boolean valid = PayseraUrlhelper.isSs1ParameterValid(ss1, data, password);
        if (!valid) {
            String message = "Sign ss1 has invalid value.";
            PayseraExceptionHelper.throwRuntimeException(this.LOGGER, message);
        }
    }

    protected void validateSs2(String ss2, String data, String password) {
        boolean valid = PayseraUrlhelper.isSs2ParameterValid(ss2, data, password);
        if (!valid) {
            String message = "Sign ss2 has invalid value.";
            PayseraExceptionHelper.throwRuntimeException(this.LOGGER, message);
        }
    }

    public boolean isConfirmationResponse() {
        boolean retVal = hasPaymentResponseStatus(PayseraResponseStatusHelper.CONFIRMED_RESPONSE);
        return retVal;
    }

    public boolean isRegistrationResponse() {
        boolean retVal = hasPaymentResponseStatus(PayseraResponseStatusHelper.REGISTERED_RESPONSE);
        return retVal;
    }

    public boolean isAdditionalInfoResponse() {
        boolean retVal = hasPaymentResponseStatus(PayseraResponseStatusHelper.ADDITIONAL_INFO_RESPONSE);
        return retVal;
    }

    public boolean isCancelResponse() {
        boolean retVal = hasPaymentResponseStatus(PayseraResponseStatusHelper.CANCEL_RESPONSE);
        return retVal;
    }

    private boolean hasPaymentResponseStatus(String statusCode) {
        boolean retVal = false;
        if (!PayseraStringHelper.isEmpty(statusCode)) {
            String statusParameterValue = getParameter(PayseraResponseParameter._12_STATUS);
            retVal = statusCode.equals(statusParameterValue);
        }
        return retVal;
    }
}
