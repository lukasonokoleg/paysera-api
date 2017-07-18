package eu.paysera.api.server.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.paysera.api.bean.PayseraResponseHandler;
import eu.paysera.api.bean.impl.PayseraClientAcceptResponseImpl;
import eu.paysera.api.bean.impl.PayseraClientCancelResponseImpl;
import eu.paysera.api.helper.PayseraClassHelper;
import eu.paysera.api.helper.PayseraStringHelper;

@SuppressWarnings("serial")
public class PayseraClientResponseServlet extends BasePayseraServlet {

    public static final String ACCEPT_CODE = "accept";
    public static final String CANCEL_CODE = "cancel";

    protected void processDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer requestUrlBuffer = request.getRequestURL();
        String requestUrl = requestUrlBuffer.toString();

        if (!PayseraStringHelper.isEmpty(requestUrl)) {
            if (requestUrl.contains("accept")) {
                onPayseraClientAcceptAction(request, response);
            } else if (requestUrl.contains("cancel")) {
                onPayseraClientCancelAction(request, response);
            } else {
                onRequestNotRecognizedAction(request, response);
            }
        } else {
            onRequestNotRecognizedAction(request, response);
        }
    }

    protected void onPayseraClientAcceptAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PayseraClientAcceptResponseImpl payseraResponse = (PayseraClientAcceptResponseImpl) PayseraClassHelper.createClassInstance(PayseraClientAcceptResponseImpl.class);
        populateBasePayseraActionImpl(payseraResponse, request, true);
        PayseraResponseHandler responseHandler = this.settingsProvider.getPayseraResponseHanlder(request.getParameterMap());
        String redirectUrl = responseHandler.handleClientAcceptResponseAndGetRedirectUrl(payseraResponse);
        response.sendRedirect(redirectUrl);
    }

    protected void onPayseraClientCancelAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PayseraClientCancelResponseImpl payseraResponse = (PayseraClientCancelResponseImpl) PayseraClassHelper.createClassInstance(PayseraClientCancelResponseImpl.class);
        populateBasePayseraActionImpl(payseraResponse, request, false);
        PayseraResponseHandler responseHandler = this.settingsProvider.getPayseraResponseHanlder(request.getParameterMap());
        String redirectUrl = responseHandler.handleClientCancelResponseAndGetRedirectUrl(payseraResponse);
        response.sendRedirect(redirectUrl);
    }

    protected void onRequestNotRecognizedAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(400, "Server could not understand the request");
    }

    public void log(String msg) {
        LOGGER.info(msg);
    }

    public void log(String message, Throwable t) {
        LOGGER.error(message, t);
    }
}
