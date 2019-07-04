
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

@Table(name="sys_role_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends CrudEntity implements Serializable {


	private Boolean flag ;
	private Long createUser ;
	private Long menuId ;
	private Long roleId ;
    /**
     修改人
    */
	private Long updateUser ;


}
