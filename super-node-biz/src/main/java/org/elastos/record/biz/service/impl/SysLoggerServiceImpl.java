package org.elastos.record.biz.service.impl;


import lombok.extern.slf4j.Slf4j;

import org.elastos.record.biz.dao.SysLoggerDao;
import org.elastos.record.biz.service.SysLoggerService;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.entity.SysLogger;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* gen by hb.nie mapper 2019-05-20
*/

//@Slf4j
@Service
@Transactional
public class SysLoggerServiceImpl extends BaseServiceImpl<SysLogger, SysLoggerDao> implements SysLoggerService {
    @Override
    public void checkNewInstance(SysLogger instance) throws InsertNewInstanceException {
        ObjectUtils.check(new Object[]{
                instance , Action.ERROR
        });
    }

}
