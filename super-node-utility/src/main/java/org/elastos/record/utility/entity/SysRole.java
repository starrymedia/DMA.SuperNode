
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

@Table(name="sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends CrudEntity implements Serializable {


	/**
	 * 关联店铺
	 */
	private Long storeId;
    /**
     创建者
    */
	private Long  createUser ;
	/**
	 * 描述
	 */
	private String description ;
	/**
	 * 角色名称
	 */
	private String name ;
    /**
     修改人
    */
	private Long updateUser ;
	/**
	 * 角色类型[-1 系统管理员 , 0 店铺管理员]
	 */
	private Integer roleType;

}
