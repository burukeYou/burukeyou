package burukeyou.common.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidationUtils {

    // mobile regex
    private final static String mobileRegex = "\\d{11}";

    // email regex
    private final static String emailRegex = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$";

    // 身份证
    private final static String idCardRegex = "(^\\\\d{18}$)|(^\\\\d{15}$)";

    // username
    private final static String usernameRegex = "[a-zA-Z]*";


    private RegexValidationUtils(){};

    public static boolean validateMobile(String value){
        return validate(value,mobileRegex);
    }

    public static boolean validateEmail(String value){
        return validate(value,emailRegex);
    }

    public static boolean validateeUsername(String value){
        return validate(value,usernameRegex);
    }


    private static boolean validate(String value,String regexValue){
        Pattern regex = Pattern.compile(regexValue);
        Matcher m = regex .matcher(value);
        return m.matches();
    }

}
