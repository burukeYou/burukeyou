package burukeyou.article.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 *      解决服务调用token传递
 */
@Configuration
public class CustomFeginInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            Map<String,String> headers = getHeaders();
            for(String headerName : headers.keySet()){
                requestTemplate.header(headerName, headers.get(headerName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private Map<String, String> getHeaders(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Map<String, String> map = new LinkedHashMap<>();
        Optional.ofNullable(attributes).ifPresent(e -> {
            HttpServletRequest request = e.getRequest();
            Enumeration<String> enumeration = request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        });
        return map;

    }

}