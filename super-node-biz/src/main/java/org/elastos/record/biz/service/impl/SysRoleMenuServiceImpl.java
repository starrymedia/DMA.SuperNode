package org.elastos.record.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.biz.dao.SysRoleMenuDao;
import org.elastos.record.biz.service.SysRoleMenuService;
import org.elastos.record.utility.entity.SysRoleMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Slf4j
@Service
@Transactional
public class SysRoleMenuServiceImpl  extends BaseServiceImpl<SysRoleMenu, SysRoleMenuDao > implements SysRoleMenuService {
    @Override
    public void checkNewInstance(SysRoleMenu instance) throws InsertNewInstanceException {

    }

    @Override
    public void deleteByTemplate(SysRoleMenu coreRoleMenu) {
        this.getEntityDao().deleteByTemplate(coreRoleMenu);
    }

    @Override
    public void deleteSample(Long id) {
        this.getEntityDao().deleteSample(id ) ;
    }

    @Override
    public List<SysRoleMenu> sample(Long roleId) {
        return this.getEntityDao().sample(roleId ) ;
    }
}
