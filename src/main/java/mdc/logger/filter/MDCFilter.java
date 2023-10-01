package mdc.logger.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mdc.logger.config.MDCConfig;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class MDCFilter extends OncePerRequestFilter {

    private final String responseHeader;
    private final String mdcKey;
    private final String requestHeader;

    public MDCFilter() {
        responseHeader = MDCConfig.HEADER_CORRELATION_KEY;
        mdcKey = MDCConfig.MDC_CORRELATION_KEY;
        requestHeader = MDCConfig.HEADER_CORRELATION_KEY;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String correlationId = extractCorrelationId(request);
            MDC.put(mdcKey, correlationId);
            if (StringUtils.hasText(responseHeader)) {
                response.addHeader(responseHeader, correlationId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(mdcKey);
        }
    }

    private String extractCorrelationId(final HttpServletRequest request) {
        final String correlationId;
        if (StringUtils.hasText(requestHeader) && StringUtils.hasText(request.getHeader(requestHeader))) {
            correlationId = request.getHeader(requestHeader);
        } else {
            correlationId = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        }
        return correlationId;
    }

    @Override
    protected boolean isAsyncDispatch(final HttpServletRequest request) {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}
