package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.elastos.record.utility.entity.SysRole;
import org.elastos.record.utility.entity.SysRole;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * genAll by hb.nie mapper 2018-12-22 21:26:18
 */
@Component
public interface SysRoleDao extends BaseDao<SysRole> {
    List<SysRole> findRoleByUserId(@Param("userId") Long userId);

}
