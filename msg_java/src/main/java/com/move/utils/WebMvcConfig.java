package com.move.utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
 
/**
 * @author Lix@jchvip.com
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
        @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new LoginUserInfoMethodArgumentResolver());
    }
}