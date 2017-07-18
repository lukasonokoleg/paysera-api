package eu.paysera.api.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

public class PayseraClassHelper {

    private static final Logger LOGGER = Logger.getLogger(PayseraClassHelper.class);

    public static <E> E createClassInstance(Class<E> className) {
        E retVal = null;
        try {
            retVal = className.newInstance();
        } catch (InstantiationException e) {
            String message = "Cought InstantiationException while creating class: " + className.getName();
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (IllegalAccessException e) {
            String message = "Cought IllegalAccessException while creating class: " + className.getName();
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        }
        return retVal;
    }

    public static <E> E createClassInstance(String classFullName, Class<E> className) {
        E retVal = null;
        try {
            Class<?> classObject = Class.forName(classFullName);
            Constructor<?> constructor = classObject.getConstructor(new Class[0]);
            Object object = constructor.newInstance(new Object[0]);
            if (className.isInstance(object)) {
                retVal = className.cast(object);
            } else {
                String message = "Class created from given classFullName ( " + classFullName + " ) is not an instance of given className ( " + className.getName() + ") ";
                PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
                throw new RuntimeException(message);
            }
        } catch (InstantiationException e) {
            String message = "Cought InstantiationException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (IllegalAccessException e) {
            String message = "Cought IllegalAccessException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (IllegalArgumentException e) {
            String message = "Cought IllegalArgumentException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (InvocationTargetException e) {
            String message = "Cought InvocationTargetException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (ClassNotFoundException e) {
            String message = "Cought ClassNotFoundException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (NoSuchMethodException e) {
            String message = "Cought NoSuchMethodException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        } catch (SecurityException e) {
            String message = "Cought SecurityException while creating class: " + classFullName;
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message, e);
        }
        return retVal;
    }
}
