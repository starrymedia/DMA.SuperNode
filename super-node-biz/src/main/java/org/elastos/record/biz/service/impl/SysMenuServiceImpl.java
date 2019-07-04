package org.elastos.record.biz.service.impl;


import lombok.extern.slf4j.Slf4j;

import org.elastos.record.biz.dao.SysMenuDao;
import org.elastos.record.biz.service.SysMenuService;

import org.elastos.record.utility.entity.SysMenu;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.ObjectUtils;
import org.elastos.record.utility.denum.Action;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* gen by hb.nie mapper 2019-05-20
*/

//@Slf4j
@Service
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuDao> implements SysMenuService {
    @Override
    public void checkNewInstance(SysMenu instance) throws InsertNewInstanceException {
        ObjectUtils.check(new Object[]{
                instance , Action.ERROR
        } );
    }

    @Override
    public List<SysMenu> findMenus() {
        return this.getEntityDao().findMenus();
    }

    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        return getEntityDao().findMenuByRoleId(roleId);
    }

    @Override
    public List<SysMenu> findMenuByUserId(Long userId) {
        return getEntityDao().findMenuByUserId(userId);
    }
}
