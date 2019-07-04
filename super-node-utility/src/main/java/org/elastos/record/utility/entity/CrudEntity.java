package org.elastos.record.utility.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.DateTemplate;
import org.beetl.sql.core.annotatoin.Version;
import org.elastos.record.utility.util.JavaBeanUtil;


import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class CrudEntity implements Serializable {


    @AutoID
    private Long id;
    /**
     * 创建时间
     */
    @DateTemplate(accept = "minDate,maxDate")
    private Date createTime;
    /**
     * 修改时间
     */
    @DateTemplate(accept = "minDate,maxDate")
    private Date updateTime;
    /**
     * 逻辑删除
     */
    private Boolean deleted;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 动态扩展字段
     */
    private String json;

    @Version
    private Integer version;

    @Override
    public String toString() {
        return JavaBeanUtil.toString(this);
    }

    public JSONObject toJSONObject() {
        return JavaBeanUtil.toMap(this);
    }


}
