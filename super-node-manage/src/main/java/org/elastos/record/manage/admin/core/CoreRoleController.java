package org.elastos.record.manage.admin.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.dao.*;
import org.elastos.record.biz.service.*;
import org.elastos.record.manage.controller.common.CommonWebController;
import org.elastos.record.manage.configuration.SecurityUtils;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.*;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * 角色相关控制器
 */
@Controller
@RequestMapping("sys/core/role")
@Slf4j
public class CoreRoleController extends CommonWebController<SysRole, SysRoleService> {
    @Autowired
    SysRoleService coreRoleDao;

    @Autowired
    SysMenuService coreMenuDao;

    @Autowired
    SysButtonService coreButtonDao;

    @Autowired
    SysRoleMenuService coreRoleMenuDao;

    @Autowired
    SysRoleButtonService coreRoleButtonDao;
    @Autowired
    SysUserRoleService coreUserRoleDao;



    /**
     * 进入列表页面
     *
     * @return
     */
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return getModelAndView("index");
    }

    /**
     * 进入编辑页面
     *
     * @return
     */
    @RequestMapping(value = "edit.html", method = {RequestMethod.GET})
    public ModelAndView edit() {
        return getModelAndView("edit");
    }

    /**
     * 进入授权页面
     *
     * @return
     */
    @RequestMapping(value = "permission.html", method = {RequestMethod.GET})
    public ModelAndView permission() {
        return getModelAndView("permission");
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
     * 添加操作
     *
     * @param coreRole
     * @return
     */
    @RequestMapping(value = "add.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(SysRole coreRole) {
        try {

            coreRoleDao.save(coreRole);
            ResultMsg result = new ResultMsg();
            result.setData(coreRole);
            return result;
        } catch (InsertNewInstanceException e) {
            e.printStackTrace();
            return ResultMsg.fail(e);
        }
    }

    /**
     * 删除操作
     *
     * @param coreRole
     * @return
     */
    @RequestMapping(value = "delete.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(SysRole coreRole) {
        Long roleId = coreRole.getId();
        SysRole single = coreRoleDao.single(roleId);
        if (single == null) {
            return ResultMsg.fail(Action.NO_RECORD);
        }
        List<SysUserRole> byRoleIds = coreUserRoleDao.findByRoleIds(Collections.singletonList(coreRole.getId()));
        if (byRoleIds.isEmpty()) {
            SysUserRole entity = new SysUserRole();
            entity.setRoleId(roleId);

            coreRoleMenuDao.deleteSample(single.getId());
            coreRoleButtonDao.deleteByRoleId(roleId);
            coreUserRoleDao.deleteById(coreRole.getId());
            coreRoleDao.deleteById(single.getId());
            return new ResultMsg();
        }
        return ResultMsg.fail("已关联用户，不能删除");


    }

    /**
     * 批量删除操作
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteBatch.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(@RequestParam(name = "id") List<Long> ids) {
        for (Long id : ids) {
            coreRoleMenuDao.deleteSample((id));
        }
        coreRoleDao.deleteByIds(ids);
        ResultMsg result = new ResultMsg();
        return result;
    }

    /**
     * 编辑操作
     *
     * @param coreRole
     * @return
     */
    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SysRole coreRole) {

        coreRole.setUpdateTime(new Date());
        coreRoleDao.updateTemplateById(coreRole);
        ResultMsg result = new ResultMsg();
        result.setData(coreRole);
        return result;
    }

    /**
     * 查询单条数据操作
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findById.json", method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg findById(Long id) {
        SysRole role = coreRoleDao.single(id);
        ResultMsg result = new ResultMsg();
        result.setData(role);
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
        List<SysRole> coreMenus = coreRoleDao.all();
        ResultMsg result = new ResultMsg();
        result.setData(coreMenus);
        return result;
    }

    /**
     * 查询用户角色
     *
     * @return
     */
    @RequestMapping(value = "findByUser.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg findByUserId() {
        List<SysRole> coreMenus = coreRoleDao.findRoleByUserId(SecurityUtils.getUser().getId());
        //向用户关系表查询用户角色信息
        String roles = "";
        for (SysRole userRole : coreMenus) {
            String role = String.valueOf(userRole.getId());
            roles = roles + "," + role;
            log.info(roles);
        }
        ResultMsg result = new ResultMsg();
        result.setData(roles);
        return result;
    }

    /**
     * 查询分页数据操作
     *
     * @param coreRole
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute SysRole coreRole, @RequestParam Long pageNum, @RequestParam Long pageSize) {
        PageQuery<SysRole> pageQuery = new PageQuery<SysRole>();
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageNumber(pageNum);
        pageQuery.setParas(coreRole);
        coreRoleDao.templatePage(pageQuery);
        UiPageFrame pageFrame = new UiPageFrame();
        pageFrame.setList(pageQuery.getList());
        pageFrame.setPageNum(pageNum);
        pageFrame.setPageSize(pageSize);
        pageFrame.setPages(pageQuery.getTotalPage());
        pageFrame.setTotal(pageQuery.getTotalRow());
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }

    /**
     * 角色添加菜单权限
     *
     * @param menuIds
     * @param roleId
     * @return
     */
    @RequestMapping(value = "addPermission.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg addPermission(@RequestParam(name = "menuIds") List<Long> menuIds, @RequestParam Long roleId) {
        SysRole coreRole = coreRoleDao.single(roleId);
        if (coreRole == null) {
            return ResultMsg.fail();
        }
        SysRoleMenu rm = new SysRoleMenu();
        SysRoleButton rb = new SysRoleButton();
        //根据id删除角色菜单关系表所有与roleid关联的数据
        coreRoleMenuDao.deleteSample((roleId));
        //根据id删除角色按钮关系表所有与roleid关联的数据
        int i = coreRoleButtonDao.deleteByRoleId((roleId));


        for (Long id : menuIds) {
            SysMenu end = coreMenuDao.single(id);
            //判断是否为菜单id
            if (end != null) {
                //向角色菜单关系表插入角色新修改的菜单权限
                rm.setRoleId(roleId);
                rm.setDeleted(false);
                rm.setMenuId((id));
                try {
                    coreRoleMenuDao.save(rm);
                } catch (InsertNewInstanceException e) {
                    e.printStackTrace();
                }
            } else {
                //向角色按钮关系表插入角色新修改的菜单权限
                rb.setRoleId(roleId);
                rb.setButtonId((id));
                rm.setDeleted(false);
                try {
                    coreRoleButtonDao.save(rb);
                } catch (InsertNewInstanceException e) {
                    e.printStackTrace();
                }

                //
            }
        }
        return ResultMsg.ok();
    }

    /**
     * 角色获取菜单权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "getPermission.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getPermission(@RequestParam Long roleId) {
        if (roleId == null) {
            return ResultMsg.fail();
        }
        try {
            //根据角色id获取菜单权限
            List<SysRoleMenu> coreRoleMenu = coreRoleMenuDao.sample((roleId));
            //根据角色id获取按钮权限
            SysRoleButton coreRoleButtonTemp = new SysRoleButton();
            coreRoleButtonTemp.setRoleId(roleId);
            List<SysRoleButton> coreRoleButton = coreRoleButtonDao.template((coreRoleButtonTemp));
            //合并序列
            ArrayList<Object> permissions = new ArrayList<>();
            permissions.addAll(coreRoleButton);
            permissions.addAll(coreRoleMenu);
            ResultMsg result = new ResultMsg();
            result.setData(permissions);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.fail();
        }
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    @RequestMapping(value = "getMenuTree.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getMenuTree() {
        List<SysMenu> listMenu = coreMenuDao.findMenus();
        List<SysButton> listButton = coreButtonDao.all();
        List<JSONObject> lstTree = new ArrayList<JSONObject>();
        for (SysMenu menu : listMenu) {
            if (menu.getId() > 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", menu.getId());
                jsonObject.put("pId", menu.getParentId());
                jsonObject.put("name", menu.getName());
                jsonObject.put("open", true);
                lstTree.add(jsonObject);
            }
        }
        //按钮
        for (SysButton button : listButton) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", button.getId());
            jsonObject.put("pId", button.getMenuId());
            jsonObject.put("name", button.getName());
            jsonObject.put("open", true);
            lstTree.add(jsonObject);
        }
        ResultMsg result = new ResultMsg();
        result.setData(lstTree);
        return result;
    }

    @Override
    protected String getModel() {
        return "sys/core/role";
    }
}
