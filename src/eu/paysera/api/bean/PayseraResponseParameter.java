package eu.paysera.api.bean;

public enum PayseraResponseParameter {
    _01_PROJECT_ID("projectid"), //
    _02_ORDER_ID("orderid"), //
    _03_LANGUAGE("lang"), //
    _05_AMOUNT("amount"), //
    _06_CURRENCY("currency"), //
    _07_PAYMENT_TYPE("payment"), //
    _08_COUNTRY("country"), //
    _09_PAY_TEXT("paytext"), //
    _10_NAME("name"), //
    _11_SURNAME("surename"), //
    _12_STATUS("status"), //
    _13_TEST("test"), //
    _14_EMAIL("p_email"), //
    _15_REQUEST_ID("requestid"), //
    _16_PAY_AMOUNT("payamount"), //
    _17_PAY_CURRENCY("paycurrency"), //
    _18_VERSION("version"), //
    _19_ACCOUNT("account"), //
    _20_PERSION_CODE_STATUS("personcodestatus");

    private final String code;

    private PayseraResponseParameter(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static String _12_STATUS_PAY_CANCEL = "0";
    public static String _12_STATUS_PAY_CONFIRM = "1";
    public static String _12_STATUS_PAY_REGISTR = "2";
    public static String _12_STATUS_PAY_INFO = "3";
}
