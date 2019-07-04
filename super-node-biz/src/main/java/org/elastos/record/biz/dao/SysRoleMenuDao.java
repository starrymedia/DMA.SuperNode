package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.elastos.record.utility.entity.SysRoleButton;
import org.elastos.record.utility.entity.SysRoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * genAll by hb.nie mapper 2018-12-22 21:26:18
 */
@Component
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
    int deleteSample(@Param("roleId") Long roleId);

    List<SysRoleMenu> sample(@Param("roleId") Long roleId);

    int deleteByTemplate(SysRoleMenu coreRoleMenu);
}
