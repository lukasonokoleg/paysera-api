package eu.paysera.api.helper;

import java.util.ArrayList;
import java.util.List;

public class PayseraCurrencyHelper {

    private static final String CURRENCY_LTL_KEY_VALUE = "LTL";
    private static final String CURRENCY_EUR_KEY_VALUE = "EUR";
    private static final String CURRENCY_USD_KEY_VALUE = "USD";

    public static List<String> getValues() {
        List<String> retVal = new ArrayList<>();
        retVal.add(CURRENCY_LTL_KEY_VALUE);
        retVal.add(CURRENCY_EUR_KEY_VALUE);
        retVal.add(CURRENCY_USD_KEY_VALUE);
        return retVal;
    }
}