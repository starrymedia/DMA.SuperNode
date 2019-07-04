package org.elastos.record.utility.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.beetl.sql.core.engine.PageQuery;
import org.elastos.record.utility.entity.DataMap;
import org.elastos.record.utility.util.JavaBeanUtil;


import java.io.Serializable;
import java.util.List;


/**
 * 分页数据类型包装
 */
@Getter
@Setter
public class UiPageFrame implements Serializable {

    private List  list;
    private long pageNum;
    private long pageSize;
    private long pages;
    private long total;


    public UiPageFrame() {
    }


    public UiPageFrame(Long pageNum, Long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public UiPageFrame(PageQuery<? extends  Serializable> query) {
        this.list = query.getList() ;
        this.pageNum = query.getPageNumber();
        this.pageSize = query.getPageSize();
        this.pages = query.getTotalPage();
        this.total = query.getTotalRow();
    }


    public JSONObject toJSONObject(){
        return JavaBeanUtil.toMap(this);
    }
}
