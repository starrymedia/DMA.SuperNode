package org.elastos.record.manage.controller.common;


import lombok.extern.slf4j.Slf4j;
import org.elastos.record.biz.service.BaseService;
import org.elastos.record.biz.util.BeanUtils;
import org.elastos.record.biz.util.GenericsUtils;
import org.elastos.record.manage.configuration.NodeConfiguration;
import org.elastos.record.manage.configuration.SecurityUtils;
import org.elastos.record.manage.dto.SessionUser;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.CrudEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public abstract class CommonWebController<T extends CrudEntity, S extends BaseService<T>> {


    @Autowired
    protected NodeConfiguration nodeConfiguration;
    @Autowired
    private ApplicationContext applicationContext;


    protected S service;


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
                Class<?> aClass = getClass();
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

    protected abstract String getModel();

    protected ModelAndView getModelAndView(String view) {
        String format = String.format("/%s/%s.html", getModel(), view);
        log.info("ModelAndView: {} ", format.replaceAll(".html.html",".html").replaceAll("//+","/"));
        ModelAndView modelAndView = new ModelAndView();
        if(format.startsWith("//")){
            format=format.substring(1);
        }
        modelAndView.setViewName(format);
        return modelAndView;
    }


    protected SessionUser getSessionUser() {
        return SecurityUtils.getUser();
    }




   public class BaseController {
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
        public ModelAndView edit(String _mode) {

            return getModelAndView("edit");
        }

        /**
         * 进入新增页面
         *
         * @return
         */
        @RequestMapping(value = "add.html", method = {RequestMethod.GET})
        public ModelAndView addPage() {
            return getModelAndView("add");
        }


        /**
         * 添加操作
         *
         * @param
         * @return
         */
        @RequestMapping(value = "add.do", method = {RequestMethod.POST})
        @ResponseBody
        public ResultMsg doAdd() {

            ResultMsg result = new ResultMsg();
            return result;
        }

        /**
         * 删除操作
         *
         * @param
         * @return
         */
        @RequestMapping(value = "delete.do", method = {RequestMethod.POST})
        @ResponseBody
        public ResultMsg delete() {


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

            return ResultMsg.ok();
        }

        /**
         * 编辑操作
         *
         * @param
         * @return
         */
        @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
        @ResponseBody
        public ResultMsg update() {

            ResultMsg result = new ResultMsg();
            return result;
        }

        /**
         * 查询单条数据操作
         *
         * @param
         * @return
         * @throws Exception
         */
        @RequestMapping(value = "findById.json", method = {RequestMethod.POST}) //请求类型
        @ResponseBody
        public ResultMsg findById(@RequestParam Long id ) {

            ResultMsg result = new ResultMsg();
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
            ResultMsg result = new ResultMsg();
            return result;
        }

        /**
         * 查询分页数据操作
         *
         * @param pageFrame
         * @return
         */
        @RequestMapping(value = "page.json", method = {RequestMethod.POST})
        @ResponseBody
        public ResultMsg page(UiPageFrame pageFrame){

            return null;
        }
    }


}
