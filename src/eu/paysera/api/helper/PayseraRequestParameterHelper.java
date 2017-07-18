package eu.paysera.api.helper;

import java.util.List;
import org.apache.log4j.Logger;

import eu.paysera.api.bean.PayseraRequest;
import eu.paysera.api.bean.PayseraRequestParameter;

public class PayseraRequestParameterHelper {

    private static final Logger LOGGER = Logger.getLogger(PayseraRequestParameterHelper.class);

    public static void setParameter(PayseraRequest request, PayseraRequestParameter paramKey, String value) {
        validate(paramKey, value);
        request.setParameter(paramKey, value);
    }

    public static void validate(PayseraRequestParameter paramKey, String value) {
        if (!hasValueInValidRange(paramKey, value)) {
            String message = "PayseraRequestParameter (" + paramKey + ") value (" + value + ") has invalid value.";
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
        }
        if (!hasValidLength(paramKey, value)) {
            String message = "PayseraRequestParameter (" + paramKey + ") value (" + value + ") has invalid length.";
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
        }
    }

    public static boolean hasValidLength(PayseraRequestParameter paramKey, String value) {
        boolean retVal = false;
        if ((value != null) && (paramKey != null)) {
            int allowedLen = paramKey.getAllowedLen();
            int valueLen = value.length();
            if (valueLen <= allowedLen) {
                retVal = true;
            }
        }
        return retVal;
    }

    public static boolean hasValueInValidRange(PayseraRequestParameter paramKey, String value) {
        boolean retVal = false;
        if ((paramKey != null) && (value != null)) {
            List<String> allowedValueRange = paramKey.getValueRange();
            if (allowedValueRange == null) {
                retVal = true;
            } else {
                retVal = allowedValueRange.contains(value);
            }
        }
        return retVal;
    }
}