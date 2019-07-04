package org.elastos.record.manage.admin.core;

import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.service.SysMenuService;
import org.elastos.record.biz.service.SysRoleMenuService;
import org.elastos.record.manage.controller.common.CommonWebController;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.SysMenu;
import org.elastos.record.utility.entity.SysRoleMenu;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 菜单相关控制器
 */

@Controller
@RequestMapping("sys/core/menu")
public class CoreMenuController extends CommonWebController<SysMenu, SysMenuService> {

    @Autowired
    SysMenuService coreMenuDao;
    @Autowired
    SysRoleMenuService coreRoleMenuDao;

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
     * @param coreMenu
     * @return
     */
    @RequestMapping(value = "add.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(SysMenu coreMenu) {
        try {
            coreMenuDao.save(coreMenu);
            ResultMsg result = new ResultMsg();
            result.setData(coreMenu);
            return result;
        } catch (InsertNewInstanceException e) {
            e.printStackTrace();
            return ResultMsg.fail(e );
        }
    }

    /**
     * 删除操作
     *
     * @param coreMenu
     * @return
     */
    @RequestMapping(value = "delete.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(SysMenu coreMenu) {
        SysRoleMenu coreRoleMenu = new SysRoleMenu();
        coreRoleMenu.setMenuId(coreMenu.getId());
        coreRoleMenuDao.deleteByTemplate(coreRoleMenu);
        coreMenuDao.deleteById(coreMenu.getId());
        return ResultMsg.ok();
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
        for (Long s : ids) {
            SysRoleMenu coreRoleMenu = new SysRoleMenu();
            coreRoleMenu.setMenuId(Long.valueOf(s));
            coreRoleMenuDao.deleteByTemplate(coreRoleMenu);
        }
        int i = coreMenuDao.deleteByIds(ids);
        return ResultMsg.ok(i);
    }

    /**
     * 编辑操作
     *
     * @param coreMenu
     * @return
     */
    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SysMenu coreMenu) {
        coreMenu.setUpdateTime(new Date());
        coreMenuDao.updateTemplateById(coreMenu);
        ResultMsg result = new ResultMsg();
        result.setData(coreMenu);
        return result;
    }

    /**
     * 查询单条数据操作
     *
     * @param coreMenu
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findById.json", method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateSysMenu(SysMenu coreMenu) {
        SysMenu menu = coreMenuDao.single(coreMenu.getId());
        ResultMsg result = new ResultMsg();
        result.setData(menu);
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
        List<SysMenu> coreMenus = coreMenuDao.all();
        ResultMsg result = new ResultMsg();
        result.setData(coreMenus);
        return result;
    }

    /**
     * 查询分页数据操作
     *
     * @param coreMenu
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute SysMenu coreMenu, @RequestParam Long pageNum,@RequestParam Long pageSize) {
        PageQuery<SysMenu> pageQuery = new PageQuery<SysMenu>();
        pageQuery.setPageSize((pageSize));
        pageQuery.setPageNumber((pageNum));
        pageQuery.setParas(coreMenu);
        coreMenuDao.templatePage(pageQuery);
        UiPageFrame pageFrame = new UiPageFrame();
        pageFrame.setList(pageQuery.getList());
        pageFrame.setPageNum((pageNum));
        pageFrame.setPageSize((pageSize));
        pageFrame.setPages(pageQuery.getTotalPage());
        pageFrame.setTotal(pageQuery.getTotalRow());
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }

    @Override
    protected String getModel() {
        return "sys/core/menu";
    }

}
