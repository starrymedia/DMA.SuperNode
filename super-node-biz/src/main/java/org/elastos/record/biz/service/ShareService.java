package org.elastos.record.biz.service;

import org.beetl.sql.core.annotatoin.Param;
import org.elastos.record.utility.entity.Share;

import java.math.BigDecimal;
import java.util.List;

public interface ShareService extends BaseService<Share> {

    /**
     * 根据条件查询分配金额总和
     *
     * @param address     地址
     * @param isSend      是否发放
     * @param startHeight 开始块
     * @param endHeight   结束块
     * @return
     */
    List<Share> querySumBalance(String address, Boolean isSend, Long startHeight, Long endHeight);

    int updateSendStatusByBlockHeight( String address, Boolean isSend, Long startHeight, Long endHeight);
}
