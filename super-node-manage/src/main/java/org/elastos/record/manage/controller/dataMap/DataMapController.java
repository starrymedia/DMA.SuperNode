package org.elastos.record.manage.controller.dataMap;

import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.service.DataMapService;
import org.elastos.record.manage.configuration.SecurityUtils;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.denum.DataMapEnum;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.DataMap;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Component
@RestController
@Slf4j
@RequestMapping("dataMap")
public class DataMapController {
    @Autowired
    private DataMapService dataMapService;

    @GetMapping("/edit.html")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView("sys/core/dataMap/edit.html");
        return modelAndView;
    }


    @GetMapping("/add.html")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("sys/core/dataMap/add.html");
        return modelAndView;
    }


    @RequestMapping("/index.html")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("sys/core/dataMap/index.html");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.print(username);
        return view;
    }

    @PostMapping("add.do")
    public ResultMsg add(DataMap dataMap) {
        DataMapEnum dataMapEnum = DataMapEnum.valueOf(dataMap.getName());
        if (dataMapEnum != null) {
            dataMap.setDescription(dataMapEnum.getDesc());
        }
        try {
            if (dataMapService.containsKey(dataMap.getName())) {
                return ResultMsg.fail(Action.KEY_EXISTS);
            } else {
                dataMapService.save(dataMap);
                return ResultMsg.ok(dataMap);
            }
        } catch (InsertNewInstanceException e) {
            e.printStackTrace();
            return ResultMsg.fail(e);
        }

    }

    @PostMapping("edit.do")
    public ResultMsg edit(DataMap dataMap) {

        try {
            if (dataMap.getId() == null) {
                return ResultMsg.fail(Action.SYSTEM_ERROR);
            }

            DataMap single = dataMapService.single(dataMap.getId());
            if (single == null) {
                return ResultMsg.fail(Action.SYSTEM_ERROR);
            } else {
                single.setName(dataMap.getName());
                single.setDescription(dataMap.getDescription());
                single.setValue(dataMap.getValue());
                single.setUpdateTime(new Date());
                dataMapService.updateTemplateById(single);
            }
            return ResultMsg.ok(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.fail();
        }

    }

    @PostMapping("page.json")
    public ResultMsg page(@RequestParam Long pageNum, @RequestParam Long pageSize, DataMap dataMap) {

        PageQuery<DataMap> query = new PageQuery<>();
        query.setPageSize(pageSize);
        query.setPageNumber(pageNum);
        query.setParas(dataMap);
        dataMapService.templatePage(query);
        UiPageFrame uiPageFrame = new UiPageFrame(query);
        return ResultMsg.ok(uiPageFrame);

    }

    @PostMapping("findById.json")
    public ResultMsg findById(@RequestParam Long id) {
        DataMap single = dataMapService.single(id);
        return ResultMsg.ok(single);

    }

    @PostMapping("/delete.do")
    public ResultMsg delete(@RequestParam(name = "id") List<Long> ids) {
        int i = dataMapService.deleteByIds(ids);
        return ResultMsg.ok(i);
    }

    @PostMapping("/dataList.json")
    @ResponseBody
    public ResultMsg dataList() {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (DataMapEnum value : DataMapEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("key", value.name());
            map.put("desc", value.getDesc());
            mapList.add(map);
        }
        return ResultMsg.ok(mapList);
    }


}
