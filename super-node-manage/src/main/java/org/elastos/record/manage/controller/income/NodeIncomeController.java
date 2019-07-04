package org.elastos.record.manage.controller.income;

import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.service.NodeIncomeService;
import org.elastos.record.manage.controller.common.CommonWebController;
import org.elastos.record.manage.controller.income.dto.NodeIncomeDto;
import org.elastos.record.utility.dto.ResultMsg;
import org.elastos.record.utility.dto.UiPageFrame;
import org.elastos.record.utility.entity.NodeIncome;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hb.nie
 * 节点收益
 */

@Controller
@RequestMapping("/nodeIncome")
public class NodeIncomeController extends CommonWebController<NodeIncome, NodeIncomeService> {
    @Override
    protected String getModel() {
        return "myNode/income/node";
    }

    /**
     * 进入列表页面
     *
     * @return
     */
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return getModelAndView("nodeIncome");
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
    public ResultMsg findById(@RequestParam Long id) {
        ResultMsg result = new ResultMsg();
        result.setData(getService().single(id));
        return result;

    }


    /**
     * 查询分页数据操作
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@RequestParam Long pageNum, @RequestParam Long pageSize, NodeIncome nodeIncome) {
        PageQuery<NodeIncome> templatePage = new PageQuery<>();
        templatePage.setParas(nodeIncome);
        templatePage.setPageNumber(pageNum);
        templatePage.setPageSize(pageSize);
        getService().templatePage(templatePage);

        List<NodeIncome> templatePageList = templatePage.getList();
        List<NodeIncomeDto> list = new ArrayList<>(templatePageList.size());
        for (NodeIncome income : templatePageList) {
            NodeIncomeDto nodeIncomeDto = new NodeIncomeDto();
            BeanUtils.copyProperties(income,nodeIncomeDto);
            list.add(nodeIncomeDto);
        }
        templatePageList.clear();
        templatePage.setList(list);
        return ResultMsg.ok(new UiPageFrame(templatePage));
    }
}
