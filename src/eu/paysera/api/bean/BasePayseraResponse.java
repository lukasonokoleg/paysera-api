package eu.paysera.api.bean;

import java.util.Map;

public abstract interface BasePayseraResponse
{
  public abstract Map<String, String> getParamMap();
  
  public abstract String getParameter(PayseraResponseParameter paramPayseraResponseParameter);
  
  public abstract Double getDoubleParameter(PayseraResponseParameter paramPayseraResponseParameter);
  
  public abstract Map<String, String[]> getHttpQuery();
  
  public abstract String getData();
  
  public abstract String getSs1();
  
  public abstract String getSs2();
  
  public abstract String getPaymentId();
  
  public abstract void populateBasePayseraResponseImpl(Map<String, String[]> paramMap, PayseraSettingsProvider paramPayseraSettingsProvider, boolean paramBoolean);
  
  public abstract boolean isConfirmationResponse();
  
  public abstract boolean isRegistrationResponse();
  
  public abstract boolean isAdditionalInfoResponse();
  
  public abstract boolean isCancelResponse();
}


/* Location:              C:\Projects\ALCS\Paysera\spark-paysera.jar!\eu\itreegroup\paysera\bean\BasePayseraResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */