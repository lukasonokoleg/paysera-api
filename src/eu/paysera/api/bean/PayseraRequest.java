package eu.paysera.api.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import eu.paysera.api.helper.PayseraMD5HashHelper;
import eu.paysera.api.helper.PayseraRequestHelper;
import eu.paysera.api.helper.PayseraUrlhelper;

@SuppressWarnings("serial")
public class PayseraRequest implements Serializable {

    private Map<String, String> paramMap;
    private String paymentId;
    private String password;

    public String buildRequestUrl() {
        updateUrl(this, PayseraRequestParameter.ACCEPTURL_KEY);
        updateUrl(this, PayseraRequestParameter.CANCELURL_KEY);
        updateUrl(this, PayseraRequestParameter.CALLBACKURL_KEY);

        String retVal = getPayseraRequestUrl(this);
        return retVal;
    }

    private static String getPayseraRequestUrl(PayseraRequest request) {
        String data = buildData(request);
        String sign = buildSign(data, request.getPassword());

        Map<String, String> params = new HashMap();
        params.put("data", data);
        params.put("sign", sign);

        String retVal = PayseraUrlhelper.constructUrl("https://www.paysera.com/pay/", params);
        return retVal;
    }

    private static String buildData(PayseraRequest request) {
        PayseraRequestHelper.validate(request);
        String retVal = PayseraUrlhelper.getEncodedDataParameter(request.getParamMap());
        return retVal;
    }

    private static String buildSign(String data, String password) {
        String retVal = PayseraMD5HashHelper.hash(data, password);
        return retVal;
    }

    private static void updateUrl(PayseraRequest request, PayseraRequestParameter parameter) {
        if (request != null) {
            String acceptUrl = request.getParameter(parameter);
            String paymentId = request.getPaymentId();
            Map<String, String> params = new HashMap<>();
            params.put("id", paymentId);
            String newAcceptUrl = PayseraUrlhelper.constructUrl(acceptUrl, params);
            request.setParameter(parameter, newAcceptUrl);
        }
    }

    public Map<String, String> getParamMap() {
        return this.paramMap;
    }

    public void setParameter(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("Input parameter key is NULL!");
        }
        if (value == null) {
            throw new IllegalArgumentException("Input parameter key is NULL!");
        }
        if (!PayseraRequestParameter.hasProperty(key)) {
            throw new IllegalArgumentException("Key " + key + " is not defined within MokLtProperties enum!");
        }
        this.paramMap.put(key, value);
    }

    public void setParameter(String key, Double value) {
        if (this.paramMap != null) {
            String valueAsString = null;
            if (value != null) {
                valueAsString = String.valueOf(value);
                this.paramMap.put(key, valueAsString);
            }
        }
    }

    public void setParameter(PayseraRequestParameter key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("Input parameter property is NULL!");
        }
        setParameter(key.getCode(), value);
    }

    public String getParameter(PayseraRequestParameter key) {
        String retVal = null;
        if (key != null) {
            String keyAsString = key.getCode();
            retVal = getParameter(keyAsString);
        }
        return retVal;
    }

    public String getParameter(String key) {
        String retVal = null;
        if ((this.paramMap != null) && (this.paramMap.containsKey(key))) {
            retVal = (String) this.paramMap.get(key);
        }
        return retVal;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "PayseraRequest [paramMap=" + this.paramMap + ", paymentId=" + this.paymentId + ", password=" + this.password + "]";
    }
}
