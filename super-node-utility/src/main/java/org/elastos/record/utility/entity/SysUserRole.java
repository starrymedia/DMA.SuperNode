
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

@Table(name="sys_user_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends CrudEntity implements Serializable {


	private Long buttonId ;
	private Long createUser ;
	private Long roleId ;
    /**
     修改人
    */
	private Long updateUser ;
	/**
	 * 用户编号
	 */
	private Long userId ;


}
