package org.elastos.record.biz.service.impl;

import org.elastos.record.biz.dao.SysConfigDao;
import org.elastos.record.biz.service.SysConfigService;
import org.elastos.record.utility.entity.SysConfig;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/20 16:10
 * @Description:
 */
@Service
@Transactional
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, SysConfigDao> implements SysConfigService {
    @Override
    public void checkNewInstance(SysConfig instance) throws InsertNewInstanceException {

    }
}
