package org.elastos.record.biz.service.impl;

import org.elastos.record.biz.dao.ShareDao;
import org.elastos.record.biz.service.ShareService;
import org.elastos.record.utility.entity.Share;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/20 16:10
 * @Description:
 */

@Service
@Transactional
public class ShareServiceImpl extends BaseServiceImpl<Share, ShareDao> implements ShareService {
    @Override
    public void checkNewInstance(Share instance) throws InsertNewInstanceException {

    }

    @Override
    public List<Share> querySumBalance(String address, Boolean isSend, Long startHeight, Long endHeight) {
        return this.getEntityDao().querySumBalance(address, isSend, startHeight, endHeight);
    }

    @Override
    public int updateSendStatusByBlockHeight(String address, Boolean isSend, Long startHeight, Long endHeight) {
        return this.getEntityDao().updateSendStatusByBlockHeight(address,isSend,startHeight,endHeight);
    }
}
