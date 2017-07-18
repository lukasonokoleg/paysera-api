package eu.paysera.api.helper;

import java.util.ArrayList;
import java.util.List;

public class PayseraTestModeHelper {

    private static final String TEST_ENABLED_KEY_VALUE = "1";
    private static final String TEST_DISENABLED_KEY_VALUE = "0";

    public static List<String> getValues() {
        List<String> retVal = new ArrayList<>();
        retVal.add(TEST_ENABLED_KEY_VALUE);
        retVal.add(TEST_DISENABLED_KEY_VALUE);
        return retVal;
    }
}