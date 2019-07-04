package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysUser;

public interface SysUserService extends BaseService<SysUser> {
    SysUser findByUserName(String username);
}
