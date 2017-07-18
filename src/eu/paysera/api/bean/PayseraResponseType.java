package eu.paysera.api.bean;

public enum PayseraResponseType {
    CLIENT_ACCEPT("CLIENT_ACCEPT"), //

    CLIENT_CANCEL("CLIENT_CANCEL"), //

    SERVER_CALLBACK("SERVER_CALLBACK");

    private String code;

    private PayseraResponseType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static PayseraResponseType valueByCode(String code) {
        PayseraResponseType retVal = null;
        PayseraResponseType[] arrayOfPayseraResponseType;
        int j = (arrayOfPayseraResponseType = values()).length;
        for (int i = 0; i < j; i++) {
            PayseraResponseType type = arrayOfPayseraResponseType[i];
            if (type.getCode().equals(code)) {
                retVal = type;
                break;
            }
        }
        return retVal;
    }
}
