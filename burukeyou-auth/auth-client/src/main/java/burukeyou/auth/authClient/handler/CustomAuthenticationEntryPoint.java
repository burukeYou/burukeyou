package burukeyou.auth.authClient.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  认证异常时被调用
 *
 */
@Component
@Slf4j
@Configuration
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${spring.application.name}")
    private String application_anme;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("auth fail....      -- {}",application_anme);

        Throwable cause = e.getCause();
        if (cause instanceof InvalidTokenException){
            log.info("error mesage:  invalid_token");
        }else if(e instanceof InsufficientAuthenticationException){
            log.info("error mesage:  access_deined");
        }
    }
}
