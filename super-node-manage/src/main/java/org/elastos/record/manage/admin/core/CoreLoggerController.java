package org.elastos.record.manage.admin.core;

import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.service.SysLoggerService;

import org.elastos.record.manage.controller.common.CommonWebController;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.SysLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 日志相关控制器
 */
@Controller
@RequestMapping("sys/core/logger")
public class CoreLoggerController extends CommonWebController<SysLogger,SysLoggerService> {
    @Autowired
    SysLoggerService sysLoggerService;

    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index(){
        return getModelAndView("index");
    }

    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value="edit.html",method = {RequestMethod.GET})
    public ModelAndView edit(){
        return getModelAndView("edit");
    }



    /**
     * 删除操作
     * @param coreLogger
     * @return
     */
    @RequestMapping(value = "delete.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(SysLogger coreLogger) {
        sysLoggerService.deleteById(coreLogger.getId());
        return new ResultMsg();
    }

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteBatch.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(@RequestParam("id") List<Long> ids) {
        int i = sysLoggerService.deleteByIds(ids);
        return new ResultMsg(i);
    }

    /**
     * 编辑操作
     * @param coreLogger
     * @return
     */
    @RequestMapping(value = "edit.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SysLogger coreLogger) {
        int i = sysLoggerService.updateTemplateById(coreLogger);
        ResultMsg result=new ResultMsg();
        result.setData(coreLogger);
        return result;
    }

    /**
     * 查询单条数据操作
     * @param coreLogger
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findById.json",method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateCoreMenu(SysLogger coreLogger) throws Exception{
        SysLogger menu=sysLoggerService.single(coreLogger.getId());
        ResultMsg result=new ResultMsg();
        result.setData(menu);
        return result;

    }

    /**
     * 查询全部数据操作
     * @return
     */
    @RequestMapping(value="all.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all(){
        List<SysLogger> coreLoggers =sysLoggerService.all();
        ResultMsg result=new ResultMsg();
        result.setData(coreLoggers);
        return  result;
    }

    /**
     * 查询分页数据操作
     * @param coreLogger
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="page.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute SysLogger coreLogger,  @RequestParam Long pageNum, @RequestParam  Long pageSize){
        PageQuery<SysLogger> pageQuery=new PageQuery<>();
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageNumber(pageNum);
        pageQuery.setParas(coreLogger);
        sysLoggerService.templatePage(pageQuery);
        UiPageFrame pageFrame=new UiPageFrame();
        pageFrame.setList(pageQuery.getList());
        pageFrame.setPageNum(pageNum);
        pageFrame.setPageSize(pageSize);
        pageFrame.setPages(pageQuery.getTotalPage());
        pageFrame.setTotal(pageQuery.getTotalRow());
        ResultMsg result=new ResultMsg();
        result.setData(pageFrame);
        return  result;
    }

    @Override
    protected String getModel() {
        return "sys/core/logger";
    }
}
