package org.elastos.record.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.biz.dao.SysUserRoleDao;
import org.elastos.record.biz.service.SysUserRoleService;
import org.elastos.record.utility.entity.SysUserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Slf4j
@Service
@Transactional
public class SysUserRoleServiceIMpl extends BaseServiceImpl<SysUserRole, SysUserRoleDao> implements SysUserRoleService  {
    @Override
    public void checkNewInstance(SysUserRole instance) throws InsertNewInstanceException {

    }

    @Override
    public List<SysUserRole> findByRoleIds(List<Long> singletonList) {
      return   this.getEntityDao().findByRoleIds(singletonList);
    }
}
