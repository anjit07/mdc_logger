package mdc.logger.interceptur;

import mdc.logger.config.MDCConfig;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


public class LoggerInterceptur implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        System.out.println("LoggerInterceptur==="+ MDC.get(MDCConfig.MDC_CORRELATION_KEY));
        request.getHeaders().set(MDCConfig.HEADER_CORRELATION_KEY,MDC.get(MDCConfig.MDC_CORRELATION_KEY));
        return execution.execute(request, body);
    }
}
