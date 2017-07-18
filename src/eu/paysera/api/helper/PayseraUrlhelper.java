package eu.paysera.api.helper;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

public class PayseraUrlhelper {

    private static final Logger LOGGER = Logger.getLogger(PayseraUrlhelper.class);

    public static final String URL_BASE = "https://www.paysera.com/pay/";

    public static final String URL_PARAM_ID = "id";
    public static final String URL_PARAM_DATA = "data";
    public static final String URL_PARAM_SIGN = "sign";
    public static final String URL_PARAM_SS1 = "ss1";
    public static final String URL_PARAM_SS2 = "ss2";
    private static final char SYMBOL_SLASH = '/';
    private static final char SYMBOL_UNDERSCORE = '_';
    private static final char SYMBOL_PLUS = '+';
    private static final char SYMBOL_MINUS = '-';
    private static final char SYMBOL_QUESTION = '?';
    private static final String SYMBOL_EQUALS = "=";
    private static final String SYMBOL_AND = "&";
    private static final String DEFAULT_ENCODING = "UTF-8";

    public static String getEncodedDataParameter(Map<String, String> parameters) {
        String dataParam = formatDataParameter(parameters);
        String retVal = encodeDataParameter(dataParam);
        return retVal;
    }

    public static Map<String, String> getParameters(String encodedData) {
        Map<String, String> retVal = null;
        if (!PayseraStringHelper.isEmpty(encodedData)) {
            String decodedData = decodeDataParameter(encodedData);
            retVal = parseDecodedDataParameter(decodedData);
        }
        return retVal;
    }

    public static boolean isSs1ParameterValid(String ss1, String data, String password) {
        boolean retVal = false;
        if (ss1 != null) {
            String ssiEthalon = constructSS1Ethalon(data, password);
            retVal = ss1.equals(ssiEthalon);
        }
        return retVal;
    }

    public static boolean isSs2ParameterValid(String ss2, String data, String password) {
        boolean retVal = true;
        return retVal;
    }

    private static String constructSS1Ethalon(String data, String password) {
        String retVal = null;
        if ((data != null) && (password != null)) {
            retVal = PayseraMD5HashHelper.hash(data, password);
        }
        return retVal;
    }

    private static Map<String, String> parseDecodedDataParameter(String data) {
        Map<String, String> retVal = new LinkedHashMap();
        if (data != null) {
            String[] propertiesAsStrings = data.split("&");
            if (propertiesAsStrings != null) {
                String[] arrayOfString1;
                int j = (arrayOfString1 = propertiesAsStrings).length;
                for (int i = 0; i < j; i++) {
                    String propertyAsString = arrayOfString1[i];
                    if (propertyAsString != null) {
                        String[] propAtrs = propertyAsString.split("=");
                        if ((propAtrs != null) && (propAtrs.length > 1)) {
                            String key = propAtrs[0];
                            String value = propAtrs[1];
                            if ((key != null) && (!key.isEmpty()) && (value != null)) {
                                retVal.put(key, value);
                            }
                        }
                    }
                }
            }
        }
        return retVal;
    }

    private static String formatDataParameter(Map<String, String> parameters) {
        StringBuilder builder = new StringBuilder();
        for (String key : parameters.keySet()) {
            String value = (String) parameters.get(key);
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static String encodeDataParameter(String decodedData) {
        String retVal = null;
        if (!PayseraStringHelper.isEmpty(decodedData)) {
            String encodedUrlParams = "";
            try {
                byte[] bytes = decodedData.getBytes("UTF-8");
                encodedUrlParams = DatatypeConverter.printBase64Binary(bytes);
            } catch (UnsupportedEncodingException e) {
                String message = "Cought UnsupportedEncodingException.";
                PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
            }
            retVal = encodedUrlParams.replace('/', '_').replace('+', '-');
        }

        return retVal;
    }

    private static String decodeDataParameter(String encodedData) {
        String retVal = null;
        if (!PayseraStringHelper.isEmpty(encodedData)) {
            retVal = encodedData.replace('-', '+').replace('_', '/');
            byte[] dataBytes = DatatypeConverter.parseBase64Binary(retVal);
            try {
                retVal = new String(dataBytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                String message = "Cought UnsupportedEncodingException.";
                PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
            }
        }
        return retVal;
    }

    public static String getSs1(Map<String, String[]> httpQuery) {
        String retVal = null;
        if ((httpQuery != null) && (httpQuery.containsKey("ss1"))) {
            retVal = PayseraArraysHelper.getFirst((String[]) httpQuery.get("ss1"));
        }
        return retVal;
    }

    public static String getSs2(Map<String, String[]> httpQuery) {
        String retVal = null;
        if ((httpQuery != null) && (httpQuery.containsKey("ss2"))) {
            retVal = PayseraArraysHelper.getFirst((String[]) httpQuery.get("ss2"));
        }
        return retVal;
    }

    public static String getId(Map<String, String[]> httpQuery) {
        String retVal = null;
        if ((httpQuery != null) && (httpQuery.containsKey("id"))) {
            String[] retValAsArray = (String[]) httpQuery.get("id");
            retVal = PayseraArraysHelper.getFirst(retValAsArray);
        }
        return retVal;
    }

    public static String getData(Map<String, String[]> httpQuery) {
        String retVal = null;
        if ((httpQuery != null) && (httpQuery.containsKey("data"))) {
            retVal = PayseraArraysHelper.getFirst((String[]) httpQuery.get("data"));
        }
        return retVal;
    }

    public static String constructUrl(String baseUrl, Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        String retVal = null;
        if ((!PayseraStringHelper.isEmpty(baseUrl)) && (params != null)) {
            builder.append(baseUrl);
            builder.append('?');
            for (String key : params.keySet()) {
                String value = (String) params.get(key);
                if ((!PayseraStringHelper.isEmpty(key)) && (!PayseraStringHelper.isEmpty(value))) {
                    builder.append(key);
                    builder.append("=");
                    builder.append(value);
                    builder.append("&");
                }
            }
            retVal = builder.toString();
            if (retVal.endsWith("&")) {
                retVal = retVal.substring(0, retVal.length() - 1);
            }
        }
        return retVal;
    }
}
