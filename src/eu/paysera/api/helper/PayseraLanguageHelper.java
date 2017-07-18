package eu.paysera.api.helper;

import java.util.ArrayList;
import java.util.List;

public class PayseraLanguageHelper {

    private static final String LANG_LIT_KEY_VALUE = "LIT";
    private static final String LANG_LAT_KEY_VALUE = "LAT";
    private static final String LANG_EST_KEY_VALUE = "EST";
    private static final String LANG_RUS_KEY_VALUE = "RUS";
    private static final String LANG_ENG_KEY_VALUE = "ENG";
    private static final String LANG_GER_KEY_VALUE = "GER";
    private static final String LANG_POL_KEY_VALUE = "POL";

    public static List<String> getValues() {
        List<String> retVal = new ArrayList<>();
        retVal.add(LANG_LIT_KEY_VALUE);
        retVal.add(LANG_LAT_KEY_VALUE);
        retVal.add(LANG_EST_KEY_VALUE);
        retVal.add(LANG_RUS_KEY_VALUE);
        retVal.add(LANG_ENG_KEY_VALUE);
        retVal.add(LANG_GER_KEY_VALUE);
        retVal.add(LANG_POL_KEY_VALUE);
        return retVal;
    }
}
