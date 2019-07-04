package org.elastos.record.biz.service;

import org.elastos.record.utility.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {
    List<SysMenu> findMenus();

    List<SysMenu> findMenuByRoleId(Long roleId);

    List<SysMenu> findMenuByUserId(Long userId);
}
