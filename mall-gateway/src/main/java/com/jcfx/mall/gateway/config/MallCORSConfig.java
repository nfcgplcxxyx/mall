package com.jcfx.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class MallCORSConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        // 让所有请求头都可以跨域
        configuration.addAllowedHeader("*");
        // 让所有请求方法都可以跨域
        configuration.addAllowedMethod("*");
        // 让所有请求来源都可以跨域
        configuration.addAllowedOrigin("*");
        // 允许携带Cookie跨域
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }

}
