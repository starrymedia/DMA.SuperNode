package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.utility.entity.Voters;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * gen by hb.nie mapper 2019-06-14 10:27:12
 */
@Component
public interface VotersDao extends BaseDao<Voters> {

    Long queryNewHeight();

    PageQuery<Voters> queryList(
            @Param("pageNumber") Long pageNumber,
            @Param("pageSize") Long pageSize,
            @Param("address") String address,
            @Param("create")  Date create,
            @Param("end") Date end);

    PageQuery<Voters> queryInfoList(
            @Param("pageNumber") Long pageNumber,
            @Param("pageSize") Long pageSize,
            @Param("address") String address);

}
