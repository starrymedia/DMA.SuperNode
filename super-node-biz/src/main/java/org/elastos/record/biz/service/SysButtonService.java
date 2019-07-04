package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysButton;

import java.util.List;

public interface SysButtonService extends BaseService<SysButton> {
    List<SysButton> findButtonByRoleId(Long coreRoleId);
}
