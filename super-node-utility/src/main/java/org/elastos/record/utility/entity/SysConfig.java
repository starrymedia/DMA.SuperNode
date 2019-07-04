
package org.elastos.record.utility.entity;

        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import org.beetl.sql.core.annotatoin.Table;

        import java.util.Date;

/**
 * gen by hn.nie 2019-06-20
 */

@Table(name = "sys_config")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfig extends CrudEntity {

    private static final long serialVersionUID = 123456789L;


    /**
     * 配置参数key
     */
    private String configKey;
    /**
     * 配置参数value
     */
    private String configValue;


}
