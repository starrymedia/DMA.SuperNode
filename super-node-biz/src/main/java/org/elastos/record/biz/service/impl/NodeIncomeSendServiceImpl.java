package org.elastos.record.biz.service.impl;

import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.dao.NodeIncomeSendDao;
import org.elastos.record.biz.service.NodeIncomeSendService;
import org.elastos.record.biz.service.ShareService;
import org.elastos.record.utility.entity.NodeIncomeSend;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/20 16:09
 * @Description:
 */

@Service
@Transactional
public class NodeIncomeSendServiceImpl extends BaseServiceImpl<NodeIncomeSend, NodeIncomeSendDao> implements NodeIncomeSendService {

    @Autowired
    private ShareService shareService;

    @Override
    public void checkNewInstance(NodeIncomeSend instance) throws InsertNewInstanceException {

    }

    @Override
    public Long queryNewHeight() {
        return this.getEntityDao().queryNewHeight();
    }

    @Override
    public BigDecimal querySumValueByAddressAndHeight(String address, Long startHeight, Long endHeight) {
        return this.getEntityDao().querySumValueByAddressAndHeight(address, startHeight, endHeight);
    }

    @Override
    public void saveSendIncomeListAndUpdateShareList(List<NodeIncomeSend> list, Long startBlock, Long endBlock) throws InsertNewInstanceException {

        for (NodeIncomeSend nodeIncomeSend:list){
            this.save(nodeIncomeSend);
        }
        shareService.updateSendStatusByBlockHeight(null, true, startBlock, endBlock);
    }

}
