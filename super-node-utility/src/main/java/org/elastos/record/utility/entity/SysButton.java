
package org.elastos.record.utility.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**

  * 
  * gen by hn.nie 2019-05-22
  *
  */

@Table(name="sys_button")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysButton extends CrudEntity implements Serializable {


    /**
     创建人
    */
	private Long createUser ;
	private Long menuId ;
	private String name ;
	private String permission ;
    /**
     修改人
    */
	private Long updateUser ;


}
