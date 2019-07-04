package org.elastos.record.biz.dao;


import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.List;

public interface BaseDao<T> extends BaseMapper<T> {
    List<T> sample(T entity);

    int deleteByIds(@Param("ids") Object[] ids);

    List<T> findByIds(@Param("ids") String[] ids);

    List<T> findByIds(@Param("ids") Collection<Long> ids);

}
