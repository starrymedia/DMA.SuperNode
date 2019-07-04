package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysRole;

import java.util.List;

public interface SysRoleService  extends BaseService<SysRole> {
    List<SysRole> findRoleByUserId(Long userId);

    SysRole findStoreAdminRole();

    SysRole findAdminRole();
}
