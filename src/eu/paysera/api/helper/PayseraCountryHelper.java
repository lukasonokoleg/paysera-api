package eu.paysera.api.helper;

import java.util.ArrayList;
import java.util.List;

public class PayseraCountryHelper {

    private static final String COUNTRY_LT_KEY_VALUE = "LT";
    private static final String COUNTRY_EE_KEY_VALUE = "EE";
    private static final String COUNTRY_LV_KEY_VALUE = "LV";
    private static final String COUNTRY_GB_KEY_VALUE = "GB";
    private static final String COUNTRY_PL_KEY_VALUE = "PL";
    private static final String COUNTRY_DE_KEY_VALUE = "DE";

    public static List<String> getValues() {
        List<String> retVal = new ArrayList<>();
        retVal.add(COUNTRY_LT_KEY_VALUE);
        retVal.add(COUNTRY_EE_KEY_VALUE);
        retVal.add(COUNTRY_LV_KEY_VALUE);
        retVal.add(COUNTRY_GB_KEY_VALUE);
        retVal.add(COUNTRY_PL_KEY_VALUE);
        retVal.add(COUNTRY_DE_KEY_VALUE);
        return retVal;
    }
}