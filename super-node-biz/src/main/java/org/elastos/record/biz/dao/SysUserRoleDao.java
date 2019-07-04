package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.elastos.record.utility.entity.SysUser;
import org.elastos.record.utility.entity.SysUserRole;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * genAll by hb.nie mapper 2018-12-22 21:26:18
 */
@Component
public interface SysUserRoleDao extends BaseDao<SysUserRole> {

    int deleteByUserId(@Param("userId") Long userId);

    List<SysUserRole> findByUserId(@Param("userId") Long userId);

    int deleteByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<SysUserRole> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
