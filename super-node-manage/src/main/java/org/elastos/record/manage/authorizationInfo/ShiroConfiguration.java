package org.elastos.record.manage.authorizationInfo;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.elastos.record.manage.constant.Api.VERSION;


/**
 * @author hb.nie
 * apache shiro 权限验证配置
 */
@Configuration
public class ShiroConfiguration {


    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(sessionDao());
        sessionManager.setGlobalSessionTimeout(1800);
//        sessionManager.setCacheManager(cacheManager());
        sessionManager.getSessionIdCookie().setName("MerchantUserId");
        return sessionManager;
    }




    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myRealm());
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断


        filterMap.put("/css/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/script/**", "anon");
        filterMap.put("/login.do", "anon");
        filterMap.put("/lang/**", "anon");
        filterMap.put("/passport/login.do", "anon");
        filterMap.put("/sys/chain/all.json", "anon");

        filterMap.put("/static/**", "anon");
        filterMap.put("/merchant/login.do", "anon");
        filterMap.put("/captcha/getCaptcha.jpg", "anon");
        filterMap.put("/upload/**", "anon");
        filterMap.put("/file/**", "anon");
        filterMap.put("/merchant/importKeystore.html", "anon");
        filterMap.put("/merchant/importKeystore.do", "anon");
        filterMap.put("/merchant/importMnemonic.html", "anon");
        filterMap.put("/merchant/importMnemonic.do", "anon");
        filterMap.put("/merchant/loginOut.do", "logout");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/test/**", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/admin/**", "anon");
        filterMap.put("/sharing/**", "anon");
        filterMap.put("/eth/chain/**", "anon");
        filterMap.put("/ela/chain/**", "anon");
        filterMap.put("/did/chain/**", "anon");
        filterMap.put(VERSION + "**", "anon");

        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/index.html");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public Realm myRealm() {
        return new Realm();
    }


}
