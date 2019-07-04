package org.elastos.record.biz.service;


import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.utility.entity.Voters;

import java.util.Date;


public interface VotersService extends BaseService<Voters> {
    Long queryNewHeight();
    PageQuery<Voters> queryList(
            Long pageNumber,
            Long pageSize,
            String address,
            Date create, Date end);

    PageQuery<Voters> queryInfoList(
           Long pageNumber,
             Long pageSize,
           String address);
}
