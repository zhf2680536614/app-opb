package com.opb.config;

import com.opb.interceptor.JwtManageInterceptor;
import com.opb.interceptor.JwtUserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类 注册Web层相关组件
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private final JwtUserInterceptor jwtUserInterceptor;

    private final JwtManageInterceptor jwtManageInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtUserInterceptor)
                .addPathPatterns("/api/user/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/register")
                .excludePathPatterns("/api/user/captcha")
                .excludePathPatterns("/api/user/captcha/validator");

        registry.addInterceptor(jwtManageInterceptor)
                .addPathPatterns("/api/manage/**")
                .excludePathPatterns("/api/manage/login");
    }
}
