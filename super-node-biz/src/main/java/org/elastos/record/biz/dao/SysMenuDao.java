package org.elastos.record.biz.dao;


import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.elastos.record.utility.entity.SysMenu;
import org.elastos.record.utility.entity.SysMenu;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * genAll by hb.nie mapper 2018-12-22 21:26:18
 */
@Component
public interface SysMenuDao extends BaseDao<SysMenu> {
    List<SysMenu> findMenuByUserId(@Param("userId") Long userId);

    List<SysMenu> findMenuByRoleId(@Param("roleId") Long roleId);

    List<SysMenu> findMenus();

}
