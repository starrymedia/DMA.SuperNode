package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.elastos.record.utility.entity.NodeIncomeSend;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


/**
 * gen by hb.nie mapper 2019-06-20 16:02:25
 */
@Component
public interface NodeIncomeSendDao extends BaseDao<NodeIncomeSend> {
    Long queryNewHeight();

   BigDecimal querySumValueByAddressAndHeight(@Param("address")String address,
                                              @Param("startHeight")Long startHeight,
                                              @Param("endHeight")Long endHeight);

    void templatePage(PageQuery<NodeIncomeSend> query);
}
