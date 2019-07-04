package org.elastos.record.biz.service;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.utility.entity.NodeIncomeSend;
import org.elastos.record.utility.exception.InsertNewInstanceException;

import java.math.BigDecimal;
import java.util.List;

public interface NodeIncomeSendService extends BaseService<NodeIncomeSend> {
    /**
     * 查询最新的块号
     *
     * @return
     */
    Long queryNewHeight();

    /**
     * 根据条件查询总计发送金额
     *
     * @param address
     * @param startHeight
     * @param endHeight
     * @return
     */
    BigDecimal querySumValueByAddressAndHeight(@Param("address") String address,
                                               @Param("startHeight") Long startHeight,
                                               @Param("endHeight") Long endHeight);


    void  saveSendIncomeListAndUpdateShareList(List<NodeIncomeSend> list,Long startBlock,Long endBlock ) throws InsertNewInstanceException;


}
