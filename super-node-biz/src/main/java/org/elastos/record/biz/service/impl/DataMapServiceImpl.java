package org.elastos.record.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.dao.DataMapDao;
import org.elastos.record.biz.service.DataMapService;
import org.elastos.record.utility.entity.DataMap;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames = "sys_data_map")
@Slf4j
@Service
@Transactional
public class DataMapServiceImpl extends BaseServiceImpl<DataMap, DataMapDao> implements DataMapService {
    @Override
    public void checkNewInstance(DataMap instance) throws InsertNewInstanceException {

    }


    @Override
    public DataMap findOneByKey(String key) {
        DataMap dataMap = new DataMap();
        dataMap.setName(key);
        return this.templateOne(dataMap);
    }


    @Override
    public void templatePage(PageQuery<DataMap> pageQuery) {
        getEntityDao().templatePage(pageQuery);
    }

    @Override
    public boolean containsKey(String key) {
        return findOneByKey(key) != null;
    }
}
