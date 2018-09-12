package com.sufu.renantangsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lenovo on 2018/6/19.
 */
@SpringBootConfiguration
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyUserInterceptorConfig myUserInterceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myUserInterceptorConfig)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/login",
//                        "/css/**",
//                        "/js/**",
//                        "/images/**",
//                        "/assets/**");
    }
}
