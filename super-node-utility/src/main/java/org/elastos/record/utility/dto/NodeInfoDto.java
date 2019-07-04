package org.elastos.record.utility.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/13 14:43
 * @Description:
 */

@Getter
@Setter
public class NodeInfoDto implements Serializable {

    private String producerPublicKey;
    private String value;
    private String address;
    private Integer rank;
    private String ownerPublicKey;
    private String nodePublicKey;
    private String nickName;
    private String url;
    private String location;
    private Boolean active;
    private String votes;
    private String netAddress;
    private String state;
    private String registerHeight;
    private String cancelHeight;
    private String inactiveHeight;
    private String illegalHeight;
    private String index;
    private String reward;
    private String estRewardPerYear;



}
