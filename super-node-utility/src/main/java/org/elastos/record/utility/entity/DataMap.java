
package org.elastos.record.utility.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;
import org.elastos.record.utility.util.JavaBeanUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * gen by hn.nie 2019-04-17
 */

@Table(name = "data_map")
@Data
@EqualsAndHashCode(callSuper = true)
public class DataMap extends CrudEntity implements Serializable {

    /**
     * key
     */
    private String name;
    /**
     * 值
     */
    private String value;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 描述
     */
    private String description;


    public JSONObject toJSONObject() {
        return JavaBeanUtil.toMap(this);
    }

    @Override
    public String toString() {
        return JavaBeanUtil.toString(this);
    }
}
