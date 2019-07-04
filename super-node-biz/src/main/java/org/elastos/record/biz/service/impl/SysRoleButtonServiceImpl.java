package org.elastos.record.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.biz.dao.SysRoleButtonDao;
import org.elastos.record.biz.service.SysRoleButtonService;
import org.elastos.record.utility.entity.SysRoleButton;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Slf4j
@Service
@Transactional
public class SysRoleButtonServiceImpl extends BaseServiceImpl<SysRoleButton, SysRoleButtonDao> implements SysRoleButtonService
{
    @Override
    public void checkNewInstance(SysRoleButton instance) throws InsertNewInstanceException {

    }

    @Override
    public int deleteByRoleId(Long roleId) {
        this.getEntityDao().deleteByRoleId(roleId ) ;
        return 0;
    }
}
