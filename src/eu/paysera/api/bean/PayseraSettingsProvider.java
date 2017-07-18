package eu.paysera.api.bean;

import java.util.Map;

public abstract interface PayseraSettingsProvider {

    public abstract String getProjectPassword(String paramString);

    public abstract PayseraResponseHandler getPayseraResponseHanlder(Map<String, String[]> paramMap);
}