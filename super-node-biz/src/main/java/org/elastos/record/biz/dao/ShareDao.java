package org.elastos.record.biz.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.elastos.record.utility.entity.Share;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


/**
 * gen by hb.nie mapper 2019-06-20 16:02:46
 */
@Component
public interface ShareDao extends BaseDao<Share> {

    List<Share> querySumBalance(@Param("address") String address,
                                @Param("isSend") Boolean isSend,
                                @Param("startHeight") Long startHeight,
                                @Param("endHeight") Long endHeight);

    int updateSendStatusByBlockHeight(@Param("address") String address,
                                      @Param("isSend") Boolean isSend,
                                      @Param("startHeight") Long startHeight,
                                      @Param("endHeight") Long endHeight);
}
