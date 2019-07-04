package org.elastos.record.manage.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elastos.dma.service.wallet.ElaWalletService;
import org.elastos.record.biz.service.*;
import org.elastos.record.manage.configuration.ApiConfiguration;
import org.elastos.record.manage.configuration.NodeConfiguration;
import org.elastos.record.manage.util.KeystoreFile;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.dto.NodeInfoDto;
import org.elastos.record.utility.dto.VotersDto;
import org.elastos.record.utility.entity.*;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/14 10:44
 * @Description:
 */

@Slf4j
@Component
@Configuration
@EnableScheduling
@EnableAsync
public class NodeScheduled {

    public final static Long CREATION_BLOCK = 402715L;//dpos开始块
    public final static Long ROUND_BLOCK = 36L;//轮询块


    @Autowired
    private VotersService votersService;
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


    @Scheduled(cron = "0 0/1 * * * ?")
    public void voteScheduled() {
        try {
            Long nowBlockHeight = votersService.queryNewHeight();//数据库当前块高度
            Long blockHeight = Long.valueOf(apiConfiguration.getHeight());//节点当前块高度
            Long checkHeight = null;
            if (nowBlockHeight == null) {
                checkHeight = CREATION_BLOCK;
            } else {
                checkHeight = nowBlockHeight + ROUND_BLOCK;
            }

            if (blockHeight >= checkHeight) {

                List<NodeInfoDto> list = apiConfiguration.getNodeListByHeight(checkHeight);
                String ProducerPublicKey = nodeConfiguration.getProducerPublicKey();
                NodeInfoDto nodeInfoDto = null;
                if (list != null && list.size() > 0 && !StringUtils.isBlank(ProducerPublicKey)) {
                    for (NodeInfoDto var : list) {
                        if (ProducerPublicKey.equals(var.getProducerPublicKey())) {
                            nodeInfoDto = var;
                        }
                    }
                }

                if (nodeInfoDto != null) {
                    BigDecimal totalValue = new BigDecimal(nodeInfoDto.getValue());
                    List<VotersDto> votersDtos = apiConfiguration.getVoters(nodeConfiguration.getProducerPublicKey());
                    if (votersDtos != null && votersDtos.size() > 0) {
                        for (VotersDto var : votersDtos) {
                            Long voteHeight = Long.valueOf(var.getHeight());
                            if (voteHeight <= checkHeight) {
                                Voters voters = new Voters();
                                voters.setAddress(var.getAddress());
                                voters.setVoteHeight(Long.valueOf(var.getHeight()));
                                voters.setTotalValue(totalValue);
                                voters.setVoteTime(var.getTime().getTime() / 1000);
                                voters.setVoteValue(new BigDecimal(var.getValue()));
                                voters.setTxid(var.getTxid());
                                voters.setBlockHeight(checkHeight);
                                voters.setDeleted(false);
                                voters.setCreateTime(new Date());
                                votersService.save(voters);
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Scheduled(cron = "0 0/1 * * * ?")
    public void incomeScheduled() {
        try {
            Long nowBlockHeight = nodeIncomeService.queryNewHeight();//数据库当前块高度
            Long blockHeight = Long.valueOf(apiConfiguration.getHeight());//节点当前块高度

            Long checkHeight = null;
            if (nowBlockHeight == null) {
                checkHeight = CREATION_BLOCK + 1;
            } else {
                checkHeight = nowBlockHeight + ROUND_BLOCK;
            }

            if (blockHeight >= checkHeight) {
                JSONObject tx = apiConfiguration.getMiningTransactionsByHeight(checkHeight);
                if (tx != null) {

                    BigDecimal income = BigDecimal.ZERO;
                    JSONArray voutArray = tx.getJSONArray("vout");
                    for (int i = 0; i < voutArray.size(); i++) {
                        JSONObject vout = voutArray.getJSONObject(i);
                        String address = vout.getString("address");
                        BigDecimal value = vout.getBigDecimal("value");//节点总收益
                        if (address.equals(nodeConfiguration.getProducerAddress())) {
                            income = value;
                        }
                    }

                    try {
                        getCommunityValue(checkHeight, income);
                    } catch (InsertNewInstanceException e) {
                        e.printStackTrace();
                        return;
                    }
                }

            }
            sendIncome(checkHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 计算社区收益金额，并保存节点收益记录
     *
     * @param blockHeight
     * @param value
     * @return
     * @throws InsertNewInstanceException
     */

    private void getCommunityValue(Long blockHeight, BigDecimal value) throws InsertNewInstanceException {
        BigDecimal managementValue = getManagementValue(blockHeight);//节点维护费用
        BigDecimal shareRate = nodeConfiguration.getShareRate();
        if (managementValue != null && shareRate != null) {
            BigDecimal incomeValue = value;//节点总收益
            BigDecimal teamValue = BigDecimal.ZERO;//团队收益
            BigDecimal communityValue = BigDecimal.ZERO;//社区收益
            BigDecimal sendCommunityValue = BigDecimal.ZERO;//实际发送的社区金额

            if (value.compareTo(managementValue) != 1) {//节点收益少于或等于节点维护费用

                managementValue = value;

            } else {//节点收益大于节点维护费用

                //用于分配收益=节点总收益-节点维护费用
                BigDecimal share = incomeValue.subtract(managementValue);

                //计算社区收益=分配收益*分配比例，
                communityValue = share.multiply(shareRate);
                //并保留8位小数
                communityValue = scale(communityValue, 8);

            }
            //分配给社区金额
            BigDecimal communityIncomeValue = communityIncome(blockHeight, communityValue);
            if (communityIncomeValue != null) {
                sendCommunityValue = communityIncomeValue;
                //团队收益=节点总收益-实际发送的社区金额-节点维护费用
                teamValue = incomeValue.subtract(sendCommunityValue).subtract(managementValue);

                NodeIncome nodeIncome = new NodeIncome();
                nodeIncome.setBlockHeight(blockHeight);
                nodeIncome.setCommunityValue(sendCommunityValue);
                nodeIncome.setIncomeValue(incomeValue);
                nodeIncome.setManagementValue(managementValue);
                nodeIncome.setTeamValue(teamValue);
                nodeIncome.setDeleted(false);
                nodeIncome.setCreateTime(new Date());
                nodeIncomeService.save(nodeIncome);
            }
        } else {
            throw new InsertNewInstanceException(Action.YSY_CONFIG_ERROR);
        }


    }


    public void sendIncome(Long blockHeight) {
        try {

            Long round = nodeConfiguration.getSendRound();
            if (round != null) {
                Long newHeight = nodeIncomeSendService.queryNewHeight();
                Long startBlockHeight = null;
                Long endBlockHeight = null;
                if (newHeight == null) {
                    startBlockHeight = CREATION_BLOCK - 35;
                    endBlockHeight = CREATION_BLOCK + (round - 1) * ROUND_BLOCK;
                } else {
                    startBlockHeight = newHeight + 1;
                    endBlockHeight = newHeight + ROUND_BLOCK * round;
                }

                if (blockHeight >= endBlockHeight) {

                    String pwd = nodeConfiguration.getWalletPwd();
                    boolean isExist = KeystoreFile.isExistKeystoreFile();
                    if (pwd == null || !isExist) {
                        System.out.println("钱包文件不存在");
                        return;
                    }
                    String privateKey = KeystoreFile.getAccountPrivateKey(pwd);
                    String nodeApiUrl = nodeConfiguration.getNodeApiUrl();
                    String memo = nodeConfiguration.getMemo();
                    if (nodeApiUrl != null && round != null && privateKey != null) {
                        List<Share> shareList = shareService.querySumBalance(null, false, startBlockHeight, endBlockHeight + 1);
                        List<String> addressList = new ArrayList<>();
                        List<BigDecimal> valueList = new ArrayList<>();
                        List<NodeIncomeSend> nodeIncomeSendList = new ArrayList<>();
                        if (shareList != null && shareList.size() > 0) {
                            for (Share share : shareList) {
                                addressList.add(share.getAddress());
                                valueList.add(share.getBalance());
                                NodeIncomeSend nodeIncomeSend = new NodeIncomeSend();
                                nodeIncomeSend.setBlockHeightStart(startBlockHeight);
                                nodeIncomeSend.setBlockHeightEnd(endBlockHeight);
                                nodeIncomeSend.setReceiveAddress(share.getAddress());
                                nodeIncomeSend.setSendValue(share.getBalance());
                                nodeIncomeSend.setDeleted(false);
                                nodeIncomeSend.setCreateTime(new Date());
                                nodeIncomeSendList.add(nodeIncomeSend);
                            }
                        }

                        ElaWalletService elaWalletService = new ElaWalletService(nodeApiUrl);

                        try {
                            String elaMemo = "";
                            if (!StringUtils.isBlank(memo)) {
                                elaMemo = "type:text,msg:" + memo;
                            }
                            System.out.println("****************** MEMO:" + elaMemo + "*******************");

                            String data = new String(elaMemo.getBytes(), "UTF-8");
                            String restul = elaWalletService.transferWithArray(privateKey, addressList, valueList, data);
                            JSONObject jsonObject = JSON.parseObject(restul);
                            Integer status = jsonObject.getInteger("status");

                            if (status != null && status == 200) {
                                String txid = jsonObject.getString("result");
                                System.out.println("****************** TXID:" + txid + "*******************");
                                for (NodeIncomeSend nodeIncomeSend : nodeIncomeSendList) {
                                    nodeIncomeSend.setTxid(txid);
                                    nodeIncomeSend.setSendAddress(elaWalletService.exportByPrivateKey(privateKey).getAddress());
                                }
                                nodeIncomeSendService.saveSendIncomeListAndUpdateShareList(nodeIncomeSendList, startBlockHeight, endBlockHeight + 1);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 计算社区收益
     *
     * @param blockHeight
     * @param value
     */
    private BigDecimal communityIncome(Long blockHeight, BigDecimal value) throws InsertNewInstanceException {

        Voters param = new Voters();
        param.setBlockHeight(blockHeight - 1);
        param.setDeleted(false);
        //总发出金额
        BigDecimal totalCommunityValue = BigDecimal.ZERO;
        List<Voters> votersList = votersService.template(param);
        if (votersList != null && votersList.size() > 0) {
            for (Voters voters : votersList) {
                BigDecimal vote = voters.getVoteValue();//投票数
                BigDecimal totalVote = voters.getTotalValue();//总票数
                String address = voters.getAddress();
                BigDecimal balance = BigDecimal.ZERO;
                //社区总收益大于0
                if (value.compareTo(BigDecimal.ZERO) == 1) {
                    //每票多少ela = 总收入/总票数
                    BigDecimal rate = divide(value, totalVote, 11);
                    //实际获取金额=投票数*每票多少ela
                    balance = vote.multiply(rate);
                    //保留8位小数
                    balance = scale(balance, 8);
                    //计算总发出金额
                    totalCommunityValue = totalCommunityValue.add(balance);
                }
                //保存记录
                Share share = new Share();
                share.setAddress(address);
                share.setBalance(balance);
                share.setBlockHeight(blockHeight);
                share.setVoteValue(vote);
                share.setVoteTotalValue(totalVote);
                share.setIsSend(false);
                share.setDeleted(false);
                share.setCreateTime(new Date());
                shareService.save(share);
            }
            return totalCommunityValue;
        }
        return null;
    }

    //配置值为每分钟多少ela，需要计算两个块直接多少分钟
   /* private BigDecimal getManagementValue(Long blockHeight) {
        BigDecimal managementValue = nodeConfiguration.getManagementFee();//节点维护费用
        JSONObject endTx = apiConfiguration.getMiningTransactionsByHeight(blockHeight);
        JSONObject startTx = apiConfiguration.getMiningTransactionsByHeight(blockHeight - ROUND_BLOCK);
        if (endTx != null && startTx != null) {
            Long endTime = endTx.getLong("time");
            Long startTime = startTx.getLong("time");
            BigDecimal time = divide(BigDecimal.valueOf(endTime - startTime), BigDecimal.valueOf(60), 2);
            return time.multiply(managementValue).setScale(8, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }*/

    // 配置值为每轮次维护费用我固定ela
    private BigDecimal getManagementValue(Long blockHeight) {
        BigDecimal managementValue = nodeConfiguration.getManagementFee();//节点维护费用
        return managementValue;
    }


    private BigDecimal divide(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.divide(var2, scale, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal scale(BigDecimal var, int scale) {
        return var.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

}
