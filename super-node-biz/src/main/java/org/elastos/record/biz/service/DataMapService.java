package org.elastos.record.biz.service;


import org.elastos.record.utility.entity.DataMap;

public interface DataMapService extends  BaseService<DataMap> {
    DataMap findOneByKey(String key);

    boolean containsKey(String name);
}
