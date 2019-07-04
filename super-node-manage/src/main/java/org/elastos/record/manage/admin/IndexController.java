package org.elastos.record.manage.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.elastos.record.biz.service.SysMenuService;
import org.elastos.record.biz.service.SysUserService;
import org.elastos.record.manage.authorizationInfo.DIDToken;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.entity.SysMenu;
import org.elastos.record.utility.entity.SysUser;
import org.elastos.record.utility.util.MD5Util;
import org.elastos.record.utility.util.TransApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 后台管理主控制器
 *
 * @Version 1.0
 */
@Controller
public class IndexController {

    @Autowired
    HttpSession session;

    @Autowired
    SysUserService coreUserDao;

    @Autowired
    SysMenuService coreMenuDao;


    /**
     * 进入后台首页
     *
     * @return
     */
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index.html");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.print(username);
        SysUser coreUser = coreUserDao.findByUserName(username);
        view.addObject("user", coreUser);
        return view;
    }

    /**
     * 进入登陆页面
     *
     * @return
     */
    @RequestMapping(value = "login.html", method = {RequestMethod.GET})
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("login.html");
        return view;
    }

    /**
     * 进入403未授权页面
     *
     * @return
     */
    @RequestMapping(value = "403.html", method = {RequestMethod.GET})
    public ModelAndView error403() {
        ModelAndView view = new ModelAndView("403.html");
        return view;
    }

    /**
     * 进入404未定义页面
     *
     * @return
     */
    @RequestMapping(value = "404.html", method = {RequestMethod.GET})
    public ModelAndView error404() {
        ModelAndView view = new ModelAndView("404.html");
        return view;
    }

    /**
     * 进入后台首页面
     *
     * @return
     */
    @RequestMapping(value = "home.html", method = {RequestMethod.GET})
    public ModelAndView home() {
        ModelAndView view = new ModelAndView("home.html");
        return view;
    }



    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg loginDo( @RequestParam String username, @RequestParam String password ,@RequestParam(required = false) String lang) {
        //用户名密码校验
        DIDToken token = new DIDToken();
        token.setUsername(username);
        token.setLang(StringUtils.isEmpty(lang)? TransApi.Language.en : TransApi.Language.valueOf(lang) );
        token.setPassword(MD5Util.MD5(password).toCharArray());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.fail("用户名或密码错误！");
        }
        return ResultMsg.ok();
    }

    /**
     * 登出操作
     *
     * @return
     */
    @RequestMapping(value = "loginOut.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginOut() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/login.html");
        SecurityUtils.getSubject().logout();
        return view;
    }

    /**
     * 获取用户菜单数据
     *
     * @return
     */
    @RequestMapping(value = "getMenus.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getMenus() {
        List<SysMenu> coreMenus = coreMenuDao.findMenuByUserId(org.elastos.record.manage.configuration.SecurityUtils.getUser().getId());
        ResultMsg result = new ResultMsg();
        result.setData(coreMenus);
        return result;
    }

}
