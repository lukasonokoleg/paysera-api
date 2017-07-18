package eu.paysera.api.bean;

import java.util.ArrayList;
import java.util.List;

import eu.paysera.api.helper.PayseraCountryHelper;
import eu.paysera.api.helper.PayseraCurrencyHelper;
import eu.paysera.api.helper.PayseraLanguageHelper;
import eu.paysera.api.helper.PayseraTestModeHelper;
import eu.paysera.api.helper.PayseraVersionHelper;

public enum PayseraRequestParameter {
    PROJECT_ID("projectid", true, 11, null), //
    ORDER_ID_KEY("orderid", false, 40, null), //
    ACCEPTURL_KEY("accepturl", true, 255, null), //
    CANCELURL_KEY("cancelurl", true, 255, null), //
    CALLBACKURL_KEY("callbackurl", true, 255, null), //
    VERSION_KEY("version", true, 9, PayseraVersionHelper.getValues()), //
    _07_LANGUAGE_KEY("lang", false, 3, PayseraLanguageHelper.getValues()), //
    AMOUNT_KEY("amount", false, 11, null), //
    CURRENCY_KEY("currency", false, 3, PayseraCurrencyHelper.getValues()), //
    PAYMENT_KEY("payment", false, 20, null), //
    COUNTRY_KEY("country", false, 2, PayseraCountryHelper.getValues()), //
    PAYTEXT_KEY("paytext", false, 255, null), //
    FIRSTNAME_KEY("p_firstname", false, 255, null), //
    LASTNAME_KEY("p_lastname", false, 255, null), //
    EMAIL_KEY("p_email", false, 255, null), //
    STREET_KEY("p_street", false, 255, null), CITY_KEY("p_city", false, 255, null), //
    STATE_KEY("p_state", false, 20, null), //
    ZIP_KEY("p_zip", false, 20, null), //
    COUNTRYCODE_KEY("p_countrycode", false, 2, null), //
    PAYMENTS_KEY("only_payments", false, 0, null), //
    DISALOW_PAYMENTS_KEY("disalow_payments", false, 0, null), //
    TEST_KEY("test", false, 1, PayseraTestModeHelper.getValues()), //
    TIME_LIMIT_KEY("time_limit", false, 19, null), //
    PERSONCODE_KEY("personcode", false, 255, null), //
    DEVELOPERID_KEY("developerid", false, 11, null), //
    REPEAT_REQUEST("repeat_request", false, 1, null);

    private final String code;
    private final boolean required;
    private final int allowedLen;
    private final List<String> valueRange;
    public static final String REPEAT_REQUEST_NO = "0";
    public static final String REPEAT_REQUEST_YES = "1";

    private PayseraRequestParameter(String code, boolean required, int allowedLen, List<String> valueRange) {
        this.code = code;
        this.required = required;
        this.allowedLen = allowedLen;
        this.valueRange = valueRange;
    }

    public String getCode() {
        return this.code;
    }

    public int getAllowedLen() {
        return this.allowedLen;
    }

    public List<String> getValueRange() {
        return this.valueRange;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean hasSameCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Input variable code can not be NULL!");
        }
        return code.equals(getCode());
    }

    public static PayseraRequestParameter getValueByCode(String code) {
        PayseraRequestParameter[] arrayOfPayseraRequestParameter;
        int j = (arrayOfPayseraRequestParameter = values()).length;
        for (int i = 0; i < j; i++) {
            PayseraRequestParameter property = arrayOfPayseraRequestParameter[i];
            if (property.hasSameCode(code)) {
                return property;
            }
        }
        return null;
    }

    public static List<PayseraRequestParameter> getRequiredProperties() {
        ArrayList<PayseraRequestParameter> retVal = new ArrayList<>();
        PayseraRequestParameter[] arrayOfPayseraRequestParameter;
        int j = (arrayOfPayseraRequestParameter = values()).length;
        for (int i = 0; i < j; i++) {
            PayseraRequestParameter property = arrayOfPayseraRequestParameter[i];
            if (property.isRequired()) {
                retVal.add(property);
            }
        }
        return retVal;
    }

    public static boolean hasProperty(String code) {
        if (code != null) {
            return getValueByCode(code) != null;
        }
        return false;
    }
}
