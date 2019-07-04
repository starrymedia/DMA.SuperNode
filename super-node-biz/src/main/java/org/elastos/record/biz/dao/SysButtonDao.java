package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.elastos.record.utility.entity.SysButton;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * gen by hb.nie mapper 2019-05-19
 */
@Component
public interface SysButtonDao extends BaseDao<SysButton> {

    List<SysButton> findButtonByRoleId(@Param("roleId") Long roleId);
}
