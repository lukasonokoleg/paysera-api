package eu.paysera.api.helper;

import eu.paysera.api.bean.BasePayseraResponse;
import eu.paysera.api.bean.PayseraResponseParameter;

public class PayseraResponseAmountHelper {

    private static int CENT_DIVISOR = 100;

    public static Double getAmount(BasePayseraResponse response) {
        Double retVal = getAmountAsCents(response);
        if (retVal != null) {
            retVal = Double.valueOf(retVal.doubleValue() / CENT_DIVISOR);
        }
        return retVal;
    }

    public static Double getAmountAsCents(BasePayseraResponse response) {
        Double retVal = null;

        if (response != null) {
            retVal = response.getDoubleParameter(PayseraResponseParameter._05_AMOUNT);
        }
        return retVal;
    }
}
