package org.elastos.record.biz.dao;


import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.elastos.record.utility.entity.SysRoleButton;
import org.elastos.record.utility.entity.SysRoleButton;
import org.springframework.stereotype.Component;


/**
 * genAll by hb.nie mapper 2018-12-22 21:26:18
 */
@Component
public interface SysRoleButtonDao extends BaseDao<SysRoleButton> {
    int deleteByRoleId(@Param("roleId") Long roleId);
}
