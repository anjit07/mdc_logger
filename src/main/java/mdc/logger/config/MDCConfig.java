package mdc.logger.config;

import lombok.Data;
import mdc.logger.filter.MDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MDCConfig {


    public static final String  HEADER_CORRELATION_KEY = "x-correlationId";
    public static final String  MDC_CORRELATION_KEY = "correlationId";

    @Bean
    public FilterRegistrationBean<MDCFilter> servletRegistrationBean() {

        final FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        final MDCFilter log4jMDCFilterFilter = new MDCFilter();
        registrationBean.setFilter(log4jMDCFilterFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
