package org.elastos.record.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.ObjectUtils;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.biz.dao.SysRoleDao;
import org.elastos.record.biz.service.SysRoleService;
import org.elastos.record.utility.entity.SysRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Slf4j
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleDao> implements SysRoleService {
    @Override
    public void checkNewInstance(SysRole instance) throws InsertNewInstanceException {

    }

    @Override
    public List<SysRole> findRoleByUserId(Long userId) {
        return this.findRoleByUserId(userId ) ;
    }

    @Override
    public SysRole findStoreAdminRole() {
        SysRole entity = new SysRole();
        entity.setRoleType(RoleType.storeAdmin);
        return this.getEntityDao().templateOne(entity);
    }

    @Override
    public SysRole findAdminRole() {
        SysRole entity = new SysRole();
        entity.setRoleType(RoleType.admin);
        return this.getEntityDao().templateOne(entity);
    }
}
class RoleType{
      static  final  Integer storeAdmin = 0;
      static  final  Integer admin = -1 ;
}