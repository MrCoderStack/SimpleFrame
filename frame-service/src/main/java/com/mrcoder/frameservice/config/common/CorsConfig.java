package com.mrcoder.frameservice.config.common;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 跨域配置
 * @author: mrcoder
 */
@Configuration
@Slf4j
public class CorsConfig {

    @Value("${spring.profiles.active:default}")
    private String profile;

    @Value("${white.list:''}")
    private String whiteList;

    @Bean
    public CorsFilter corsFilter() {
        log.info("**************spring.profiles.active: {}**************", profile);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        if (profile.equals("prod")) { // 生产
            config.setAllowedOrigins(Arrays.asList(whiteList.split(","))); // 1、设置访问源地址
        } else {
            config.addAllowedOrigin("*"); // 开发测试不做限制
        }
        config.addAllowedHeader("*"); // 2、设置访问源请求头
        config.setAllowedMethods(Arrays.asList("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")); // 3、设置访问源请求方法
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

