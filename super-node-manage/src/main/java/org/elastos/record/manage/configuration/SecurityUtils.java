package org.elastos.record.manage.configuration;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.elastos.record.manage.constant.Constant;
import org.elastos.record.manage.dto.SessionUser;

public class SecurityUtils extends org.apache.shiro.SecurityUtils {


    public static SessionUser getUser() {
        return (SessionUser) getSubject().getSession().getAttribute(Constant.ShiroHttpSession.CORE_USER_KEY);
    }


    static void setUser(SessionUser user) {
        getSubject().getSession().setAttribute(Constant.ShiroHttpSession.CORE_USER_KEY, user);
    }

    public static SimpleAuthorizationInfo getSimpleAuthorizationInfo() {
        return getUser() == null ? null : (SimpleAuthorizationInfo) getSubject().getSession().getAttribute(Constant.ShiroHttpSession.SIMPLE_AUTHORIZATION_INFO);
    }


    public static void loginSuccess(SessionUser coreUser, SimpleAuthorizationInfo simpleAuthorizationInfo) {
        loginSuccess(coreUser);
        getSubject().getSession().setAttribute(Constant.ShiroHttpSession.SIMPLE_AUTHORIZATION_INFO, simpleAuthorizationInfo);
    }

    public static void loginSuccess(SessionUser coreUser) {
        setUser(coreUser);
    }
}
