package org.elastos.record.manage.admin.core;


import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.service.SysButtonService;

import org.elastos.record.manage.controller.common.CommonWebController;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.SysButton;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 按钮相关控制器
 */

@Controller
@RequestMapping("sys/core/button")
public class CoreButtonController extends CommonWebController<SysButton, SysButtonService> {
    @Autowired
    SysButtonService coreButtonDao;


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
        ModelAndView view = getModelAndView("edit");
        return view;
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "add.html", method = {RequestMethod.GET})
    public ModelAndView add() {
        ModelAndView view = getModelAndView("add");
        return view;
    }

    /**
     * 添加操作
     *
     * @param coreButton
     * @return
     */
    @RequestMapping(value = "add.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(SysButton coreButton) {
        try {
            coreButtonDao.save(coreButton);
            ResultMsg result = new ResultMsg();
            result.setData(coreButton);
            return result;
        } catch (InsertNewInstanceException e) {
            e.printStackTrace();
            return null ;
        }
    }

    /**
     * 删除操作
     *
     * @param coreButton
     * @return
     */
    @RequestMapping(value = "delete.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(SysButton coreButton) {
        coreButtonDao.deleteById(coreButton.getId());
        ResultMsg result = new ResultMsg();
        return result;
    }

    /**
     * 批量删除操作
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteBatch.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch( @RequestParam(name = "id") List<Long> ids) {
        coreButtonDao.deleteByIds(ids);
        ResultMsg result = new ResultMsg();
        return result;
    }

    /**
     * 编辑操作
     *
     * @param coreButton
     * @return
     */
    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SysButton coreButton) {
        coreButtonDao.updateTemplateById(coreButton);
        ResultMsg result = new ResultMsg();
        result.setData(coreButton);
        return result;
    }

    /**
     * 查询单条数据操作
     *
     * @param coreButton
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findById.json", method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateSysMenu(SysButton coreButton) throws Exception {
        SysButton menu = coreButtonDao.single(coreButton.getId());
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
        List<SysButton> coreButtons = coreButtonDao.all();
        ResultMsg result = new ResultMsg();
        result.setData(coreButtons);
        return result;
    }

    /**
     * 查询分页数据操作
     *
     * @param coreButton
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute SysButton coreButton, @RequestParam Long pageNum, @RequestParam Long pageSize) {
        PageQuery<SysButton> pageQuery = new PageQuery<SysButton>();
        pageQuery.setPageSize((pageSize));
        pageQuery.setPageNumber((pageNum));
        pageQuery.setParas(coreButton);
        coreButtonDao.templatePage(pageQuery);
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
        return "sys/core/button";
    }

}
