package com.dagm.shorter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description: cors 同源跨域配置
 * @Author: Guimu
 * @Create: 2019/02/20 23:11:07
 **/

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.setAllowCredentials(true);//允许跨域传递身份认证
       /* List<String> Headers = new ArrayList<String>();
        Headers.add("Origin, No-Cache");
        Headers.add("X-Requested-With");
        Headers.add("If-Modified-Since");
        Headers.add("Last-Modified");
        Headers.add("Cache-Control");
        Headers.add("Expires");
        Headers.add("Content-Type");
        Headers.add("X-E4M-With");
        Headers.add("userId");
        Headers.add("token");
        corsConfiguration.setAllowedHeaders(Headers);*/
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
