package org.elastos.record.manage.authorizationInfo;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.elastos.record.biz.service.*;
import org.elastos.record.manage.configuration.SecurityUtils;
import org.elastos.record.biz.dao.SysButtonDao;
import org.elastos.record.biz.dao.SysMenuDao;
import org.elastos.record.biz.dao.SysRoleDao;
import org.elastos.record.biz.dao.SysUserDao;
import org.elastos.record.manage.dto.SessionUser;
import org.elastos.record.utility.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author hb.nie
 * shiro 权限认证器
 */
public class Realm extends AuthorizingRealm {


    @Autowired
    SysUserService sysUserDao;

    @Autowired
    SysRoleService roleDao;

    @Autowired
    SysMenuService sysMenuDao;

    @Autowired
    SysButtonService sysButtonDao;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从凭证中获得用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询用户对象
        SysUser entity = new SysUser();
        entity.setName(username);
        SysUser coreUser = sysUserDao.templateOne(entity);

        //查询用户拥有的角色
        List<SysRole> list = roleDao.findRoleByUserId(coreUser.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (SysRole coreRole : list) {
            //赋予用户角色
            info.addRole(coreRole.getName());
            List<SysMenu> menus = sysMenuDao.findMenuByRoleId(coreRole.getId());
            List<SysButton> buttons = sysButtonDao.findButtonByRoleId(coreRole.getId());

            for (SysMenu menu : menus) {//赋予用户角色权限
                System.out.print(menu.getPermission());
                info.addStringPermission(menu.getPermission());
            }
            for (SysButton button : buttons) {//赋予用户角色权限
                System.out.print(button.getPermission());
                info.addStringPermission(button.getPermission());
            }
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        DIDToken didToken = (DIDToken) authenticationToken;
        //获得当前用户的用户名
        String username = (String) didToken.getPrincipal();
        String password = new String(didToken.getPassword());


        //从数据库中根据用户名查找用户
        SysUser coreUser = sysUserDao.findByUserName(username);
        String did = didToken.getUsername();

        if (coreUser == null) {
            throw new UnknownAccountException("没有在本系统中找到对应的用户信息。");
        } else if (!coreUser.getPassword().equalsIgnoreCase(password)) {
            throw new AccountException("密码错误");
        }


        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(coreUser.getId());

        sessionUser.setLang(didToken.getLang());
        sessionUser.setLoginTime(new Date());
        SecurityUtils.loginSuccess(sessionUser);
        return new SimpleAuthenticationInfo(coreUser.getName(), coreUser.getPassword(), getName());
    }

}
