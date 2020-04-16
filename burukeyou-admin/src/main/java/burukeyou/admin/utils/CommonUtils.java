package burukeyou.admin.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

    private CommonUtils(){};

    public static String getRemoteIpAddress(HttpServletRequest request) {
        String ipAdress = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipAdress) || "unknown".equalsIgnoreCase(ipAdress)) {
            ipAdress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAdress) || "unknown".equalsIgnoreCase(ipAdress)) {
            ipAdress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAdress) || "unknown".equalsIgnoreCase(ipAdress)) {
            ipAdress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ipAdress) || "unknown".equalsIgnoreCase(ipAdress)) {
            ipAdress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ipAdress) || "unknown".equalsIgnoreCase(ipAdress)) {
            ipAdress = request.getRemoteAddr();
        }
        return ipAdress;
    }


}
