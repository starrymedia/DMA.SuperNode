package org.elastos.record.manage.configuration;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.elastos.record.biz.service.DataMapService;
import org.elastos.record.manage.util.MarketUtils;
import org.elastos.record.utility.denum.DataMapEnum;
import org.elastos.record.utility.entity.DataMap;
import org.elastos.record.utility.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;

@Configuration
@ConfigurationProperties(prefix = "node.config")
@Setter
@Getter
public class NodeConfiguration {


    @Autowired
    private DataMapService dataMapService;

    private String baseUrl;
    private String walletApiUrl;
    private String nodeApiUrl;
    private String producerPublicKey;
    private String producerAddress;
    private BigDecimal managementFee;
    private BigDecimal shareRate;
    private Long sendRound;
    private String walletPwd;
    private String memo;


    public String getWalletApiUrl() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.ELA_WALLET_API_URL.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            return dataMap.getValue();
        }
        return null;
    }

    public String getNodeApiUrl() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.ELA_NODE_API_URL.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            return dataMap.getValue();
        }
        return null;
    }

    //配置值为每轮次维护费用我固定ela
    public BigDecimal getManagementFee() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.MANAGEMENT_FEE.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            return new BigDecimal(dataMap.getValue());
        }
        return null;
    }

    //配置值为每分钟多少美元，需要将美元兑换为ela
    /*public BigDecimal getManagementFee() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.MANAGEMENT_FEE.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            try {
                return MarketUtils.convertUsdToEla(new BigDecimal(dataMap.getValue()));
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
        return null;
    }*/

    public BigDecimal getShareRate() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.SHARE_RATE.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {

            return new BigDecimal(dataMap.getValue());
        }
        return null;
    }

    public Long getSendRound() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.SEND_ROUND.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            return Long.valueOf(dataMap.getValue());
        }
        return null;
    }

    public String getWalletPwd() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.WALLET_PWD.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            return dataMap.getValue();
        }
        return null;
    }

    public String getMemo() {
        DataMap dataMap = dataMapService.findOneByKey(DataMapEnum.ELA_MEMO.name());
        if (dataMap != null && !StringUtils.isBlank(dataMap.getValue())) {
            return dataMap.getValue();
        }
        return null;
    }
}
