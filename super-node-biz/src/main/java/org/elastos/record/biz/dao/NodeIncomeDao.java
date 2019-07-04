package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.elastos.record.utility.dto.IncomeDto;
import org.elastos.record.utility.entity.NodeIncome;
import org.springframework.stereotype.Component;


/**
 * gen by hb.nie mapper 2019-06-20 16:02:04
 */
@Component
public interface NodeIncomeDao extends BaseDao<NodeIncome> {
    Long queryNewHeight();

   IncomeDto querySumByHeight(@Param("startHeight")Long satrtHeight,
                              @Param("endHeight")Long endHeight);
}
