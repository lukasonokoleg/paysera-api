package eu.paysera.api.helper;

import javax.servlet.ServletException;
import org.apache.log4j.Logger;

public class PayseraExceptionHelper {

    public static void throwRuntimeException(Logger logger, String message) {
        logger.error(message);
        throw new RuntimeException(message);
    }

    public static void throwRuntimeException(Logger logger, String message, Throwable e) {
        logger.error(message, e);
        throw new RuntimeException(message, e);
    }

    public static void throwServletException(Logger logger, String message) throws ServletException {
        logger.error(message);
        throw new ServletException(message);
    }

    public static void throwServletException(Logger logger, String message, Throwable e) throws ServletException {
        logger.error(message, e);
        throw new ServletException(message, e);
    }

    public static void ensureObjectIsNotNull(Logger logger, String message, Object object) {
        if (object == null) {
            throwRuntimeException(logger, message);
        }
    }
}