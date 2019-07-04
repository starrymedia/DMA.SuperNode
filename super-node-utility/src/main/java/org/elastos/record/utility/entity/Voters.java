
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * gen by hn.nie 2019-06-14
 */

@Table(name = "voters")
@Data
@EqualsAndHashCode(callSuper = true)
public class Voters extends CrudEntity {
    private static final long serialVersionUID = 123456789L;


    /**
     * 投票人地址
     */
    private String address;
    /**
     * 当前节点高度
     */
    private Long blockHeight;


    /**
     * 当前块投票总数
     */
    private BigDecimal totalValue;
    /**
     * 投票txid
     */
    private String txid;

    /**
     * 投票块高度
     */
    private Long voteHeight;
    /**
     * 投票时间时间
     */
    private Long voteTime;
    /**
     * 投票数
     */
    private BigDecimal voteValue;

}
