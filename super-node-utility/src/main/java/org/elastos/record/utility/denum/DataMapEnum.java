package org.elastos.record.utility.denum;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/21 14:11
 * @Description:
 */
public enum DataMapEnum {

    MANAGEMENT_FEE("服务管理费用"),
    SHARE_RATE("社区分配比例"),
    SEND_ROUND("奖励发送轮次"),
    WALLET_PWD("钱包密码"),
    ELA_WALLET_API_URL("亦来云钱包API URL"),
    ELA_NODE_API_URL("亦来云节点API URL"),
    ELA_MEMO("亦来云转账备注"),
    ;

    String desc;


    DataMapEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
