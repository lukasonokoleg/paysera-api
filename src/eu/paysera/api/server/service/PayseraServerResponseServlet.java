package eu.paysera.api.server.service;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.paysera.api.bean.PayseraResponseHandler;
import eu.paysera.api.bean.impl.PayseraServerCallbackResponseImpl;

@SuppressWarnings("serial")
public class PayseraServerResponseServlet extends BasePayseraServlet {

    private static final String SUCCESS_RESPONSE = "OK";

    protected void processDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PayseraServerCallbackResponseImpl payseraResponse = new PayseraServerCallbackResponseImpl();
        populateBasePayseraActionImpl(payseraResponse, request, true);
        PayseraResponseHandler responseHandler = this.settingsProvider.getPayseraResponseHanlder(request.getParameterMap());
        responseHandler.handleServerCallbackResponse(payseraResponse);
        sendSuccesResponse(response);
    }

    protected void sendSuccesResponse(HttpServletResponse response) throws IOException {
        Writer writer = response.getWriter();
        writer.write(SUCCESS_RESPONSE);
        writer.flush();
    }
}
