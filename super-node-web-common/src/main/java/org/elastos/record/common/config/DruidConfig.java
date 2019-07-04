package org.elastos.record.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.druid.core")
    public DruidProperties coreProperties() {
        return new DruidProperties();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.druid.store")
    public DruidProperties storeProperties() {
        return new DruidProperties();
    }

    @Bean(name = "core")
    @Primary
    public DruidDataSource core() {
        DruidDataSource druidDataSource = new DruidDataSource();
        DruidProperties source = coreProperties();
        BeanUtils.copyProperties(source, druidDataSource);

        return druidDataSource;
    }

   /* @Bean(name = "store")
    public DruidDataSource store() {
        DruidDataSource druidDataSource = new DruidDataSource();
        DruidProperties source = storeProperties();
        BeanUtils.copyProperties(source, druidDataSource);
        return druidDataSource;
    }*/





    @Bean
    public ServletRegistrationBean druidServlet() {
        log.info("initDataMap Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "192.168.3.82");
        // 控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "merchant");
        servletRegistrationBean.addInitParameter("loginPassword", "merchant");
        // 是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
