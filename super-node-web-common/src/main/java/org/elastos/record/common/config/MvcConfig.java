package org.elastos.record.common.config;

import org.elastos.record.common.Interceptor.LoggerInterceptor;
import org.elastos.record.common.Interceptor.SessionInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    //拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
//        日志记录
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }

    @Override
    //URL到视图的映射
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    @Override
    //跨域访问配置
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    //格式化配置
    public void addFormatters(FormatterRegistry registry) {

    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128000KB");
        factory.setMaxRequestSize("128000KB");
        return factory.createMultipartConfig();
    }

}