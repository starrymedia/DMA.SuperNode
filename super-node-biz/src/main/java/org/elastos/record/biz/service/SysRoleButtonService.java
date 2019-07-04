package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysRoleButton;

public interface SysRoleButtonService extends BaseService<SysRoleButton> {
    int deleteByRoleId(Long roleId);

}
