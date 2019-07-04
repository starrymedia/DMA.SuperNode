package org.elastos.record.manage.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.service.VotersService;
import org.elastos.record.manage.configuration.ApiConfiguration;
import org.elastos.record.manage.configuration.NodeConfiguration;
import org.elastos.record.utility.dto.NodeInfoDto;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.dto.VotersDto;
import org.elastos.record.utility.entity.Voters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/13 14:01
 * @Description:
 */

@Controller
@Slf4j
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeConfiguration nodeConfiguration;

    @Autowired
    private ApiConfiguration apiConfiguration;


    @Autowired
    private VotersService votersService;

    /**
     * 节点列表
     * @return
     */
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView indexHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/node/index.html");
        return modelAndView;
    }


    /**
     * 我的节点列表
     * @return
     */
    @RequestMapping(value = "myIndex.html", method = {RequestMethod.GET})
    public ModelAndView MyNodeindexHtml() {
        ModelAndView modelAndView = new ModelAndView();
        List<NodeInfoDto> list = apiConfiguration.getNodeList();
        String ProducerPublicKey = nodeConfiguration.getProducerPublicKey();
        if (list != null && list.size() > 0) {
            for (NodeInfoDto var : list) {
                if (ProducerPublicKey.equals(var.getProducerPublicKey())) {
                    modelAndView.addObject("NodeInfoDto", var);
                }
            }
        }
        List<VotersDto> votersDtoList = apiConfiguration.getVoters(ProducerPublicKey);
        modelAndView.addObject("size", votersDtoList.size());
        modelAndView.setViewName("/myNode/index.html");
        return modelAndView;
    }

    /**
     * 投票列表
     * @return
     */
    @RequestMapping(value = "voters.html", method = {RequestMethod.GET})
    public ModelAndView votersHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/myNode/voters.html");
        return modelAndView;
    }


    /**
     * 投票信息列表
     * @param producerPublicKey
     * @return
     */
    @RequestMapping(value = "info.html", method = {RequestMethod.GET})
    public ModelAndView infoHtml(String producerPublicKey) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/node/info.html");
        modelAndView.addObject("producerPublicKey", producerPublicKey);
        return modelAndView;
    }


    @RequestMapping(value = "list.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg list(String address) {
        if (StringUtils.isBlank(address)) {
            List<NodeInfoDto> list = apiConfiguration.getNodeList();
            return ResultMsg.ok(list);
        } else {
            List<NodeInfoDto> list = apiConfiguration.getNodeListByAddress(address);
            return ResultMsg.ok(list);
        }

    }



    @RequestMapping(value = "info.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg info(String producerPublicKey) {
        List<VotersDto> list = apiConfiguration.getVoters(producerPublicKey);
        return ResultMsg.ok(list);
    }






    @RequestMapping(value = "voters.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg voters(@RequestParam Long pageNum, @RequestParam Long pageSize, String address, String createTime, String endTime) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date create = null;
        Date end = null;
        try {
            if (!StringUtils.isBlank(createTime) && !StringUtils.isBlank(createTime)) {
                create = simpleDateFormat.parse(createTime);
                end = simpleDateFormat.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageQuery<Voters> pageQuery = votersService.queryList(pageNum, pageSize, address,create,end);
        return result(pageQuery, pageNum, pageSize);
    }


    /**
     * 进入列表页面
     *
     * @return
     */
    @RequestMapping(value = "votersInfo.html", method = {RequestMethod.GET})
    public ModelAndView votersInfoHtml(String address) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/myNode/votersInfo.html");
        modelAndView.addObject("address", address);
        return modelAndView;
    }


    @RequestMapping(value = "votersInfo.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg votersInfo(@RequestParam Long pageNum, @RequestParam Long pageSize, String address) {
        PageQuery<Voters> pageQuery = votersService.queryInfoList(pageNum, pageSize, address);
        return result(pageQuery, pageNum, pageSize);
    }

    private ResultMsg result(PageQuery pageQuery, Long pageNum, Long pageSize) {
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

}
