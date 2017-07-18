package eu.paysera.api.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

public class PayseraMD5HashHelper {

    private static final Logger LOGGER = Logger.getLogger(PayseraMD5HashHelper.class);

    public static String hash(String data, String password) {
        if (data == null) {
            throw new IllegalArgumentException("Input variable data has NULL value!");
        }
        if (password == null) {
            throw new IllegalArgumentException("Input variable password has NULL value!");
        }
        String retVal = null;
        String tmpVal = data + password;
        byte[] bytesOfMessage = tmpVal.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] thedigest = md5.digest(bytesOfMessage);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < thedigest.length; i++) {
                sb.append(Integer.toHexString(thedigest[i] & 0xFF | 0x100).substring(1, 3));
            }
            retVal = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            String message = "Cought NoSuchAlgorithmException.";
            PayseraExceptionHelper.throwRuntimeException(LOGGER, message);
        }
        return retVal;
    }
}
