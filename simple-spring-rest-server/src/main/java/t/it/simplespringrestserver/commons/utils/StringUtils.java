package t.it.simplespringrestserver.commons.utils;

public enum StringUtils {
    ;
    public static final String COMMON_GREETER_UPDATE_MESSAGE = "Your name is replaced";

    public static String helloGreeter(String value) {
        return "Hello, " + value;
    }
}
