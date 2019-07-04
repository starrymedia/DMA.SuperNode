package org.elastos.record.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.ObjectUtils;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.biz.dao.SysUserDao;
import org.elastos.record.biz.service.SysUserService;
import org.elastos.record.utility.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * gen by hb.nie mapper 2019-05-20
 */

//@Slf4j
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserDao> implements SysUserService {
    @Override
    public void checkNewInstance(SysUser instance) throws InsertNewInstanceException {
        ObjectUtils.check(new Object[]{
                instance , Action.ERROR
        });
    }

    @Override
    public SysUser findByUserName(String username) {
        return getEntityDao().findByUserName(username);
    }
}
