package org.elastos.record.common.controller;

import org.beetl.sql.core.engine.PageQuery;

import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.entity.SysButton;
import org.elastos.record.biz.service.BaseService;
import org.elastos.record.biz.util.BeanUtils;
import org.elastos.record.biz.util.GenericsUtils;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.entity.CrudEntity;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.JsonResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BaseController<T extends CrudEntity, S extends BaseService<T>> implements ApplicationContextAware {

    protected S service;
    protected ApplicationContext applicationContext;


    @PostMapping({"add.do"})
    public JsonResult<T> add(@ModelAttribute T t) {
        try {
            T save = getService().save(t);
            return JsonResult.success(save);
        } catch (InsertNewInstanceException e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }



    @PostMapping("delete.do")
    public JsonResult<T> delete(@RequestParam Long id) {
        int i = getService().deleteById(id);
        return i > 0 ? JsonResult.success() : JsonResult.error(Action.DELETE_ERROR);
    }



    @PostMapping("deleteBatch.do")
    public JsonResult<T> deleteBatch(@RequestParam Long[] id) {
        S service = getService();
        boolean deleted = false;
        for (Long aLong : id) {
            if (aLong != null) {
                int i = service.deleteById(aLong);
                if (i > 0) {
                    deleted = true;
                }
            }
        }
        return deleted ? JsonResult.success() : JsonResult.error(Action.DELETE_ERROR);
    }


    @PostMapping(value = "info.json")
    public JsonResult<T> info(T t) {
        t = getService().templateOne(t);
        return JsonResult.success(t);
    }



    @PostMapping("edit.do")
    public JsonResult<T> edit(T t) {
        int i = getService().updateTemplateById(t);
        return i > 0 ? JsonResult.success() : JsonResult.error(Action.UPDATE_ERROR);
    }



    @PostMapping(value = {"all.list", "all.json"})
    public JsonResult<List<T>> all(T t) {
        List<T> template = getService().template(t);
        return JsonResult.success(template);
    }


    @PostMapping(value = {"all.page", "page.json"})
    public JsonResult<PageQuery<T>> all(@RequestParam long pageNum, @RequestParam long pageSize, T t) {
        PageQuery<T> pageQuery = new PageQuery<>(pageNum, pageSize);
        pageQuery.setParas(t);
        getService().templatePage(pageQuery);
        return JsonResult.success(pageQuery);
    }


    /**
     * 进入列表页面
     *
     * @return
     */

    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {

        return null;
    }

    /**
     * 进入编辑页面
     *
     * @return
     */

    @RequestMapping(value = "edit.html", method = {RequestMethod.GET})
    public ModelAndView edit() {
        return null;
    }

    /**
     * 进入新增页面
     *
     * @return
     */

    @RequestMapping(value = "add.html", method = {RequestMethod.GET})
    public ModelAndView add() {
        return null;
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
    public ResultMsg findById(SysButton coreButton) throws Exception {
        T menu = getService().single(coreButton.getId());
        ResultMsg result = new ResultMsg();
        result.setData(menu);
        return result;

    }


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dateFormat2.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat2, false));
    }


    @SuppressWarnings("unchecked")
    public S getService() {
        try {
            if (service == null) {
                Class<? extends BaseController> aClass = getClass();
                Class<S> serviceType = GenericsUtils.getGenericClass(aClass, 1);
                List<Field> fields = BeanUtils.getFieldsByType(this, serviceType);
                if (fields != null && fields.size() > 0) {
                    service = (S) BeanUtils.getDeclaredProperty(this, fields.get(0).getName());
                } else {
                    service = this.applicationContext.getBean(serviceType);
                }
            }

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return service;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
