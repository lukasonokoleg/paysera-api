package eu.paysera.api.helper;

public class PayseraArraysHelper {

    public static String getFirst(String[] dataAsArray) {
        String retVal = null;
        if ((dataAsArray != null) && (dataAsArray.length > 0)) {
            retVal = dataAsArray[0];
        }
        return retVal;
    }
}
