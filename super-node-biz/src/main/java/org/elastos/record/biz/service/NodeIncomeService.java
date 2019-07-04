package org.elastos.record.biz.service;

import org.beetl.sql.core.annotatoin.Param;
import org.elastos.record.utility.dto.IncomeDto;
import org.elastos.record.utility.entity.NodeIncome;

public interface NodeIncomeService extends BaseService<NodeIncome> {
    /**
     * 查询最新的块号
     *
     * @return
     */
    Long queryNewHeight();

    /**
     * 根据块号获取收益统计
     *
     * @param satrtHeight 开始块高
     * @param endHeight   结束块高
     * @return
     */
    IncomeDto querySumByHeight(Long satrtHeight, Long endHeight);



}
