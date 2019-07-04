package org.elastos.record.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elastos.dma.utility.dmaenum.Currency;
import org.elastos.record.biz.service.NodeIncomeSendService;
import org.elastos.record.biz.service.NodeIncomeService;
import org.elastos.record.biz.service.ShareService;
import org.elastos.record.biz.service.VotersService;
import org.elastos.record.manage.configuration.ApiConfiguration;
import org.elastos.record.manage.configuration.NodeConfiguration;
import org.elastos.record.manage.util.MarketUtils;
import org.elastos.record.utility.dto.IncomeDto;
import org.elastos.record.utility.dto.NodeInfoDto;
import org.elastos.record.utility.dto.VotersDto;
import org.elastos.record.utility.entity.Share;
import org.elastos.record.utility.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elastos.record.manage.constant.Api.VERSION;
import static org.elastos.record.manage.task.NodeScheduled.CREATION_BLOCK;
import static org.elastos.record.manage.task.NodeScheduled.ROUND_BLOCK;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/21 09:59
 * @Description:
 */
@Controller
@Slf4j
@RequestMapping(VERSION)
public class ApiController {

    @Autowired
    private NodeConfiguration nodeConfiguration;

    @Autowired
    private ApiConfiguration apiConfiguration;

    @Autowired
    private NodeIncomeService nodeIncomeService;

    @Autowired
    private NodeIncomeSendService nodeIncomeSendService;

    @Autowired
    private ShareService shareService;


    @RequestMapping(value = "nodeInfo.json", method = {RequestMethod.GET})
    @ResponseBody
    public JsonResult htmlApi(String address) {

        Map<String, Object> map = new HashMap<>();
        //节点投票信息
        nodeVoteInfo(map, address);
        //节点收益信息
        nodeIncomeInfo(map, address);
        Long sendRound = nodeConfiguration.getSendRound();
        if (sendRound != null) {
            Long startheight = nodeIncomeSendService.queryNewHeight();
            Long endHeight = Long.valueOf(0);

            if (startheight == null) {
                startheight = CREATION_BLOCK - 36;
                endHeight = ROUND_BLOCK * (sendRound - 1) + CREATION_BLOCK + 1;
            } else {
                endHeight = ROUND_BLOCK * sendRound + startheight + 1;
            }

            Long newBlockHeight = Long.valueOf(apiConfiguration.getHeight());
            map.put("startBlockHeight", startheight);
            map.put("estimateSendBlockHeight", endHeight);
            map.put("newBlockHeight", newBlockHeight);

        }

        BigDecimal ELARate = MarketUtils.getRate(Currency.ELA, Currency.USD);
        map.put("ELARate", ELARate.toString());
        return JsonResult.success(map);
    }


    /**
     * 节点投票信息
     */
    private void nodeVoteInfo(Map<String, Object> map, String address) {
        BigDecimal votes = BigDecimal.ZERO;

        //节点排名列表
        List<NodeInfoDto> list = apiConfiguration.getNodeList();
        //我们节点拥有者公钥
        String ProducerPublicKey = nodeConfiguration.getProducerPublicKey();
        if (list != null && list.size() > 0) {
            for (NodeInfoDto var : list) {
                //我们节点信息
                if (ProducerPublicKey.equals(var.getProducerPublicKey())) {
                    map.put("rank", var.getRank());//节点排名
                    map.put("votes", var.getValue());//投票数
                    votes = new BigDecimal(var.getValue());
                }
            }
        }
        List<VotersDto> votersDtoList = apiConfiguration.getVoters(ProducerPublicKey);
        if (votersDtoList != null && votersDtoList.size() > 0) {
            map.put("voters", votersDtoList.size());//投票人数
            if (!StringUtils.isBlank(address)) {
                for (VotersDto votersDto : votersDtoList) {
                    String votersAddress = votersDto.getAddress();
                    if (votersAddress.equals(address)) {
                        BigDecimal myVotes = new BigDecimal(votersDto.getValue());
                        map.put("myVotes", votersDto.getValue());//投票数
                        BigDecimal voteRate = myVotes.divide(votes, 7, BigDecimal.ROUND_HALF_UP);
                        voteRate = voteRate.multiply(new BigDecimal("100")).setScale(5, BigDecimal.ROUND_HALF_UP);
                        map.put("voteRate", voteRate);//投票占比
                    }


                }


            }

        }
    }


    /**
     * 节点收益信息
     */
    private void nodeIncomeInfo(Map<String, Object> map, String address) {

        IncomeDto nodeIncomeSum = nodeIncomeService.querySumByHeight(null, null);
        if (nodeIncomeSum != null) {
            map.put("nodeIncome", nodeIncomeSum.getIncomeValue());//节点总收益
            map.put("managementIncome", nodeIncomeSum.getManagementValue());//维护总费用
            map.put("teamIncome", nodeIncomeSum.getTeamValue());//团队总收益
            map.put("communityIncome", nodeIncomeSum.getCommunityValue());//社区总费用
        }

        Long height = nodeIncomeSendService.queryNewHeight();
        IncomeDto communityIncomeSum = nodeIncomeService.querySumByHeight(height+1, null);
        map.put("nowCommunityIncome", communityIncomeSum.getCommunityValue());//当前未分配的社区收益
        map.put("nowManagementIncome", communityIncomeSum.getManagementValue());//当前未分配的维护费用
        map.put("nowTeamIncome", communityIncomeSum.getTeamValue());//当前未分配的团队收益
        map.put("nowNodeIncome", communityIncomeSum.getIncomeValue());//当前未分配的总收益

        if (!StringUtils.isBlank(address)) {
            BigDecimal myTotalIncome = nodeIncomeSendService.querySumValueByAddressAndHeight(address, null, null);
            if (myTotalIncome == null) {
                myTotalIncome = BigDecimal.ZERO;
            }
            map.put("myTotalIncome", myTotalIncome);//我的总收益
            List<Share> shareList = shareService.querySumBalance(address, false, null, null);
            if (shareList != null && shareList.size() > 0) {
                BigDecimal myEstimateIncome = shareList.get(0).getBalance();
                map.put("myEstimateIncome", myEstimateIncome);//我的预计收益
            } else {
                map.put("myEstimateIncome", BigDecimal.ZERO);//我的预计收益
            }


        }


    }

}
