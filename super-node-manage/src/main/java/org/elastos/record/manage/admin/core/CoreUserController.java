package org.elastos.record.manage.admin.core;

import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.dao.SysRoleDao;
import org.elastos.record.biz.dao.SysUserDao;
import org.elastos.record.biz.dao.SysUserRoleDao;
import org.elastos.record.biz.service.SysUserService;
import org.elastos.record.manage.controller.common.CommonWebController;
import org.elastos.record.manage.configuration.SecurityUtils;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.SysRole;
import org.elastos.record.utility.entity.SysUser;
import org.elastos.record.utility.entity.SysUserRole;
import org.elastos.record.utility.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 用户相关控制器
 */

@Controller
@RequestMapping("sys/core/user")
@Slf4j
public class CoreUserController extends CommonWebController<SysUser, SysUserService> {

    @Autowired
    SysUserDao coreUserDao;

    @Autowired
    SysUserRoleDao coreUserRoleDao;
    @Autowired
    SysRoleDao coreRoleDao;


    /**
     * 进入列表页面
     *
     * @return
     */
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view = getModelAndView("index");
        return view;
    }

    /**
     * 进入编辑页面
     *
     * @return
     */
    @RequestMapping(value = "edit.html", method = {RequestMethod.GET})
    public ModelAndView edit(String _mode) {
        ModelAndView view = getModelAndView("edit");
        if (_mode != null && _mode.equals("readonly")) {
            view.addObject("roleList", coreRoleDao.all());

        } else {
            List<SysRole> roleByUserId = coreRoleDao.findRoleByUserId(SecurityUtils.getUser().getId());
            view.addObject("roleList", roleByUserId);
        }
        return view;
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "add.html", method = {RequestMethod.GET})
    public ModelAndView add() {
        return getModelAndView("add");
    }

    /**
     * 进入密码修改页面
     *
     * @return
     */
    @RequestMapping(value = "updatePwd.html", method = {RequestMethod.GET})
    public ModelAndView updatePwd() {
        return getModelAndView("updatePwd");
    }

    /**
     * 添加操作
     *
     * @param coreUser
     * @return
     */
    @RequestMapping(value = "add.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(SysUser coreUser ) {
        //向用户表插入新添加用户信息
        coreUser.setPassword(MD5Util.MD5("123456"));
        coreUser.setCreateTime(new Date());

        coreUserDao.insertTemplate(coreUser);
        //根据添加的用户名查出单条数据
        SysUser newUser = coreUserDao.findByUserName(coreUser.getName());
        //向用户角色关系表插入新添加用户建立的角色
        String[] roles = coreUser.getRole().split(",");
        for (String role : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(newUser.getId());
            ur.setRoleId(Long.valueOf(role));
            coreUserRoleDao.insertTemplate(ur);
        }
        ResultMsg result = new ResultMsg();
        result.setData(coreUser);
        return result;
    }

    /**
     * 删除操作
     *
     * @param coreUser
     * @return
     */
    @RequestMapping(value = "delete.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(SysUser coreUser ) {

        //根据id删除用户角色关系表所有与id关联的数据
        SysUserRole ur = new SysUserRole();
        ur.setUserId(coreUser.getId());
        coreUserRoleDao.deleteByUserId(ur.getUserId());

        //根据id删除用户表单条数据
        coreUserDao.deleteById(coreUser.getId());
        return ResultMsg.ok();
    }

    /**
     * 批量删除操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteBatch.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        //根据多个用户id删除用户表信息
        coreUserDao.deleteByIds(id.split(","));
        //根据多个用户id删除用户角色关系表信息
        coreUserRoleDao.deleteByIds(id.split(","));
        return ResultMsg.ok();
    }

    /**
     * 编辑操作
     *
     * @param coreUser
     * @return
     */
    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SysUser coreUser ) {
        //更新用户表信息
        coreUser.setUpdateTime(new Date());

        coreUserDao.updateTemplateById(coreUser);
        //根据新的用户名查出单条数据
        SysUser editUser = coreUserDao.findByUserName(coreUser.getName());
        //根据id删除用户角色关系表所有与id关联的数据
        SysUserRole ur = new SysUserRole();
        ur.setUserId(editUser.getId());
        coreUserRoleDao.deleteByUserId(ur.getUserId());
        if (coreUser.getRole() != null) {
            //向用户角色关系表插入用户新修改的角色
            String[] roles = coreUser.getRole().split(",");
            for (String role : roles) {
                ur.setUserId(editUser.getId());
                ur.setRoleId(Long.valueOf(role));
                coreUserRoleDao.insertTemplate(ur);
            }
        }
        ResultMsg result = new ResultMsg();
        result.setData(coreUser);
        return result;
    }

    /**
     * 查询单条数据操作
     *
     * @param coreUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findById.json", method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateSysMenu(SysUser coreUser ) throws Exception {
        //向用户表查询用户信息
        SysUser user = coreUserDao.single(coreUser.getId());
        //向用户关系表查询用户角色信息
        List<SysUserRole> userRoles = coreUserRoleDao.findByUserId(user.getId());
        String roles = "";
        for (SysUserRole userRole : userRoles) {

            String role = String.valueOf(userRole.getRoleId());
            roles = roles + "," + role;
            log.info(roles);
        }
        user.setRole(roles);
        ResultMsg result = new ResultMsg();
        result.setData(user);
        return result;

    }

    /**
     * 查询全部数据操作
     *
     * @return
     */
    @RequestMapping(value = "all.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all() {
        List<SysUser> coreMenus = coreUserDao.all();
        ResultMsg result = new ResultMsg();
        result.setData(coreMenus);
        return result;
    }

    /**
     * 查询分页数据操作
     *
     * @param coreUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute SysUser coreUser ,@RequestParam Long pageNum,@RequestParam Long pageSize) {
        PageQuery<SysUser> pageQuery = new PageQuery<SysUser>();
        pageQuery.setPageSize((pageSize));
        pageQuery.setPageNumber((pageNum));
        pageQuery.setParas(coreUser);
        //查出用户分页集合
        coreUserDao.templatePage(pageQuery);
        //遍历用户集合根据用户名称查出角色信息写入vo字段
        List<SysUser> users = pageQuery.getList();
        for (SysUser user : users) {
            //根据用户名称查出角色信息id
            List<SysUserRole> userRoles = coreUserRoleDao.findByUserId(user.getId());
            //格式拼接用","作为分隔符
            String roles = "";
            for (SysUserRole userRole : userRoles) {
                String role = String.valueOf(userRole.getRoleId());
                roles = roles + "," + role;
                log.info(roles);
            }
            //写入vo,格式（1，2）
            user.setRole(roles);
        }


        UiPageFrame pageFrame = new UiPageFrame();
        pageFrame.setList(users);
        pageFrame.setPageNum((pageNum));
        pageFrame.setPageSize((pageSize));
        pageFrame.setPages(pageQuery.getTotalPage());
        pageFrame.setTotal(pageQuery.getTotalRow());
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }

    /**
     * 修改用户密码操作
     *
     * @param oldPwd
     * @param newPwd
     * @param confirm
     * @return
     */
    @RequestMapping(value = "/updatePwd.do", method = RequestMethod.POST, params = {"oldPwd", "newPwd", "confirm"})
    @ResponseBody
    public ResultMsg updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, @RequestParam("confirm") String confirm) {
        if (oldPwd == null || oldPwd.length() <= 0 || newPwd == null || newPwd.length() <= 0 || confirm == null || confirm.length() <= 0) {
            return ResultMsg.fail("三个密码都不能为空");
        }
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser coreUser  = coreUserDao.findByUserName(username);
        if (!coreUser.getPassword().equals(MD5Util.MD5(oldPwd))) {
            return ResultMsg.fail("密码输入错误");
        }
        coreUser.setPassword(MD5Util.MD5(newPwd));
        coreUserDao.updateTemplateById(coreUser);
        return ResultMsg.ok();
    }

    @Override
    protected String getModel() {
        return "sys/core/user";
    }
}
