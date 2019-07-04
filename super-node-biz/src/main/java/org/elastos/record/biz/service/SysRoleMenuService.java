package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysMenu;
import org.elastos.record.utility.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService extends BaseService<SysRoleMenu> {
    void deleteByTemplate(SysRoleMenu coreRoleMenu);

    void deleteSample(Long id);

    List<SysRoleMenu> sample(Long roleId);
}
