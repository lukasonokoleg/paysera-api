package eu.paysera.api.server.service;

import eu.paysera.api.bean.PayseraSettingsProvider;
import eu.paysera.api.bean.impl.BasePayseraResponseImpl;
import eu.paysera.api.helper.PayseraClassHelper;
import eu.paysera.api.helper.PayseraExceptionHelper;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public abstract class BasePayseraServlet extends HttpServlet {

    protected static final Logger LOGGER = Logger.getLogger(Logger.class);

    public static final String INIT_PARAM_PAYSERA_SETTINGS_PROVIDER = "payseraSettingsProvider";
    protected PayseraSettingsProvider settingsProvider;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initPayseraSettingsProvider();
        validatePayseraSettingsProvider();
    }

    private final void validatePayseraSettingsProvider() {
        if (this.settingsProvider == null) {
            String message = "PayseraSettingsProvider must be initialized.";
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PayseraExceptionHelper.ensureObjectIsNotNull(LOGGER, "Got NULL valued HttpServletRequest request.", request);
        PayseraExceptionHelper.ensureObjectIsNotNull(LOGGER, "Got NULL valued HttpServletRequest request.", response);
        processDoGet(request, response);
    }

    protected void populateBasePayseraActionImpl(BasePayseraResponseImpl actionImpl, HttpServletRequest request, boolean parseData) {
        Map<String, String[]> httpQuery = request.getParameterMap();
        actionImpl.populateBasePayseraResponseImpl(httpQuery, this.settingsProvider, parseData);
    }

    protected void initPayseraSettingsProvider() {
        String payseraResponseHandlerClassName = getServletConfig().getInitParameter("payseraSettingsProvider");
        this.settingsProvider = ((PayseraSettingsProvider) PayseraClassHelper.createClassInstance(payseraResponseHandlerClassName, PayseraSettingsProvider.class));
    }

    protected abstract void processDoGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws IOException;
}
