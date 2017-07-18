package eu.paysera.api.bean;

public abstract interface PayseraResponseHandler {

    public abstract String handleClientAcceptResponseAndGetRedirectUrl(PayseraClientAcceptResponse paramPayseraClientAcceptResponse);

    public abstract String handleClientCancelResponseAndGetRedirectUrl(PayseraClientCancelResponse paramPayseraClientCancelResponse);

    public abstract void handleServerCallbackResponse(PayseraServerCallbackResponse paramPayseraServerCallbackResponse);
}
