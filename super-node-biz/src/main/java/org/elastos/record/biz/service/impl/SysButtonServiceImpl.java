package org.elastos.record.biz.service.impl;


import lombok.extern.slf4j.Slf4j;

import org.elastos.record.biz.dao.SysButtonDao;

import org.elastos.record.biz.service.SysButtonService;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.entity.SysButton;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* gen by hb.nie mapper 2019-05-20
*/

//@Slf4j
@Service
@Transactional
public class SysButtonServiceImpl extends BaseServiceImpl<SysButton, SysButtonDao> implements SysButtonService {
    @Override
    public void checkNewInstance(SysButton instance) throws InsertNewInstanceException {
        ObjectUtils.check(new Object[]{
                instance , Action.ERROR
        } );
    }

    @Override
    public List<SysButton> findButtonByRoleId(Long coreRoleId) {
        return getEntityDao().findButtonByRoleId(coreRoleId);
    }
}
