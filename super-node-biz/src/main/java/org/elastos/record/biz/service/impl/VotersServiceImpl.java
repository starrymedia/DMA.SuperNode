package org.elastos.record.biz.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.biz.dao.VotersDao;
import org.elastos.record.biz.service.VotersService;
import org.elastos.record.utility.entity.Voters;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * gen by hb.nie mapper 2019-05-20
 */

//@Slf4j
@Service
@Transactional
public class VotersServiceImpl extends BaseServiceImpl<Voters, VotersDao> implements VotersService {


    @Override
    public void checkNewInstance(Voters instance) throws InsertNewInstanceException {

    }

    @Override
    public Long queryNewHeight() {
        return this.getEntityDao().queryNewHeight();
    }

    @Override
    public PageQuery<Voters> queryList(Long pageNumber, Long pageSize, String address,Date create, Date end) {
        return this.getEntityDao().queryList(pageNumber, pageSize, address,create,end);
    }

    @Override
    public PageQuery<Voters> queryInfoList(Long pageNumber, Long pageSize, String address) {
        return this.getEntityDao().queryInfoList(pageNumber,pageSize,address);
    }
}
