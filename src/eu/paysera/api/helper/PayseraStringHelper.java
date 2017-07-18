package eu.paysera.api.helper;

public class PayseraStringHelper {

    public static boolean isEmpty(String value) {
        boolean retVal = (value != null) && (value.isEmpty());
        return retVal;
    }

    public static String toIntegerString(Double value) {
        String retVal = null;
        if (value != null) {
            Double valueRounded = Double.valueOf(PayseraDoubleHelper.round(value.doubleValue(), 0));
            Integer valueAsInteger = Integer.valueOf(valueRounded.intValue());
            retVal = String.valueOf(valueAsInteger);
        }
        return retVal;
    }
}