package eu.paysera.api.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.paysera.api.bean.PayseraRequest;
import eu.paysera.api.bean.PayseraRequestParameter;

public class PayseraRequestHelper {

    private static final Logger LOGGER = Logger.getLogger(PayseraRequestHelper.class);

    public static void validate(PayseraRequest request) {
        ensureHasRequiredParameters(request.getParamMap());
        if (request != null) {
            Map<String, String> properties = request.getParamMap();
            for (String key : properties.keySet()) {
                String propValue = (String) properties.get(key);
                PayseraRequestParameter property = PayseraRequestParameter.getValueByCode(key);
                PayseraRequestParameterHelper.validate(property, propValue);
            }
        }
    }

    private static void ensureHasRequiredParameters(Map<String, String> properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Specified input variable properties is NULL!");
        }
        List<PayseraRequestParameter> requiredProperties = PayseraRequestParameter.getRequiredProperties();
        List<String> missingProperties = new ArrayList();
        for (PayseraRequestParameter property : requiredProperties) {
            String propertyCode = property.getCode();
            if (!properties.containsKey(propertyCode)) {
                missingProperties.add("Properties have missing required property: " + propertyCode);
            }
        }
        if (!missingProperties.isEmpty()) {
            String message = "Missed these required properties: " + Arrays.toString(missingProperties.toArray());
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
        }
    }
}