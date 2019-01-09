package com.order.web.config;

import com.order.web.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebTokenConfig implements WebMvcConfigurer {

    /**
     * 拦截器在spring之前，spring无法注入interceptor拦截器无法注入解决方案
     * @return
     */
    @Bean
    public AuthorizationInterceptor getAuthorizationInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**");
    }
}
