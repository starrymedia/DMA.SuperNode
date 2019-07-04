package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends BaseService<SysUserRole> {
    List<SysUserRole> findByRoleIds(List<Long> singletonList);
}
