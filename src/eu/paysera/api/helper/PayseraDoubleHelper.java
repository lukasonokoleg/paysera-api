package eu.paysera.api.helper;

import java.math.BigDecimal;

public class PayseraDoubleHelper {

    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, 4);
        return bd.doubleValue();
    }
}
