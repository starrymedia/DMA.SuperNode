package org.elastos.record.biz.dao;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.utility.entity.DataMap;
import org.springframework.stereotype.Component;

/**
 * genAll by hb.nie mapper 2018-12-22
 */
@Component
@SqlResource("dataMap")
public interface DataMapDao extends BaseDao<DataMap> {
    void templatePage(PageQuery<DataMap> query);
}
