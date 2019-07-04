package org.elastos.record.utility.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/13 17:06
 * @Description:
 */

@Setter
@Getter
public class VotersDto implements Serializable {

    private String producerPublicKey;
    private String voteType;
    private String txid;
    private String value;
    private String address;
    private Date time;
    private String height;

}
