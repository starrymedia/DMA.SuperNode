package org.elastos.record.biz.service.impl;

import org.elastos.record.biz.dao.NodeIncomeDao;
import org.elastos.record.biz.service.NodeIncomeService;
import org.elastos.record.utility.dto.IncomeDto;
import org.elastos.record.utility.entity.NodeIncome;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/20 16:09
 * @Description:
 */

@Service
@Transactional
public class NodeIncomeServiceImpl extends BaseServiceImpl<NodeIncome, NodeIncomeDao> implements NodeIncomeService {
    @Override
    public void checkNewInstance(NodeIncome instance) throws InsertNewInstanceException {

    }

    @Override
    public Long queryNewHeight() {
        return this.getEntityDao().queryNewHeight();
    }

    @Override
    public IncomeDto querySumByHeight(Long satrtHeight, Long endHeight) {
        return this.getEntityDao().querySumByHeight(satrtHeight,endHeight);
    }
}
