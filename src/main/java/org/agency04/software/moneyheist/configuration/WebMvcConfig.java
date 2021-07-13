package org.agency04.software.moneyheist.configuration;

import org.agency04.software.moneyheist.interceptor.FindIdentityByUrlInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FindIdentityByUrlInterceptor findIdentityByUrlInterceptor(){
        return new FindIdentityByUrlInterceptor();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(findIdentityByUrlInterceptor());
    }
}